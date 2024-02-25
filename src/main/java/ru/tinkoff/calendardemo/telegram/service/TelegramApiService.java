package ru.tinkoff.calendardemo.telegram.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.tinkoff.calendardemo.exception.NotFoundException;
import ru.tinkoff.calendardemo.telegram.config.TelegramBotConfig;

@EnableConfigurationProperties(TelegramBotConfig.class)
@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramApiService {

    private final TelegramBotConfig telegramBotConfig;

    private TelegramBot bot;

    @PostConstruct
    private void init() {
        if (StringUtils.isEmpty(telegramBotConfig.token()) || StringUtils.isEmpty(telegramBotConfig.chatId())) {
            log.error("telegramToken or telegramChatId are empty");
            throw new NotFoundException("telegramToken or telegramChatId are empty");
        }

        this.bot = new TelegramBot(telegramBotConfig.token());
        this.bot.setUpdatesListener( updates -> {
            updates.forEach( update -> {
                var messageIn = update.message().text();

            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void sendMessage(String message) {
        this.bot.execute(new SendMessage(telegramBotConfig.chatId(), message));
    }
}
