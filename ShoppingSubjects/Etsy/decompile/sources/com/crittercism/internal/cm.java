package com.crittercism.internal;

import android.util.Log;
import com.crittercism.app.Crittercism.LoggingLevel;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.net.URL;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class cm {
    public static int a = a.a;
    private static b b = b.Info;
    private static cm c = new cm();

    /* renamed from: com.crittercism.internal.cm$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|5|6|7|9|10|(2:11|12)|13|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|18|19|20|22) */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0052 */
        static {
            /*
                com.crittercism.app.Crittercism$LoggingLevel[] r0 = com.crittercism.app.Crittercism.LoggingLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r0 = 1
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.crittercism.app.Crittercism$LoggingLevel r2 = com.crittercism.app.Crittercism.LoggingLevel.Silent     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001f }
                com.crittercism.app.Crittercism$LoggingLevel r3 = com.crittercism.app.Crittercism.LoggingLevel.Error     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x002a }
                com.crittercism.app.Crittercism$LoggingLevel r4 = com.crittercism.app.Crittercism.LoggingLevel.Warning     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.crittercism.app.Crittercism$LoggingLevel r4 = com.crittercism.app.Crittercism.LoggingLevel.Info     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                com.crittercism.internal.cm$b[] r3 = com.crittercism.internal.cm.b.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                a = r3
                int[] r3 = a     // Catch:{ NoSuchFieldError -> 0x0048 }
                com.crittercism.internal.cm$b r4 = com.crittercism.internal.cm.b.Silent     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0052 }
                com.crittercism.internal.cm$b r3 = com.crittercism.internal.cm.b.Error     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x005c }
                com.crittercism.internal.cm$b r1 = com.crittercism.internal.cm.b.Warning     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.cm.AnonymousClass1.<clinit>():void");
        }
    }

    public enum a {
        ;

        static {
            d = new int[]{a, b, c};
        }
    }

    public enum b {
        Silent(0),
        Error(100),
        Warning(200),
        Info(300),
        Debug(400),
        Verbose(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
        
        private int g;

        private b(int i) {
            this.g = i;
        }

        public final boolean a(b bVar) {
            return this.g >= bVar.g;
        }

        public static b a(LoggingLevel loggingLevel) {
            switch (loggingLevel) {
                case Silent:
                    return Silent;
                case Error:
                    return Error;
                case Warning:
                    return Warning;
                case Info:
                    return Info;
                default:
                    return Warning;
            }
        }
    }

    public static void a(LoggingLevel loggingLevel) {
        b = b.a(loggingLevel);
    }

    public static void a(String str) {
        if (b.a(b.Error)) {
            Log.e("Crittercism", str);
        }
    }

    public static void a(String str, Throwable th) {
        if (b.a(b.Error)) {
            Log.e("Crittercism", str, th);
        }
    }

    public static void b(String str) {
        if (b.a(b.Warning)) {
            Log.w("Crittercism", str);
        }
    }

    public static void b(String str, Throwable th) {
        if (b.a(b.Warning)) {
            Log.w("Crittercism", str, th);
        }
    }

    public static void c(String str) {
        if (b.a(b.Info)) {
            Log.i("Crittercism", str);
        }
    }

    public static void d(String str) {
        if (b.a(b.Debug)) {
            Log.d("Crittercism", str);
        }
    }

    public static void c(String str, Throwable th) {
        if (b.a(b.Debug)) {
            Log.d("Crittercism", str, th);
        }
    }

    public static void a(Throwable th) {
        if (b.a(b.Debug)) {
            Log.d("Crittercism", th.getMessage(), th);
        }
    }

    public static void a(bz bzVar) {
        if (b.a(b.Debug)) {
            d(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            d("----- BEGIN HTTP REQUEST ----- ");
            if (bzVar == null) {
                d("null");
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(bzVar.b);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(bzVar.a.toExternalForm());
            sb.append(" HTTP/1.1");
            d(sb.toString());
            for (Entry entry : bzVar.c.entrySet()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append((String) entry.getKey());
                sb2.append(": ");
                sb2.append((String) entry.getValue());
                d(sb2.toString());
            }
            d("");
            byte[] a2 = bzVar.a();
            if (a2 != null) {
                String str = new String(a2);
                try {
                    Object nextValue = new JSONTokener(str).nextValue();
                    if (nextValue instanceof JSONObject) {
                        d(((JSONObject) nextValue).toString(4));
                    } else if (nextValue instanceof JSONArray) {
                        d(((JSONArray) nextValue).toString(4));
                    } else {
                        d(str);
                    }
                } catch (JSONException unused) {
                    d(str);
                }
            }
            d("-----  END HTTP REQUEST  ----- ");
            d(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
    }

    public static void a(URL url, cb cbVar) {
        if (b.a(b.Debug)) {
            d(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            d("----- BEGIN HTTP RESPONSE ----- ");
            StringBuilder sb = new StringBuilder();
            sb.append(cbVar.a);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(url.toExternalForm());
            d(sb.toString());
            d(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (cbVar.b != null) {
                String str = new String(cbVar.b);
                try {
                    Object nextValue = new JSONTokener(str).nextValue();
                    if (nextValue instanceof JSONObject) {
                        JSONObject jSONObject = (JSONObject) nextValue;
                        StringBuilder sb2 = new StringBuilder("Response: ");
                        sb2.append(jSONObject.toString(4));
                        d(sb2.toString());
                    } else if (nextValue instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) nextValue;
                        StringBuilder sb3 = new StringBuilder("Response: ");
                        sb3.append(jSONArray.toString(4));
                        d(sb3.toString());
                    } else {
                        StringBuilder sb4 = new StringBuilder("Response: ");
                        sb4.append(str);
                        d(sb4.toString());
                    }
                } catch (JSONException unused) {
                    StringBuilder sb5 = new StringBuilder("Response: ");
                    sb5.append(str);
                    d(sb5.toString());
                }
            } else if (cbVar.c != null) {
                a((Throwable) cbVar.c);
            } else {
                d("Response: null");
            }
            d("-----  END HTTP RESPONSE  ----- ");
            d(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
    }

    public static void b(Throwable th) {
        try {
            a(th);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
        }
    }
}
