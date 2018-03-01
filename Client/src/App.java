package it.menzani.groupchat.client;

import it.menzani.groupchat.client.controller.ConnectionDialogController;
import it.menzani.groupchat.client.controller.Controller;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public final class App extends Application {
    public static Font CUSTOM_FONT = Font.font("Source Sans Pro", 13D);

    private Stage primaryStage;

    @Override
    public void init() throws IOException {
        if (getParameters().getUnnamed().contains("--load-custom-font")) {
            Font font = loadCustomFont();
            if (font != null) CUSTOM_FONT = font;
        }
    }

    private Font loadCustomFont() throws IOException {
        try (InputStream stream = getClass().getResourceAsStream("SourceSansPro-Regular.otf")) {
            Font font = Font.loadFont(stream, 13D);
            if (font == null) {
                System.err.println("Could not load custom font; might fall back to system default.");
            }
            return font;
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        try (InputStream stream = getClass().getResourceAsStream("icon.png")) {
            primaryStage.getIcons().add(new Image(stream));
        }
        primaryStage.setTitle("Group Chat");
        primaryStage.setResizable(false);
        setController(new ConnectionDialogController(this));
    }

    public void packPrimaryStage() {
        primaryStage.sizeToScene();
    }

    public void setController(Controller controller) {
        Scene scene = primaryStage.getScene();
        Parent root = controller.createRoot();
        if (scene == null) {
            primaryStage.setScene(new Scene(root));
        } else {
            primaryStage.hide();
            scene.setRoot(root);
            primaryStage.centerOnScreen();
        }
        packPrimaryStage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
