package com.contextlogic.wish.util;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.contextlogic.wish.application.WishApplication;

public class TabletUtil {
    private static Boolean sTabletCheckResult;

    private static float getMinimumScreenDimension() {
        if (WishApplication.getInstance() == null || WishApplication.getInstance().getSystemService("window") == null) {
            return 0.0f;
        }
        Display defaultDisplay = ((WindowManager) WishApplication.getInstance().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return Math.min(((float) displayMetrics.widthPixels) / displayMetrics.density, ((float) displayMetrics.heightPixels) / displayMetrics.density);
    }

    public static boolean isTablet() {
        if (sTabletCheckResult == null) {
            sTabletCheckResult = Boolean.valueOf(getMinimumScreenDimension() >= 525.0f);
        }
        return sTabletCheckResult.booleanValue();
    }
}
