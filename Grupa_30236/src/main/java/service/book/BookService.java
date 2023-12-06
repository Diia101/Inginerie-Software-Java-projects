package service.book;

import model.Book;
import model.User;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(Long id);
    List<Book> findAll();

    Book findById(Long id);

    boolean save(Book book);

    int getAgeOfBook(Long id);

    boolean buyBook(User user, Book book);
}
