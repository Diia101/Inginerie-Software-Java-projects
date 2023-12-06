package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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


        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        TableColumn<Book, String> publishedDateColumn = new TableColumn<>("Published Date");
        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());


        customerView.getBookTable().getColumns().addAll(titleColumn, authorColumn, publishedDateColumn);

        loadBooks();
        customerView.getBookTable().setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    Book selectedBook = row.getItem();
                    // Acum poți face ce dorești cu cartea selectată
                    System.out.println("Selected Book: " + selectedBook.getTitle());
                }
            });
            return row;
        });
        //pt buton de cumparare
        customerView.addBuyButtonListener(new BuyButtonListener());
    }

    private void loadBooks() {
       //incarca toate cartile disponibile
        ObservableList<Book> books = FXCollections.observableArrayList(bookService.findAll());
        customerView.getBookTable().setItems(books);
    }

    private class BuyButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // accesez cartilee selectate din tabel cu customerView.getBookTable().getSelectionModel().getSelectedItems()
            // obtin cartile  selectate din tabel
            ObservableList<Book> selectedBooks = customerView.getBookTable().getSelectionModel().getSelectedItems();
          //verific daca exista carti selectate
            if (!selectedBooks.isEmpty()) {
               //fac cumparare pt fiecare carte cumparata
                for (Book selectedBook : selectedBooks) {
                    boolean buyResult = bookService.buyBook(user, selectedBook);

                    if (buyResult) {
                        customerView.showPurchaseMessage("Cartea a fost cumpărată cu succes!");
                        System.out.println(("Cartea a fost cumpărată cu succes!"));
                    } else {
                        customerView.showPurchaseMessage("Cumpărarea cărții a eșuat. Verificați disponibilitatea.");
                        System.out.println("Cumpărarea cărții a eșuat. Verificați disponibilitatea.");
                    }
                }
                //reincarc lista de carti dupa cumparare
                loadBooks();
            } else {
                // daca nu este selectata nicio carte, afiseaz un mesaj
                customerView.showPurchaseMessage("Vă rugăm să selectați o carte pentru a o cumpăra.");
                System.out.println("Vă rugăm să selectați o carte pentru a o cumpăra.");
            }
        }
    }
}
