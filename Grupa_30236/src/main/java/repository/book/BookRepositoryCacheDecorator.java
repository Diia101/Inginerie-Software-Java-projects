package repository.book;

import model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator{
    private Cache<Book> cache;

    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache){
        super(bookRepository);
        this.cache = cache;
    }

    @Override
    public List<Book> findAll() {
        if (cache.hasResult()){
           return cache.load();
        }

        List<Book> books = decoratedRepository.findAll();
        cache.save(books);

        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {

        if (cache.hasResult()){
            return cache.load()
                    .stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(Book book) {
        cache.invalidateCache();
        return decoratedRepository.save(book);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }

    @Override
    public boolean checkAvailability(Book book) {
       //daca e disponibil
        if (cache.hasResult()) {
            return cache.load().contains(book);
        }

        return decoratedRepository.checkAvailability(book);
    }

    @Override
    public void updateStock(Book book, int quantityChange) {
       //actualizam cacheu
        decoratedRepository.updateStock(book, quantityChange);
        cache.invalidateCache();
    }


    @Override
    public void removeById(Long id) {
        decoratedRepository.removeById(id);
        cache.invalidateCache();
    }
    @Override
    public boolean updateBook(Long id, String newTitle, String newAuthor, LocalDate newPublishedDate)
    {
        cache.invalidateCache();
        return decoratedRepository.updateBook(id, newTitle, newAuthor,newPublishedDate);
    }

}
