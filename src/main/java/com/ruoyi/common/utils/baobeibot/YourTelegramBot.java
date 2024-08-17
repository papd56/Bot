package com.ruoyi.common.utils.baobeibot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class YourTelegramBot extends TelegramWebhookBot {

    private String botUsername;
    private String botToken;
    private String webhookPath;

    public YourTelegramBot(String botUsername, String botToken, String webhookPath) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.webhookPath = webhookPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webhookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        // 可以选择直接处理更新，或者将其传递给控制器
        // 在这里你可以直接调用控制器中的处理逻辑
        return null;
    }
}
