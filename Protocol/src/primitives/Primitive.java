package it.menzani.groupchat.protocol.primitives;

import it.menzani.groupchat.protocol.Serializable;

import java.io.*;

abstract class Primitive implements Serializable {
    private final char endOfData;

    Primitive(char endOfData) {
        this.endOfData = endOfData;
    }

    boolean isNotEndOfData(DataInput in, InputStream stream) throws IOException {
        stream.mark(2);
        boolean result = in.readChar() != endOfData;
        if (result) {
            stream.reset();
        }
        return result;
    }

    void writeEndOfData(DataOutput out) throws IOException {
        out.writeChar(endOfData);
    }
}
