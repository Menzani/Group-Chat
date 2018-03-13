package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.Serialize;
import it.menzani.groupchat.protocol.primitives.OpCode;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class UsernameTooLong extends Packet {
    @Serialize
    public UsernameTooLong() {
        super(OpCode.USERNAME_TOO_LONG);
    }

    @Override
    public void serialize(DataOutput out) throws IOException {
        writeOpCode(out);
    }

    @Override
    public void deserialize(DataInput in) {
        // Do nothing
    }
}
