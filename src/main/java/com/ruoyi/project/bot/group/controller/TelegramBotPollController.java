package com.ruoyi.project.bot.group.controller;

import com.ruoyi.common.utils.baobeibot.GetGroupInfoList;
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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
@RequestMapping("/botpolling")
public class TelegramBotPollController {

    @Autowired
    private TelegramBotPoll telegramBotPoll;

    @Autowired
    private GetGroupInfoList getGroupInfoList;

    @PostMapping("/list")
    public R<Void> list(Update update) throws TelegramApiException {
        getGroupInfoList.getChatIds();
        return R.ok();
    }

}
