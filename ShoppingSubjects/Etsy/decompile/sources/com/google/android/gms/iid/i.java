package com.google.android.gms.iid;

abstract class i {
    private static i a;

    i() {
    }

    static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                a = new c();
            }
            iVar = a;
        }
        return iVar;
    }

    /* access modifiers changed from: 0000 */
    public abstract j<Boolean> a(String str, boolean z);
}
