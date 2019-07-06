package com.google.firebase.perf.network;

import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import java.io.IOException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

public final class zzf<T> implements ResponseHandler<T> {
    private final zzaa zzdo;
    private final zzc zzeb;
    private final ResponseHandler<? extends T> zzek;

    public zzf(ResponseHandler<? extends T> responseHandler, zzaa zzaa, zzc zzc) {
        this.zzek = responseHandler;
        this.zzdo = zzaa;
        this.zzeb = zzc;
    }

    public final T handleResponse(HttpResponse httpResponse) throws IOException {
        this.zzeb.zzf(this.zzdo.zzas());
        this.zzeb.zza(httpResponse.getStatusLine().getStatusCode());
        Long zza = zzh.zza((HttpMessage) httpResponse);
        if (zza != null) {
            this.zzeb.zzb(zza.longValue());
        }
        String zza2 = zzh.zza(httpResponse);
        if (zza2 != null) {
            this.zzeb.zzc(zza2);
        }
        this.zzeb.zzf();
        return this.zzek.handleResponse(httpResponse);
    }
}
