package com.contextlogic.wish.util;

import android.content.Context;
import android.graphics.Typeface;
import com.contextlogic.wish.application.WishApplication;
import java.util.concurrent.ConcurrentHashMap;

public class FontUtil {
    private static ConcurrentHashMap<String, Typeface> sFontCache = new ConcurrentHashMap<>();

    public static Typeface getTypefaceForStyle(int i) {
        Typeface createFont;
        String valueOf = String.valueOf(i);
        WishApplication instance = WishApplication.getInstance();
        Typeface typeface = (Typeface) sFontCache.get(valueOf);
        if (typeface == null) {
            if (i == 1) {
                createFont = createFont(instance, "proximanova-bold.otf");
            } else if (i == 2) {
                createFont = createFont(instance, "proximanova-regularit.otf");
            } else if (i == 3) {
                createFont = createFont(instance, "proximanova-bold.otf");
            } else {
                createFont = createFont(instance, "proximanova-regular.otf");
            }
            typeface = createFont;
            if (typeface != null) {
                sFontCache.put(valueOf, typeface);
            }
        }
        return typeface;
    }

    private static Typeface createFont(Context context, String str) {
        try {
            return Typeface.createFromAsset(context.getAssets(), str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
