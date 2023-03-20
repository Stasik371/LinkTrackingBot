package ru.tinkoff.edu.java.linkparser.parsers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.linkparser.records.GithubRecord;

import java.net.URI;
import java.util.regex.Pattern;


public final class GitHubParser implements Parser {

    @Nullable
    @Override
    public Record parse(@NotNull URI uri) {
        var path = uri.getPath();
        var regexForGitHub = "^/\\w+/\\w+$";
        if (path.matches(regexForGitHub)) {
            var patternForUser = Pattern.compile("\\w+");
            var matcherForUser = patternForUser.matcher(path);
            matcherForUser.find(0);
            String user = path.substring(matcherForUser.start(), matcherForUser.end());
            String repo = path.substring(matcherForUser.end() + 1);
            return new GithubRecord(user, repo);
        }
        return null;
    }
}
