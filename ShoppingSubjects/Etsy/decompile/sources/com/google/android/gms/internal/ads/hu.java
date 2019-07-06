package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.ao;
import java.io.InputStream;
import java.util.Map;

@TargetApi(21)
public final class hu extends hs {
    public final WebResourceResponse a(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        WebResourceResponse webResourceResponse = new WebResourceResponse(str, str2, i, str3, map, inputStream);
        return webResourceResponse;
    }

    public final zzaqx a(nn nnVar, boolean z) {
        return new zzarv(nnVar, z);
    }

    public final CookieManager c(Context context) {
        if (e()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        } catch (Throwable th) {
            gv.b("Failed to obtain CookieManager.", th);
            ao.i().a(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public final int f() {
        return 16974374;
    }
}
