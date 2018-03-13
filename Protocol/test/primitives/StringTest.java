package it.menzani.groupchat.protocol.primitives;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTest {
    private static java.lang.String javaString;
    private static ByteArrayOutputStream stream;

    @BeforeAll
    static void initAll() {
        javaString = UUID.randomUUID().toString();
        stream = new ByteArrayOutputStream();
    }

    @Test
    void serialize() throws IOException {
        String string = new String(javaString);
        string.serializeStream(stream);
    }

    @Test
    void deserialize() throws IOException {
        String string = new String();
        string.deserializeStream(new ByteArrayInputStream(stream.toByteArray()));

        assertEquals(javaString, string.asJavaString());
    }
}