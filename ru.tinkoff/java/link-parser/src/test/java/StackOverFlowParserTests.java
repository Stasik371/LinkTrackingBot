import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.parsers.Parser;
import ru.tinkoff.edu.java.linkparser.parsers.StackOverFlowParser;
import ru.tinkoff.edu.java.linkparser.records.StackOverFlowRecord;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StackOverFlowParserTests {
    private final static Parser parser = new StackOverFlowParser();

    @Test
    @DisplayName("Bad URL case(another host, but same path structure)")
    void anotherURLTest() {
        var uri = URI.create("https://edu.tinkoff.ru/questions/1234/asd");
        assertThat(null, equalTo(parser.parse(uri)));
    }

    @Test
    @DisplayName("Bad URL case(no \"questions\" word)")
    void badURLTest() {
        var uri = URI.create("https://stackoverflow.com/search?q=unsupported%20link");
        assertThat(null, equalTo(parser.parse(uri)));
    }

    @Test
    @DisplayName("Standard URL case")
    void standardURLTest() {
        var uri = URI.create("https://stackoverflow.com/questions/20457368/plugins-unrecognized-tag-in-pom-xml");
        var record = new StackOverFlowRecord("20457368");
        assertThat(record, equalTo(parser.parse(uri)));
    }

    @Test
    @DisplayName("Bad URL case(no id in link)")
    void NoIdURLTest() {
        var uri = URI.create("https://stackoverflow.com/questions//plugins-unrecognized-tag-in-pom-xml");
        assertThat(null, equalTo(parser.parse(uri)));
    }


}
