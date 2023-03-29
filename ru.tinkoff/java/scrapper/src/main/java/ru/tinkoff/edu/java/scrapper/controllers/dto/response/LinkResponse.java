package ru.tinkoff.edu.java.scrapper.controllers.dto.response;

import org.hibernate.validator.constraints.URL;

public record LinkResponse(long id, @URL String url) {
}
