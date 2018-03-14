package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.PipedDataIOFactory;
import it.menzani.groupchat.protocol.primitives.String;
import it.menzani.groupchat.protocol.primitives.StringTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.DataInput;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserJoinTest {
    private PipedDataIOFactory dataIOFactory;

    @BeforeEach
    void init() {
        dataIOFactory = new PipedDataIOFactory();
    }

    @Test
    @DisplayName("Serialize/deserialize USER_JOIN packet with empty username")
    void emptyUsername() throws IOException {
        String username = String.EMPTY;
        UserJoin userJoin = new UserJoin(username);
        userJoin.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                Character.toString(username.getEndOfData())));

        UserJoin _userJoin = new UserJoin();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _userJoin.deserialize(in);

        assertEquals(userJoin.getOpCode().getValue(), opCode);
        assertEquals(userJoin.getUsername(), _userJoin.getUsername());
        assertEquals(userJoin, _userJoin);
    }

    @Test
    @DisplayName("Serialize/deserialize USER_JOIN packet with non-empty username")
    void nonEmptyUsername() throws IOException {
        String username = StringTest.randomString();
        UserJoin userJoin = new UserJoin(username);
        userJoin.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                username.asJavaString() + username.getEndOfData()));

        UserJoin _userJoin = new UserJoin();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _userJoin.deserialize(in);

        assertEquals(userJoin.getOpCode().getValue(), opCode);
        assertEquals(userJoin.getUsername(), _userJoin.getUsername());
        assertEquals(userJoin, _userJoin);
    }
}