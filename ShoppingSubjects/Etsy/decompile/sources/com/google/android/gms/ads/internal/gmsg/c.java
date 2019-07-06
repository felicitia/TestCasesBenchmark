package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.internal.ads.bu;
import java.util.Map;

@bu
public final class c implements ae<Object> {
    private final Context a;

    public c(Context context) {
        this.a = context;
    }

    public final void zza(Object obj, Map<String, String> map) {
        if (ao.B().a(this.a)) {
            ao.B().a(this.a, (String) map.get("eventName"), (String) map.get("eventId"));
        }
    }
}
