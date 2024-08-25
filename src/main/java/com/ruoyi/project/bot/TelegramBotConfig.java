package com.ruoyi.project.bot;

import com.ruoyi.project.order.controller.TelegramBotGarantee;
import com.ruoyi.project.order.controller.TelegramBotPoll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Configuration
public class TelegramBotConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public TelegramBotPoll telegramBotPoll() {
        return new TelegramBotPoll();
    }

    @Bean
    public TelegramBotGarantee telegramBotGarantee() {
        return new TelegramBotGarantee();
    }

    @PostConstruct
    public void registerBot() throws TelegramApiException {
        telegramBotsApi().registerBot(telegramBotPoll());
        telegramBotsApi().registerBot(telegramBotGarantee());
    }
}
