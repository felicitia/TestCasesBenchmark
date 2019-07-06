package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@bu
public final class my implements Iterable<mw> {
    private final List<mw> a = new ArrayList();

    public static boolean a(mo moVar) {
        mw b = b(moVar);
        if (b == null) {
            return false;
        }
        b.b.a();
        return true;
    }

    static mw b(mo moVar) {
        Iterator it = ao.z().iterator();
        while (it.hasNext()) {
            mw mwVar = (mw) it.next();
            if (mwVar.a == moVar) {
                return mwVar;
            }
        }
        return null;
    }

    public final int a() {
        return this.a.size();
    }

    public final void a(mw mwVar) {
        this.a.add(mwVar);
    }

    public final void b(mw mwVar) {
        this.a.remove(mwVar);
    }

    public final Iterator<mw> iterator() {
        return this.a.iterator();
    }
}
