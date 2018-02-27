package it.menzani.groupchat.client.view.connectionDialog;

@FunctionalInterface
public interface ConnectAction {
    Result doAction(String hostname, String username);
}
