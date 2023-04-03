package integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimpleIntegrationTest extends IntegrationEnvironment {

    @Test
    @DisplayName("Container is running")
    void runContainers() {
        assertThat(POSTGRE_SQL_CONTAINER.isCreated(), equalTo(true));
    }
}
