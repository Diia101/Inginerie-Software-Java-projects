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

public class AddEmployeeView extends Stage {

    private static TextField emailText;
    private  Button backBtn;
    private  Button addBtn ;


    public AddEmployeeView() {
        setTitle("Add Employee");
        setWidth(250);
        setHeight(329);

        VBox root = new VBox(10);
        root.setPadding(new Insets(5));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Add Employee");

        HBox emailBox = createInputBox("Email:");


        emailText = (TextField) emailBox.getChildren().get(1);

        addBtn = new Button("Add");
        backBtn = new Button("Back");

        HBox buttonBox = new HBox(10, addBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel,  emailBox, buttonBox);

        Scene scene = new Scene(root);
        setScene(scene);

    }

    public AddEmployeeView(TextField emailText, TextField authorText, TextField publishedDataText, Button backBtn, Button addBtn) {
        this.emailText = emailText;
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

    public static String getEmail() {
        return emailText.getText();
    }

    public void clearFields() {
        emailText.clear();
    }

    public AddEmployeeView(AddBookController controller) {
        // ...

        addBtn.setOnAction(event -> {
            String email = getEmail();

        //    controller.processBookData(email);

            // Închideți fereastra după adăugare
            close();
        });
    }
    public void addAddButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addBtn.setOnAction(addButtonListener);
    }
}
