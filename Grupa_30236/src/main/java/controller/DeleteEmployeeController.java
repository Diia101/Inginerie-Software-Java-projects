package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import model.builder.UserBuilder;
import service.user.UserService;
import view.AddEmployeeView;
import view.AdminView;
import view.DeleteEmployeeView;

import java.util.List;

public class DeleteEmployeeController  {
    private String email;
    private final DeleteEmployeeView deleteEmployeeView;
    private final AdminView adminView;
    private final UserService userService;

    public DeleteEmployeeController(String email,UserService userService, DeleteEmployeeView deleteEmployeeView, AdminView adminView) {
        this.email = email;
        this.deleteEmployeeView=deleteEmployeeView;
        this.adminView= adminView;
        this.userService = userService;
        deleteEmployeeView.addDeleteButtonListener(new addDeleteButtonListener());
    }

    public void processBookData(String name, String author, String publishedData) {
        System.out.println("Email: " + email);
    }

    private class addDeleteButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event)
        {
            String email = AddEmployeeView.getEmail();

            User u = new UserBuilder().setUsername(email).build();
            // UserRepository.save(u);
            displayTable();
        }
        private void displayTable(){
            List<User> users = userService.getAllUsers();
            adminView.displayUsers(users);
        }
    }

}
