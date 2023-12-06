package view;

import controller.UpdateBookController;
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

public class RetriveEmployeeView extends Stage {

    private static TextField emailText;
    private  Button backBtn;
    private  Button updateBtn ;

    public RetriveEmployeeView() {
        setTitle("Update Employee");
        setWidth(250);
        setHeight(329);

        VBox root = new VBox(10);
        root.setPadding(new Insets(5));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Retrive Employee");

        HBox nameBox = createInputBox("Email:");



        emailText = (TextField) nameBox.getChildren().get(1);


        updateBtn = new Button("Retrive");
        backBtn = new Button("Back");

        HBox buttonBox = new HBox(10, updateBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel,  nameBox, buttonBox);

        Scene scene = new Scene(root);
        setScene(scene);
    }

    public RetriveEmployeeView(TextField nameText, Button backBtn, Button updateBtn) {
        this.emailText = nameText;
        this.backBtn = backBtn;
        this.updateBtn = updateBtn;
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

    public Button getUpdateBtn() {
        return updateBtn;
    }

    public static String getEmail() {
        return emailText.getText();
    }


    public void clearFields() {
        emailText.clear();
    }

    public RetriveEmployeeView(UpdateBookController controller) {
        // ...

        updateBtn.setOnAction(event -> {
            String email = getEmail();


            //  controller.processBookData(email);

            close();
        });
    }
    public void addRetriveButtonListener(EventHandler<ActionEvent> updateButtonListener) {
        updateBtn.setOnAction(updateButtonListener);
    }
}

