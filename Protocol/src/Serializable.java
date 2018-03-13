package it.menzani.groupchat.protocol;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface Serializable {
    void serialize(DataOutput out) throws IOException;

    void deserialize(DataInput in) throws IOException;
}
