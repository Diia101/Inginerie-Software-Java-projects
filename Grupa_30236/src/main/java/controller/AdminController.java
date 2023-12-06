package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import service.user.UserService;
import view.AddEmployeeView;
import view.AdminView;
import view.DeleteEmployeeView;
import view.RetriveEmployeeView;

import java.util.List;

public class AdminController {

    private final AdminView adminView;
    private final UserService userService;

    public AdminController(AdminView adminView, UserService userService) {
        this.adminView = adminView;
        this.userService = userService;


        initialize();
        displayTable();
    }

    private void initialize() {
        // initializez tabel și coloane
        //initializeBookTable();

        // adaug butoane
        adminView.addAddButtonListener(new AddButtonListener());
        adminView.addUpdateButtonListener(new UpdateButtonListener());
        adminView.addDeleteButtonListener(new DeleteButtonListener());
        adminView.addRetriveButtonListener(new RetriveButtonListener());
    }

//    private void initializeBookTable() {
//        // initiallizez coloane tabel
//        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
//        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
//
//        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
//        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
//
//        TableColumn<Book, String> publishedDateColumn = new TableColumn<>("Published Date");
//        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());
//
//        // adaug coloane la tabel
//        employeeView.getBookTable().getColumns().addAll(titleColumn, authorColumn, publishedDateColumn);
//
//        // incarc initial a datele in tabel
//        loadBooks();
//    }

//    private void loadBooks() {
//        ObservableList<Book> books = FXCollections.observableArrayList(bookService.findAll());
//        employeeView.getBookTable().setItems(books);
//    }

    private class AddButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //adaug carte
            AddEmployeeView add = new AddEmployeeView();
            add.show();
            AddEmployeeView addEmployeeView = new AddEmployeeView();
            AddEmployeeController controller = new AddEmployeeController("", userService,addEmployeeView,adminView);
            addEmployeeView.show();

        }
    }

    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
          //  UpdateEmployeeView updateEmployeeView = new UpdateEmployeeView();
          //  UpdateEmployeeController controller = new UpdateEmployeeController("", bookService,updateEmployeeView); // Puteți furniza valori implicite sau lăsați goale

          //  updateEmployeeView.show();
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //sterg datele si se reincarca datele in tabel
            DeleteEmployeeView deleteBookView = new DeleteEmployeeView();
           // DeleteEmployeeController controller = new DeleteEmployeeController("", "", "", bookService,deleteEmployeeView); // Puteți furniza valori implicite sau lăsați goale

           // deleteEmployeeView.show();
        }
    }
    private class RetriveButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //sterg carte si apoi adaug
            RetriveEmployeeView retriveBookView = new RetriveEmployeeView();
           // RetriveEmployeeController controller = new RetriveEmployeeController(0L,"", "", "", bookService,retriveEmployeeView); // Puteți furniza valori implicite sau lăsați goale

           // retriveEmployeeView.show();
        }
    }
    private void displayTable(){
        List<User> users = userService.getAllUsers();
        adminView.displayUsers(users);
    }
}
