package ru.tinkoff.edu.java.linkparser.parsers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

public final class ChainOfParsers {

    @Nullable
    public static Record parse(@NotNull URI url) {
        return switch (url.getHost()) {
            case "github.com" -> new GitHubParser().parse(url);
            case "stackoverflow.com" -> new StackOverFlowParser().parse(url);
            default -> null;
        };
    }
}
