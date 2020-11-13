package com.r00t.remotecontrol.services;

public enum ControlActions {
    MOUSE_MOVE("mouse_move_event"),
    MOUSE_KEY("mouse_key_event"),
    KEYBOARD_KEY("keyboard_key_event"),
    KEYBOARD_WRITE("keyboard_write_event");

    String event;

    ControlActions(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
