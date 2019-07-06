package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import java.util.Map;

@bu
public final class e {
    private final nn a;
    private final boolean b;
    private final String c;

    public e(nn nnVar, Map<String, String> map) {
        this.a = nnVar;
        this.c = (String) map.get("forceOrientation");
        this.b = map.containsKey("allowOrientationChange") ? Boolean.parseBoolean((String) map.get("allowOrientationChange")) : true;
    }

    public final void a() {
        if (this.a == null) {
            gv.e("AdWebView is null");
            return;
        }
        int i = "portrait".equalsIgnoreCase(this.c) ? ao.g().b() : "landscape".equalsIgnoreCase(this.c) ? ao.g().a() : this.b ? -1 : ao.g().c();
        this.a.setRequestedOrientation(i);
    }
}
