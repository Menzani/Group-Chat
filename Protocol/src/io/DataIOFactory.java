package it.menzani.groupchat.protocol.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.nio.charset.Charset;

public interface DataIOFactory {
    Charset charset();

    DataInput input();

    DataOutput output();
}
