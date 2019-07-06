package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.ack;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.ka;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class am extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzbp a;

    private am(zzbp zzbp) {
        this.a = zzbp;
    }

    /* synthetic */ am(zzbp zzbp, aj ajVar) {
        this(zzbp);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final String doInBackground(Void... voidArr) {
        try {
            this.a.zzaay = (ack) this.a.zzaav.get(((Long) ajh.f().a(akl.cz)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            ka.c("", e);
        }
        return this.a.zzea();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.a.zzaax != null && str != null) {
            this.a.zzaax.loadUrl(str);
        }
    }
}
