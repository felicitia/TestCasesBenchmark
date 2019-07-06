package com.contextlogic.wish.util;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import com.contextlogic.wish.application.WishApplication;

public class ValueUtil {
    public static float convertDpToPx(float f) {
        if (WishApplication.getInstance() == null) {
            return f;
        }
        return TypedValue.applyDimension(1, f, WishApplication.getInstance().getResources().getDisplayMetrics());
    }

    public static float convertPxToDp(float f) {
        Display defaultDisplay = ((WindowManager) WishApplication.getInstance().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return f / displayMetrics.density;
    }

    public static int convertSpToPx(float f) {
        return (int) TypedValue.applyDimension(2, f, WishApplication.getInstance().getResources().getDisplayMetrics());
    }
}
