package it.menzani.groupchat.protocol.primitives;

import it.menzani.groupchat.protocol.io.BufferedDataInput;
import it.menzani.groupchat.protocol.io.Serializable;

import java.io.DataOutput;
import java.io.IOException;

abstract class Primitive implements Serializable {
    private final char endOfData;

    Primitive(char endOfData) {
        this.endOfData = endOfData;
    }

    boolean isNotEndOfData(BufferedDataInput in) throws IOException {
        in.mark(2);
        boolean result = in.readChar() != endOfData;
        if (result) {
            in.reset();
        }
        return result;
    }

    void writeEndOfData(DataOutput out) throws IOException {
        out.writeChar(endOfData);
    }
}
