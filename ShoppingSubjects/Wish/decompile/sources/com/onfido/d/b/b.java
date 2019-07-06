package com.onfido.d.b;

import com.onfido.d.a.d;
import com.onfido.d.b.a.j;
import com.onfido.d.b.b.a;
import com.onfido.d.c;
import com.onfido.d.e;
import com.onfido.d.h;
import com.onfido.d.k;
import com.onfido.d.l;
import com.onfido.d.m;
import java.util.ArrayList;
import java.util.Map;

public final class b {
    private static int a(m mVar, m mVar2) {
        if (mVar == null || mVar2 == null) {
            return 0;
        }
        return (int) Math.abs(mVar.a() - mVar2.a());
    }

    private static int a(m[] mVarArr) {
        return Math.max(Math.max(a(mVarArr[0], mVarArr[4]), (a(mVarArr[6], mVarArr[2]) * 17) / 18), Math.max(a(mVarArr[1], mVarArr[5]), (a(mVarArr[7], mVarArr[3]) * 17) / 18));
    }

    private static k[] a(c cVar, Map<e, ?> map, boolean z) {
        ArrayList arrayList = new ArrayList();
        com.onfido.d.b.b.b a = a.a(cVar, map, z);
        for (m[] mVarArr : a.b()) {
            d a2 = j.a(a.a(), mVarArr[4], mVarArr[5], mVarArr[6], mVarArr[7], b(mVarArr), a(mVarArr));
            k kVar = new k(a2.b(), a2.a(), mVarArr, com.onfido.d.a.PDF_417);
            kVar.a(l.ERROR_CORRECTION_LEVEL, a2.c());
            c cVar2 = (c) a2.d();
            if (cVar2 != null) {
                kVar.a(l.PDF417_EXTRA_METADATA, cVar2);
            }
            arrayList.add(kVar);
        }
        return (k[]) arrayList.toArray(new k[arrayList.size()]);
    }

    private static int b(m mVar, m mVar2) {
        if (mVar == null || mVar2 == null) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(mVar.a() - mVar2.a());
    }

    private static int b(m[] mVarArr) {
        return Math.min(Math.min(b(mVarArr[0], mVarArr[4]), (b(mVarArr[6], mVarArr[2]) * 17) / 18), Math.min(b(mVarArr[1], mVarArr[5]), (b(mVarArr[7], mVarArr[3]) * 17) / 18));
    }

    public k a(c cVar, Map<e, ?> map) {
        k[] a = a(cVar, map, false);
        if (a != null && a.length != 0 && a[0] != null) {
            return a[0];
        }
        throw h.a();
    }

    public void a() {
    }
}
