package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ai;
import com.google.android.gms.ads.internal.bg;

@bu
public final class nt {
    public static kt<nn> a(Context context, zzang zzang, String str, ack ack, bg bgVar) {
        ks a = ki.a(null);
        nu nuVar = new nu(context, ack, zzang, bgVar, str);
        return ki.a((kt<A>) a, (kd<? super A, ? extends B>) nuVar, kz.a);
    }

    public static nn a(Context context, ot otVar, String str, boolean z, boolean z2, @Nullable ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) throws zzarg {
        akl.a(context);
        if (((Boolean) ajh.f().a(akl.az)).booleanValue()) {
            return oz.a(context, otVar, str, z2, z, ack, zzang, aky, aiVar, bgVar, ahh);
        }
        try {
            nv nvVar = new nv(context, otVar, str, z, z2, ack, zzang, aky, aiVar, bgVar, ahh);
            return (nn) jg.a(nvVar);
        } catch (Throwable th) {
            throw new zzarg("Webview initialization failed.", th);
        }
    }
}
