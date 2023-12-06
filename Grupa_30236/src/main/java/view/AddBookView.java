package view;

import controller.AddBookController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBookView extends Stage {

    private static TextField nameText;
    private static TextField authorText;
    private static TextField publishedDateText;
    private  Button backBtn;
    private  Button addBtn ;


    public AddBookView() {
        setTitle("Add Book");
        setWidth(250);
        setHeight(329);

        VBox root = new VBox(10);
        root.setPadding(new Insets(5));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Add Book");

        HBox nameBox = createInputBox("Name:");
        HBox authorBox = createInputBox("Author");
        HBox publishedDataBox = createInputBox("PublishedData:");


        nameText = (TextField) nameBox.getChildren().get(1);
        authorText = (TextField)authorBox.getChildren().get(1);
       publishedDateText = (TextField) publishedDataBox.getChildren().get(1);

        addBtn = new Button("Add");
        backBtn = new Button("Back");

        HBox buttonBox = new HBox(10, addBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel,  nameBox, authorBox, publishedDataBox, buttonBox);

        Scene scene = new Scene(root);
        setScene(scene);

    }

    public AddBookView(TextField nameText, TextField authorText, TextField publishedDataText, Button backBtn, Button addBtn) {
        this.nameText = nameText;
        this.authorText = authorText;
        this.publishedDateText = publishedDataText;
        this.backBtn = backBtn;
        this.addBtn = addBtn;
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

    public Button getAddBtn() {
        return addBtn;
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

    public AddBookView(AddBookController controller) {
        // ...

        addBtn.setOnAction(event -> {
            String name = getName();
            String author = getAuthor();
            String publishedDate = getPublishedDate();


            controller.processBookData(name, author, publishedDate);

            close();
        });
    }
    public void addAddButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addBtn.setOnAction(addButtonListener);
    }
}
