package ru.tinkoff.edu.java.bot.webclients.implementations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.controllers.dto.ApiErrorResponseDTO;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.LinkResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.RemoveLinkResponse;
import ru.tinkoff.edu.java.bot.webclients.interfaces.ScrapperClient;

@Getter
@Setter
public class ScrapperClientImpls implements ScrapperClient {
    private final WebClient webClient;

    private Long tgChatId;
    private final String REQUIRED_HEADER = "Tg-Chat-Id";

    @Value("${client.baseUrl}")
    private String baseUrl;


    public ScrapperClientImpls() {
        webClient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

    }

    public ScrapperClientImpls(String url) {
        webClient = WebClient
                .builder()
                .baseUrl(url)
                .build();
    }

    @Override
    public HttpStatus registerChat(long id) {
        return webClient.post()
                .uri("/tg-chat/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(ApiErrorResponseDTO.class).flatMap(error -> {
                        return Mono.error(new ApiErrorResponse(error.description(), error.code(),
                                error.exceptionsName(), error.exceptionMessage(), error.stacktrace()));
                    });
                })
                .bodyToMono(HttpStatus.class)
                .block();
    }

    @Override
    public HttpStatus deleteChat(long id) {
        return webClient.delete()
                .uri("/tg-chat/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(ApiErrorResponseDTO.class).flatMap(error -> {
                        return Mono.error(new ApiErrorResponse(error.description(), error.code(),
                                error.exceptionsName(), error.exceptionMessage(), error.stacktrace()));
                    });
                })
                .bodyToMono(HttpStatus.class)
                .block();
    }

    @Override
    public ListLinksResponse getAllLinks(long tgChatId) {
        return webClient.get()
                .uri("/links")
                .header(REQUIRED_HEADER, Long.toString(tgChatId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(ApiErrorResponseDTO.class).flatMap(error -> {
                        return Mono.error(new ApiErrorResponse(error.description(), error.code(),
                                error.exceptionsName(), error.exceptionMessage(), error.stacktrace()));
                    });
                })
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    @Override
    public RemoveLinkResponse deleteLink(long tgChatId, RemoveLinkRequest linkRequest) {
        return webClient.method(HttpMethod.DELETE)
                .uri("/links")
                .body(Mono.just(linkRequest), RemoveLinkRequest.class)
                .header(REQUIRED_HEADER, Long.toString(tgChatId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(ApiErrorResponseDTO.class).flatMap(error -> {
                        return Mono.error(new ApiErrorResponse(error.description(), error.code(),
                                error.exceptionsName(), error.exceptionMessage(), error.stacktrace()));
                    });
                })
                .bodyToMono(RemoveLinkResponse.class)
                .block();
    }

    @Override
    public LinkResponse addLink(long tgChatId, AddLinkRequest linkRequest) {
        return webClient.post()
                .uri("/links")
                .body(Mono.just(linkRequest), AddLinkRequest.class)
                .header(REQUIRED_HEADER, Long.toString(tgChatId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return response.bodyToMono(ApiErrorResponseDTO.class).flatMap(error -> {
                        return Mono.error(new ApiErrorResponse(error.description(), error.code(),
                                error.exceptionsName(), error.exceptionMessage(), error.stacktrace()));
                    });
                })
                .bodyToMono(LinkResponse.class)
                .block();
    }
}
