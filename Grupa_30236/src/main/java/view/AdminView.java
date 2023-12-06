package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.User;

import java.util.List;

public class AdminView  {
    private Stage primaryStage;
    private TableView<User> userTable;
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private Button retriveButton;
    private Text actiontarget;

    public AdminView(Stage primaryStage, List<Book> books, User user) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Admin Dashboard");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);

        userTable = new TableView<>();
        initializeUserTable( gridPane);

        addButton = new Button("Add Employee");
        updateButton = new Button("Update Employee");
        deleteButton = new Button("Delete Employee");
        retriveButton = new Button("Retrive Employee");

        HBox buttonHBox = new HBox(10);
        buttonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonHBox.getChildren().addAll(addButton, updateButton, deleteButton,retriveButton);
        gridPane.add(buttonHBox, 1, 3);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 5);
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Welcome to Admin Dashboard");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeUserTable(GridPane gridPane) {
        TableColumn<User, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        userTable.getColumns().addAll(idColumn,usernameColumn);
        gridPane.add(userTable, 0, 2, 2, 1);

        // ObservableList<Book> data = FXCollections.observableArrayList();
        //    bookTable.setItems(data);
    }

    public void addActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public User getSelectedUser() {
        return userTable.getSelectionModel().getSelectedItem();
    }

    public void addAddButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addButton.setOnAction(addButtonListener);
    }

    public void addUpdateButtonListener(EventHandler<ActionEvent> updateButtonListener) {
        updateButton.setOnAction(updateButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }
    public void addRetriveButtonListener(EventHandler<ActionEvent> retriveButtonListener) {
        retriveButton.setOnAction(retriveButtonListener);
    }

//    @Override
//    public Node getStyleableNode() {
//        return super.getStyleableNode();
//    }

    public TableView<User> getUserTable() {
        return userTable;
    }

    public void show() {
        primaryStage.show();
    }

    public void displayUsers(List<User> users)
    {
        userTable.getItems().clear();  //curata tabelu
        ObservableList<User> observableList = FXCollections.observableArrayList(users);
        userTable.setItems(observableList);
    }


}
