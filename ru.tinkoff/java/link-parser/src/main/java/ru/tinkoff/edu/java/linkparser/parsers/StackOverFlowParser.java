package ru.tinkoff.edu.java.linkparser.parsers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.linkparser.records.StackOverFlowRecord;

import java.net.URI;
import java.util.regex.Pattern;



public final class StackOverFlowParser implements Parser {

    @Nullable
    @Override
    public Record parse(@NotNull URI uri) {
        var path = uri.getPath();
        var regexForStackOverFlow = "/questions/\\d+/.+";
        if (path.matches(regexForStackOverFlow)) {
            var pattern = Pattern.compile("\\d+");
            var matcher = pattern.matcher(path);
            matcher.find(0);
            String id = path.substring(matcher.start(), matcher.end());
            return new StackOverFlowRecord(id);
        }
        return null;
    }
}
