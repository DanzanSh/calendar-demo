package ru.tinkoff.calendardemo.telegram.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot.telegram")
public record TelegramBotConfig(String token, String chatId) {
}

