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

class MessageTest {
    private PipedDataIOFactory dataIOFactory;

    @BeforeEach
    void init() {
        dataIOFactory = new PipedDataIOFactory();
    }

    @Test
    @DisplayName("Serialize/deserialize MESSAGE packet with empty text and username")
    void emptyTextAndUsername() throws IOException {
        String text = String.EMPTY;
        String username = String.EMPTY;
        Message message = new Message(text, username);
        message.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                Character.toString(text.getEndOfData()) + username.getEndOfData()));

        Message _message = new Message();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _message.deserialize(in);

        assertEquals(message.getOpCode().getValue(), opCode);
        assertEquals(message.getText(), _message.getText());
        assertEquals(message.getUsername(), _message.getUsername());
        assertEquals(message, _message);
    }

    @Test
    @DisplayName("Serialize/deserialize MESSAGE packet with empty text and non-empty username")
    void emptyTextAndNonEmptyUsername() throws IOException {
        String text = String.EMPTY;
        String username = StringTest.randomString();
        Message message = new Message(text, username);
        message.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                text.getEndOfData() + username.asJavaString() + username.getEndOfData()));

        Message _message = new Message();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _message.deserialize(in);

        assertEquals(message.getOpCode().getValue(), opCode);
        assertEquals(message.getText(), _message.getText());
        assertEquals(message.getUsername(), _message.getUsername());
        assertEquals(message, _message);
    }

    @Test
    @DisplayName("Serialize/deserialize MESSAGE packet with non-empty text and empty username")
    void nonEmptyTextAndEmptyUsername() throws IOException {
        String text = StringTest.randomString();
        String username = String.EMPTY;
        Message message = new Message(text, username);
        message.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                text.asJavaString() + text.getEndOfData() + username.getEndOfData()));

        Message _message = new Message();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _message.deserialize(in);

        assertEquals(message.getOpCode().getValue(), opCode);
        assertEquals(message.getText(), _message.getText());
        assertEquals(message.getUsername(), _message.getUsername());
        assertEquals(message, _message);
    }

    @Test
    @DisplayName("Serialize/deserialize MESSAGE packet with non-empty text and username")
    void nonEmptyTextAndUsername() throws IOException {
        String text = StringTest.randomString();
        String username = StringTest.randomString();
        Message message = new Message(text, username);
        message.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                text.asJavaString() + text.getEndOfData() + username.asJavaString() + username.getEndOfData()));

        Message _message = new Message();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _message.deserialize(in);

        assertEquals(message.getOpCode().getValue(), opCode);
        assertEquals(message.getText(), _message.getText());
        assertEquals(message.getUsername(), _message.getUsername());
        assertEquals(message, _message);
    }
}