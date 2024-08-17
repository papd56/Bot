package com.ruoyi.common.utils.bot;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendUtils {

    public static SendMessage sendMessageInit(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setDisableWebPagePreview(true);
        return sendMessage;
    }

    public static SendMessage sendMessageInit2(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setDisableWebPagePreview(true);
        return sendMessage;
    }

    public static SendMessage sendMessageInit(Long chatId, String text, ParseMode parseMode) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setDisableWebPagePreview(true);
        return sendMessage;
    }
}
