package ru.tinkoff.edu.repository;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class IntegrationEnvironment {
    protected static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    private static final String CHANGELOG_PATH = "master.xml";
    private static final Path ROOT_DIRECTORY = Path.of(".").toAbsolutePath().getParent().getParent()
            .resolve("migrations/");

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:14");
        POSTGRE_SQL_CONTAINER.start();
        try {
            var connection = DriverManager.getConnection(
                    POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    POSTGRE_SQL_CONTAINER.getUsername(),
                    POSTGRE_SQL_CONTAINER.getPassword());
            var db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            var liquibase = new liquibase.Liquibase(
                    CHANGELOG_PATH,
                    new DirectoryResourceAccessor(ROOT_DIRECTORY),
                    db);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException | FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
