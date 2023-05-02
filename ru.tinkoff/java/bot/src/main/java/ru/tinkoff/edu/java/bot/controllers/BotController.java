package ru.tinkoff.edu.java.bot.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.update.senders.HttpUpdateListener;

@RestController
public class BotController {


    private final HttpUpdateListener httpUpdateListener;

    @Autowired
    public BotController(HttpUpdateListener httpUpdateListener) {
        this.httpUpdateListener = httpUpdateListener;
    }


    @PostMapping("/updates")
    public ResponseEntity<HttpStatus> updates(@RequestBody @Valid LinkUpdateRequest linkUpdateRequest) {
        httpUpdateListener.receiver(linkUpdateRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
