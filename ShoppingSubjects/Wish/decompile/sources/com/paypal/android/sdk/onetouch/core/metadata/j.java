package com.paypal.android.sdk.onetouch.core.metadata;

import java.util.TimerTask;

final class j extends TimerTask {
    private /* synthetic */ h a;

    j(h hVar) {
        this.a = hVar;
    }

    public final void run() {
        this.a.w = this.a.w + 1;
        String j = h.h;
        StringBuilder sb = new StringBuilder("****** LoadConfigurationTask #");
        sb.append(this.a.w);
        ai.a(j, sb.toString());
        ab.a().a(new v(this.a.r, this.a.y, this.a.G));
    }
}
