package com.threatmetrix.TrustDefender.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.threatmetrix.TrustDefender.internal.P.O;
import com.threatmetrix.TrustDefender.internal.XU.E;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class U implements E {
    /* access modifiers changed from: private */

    /* renamed from: if reason: not valid java name */
    public static final String f544if = TL.m331if(U.class);

    /* renamed from: for reason: not valid java name */
    private final L f545for = new L(this.f546int);

    /* renamed from: int reason: not valid java name */
    private final CountDownLatch f546int = new CountDownLatch(1);

    static class L implements ServiceConnection {

        /* renamed from: for reason: not valid java name */
        private final CountDownLatch f547for;

        /* renamed from: if reason: not valid java name */
        volatile IBinder f548if = null;

        L(CountDownLatch countDownLatch) {
            this.f547for = countDownLatch;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                this.f548if = iBinder;
                this.f547for.countDown();
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            this.f548if = null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:0x001a, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
            com.threatmetrix.TrustDefender.internal.U.m342do();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
            r4 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
            r1.recycle();
            r0.recycle();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001c */
        /* renamed from: new reason: not valid java name */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static java.lang.String m345new(android.os.IBinder r4) {
            /*
                android.os.Parcel r0 = android.os.Parcel.obtain()
                android.os.Parcel r1 = android.os.Parcel.obtain()
                java.lang.String r2 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService"
                r0.writeInterfaceToken(r2)     // Catch:{ Exception -> 0x001c }
                r2 = 1
                r3 = 0
                r4.transact(r2, r0, r1, r3)     // Catch:{ Exception -> 0x001c }
                r1.readException()     // Catch:{ Exception -> 0x001c }
                java.lang.String r4 = r1.readString()     // Catch:{ Exception -> 0x001c }
                goto L_0x0020
            L_0x001a:
                r4 = move-exception
                goto L_0x0027
            L_0x001c:
                com.threatmetrix.TrustDefender.internal.U.f544if     // Catch:{ all -> 0x001a }
                r4 = 0
            L_0x0020:
                r1.recycle()
                r0.recycle()
                return r4
            L_0x0027:
                r1.recycle()
                r0.recycle()
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.U.L.m345new(android.os.IBinder):java.lang.String");
        }
    }

    /* renamed from: for reason: not valid java name */
    public final boolean m343for(O o) {
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        return o.f487for.bindService(intent, this.f545for, 1);
    }

    /* renamed from: if reason: not valid java name */
    public final String m344if(int i) {
        try {
            if (this.f546int.await((long) i, TimeUnit.MILLISECONDS)) {
                IBinder iBinder = this.f545for.f548if;
                if (iBinder != null) {
                    return L.m345new(iBinder);
                }
                return null;
            }
        } catch (InterruptedException unused) {
        } catch (Exception e) {
            TL.m338new(f544if, e.toString());
        }
        return null;
    }
}
