package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class acw extends abi<Integer, Object> {
    public Long a;
    public Boolean b;
    public Boolean c;

    public acw() {
    }

    public acw(String str) {
        a(str);
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Object> a() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(Integer.valueOf(0), this.a);
        hashMap.put(Integer.valueOf(1), this.b);
        hashMap.put(Integer.valueOf(2), this.c);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        HashMap b2 = b(str);
        if (b2 != null) {
            this.a = (Long) b2.get(Integer.valueOf(0));
            this.b = (Boolean) b2.get(Integer.valueOf(1));
            this.c = (Boolean) b2.get(Integer.valueOf(2));
        }
    }
}
