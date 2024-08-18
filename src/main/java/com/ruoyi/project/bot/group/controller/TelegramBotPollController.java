package com.ruoyi.project.bot.group.controller;

import com.ruoyi.project.order.controller.TelegramBotPoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/botpolling")
public class TelegramBotPollController {

    @Autowired
    private TelegramBotPoll telegramBotPoll;
}
