package ru.tinkoff.edu.linkparser.parsers;


import ru.tinkoff.edu.linkparser.records.StackOverFlowRecord;

import java.net.URI;
import java.util.regex.Pattern;


public final class StackOverFlowParser implements Parser {
    private final Pattern pattern;

    public StackOverFlowParser() {
        pattern = Pattern.compile("\\d+");
    }


    @Override
    public Record parse(URI uri) {
        var path = uri.getPath();
        var regexForStackOverFlow = "/questions/\\d+/.+";
        if (path.matches(regexForStackOverFlow)) {
            var matcher = pattern.matcher(path);
            matcher.find(0);
            String id = path.substring(matcher.start(), matcher.end());
            return new StackOverFlowRecord(id);
        }
        return null;
    }
}
