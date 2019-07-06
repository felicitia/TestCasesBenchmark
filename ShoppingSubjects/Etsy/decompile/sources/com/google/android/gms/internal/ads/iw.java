package com.google.android.gms.internal.ads;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import java.util.Map;

public final class iw extends amf<all> {
    private final le<all> a;
    private final Map<String, String> b;
    private final jt c;

    public iw(String str, le<all> leVar) {
        this(str, null, leVar);
    }

    private iw(String str, Map<String, String> map, le<all> leVar) {
        super(0, str, new ix(leVar));
        this.b = null;
        this.a = leVar;
        this.c = new jt();
        this.c.a(str, BaseHttpRequest.GET, null, null);
    }

    /* access modifiers changed from: protected */
    public final arb<all> a(all all) {
        return arb.a(all, lu.a(all));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void a(Object obj) {
        all all = (all) obj;
        this.c.a(all.c, all.a);
        jt jtVar = this.c;
        byte[] bArr = all.b;
        if (jt.c() && bArr != null) {
            jtVar.a(bArr);
        }
        this.a.b(all);
    }
}
