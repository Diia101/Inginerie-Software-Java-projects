    package repository.book;

    import model.Book;

    public abstract class BookRepositoryDecorator implements BookRepository{
        protected BookRepository decoratedRepository;

        public BookRepositoryDecorator(BookRepository bookRepository){
            this.decoratedRepository = bookRepository;
        }
        @Override
        public boolean checkAvailability(Book book) {
          //daca e disponibila in baza de date
            return decoratedRepository.checkAvailability(book);
        }

        @Override
        public void updateStock(Book book, int quantityChange) {
            //actualizare in baza de date
            decoratedRepository.updateStock(book, quantityChange);
        }


        @Override
        public void removeById(Long id) {
            decoratedRepository.removeById(id);
        }
    }
