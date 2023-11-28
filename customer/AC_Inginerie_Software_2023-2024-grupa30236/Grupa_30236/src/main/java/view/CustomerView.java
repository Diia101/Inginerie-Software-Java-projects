package view;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.User;

import java.util.List;

public class CustomerView extends Parent {
    private Stage primaryStage; // Adăugați membrul de instanță Stage
    private TableView<Book> bookTable;
    private Button buyButton;
    private Text actiontarget;
    private String welcomeMessage;



    public CustomerView(Stage primaryStage, List<Book> availableBooks, User user) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Customer Dashboard");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeBookTable(availableBooks, gridPane);

        buyButton = new Button("Buy Selected Book");
        HBox buyButtonHBox = new HBox(10);
        buyButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        buyButtonHBox.getChildren().add(buyButton);
        gridPane.add(buyButtonHBox, 1, 3);

        actiontarget = new Text();
        gridPane.add(actiontarget, 1, 5);
    }

//    public CustomerView() {
//
//    }


    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome to our Book Store - Customer Dashboard");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeBookTable(List<Book> availableBooks, GridPane gridPane) {
        if (availableBooks != null && !availableBooks.isEmpty()) {
            ObservableList<Book> data = FXCollections.observableArrayList(availableBooks);

            TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
            titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

            TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
            authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

            TableColumn<Book, String> publishedDateColumn = new TableColumn<>("Published Date");
            publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());

            bookTable.getColumns().addAll(titleColumn, authorColumn, publishedDateColumn);
            bookTable.setItems(data);

            VBox tableVBox = new VBox();
            tableVBox.getChildren().addAll(bookTable);
            gridPane.add(tableVBox, 0, 2, 2, 1);
        } else {
            // Handle the case when availableBooks is null or empty
            // For example, display a message or handle it according to your requirements
            System.out.println("No available books.");
        }
    }
    public void addActionTargetText(String text){ this.actiontarget.setText(text);}

    public Book getSelectedBook() {
        return bookTable.getSelectionModel().getSelectedItem();
    }

    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener) {
        buyButton.setOnAction(buyButtonListener);
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    public TableView<Book> getBookTable() {
        return bookTable;
    }


    public void show() {
        primaryStage.show();
    }
}
