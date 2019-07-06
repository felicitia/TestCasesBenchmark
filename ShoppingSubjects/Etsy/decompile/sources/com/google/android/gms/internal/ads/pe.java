package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import java.util.Collections;
import java.util.Map;

@bu
public final class pe {
    public final String a;
    public final Uri b;
    public final Map<String, String> c;
    private final String d;

    @TargetApi(21)
    public pe(WebResourceRequest webResourceRequest) {
        this(webResourceRequest.getUrl().toString(), webResourceRequest.getUrl(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders());
    }

    public pe(String str) {
        this(str, Uri.parse(str), null, null);
    }

    private pe(String str, Uri uri, String str2, Map<String, String> map) {
        this.a = str;
        this.b = uri;
        if (str2 == null) {
            str2 = BaseHttpRequest.GET;
        }
        this.d = str2;
        if (map == null) {
            map = Collections.emptyMap();
        }
        this.c = map;
    }
}
