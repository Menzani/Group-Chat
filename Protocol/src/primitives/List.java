package it.menzani.groupchat.protocol.primitives;

import it.menzani.groupchat.protocol.io.BufferedDataInput;
import it.menzani.groupchat.protocol.io.Deserialize;
import it.menzani.groupchat.protocol.io.Serialize;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public final class List<T extends Primitive> extends Primitive {
    public static final List<?> EMPTY = new List<>(Collections.emptyList());

    private Class<T> elementsClass;
    private java.util.List<T> javaList;

    private List() {
        super('ç');
    }

    @Deserialize
    public List(Class<T> elementsClass) {
        this();
        this.elementsClass = elementsClass;
    }

    @Serialize
    public List(java.util.List<T> javaList) {
        this();
        this.javaList = javaList;
    }

    public java.util.List<T> asJavaList() {
        return javaList;
    }

    @Override
    public void serialize(DataOutput out) throws IOException {
        for (T element : javaList) {
            element.serialize(out);
        }
        writeEndOfData(out);
    }

    @Override
    public void deserialize(DataInput in) throws IOException {
        javaList = new ArrayList<>();
        while (isNotEndOfData((BufferedDataInput) in)) {
            T element = newElement();
            element.deserialize(in);
            javaList.add(element);
        }
    }

    private T newElement() throws IOException {
        try {
            return elementsClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        List<?> that = (List<?>) obj;
        return javaList.equals(that.javaList);
    }

    @Override
    public int hashCode() {
        return javaList.hashCode();
    }

    @Override
    public java.lang.String toString() {
        return javaList.toString();
    }
}
