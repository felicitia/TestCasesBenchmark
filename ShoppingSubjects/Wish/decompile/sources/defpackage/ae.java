package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: ae reason: default package */
/* compiled from: GA */
public final class ae extends q {
    private final int b;
    private final Context c;

    public ae(Context context, int i) {
        this.c = context;
        this.b = i;
    }

    @SuppressLint({"HardwareIds"})
    public final void c() {
        switch (this.b) {
            case 0:
                super.a(System.getProperty(c.y[4], ""));
                return;
            case 1:
                super.a(System.getProperty(c.y[5], ""));
                super.a(System.getProperty(c.y[6], ""));
                return;
            case 2:
                super.a(d());
                return;
            case 3:
                super.a(String.valueOf(SystemClock.uptimeMillis() / 1000));
                return;
            case 4:
                super.a(System.getProperty(c.y[7], ""));
                return;
            case 5:
                if (this.c != null) {
                    super.a(Secure.getString(this.c.getContentResolver(), "android_id"));
                    break;
                }
                break;
        }
    }

    private static String d() {
        String str = "O";
        try {
            String property = System.getProperty(c.y[7], null);
            if (property == null || property.length() <= 0) {
                return str;
            }
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(property);
            return (!matcher.matches() || Integer.parseInt(matcher.group(1)) <= 1) ? str : "N";
        } catch (Exception unused) {
            return str;
        }
    }

    public final boolean b() {
        return this.a != null && this.a.size() > 0;
    }
}
