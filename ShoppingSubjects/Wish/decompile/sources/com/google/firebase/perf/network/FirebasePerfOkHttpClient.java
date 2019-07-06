package com.google.firebase.perf.network;

import android.support.annotation.Keep;
import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import com.google.android.gms.internal.firebase-perf.zzh;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FirebasePerfOkHttpClient {
    private FirebasePerfOkHttpClient() {
    }

    @Keep
    public static Response execute(Call call) throws IOException {
        zzc zza = zzc.zza(zzh.zzo());
        zzaa zzaa = new zzaa();
        long zzar = zzaa.zzar();
        try {
            Response execute = call.execute();
            zza(execute, zza, zzar, zzaa.zzas());
            return execute;
        } catch (IOException e) {
            Request request = call.request();
            if (request != null) {
                HttpUrl url = request.url();
                if (url != null) {
                    zza.zza(url.url().toString());
                }
                if (request.method() != null) {
                    zza.zzb(request.method());
                }
            }
            zza.zzc(zzar);
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    @Keep
    public static void enqueue(Call call, Callback callback) {
        zzaa zzaa = new zzaa();
        Callback callback2 = callback;
        zzg zzg = new zzg(callback2, zzh.zzo(), zzaa, zzaa.zzar());
        call.enqueue(zzg);
    }

    static void zza(Response response, zzc zzc, long j, long j2) throws IOException {
        Request request = response.request();
        if (request != null) {
            zzc.zza(request.url().url().toString());
            zzc.zzb(request.method());
            if (request.body() != null) {
                long contentLength = request.body().contentLength();
                if (contentLength != -1) {
                    zzc.zza(contentLength);
                }
            }
            ResponseBody body = response.body();
            if (body != null) {
                long contentLength2 = body.contentLength();
                if (contentLength2 != -1) {
                    zzc.zzb(contentLength2);
                }
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    zzc.zzc(contentType.toString());
                }
            }
            zzc.zza(response.code());
            zzc.zzc(j);
            zzc.zzf(j2);
            zzc.zzf();
        }
    }
}
