package com.google.ads.conversiontracking;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.ads.conversiontracking.g.b;

public class a extends b {
    public static boolean a(Context context, Uri uri) {
        if (uri == null) {
            Log.e("GoogleConversionReporter", "Failed to register referrer from a null click url");
            return false;
        }
        String valueOf = String.valueOf(uri);
        StringBuilder sb = new StringBuilder(13 + String.valueOf(valueOf).length());
        sb.append("Registering: ");
        sb.append(valueOf);
        Log.i("GoogleConversionReporter", sb.toString());
        b a = g.a(uri);
        if (a == null) {
            String valueOf2 = String.valueOf(uri);
            StringBuilder sb2 = new StringBuilder(31 + String.valueOf(valueOf2).length());
            sb2.append("Failed to parse referrer from: ");
            sb2.append(valueOf2);
            Log.w("GoogleConversionReporter", sb2.toString());
            return false;
        }
        boolean a2 = g.a(context, a);
        if (a2) {
            String valueOf3 = String.valueOf(uri);
            StringBuilder sb3 = new StringBuilder(25 + String.valueOf(valueOf3).length());
            sb3.append("Successfully registered: ");
            sb3.append(valueOf3);
            Log.i("GoogleConversionReporter", sb3.toString());
        } else {
            String valueOf4 = String.valueOf(uri);
            StringBuilder sb4 = new StringBuilder(20 + String.valueOf(valueOf4).length());
            sb4.append("Failed to register: ");
            sb4.append(valueOf4);
            Log.w("GoogleConversionReporter", sb4.toString());
        }
        return a2;
    }
}
