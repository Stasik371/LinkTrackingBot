package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;


@RestController
@RequestMapping("/tg-chat")
public class TelegramChatController {
    private TgChatService tgChatService;

    @Autowired
    public TelegramChatController(TgChatService tgChatService) {
        this.tgChatService = tgChatService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> registerChat(@PathVariable("id") long id) {
        tgChatService.register(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChat(@PathVariable("id") long id) {
        tgChatService.unregister(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
