package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.lang.ref.WeakReference;

final class alr {
    /* access modifiers changed from: private */
    public final WeakReference<nn> a;
    /* access modifiers changed from: private */
    public String b;

    public alr(nn nnVar) {
        this.a = new WeakReference<>(nnVar);
    }

    public final void a(az azVar) {
        azVar.a("/loadHtml", (ae<? super T>) new als<Object>(this, azVar));
        azVar.a("/showOverlay", (ae<? super T>) new alu<Object>(this, azVar));
        azVar.a("/hideOverlay", (ae<? super T>) new alv<Object>(this, azVar));
        nn nnVar = (nn) this.a.get();
        if (nnVar != null) {
            nnVar.zza("/sendMessageToSdk", (ae<? super nn>) new alw<Object>(this, azVar));
        }
    }
}
