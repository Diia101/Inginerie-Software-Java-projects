package view;

import controller.UpdateBookController;
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

public class UpdateBookView extends Stage {

    private TableView<Book> bookTable;
    private static TextField idText;
    private static TextField nameText;
    private static TextField authorText;
    private static TextField publishedDateText;
    private Button backBtn;
    private Button updateBtn;
    private Stage primaryStage;

    public UpdateBookView() {
        setTitle("Update Book");
        setWidth(250);
        setHeight(329);
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        bookTable = new TableView<>();
        initializeBookTable();  // Modificare aici

        VBox root = new VBox(10);
        root.setPadding(new Insets(5));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Update Book");

        HBox idBox = createInputBox("Id:");
        HBox nameBox = createInputBox("Name:");
        HBox authorBox = createInputBox("Author");
        HBox publishedDataBox = createInputBox("PublishedData:");

        idText = (TextField) idBox.getChildren().get(1);
        nameText = (TextField) nameBox.getChildren().get(1);
        authorText = (TextField) authorBox.getChildren().get(1);
        publishedDateText = (TextField) publishedDataBox.getChildren().get(1);

        updateBtn = new Button("Update");
        backBtn = new Button("Back");

        HBox buttonBox = new HBox(10, updateBtn, backBtn);
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
        return updateBtn;
    }

    //    public static Long getId() {
//        return Long.valueOf(idText.getText());
//    }
    public static Long getId() {
        String idString = idText.getText().trim();
        if (idString.isEmpty()) {

            return null;
        }
        try {
            return Long.valueOf(idString);
        } catch (NumberFormatException e) {
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

    public UpdateBookView(UpdateBookController controller) {
        updateBtn.setOnAction(event -> {
            String name = getName();
            String author = getAuthor();
            String publishedData = getPublishedDate();

            //date -> controler
            controller.processBookData(name, author, publishedData);

            close();
        });
    }

    public void addUpdateButtonListener(EventHandler<ActionEvent> updateButtonListener) {
        updateBtn.setOnAction(updateButtonListener);
    }

    public void displayBooks(List<Book> books) {
        bookTable.getItems().clear();
        ObservableList<Book> observableList = FXCollections.observableArrayList(books);
        bookTable.setItems(observableList);
    }
}
