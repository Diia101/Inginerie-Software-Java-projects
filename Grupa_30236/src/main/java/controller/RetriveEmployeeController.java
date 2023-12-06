package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import model.builder.UserBuilder;
import service.user.UserService;
import view.AddEmployeeView;
import view.AdminView;
import view.RetriveEmployeeView;

import java.util.List;

public class RetriveEmployeeController  {
    private String email;
    private  RetriveEmployeeView retriveEmployeeView;
    private final AdminView adminView;
    private final UserService userService;

    public RetriveEmployeeController(String email,UserService userService, RetriveEmployeeView retriveeEmployeeView, AdminView adminView) {
        this.email = email;
        this.retriveEmployeeView= retriveEmployeeView;
        this.adminView= adminView;
        this.userService = userService;
        retriveEmployeeView.addRetriveButtonListener(new addRetriveButtonListener());
    }

    public void processBookData(String name, String author, String publishedData) {
        System.out.println("Email: " + email);
    }

    private class addRetriveButtonListener implements EventHandler<ActionEvent>{
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
