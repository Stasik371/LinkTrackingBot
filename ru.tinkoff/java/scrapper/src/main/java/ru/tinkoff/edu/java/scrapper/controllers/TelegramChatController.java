package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tg-chat")
public class TelegramChatController {
    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> registerChat(@PathVariable("id") long id) {
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChat(@PathVariable("id") long id){
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
