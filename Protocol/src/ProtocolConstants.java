package it.menzani.groupchat.protocol;

public final class ProtocolConstants {
    private ProtocolConstants() {}

    private static final Version LATEST_VERSION = Version.V_1;
    public static final short TCP_PORT = 12556;

    public static int getLatestVersion() {
        return LATEST_VERSION.getCode();
    }
}
