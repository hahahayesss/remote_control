package com.r00t.remotecontrol.domain;

public enum KeyboardButton {
    LEFT_ARROW(37),
    RIGHT_ARROW(39),
    UP_ARROW(38),
    DOWN_ARROW(40),

    BACKSPACE('\b'),
    ENTER('\n');

    private final int type;

    KeyboardButton(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
