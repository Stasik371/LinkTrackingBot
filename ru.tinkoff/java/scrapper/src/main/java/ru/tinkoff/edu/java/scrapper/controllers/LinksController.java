package ru.tinkoff.edu.java.scrapper.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;


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

    public ResponseEntity<RemoveLinkRequest> addLink(@RequestHeader("Tg-Chat-Id") long tgChatId,
                                                     @RequestBody @Valid AddLinkRequest linkRequest) {
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
        return new ResponseEntity<>(new RemoveLinkRequest(null), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<AddLinkRequest> deleteLink(@RequestHeader("Tg-Chat-Id") long tgChatId,
                                                     @RequestBody @Valid RemoveLinkRequest linkRequest) {
        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
        return new ResponseEntity<>(new AddLinkRequest(null), HttpStatus.OK);
    }


}
