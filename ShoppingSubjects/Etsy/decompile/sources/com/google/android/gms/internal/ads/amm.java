package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@bu
public final class amm implements ajo {
    /* access modifiers changed from: private */
    @Nullable
    public aml a;
    /* access modifiers changed from: private */
    public boolean b;
    private final Context c;
    /* access modifiers changed from: private */
    public final Object d = new Object();

    public amm(Context context) {
        this.c = context;
    }

    private final Future<ParcelFileDescriptor> a(zzsg zzsg) {
        amn amn = new amn(this);
        amo amo = new amo(this, amn, zzsg);
        amr amr = new amr(this, amn);
        synchronized (this.d) {
            this.a = new aml(this.c, ao.t().a(), amo, amr);
            this.a.checkAvailabilityAndConnect();
        }
        return amn;
    }

    /* access modifiers changed from: private */
    public final void a() {
        synchronized (this.d) {
            if (this.a != null) {
                this.a.disconnect();
                this.a = null;
                Binder.flushPendingCommands();
            }
        }
    }

    public final all a(amf<?> amf) throws zzae {
        all all;
        zzsg zzh = zzsg.zzh(amf);
        long intValue = (long) ((Integer) ajh.f().a(akl.cK)).intValue();
        long elapsedRealtime = ao.l().elapsedRealtime();
        try {
            zzsi zzsi = (zzsi) new zzaev((ParcelFileDescriptor) a(zzh).get(intValue, TimeUnit.MILLISECONDS)).zza(zzsi.CREATOR);
            if (zzsi.zzbnj) {
                throw new zzae(zzsi.zzbnk);
            }
            if (zzsi.zzbnh.length != zzsi.zzbni.length) {
                all = null;
            } else {
                HashMap hashMap = new HashMap();
                for (int i = 0; i < zzsi.zzbnh.length; i++) {
                    hashMap.put(zzsi.zzbnh[i], zzsi.zzbni[i]);
                }
                all = new all(zzsi.statusCode, zzsi.data, (Map<String, String>) hashMap, zzsi.zzac, zzsi.zzad);
            }
            return all;
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            return null;
        } finally {
            long j = ao.l().elapsedRealtime() - elapsedRealtime;
            StringBuilder sb = new StringBuilder(52);
            sb.append("Http assets remote cache took ");
            sb.append(j);
            sb.append("ms");
            gv.a(sb.toString());
        }
    }
}
