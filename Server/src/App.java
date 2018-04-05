package it.menzani.groupchat.server;

import it.menzani.groupchat.protocol.ProtocolConstants;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class App implements Runnable {
    private App() {
    }

    private final ExecutorService clientPool = Executors.newCachedThreadPool();

    private void doRun() throws IOException {
        ServerSocket socket = new ServerSocket(ProtocolConstants.TCP_PORT);
        while (true) {
            ClientHandle handle = new ClientHandle(socket.accept());
            clientPool.execute(handle);
        }
    }

    @Override
    public void run() {
        try {
            doRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
