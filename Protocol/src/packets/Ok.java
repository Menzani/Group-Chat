package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.Deserialize;
import it.menzani.groupchat.protocol.io.Serialize;
import it.menzani.groupchat.protocol.primitives.List;
import it.menzani.groupchat.protocol.primitives.OpCode;
import it.menzani.groupchat.protocol.primitives.String;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class Ok extends Packet {
    private List<String> onlineUsers;

    @Deserialize
    public Ok() {
        super(OpCode.OK);
    }

    @Serialize
    public Ok(@NotNull List<String> onlineUsers) {
        this();
        this.onlineUsers = onlineUsers;
    }

    public List<String> getOnlineUsers() {
        return onlineUsers;
    }

    @Override
    public void serialize(DataOutput out) throws IOException {
        writeOpCode(out);
        onlineUsers.serialize(out);
    }

    @Override
    public void deserialize(DataInput in) throws IOException {
        onlineUsers = new List<>(String.class);
        onlineUsers.deserialize(in);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Ok that = (Ok) obj;
        return onlineUsers.equals(that.onlineUsers);
    }

    @Override
    public int hashCode() {
        return onlineUsers.hashCode();
    }

    @Override
    public java.lang.String toString() {
        return "Ok{" +
                "onlineUsers=" + onlineUsers +
                '}';
    }
}
