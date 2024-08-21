package com.ruoyi.project.order.controller;

import com.ruoyi.common.utils.bot.SendUtils;
import com.ruoyi.project.bot.group.service.IBotGroupService;
import com.ruoyi.project.common.RedisConstantKey;
import com.ruoyi.project.enu.OrderStatus;
import com.ruoyi.project.group.domain.BotGroupList;
import com.ruoyi.project.group.service.IBotGroupListService;
import com.ruoyi.project.order.domain.BotOrderList;
import com.ruoyi.project.order.service.IBotOrderListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
public class TelegramBotPoll extends TelegramLongPollingBot {

    @Autowired
    private IBotOrderListService iBotOrderListService;

    @Autowired
    private IBotGroupListService iBotGroupListService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onUpdateReceived(Update update) {
        BotOrderList botOrderList = new BotOrderList();
        // 如果是按钮点击事件，处理回调数据
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String text = update.getCallbackQuery().getMessage().getText();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            switch (callbackData) {
                case "button1":
                    break;
                case "button2":
                    break;
                default:
            }
            if (callbackData.equalsIgnoreCase("button1")) {
                InlineKeyboardMarkup inlineKeyboardMarkup = orderFinsh(messageId);
                try {
                    execute(SendUtils.sendMessageInit2(chatId, text, inlineKeyboardMarkup));
                    updateOrderFinsh(update, botOrderList, text);
                } catch (TelegramApiException e) {
                    log.info("报备信息异常: {}", e.getMessage());
                }
            } else {
                try {
                    InlineKeyboardMarkup inlineKeyboardMarkup = caleTemplate(messageId);
                    execute(SendUtils.sendMessageInit2(chatId, text, inlineKeyboardMarkup));
                    //取消订单
                    updateOrderInfo(update, botOrderList, text);
                } catch (TelegramApiException e) {
                    log.info("信息回调成功：{}", e.getMessage());
                }
                return;
            }
        }
        //群组id
        Long chatIds = 0L;
        if (update.hasMessage() && update.getMessage().hasText()) {
            BotGroupList botGroupList = new BotGroupList();
            //获取群组id
            Chat chat = update.getMessage().getChat();
            if (chat.isUserChat()) {
                // 处理私聊消息
            } else if (chat.getType().equalsIgnoreCase("supergroup")) {
                // 处理群组消息
                chatIds = chat.getId();
                // 保存群组 ID 到数据库或其他地方
                redisTemplate.opsForValue().set(RedisConstantKey.GROUPID, chatIds);
                botGroupList.setGroupId(chatIds);
                botGroupList.setGroupName(chat.getTitle());
                botGroupList.setUserName(update.getMessage().getFrom().getUserName());
                botGroupList.setNickName(update.getMessage().getFrom().getFirstName());
                iBotGroupListService.insertBotGroupList(botGroupList);
            }
            String messageText = update.getMessage().getText();
            Integer messageId = update.getMessage().getMessageId();
            long chatId = update.getMessage().getChatId();
            //如果回调确认消息 包含(/start) 则会执行这个命令
            if (messageText.startsWith("/start")) {
                try {
                    String text = "报备完成";
                    execute(SendUtils.sendMessageInit(chatId, text));
                    if (Boolean.TRUE.equals(redisTemplate.hasKey(RedisConstantKey.AUDITVERIFICATION))) {
                        Object o = redisTemplate.opsForValue().get(RedisConstantKey.AUDITVERIFICATION);
                        InlineKeyboardMarkup markup = reportCompleted(messageId);
                        //取出来当前发送消息的群组id
                        if (Boolean.TRUE.equals(redisTemplate.hasKey(RedisConstantKey.GROUPID))) {
                            Object o1 = redisTemplate.opsForValue().get(RedisConstantKey.GROUPID);
                            assert o1 != null;
                            assert o != null;
                            execute(SendUtils.sendMessageInit(Long.valueOf(o1.toString()), o.toString(), markup));
                        }
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                if (messageText.equals("./start")) {
                    execute(SendUtils.sendMessageInit(chatId, "欢迎使用报备机器人"));
                }
                // 处理用户消息
                if ("报备".equals(messageText)) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("报备模板中必须包含: \n" +
                            "交易方对接人：@hwdb\n" +
                            "交易金额：100u\n" +
                            "订单完成时间：1天");
                    execute(sendMessage);
                    insertOrderInfo(update, botOrderList, sendMessage.getText());
                } else if (messageText.contains("地区") || messageText.contains("地址")
                        || messageText.contains("交易金额") || messageText.contains("交易方对接人")
                        || messageText.contains("客户结算完整地址")) {

                    String messagTexts = "发送成功，请在公群内查看";
                    //将发送的消息存入到 缓存redis
                    execute(SendUtils.sendMessageInit(chatId, messagTexts));
                    // 创建一个带有按钮的键盘
                    InlineKeyboardMarkup markup = createKeyboard(messageId);
                    if (Boolean.TRUE.equals(redisTemplate.hasKey(RedisConstantKey.GROUPID))) {
                        Object o = redisTemplate.opsForValue().get(RedisConstantKey.GROUPID);
                        assert o != null;
                        execute(SendUtils.sendMessageInit(Long.valueOf(o.toString()), messageText, markup));
                    }
                    redisTemplate.opsForValue().set(RedisConstantKey.AUDITVERIFICATION, messageText);
                    insertOrderInfo(update, botOrderList, messagTexts);
                    insertOrderInfo(update, botOrderList, messageText);
                }
            } catch (TelegramApiException e) {
                log.info("机器人消息发送异常: {}", e.getMessage());
            }
        }
    }

