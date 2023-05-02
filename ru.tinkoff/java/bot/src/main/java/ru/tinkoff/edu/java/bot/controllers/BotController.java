package ru.tinkoff.edu.java.bot.controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;
<<<<<<< HEAD

@RestController
public class BotController {
    @PostMapping("/updates")
    public ResponseEntity<HttpStatus> updates(@RequestBody @Valid LinkUpdateRequest linkUpdateRequest) {
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
=======
import ru.tinkoff.edu.java.bot.update.senders.UpdateSender;

@RestController
public class BotController {


    private final UpdateSender updateSender;

    @Autowired
    public BotController(UpdateSender updateSender) {
        this.updateSender = updateSender;
    }


    @PostMapping("/updates")
    public ResponseEntity<HttpStatus> updates(@RequestBody @Valid LinkUpdateRequest linkUpdateRequest) {
        updateSender.receiver(linkUpdateRequest);
>>>>>>> parent of d68e369 (little refactoring.Also added ability to listen sync and async messages together)
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
