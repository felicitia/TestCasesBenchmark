package com.google.android.gms.internal.ads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class lu {
    private static long a(String str) {
        try {
            return a().parse(str).getTime();
        } catch (ParseException e) {
            ct.a(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    public static acb a(all all) {
        boolean z;
        long j;
        boolean z2;
        long j2;
        long j3;
        long j4;
        all all2 = all;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = all2.c;
        String str = (String) map.get("Date");
        long a = str != null ? a(str) : 0;
        String str2 = (String) map.get("Cache-Control");
        if (str2 != null) {
            String[] split = str2.split(",");
            z2 = false;
            j2 = 0;
            j = 0;
            for (String trim : split) {
                String trim2 = trim.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim2.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim2.startsWith("stale-while-revalidate=")) {
                    j = Long.parseLong(trim2.substring(23));
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    z2 = true;
                }
            }
            z = true;
        } else {
            z2 = false;
            z = false;
            j2 = 0;
            j = 0;
        }
        String str3 = (String) map.get("Expires");
        long a2 = str3 != null ? a(str3) : 0;
        String str4 = (String) map.get("Last-Modified");
        long a3 = str4 != null ? a(str4) : 0;
        String str5 = (String) map.get("ETag");
        if (z) {
            long j5 = currentTimeMillis + (j2 * 1000);
            j4 = z2 ? j5 : j5 + (j * 1000);
            j3 = j5;
        } else if (a <= 0 || a2 < a) {
            j4 = 0;
            j3 = 0;
        } else {
            j3 = currentTimeMillis + (a2 - a);
            j4 = j3;
        }
        acb acb = new acb();
        acb.a = all2.b;
        acb.b = str5;
        acb.f = j3;
        acb.e = j4;
        acb.c = a;
        acb.d = a3;
        acb.g = map;
        acb.h = all2.d;
        return acb;
    }

    static String a(long j) {
        return a().format(new Date(j));
    }

    private static SimpleDateFormat a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
