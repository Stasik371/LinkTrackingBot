package ru.tinkoff.edu.java.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.controllers.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.controllers.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.controllers.dto.response.LinkResponse;
import ru.tinkoff.edu.java.controllers.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.services.interfaces.LinkService;

import java.net.URI;


@RestController
@RequestMapping("/links")
public class LinksController {

    private final LinkService linkServiceImpl;

    @Autowired
    public LinksController(LinkService linkServiceImpl) {
        this.linkServiceImpl = linkServiceImpl;
    }

    @GetMapping
    public ResponseEntity<ListLinksResponse> getAllLinks(@RequestHeader("Tg-Chat-Id") long tgChatId) {
        var links = linkServiceImpl.listAll(tgChatId);
        return new ResponseEntity<>(links, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LinkResponse> addLink(@RequestHeader("Tg-Chat-Id") long tgChatId,
                                                @RequestBody @Valid AddLinkRequest linkRequest) {

        var link = linkServiceImpl.add(tgChatId, URI.create(linkRequest.link()));
        return new ResponseEntity<>(new LinkResponse(link.id(), link.url()), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<LinkResponse> deleteLink(@RequestHeader("Tg-Chat-Id") long tgChatId,
                                                   @RequestBody @Valid RemoveLinkRequest linkRequest) {
        return new ResponseEntity<>(linkServiceImpl.remove(tgChatId, URI.create(linkRequest.link())), HttpStatus.OK);
    }


}
