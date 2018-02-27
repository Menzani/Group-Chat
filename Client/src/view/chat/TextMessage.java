package it.menzani.groupchat.client.view.chat;

import it.menzani.groupchat.client.App;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

final class TextMessage extends TextArea {
    TextMessage(String text) {
        super(text);
        setFont(App.CUSTOM_FONT);
        setEditable(false);
        setWrapText(true);
        setFocusTraversable(false);
        setPrefHeight(calculateHeight(text));
        setStyle("-fx-text-box-border: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) return;
            deselect();
        });
    }

    // https://stackoverflow.com/a/37007833/3453226
    private double calculateHeight(String text) {
        Text textControl = new Text(text);
        textControl.setWrappingWidth(ChatView.WIDTH);
        textControl.setFont(getFont());
        Parent pane = new StackPane(textControl);
        pane.layout();
        return textControl.getLayoutBounds().getHeight() + 30D;
    }
}
