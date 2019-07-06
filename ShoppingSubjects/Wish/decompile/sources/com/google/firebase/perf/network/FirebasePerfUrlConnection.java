package com.google.firebase.perf.network;

import android.support.annotation.Keep;
import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzad;
import com.google.android.gms.internal.firebase-perf.zzc;
import com.google.android.gms.internal.firebase-perf.zzh;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class FirebasePerfUrlConnection {
    private FirebasePerfUrlConnection() {
    }

    @Keep
    public static InputStream openStream(URL url) throws IOException {
        return zza(new zzad(url), zzh.zzo(), new zzaa());
    }

    @Keep
    public static Object getContent(URL url) throws IOException {
        return zzb(new zzad(url), zzh.zzo(), new zzaa());
    }

    @Keep
    public static Object getContent(URL url, Class[] clsArr) throws IOException {
        return zza(new zzad(url), clsArr, zzh.zzo(), new zzaa());
    }

    @Keep
    public static Object instrument(Object obj) throws IOException {
        if (obj instanceof HttpsURLConnection) {
            return new zzd((HttpsURLConnection) obj, new zzaa(), zzc.zza(zzh.zzo()));
        }
        return obj instanceof HttpURLConnection ? new zzc((HttpURLConnection) obj, new zzaa(), zzc.zza(zzh.zzo())) : obj;
    }

    private static InputStream zza(zzad zzad, zzh zzh, zzaa zzaa) throws IOException {
        zzaa.reset();
        long zzar = zzaa.zzar();
        zzc zza = zzc.zza(zzh);
        try {
            URLConnection openConnection = zzad.openConnection();
            if (openConnection instanceof HttpsURLConnection) {
                return new zzd((HttpsURLConnection) openConnection, zzaa, zza).getInputStream();
            }
            if (openConnection instanceof HttpURLConnection) {
                return new zzc((HttpURLConnection) openConnection, zzaa, zza).getInputStream();
            }
            return openConnection.getInputStream();
        } catch (IOException e) {
            zza.zzc(zzar);
            zza.zzf(zzaa.zzas());
            zza.zza(zzad.toString());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static Object zzb(zzad zzad, zzh zzh, zzaa zzaa) throws IOException {
        zzaa.reset();
        long zzar = zzaa.zzar();
        zzc zza = zzc.zza(zzh);
        try {
            URLConnection openConnection = zzad.openConnection();
            if (openConnection instanceof HttpsURLConnection) {
                return new zzd((HttpsURLConnection) openConnection, zzaa, zza).getContent();
            }
            if (openConnection instanceof HttpURLConnection) {
                return new zzc((HttpURLConnection) openConnection, zzaa, zza).getContent();
            }
            return openConnection.getContent();
        } catch (IOException e) {
            zza.zzc(zzar);
            zza.zzf(zzaa.zzas());
            zza.zza(zzad.toString());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static Object zza(zzad zzad, Class[] clsArr, zzh zzh, zzaa zzaa) throws IOException {
        zzaa.reset();
        long zzar = zzaa.zzar();
        zzc zza = zzc.zza(zzh);
        try {
            URLConnection openConnection = zzad.openConnection();
            if (openConnection instanceof HttpsURLConnection) {
                return new zzd((HttpsURLConnection) openConnection, zzaa, zza).getContent(clsArr);
            }
            if (openConnection instanceof HttpURLConnection) {
                return new zzc((HttpURLConnection) openConnection, zzaa, zza).getContent(clsArr);
            }
            return openConnection.getContent(clsArr);
        } catch (IOException e) {
            zza.zzc(zzar);
            zza.zzf(zzaa.zzas());
            zza.zza(zzad.toString());
            zzh.zzb(zza);
            throw e;
        }
    }
}
