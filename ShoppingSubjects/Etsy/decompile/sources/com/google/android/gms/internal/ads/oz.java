package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ai;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;

@bu
public final class oz {
    public static nn a(Context context, ot otVar, String str, boolean z, boolean z2, @Nullable ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) throws zzarg {
        try {
            pa paVar = new pa(context, otVar, str, z, z2, ack, zzang, aky, aiVar, bgVar, ahh);
            return (nn) jg.a(paVar);
        } catch (Throwable th) {
            Throwable th2 = th;
            ao.i().a(th2, "AdWebViewFactory.newAdWebView2");
            throw new zzarg("Webview initialization failed.", th2);
        }
    }
}
