package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.formats.a.C0132a;
import com.google.android.gms.ads.formats.a.b;
import com.google.android.gms.ads.formats.h;
import com.google.android.gms.ads.i;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@bu
public final class amg extends h {
    private final zzrr a;
    private final List<b> b = new ArrayList();
    private final amb c;
    private final i d = new i();
    private final C0132a e;

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a A[Catch:{ RemoteException -> 0x0055 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007a A[Catch:{ RemoteException -> 0x0087 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0020 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public amg(com.google.android.gms.internal.ads.zzrr r5) {
        /*
            r4 = this;
            r4.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.b = r0
            com.google.android.gms.ads.i r0 = new com.google.android.gms.ads.i
            r0.<init>()
            r4.d = r0
            r4.a = r5
            r5 = 0
            com.google.android.gms.internal.ads.zzrr r0 = r4.a     // Catch:{ RemoteException -> 0x0055 }
            java.util.List r0 = r0.getImages()     // Catch:{ RemoteException -> 0x0055 }
            if (r0 == 0) goto L_0x005b
            java.util.Iterator r0 = r0.iterator()     // Catch:{ RemoteException -> 0x0055 }
        L_0x0020:
            boolean r1 = r0.hasNext()     // Catch:{ RemoteException -> 0x0055 }
            if (r1 == 0) goto L_0x005b
            java.lang.Object r1 = r0.next()     // Catch:{ RemoteException -> 0x0055 }
            boolean r2 = r1 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x0055 }
            if (r2 == 0) goto L_0x0047
            android.os.IBinder r1 = (android.os.IBinder) r1     // Catch:{ RemoteException -> 0x0055 }
            if (r1 == 0) goto L_0x0047
            java.lang.String r2 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)     // Catch:{ RemoteException -> 0x0055 }
            boolean r3 = r2 instanceof com.google.android.gms.internal.ads.zzpw     // Catch:{ RemoteException -> 0x0055 }
            if (r3 == 0) goto L_0x0040
            r1 = r2
            com.google.android.gms.internal.ads.zzpw r1 = (com.google.android.gms.internal.ads.zzpw) r1     // Catch:{ RemoteException -> 0x0055 }
            goto L_0x0048
        L_0x0040:
            com.google.android.gms.internal.ads.zzpy r2 = new com.google.android.gms.internal.ads.zzpy     // Catch:{ RemoteException -> 0x0055 }
            r2.<init>(r1)     // Catch:{ RemoteException -> 0x0055 }
            r1 = r2
            goto L_0x0048
        L_0x0047:
            r1 = r5
        L_0x0048:
            if (r1 == 0) goto L_0x0020
            java.util.List<com.google.android.gms.ads.formats.a$b> r2 = r4.b     // Catch:{ RemoteException -> 0x0055 }
            com.google.android.gms.internal.ads.amb r3 = new com.google.android.gms.internal.ads.amb     // Catch:{ RemoteException -> 0x0055 }
            r3.<init>(r1)     // Catch:{ RemoteException -> 0x0055 }
            r2.add(r3)     // Catch:{ RemoteException -> 0x0055 }
            goto L_0x0020
        L_0x0055:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.ka.b(r1, r0)
        L_0x005b:
            com.google.android.gms.internal.ads.zzrr r0 = r4.a     // Catch:{ RemoteException -> 0x0069 }
            com.google.android.gms.internal.ads.zzpw r0 = r0.zzjz()     // Catch:{ RemoteException -> 0x0069 }
            if (r0 == 0) goto L_0x006f
            com.google.android.gms.internal.ads.amb r1 = new com.google.android.gms.internal.ads.amb     // Catch:{ RemoteException -> 0x0069 }
            r1.<init>(r0)     // Catch:{ RemoteException -> 0x0069 }
            goto L_0x0070
        L_0x0069:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.ka.b(r1, r0)
        L_0x006f:
            r1 = r5
        L_0x0070:
            r4.c = r1
            com.google.android.gms.internal.ads.zzrr r0 = r4.a     // Catch:{ RemoteException -> 0x0087 }
            com.google.android.gms.internal.ads.zzps r0 = r0.zzkf()     // Catch:{ RemoteException -> 0x0087 }
            if (r0 == 0) goto L_0x008d
            com.google.android.gms.internal.ads.ama r0 = new com.google.android.gms.internal.ads.ama     // Catch:{ RemoteException -> 0x0087 }
            com.google.android.gms.internal.ads.zzrr r1 = r4.a     // Catch:{ RemoteException -> 0x0087 }
            com.google.android.gms.internal.ads.zzps r1 = r1.zzkf()     // Catch:{ RemoteException -> 0x0087 }
            r0.<init>(r1)     // Catch:{ RemoteException -> 0x0087 }
            r5 = r0
            goto L_0x008d
        L_0x0087:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.ka.b(r1, r0)
        L_0x008d:
            r4.e = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.amg.<init>(com.google.android.gms.internal.ads.zzrr):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public final IObjectWrapper k() {
        try {
            return this.a.zzka();
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final String a() {
        try {
            return this.a.getHeadline();
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final List<b> b() {
        return this.b;
    }

    public final String c() {
        try {
            return this.a.getBody();
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final b d() {
        return this.c;
    }

    public final String e() {
        try {
            return this.a.getCallToAction();
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final String f() {
        try {
            return this.a.getAdvertiser();
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final Double g() {
        try {
            double starRating = this.a.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final String h() {
        try {
            return this.a.getStore();
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final String i() {
        try {
            return this.a.getPrice();
        } catch (RemoteException e2) {
            ka.b("", e2);
            return null;
        }
    }

    public final i j() {
        try {
            if (this.a.getVideoController() != null) {
                this.d.a(this.a.getVideoController());
            }
        } catch (RemoteException e2) {
            ka.b("Exception occurred while getting video controller", e2);
        }
        return this.d;
    }

    public final Object l() {
        try {
            IObjectWrapper zzke = this.a.zzke();
            if (zzke != null) {
                return ObjectWrapper.unwrap(zzke);
            }
        } catch (RemoteException e2) {
            ka.b("", e2);
        }
        return null;
    }
}
