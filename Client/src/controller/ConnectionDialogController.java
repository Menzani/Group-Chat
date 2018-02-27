package it.menzani.groupchat.client.controller;

import it.menzani.groupchat.client.App;
import it.menzani.groupchat.client.view.connectionDialog.Component;
import it.menzani.groupchat.client.view.connectionDialog.ConnectionDialogView;
import it.menzani.groupchat.client.view.connectionDialog.Result;
import javafx.application.Platform;
import javafx.scene.Parent;

public final class ConnectionDialogController implements Controller {
    private final App app;
    private final ConnectionDialogView view;

    public ConnectionDialogController(App app) {
        this.app = app;
        view = new ConnectionDialogView(app);
    }

    @Override
    public Parent createRoot() {
        view.setCancelAction(Platform::exit); // TODO Will cancel any "connection" task in any given executor and shutdown that executor too.
        view.setConnectAction((hostname, username) -> {
            if (hostname.equals("localhost") && username.equals("System")) {
                app.setController(new ChatController());
                return Result.success();
            }
            return Result.error("Username is not valid.", Component.USERNAME);
        });
        return view;
    }
}
