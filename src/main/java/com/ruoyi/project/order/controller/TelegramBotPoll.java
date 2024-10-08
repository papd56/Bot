package com.ruoyi.project.order.controller;

import com.ruoyi.common.utils.OrderNoGenerator;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bot.SendUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.ruoyi.project.common.RedisConstantKey;
import com.ruoyi.project.enu.OrderStatus;
import com.ruoyi.project.group.domain.BotGroupList;
import com.ruoyi.project.group.mapper.BotGroupListMapper;
import com.ruoyi.project.group.service.IBotGroupListService;
import com.ruoyi.project.order.domain.BotOrderList;
import com.ruoyi.project.order.mapper.BotOrderListMapper;
import com.ruoyi.project.order.service.IBotOrderListService;
import com.ruoyi.project.user.domain.BotUserList;
import com.ruoyi.project.user.mapper.BotUserListMapper;
import com.ruoyi.project.user.service.IBotUserListService;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class TelegramBotPoll extends TelegramLongPollingBot {

    @Autowired
    private IBotOrderListService iBotOrderListService;

    @Autowired
    private IBotGroupListService iBotGroupListService;

    @Autowired
    private BotUserListMapper botUserListMapper;

    @Autowired
    private BotGroupListMapper botGroupListMapper;

    @Autowired
    private BotOrderListMapper botOrderListMapper;

    @Autowired
    private IBotUserListService iBotUserListService;

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
                    String texts = update.getCallbackQuery().getFrom().getUserName();
                    String replace = texts.replace("@", "");
                    String replace1 = text.replace(replace, "******");
                    execute(SendUtils.sendMessageInit2(chatId, replace1, inlineKeyboardMarkup));
//                    updateOrderFinsh(update, botOrderList, text);
                } catch (TelegramApiException e) {
                    log.info("报备信息异常: {}", e.getMessage());
                }
            } else {
                try {
                    InlineKeyboardMarkup inlineKeyboardMarkup = caleTemplate(messageId);
                    execute(SendUtils.sendMessageInit2(chatId, text, inlineKeyboardMarkup));
                    //取消订单 修改订单状态已取消
                    updateOrderInfo(update, botOrderList, text);
                } catch (TelegramApiException e) {
                    log.info("信息回调成功：{}", e.getMessage());
                }
                return;
            }
        }
        //飞机用户第一次加群
        if (!CollectionUtils.isEmpty(update.getMessage().getNewChatMembers())) {
            //第一次进群初始化群组信息
            BotGroupList botGroupList = new BotGroupList();
            botGroupList.setGroupId(update.getMessage().getChat().getId());
            if (isAdministratorOrCreator(update.getMessage().getChat().getId(), update.getMessage().getNewChatMembers().get(0).getId())) {
                botGroupList.setIsNotManage(1L);
            } else {
                botGroupList.setIsNotManage(0L);
            }
            botGroupList.setGroupName(update.getMessage().getChat().getTitle());
            botGroupList.setUserName(update.getMessage().getNewChatMembers().get(0).getUserName());
            botGroupList.setNickName(update.getMessage().getNewChatMembers().get(0).getFirstName());
            BotGroupList botGroupList1 = botGroupListMapper.selectBotGroupByIdAndUserName(botGroupList.getGroupId(), botGroupList.getUserName());
            if (botGroupList1 == null) {
                iBotGroupListService.insertBotGroupList(botGroupList);
            } else {
                botGroupList1.setGroupId(botGroupList.getGroupId());
                botGroupList1.setGroupName(botGroupList.getGroupName());
                botGroupList1.setUserName(botGroupList.getUserName());
                botGroupList1.setNickName(botGroupList.getNickName());
                iBotGroupListService.updateBotGroupList(botGroupList1);
            }

            //第一次进群初始化用户信息
            BotUserList botUserList = new BotUserList();
            //初始化用户信息
            botUserList.setGroupId(update.getMessage().getChat().getId());
            botUserList.setTgUnqiueId(update.getMessage().getNewChatMembers().get(0).getId());
            botUserList.setUserName(update.getMessage().getNewChatMembers().get(0).getUserName());
            botUserList.setGroupName(update.getMessage().getChat().getTitle());
            botUserList.setNickName(update.getMessage().getNewChatMembers().get(0).getFirstName());

            BotUserList botUserList1 = botUserListMapper.selectBotGroupByIdAndUserName(botUserList.getTgUnqiueId(), botUserList.getUserName());
            if (botUserList1 == null) {
                iBotUserListService.insertBotUserList(botUserList);
            } else {
                botUserList1.setGroupId(botUserList.getGroupId());
                botUserList1.setTgUnqiueId(botUserList.getTgUnqiueId());
                botUserList1.setUserName(botUserList.getUserName());
                botUserList1.setNickName(botUserList.getNickName());
                botUserList1.setGroupName(botUserList.getGroupName());
                iBotUserListService.updateBotUserList(botUserList1);
            }

        }


        if (update.hasMessage() && update.getMessage().hasText()) {
            // 在超级群组中，可以获取更多的用户信息
            User user = update.getMessage().getFrom();
//            if (isAdministratorOrCreator(update.getMessage().getChatId(), user.getId())) {
            String messageText = update.getMessage().getText();
            Integer messageId = update.getMessage().getMessageId();
            long chatId = update.getMessage().getChatId();
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
                    return;
                }
                //获取报备用户名称
                BotUserList botUserList2 = null;
                if (messageText.contains("地区") || messageText.contains("地址")
                        || messageText.contains("交易金额") || messageText.contains("交易方对接人")
                        || messageText.contains("客户结算完整地址")) {
                    if (!StringUtils.isEmpty(update.getMessage().getEntities().get(0).getText())) {
                        String userName = update.getMessage().getEntities().get(0).getText();
                        String replace = userName.replace("@", "");
                        botUserList2 = botUserListMapper.selectByName(replace);
                    }
                    //将发送的消息存入到 缓存redis
                    redisTemplate.opsForValue().set(RedisConstantKey.AUDITVERIFICATION, messageText);
                    // 创建一个带有按钮的键盘
                    InlineKeyboardMarkup markup = createKeyboard(messageId);
                    if (botUserList2 != null) {
                        String text = update.getMessage().getEntities().get(0).getText();
                        String replace = text.replace("@", "");
                        String replace1 = messageText.replace(replace, "******");
                        execute(SendUtils.sendMessageInit(botUserList2.getGroupId(), replace1, markup));
                        //消息通知用户报备成功
                        InlineKeyboardMarkup success = reportCompleted(messageId);
                        execute(SendUtils.sendMessageInit(botUserList2.getTgUnqiueId(), messageText, success));
                    } else {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText("报备用户不存在");
                        sendMessage.setChatId(chatId);
                        execute(sendMessage);
                        return;
                    }

                    String messagTexts = "发送成功，请在公群内查看";
                    execute(SendUtils.sendMessageInit(chatId, messagTexts));
                    // 正则表达式：匹配"交易金额:"后面的数字和"元"
                    Pattern pattern = Pattern.compile("交易金额：(\\d+)");
                    Matcher matcher = pattern.matcher(messageText);
                    if (matcher.find()) {
                        String amountStr = matcher.group(1);
                        BigDecimal bigDecimal = new BigDecimal(amountStr);
                        botOrderList.setTransactionAmount(bigDecimal);
                    }
                    BotOrderList botOrderList1 = new BotOrderList();
                    botOrderList1.setTransactionAmount(BigDecimal.ZERO);
//                    insertOrderInfo(update, botOrderList1, messagTexts);
                    insertOrderInfo(update, botOrderList, messageText);
                }

                //如果请求是b0 操作则显示账单统计
                if (messageText.equalsIgnoreCase("b0")) {
                    String userName = update.getMessage().getFrom().getUserName();
                    Long groupId = update.getMessage().getChat().getId();
                    BotGroupList botGroupList = botGroupListMapper.selectBotGroupByIdAndUserName(groupId, userName);
                    SendMessage sendMessage = getSendMessage(chatId, botGroupList);
                    execute(sendMessage);
                }

                //我的订单 指令
                if (messageText.equalsIgnoreCase("我的订单")) {
                    String userName = update.getMessage().getFrom().getUserName();
                    Long groupId = update.getMessage().getChat().getId();
                    BotUserList botUserList1 = botUserListMapper.selectBotGroupByIdAndUserName(groupId, userName);
                    BotGroupList botGroupList = botGroupListMapper.selectBotGroupByIdAndUserName(botUserList1.getGroupId(), userName);
                    SendMessage sendMessage = myOrder(groupId, botGroupList);
                    execute(sendMessage);
                    //根据用户名查询当前用户的所有订单
                    InlineKeyboardMarkup inlineKeyboardMarkup = myOrder();
                    List<BotOrderList> botOrderLists = botOrderListMapper.selectByNameList(userName);
                    for (BotOrderList botOrderList1 : botOrderLists) {
                        SendMessage sendMessageList = myOrderList(groupId, botGroupList, botOrderList1, inlineKeyboardMarkup);
                        execute(sendMessageList);
                    }
                }
                //我的报备 指令
                if (messageText.equalsIgnoreCase("我的报备")) {
                    String userName = update.getMessage().getFrom().getUserName();
                    Long groupId = update.getMessage().getChat().getId();
                    BotUserList botUserList1 = botUserListMapper.selectBotGroupByIdAndUserName(groupId, userName);
                    BotGroupList botGroupList = botGroupListMapper.selectBotGroupByIdAndUserName(botUserList1.getGroupId(), userName);
                    //根据用户名查询当前用户的所有订单
                    List<BotOrderList> botOrderLists = botOrderListMapper.selectByNameList(userName);
                    for (BotOrderList botOrderList1 : botOrderLists) {
                        SendMessage sendMessageList = myReport(groupId, botGroupList, botOrderList1);
                        execute(sendMessageList);
                    }
                }

                //如果回调确认消息 包含(/start) 则会执行这个命令
                if (messageText.startsWith("/start")) {
                    BotUserList botUserList1 = botUserListMapper.selectBotGroupByIdAndUserName(update.getMessage().getFrom().getId(), update.getMessage().getFrom().getUserName());
                    if (botUserList1 != null) {
                        String text = "报备完成";
                        execute(SendUtils.sendMessageInit(chatId, text));
                        //修改订单状态为已完成
//                        String userName = update.getMessage().getFrom().getUserName();
//                        Long groupId = update.getMessage().getChat().getId();
//                        BotUserList botUserList = botUserListMapper.selectBotGroupByIdAndUserName(groupId, userName);
//                        BotGroupList botGroupList = botGroupListMapper.selectBotGroupByIdAndUserName(botUserList.getGroupId(), userName);
//                        iBotOrderListService.updateBotOrderList(botOrderList)
                    } else {
                        String text = "暂无权限";
                        execute(SendUtils.sendMessageInit(chatId, text));
                        return;
                    }
                    if (Boolean.TRUE.equals(redisTemplate.hasKey(RedisConstantKey.AUDITVERIFICATION))) {
                        Object o = redisTemplate.opsForValue().get(RedisConstantKey.AUDITVERIFICATION);
                        String text = update.getMessage().getChat().getUserName();
                        String replace = text.replace("@", "");
                        assert o != null;
                        String replace1 = o.toString().replace(replace, "******");

                        InlineKeyboardMarkup markup = orderFinsh(messageId);
                        //取出来当前发送消息的群组id
                        execute(SendUtils.sendMessageInit(botUserList1.getGroupId(), replace1, markup));
                    }
                }
            } catch (TelegramApiException e) {
                log.info("机器人消息发送异常: {}", e.getMessage());
            }
