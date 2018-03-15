package it.menzani.groupchat.client.view.chat;

import it.menzani.groupchat.client.App;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ChatView extends AnchorPane {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    static final double WIDTH = 700D;

    private final Label usersLabel = new Label();
    private final VBox messageHistoryVBox = new VBox();
    private final ScrollPane messageHistoryScrollPane = new ScrollPane(messageHistoryVBox);
    private final TextArea messageTextArea = new TextArea();

    public ChatView() {
        createChildren();
        createListeners();

        setUsers(Collections.emptyList());
        generateLoad();
    }

    private void createChildren() {
        usersLabel.setFont(App.CUSTOM_FONT);
        usersLabel.setTextAlignment(TextAlignment.CENTER);
        usersLabel.setWrapText(true);
        usersLabel.setMaxHeight(95D);
        usersLabel.setPadding(new Insets(2D));
        usersLabel.setFocusTraversable(true); // Prevents messageTextArea from getting the focus on load.
        usersLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, null, null)));
        setTopAnchor(usersLabel, 2D);
        setLeftAnchor(usersLabel, 100D);
        setRightAnchor(usersLabel, 100D);

        messageHistoryScrollPane.setFitToWidth(true);
        messageHistoryScrollPane.getStylesheets().add(App.resolvePath("whiteScrollPane.css"));

        messageTextArea.setFont(App.CUSTOM_FONT);
        messageTextArea.setPrefRowCount(1);
        messageTextArea.setPromptText("Write your message here and press Enter");
        messageTextArea.setWrapText(true);
        VBox.setMargin(messageTextArea, new Insets(5D));

        VBox messagesVBox = new VBox(messageHistoryScrollPane, messageTextArea);
        messagesVBox.setMaxHeight(500D);
        setBottomAnchor(messagesVBox, 0D);
        setLeftAnchor(messagesVBox, 0D);
        setRightAnchor(messagesVBox, 0D);

        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        setPrefSize(WIDTH, 600D);
        getChildren().addAll(usersLabel, messagesVBox);
    }

    private void createListeners() {
        messageHistoryVBox.heightProperty().addListener(observable -> messageHistoryScrollPane.setVvalue(1D));

        messageTextArea.focusedProperty().addListener(observable -> usersLabel.setFocusTraversable(false));
        messageTextArea.setOnKeyReleased(event -> {
            if (event.getCode() != KeyCode.ESCAPE) return;
            messageTextArea.clear();
        });
    }

    private void generateLoad() {
        Platform.runLater(() -> {
            final List<String> users = Arrays.asList("Ezechiele", "Gertrude", "Amilcare", "Camilla", "Barbara", "Marco",
                    "Gioacchino", "Amelia", "Narciso", "Lollo", "Carla", "Valentina", "John", "Bill");
            setUsers(users);

            final String[] messages = {
                    "Great care should be taken", "to ensure proper attribution is given.",
                    "Some care must be exercised when doing this.", "Performance may suffer otherwise.",
                    "好，休是休士顿，我们这里已经出问题了", "Sono completamente daccordo con il messaggio precedente.",
                    "Miracle skin cream", "Join a Stellar revolution!", "Di cosa sono fatti i mattoni?",
                    "Siamo fatti della stessa materia", "di cui sono fatti i sogni.",
                    "La consistenza dei mattoni può spiegare molte cose...", "per assicurare il legno del bastoncino",
                    "That's working exactly as intended!", "Cosa succede se improvvisamente ogni goccia d'acqua",
                    "这就是斯巴达!", "I completely agree with the previous message.", "L'acqua sarà l'oro del futuro."
            };
            for (int i = 0; i < 7; i++) {
                addMessage("System", messages[i]);
            }
        });
    }

    public void setMessageSendAction(MessageSendAction action) {
        messageTextArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            if (event.isControlDown() || event.isShiftDown()) {
                messageTextArea.appendText(LINE_SEPARATOR);
                return;
            }
            event.consume();
            String text = messageTextArea.getText();
            messageTextArea.clear();
            action.doAction(text);
        });
    }

    public void addMessage(String sender, String message) {
        Node node = new TextMessage(sender + ":" + LINE_SEPARATOR + message);
        messageHistoryVBox.getChildren().add(node);
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
