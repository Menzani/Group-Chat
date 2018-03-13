package it.menzani.groupchat.protocol.io;

import java.io.DataInput;
import java.io.IOException;

public interface BufferedDataInput extends DataInput {
    void mark(int readLimit);
    void reset() throws IOException;
}
