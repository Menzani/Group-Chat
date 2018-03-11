package it.menzani.groupchat.protocol;

import java.io.*;

public interface Serializable {
    void serialize(DataOutput out) throws IOException;

    void deserialize(DataInput in, InputStream stream) throws IOException;

    default void serializeStream(OutputStream stream) throws IOException {
        serialize(new DataOutputStream(stream));
    }

    default void deserializeStream(InputStream stream) throws IOException {
        InputStream bufferedStream = new BufferedInputStream(stream);
        deserialize(new DataInputStream(bufferedStream), bufferedStream);
    }
}
