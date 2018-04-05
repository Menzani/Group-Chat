package it.menzani.groupchat.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Room {
    private final List<User> users = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void broadcastMessage(User sender, String message) {

    }

    boolean addUser(User user) {
        if (users.contains(user)) return false;
        users.add(user);
        return true;
    }

    void removeUser(User user) {
        boolean contained = users.remove(user);
        assert contained : users;
    }
}
