package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;

public final class ResourcesCompat {

    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(Typeface typeface);

        public final void callbackSuccessAsync(final Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public final void callbackFailAsync(final int i, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(i);
                }
            });
        }
    }

    public static Drawable getDrawable(Resources resources, int i, Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return resources.getDrawable(i, theme);
        }
        return resources.getDrawable(i);
    }

    public static Typeface getFont(Context context, int i, TypedValue typedValue, int i2, FontCallback fontCallback) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, typedValue, i2, fontCallback, null, true);
    }

    private static Typeface loadFont(Context context, int i, TypedValue typedValue, int i2, FontCallback fontCallback, Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface loadFont = loadFont(context, resources, typedValue, i, i2, fontCallback, handler, z);
        if (loadFont != null || fontCallback != null) {
            return loadFont;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Font resource ID #0x");
        sb.append(Integer.toHexString(i));
        sb.append(" could not be retrieved.");
        throw new NotFoundException(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface loadFont(android.content.Context r14, android.content.res.Resources r15, android.util.TypedValue r16, int r17, int r18, android.support.v4.content.res.ResourcesCompat.FontCallback r19, android.os.Handler r20, boolean r21) {
        /*
            r3 = r15
            r1 = r16
            r4 = r17
            r5 = r18
            r9 = r19
            r10 = r20
            java.lang.CharSequence r2 = r1.string
            if (r2 != 0) goto L_0x003e
            android.content.res.Resources$NotFoundException r2 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Resource \""
            r5.append(r6)
            java.lang.String r3 = r3.getResourceName(r4)
            r5.append(r3)
            java.lang.String r3 = "\" ("
            r5.append(r3)
            java.lang.String r3 = java.lang.Integer.toHexString(r17)
            r5.append(r3)
            java.lang.String r3 = ") is not a Font: "
            r5.append(r3)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            r2.<init>(r1)
            throw r2
        L_0x003e:
            java.lang.CharSequence r1 = r1.string
            java.lang.String r11 = r1.toString()
            java.lang.String r1 = "res/"
            boolean r1 = r11.startsWith(r1)
            r12 = 0
            r13 = -3
            if (r1 != 0) goto L_0x0054
            if (r9 == 0) goto L_0x0053
            r9.callbackFailAsync(r13, r10)
        L_0x0053:
            return r12
        L_0x0054:
            android.graphics.Typeface r1 = android.support.v4.graphics.TypefaceCompat.findFromCache(r3, r4, r5)
            if (r1 == 0) goto L_0x0060
            if (r9 == 0) goto L_0x005f
            r9.callbackSuccessAsync(r1, r10)
        L_0x005f:
            return r1
        L_0x0060:
            java.lang.String r1 = r11.toLowerCase()     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            java.lang.String r2 = ".xml"
            boolean r1 = r1.endsWith(r2)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            if (r1 == 0) goto L_0x008d
            android.content.res.XmlResourceParser r1 = r3.getXml(r4)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            android.support.v4.content.res.FontResourcesParserCompat$FamilyResourceEntry r2 = android.support.v4.content.res.FontResourcesParserCompat.parse(r1, r3)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            if (r2 != 0) goto L_0x0083
            java.lang.String r1 = "ResourcesCompat"
            java.lang.String r2 = "Failed to find font-family tag"
            android.util.Log.e(r1, r2)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            if (r9 == 0) goto L_0x0082
            r9.callbackFailAsync(r13, r10)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
        L_0x0082:
            return r12
        L_0x0083:
            r1 = r14
            r6 = r9
            r7 = r10
            r8 = r21
            android.graphics.Typeface r1 = android.support.v4.graphics.TypefaceCompat.createFromResourcesFamilyXml(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            return r1
        L_0x008d:
            r1 = r14
            android.graphics.Typeface r1 = android.support.v4.graphics.TypefaceCompat.createFromResourcesFontFile(r1, r3, r4, r11, r5)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            if (r9 == 0) goto L_0x009d
            if (r1 == 0) goto L_0x009a
            r9.callbackSuccessAsync(r1, r10)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
            goto L_0x009d
        L_0x009a:
            r9.callbackFailAsync(r13, r10)     // Catch:{ XmlPullParserException -> 0x00b7, IOException -> 0x009e }
        L_0x009d:
            return r1
        L_0x009e:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "ResourcesCompat"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to read xml resource "
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3, r1)
            goto L_0x00cf
        L_0x00b7:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "ResourcesCompat"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to parse xml resource "
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3, r1)
        L_0x00cf:
            if (r9 == 0) goto L_0x00d4
            r9.callbackFailAsync(r13, r10)
        L_0x00d4:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }
}
