package com.r00t.remotecontrol.domain;

public enum MouseButton {
    LEFT(0),
    RIGHT(1);

    private final int type;

    MouseButton(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
