package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class zzok implements OnClickListener {
    private final az zzaad;
    /* access modifiers changed from: private */
    @Nullable
    public zzro zzbhm;
    @Nullable
    private ae zzbhn;
    @Nullable
    @VisibleForTesting
    String zzbho;
    @Nullable
    @VisibleForTesting
    Long zzbhp;
    @Nullable
    @VisibleForTesting
    WeakReference<View> zzbhq;

    public zzok(az azVar) {
        this.zzaad = azVar;
    }

    private final void zzjx() {
        this.zzbho = null;
        this.zzbhp = null;
        if (this.zzbhq != null) {
            View view = (View) this.zzbhq.get();
            this.zzbhq = null;
            if (view != null) {
                view.setClickable(false);
                view.setOnClickListener(null);
            }
        }
    }

    public final void cancelUnconfirmedClick() {
        if (this.zzbhm != null && this.zzbhp != null) {
            zzjx();
            try {
                this.zzbhm.onUnconfirmedClickCancelled();
            } catch (RemoteException e) {
                ka.d("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onClick(View view) {
        if (this.zzbhq != null && this.zzbhq.get() == view) {
            if (!(this.zzbho == null || this.zzbhp == null)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", this.zzbho);
                    jSONObject.put("time_interval", ao.l().currentTimeMillis() - this.zzbhp.longValue());
                    jSONObject.put("messageType", "onePointFiveClick");
                    this.zzaad.a("sendMessageToNativeJs", jSONObject);
                } catch (JSONException e) {
                    gv.b("Unable to dispatch sendMessageToNativeJs event", e);
                }
            }
            zzjx();
        }
    }

    public final void zza(zzro zzro) {
        this.zzbhm = zzro;
        if (this.zzbhn != null) {
            this.zzaad.b("/unconfirmedClick", this.zzbhn);
        }
        this.zzbhn = new alc(this);
        this.zzaad.a("/unconfirmedClick", this.zzbhn);
    }

    @Nullable
    public final zzro zzjw() {
        return this.zzbhm;
    }
}
