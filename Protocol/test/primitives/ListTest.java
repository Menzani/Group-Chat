package it.menzani.groupchat.protocol.primitives;

import it.menzani.groupchat.protocol.io.BufferedDataInputStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {
    private static java.util.List<String> javaList;
    private static ByteArrayOutputStream stream;

    @BeforeAll
    static void initAll() {
        javaList = Stream.generate(() -> new String(UUID.randomUUID().toString()))
                .limit(2)
                .collect(Collectors.toList());
        stream = new ByteArrayOutputStream();
    }

    @Test
    void serialize() throws IOException {
        List<String> list = new List<>(javaList);
        list.serialize(new DataOutputStream(stream));
    }

    @Test
    void deserialize() throws IOException {
        List<String> list = new List<>(String.class);
        list.deserialize(new BufferedDataInputStream(new ByteArrayInputStream(stream.toByteArray())));

        assertEquals(javaList, list.asJavaList());
    }
}