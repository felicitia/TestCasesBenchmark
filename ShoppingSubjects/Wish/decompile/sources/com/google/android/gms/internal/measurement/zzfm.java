package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class zzfm extends zzjs {
    private final SSLSocketFactory zzalf;

    public zzfm(zzjt zzjt) {
        super(zzjt);
        this.zzalf = VERSION.SDK_INT < 19 ? new zzke() : null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] zzb(java.net.HttpURLConnection r5) throws java.io.IOException {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0025 }
            r1.<init>()     // Catch:{ all -> 0x0025 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ all -> 0x0025 }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0023 }
        L_0x000e:
            int r2 = r5.read(r0)     // Catch:{ all -> 0x0023 }
            if (r2 <= 0) goto L_0x0019
            r3 = 0
            r1.write(r0, r3, r2)     // Catch:{ all -> 0x0023 }
            goto L_0x000e
        L_0x0019:
            byte[] r0 = r1.toByteArray()     // Catch:{ all -> 0x0023 }
            if (r5 == 0) goto L_0x0022
            r5.close()
        L_0x0022:
            return r0
        L_0x0023:
            r0 = move-exception
            goto L_0x0029
        L_0x0025:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
        L_0x0029:
            if (r5 == 0) goto L_0x002e
            r5.close()
        L_0x002e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfm.zzb(java.net.HttpURLConnection):byte[]");
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: protected */
    public final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain HTTP connection");
        }
        if (this.zzalf != null && (openConnection instanceof HttpsURLConnection)) {
            ((HttpsURLConnection) openConnection).setSSLSocketFactory(this.zzalf);
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setReadTimeout(61000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzex() {
        NetworkInfo networkInfo;
        zzch();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    public final boolean zzgn() {
        return false;
    }

    public final /* bridge */ /* synthetic */ zzjz zzjf() {
        return super.zzjf();
    }

    public final /* bridge */ /* synthetic */ zzed zzjg() {
        return super.zzjg();
    }

    public final /* bridge */ /* synthetic */ zzek zzjh() {
        return super.zzjh();
    }
}
