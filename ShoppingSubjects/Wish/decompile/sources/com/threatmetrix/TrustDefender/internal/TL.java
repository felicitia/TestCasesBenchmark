package com.threatmetrix.TrustDefender.internal;

import android.util.Log;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TL {

    /* renamed from: do reason: not valid java name */
    private static final Pattern f538do = Pattern.compile("\\{\\}");

    /* renamed from: int reason: not valid java name */
    private static final boolean f539int = (KF.numeric.intValue() == -1);

    /* renamed from: new reason: not valid java name */
    private static boolean f540new = false;

    TL() {
    }

    /* renamed from: if reason: not valid java name */
    public static void m335if(boolean z) {
        f540new = z;
    }

    /* renamed from: if reason: not valid java name */
    public static void m332if(String str, String str2) {
        if (f539int || Log.isLoggable(str, 6)) {
            Log.e(str, str2);
        }
    }

    /* renamed from: if reason: not valid java name */
    public static void m334if(String str, String str2, String... strArr) {
        if (f539int || Log.isLoggable(str, 6)) {
            Log.e(str, m327for(str2, (Object[]) strArr));
        }
    }

    /* renamed from: int reason: not valid java name */
    public static void m337int(String str, String str2, Throwable th) {
        if (f539int || Log.isLoggable(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    /* renamed from: do reason: not valid java name */
    public static void m325do(String str, String str2) {
        if (!f539int) {
            if (!(!f540new && Log.isLoggable(str, 5))) {
                return;
            }
        }
        Log.w(str, str2);
    }

    /* renamed from: do reason: not valid java name */
    public static void m326do(String str, String str2, String... strArr) {
        if (!f539int) {
            if (!(!f540new && Log.isLoggable(str, 5))) {
                return;
            }
        }
        Log.w(str, m327for(str2, (Object[]) strArr));
    }

    /* renamed from: if reason: not valid java name */
    public static void m333if(String str, String str2, Throwable th) {
        if (!f539int) {
            if (!(!f540new && Log.isLoggable(str, 5))) {
                return;
            }
        }
        Log.w(str, str2, th);
    }

    /* renamed from: new reason: not valid java name */
    public static void m338new(String str, String str2) {
        if (!f539int) {
            if (!(!f540new && Log.isLoggable(str, 4))) {
                return;
            }
        }
        Log.i(str, str2);
    }

    /* renamed from: for reason: not valid java name */
    public static void m329for(String str, String str2, String... strArr) {
        if (!f539int) {
            if (!(!f540new && Log.isLoggable(str, 4))) {
                return;
            }
        }
        Log.i(str, m327for(str2, (Object[]) strArr));
    }

    /* renamed from: for reason: not valid java name */
    public static void m328for(String str, String str2, Throwable th) {
        if (!f539int) {
            if (!(!f540new && Log.isLoggable(str, 4))) {
                return;
            }
        }
        Log.i(str, str2, th);
    }

    /* renamed from: if reason: not valid java name */
    public static String m331if(Class cls) {
        StringBuilder sb = new StringBuilder("c.t.tdm.");
        sb.append(cls.getSimpleName());
        String obj = sb.toString();
        return obj.length() > 23 ? obj.substring(0, 23) : obj;
    }

    /* renamed from: new reason: not valid java name */
    static boolean m339new() {
        return f539int;
    }

    /* renamed from: for reason: not valid java name */
    private static String m327for(String str, Object... objArr) {
        if (str == null || objArr == null) {
            return "";
        }
        Matcher matcher = f538do.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            if (matcher.find()) {
                if (obj != null) {
                    matcher.appendReplacement(stringBuffer, String.valueOf(obj));
                } else {
                    matcher.appendReplacement(stringBuffer, "null");
                }
                i++;
            } else {
                throw new InvalidParameterException("Incorrect number of arguments for this format string");
            }
        }
        if (!matcher.find()) {
            return matcher.appendTail(stringBuffer).toString();
        }
        throw new InvalidParameterException("Incorrect number of arguments for this format string");
    }

    /* renamed from: for reason: not valid java name */
    static boolean m330for() {
        return !f540new;
    }

    /* renamed from: if reason: not valid java name */
    static boolean m336if() {
        if (!f540new && Log.isLoggable("TrustDefender", 2)) {
            PH ph = PH.m275do();
            try {
                if (ph.f494char) {
                    ph.f497for.setInfoLogging(1);
                }
            } catch (Throwable th) {
                String str = PH.f490int;
                String str2 = "Native code:";
                if (f539int || Log.isLoggable(str, 6)) {
                    Log.e(str, str2, th);
                }
            }
            return true;
        }
        PH ph2 = PH.m275do();
        try {
            if (ph2.f494char) {
                ph2.f497for.setInfoLogging(0);
            }
        } catch (Throwable th2) {
            String str3 = PH.f490int;
            String str4 = "Native code:";
            if (f539int || Log.isLoggable(str3, 6)) {
                Log.e(str3, str4, th2);
            }
        }
        return false;
    }
}
