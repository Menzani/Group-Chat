package it.menzani.groupchat.client.view.chat;

@FunctionalInterface
public interface MessageSendAction {
    void doAction(String message);
}
