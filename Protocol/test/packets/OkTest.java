package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.PipedDataIOFactory;
import it.menzani.groupchat.protocol.primitives.List;
import it.menzani.groupchat.protocol.primitives.String;
import it.menzani.groupchat.protocol.primitives.StringTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OkTest {
    private PipedDataIOFactory dataIOFactory;

    @BeforeEach
    void init() {
        dataIOFactory = new PipedDataIOFactory();
    }

    @Test
    @DisplayName("Serialize/deserialize OK packet with no online users")
    void noOnlineUsers() throws IOException {
        List<String> onlineUsers = List.empty();
        Ok ok = new Ok(onlineUsers);
        ok.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                Character.toString(onlineUsers.getEndOfData())));

        Ok _ok = new Ok();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _ok.deserialize(in);

        assertEquals(ok.getOpCode().getValue(), opCode);
        assertEquals(ok.getOnlineUsers(), _ok.getOnlineUsers());
        assertEquals(ok, _ok);
    }

    @Test
    @DisplayName("Serialize/deserialize OK packet with some online users")
    void someOnlineUsers() throws IOException {
        String user0 = StringTest.randomString();
        String user1 = StringTest.randomString();
        List<String> onlineUsers = new List<>(Arrays.asList(user0, user1));
        Ok ok = new Ok(onlineUsers);
        ok.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                user0.asJavaString() + user0.getEndOfData() + user1.asJavaString() + user1.getEndOfData() + onlineUsers.getEndOfData()));

        Ok _ok = new Ok();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _ok.deserialize(in);

        assertEquals(ok.getOpCode().getValue(), opCode);
        assertEquals(ok.getOnlineUsers(), _ok.getOnlineUsers());
        assertEquals(ok, _ok);
    }
}