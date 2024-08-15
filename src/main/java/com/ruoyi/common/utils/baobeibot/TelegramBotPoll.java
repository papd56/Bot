package com.ruoyi.common.utils.baobeibot;

import com.ruoyi.common.utils.bot.SendUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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
//            User user = update.getMessage().getFrom();
//
//            // 获取用户信息
//            String firstName = user.getFirstName();
//            String lastName = user.getLastName();
//            String username = user.getUserName();

            // 发送回复消息
            try {
                // 处理用户消息
                if ("报备".equals(messageText)) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("报备模板中必须包含: \n" +
                            "交易方对接人：@hwdb\n" +
                            "交易金额：100u\n" +
                            "订单完成时间：1天");
                    execute(sendMessage);
                } else if (messageText.contains("交易方对接人:@ELlaet\n" +
                        "交易金额：95u/h\n" +
                        "订单完成时间：1天\n" +
                        "几个固话/手机口：1\n" +
                        "地区：宁夏\n" +
                        "地址：TCF5QbYdDPyBNm6BodGneBxcV69s7WrYVv")) {

                    execute(SendUtils.sendMessageInit(chatId, "发送成功，请在公群内查看"));
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
