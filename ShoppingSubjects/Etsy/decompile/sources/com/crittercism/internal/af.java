package com.crittercism.internal;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;

public final class af extends ab {
    private int g;

    public af(v vVar, int i) {
        super(vVar);
        this.g = i;
    }

    /* access modifiers changed from: protected */
    public final v g() {
        if (this.a.c().equals(BaseHttpRequest.HEAD) || (this.g >= 100 && this.g <= 199) || this.g == 204 || this.g == 304) {
            this.a.b(a());
            return this.a.b();
        } else if (this.f) {
            return new z(this);
        } else {
            if (this.d) {
                if (this.e > 0) {
                    return new x(this, this.e);
                }
                this.a.b(a());
                return this.a.b();
            } else if (!this.a.c().equals("CONNECT")) {
                return new aa(this);
            } else {
                this.a.b(a());
                return this.a.b();
            }
        }
    }
}
