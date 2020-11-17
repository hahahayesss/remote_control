package com.r00t.remotecontrol.domain;

public enum LogEvent {
    CONNECT("connected"),
    MOUSE("mouse_event"),
    KEYBOARD("keyboard_event");

    private final String event;

    LogEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
