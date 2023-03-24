package ru.tinkoff.edu.java.bot.controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

@RestController
public class BotController {
    @PostMapping("/updates")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid LinkUpdateRequest linkUpdateRequest) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
