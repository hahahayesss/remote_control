package com.r00t.remotecontrol.domain.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Commons {

    public static String timestampToDateAndTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(new Date(timestamp));
    }
}
