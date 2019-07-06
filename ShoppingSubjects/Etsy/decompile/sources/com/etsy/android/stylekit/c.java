package com.etsy.android.stylekit;

import android.content.Context;
import android.content.ContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/* compiled from: StyleKitContextWrapper */
public class c {
    public static ContextWrapper a(Context context) {
        return CalligraphyContextWrapper.wrap(context);
    }
}
