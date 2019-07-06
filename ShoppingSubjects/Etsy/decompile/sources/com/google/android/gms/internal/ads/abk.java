package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class abk extends abi<Integer, Object> {
    public String a;
    public long b;
    public String c;
    public String d;
    public String e;

    public abk() {
        this.a = "E";
        this.b = -1;
        this.c = "E";
        this.d = "E";
        this.e = "E";
    }

    public abk(String str) {
        this();
        a(str);
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Object> a() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(Integer.valueOf(0), this.a);
        hashMap.put(Integer.valueOf(4), this.e);
        hashMap.put(Integer.valueOf(3), this.d);
        hashMap.put(Integer.valueOf(2), this.c);
        hashMap.put(Integer.valueOf(1), Long.valueOf(this.b));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        HashMap b2 = b(str);
        if (b2 != null) {
            this.a = b2.get(Integer.valueOf(0)) == null ? "E" : (String) b2.get(Integer.valueOf(0));
            this.b = b2.get(Integer.valueOf(1)) == null ? -1 : ((Long) b2.get(Integer.valueOf(1))).longValue();
            this.c = b2.get(Integer.valueOf(2)) == null ? "E" : (String) b2.get(Integer.valueOf(2));
            this.d = b2.get(Integer.valueOf(3)) == null ? "E" : (String) b2.get(Integer.valueOf(3));
            this.e = b2.get(Integer.valueOf(4)) == null ? "E" : (String) b2.get(Integer.valueOf(4));
        }
    }
}
