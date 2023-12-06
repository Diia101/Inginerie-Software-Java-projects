package view;

import controller.EmployeeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.User;

import java.util.List;

public class EmployeeView  {
    private Stage primaryStage;
    private TableView<Book> bookTable;
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private Button retriveButton;
    private Text actiontarget;

    public EmployeeView(Stage primaryStage, List<Book> books, User user) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Employee Dashboard");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);

        bookTable = new TableView<>();
        initializeBookTable(gridPane);

        addButton = new Button("Add Book");
        updateButton = new Button("Update Book");
        deleteButton = new Button("Delete Book");
        retriveButton = new Button("Retrive Book");

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
        Text sceneTitle = new Text("Welcome to Employee Dashboard");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeBookTable(GridPane gridPane) {
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        TableColumn<Book, String> publishedDateColumn = new TableColumn<>("Published Date");
        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());

        bookTable.getColumns().addAll(titleColumn, authorColumn, publishedDateColumn);
        gridPane.add(bookTable, 0, 2, 2, 1);

      // ObservableList<Book> data = FXCollections.observableArrayList();
    //    bookTable.setItems(data);
    }

    public void addActionTargetText(String text) {
        this.actiontarget.setText(text);
    }

    public Book getSelectedBook() {
        return bookTable.getSelectionModel().getSelectedItem();
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

    public TableView<Book> getBookTable() {
        return bookTable;
    }

    public void show() {
        primaryStage.show();
    }

    public void displayBooks(List<Book> books)
    {
        bookTable.getItems().clear();  //curata tabelu
        ObservableList<Book> observableList = FXCollections.observableArrayList(books);
        bookTable.setItems(observableList);
    }


}


