package com.threatmetrix.TrustDefender.internal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

class CC {

    /* renamed from: for reason: not valid java name */
    private static final Hashtable<String, Long> f63for = new Hashtable<>();

    /* renamed from: int reason: not valid java name */
    private static final AtomicLong f64int = new AtomicLong(0);

    /* renamed from: new reason: not valid java name */
    private static final String f65new = TL.m331if(CC.class);

    /* renamed from: if reason: not valid java name */
    private long f66if = S.m171for();

    CC() {
    }

    /* renamed from: do reason: not valid java name */
    public final void m26do(String str, String str2) {
        long j = S.m171for() - this.f66if;
        f63for.put(String.format("%03d:%s:%s", new Object[]{Integer.valueOf(f63for.size()), str, str2}), Long.valueOf(j));
        this.f66if = S.m171for();
    }

    /* renamed from: new reason: not valid java name */
    public static void m25new() {
        f64int.set(S.m171for());
    }

    /* renamed from: if reason: not valid java name */
    public static void m24if() {
        f63for.clear();
        f64int.set(0);
    }

    /* renamed from: do reason: not valid java name */
    public static String m23do() {
        long j = S.m171for() - f64int.get();
        if (j <= TimeUnit.MILLISECONDS.convert(4, TimeUnit.SECONDS)) {
            return null;
        }
        ArrayList arrayList = new ArrayList(f63for.entrySet());
        Collections.sort(arrayList, new Comparator<Entry<String, Long>>() {
            public final /* synthetic */ int compare(Object obj, Object obj2) {
                return ((Long) ((Entry) obj2).getValue()).compareTo((Long) ((Entry) obj).getValue());
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("tt:%s;", new Object[]{decimalFormat.format(((double) j) / 1000.0d)}));
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String str = (String) entry.getKey();
            Long l = (Long) entry.getValue();
            String[] split = str.split(":");
            sb.append(String.format("%s:%s;", new Object[]{split[split.length - 1], decimalFormat.format(l.doubleValue() / 1000.0d)}));
            i++;
            if (i >= 10) {
                break;
            }
        }
        return sb.toString();
    }
}
