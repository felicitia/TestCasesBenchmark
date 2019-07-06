package com.etsy.android.uikit.c;

import android.content.Context;

/* compiled from: SystemUiHelper */
public final class a {
    private static final String a = "a";

    public static int a(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 0 : -1;
    }
}
