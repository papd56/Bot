package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(RuoYiApplication.class, args);
    }
}
