package ru.tinkoff.edu.java.linkparser.parsers;

import ru.tinkoff.edu.java.linkparser.records.GithubRecord;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class GitHubParser implements Parser {

    @Override
    public Record parse(URI uri) {
        if (!uri.getHost().equals("github.com")) return null;
        var path = uri.getPath();
        System.out.println(path);
        var regexForGitHub = "^/\\w+/\\w+$";
        if (path.matches(regexForGitHub)) {
            System.out.println("smth");
            Pattern patternForUser = Pattern.compile("\\w+");
            Matcher matcherForUser = patternForUser.matcher(path);
            matcherForUser.find(0);
            String user = path.substring(matcherForUser.start(), matcherForUser.end());
            String repo = path.substring(matcherForUser.end() + 1);
            return new GithubRecord(user, repo);
        }
        return null;
    }
}
