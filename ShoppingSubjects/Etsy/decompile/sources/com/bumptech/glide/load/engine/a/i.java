package com.bumptech.glide.load.engine.a;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

/* compiled from: MemorySizeCalculator */
public class i {
    private final int a;
    private final int b;
    private final Context c;

    /* compiled from: MemorySizeCalculator */
    private static class a implements b {
        private final DisplayMetrics a;

        public a(DisplayMetrics displayMetrics) {
            this.a = displayMetrics;
        }

        public int a() {
            return this.a.widthPixels;
        }

        public int b() {
            return this.a.heightPixels;
        }
    }

    /* compiled from: MemorySizeCalculator */
    interface b {
        int a();

        int b();
    }

    public i(Context context) {
        this(context, (ActivityManager) context.getSystemService("activity"), new a(context.getResources().getDisplayMetrics()));
    }

    i(Context context, ActivityManager activityManager, b bVar) {
        this.c = context;
        int a2 = a(activityManager);
        int a3 = bVar.a() * bVar.b() * 4;
        int i = a3 * 4;
        int i2 = a3 * 2;
        int i3 = i2 + i;
        if (i3 <= a2) {
            this.b = i2;
            this.a = i;
        } else {
            int round = Math.round(((float) a2) / 6.0f);
            this.b = round * 2;
            this.a = round * 4;
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            String str = "MemorySizeCalculator";
            StringBuilder sb = new StringBuilder();
            sb.append("Calculated memory cache size: ");
            sb.append(a(this.b));
            sb.append(" pool size: ");
            sb.append(a(this.a));
            sb.append(" memory class limited? ");
            sb.append(i3 > a2);
            sb.append(" max size: ");
            sb.append(a(a2));
            sb.append(" memoryClass: ");
            sb.append(activityManager.getMemoryClass());
            sb.append(" isLowMemoryDevice: ");
            sb.append(b(activityManager));
            Log.d(str, sb.toString());
        }
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    private static int a(ActivityManager activityManager) {
        return Math.round(((float) (activityManager.getMemoryClass() * 1024 * 1024)) * (b(activityManager) ? 0.33f : 0.4f));
    }

    private String a(int i) {
        return Formatter.formatFileSize(this.c, (long) i);
    }

    @TargetApi(19)
    private static boolean b(ActivityManager activityManager) {
        if (VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return VERSION.SDK_INT < 11;
    }
}
