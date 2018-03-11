package it.menzani.groupchat.protocol.primitives;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;

public final class String extends Primitive {
    private java.lang.String javaString;

    public String() {
        super('§');
    }

    public String(java.lang.String javaString) {
        this();
        this.javaString = javaString;
    }

    public java.lang.String asJavaString() {
        return javaString;
    }

    @Override
    public void serialize(DataOutput out) throws IOException {
        out.writeChars(javaString);
        writeEndOfData(out);
    }

    @Override
    public void deserialize(DataInput in, InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        while (isNotEndOfData(in, stream)) {
            builder.append(in.readChar());
        }
        javaString = builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        String that = (String) obj;
        return javaString.equals(that.javaString);
    }

    @Override
    public int hashCode() {
        return javaString.hashCode();
    }

    @Override
    public java.lang.String toString() {
        return javaString;
    }
}
