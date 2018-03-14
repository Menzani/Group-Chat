package it.menzani.groupchat.protocol.primitives;

import it.menzani.groupchat.protocol.io.PipedDataIOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTest {
    private PipedDataIOFactory dataIOFactory;

    @BeforeEach
    void init() {
        dataIOFactory = new PipedDataIOFactory();
    }

    @Test
    @DisplayName("Serialize/deserialize empty string")
    void emptyString() throws IOException {
        String string = String.EMPTY;
        string.serialize(dataIOFactory.output());

        assertEquals(
                Character.toString(string.getEndOfData()),
                dataIOFactory.outputToString()
        );

        String _string = new String();
        _string.deserialize(dataIOFactory.input());

        assertEquals(string.asJavaString(), _string.asJavaString());
        assertEquals(string, _string);
    }

    @Test
    @DisplayName("Serialize/deserialize 36-characters string")
    void _36_CharactersString() throws IOException {
        String string = randomString();
        string.serialize(dataIOFactory.output());

        assertEquals(
                string.asJavaString() + string.getEndOfData(),
                dataIOFactory.outputToString()
        );

        String _string = new String();
        _string.deserialize(dataIOFactory.input());

        assertEquals(string.asJavaString(), _string.asJavaString());
        assertEquals(string, _string);
    }

    static String randomString() {
        return new String(UUID.randomUUID().toString());
    }
}