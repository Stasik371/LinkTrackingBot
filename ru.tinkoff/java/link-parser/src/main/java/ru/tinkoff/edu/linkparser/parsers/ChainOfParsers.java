package ru.tinkoff.edu.linkparser.parsers;



import java.net.URI;

public final class ChainOfParsers {


    public static Record parse(URI url) {
        return switch (url.getHost()) {
            case "github.com" -> new GitHubParser().parse(url);
            case "stackoverflow.com" -> new StackOverFlowParser().parse(url);
            default -> null;
        };
    }
}
