package ru.tinkoff.edu.java.controllers.dto.request;


import org.hibernate.validator.constraints.URL;

public record AddLinkRequest(@URL String link) {
}
