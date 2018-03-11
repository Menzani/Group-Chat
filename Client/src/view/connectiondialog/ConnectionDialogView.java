package it.menzani.groupchat.client.view.connectiondialog;

import it.menzani.groupchat.client.App;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public final class ConnectionDialogView extends GridPane {
    private final App app;
    private final TextField hostnameTextField = new TextField();
    private final TextField usernameTextField = new TextField();
    private final Button cancelButton = new Button("Cancel");
    private final Button connectButton = new Button("Connect");
    private final Label errorMessageLabel = new Label();

    public ConnectionDialogView(App app) {
        this.app = app;
        createChildren();
        createListeners();
    }

    private void createChildren() {
        Label hostnameLabel = new Label("Hostname:");
        hostnameLabel.setLabelFor(hostnameTextField);
        setMargin(hostnameLabel, new Insets(10D, 10D, 0D, 10D));
        hostnameLabel.setFont(App.CUSTOM_FONT);
        Label usernameLabel = new Label("Username:");
        usernameLabel.setLabelFor(usernameTextField);
        setMargin(usernameLabel, new Insets(10D));
        usernameLabel.setFont(App.CUSTOM_FONT);

        hostnameTextField.setPrefColumnCount(17);
        setMargin(hostnameTextField, new Insets(10D, 10D, 0D, 0D));
        hostnameTextField.setFont(App.CUSTOM_FONT);
        usernameTextField.setPrefColumnCount(17);
        setMargin(usernameTextField, new Insets(10D, 10D, 10D, 0D));
        usernameTextField.setFont(App.CUSTOM_FONT);

        cancelButton.setCancelButton(true);
        cancelButton.setFont(App.CUSTOM_FONT);
        connectButton.setDefaultButton(true);
        connectButton.setFont(App.CUSTOM_FONT);

        HBox buttonsHBox = new HBox(10D, cancelButton, connectButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);
        setMargin(buttonsHBox, new Insets(0D, 10D, 10D, 0D));

        setMargin(errorMessageLabel, new Insets(0D, 10D, 10D, 20D));
        errorMessageLabel.setTextFill(Color.RED);
        errorMessageLabel.setFont(App.CUSTOM_FONT);

        addRow(0, hostnameLabel, hostnameTextField);
        addRow(1, usernameLabel, usernameTextField);
        add(buttonsHBox, 1, 2);
    }

    private void createListeners() {
        final InvalidationListener listener = observable -> {
            boolean contained = getChildren().remove(errorMessageLabel);
            if (contained) {
                connectButton.setDisable(false);
                app.packPrimaryStage();
            }
        };
        hostnameTextField.textProperty().addListener(listener);
        usernameTextField.textProperty().addListener(listener);
    }

    public void setCancelAction(Runnable action) {
        cancelButton.setOnAction(event -> action.run());
    }

    public void setConnectAction(ConnectAction action) {
        connectButton.setOnAction(event -> {
            Result result = action.doAction(hostnameTextField.getText(), usernameTextField.getText());
            if (result.isSuccess()) return;

            connectButton.setDisable(true);
            errorMessageLabel.setText(result.getErrorMessage());
            add(errorMessageLabel, 0, 3, 2, 1);
            app.packPrimaryStage();
            switch (result.getErrorTarget()) {
                case HOSTNAME:
                    hostnameTextField.selectAll();
                    hostnameTextField.requestFocus();
                    break;
                case USERNAME:
                    usernameTextField.selectAll();
                    usernameTextField.requestFocus();
                    break;
            }
        });
    }
}
