package com.ruoyi.common.utils.baobeibot;

import com.ruoyi.common.constant.ChatType;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.bot.SendUtils;
import com.ruoyi.project.order.service.impl.KeyboardMarkUpImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.*;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@Component
public class TelegramBotPoll extends TelegramLongPollingBot {

    @Autowired
    private YourTelegramBot yourTelegramBot;

    @Override
    public void onUpdateReceived(Update update) {
        TelegramBot telegramBot = new TelegramBot() {
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
        };

        // 如果是按钮点击事件，处理回调数据
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String remark = "";
            switch (callbackData) {
                case "button1":
                    break;
                case "button2":
                    remark = "交易方对接人：@*****\n" +
                            "交易金额：120u\n" +
                            "结算汇率：\n" +
                            "结算手续费2U谁出：公群方（客户/公群方）\n" +
                            "订单完成时间：1天\n" +
                            "几个固话/手机口：1\n" +
                            "地区:河北\n" +
                            "客户结算完整地址：TQp15c1K7MEgNAFNLvwRSSx4JgCpfUdJ5D";
                    break;
                default:
            }
            // 创建一个带有按钮的键盘
            try {
                InlineKeyboardMarkup inlineKeyboardMarkup = caleTemplate();
                execute(SendUtils.sendMessageInit2(chatId, remark, inlineKeyboardMarkup));
            } catch (TelegramApiException e) {
                log.info("信息回调成功：{}", e.getMessage());
            }
            return;
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            Chat chat = update.getMessage().getChat();
            String chatTitle = chat.getTitle();
            String chatType = chat.getType().toString();
            Boolean hasHiddenMembers = chat.getHasHiddenMembers();
            boolean groupMessage = update.getMessage().isGroupMessage();
            if (chat.getType().equalsIgnoreCase(ChatType.GROUP)) {
                // 处理群组
            } else if (chat.getType().equals(ChatType.PRIVATE)) {
                System.out.println(true);
                // 处理私聊
            } else if (chat.getType().equalsIgnoreCase(ChatType.CHANNEL)) {
                // 处理频道
            }
            // 创建一个带有按钮的键盘
            InlineKeyboardMarkup markup = createKeyboard();
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
                } else if (messageText.contains("地区") || messageText.contains("地址")) {
                    execute(SendUtils.sendMessageInit(-1002228392062L, "交易方对接人：@*****\n" +
                            "交易金额：120u\n" +
                            "结算汇率：\n" +
                            "结算手续费2U谁出：公群方（客户/公群方）\n" +
                            "订单完成时间：1天\n" +
                            "几个固话/手机口：1\n" +
                            "地区:河北\n" +
                            "客户结算完整地址：TQp15c1K7MEgNAFNLvwRSSx4JgCpfUdJ5D", markup));
                    String messagTexts = "发送成功，请在公群内查看";
                    execute(SendUtils.sendMessageInit(chatId, messagTexts));

                }
            } catch (TelegramApiException e) {
                log.info("机器人消息发送异常: {}", e.getMessage());
            }
        }
    }

    // 发送带按钮的消息
    private void sendButtonsMessage(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        // 创建一个 ReplyKeyboardMarkup 对象，用于设置按钮

        // 第一行按钮
        KeyboardRow keyboardRow3 = new KeyboardRow();
        List<String> listButtonNameRow3 = new ArrayList<>();
        listButtonNameRow3.add(Constants.BAOBEI);
        listButtonNameRow3.add(Constants.ORDER);
        keyboardRow3.addAll(listButtonNameRow3);

        List<KeyboardRow> rowList = new ArrayList<>();
        rowList.add(keyboardRow3);

        KeyboardMarkUpImpl keyboardMarkup = new KeyboardMarkUpImpl();
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardMarkup.getReplyKeyboardMarkup(rowList);

        // 将 ReplyKeyboardMarkup 设置到消息中
        message.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.info("发送按钮信息异常：{}", e.getMessage());
        }
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
            TelegramWebhookBot telegramWebhookBot = new TelegramWebhookBot() {
                @Override
                public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
                    return null;
                }

                @Override
                public String getBotPath() {
                    return "";
                }

                @Override
                public String getBotUsername() {
                    return "";
                }
            };
            botSession.setToken(telegramBotPoll.getBotToken());
            TelegramBotsApi botsApi = new TelegramBotsApi(botSession.getClass());
            botsApi.registerBot(telegramBotPoll);
            WebhookBot webhookBot = new WebhookBot() {
                @Override
                public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
                    return null;
                }

                @Override
                public void setWebhook(SetWebhook setWebhook) throws TelegramApiException {

                }

                @Override
                public String getBotPath() {
                    return "";
                }

                @Override
                public String getBotUsername() {
                    return "";
                }

                @Override
                public String getBotToken() {
                    return "";
                }
            };
            SetWebhook setWebhook = new SetWebhook();
            setWebhook.setUrl("https://api.telegram.org/bot<7313816769:AAGbH_WqbZzWov2QKQHO1isgQUR9b0vmvPI>/setWebhook?url=https://acbot.top/webhook");
            setWebhook.setSecretToken("7313816769:AAGbH_WqbZzWov2QKQHO1isgQUR9b0vmvPI");
            botsApi.registerBot(webhookBot, setWebhook);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardMarkup createKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("7421");
        button1.setCallbackData("button1");
        button1.setUrl("https://t.me/dbcksq");
        row.add(button1);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("取消");
        button2.setCallbackData("button2");
        row.add(button2);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }


    private InlineKeyboardMarkup caleTemplate() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("7421");
        button1.setCallbackData("button1");
        button1.setUrl("https://t.me/dbcksq");
        row.add(button1);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("已取消");
        button2.setCallbackData("button2");
        row.add(button2);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }


}
