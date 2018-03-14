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

class UserLeaveTest {
    private PipedDataIOFactory dataIOFactory;

    @BeforeEach
    void init() {
        dataIOFactory = new PipedDataIOFactory();
    }

    @Test
    @DisplayName("Serialize/deserialize USER_LEAVE packet, self variant (empty username)")
    void self() throws IOException {
        UserLeave userLeave = UserLeave.self();
        String username = userLeave.getUsername();
        userLeave.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                Character.toString(username.getEndOfData())));

        UserLeave _userLeave = new UserLeave();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _userLeave.deserialize(in);

        assertEquals(userLeave.getOpCode().getValue(), opCode);
        assertEquals(userLeave.getUsername(), _userLeave.getUsername());
        assertEquals(userLeave, _userLeave);
    }

    @Test
    @DisplayName("Serialize/deserialize USER_LEAVE packet with non-empty username")
    void nonEmptyUsername() throws IOException {
        String username = StringTest.randomString();
        UserLeave userLeave = new UserLeave(username);
        userLeave.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                username.asJavaString() + username.getEndOfData()));

        UserLeave _userLeave = new UserLeave();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _userLeave.deserialize(in);

        assertEquals(userLeave.getOpCode().getValue(), opCode);
        assertEquals(userLeave.getUsername(), _userLeave.getUsername());
        assertEquals(userLeave, _userLeave);
    }
}