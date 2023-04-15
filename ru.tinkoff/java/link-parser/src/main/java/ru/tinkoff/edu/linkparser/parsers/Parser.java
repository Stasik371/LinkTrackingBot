package ru.tinkoff.edu.linkparser.parsers;

import java.net.URI;


public sealed interface Parser permits GitHubParser, StackOverFlowParser {

    Record parse(URI url);

}
