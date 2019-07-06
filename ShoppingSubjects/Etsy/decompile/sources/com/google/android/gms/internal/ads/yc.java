package com.google.android.gms.internal.ads;

final class yc implements yj {
    private yj[] a;

    yc(yj... yjVarArr) {
        this.a = yjVarArr;
    }

    public final boolean a(Class<?> cls) {
        for (yj a2 : this.a) {
            if (a2.a(cls)) {
                return true;
            }
        }
        return false;
    }

    public final yi b(Class<?> cls) {
        yj[] yjVarArr;
        for (yj yjVar : this.a) {
            if (yjVar.a(cls)) {
                return yjVar.b(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
