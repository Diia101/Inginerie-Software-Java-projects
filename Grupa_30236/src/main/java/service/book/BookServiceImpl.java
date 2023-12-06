package service.book;

import model.Book;
import model.User;
import repository.book.BookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService{

    final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        Book book = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int)ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }
    @Override
    public boolean buyBook(User user, Book book) {
        //verific daca cartea e disponibila si true daca a reusit cumparearea
        if (bookRepository.checkAvailability(book)) {
                bookRepository.updateStock(book, -1);
                return true;
        } else {
            //cartea nu e disponibila
            return false;
        }
    }


    //CRUD
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean addBook(Book book) {
      //adaug carte
        return bookRepository.save(book);
    }

    @Override
    public boolean updateBook(Book book) {
       return bookRepository.updateBook(book.getId(), book.getTitle(), book.getAuthor(),book.getPublishedDate());
    }

    @Override
    public boolean deleteBook(Long id) {
        //verific daca cartea cu idul specificat exista inainte de stergere
        if (bookRepository.findById(id).isPresent()) {
           //sterg cartea
            bookRepository.removeById(id);
            return true;
        } else {
           //cartea nu exista
            return false;
        }
    }
}
