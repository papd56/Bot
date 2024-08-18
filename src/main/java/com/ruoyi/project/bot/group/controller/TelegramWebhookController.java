package com.ruoyi.project.bot.group.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TelegramWebhookController {

    @PostMapping("/webhook")
    public void onUpdateReceived(@RequestBody Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String receivedText = update.getMessage().getText();

            if ("/start".equals(receivedText)) {
                sendButtonsMessage(chatId);
            }
            // 处理其他消息
        }
    }

    private void sendButtonsMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("请选择一个选项：");

        // 创建一个 ReplyKeyboardMarkup 对象，用于设置按钮
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);  // 自动调整大小
        replyKeyboardMarkup.setOneTimeKeyboard(true);  // 点击后隐藏键盘

        // 创建键盘按钮行
        List<KeyboardRow> keyboard = new ArrayList<>();

        // 第一行按钮
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Option 1"));
        keyboardFirstRow.add(new KeyboardButton("Option 2"));
        keyboardFirstRow.add(new KeyboardButton("Option 3"));

        // 将行添加到键盘
        keyboard.add(keyboardFirstRow);

        // 将键盘设置到 ReplyKeyboardMarkup 对象
        replyKeyboardMarkup.setKeyboard(keyboard);

        // 将 ReplyKeyboardMarkup 设置到消息中
        message.setReplyMarkup(replyKeyboardMarkup);
    }
}
