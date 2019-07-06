package com.crittercism.internal;

import com.etsy.android.lib.convos.Draft;

public abstract class ab extends v {
    boolean d = false;
    int e;
    boolean f = false;
    private boolean g = false;
    private boolean h = false;

    /* access modifiers changed from: protected */
    public final int d() {
        return 32;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 128;
    }

    /* access modifiers changed from: protected */
    public abstract v g();

    public ab(v vVar) {
        super(vVar);
    }

    public final v b() {
        if (this.h) {
            return g();
        }
        this.b.b = 0;
        return this;
    }

    public final v c() {
        this.b.b = 0;
        return new ai(this);
    }

    public final boolean a(w wVar) {
        int i = this.b.b;
        boolean z = false;
        if (i == 0 || (i == 1 && this.b.a[0] == 13)) {
            this.h = true;
            return true;
        }
        try {
            String[] split = wVar.toString().split(Draft.IMAGE_DELIMITER, 2);
            if (split.length != 2) {
                return false;
            }
            String trim = split[0].trim();
            String trim2 = split[1].trim();
            if (!this.d && trim.equalsIgnoreCase("content-length")) {
                int parseInt = Integer.parseInt(trim2);
                if (parseInt < 0) {
                    return false;
                }
                this.d = true;
                this.e = parseInt;
            } else if (trim.equalsIgnoreCase("transfer-encoding")) {
                this.f = trim2.equalsIgnoreCase("chunked");
            } else if (!this.g && trim.equalsIgnoreCase("host") && trim2 != null) {
                this.g = true;
                this.a.a(trim2);
            }
            z = true;
            return z;
        } catch (NumberFormatException unused) {
        }
    }
}
