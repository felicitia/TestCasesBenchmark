package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class adh extends abi<Integer, Long> {
    public Long a;
    public Long b;
    public Long c;

    public adh() {
    }

    public adh(String str) {
        a(str);
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> a() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
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
            this.b = (Long) b2.get(Integer.valueOf(1));
            this.c = (Long) b2.get(Integer.valueOf(2));
        }
    }
}
