package it.menzani.groupchat.protocol.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class PipedDataIOFactory implements DataIOFactory {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Override
    public Charset charset() {
        return StandardCharsets.UTF_16;
    }

    @Override
    public DataInput input() {
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return new BufferedDataInputStream(inputStream);
    }

    @Override
    public DataOutput output() {
        return new DataOutputStream(outputStream);
    }

    public String outputToString() throws UnsupportedEncodingException {
        return outputStream.toString(charset().name());
    }
}
