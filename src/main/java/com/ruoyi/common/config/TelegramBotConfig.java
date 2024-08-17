package com.ruoyi.common.config;

import com.ruoyi.common.utils.baobeibot.YourTelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotConfig {

    @Bean
    public YourTelegramBot yourTelegramBot() {
        String botUsername = "your_bot_username";
        String botToken = "7313816769:AAGbH_WqbZzWov2QKQHO1isgQUR9b0vmvPI";
        String webhookPath = "https://acbot.top/webhook";

        return new YourTelegramBot(botUsername, botToken, webhookPath);
    }
}
