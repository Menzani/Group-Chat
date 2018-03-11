package it.menzani.groupchat.protocol;

enum Version {
    V_1(1);

    private final int code;

    Version(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
