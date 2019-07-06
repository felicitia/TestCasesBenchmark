package com.contextlogic.wish.util;

import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import com.contextlogic.wish.application.WishApplication;

public class DisplayUtil {
    public static int getDisplayWidth() {
        return getDisplaySize().x;
    }

    public static int getDisplayHeight() {
        return getDisplaySize().y;
    }

    private static Point getDisplaySize() {
        return getDisplaySize(((WindowManager) WishApplication.getInstance().getSystemService("window")).getDefaultDisplay());
    }

    private static Point getDisplaySize(Display display) {
        Point point = new Point();
        getDisplaySize(display, point);
        return point;
    }

    private static void getDisplaySize(Display display, Point point) {
        try {
            Display.class.getMethod("getSize", new Class[]{Class.forName("android.graphics.Point")}).invoke(display, new Object[]{point});
        } catch (Throwable unused) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
    }

    public static boolean isLandscape() {
        return WishApplication.getInstance().getResources().getConfiguration().orientation == 2;
    }

    public static int getStatusBarHeight() {
        int identifier = WishApplication.getInstance().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return WishApplication.getInstance().getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getActionBarHeight(Context context) {
        if (context != null) {
            TypedValue typedValue = new TypedValue();
            if (context.getTheme().resolveAttribute(16843499, typedValue, true)) {
                return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            }
        }
        return 0;
    }
}
