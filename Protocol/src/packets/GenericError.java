package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.Deserialize;
import it.menzani.groupchat.protocol.io.Serialize;
import it.menzani.groupchat.protocol.primitives.OpCode;
import it.menzani.groupchat.protocol.primitives.String;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class GenericError extends Packet {
    private String description;

    @Deserialize
    public GenericError() {
        super(OpCode.GENERIC_ERROR);
    }

    @Serialize
    public GenericError(@NotNull String description) {
        this();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void serialize(DataOutput out) throws IOException {
        writeOpCode(out);
        description.serialize(out);
    }

    @Override
    public void deserialize(DataInput in) throws IOException {
        description = new String();
        description.deserialize(in);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GenericError that = (GenericError) obj;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public java.lang.String toString() {
        return "GenericError{" +
                "description=" + description +
                '}';
    }
}
