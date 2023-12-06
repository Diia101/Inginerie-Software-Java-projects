package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import model.builder.UserBuilder;
import service.user.UserService;
import view.AddEmployeeView;
import view.AdminView;
import view.UpdateEmployeeView;

import java.util.List;

public class UpdateEmployeeController  {
    private String email;
    private UpdateEmployeeView updateEmployeeView;
    private final AdminView adminView;
    private final UserService userService;

    public UpdateEmployeeController(String email,UserService userService, UpdateEmployeeView updateEmployeeView, AdminView adminView) {
        this.email = email;
        this.updateEmployeeView= updateEmployeeView;
        this.adminView= adminView;
        this.userService = userService;
        updateEmployeeView.addUpdateButtonListener(new addUpdateButtonListener());
    }

    public void processBookData(String name, String author, String publishedData) {
        System.out.println("Email: " + email);
    }

    private class addUpdateButtonListener implements EventHandler<ActionEvent>{
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
