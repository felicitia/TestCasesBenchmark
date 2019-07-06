package defpackage;

import android.webkit.JavascriptInterface;

/* renamed from: e reason: default package */
/* compiled from: GA */
public final class e {
    private b a;
    private p b;

    public e(b bVar, p pVar) {
        this.b = pVar;
        this.a = bVar;
    }

    @JavascriptInterface
    public final void pt$__r_t(String str, String str2, String str3, String str4, String str5) {
        this.a.execute(new String[]{str, str3, str2, str4, str5});
    }

    @JavascriptInterface
    public final String _a$_fc_res(int[] iArr, int[] iArr2) {
        return this.b.a(iArr, iArr2).toString().replaceAll("\\\\u(D[A-F8-9][A-F0-9]{2})", "\\u$1");
    }
}
