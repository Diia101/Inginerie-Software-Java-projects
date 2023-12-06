package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.builder.BookBuilder;
import service.book.BookService;
import view.DeleteBookView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DeleteBookController  {
    private String name;
    private Long id;
    private String author;
    private String publishedData;
    private final BookService bookService;
    private final DeleteBookView deleteBookView;

    public DeleteBookController(Long id,String name, String author, String publishedData, BookService bookService, DeleteBookView deleteBookView) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.deleteBookView= deleteBookView;
        this.publishedData = publishedData;
        this.bookService = bookService;
        deleteBookView.addDeleteButtonListener(new addDeleteButtonListener());
        displayTable();
    }

    public void processBookData(String name, String author, String publishedData) {
        System.out.println("Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Published Data: " + publishedData);
    }

//    private class addRetriveButtonListener implements EventHandler<ActionEvent>{
//        @Override
//        public void handle(ActionEvent event)
//        { Long id = retriveBookView.getId();
//            String name = RetriveBookView.getName();
//            String author = RetriveBookView.getAuthor();
//            LocalDate publishedDate = LocalDate.parse(RetriveBookView.getPublishedDate());
//             Book b = bookService.findById(id);
//        }
//    }

    private class addDeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Long id = deleteBookView.getId();
            String name = DeleteBookView.getName();
            String author = DeleteBookView.getAuthor();


            String publishedDateText = DeleteBookView.getPublishedDate();
            LocalDate publishedDate = null;
            if (!publishedDateText.isEmpty()) {
                try {
                    publishedDate = LocalDate.parse(publishedDateText);
                } catch (DateTimeParseException e) {

                    e.printStackTrace();
                }
            }
            Book bb = new BookBuilder().setId(id).setTitle(name).setAuthor(author).setPublishedDate(publishedDate).build();
            bookService.deleteBook(id);
            displayTable();
//            if (id != null ) {
//                Book b = bookService.findById(id);
//                List<Book> s = new ArrayList<>();
//                s.add(b);
//                displayTable();
//            } else {
//                // Tratați cazul în care id-ul sau data este null
//            }
        }
    }


    //    private void displayTable(){
//        List<Book> b = new ArrayList<>();
//        id= RetriveBookView.getId();
//        Book a = bookService.findById(id);
//        b.add(a);
//        retriveBookView.displayBooks(b);
//    }
    private void displayTable() {

        Long I_id = DeleteBookView.getId();
//    Book a = bookService.findById(I_id);
//    b.add(a);
        if (I_id != null) {
            bookService.deleteBook(I_id);
            ///b.add(a);
        } else {
            //daca e null
        }
        List<Book> b =bookService.findAll();
        deleteBookView.displayBooks(b);
    }
}