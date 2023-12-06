    package repository.book;

    import model.Book;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    public class BookRepositoryMock implements BookRepository{

        private List<Book> books;

        public BookRepositoryMock(){
            books = new ArrayList<>();
        }

        @Override
        public List<Book> findAll() {
            return books;
        }

        @Override
        public Optional<Book> findById(Long id) {
            return books.parallelStream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        @Override
        public boolean save(Book book) {
            return books.add(book);
        }

        @Override
        public void removeAll() {
            books.clear();
        }
        @Override
        public boolean checkAvailability(Book book) {
           //testare
           // return true;
            return books.contains(book);
        }

        @Override
        public void updateStock(Book book, int quantityChange) {
            // Implementare simplă pentru testare
            Optional<Book> existingBook = findById(book.getId());
            existingBook.ifPresent(b -> b.setStock(b.getStock() + quantityChange));
        }

        @Override
        public void removeById(Long id) {
           //stergere
            books.removeIf(book -> book.getId().equals(id));
        }
        @Override
        public boolean updateBook(Long id, String newTitle, String newAuthor, LocalDate newPublishedDate)
        {
            Optional<Book> bookToUpdate = findById(id);
            if (bookToUpdate.isPresent()) {
                Book book = bookToUpdate.get();
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                book.setPublishedDate(newPublishedDate);
                return true;
            }
            return false;
        }
    }


