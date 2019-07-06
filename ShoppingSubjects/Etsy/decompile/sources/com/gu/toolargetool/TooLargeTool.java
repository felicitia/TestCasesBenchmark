package com.gu.toolargetool;

import android.app.Application;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public final class TooLargeTool {
    private static float KB(int i) {
        return ((float) i) / 1000.0f;
    }

    private TooLargeTool() {
    }

    public static void logBundleBreakdown(String str, @NonNull Bundle bundle) {
        Log.println(3, str, bundleBreakdown(bundle));
    }

    public static void logBundleBreakdown(String str, int i, @NonNull Bundle bundle) {
        Log.println(i, str, bundleBreakdown(bundle));
    }

    @NonNull
    public static String bundleBreakdown(@NonNull Bundle bundle) {
        String format = String.format(Locale.UK, "Bundle@%d contains %d keys and measures %,.1f KB when serialized as a Parcel", new Object[]{Integer.valueOf(System.identityHashCode(bundle)), Integer.valueOf(bundle.size()), Float.valueOf(KB(sizeAsParcel(bundle)))});
        for (Entry entry : valueSizes(bundle).entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(format);
            sb.append(String.format(Locale.UK, "\n* %s = %,.1f KB", new Object[]{entry.getKey(), Float.valueOf(KB(((Integer) entry.getValue()).intValue()))}));
            format = sb.toString();
        }
        return format;
    }

    public static Map<String, Integer> valueSizes(@NonNull Bundle bundle) {
        HashMap hashMap = new HashMap(bundle.size());
        Bundle bundle2 = new Bundle(bundle);
        try {
            int sizeAsParcel = sizeAsParcel(bundle);
            for (String str : bundle2.keySet()) {
                bundle.remove(str);
                int sizeAsParcel2 = sizeAsParcel(bundle);
                hashMap.put(str, Integer.valueOf(sizeAsParcel - sizeAsParcel2));
                sizeAsParcel = sizeAsParcel2;
            }
            return hashMap;
        } finally {
            bundle.putAll(bundle2);
        }
    }

    public static int sizeAsParcel(@NonNull Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeBundle(bundle);
            return obtain.dataSize();
        } finally {
            obtain.recycle();
        }
    }

    public static int sizeAsParcel(@NonNull Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeParcelable(parcelable, 0);
            return obtain.dataSize();
        } finally {
            obtain.recycle();
        }
    }

    public static void startLogging(Application application) {
        startLogging(application, 3, "TooLargeTool");
    }

    public static void startLogging(Application application, int i, @NonNull String str) {
        startLogging(application, b.c, new c(i, str));
    }

    public static void startLogging(Application application, b bVar, c cVar) {
        application.registerActivityLifecycleCallbacks(new ActivitySavedStateLogger(bVar, cVar, true));
    }
}
