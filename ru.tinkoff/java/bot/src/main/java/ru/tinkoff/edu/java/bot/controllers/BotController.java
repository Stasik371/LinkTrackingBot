package ru.tinkoff.edu.java.bot.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.bot.TelegramBotImpl;

@RestController
public class BotController {

    private final TelegramBotImpl bot;

    @Autowired
    public BotController(TelegramBotImpl bot) {
        this.bot = bot;
    }

    @PostMapping("/updates")
    public ResponseEntity<HttpStatus> updates(@RequestBody @Valid LinkUpdateRequest linkUpdateRequest) {
        bot.sendUpdates(linkUpdateRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