    private void insertOrderInfo(Update update, BotOrderList botOrderList, String messagTexts) {
        botOrderList.setTradeInfo(messagTexts);
        botOrderList.setTradeUser(update.getMessage().getChat().getUserName());
        botOrderList.setTradeTime(new Date());
        botOrderList.setInitiatorReportUser(update.getMessage().getFrom().getFirstName());
        botOrderList.setOrderStatus(OrderStatus.CONFIRMED.getCode());
        botOrderList.setCreateTime(new Date());
        iBotOrderListService.insertBotOrderList(botOrderList);
    }

    private void updateOrderInfo(Update update, BotOrderList botOrderList, String messagTexts) {
        botOrderList.setTradeInfo(messagTexts);
        botOrderList.setTradeUser(update.getCallbackQuery().getFrom().getUserName());
        botOrderList.setInitiatorReportUser(update.getCallbackQuery().getFrom().getFirstName());
        botOrderList.setOrderStatus(OrderStatus.CANCELED.getCode());
        botOrderList.setUpdateTime(new Date());
        iBotOrderListService.insertBotOrderList(botOrderList);
    }

    private void updateOrderFinsh(Update update, BotOrderList botOrderList, String messagTexts) {
        botOrderList.setTradeInfo(messagTexts);
        botOrderList.setTradeUser(update.getCallbackQuery().getFrom().getUserName());
        botOrderList.setInitiatorReportUser(update.getCallbackQuery().getFrom().getFirstName());
        botOrderList.setOrderStatus(OrderStatus.COMPLETED.getCode());
        botOrderList.setUpdateTime(new Date());
        iBotOrderListService.insertBotOrderList(botOrderList);
    }

    @Override
    public String getBotUsername() {
        // 替换为你的 Bot 的用户名
        return "@hawkins8897bot";
    }

    @Override
    public String getBotToken() {
        // 替换为你的 Bot 的 Token
        return "7313816769:AAGbH_WqbZzWov2QKQHO1isgQUR9b0vmvPI";
    }

    private InlineKeyboardMarkup createKeyboard(Integer messageId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("确认");
        button1.setCallbackData("button1");
        button1.setUrl("https://t.me/hawkins8897bot?start=" + messageId);
        row.add(button1);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("取消");
        button2.setCallbackData("button2");
        row.add(button2);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }


    private InlineKeyboardMarkup caleTemplate(Integer messageId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("已取消");
        button2.setCallbackData("button2");
        row.add(button2);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }

    private InlineKeyboardMarkup baobeiFinsh(Integer messageId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setCallbackData("button1");
        button1.setUrl("https://t.me/hawkins8897bot?start=" + messageId);
        row.add(button1);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }

    //通知报备群-报备完成
    private InlineKeyboardMarkup reportCompleted(Integer messageId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("报备成功");
        button1.setCallbackData("button1");
        row.add(button1);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }

    //通知报备群 订单完成
    private InlineKeyboardMarkup orderFinsh(Integer messageId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("订单完成");
        button1.setCallbackData("button1");
        row.add(button1);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }
}
