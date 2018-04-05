package it.menzani.groupchat.server;

import it.menzani.groupchat.protocol.io.DataIOFactory;
import it.menzani.groupchat.protocol.io.SocketDataIOFactory;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.net.Socket;

final class ClientHandle implements Runnable {
    private final DataInput in;
    private final DataOutput out;

    ClientHandle(Socket socket) throws IOException {
        DataIOFactory dataIOFactory = new SocketDataIOFactory(socket);
        in = dataIOFactory.input();
        out = dataIOFactory.output();
    }

    private void doRun() throws IOException {

    }

    @Override
    public void run() {
        try {
            doRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
