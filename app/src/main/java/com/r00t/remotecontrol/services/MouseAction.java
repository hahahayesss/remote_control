package com.r00t.remotecontrol.services;

import android.content.Context;
import android.content.Intent;

import com.r00t.remotecontrol.models.LocationVector;

public class MouseAction {
    private static String ACTION = "MOUSE_ACTION";

    public static Intent createMoveActionIntent(Context context, LocationVector locationVector) {
        Intent i = new Intent(context, ControlListener.class);
        i.setAction(ACTION);
        i.putExtra(ActionsEnum.VEC_X.value, locationVector.getX());
        i.putExtra(ActionsEnum.VEC_Y.value, locationVector.getY());
        return i;
    }

//    public static LocationVector getMoveActionIntent()

    public static Intent createClickActionIntent(Context context, ClickActionButton clickActionButton) {
        Intent i = new Intent(context, ControlListener.class);
        i.setAction(ACTION);
        i.putExtra(ActionsEnum.BUTTON.value, clickActionButton.value);
        return i;
    }

    public enum ClickActionButton {
        LEFT_BUTTON(0),
        RIGHT_BUTTON(1);

        int value;

        ClickActionButton(int value) {
            this.value = value;
        }
    }

    private enum ActionsEnum {
        VEC_X("mouseMoveActionVectorX"),
        VEC_Y("mouseMoveActionVectorY"),
        BUTTON("mouseClickActionButton");

        String value;

        ActionsEnum(String value) {
            this.value = value;
        }
    }
}
