package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.builder.BookBuilder;
import service.book.BookService;
import view.AddBookView;
import view.DeleteBookView;
import view.RetriveBookView;
import view.UpdateBookView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class UpdateBookController  {
    private String name;
    private Long id;
    private String author;
    private String publishedData;
    private final BookService bookService;
    private final UpdateBookView updateBookView;

    public UpdateBookController(Long id,String name, String author, String publishedData, BookService bookService, UpdateBookView updateBookView) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.updateBookView= updateBookView;
        this.publishedData = publishedData;
        this.bookService = bookService;
        updateBookView.addUpdateButtonListener(new addUpdateButtonListener());
        displayTable();
    }

    public void processBookData(String name, String author, String publishedData) {
        // Faceți ce doriți cu datele în acest punct
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

    private class addUpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Long id = updateBookView.getId();
            String name = UpdateBookView.getName();
            String author = UpdateBookView.getAuthor();

            // Verificați dacă câmpul de dată nu este gol înainte de a încerca să parsați
            String publishedDateText = UpdateBookView.getPublishedDate();
            LocalDate publishedDate = null;
            if (!publishedDateText.isEmpty()) {
                try {
                    publishedDate = LocalDate.parse(publishedDateText);
                } catch (DateTimeParseException e) {
                    // Tratați cazul în care data nu poate fi parsată
                    e.printStackTrace(); // Puteți înlocui cu o acțiune corespunzătoare
                }
            }
Book bb = new BookBuilder().setId(id).setTitle(name).setAuthor(author).setPublishedDate(publishedDate).build();
            bookService.updateBook(bb);
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
        List<Book> b = new ArrayList<>();
        Long I_id = UpdateBookView.getId();
//    Book a = bookService.findById(I_id);
//    b.add(a);
        if (I_id != null) {
            Book a = bookService.findById(I_id);
            b.add(a);
        } else {
            // Tratează cazul în care id-ul este null sau incorect
            // Puteți afișa un mesaj de eroare sau face altceva în consecință
        }
        updateBookView.displayBooks(b);
    }
}