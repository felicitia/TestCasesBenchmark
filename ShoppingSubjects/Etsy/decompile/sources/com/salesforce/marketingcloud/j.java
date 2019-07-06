package com.salesforce.marketingcloud;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailability;
import com.salesforce.marketingcloud.e.h;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY})
public final class j {
    private static final String a = a("NULL");
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static int e = 6;
    private static a f;

    public static String a(Class<?> cls) {
        return a(cls.getSimpleName());
    }

    public static String a(String str) {
        return b(a("~!%s", str));
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    static void a(int i) {
        if (i < 2) {
            i = 2;
        }
        if (i > 6) {
            i = 6;
        }
        e = i;
    }

    private static void a(int i, String str, Throwable th, String str2, Object... objArr) {
        if (f != null && i >= e) {
            try {
                a aVar = f;
                String b2 = b(str);
                if (objArr != null) {
                    if (objArr.length != 0) {
                        str2 = String.format(Locale.ENGLISH, str2, objArr);
                    }
                }
                aVar.a(i, b2, d(str2), th);
            } catch (Exception e2) {
                Log.e("~!Logger", String.format(Locale.ENGLISH, "Exception was thrown by %s", new Object[]{f.getClass().getName()}), e2);
            }
        }
    }

    static void a(a aVar) {
        f = aVar;
    }

    static void a(String str, String str2, String str3) {
        b = str;
        c = str2;
        d = str3;
    }

    public static void a(@NonNull String str, @NonNull String str2, Object... objArr) {
        a(2, str, null, str2, objArr);
    }

    public static void a(@NonNull String str, Throwable th, @NonNull String str2, Object... objArr) {
        a(3, str, th, str2, objArr);
    }

    private static String b(String str) {
        return str == null ? a : str.length() <= 23 ? str : str.substring(0, 23);
    }

    public static void b(@NonNull String str, @NonNull String str2, Object... objArr) {
        a(3, str, null, str2, objArr);
    }

    public static void b(@NonNull String str, Throwable th, @NonNull String str2, Object... objArr) {
        a(5, str, th, str2, objArr);
    }

    private static String c(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append("\nSdk Version: ");
        sb.append(h.a());
        try {
            sb.append("\nGoogle Play Services Version: ");
            sb.append(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        } catch (Exception unused) {
        }
        return sb.toString();
    }

    public static void c(@NonNull String str, @NonNull String str2, Object... objArr) {
        a(4, str, null, str2, objArr);
    }

    public static void c(@NonNull String str, Throwable th, @NonNull String str2, Object... objArr) {
        a(6, str, th, c(str2), objArr);
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return e(str);
        }
        if (b != null) {
            str = str.replaceAll(b, "████████-████-████-████-████████████");
        }
        if (c != null) {
            str = str.replaceAll(c, "███████████████████████");
        }
        if (d != null) {
            str = str.replaceAll(d, "████████");
        }
        return str;
    }

    public static void d(@NonNull String str, @NonNull String str2, Object... objArr) {
        a(5, str, null, str2, objArr);
    }

    private static String e(String str) {
        if (str == null) {
            return "Log message was `null`";
        }
        if (str.trim().length() == 0) {
            str = "Log message was empty";
        }
        return str;
    }

    public static void e(@NonNull String str, @NonNull String str2, Object... objArr) {
        a(6, str, null, c(str2), objArr);
    }
}
