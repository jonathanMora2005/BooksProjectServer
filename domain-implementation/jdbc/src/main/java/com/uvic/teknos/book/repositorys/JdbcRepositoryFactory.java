package com.uvic.teknos.book.repositorys;

import com.jonathan.mybooklist.repositories.*;
import com.jonathan.mybooklist.RepositoryFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcRepositoryFactory implements RepositoryFactory {
    private Connection connection ;

    public JdbcRepositoryFactory() throws SQLException {
        var properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/datasource.properties"));
            connection = DriverManager.getConnection(String.format("%s:%s://%s/%s",
                    properties.getProperty("protocol"),
                    properties.getProperty("subprotocol"),
                    properties.getProperty("url"),
                    properties.getProperty("database")), properties.getProperty("user"), properties.getProperty("password"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthorRepository getAuthorRepository() {
        return new JdbcAuthorRepository(connection);
    }

    @Override
    public GenreRepository getGenreRepository() {
        return new JdbcGenreRepository(connection);
    }

    @Override
    public BookreadRepository getReadRepositori() {
        return new JdbcReadBookRepository(connection);
    }

    @Override
    public PublishingRepository getPublishingRepository() {
        return new JdbcPublshingRepository(connection);
    }

    @Override
    public PersonalinformationRepository getPersonalInformationRepository() {
        return new JdbcPersonalInformationRepository(connection);
    }
}
