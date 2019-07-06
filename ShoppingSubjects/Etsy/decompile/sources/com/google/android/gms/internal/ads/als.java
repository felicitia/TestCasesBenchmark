package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class als implements ae<Object> {
    final /* synthetic */ alr a;
    private final /* synthetic */ az b;

    als(alr alr, az azVar) {
        this.a = alr;
        this.b = azVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        nn nnVar = (nn) this.a.a.get();
        if (nnVar == null) {
            this.b.b("/loadHtml", this);
            return;
        }
        nnVar.zzuf().zza((op) new alt(this, map, this.b));
        String str = (String) map.get("overlayHtml");
        String str2 = (String) map.get("baseUrl");
        if (TextUtils.isEmpty(str2)) {
            nnVar.loadData(str, "text/html", "UTF-8");
        } else {
            nnVar.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", null);
        }
    }
}
