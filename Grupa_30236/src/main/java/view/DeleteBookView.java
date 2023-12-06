package view;

import controller.DeleteBookController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;

import java.util.List;

public class DeleteBookView extends Stage {

    private TableView<Book> bookTable;
    private static TextField idText;
    private static TextField nameText;
    private static TextField authorText;
    private static TextField publishedDateText;
    private Button backBtn;
    private Button deleteBtn;
    private Stage primaryStage;

    public DeleteBookView() {
        setTitle("Delete Book");
        setWidth(250);
        setHeight(329);
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        bookTable = new TableView<>();
        initializeBookTable();  // Modificare aici

        VBox root = new VBox(10);
        root.setPadding(new Insets(5));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Delete Book");

        HBox idBox = createInputBox("Id:");
        HBox nameBox = createInputBox("Name:");
        HBox authorBox = createInputBox("Author");
        HBox publishedDataBox = createInputBox("PublishedData:");

        idText = (TextField) idBox.getChildren().get(1);
        nameText = (TextField) nameBox.getChildren().get(1);
        authorText = (TextField) authorBox.getChildren().get(1);
        publishedDateText = (TextField) publishedDataBox.getChildren().get(1);

        deleteBtn = new Button("Delete");
        backBtn = new Button("Back");

        HBox buttonBox = new HBox(10, deleteBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel, idBox, nameBox, authorBox, publishedDataBox, bookTable, buttonBox);  // Modificare aici

        Scene scene = new Scene(root);
        setScene(scene);
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeBookTable() {
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        TableColumn<Book, String> publishedDateColumn = new TableColumn<>("Published Date");
        publishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishedDateProperty());

        bookTable.getColumns().addAll(titleColumn, authorColumn, publishedDateColumn);
    }

    private HBox createInputBox(String label) {
        Label lbl = new Label(label);

        TextField textField = new TextField();
        textField.setPrefWidth(150);

        HBox box = new HBox(10, lbl, textField);
        box.setAlignment(Pos.CENTER);

        return box;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    //    public static Long getId() {
//        return Long.valueOf(idText.getText());
//    }
    public static Long getId() {
        String idString = idText.getText().trim();
        if (idString.isEmpty()) {
            //daca textul e gol
            return null;
        }
        try {
            return Long.valueOf(idString);
        } catch (NumberFormatException e) {
            //daca conversia esueaza
            return null;
        }
    }

    public static String getName() {
        return nameText.getText();
    }

    public static String getAuthor() {
        return authorText.getText();
    }

    public static String getPublishedDate() {
        return publishedDateText.getText();
    }

    public void clearFields() {
        nameText.clear();
        authorText.clear();
        publishedDateText.clear();
    }

    public DeleteBookView(DeleteBookController controller) {
        deleteBtn.setOnAction(event -> {
            String name = getName();
            String author = getAuthor();
            String publishedData = getPublishedDate();

            controller.processBookData(name, author, publishedData);


            close();
        });
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteBtn.setOnAction(deleteButtonListener);
    }

    public void displayBooks(List<Book> books) {
        bookTable.getItems().clear();
        ObservableList<Book> observableList = FXCollections.observableArrayList(books);
        bookTable.setItems(observableList);
    }
}
