package repository;


import model.Book;
import model.builder.BookBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class BookRepositoryMySQLTest {
    private Connection connection;
    private BookRepositoryMySQL bookRepository;

    @Before
    public void setUp() throws SQLException {
        // Configurați aici conexiunea cu baza de date sau utilizați un mock pentru conexiunea la bază de date
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/namedb", "username", "password");
        bookRepository = new BookRepositoryMySQL(connection);
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testFindById() {
        // Implementați testul pentru findById() aici
        Optional<Book> book = bookRepository.findById(1L);
        assertTrue(book.isPresent());
        assertEquals(1L, book.get().getId().longValue());
    }

    @Test
    public void testSave() {
        // Implementați testul pentru save() aici
        Book book = new BookBuilder()
                .setTitle("Test Book")
                .setAuthor("Test Author")
                .setPublishedDate(LocalDate.now())
                .build();
        assertTrue(bookRepository.save(book));
        assertNotNull(book.getId());
    }

    @Test
    public void testRemoveAll() {
        // Implementați testul pentru removeAll() aici
        bookRepository.removeAll();
        assertTrue(bookRepository.findAll().isEmpty());
    }
}
