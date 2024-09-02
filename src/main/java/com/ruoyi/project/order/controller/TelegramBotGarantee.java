//package com.ruoyi.project.order.controller;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.CollectionUtils;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Log4j2
//public class TelegramBotGarantee extends TelegramLongPollingBot {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        // 如果是按钮点击事件，处理回调数据
//        if (update.hasCallbackQuery()) {
//
//        }
//        //飞机用户第一次加群
//        if (!CollectionUtils.isEmpty(update.getMessage().getNewChatMembers())) {
//
//        }
//        //消息通知事件
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            Integer messageId = update.getMessage().getMessageId();
//            long chatId = update.getMessage().getChatId();
//
//            // 创建ReplyKeyboardMarkup
//            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//            replyKeyboardMarkup.setSelective(true);
//            replyKeyboardMarkup.setResizeKeyboard(true);
//            replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//            KeyboardRow keyboardFirstRow = new KeyboardRow();
//            keyboardFirstRow.add(new KeyboardButton("拉专群"));
//            keyboardFirstRow.add(new KeyboardButton("开公群"));
//
//            KeyboardRow keyboardSendRow = new KeyboardRow();
//            keyboardSendRow.add(new KeyboardButton("咨询/解封"));
//            keyboardSendRow.add(new KeyboardButton("纠纷仲裁"));
//
//            KeyboardRow keyboardThreeRow = new KeyboardRow();
//            keyboardThreeRow.add(new KeyboardButton("买广告"));
//            keyboardThreeRow.add(new KeyboardButton("买会员"));
//
//            KeyboardRow keyboardFourRow = new KeyboardRow();
//            keyboardFourRow.add(new KeyboardButton("资源对接"));
//            keyboardFourRow.add(new KeyboardButton("投诉"));
//
//            KeyboardRow keyboardFiveRow = new KeyboardRow();
//            keyboardFiveRow.add(new KeyboardButton("错群恢复"));
//            keyboardFiveRow.add(new KeyboardButton("自助验群"));
//
//            List<KeyboardRow> keyboardRows = new ArrayList<>();
//            keyboardRows.add(keyboardFirstRow);
//            keyboardRows.add(keyboardSendRow);
//            keyboardRows.add(keyboardThreeRow);
//            keyboardRows.add(keyboardFourRow);
//            keyboardRows.add(keyboardFiveRow);
//
//            if (messageText.equalsIgnoreCase("拉专群") ||
//                    messageText.equalsIgnoreCase("开公群") ||
//                    messageText.equalsIgnoreCase("咨询/解封") ||
//                    messageText.equalsIgnoreCase("纠纷仲裁") ||
//                    messageText.equalsIgnoreCase("买广告") ||
//                    messageText.equalsIgnoreCase("买会员") ||
//                    messageText.equalsIgnoreCase("资源对接") ||
//                    messageText.equalsIgnoreCase("投诉") ||
//                    messageText.equalsIgnoreCase("错群恢复") ||
//                    messageText.equalsIgnoreCase("自助验群")) {
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setChatId(chatId);
//                sendMessage.setText("正在分配人工客服，请耐心等待…\n" +
//                        "等待期间，您可先将需要办理的业务内容进行描述，人工客服稍候将直接回复您\uD83D\uDE0A");
//                try {
//                    execute(sendMessage);
//                    return;
//                } catch (TelegramApiException e) {
//                    log.info("拉专群信息错误: {}", e.getMessage());
//                }
//            }
//            // 将行添加到键盘
//            replyKeyboardMarkup.setKeyboard(keyboardRows);
//            // 发送消息
//            SendMessage message = new SendMessage();
//            message.setChatId(chatId); // 设置聊天ID
//            message.setText("请选择一个功能:");
//            message.setReplyMarkup(replyKeyboardMarkup);
//            try {
//                execute(message); // 执行发送消息
//            } catch (TelegramApiException e) {
//                log.info("消息执行异常: {}", e.getMessage());
//            }
//        }
//
//    }
//
//    @Override
//    public String getBotUsername() {
//        // 替换为你的 Bot 的用户名
//        return "@oykf_bot";
//    }
//
//    @Override
//    public String getBotToken() {
//        // 替换为你的 Bot 的 Token
//        return "7130334331:AAHquum6IOQWikJLfaofZK3hc1Bt1o2rYIU";
//    }
//
//
//}
