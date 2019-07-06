package com.paypal.android.sdk.onetouch.core.metadata;

import java.util.TimerTask;

final class i extends TimerTask {
    private /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public final void run() {
        this.a.v = this.a.v + 1;
        String j = h.h;
        StringBuilder sb = new StringBuilder("****** LogRiskMetadataTask #");
        sb.append(this.a.v);
        ai.a(j, sb.toString());
        if (h.c(this.a)) {
            String j2 = h.h;
            StringBuilder sb2 = new StringBuilder("No host activity in the last ");
            sb2.append(this.a.u / 1000);
            sb2.append(" seconds. Stopping update interval.");
            ai.a(j2, sb2.toString());
            this.a.F.cancel();
            return;
        }
        try {
            h.f(this.a);
        } catch (Exception e) {
            ai.a(h.h, "Error in logRiskMetadataTask: ", (Throwable) e);
        }
    }
}
