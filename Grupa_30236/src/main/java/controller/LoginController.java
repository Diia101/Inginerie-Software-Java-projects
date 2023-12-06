package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import launcher.ComponentFactory;
import model.Book;
import model.User;
import model.validator.Notification;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.user.AuthenticationService;
import view.AdminView;
import view.CustomerView;
import view.EmployeeView;
import view.LoginView;

import java.util.List;

public class LoginController {
    private final LoginView loginView;
    public BookServiceImpl bookService ;
    private final AuthenticationService authenticationService;



    public LoginController(LoginView loginView, AuthenticationService authenticationService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());

    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()) {
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("LogIn Successful!");
                if (loginNotification.getResult().getRoles().get(0).getRole().equals("administrator")) {
                     showAdminView();
                }
                   else if (loginNotification.getResult().getRoles().get(0).getRole().equals("employee")) {
                    showEmployeeView();
                }
                    else {
                   showCustomerView();
                }
               //  loginView.close();
            }
        }
    }

    private void showAdminView() {
        BookService bookService = ComponentFactory.getInstance(false, new Stage()).getBookService();
        List<Book> availableBooks = bookService.findAll();
        User user = new User();
        AdminView adminView = new AdminView(new Stage(), availableBooks, user);
        adminView.show();
    }

    private void showEmployeeView() {
        BookService bookService = ComponentFactory.getInstance(false, new Stage()).getBookService();
        List<Book> availableBooks = bookService.findAll();
        User user = new User();
        EmployeeView employeeView = new EmployeeView(new Stage(), availableBooks, user);
        EmployeeController employeeController = new EmployeeController(employeeView,bookService);
        employeeView.show();
    }

    private void showCustomerView() {
        BookService bookService = ComponentFactory.getInstance(false, new Stage()).getBookService();
        List<Book> availableBooks = bookService.findAll();
        User user = new User();
        CustomerView customerView = new CustomerView(new Stage(), availableBooks, user);
        customerView.show();
    }
//    private void showAdminView() {
//        BookService bookService = ComponentFactory.getInstance(false, new Stage()).getBookService();
//        List<Book> availableBooks = bookService.findAll();
//        User user = new User();
//        AdminView adminView = new AdminView(new Stage(), availableBooks, user);
//        AdminController adminController = new AdminController(adminView,bookService);
//        adminView.show();
//    }
    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register Successful!");
            }
        }
    }

}
