package it.menzani.groupchat.client.view.chat;

import it.menzani.groupchat.client.App;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ChatView extends AnchorPane {
    static final double WIDTH = 700D;

    private final Label usersLabel = new Label();
    private final VBox messageHistoryVBox = new VBox();
    private final ScrollPane messageHistoryScrollPane = new ScrollPane(messageHistoryVBox);
    private final TextField messageTextField = new TextField();

    public ChatView() {
        usersLabel.setFont(App.CUSTOM_FONT);
        usersLabel.setTextAlignment(TextAlignment.CENTER);
        usersLabel.setWrapText(true);
        usersLabel.setMaxHeight(95D);
        usersLabel.setPadding(new Insets(2D));
        usersLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, null, null)));
        setTopAnchor(usersLabel, 2D);
        setLeftAnchor(usersLabel, 100D);
        setRightAnchor(usersLabel, 100D);

        messageHistoryScrollPane.setFitToWidth(true);
        messageHistoryScrollPane.getStylesheets().add("whiteScrollPane.css");

        messageTextField.setFont(App.CUSTOM_FONT);
        VBox.setMargin(messageTextField, new Insets(5D));

        VBox messagesVBox = new VBox(messageHistoryScrollPane, messageTextField);
        messagesVBox.setMaxHeight(500D);
        setBottomAnchor(messagesVBox, 0D);
        setLeftAnchor(messagesVBox, 0D);
        setRightAnchor(messagesVBox, 0D);

        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        setPrefSize(WIDTH, 600D);
        getChildren().addAll(usersLabel, messagesVBox);

        setUsers(Collections.emptyList());
        generateLoad();
    }

    private void generateLoad() {
        Platform.runLater(() -> {
            final List<String> users = Arrays.asList("Ezechiele", "Gertrude", "Amilcare", "Camilla", "Barbara", "Marco",
                    "Gioacchino", "Amelia", "Narciso", "Lollo", "Carla", "Valentina", "John", "Bill");
            setUsers(users);

            final String[] messages = {
                    "Great care should be taken", "to ensure proper attribution is given.", "Some care must be exercised when doing this.",
                    "Performance may suffer otherwise.", "好，休是休士顿，我们这里已经出问题了",
                    "Miracle skin cream", "Join a Stellar revolution!", "Di cosa sono fatti i mattoni?",
                    "Siamo fatti della stessa materia", "di cui sono fatti i sogni.", "La consistenza dei mattoni può spiegare molte cose...",
                    "per assicurare il legno del bastoncino", "That's working exactly as intended!",
                    "Cosa succede se improvvisamente ogni goccia d'acqua", "这就是斯巴达!", "Sono completamente daccordo con il messaggio precedente.",
                    "I completely agree with the previous message.", "L'acqua sarà l'oro del futuro."
            };
            for (int i = 0; i < 7; i++) {
                addMessage("System", messages[i]);
            }
        });
    }

    public void addMessage(String sender, String message) {
        messageHistoryVBox.getChildren().add(new TextMessage(sender + ":" + System.lineSeparator() + message));
        messageHistoryScrollPane.setVvalue(1D);
    }

    public void setUsers(List<String> users) {
        if (users.isEmpty()) {
            usersLabel.setText("No other users connected.");
            usersLabel.setTextFill(Color.GRAY);
        } else {
            usersLabel.setText(users.stream().collect(Collectors.joining("  ")));
            usersLabel.setTextFill(Color.BLACK);
        }
    }
}
