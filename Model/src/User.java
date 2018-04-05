package it.menzani.groupchat.model;

public final class User {
    private final String name;
    private Room room;

    public User(String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("name must not be empty.");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public void joinRoom(Room room) {
        if (this.room != null) throw new IllegalStateException("User already in room.");
        this.room = room;
        room.addUser(this);
    }

    public void leaveRoom() {
        if (room == null) throw new IllegalStateException("User not in room.");
        room.removeUser(this);
        room = null;
    }
}
