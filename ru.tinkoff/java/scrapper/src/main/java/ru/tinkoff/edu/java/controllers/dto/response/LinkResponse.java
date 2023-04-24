package ru.tinkoff.edu.java.controllers.dto.response;

import org.hibernate.validator.constraints.URL;

public record LinkResponse(long id, @URL String url) {
}
