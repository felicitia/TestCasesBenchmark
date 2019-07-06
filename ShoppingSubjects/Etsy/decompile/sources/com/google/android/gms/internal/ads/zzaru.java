package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.ao;
import java.io.File;
import java.util.Collections;
import java.util.Map;

@bu
@TargetApi(11)
public class zzaru extends zzaqx {
    public zzaru(nn nnVar, boolean z) {
        super(nnVar, z);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        if (!(webView instanceof nn)) {
            gv.e("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        nn nnVar = (nn) webView;
        if (this.zzxd != null) {
            this.zzxd.a(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            if (map == null) {
                map = Collections.emptyMap();
            }
            return super.zzd(str, map);
        }
        if (nnVar.zzuf() != null) {
            nnVar.zzuf().zznk();
        }
        akb<String> akb = nnVar.zzud().d() ? akl.K : nnVar.zzuj() ? akl.J : akl.I;
        String str2 = (String) ajh.f().a(akb);
        ao.e();
        return hd.c(nnVar.getContext(), nnVar.zztq().zzcw, str2);
    }
}
