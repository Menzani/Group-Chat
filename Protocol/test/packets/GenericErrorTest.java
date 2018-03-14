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

class GenericErrorTest {
    private PipedDataIOFactory dataIOFactory;

    @BeforeEach
    void init() {
        dataIOFactory = new PipedDataIOFactory();
    }

    @Test
    @DisplayName("Serialize/deserialize GENERIC_ERROR packet with empty description")
    void emptyDescription() throws IOException {
        String description = String.EMPTY;
        GenericError genericError = new GenericError(description);
        genericError.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                Character.toString(description.getEndOfData())));

        GenericError _genericError = new GenericError();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _genericError.deserialize(in);

        assertEquals(genericError.getOpCode().getValue(), opCode);
        assertEquals(genericError.getDescription(), _genericError.getDescription());
        assertEquals(genericError, _genericError);
    }

    @Test
    @DisplayName("Serialize/deserialize GENERIC_ERROR packet with non-empty description")
    void nonEmptyDescription() throws IOException {
        String description = StringTest.randomString();
        GenericError genericError = new GenericError(description);
        genericError.serialize(dataIOFactory.output());

        assertTrue(dataIOFactory.outputToString().endsWith(
                description.asJavaString() + description.getEndOfData()));

        GenericError _genericError = new GenericError();
        DataInput in = dataIOFactory.input();
        int opCode = in.readInt();
        _genericError.deserialize(in);

        assertEquals(genericError.getOpCode().getValue(), opCode);
        assertEquals(genericError.getDescription(), _genericError.getDescription());
        assertEquals(genericError, _genericError);
    }
}