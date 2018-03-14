package it.menzani.groupchat.protocol.primitives;

import it.menzani.groupchat.protocol.io.PipedDataIOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {
    private PipedDataIOFactory dataIOFactory;

    @BeforeEach
    void init() {
        dataIOFactory = new PipedDataIOFactory();
    }

    @Test
    @DisplayName("Serialize/deserialize empty list")
    void emptyList() throws IOException {
        List<?> list = List.empty();
        list.serialize(dataIOFactory.output());

        assertEquals(
                Character.toString(list.getEndOfData()),
                dataIOFactory.outputToString()
        );

        List<?> _list = new List<>(Primitive.class);
        _list.deserialize(dataIOFactory.input());

        assertEquals(list.asJavaList(), _list.asJavaList());
        assertEquals(list, _list);
    }

    @Test
    @DisplayName("Serialize/deserialize singleton list of string")
    void singletonStringList() throws IOException {
        String string = StringTest.randomString();
        List<String> list = new List<>(Collections.singletonList(string));
        list.serialize(dataIOFactory.output());

        assertEquals(
                string.asJavaString() + string.getEndOfData() + list.getEndOfData(),
                dataIOFactory.outputToString()
        );

        List<String> _list = new List<>(String.class);
        _list.deserialize(dataIOFactory.input());

        assertEquals(list.asJavaList(), _list.asJavaList());
        assertEquals(list, _list);
    }

    @Test
    @DisplayName("Serialize/deserialize two-elements list of string")
    void twoElementsStringList() throws IOException {
        String string0 = StringTest.randomString();
        String string1 = StringTest.randomString();
        List<String> list = new List<>(Arrays.asList(string0, string1));
        list.serialize(dataIOFactory.output());

        assertEquals(
                string0.asJavaString() + string0.getEndOfData() + string1.asJavaString() + string1.getEndOfData() + list.getEndOfData(),
                dataIOFactory.outputToString()
        );

        List<String> _list = new List<>(String.class);
        _list.deserialize(dataIOFactory.input());

        assertEquals(list.asJavaList(), _list.asJavaList());
        assertEquals(list, _list);
    }
}