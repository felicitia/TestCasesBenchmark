package com.google.android.gms.internal.icing;

final class be implements bk {
    private bk[] a;

    be(bk... bkVarArr) {
        this.a = bkVarArr;
    }

    public final boolean a(Class<?> cls) {
        for (bk a2 : this.a) {
            if (a2.a(cls)) {
                return true;
            }
        }
        return false;
    }

    public final bj b(Class<?> cls) {
        bk[] bkVarArr;
        for (bk bkVar : this.a) {
            if (bkVar.a(cls)) {
                return bkVar.b(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
