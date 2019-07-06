package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
final class pw implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    @VisibleForTesting
    private px a;
    private final String b;
    private final String c;
    private final LinkedBlockingQueue<vy> d;
    private final HandlerThread e = new HandlerThread("GassClient");

    public pw(Context context, String str, String str2) {
        this.b = str;
        this.c = str2;
        this.e.start();
        this.a = new px(context, this.e.getLooper(), this, this);
        this.d = new LinkedBlockingQueue<>();
        this.a.checkAvailabilityAndConnect();
    }

    private final zzatx a() {
        try {
            return this.a.a();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }

    private final void b() {
        if (this.a == null) {
            return;
        }
        if (this.a.isConnected() || this.a.isConnecting()) {
            this.a.disconnect();
        }
    }

    @VisibleForTesting
    private static vy c() {
        vy vyVar = new vy();
        vyVar.k = Long.valueOf(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
        return vyVar;
    }

    public final vy a(int i) {
        vy vyVar;
        try {
            vyVar = (vy) this.d.poll(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            vyVar = null;
        }
        return vyVar == null ? c() : vyVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        b();
        r3.e.quit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0027 */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0025 A[ExcHandler: all (r4v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:2:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzatx r4 = r3.a()
            if (r4 == 0) goto L_0x003a
            com.google.android.gms.internal.ads.zzatt r0 = new com.google.android.gms.internal.ads.zzatt     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            java.lang.String r1 = r3.b     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            java.lang.String r2 = r3.c     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            com.google.android.gms.internal.ads.zzatv r4 = r4.zza(r0)     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            com.google.android.gms.internal.ads.vy r4 = r4.zzwe()     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.vy> r0 = r3.d     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
            r0.put(r4)     // Catch:{ Throwable -> 0x0027, all -> 0x0025 }
        L_0x001c:
            r3.b()
            android.os.HandlerThread r4 = r3.e
            r4.quit()
            return
        L_0x0025:
            r4 = move-exception
            goto L_0x0031
        L_0x0027:
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.vy> r4 = r3.d     // Catch:{ InterruptedException -> 0x001c, all -> 0x0025 }
            com.google.android.gms.internal.ads.vy r0 = c()     // Catch:{ InterruptedException -> 0x001c, all -> 0x0025 }
            r4.put(r0)     // Catch:{ InterruptedException -> 0x001c, all -> 0x0025 }
            goto L_0x001c
        L_0x0031:
            r3.b()
            android.os.HandlerThread r0 = r3.e
            r0.quit()
            throw r4
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.pw.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.d.put(c());
        } catch (InterruptedException unused) {
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            this.d.put(c());
        } catch (InterruptedException unused) {
        }
    }
}
