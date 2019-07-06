package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@bu
@TargetApi(11)
public final class zzart extends zzaru {
    public zzart(nn nnVar, boolean z) {
        super(nnVar, z);
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, null);
    }
}
