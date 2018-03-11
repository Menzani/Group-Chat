package it.menzani.groupchat.client.view.connectiondialog;

@FunctionalInterface
public interface ConnectAction {
    Result doAction(String hostname, String username);
}
