package com.ruoyi.common.utils.baobeibot;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class GetGroupInfoList extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "7128410523:AAHVSQHAPXitivGxOqdr1StJaI7vY5zzvDY";

    public List<Long> getChatIds() throws TelegramApiException {
        List<Long> chatIds = new ArrayList<>();
        ArrayList<Update> updates = execute(new GetUpdates());
        for (Update update : updates) {
            Chat chat = update.getMessage().getChat();
            if (chat.getType().equals("group") || chat.getType().equals("supergroup")) {
                chatIds.add(chat.getId());
            }
        }
        return chatIds;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return "";
    }

    // ... 其他方法 ...
}
