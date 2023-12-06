package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import model.builder.UserBuilder;
import service.user.UserService;
import view.AddEmployeeView;
import view.AdminView;

import java.util.List;

public class AddEmployeeController  {
    private String email;
    private final AddEmployeeView addEmployeeView;
    private final AdminView adminView;
    private final UserService userService;

    public AddEmployeeController(String email,UserService userService, AddEmployeeView addEmployeeView, AdminView adminView) {
        this.email = email;
        this.addEmployeeView=addEmployeeView;
        this.adminView= adminView;
        this.userService = userService;
        addEmployeeView.addAddButtonListener(new addAddButtonListener());
    }

    public void processBookData(String name, String author, String publishedData) {
        System.out.println("Email: " + email);
    }

    private class addAddButtonListener implements EventHandler<ActionEvent>{
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
