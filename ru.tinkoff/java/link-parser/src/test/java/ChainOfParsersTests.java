import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.parsers.ChainOfParsers;
import ru.tinkoff.edu.java.linkparser.records.GithubRecord;
import ru.tinkoff.edu.java.linkparser.records.StackOverFlowRecord;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ChainOfParsersTests {
    @Test
    @DisplayName("Bad Github URL case(more than 2 levels in path")
    void badUrlTestMoreThan2Levels() {
        var uri = URI.create("https://github.com/marketplace/category/github-enterprise");
        assertThat(null, equalTo(ChainOfParsers.parse(uri)));
    }

    @Test
    @DisplayName("Standard Github URL case")
    void standardUrl() {
        var uri = URI.create("https://github.com/SomeUser/SomeRepository");
        assertThat(new GithubRecord("SomeUser", "SomeRepository"), equalTo(ChainOfParsers.parse(uri)));
    }

    @Test
    @DisplayName("Bad Github URL case(another host, but same path structure)")
    void badURLTestAnotherHost() {
        var uri = URI.create("https://edu.tinkoff.ru/questions/1234");
        assertThat(null, equalTo(ChainOfParsers.parse(uri)));
    }

    @Test
    @DisplayName("Bad StackOverFlow URL case(another host, but same path structure)")
    void anotherURLTest() {
        var uri = URI.create("https://edu.tinkoff.ru/questions/1234/asd");
        assertThat(null, equalTo(ChainOfParsers.parse(uri)));
    }

    @Test
    @DisplayName("Bad StackOverFlow URL case(no \"questions\" word)")
    void badURLTest() {
        var uri = URI.create("https://stackoverflow.com/search?q=unsupported%20link");
        assertThat(null, equalTo(ChainOfParsers.parse(uri)));
    }

    @Test
    @DisplayName("Standard StackOverFlow URL case")
    void standardURLTest() {
        var uri = URI.create("https://stackoverflow.com/questions/20457368/plugins-unrecognized-tag-in-pom-xml");
        var record = new StackOverFlowRecord("20457368");
        assertThat(record, equalTo(ChainOfParsers.parse(uri)));
    }

    @Test
    @DisplayName("Bad StackOverFlow URL case(no id in link)")
    void NoIdURLTest() {
        var uri = URI.create("https://stackoverflow.com/questions//plugins-unrecognized-tag-in-pom-xml");
        assertThat(null, equalTo(ChainOfParsers.parse(uri)));
    }
}
