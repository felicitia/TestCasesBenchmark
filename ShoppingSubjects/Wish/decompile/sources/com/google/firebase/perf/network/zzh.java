package com.google.firebase.perf.network;

import android.util.Log;
import com.google.android.gms.internal.firebase-perf.zzc;
import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;

public final class zzh {
    public static Long zza(HttpMessage httpMessage) {
        try {
            Header firstHeader = httpMessage.getFirstHeader("content-length");
            if (firstHeader != null) {
                return Long.valueOf(Long.parseLong(firstHeader.getValue()));
            }
        } catch (NumberFormatException unused) {
            Log.d("FirebasePerformance", "The content-length value is not a valid number");
        }
        return null;
    }

    public static String zza(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("content-type");
        if (firstHeader != null) {
            String value = firstHeader.getValue();
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public static void zzb(zzc zzc) {
        if (zzc.zzc() == null) {
            zzc.zzd();
        }
        zzc.zzf();
    }
}
