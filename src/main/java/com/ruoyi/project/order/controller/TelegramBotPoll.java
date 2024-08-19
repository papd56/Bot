package com.ruoyi.project.order.controller;

import com.ruoyi.common.constant.ChatType;
import com.ruoyi.common.utils.DateTimeUtil;
import com.ruoyi.common.utils.bot.SendUtils;
import com.ruoyi.project.enu.OrderStatus;
import com.ruoyi.project.order.domain.BotOrderList;
import com.ruoyi.project.order.service.IBotOrderListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class TelegramBotPoll extends TelegramLongPollingBot {

    @Autowired
    private IBotOrderListService iBotOrderListService;

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
                InlineKeyboardMarkup inlineKeyboardMarkup = baobeiFinsh(messageId);
                try {
                    execute(SendUtils.sendMessageInit2(chatId, text, inlineKeyboardMarkup));
                } catch (TelegramApiException e) {
                    log.info("报备信息异常: {}", e.getMessage());
                }
            }
            // 创建一个带有按钮的键盘
            try {
                InlineKeyboardMarkup inlineKeyboardMarkup = caleTemplate(messageId);
                execute(SendUtils.sendMessageInit2(chatId, text, inlineKeyboardMarkup));
                //取消订单
//                updateOrderInfo(update, botOrderList, text);
            } catch (TelegramApiException e) {
                log.info("信息回调成功：{}", e.getMessage());
            }
            return;
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Integer messageId = update.getMessage().getMessageId();
            long chatId = update.getMessage().getChatId();
            Chat chat = update.getMessage().getChat();
            if (chat.getType().equalsIgnoreCase(ChatType.GROUP)) {
                // 处理群组
            } else if (chat.getType().equals(ChatType.PRIVATE)) {
                // 处理私聊
            } else if (chat.getType().equalsIgnoreCase(ChatType.CHANNEL)) {
                // 处理频道
            }
            //如果回调确认消息 包含(/start) 则会执行这个命令
            if (messageText.startsWith("/start")) {
                try {
                    String text = "报备完成";
                    execute(SendUtils.sendMessageInit(chatId, text));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            // 创建一个带有按钮的键盘
            InlineKeyboardMarkup markup = createKeyboard(messageId);
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
//                    insertOrderInfo(update, botOrderList, sendMessage.getText());
                } else if (messageText.contains("地区") || messageText.contains("地址")
                        || messageText.contains("交易金额") || messageText.contains("交易方对接人")
                        || messageText.contains("客户结算完整地址")) {
                    execute(SendUtils.sendMessageInit(-1002228392062L, messageText, markup));
                    String messagTexts = "发送成功，请在公群内查看";
                    execute(SendUtils.sendMessageInit(chatId, messagTexts));
//                    insertOrderInfo(update, botOrderList, messagTexts);
                }
            } catch (TelegramApiException e) {
                log.info("机器人消息发送异常: {}", e.getMessage());
            }
        }
    }


    private void insertOrderInfo(Update update, BotOrderList botOrderList, String messagTexts) {
        botOrderList.setTradeInfo(messagTexts);
        botOrderList.setTradeUser(update.getMessage().getChat().getUserName());
        botOrderList.setTradeTime(DateTimeUtil.formatTimestamp(update.getMessage().getDate()));
        botOrderList.setInitiatorReportUser(update.getMessage().getFrom().getFirstName());
        botOrderList.setOrderStatus(OrderStatus.CONFIRMED.getCode());
        iBotOrderListService.insertBotOrderList(botOrderList);
        //创建报备订单
    }

    private void updateOrderInfo(Update update, BotOrderList botOrderList, String messagTexts) {
        botOrderList.setTradeInfo(messagTexts);
        botOrderList.setTradeUser(update.getMessage().getChat().getUserName());
        botOrderList.setTradeTime(DateTimeUtil.formatTimestamp(update.getMessage().getDate()));
        botOrderList.setInitiatorReportUser(update.getMessage().getFrom().getFirstName());
        botOrderList.setOrderStatus(OrderStatus.CANCELED.getCode());
        botOrderList.setUpdateTime(new Date());
        //创建报备订单
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

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        TelegramLongPollingBot bot = new TelegramBotPoll();
        botsApi.registerBot(bot);
    }

}
