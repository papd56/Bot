package com.ruoyi.project.bot.group.controller;

import com.ruoyi.common.utils.baobeibot.TelegramBotPoll;
import com.ruoyi.framework.web.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@RestController
@RequestMapping("/botpolling")
public class TelegramBotPollController {

    @Autowired
    private TelegramBotPoll telegramBotPoll;

    @PostMapping("/list")
    public R<Void> list(Update update) {
        Message message = new Message();
        message.setText("报备");
        Chat chat = new Chat();
        chat.setId(6836792096L);
//        User user = new User();
//        user.setFirstName("hawkins");
//        user.setLastName("test");
//        user.setUserName("test8888");
        message.setChat(chat);
        update.setMessage(message);
        telegramBotPoll.onUpdateReceived(update);
        return R.ok();
    }

}
