package it.menzani.groupchat.protocol;

import java.io.DataInput;
import java.io.IOException;

public interface BufferedDataInput extends DataInput {
    void mark(int readLimit);
    void reset() throws IOException;
}
