package com.etsy.android.uikit.webview;

import com.etsy.android.lib.config.b;
import com.etsy.android.lib.config.c;
import kotlin.jvm.internal.p;
import kotlin.text.m;

/* compiled from: GCPUrlHostnameReplacer.kt */
public final class a {
    public static final C0115a a = new C0115a(null);
    private final c b;

    /* renamed from: com.etsy.android.uikit.webview.a$a reason: collision with other inner class name */
    /* compiled from: GCPUrlHostnameReplacer.kt */
    public static final class C0115a {
        private C0115a() {
        }

        public /* synthetic */ C0115a(o oVar) {
            this();
        }
    }

    public a(c cVar) {
        p.b(cVar, "configMap");
        this.b = cVar;
    }

    public final boolean a(String str) {
        p.b(str, "url");
        if (this.b.c()) {
            return m.a(str, "https://www.etsy.com", false, 2, null) || m.a(str, "http://www.etsy.com", false, 2, null) || m.a(str, "www.etsy.com", false, 2, null);
        }
        return false;
    }

    public final String b(String str) {
        p.b(str, "url");
        if (!this.b.c()) {
            return str;
        }
        String b2 = this.b.b(b.ck);
        if (b2 == null) {
            b2 = "https://www.vms.etsy.com";
        }
        String str2 = b2;
        if (m.a(str, "https://www.etsy.com", false, 2, null)) {
            p.a((Object) str2, "gcpHost");
            str = m.a(str, "https://www.etsy.com", str2, false, 4, (Object) null);
        } else if (m.a(str, "http://www.etsy.com", false, 2, null)) {
            p.a((Object) str2, "gcpHost");
            str = m.a(str, "http://www.etsy.com", str2, false, 4, (Object) null);
        } else if (m.a(str, "www.etsy.com", false, 2, null)) {
            p.a((Object) str2, "gcpHost");
            str = m.a(str, "www.etsy.com", str2, false, 4, (Object) null);
        }
        return str;
    }
}
