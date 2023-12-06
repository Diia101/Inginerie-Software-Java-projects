package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import service.book.BookService;
import view.*;

import java.util.List;

public class EmployeeController {

    private final EmployeeView employeeView;
    private final BookService bookService;

    public EmployeeController(EmployeeView employeeView, BookService bookService) {
        this.employeeView = employeeView;
        this.bookService = bookService;

        initialize();
        displayTable();
    }

    private void initialize() {
        //initializeBookTable();

        //adaug butoanele
        employeeView.addAddButtonListener(new AddButtonListener());
        employeeView.addUpdateButtonListener(new UpdateButtonListener());
        employeeView.addDeleteButtonListener(new DeleteButtonListener());
        employeeView.addRetriveButtonListener(new RetriveButtonListener());
    }

//    private void initializeBookTable() {
//
//        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
//        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
//
//        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
//        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
//
//        TableColumn<Book, String> publishedDateColumn = new TableColumn<>("Published Date");
//        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());
//
//
//        employeeView.getBookTable().getColumns().addAll(titleColumn, authorColumn, publishedDateColumn);
//
//
//        loadBooks();
//    }

//    private void loadBooks() {
//        ObservableList<Book> books = FXCollections.observableArrayList(bookService.findAll());
//        employeeView.getBookTable().setItems(books);
//    }

    private class AddButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //adaug
//            AddBookView add = new AddBookView();
//            add.show();
           AddBookView addBookView = new AddBookView();
           AddBookController controller = new AddBookController("", "", "", bookService,addBookView,employeeView); // Puteți furniza valori implicite sau lăsați goale

            addBookView.show();

        }
    }

    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            UpdateBookView updateBookView = new UpdateBookView();
            UpdateBookController controller = new UpdateBookController(0L,"" ,"", "", bookService,updateBookView); // Puteți furniza valori implicite sau lăsați goale

            updateBookView.show();
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            DeleteBookView deleteBookView = new DeleteBookView();
            DeleteBookController controller = new DeleteBookController(0L, "", "","", bookService,deleteBookView); // Puteți furniza valori implicite sau lăsați goale

            deleteBookView.show();
        }
    }
    private class RetriveButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            RetriveBookView retriveBookView = new RetriveBookView();
            RetriveBookController controller = new RetriveBookController(0L,"", "", "", bookService,retriveBookView); // Puteți furniza valori implicite sau lăsați goale

            retriveBookView.show();
        }
    }
    private void displayTable(){
        List<Book> b = bookService.findAll();
        employeeView.displayBooks(b);
    }

//    private class ReportHandler implements EventHandler<ActionEvent> {
//
//        @Override
//        public void handle(ActionEvent event) {
//            //loginView.logOut();
//
//            try {
//                PdfWriter writer = new PdfWriter(pdfFilePath);
//                PdfDocument pdfDocument = new PdfDocument(writer);
//                Document document = new Document(pdfDocument);
//
//                document.add(new Paragraph("SOLD BOOK RAPORT\n\n\n"));
//
//
//                for (Book soldBook : selectedBooks) {
//                    document.add(new Paragraph("Title: " + soldBook.getTitle()));
//                    document.add(new Paragraph("Author: " + soldBook.getAuthor()));
//                    document.add(new Paragraph("Price: " + soldBook.getPrice()));
//                    document.add(new Paragraph("*   *   *   *   *   *   *   *   *   *   *"));
//                }
//
//                document.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
