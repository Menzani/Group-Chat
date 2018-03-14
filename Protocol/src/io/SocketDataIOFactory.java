package it.menzani.groupchat.protocol.io;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class SocketDataIOFactory implements DataIOFactory {
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public SocketDataIOFactory(Socket socket) throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public Charset charset() {
        return StandardCharsets.UTF_16;
    }

    @Override
    public DataInput input() {
        return new BufferedDataInputStream(inputStream);
    }

    @Override
    public DataOutput output() {
        return new DataOutputStream(outputStream);
    }
}
