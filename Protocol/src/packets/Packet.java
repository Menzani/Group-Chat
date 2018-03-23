package it.menzani.groupchat.protocol.packets;

import it.menzani.groupchat.protocol.io.Serializable;
import it.menzani.groupchat.protocol.primitives.OpCode;

import java.io.DataOutput;
import java.io.IOException;

abstract class Packet implements Serializable {
    private final OpCode opCode;

    Packet(OpCode opCode) {
        this.opCode = opCode;
    }

    OpCode getOpCode() {
        return opCode;
    }

    void writeOpCode(DataOutput out) throws IOException {
        out.writeInt(opCode.getValue());
    }

    @Override
    public String toString() {
        return "Packet{" +
                "opCode=" + opCode +
                '}';
    }
}
