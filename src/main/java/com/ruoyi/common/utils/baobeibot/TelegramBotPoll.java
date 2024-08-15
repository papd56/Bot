package com.ruoyi.common.utils.baobeibot;

import com.ruoyi.common.constant.ChatType;
import com.ruoyi.common.utils.bot.SendUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Collections;
import java.util.List;

import static com.ruoyi.common.utils.bot.SendUtils.sendMessageInit;
import static net.sf.jsqlparser.parser.feature.Feature.execute;

@Log4j2
@Component
public class TelegramBotPoll extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        TelegramBot telegramBot = new TelegramBot() {
            @Override
            public String getBotUsername() {
                // 替换为你的 Bot 的用户名
                return "@qunbaobei8bot";
            }

            @Override
            public String getBotToken() {
                // 替换为你的 Bot 的 Token
                return "7237081474:AAGsSnjPvvr1RLOgdrQjA9XNl-JrV0bQ-5o";
            }
        };
        String botToken = telegramBot.getBotToken();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            Chat chat = update.getMessage().getChat();
            String chatTitle = chat.getTitle();
            String chatType = chat.getType().toString();
            Boolean hasHiddenMembers = chat.getHasHiddenMembers();


            if (chat.getType().equalsIgnoreCase(ChatType.GROUP)) {
                // 处理群组
            } else if (chat.getType().equals(ChatType.PRIVATE)) {
                System.out.println(true);
                // 处理私聊
            } else if (chat.getType().equalsIgnoreCase(ChatType.CHANNEL)) {
                // 处理频道
            }
            //获取群组成员列表
//            GetChatMember getChatMembers = new GetChatMember();
//            getChatMembers.setChatId(chatId);
//            try {
//                String status = execute(getChatMembers).getStatus();
//                System.out.println(status);
//            } catch (TelegramApiException e) {
//                log.info("获取群组成员列表失败：{}", e.getMessage());
//            }

//            User user = update.getMessage().getFrom();
//
//            // 获取用户信息
//            String firstName = user.getFirstName();
//            String lastName = user.getLastName();
//            String username = user.getUserName();

            // 发送回复消息
            try {
                // 这里是消息接收到后的处理逻辑，比如转发到另一个群
//                forwardMessage(messageText, chatId);

                if (messageText.equals("./start")) {
                    execute(SendUtils.sendMessageInit(chatId, "欢迎使用"));
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
                } else if (messageText.contains("地区") || messageText.contains("地址")) {
                    execute(SendUtils.sendMessageInit(-1002228392062L, "交易方对接人：@*****\n" +
                            "交易金额：120u\n" +
                            "结算汇率：\n" +
                            "结算手续费2U谁出：公群方（客户/公群方）\n" +
                            "订单完成时间：1天\n" +
                            "几个固话/手机口：1\n" +
                            "地区:河北\n" +
                            "客户结算完整地址：TQp15c1K7MEgNAFNLvwRSSx4JgCpfUdJ5D"));
                    String messagTexts = "发送成功，请在公群内查看";
                    execute(SendUtils.sendMessageInit(chatId, messagTexts));

                }
            } catch (TelegramApiException e) {
                log.info("机器人消息发送异常: {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        // 替换为你的 Bot 的用户名
        return "@qunbaobei8bot";
    }

    @Override
    public String getBotToken() {
        // 替换为你的 Bot 的 Token
        return "7128410523:AAHVSQHAPXitivGxOqdr1StJaI7vY5zzvDY";
    }

    // 转发消息的函数
    private void forwardMessage(String message, Long chatId) {
        // 目标群组的Chat ID
        Long targetChatId = -1002228392062L;  // 替换为目标群组的Chat ID

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(targetChatId.toString());
        sendMessage.setText(message);

        try {
            execute(sendMessage);  // 发送消息到目标群
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            DefaultBotSession botSession = new DefaultBotSession();
            TelegramBotPoll telegramBotPoll = new TelegramBotPoll();
            botSession.setToken(telegramBotPoll.getBotToken());
            TelegramBotsApi botsApi = new TelegramBotsApi(botSession.getClass());
            botsApi.registerBot(telegramBotPoll);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
