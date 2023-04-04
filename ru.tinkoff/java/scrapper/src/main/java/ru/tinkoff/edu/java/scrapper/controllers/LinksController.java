package ru.tinkoff.edu.java.scrapper.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.controllers.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.controllers.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.controllers.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.controllers.dto.request.RemoveLinkRequest;


@RestController
@RequestMapping("/links")
public class LinksController {
    @GetMapping
    public ResponseEntity<ListLinksResponse> getAllLinks(@RequestHeader("Tg-Chat-Id") long tgChatId) {
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
        return new ResponseEntity<>(new ListLinksResponse(null, -1), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LinkResponse> addLink(@RequestHeader("Tg-Chat-Id") long tgChatId,
                                                @RequestBody @Valid AddLinkRequest linkRequest) {
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
        return new ResponseEntity<>(new LinkResponse(-1, null), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RemoveLinkRequest> deleteLink(@RequestHeader("Tg-Chat-Id") long tgChatId,
                                                        @RequestBody @Valid RemoveLinkRequest linkRequest) {
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
        return new ResponseEntity<>(new RemoveLinkRequest(null), HttpStatus.OK);
    }


}
