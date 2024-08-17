package com.ruoyi.project.order.service.impl;

import com.ruoyi.project.order.service.KeyboardMarkup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyboardMarkUpImpl implements KeyboardMarkup {


    @Override
    public ReplyKeyboardMarkup getReplyKeyboardMarkup(List<KeyboardRow> rowList) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        rowList.forEach(row -> {
           List<KeyboardRow> keyboard = new ArrayList<>();
           keyboard.add(row);
        });
        replyKeyboardMarkup.setKeyboard(rowList);
        return replyKeyboardMarkup;
    }
}
