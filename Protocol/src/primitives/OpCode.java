package it.menzani.groupchat.protocol.primitives;

public enum OpCode {
    OK(0),
    INVALID_CHARS(1),
    GENERIC_ERROR(2),

    VERSION_NOT_EXISTS(3),

    USERNAME_TAKEN(4),
    USERNAME_TOO_SHORT(5),
    USERNAME_TOO_LONG(6),

    MESSAGE(7),
    MESSAGE_TOO_LONG(8),

    USER_JOIN(9),
    USER_LEAVE(10);

    private final int value;

    OpCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