//            }
//            else {
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setChatId(update.getMessage().getChatId());
//                sendMessage.setText("暂无权限");
//                try {
//                    execute(sendMessage);
//                } catch (TelegramApiException e) {
//                    log.info("暂无权限操作: {}", e.getMessage());
//                }
//            }
        }

    }

    private static @NotNull SendMessage getSendMessage(long chatId, BotGroupList botGroupList) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(botGroupList.getGroupName() + "\n"
                + "交易完成:" + botGroupList.getFinishAmount() + "\n" + "交易中:" + botGroupList.getTrandPendingAmount() + "\n" +
                "剩下可报备金额:" + botGroupList.getBalance());
        return sendMessage;
    }

    private static @NotNull SendMessage myOrder(long chatId, BotGroupList botGroupList) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(botGroupList.getGroupNumber() + "\n"
                + "交易完成金额:" + botGroupList.getFinishAmount() + "\n" + "交易中:" + botGroupList.getTrandPendingAmount() + "\n" +
                "剩下可报备金额:" + botGroupList.getBalance());
        return sendMessage;
    }

    private static @NotNull SendMessage myOrderList(long chatId, BotGroupList botGroupList, BotOrderList botOrderList, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
//        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setParseMode("HTML");
        sendMessage.setText(botGroupList.getGroupNumber() + "订单" + "交易方@" + botOrderList.getTradeUser() + "金额" + botOrderList.getTransactionAmount() + OrderStatus.getDesc(botOrderList.getOrderStatus()));
        return sendMessage;
    }

    private static @NotNull SendMessage myReport(long chatId, BotGroupList botGroupList, BotOrderList botOrderList) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("公群" + botGroupList.getGroupNumber() + "订单" + botOrderList.getOrderNumber() + "金额" + botOrderList.getTransactionAmount() + OrderStatus.getDesc(botOrderList.getOrderStatus()));
        return sendMessage;
    }

    private void insertOrderInfo(Update update, BotOrderList botOrderList, String messagTexts) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // 格式化日期为字符串
        String formattedDate = formatter.format(date);
        botOrderList.setTradeInfo(messagTexts);
        botOrderList.setTradeUser(update.getMessage().getChat().getUserName());
        botOrderList.setTradeTime(formattedDate);
        botOrderList.setInitiatorReportUser(update.getMessage().getFrom().getFirstName());
        botOrderList.setOrderStatus(OrderStatus.CONFIRMED.getCode());
        botOrderList.setCreateTime(new Date());
        botOrderList.setOrderNumber(OrderNoGenerator.generateOrderNo());
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
        return "@OKXbaobei_bot";
    }

    @Override
    public String getBotToken() {
        // 替换为你的 Bot 的 Token
        return "7527507809:AAHYJ8KXoTBlUdJZmRfsPdQTeA4MYXnppxY";
    }

    private InlineKeyboardMarkup createKeyboard(Integer messageId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("确认");
        button1.setCallbackData("button1");
        button1.setUrl("https://t.me/OKXbaobei_bot?start=" + messageId);
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

    //指令 我的订单
    private InlineKeyboardMarkup myOrder() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button2.setCallbackData("button2");
        button2.setText("2");
        button3.setCallbackData("button3");
        button3.setText("3");
        button4.setCallbackData("button4");
        button4.setText("4");
        button1.setText("1");
        button1.setCallbackData("button1");
        row.add(button1);
        row.add(button2);
        row.add(button3);
        row.add(button4);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }

    //指令 我的报备
    private InlineKeyboardMarkup myReport() {
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


    public boolean isAdministratorOrCreator(long chatId, long userId) {
        try {
            GetChatMember getChatMember = new GetChatMember();
            getChatMember.setChatId(chatId);
            getChatMember.setUserId(userId);
            ChatMember chatMember = execute(getChatMember);
            return chatMember.getStatus().equals("administrator")
                    || chatMember.getStatus().equals("creator");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }
}
