package com.ruoyi.project.order.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public interface KeyboardMarkup {

    ReplyKeyboardMarkup getReplyKeyboardMarkup(List<KeyboardRow> rowList);
}
