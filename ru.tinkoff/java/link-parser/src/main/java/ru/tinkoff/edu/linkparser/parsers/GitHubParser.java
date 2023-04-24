package ru.tinkoff.edu.linkparser.parsers;


import ru.tinkoff.edu.linkparser.records.GithubRecord;

import java.net.URI;
import java.util.regex.Pattern;


public final class GitHubParser implements Parser {

    private final Pattern patternForUser;
    public GitHubParser() {
        patternForUser = Pattern.compile("\\w+");
    }


    @Override
    public Record parse(URI uri) {
        var path = uri.getPath();
        var regexForGitHub = "^/\\w+/\\w+$";
        if (path.matches(regexForGitHub)) {
            var matcherForUser = patternForUser.matcher(path);
            matcherForUser.find(0);
            String user = path.substring(matcherForUser.start(), matcherForUser.end());
            String repo = path.substring(matcherForUser.end() + 1);
            return new GithubRecord(user, repo);
        }
        return null;
    }
}
