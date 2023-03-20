import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.parsers.GitHubParser;
import ru.tinkoff.edu.java.linkparser.parsers.Parser;
import ru.tinkoff.edu.java.linkparser.parsers.StackOverFlowParser;
import ru.tinkoff.edu.java.linkparser.records.GithubRecord;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GitHubParserTests {
    private final static Parser parser = new GitHubParser();

    @Test
    @DisplayName("Bad URL case(more than 2 levels in path")
    void badUrlTestMoreThan2Levels() {
        var uri = URI.create("https://github.com/marketplace/category/github-enterprise");
        assertThat(null, equalTo(parser.parse(uri)));
    }

    @Test
    @DisplayName("Standard URL case")
    void standardUrl() {
        var uri = URI.create("https://github.com/SomeUser/SomeRepository");
        assertThat(new GithubRecord("SomeUser", "SomeRepository"), equalTo(parser.parse(uri)));
    }

    @Test
    @DisplayName("Bad URL case(another host, but same path structure)")
    void badURLTestAnotherHost() {
        var uri = URI.create("https://edu.tinkoff.ru/questions/1234");
        assertThat(null, equalTo(parser.parse(uri)));
    }


}
