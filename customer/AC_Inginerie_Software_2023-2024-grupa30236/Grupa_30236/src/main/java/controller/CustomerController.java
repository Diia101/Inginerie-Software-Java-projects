package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Book;
import model.User;
import service.book.BookService;
import view.CustomerView;

public class CustomerController {

    private final CustomerView customerView;
    private final User user;
    private final BookService bookService;

    public CustomerController(CustomerView customerView, User user, BookService bookService) {
        this.customerView = customerView;
        this.user = user;
        this.bookService = bookService;

        initialize();
    }

    private void initialize() {
        customerView.setWelcomeMessage("Welcome, " + user.getUsername() + "!");

        // Adaugă coloane la tabel (înlocuiește acestea cu propriile tale atribute Book)
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        TableColumn<Book, String> publishedDateColumn = new TableColumn<>("Published Date");
        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());

        // Adaugă coloanele la tabel
        customerView.getBookTable().getColumns().addAll(titleColumn, authorColumn, publishedDateColumn);

        // Încarcă datele în tabel (presupunând că ai un serviciu bookService)
        loadBooks();

        // Adaugă un ascultător pentru butonul de cumpărare
        customerView.addBuyButtonListener(new BuyButtonListener());
    }

    private void loadBooks() {
        // Încarcă toate cărțile disponibile
        ObservableList<Book> books = FXCollections.observableArrayList(bookService.findAll());
        customerView.getBookTable().setItems(books);
    }

    private class BuyButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // Logica pentru procesul de cumpărare
            // Poți accesa cărțile selectate din tabel utilizând customerView.getBookTable().getSelectionModel().getSelectedItems()
            // și apoi să faci ce dorești cu acele cărți
        }
    }
}
