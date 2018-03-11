package it.menzani.groupchat.protocol.primitives;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public final class List<T extends Primitive> extends Primitive {
    private Class<T> elementsClass;
    private java.util.List<T> javaList;

    private List() {
        super('ç');
    }

    public List(Class<T> elementsClass) {
        this();
        this.elementsClass = elementsClass;
    }

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
    public void deserialize(DataInput in, InputStream stream) throws IOException {
        javaList = new ArrayList<>();
        while (isNotEndOfData(in, stream)) {
            T element = newElement();
            element.deserialize(in, stream);
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
