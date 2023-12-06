package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import service.book.BookService;
import view.RetriveBookView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class RetriveBookController  {
    private String name;
    private Long id;
    private String author;
    private String publishedData;
    private final BookService bookService;
    private final RetriveBookView retriveBookView;

    public RetriveBookController(Long id,String name, String author, String publishedData, BookService bookService, RetriveBookView retriveBookView) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.retriveBookView= retriveBookView;
        this.publishedData = publishedData;
        this.bookService = bookService;
        retriveBookView.addRetriveButtonListener(new addRetriveButtonListener());
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

    private class addRetriveButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Long id = retriveBookView.getId();
            String name = RetriveBookView.getName();
            String author = RetriveBookView.getAuthor();

            //daca nu e gol campul
            String publishedDateText = RetriveBookView.getPublishedDate();
            LocalDate publishedDate = null;
            if (!publishedDateText.isEmpty()) {
                try {
                    publishedDate = LocalDate.parse(publishedDateText);
                } catch (DateTimeParseException e) {
                    //daca nu poate fi pardata
                    e.printStackTrace();
                }
            }

            if (id != null ) {
                Book b = bookService.findById(id);
                List<Book> s = new ArrayList<>();
                s.add(b);
                displayTable();
            } else {
                //daca e null
            }
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
    Long I_id = RetriveBookView.getId();
//    Book a = bookService.findById(I_id);
//    b.add(a);
    if (I_id != null) {
        Book a = bookService.findById(I_id);
        b.add(a);
    } else {
        //daca id e null sau gresit
    }
    retriveBookView.displayBooks(b);
}
}