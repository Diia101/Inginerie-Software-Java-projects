package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.builder.BookBuilder;
import service.book.BookService;
import view.AddBookView;
import view.EmployeeView;

import java.time.LocalDate;
import java.util.List;

public class AddBookController  {
    private String name;
    private String author;
    private String publishedData;
    private final BookService bookService;
     private final AddBookView addBookView;
     private final EmployeeView employeeView;

    public AddBookController(String name, String author, String publishedData, BookService bookService, AddBookView addBookView, EmployeeView employeeView) {
        this.name = name;
        this.author = author;
        this.addBookView=addBookView;
        this.publishedData = publishedData;
        this.bookService = bookService;
        this.employeeView= employeeView;
        addBookView.addAddButtonListener(new addAddButtonListener());
    }

    public void processBookData(String name, String author, String publishedData) {
        System.out.println("Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Published Data: " + publishedData);
    }

    private class addAddButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event)
        {
            String name = AddBookView.getName();
            String author = AddBookView.getAuthor();
            LocalDate publishedDate = LocalDate.parse(AddBookView.getPublishedDate());

            Book b = new BookBuilder().setTitle(name).setAuthor(author).setPublishedDate(publishedDate).build();
            bookService.save(b);
            displayTable();
        }
        private void displayTable(){
            List<Book> b = bookService.findAll();
            employeeView.displayBooks(b);
        }
    }

}
