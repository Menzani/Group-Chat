package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.Deserialize;
import it.menzani.groupchat.protocol.io.Serialize;
import it.menzani.groupchat.protocol.primitives.OpCode;
import it.menzani.groupchat.protocol.primitives.String;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class UserJoin extends Packet {
    private String username;

    @Deserialize
    public UserJoin() {
        super(OpCode.USER_JOIN);
    }

    @Serialize
    public UserJoin(@NotNull String username) {
        this();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void serialize(DataOutput out) throws IOException {
        writeOpCode(out);
        username.serialize(out);
    }

    @Override
    public void deserialize(DataInput in) throws IOException {
        username = new String();
        username.deserialize(in);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserJoin that = (UserJoin) obj;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public java.lang.String toString() {
        return "UserJoin{" +
                "username=" + username +
                '}';
    }
}
