package com.google.firebase.perf.network;

import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import com.google.android.gms.internal.firebase-perf.zzh;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public final class zzg implements Callback {
    private final zzaa zzdo;
    private final zzc zzeb;
    private final Callback zzel;
    private final long zzem;

    public zzg(Callback callback, zzh zzh, zzaa zzaa, long j) {
        this.zzel = callback;
        this.zzeb = zzc.zza(zzh);
        this.zzem = j;
        this.zzdo = zzaa;
    }

    public final void onFailure(Call call, IOException iOException) {
        Request request = call.request();
        if (request != null) {
            HttpUrl url = request.url();
            if (url != null) {
                this.zzeb.zza(url.url().toString());
            }
            if (request.method() != null) {
                this.zzeb.zzb(request.method());
            }
        }
        this.zzeb.zzc(this.zzem);
        this.zzeb.zzf(this.zzdo.zzas());
        zzh.zzb(this.zzeb);
        this.zzel.onFailure(call, iOException);
    }

    public final void onResponse(Call call, Response response) throws IOException {
        Response response2 = response;
        FirebasePerfOkHttpClient.zza(response2, this.zzeb, this.zzem, this.zzdo.zzas());
        this.zzel.onResponse(call, response);
    }
}
