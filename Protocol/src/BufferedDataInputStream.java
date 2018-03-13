package it.menzani.groupchat.protocol;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public final class BufferedDataInputStream implements BufferedDataInput {
    private final InputStream stream;
    private final DataInput in;

    public BufferedDataInputStream(InputStream stream) {
        this.stream = new BufferedInputStream(stream);
        in = new DataInputStream(this.stream);
    }

    @Override
    public void mark(int readLimit) {
        stream.mark(readLimit);
    }

    @Override
    public void reset() throws IOException {
        stream.reset();
    }

    @Override
    public void readFully(@NotNull byte[] b) throws IOException {
        in.readFully(b);
    }

    @Override
    public void readFully(@NotNull byte[] b, int off, int len) throws IOException {
        in.readFully(b, off, len);
    }

    @Override
    public int skipBytes(int n) throws IOException {
        return in.skipBytes(n);
    }

    @Override
    public boolean readBoolean() throws IOException {
        return in.readBoolean();
    }

    @Override
    public byte readByte() throws IOException {
        return in.readByte();
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return in.readUnsignedByte();
    }

    @Override
    public short readShort() throws IOException {
        return in.readShort();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return in.readUnsignedShort();
    }

    @Override
    public char readChar() throws IOException {
        return in.readChar();
    }

    @Override
    public int readInt() throws IOException {
        return in.readInt();
    }

    @Override
    public long readLong() throws IOException {
        return in.readLong();
    }

    @Override
    public float readFloat() throws IOException {
        return in.readFloat();
    }

    @Override
    public double readDouble() throws IOException {
        return in.readDouble();
    }

    @Override
    public String readLine() throws IOException {
        return in.readLine();
    }

    @NotNull
    @Override
    public String readUTF() throws IOException {
        return in.readUTF();
    }
}
