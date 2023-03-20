package ru.tinkoff.edu.java.linkparser.parsers;

import ru.tinkoff.edu.java.linkparser.records.StackOverFlowRecord;

import java.net.URI;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public final class StackOverFlowParser implements Parser {

    @Override
    public Record parse(URI uri) {
        if (!uri.getHost().equals("stackoverflow.com")) return null;
        var path = uri.getPath();
        var regexForStackOverFlow = "/questions/\\d+/.+";
        if (path.matches(regexForStackOverFlow)) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(path);
            int start = 0;
            matcher.find(start);
            String id = path.substring(matcher.start(), matcher.end());
            var record = new StackOverFlowRecord(id);
            return record;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new StackOverFlowParser().parse(URI.create("https://stackoverflow.com/search?q=unsupported%20link")));
    }
}
