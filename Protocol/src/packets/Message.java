package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.Deserialize;
import it.menzani.groupchat.protocol.io.Serialize;
import it.menzani.groupchat.protocol.primitives.OpCode;
import it.menzani.groupchat.protocol.primitives.String;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class Message extends Packet {
    private String text;
    private String username;

    @Deserialize
    public Message() {
        super(OpCode.MESSAGE);
    }

    @Serialize
    public Message(@NotNull String text) {
        this(text, String.EMPTY);
    }

    @Serialize
    public Message(@NotNull String text, @NotNull String username) {
        this();
        this.text = text;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void serialize(DataOutput out) throws IOException {
        writeOpCode(out);
        text.serialize(out);
        username.serialize(out);
    }

    @Override
    public void deserialize(DataInput in) throws IOException {
        text = new String();
        text.deserialize(in);
        username = new String();
        username.deserialize(in);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Message that = (Message) obj;
        return text.equals(that.text) && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public java.lang.String toString() {
        return "Message{" +
                "text=" + text +
                ", username=" + username +
                '}';
    }
}
