package it.menzani.groupchat.client.controller;

import it.menzani.groupchat.client.view.chat.ChatView;
import javafx.scene.Parent;

public final class ChatController implements Controller {
    private final ChatView view = new ChatView();

    @Override
    public Parent createRoot() {
        view.setMessageSendAction(message -> view.addMessage("You", message));
        return view;
    }
}
