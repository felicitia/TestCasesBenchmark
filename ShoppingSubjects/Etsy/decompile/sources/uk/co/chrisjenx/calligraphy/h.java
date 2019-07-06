package uk.co.chrisjenx.calligraphy;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* compiled from: TypefaceUtils */
public final class h {
    private static final Map<String, Typeface> a = new HashMap();
    private static final Map<Typeface, CalligraphyTypefaceSpan> b = new HashMap();

    public static Typeface a(AssetManager assetManager, String str) {
        synchronized (a) {
            try {
                if (!a.containsKey(str)) {
                    Typeface createFromAsset = Typeface.createFromAsset(assetManager, str);
                    a.put(str, createFromAsset);
                    return createFromAsset;
                }
                Typeface typeface = (Typeface) a.get(str);
                return typeface;
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Can't create asset from ");
                sb.append(str);
                sb.append(". Make sure you have passed in the correct path and file name.");
                Log.w("Calligraphy", sb.toString(), e);
                a.put(str, null);
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static CalligraphyTypefaceSpan a(Typeface typeface) {
        if (typeface == null) {
            return null;
        }
        synchronized (b) {
            if (!b.containsKey(typeface)) {
                CalligraphyTypefaceSpan calligraphyTypefaceSpan = new CalligraphyTypefaceSpan(typeface);
                b.put(typeface, calligraphyTypefaceSpan);
                return calligraphyTypefaceSpan;
            }
            CalligraphyTypefaceSpan calligraphyTypefaceSpan2 = (CalligraphyTypefaceSpan) b.get(typeface);
            return calligraphyTypefaceSpan2;
        }
    }

    public static boolean b(Typeface typeface) {
        return typeface != null && a.containsValue(typeface);
    }
}
