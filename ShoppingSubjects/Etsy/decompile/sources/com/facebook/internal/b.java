package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: AttributionIdentifiers */
public class b {
    private static final String a = b.class.getCanonicalName();
    private static b g;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private long f;

    /* compiled from: AttributionIdentifiers */
    private static final class a implements IInterface {
        private IBinder a;

        a(IBinder iBinder) {
            this.a = iBinder;
        }

        public IBinder asBinder() {
            return this.a;
        }

        public String a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean b() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* renamed from: com.facebook.internal.b$b reason: collision with other inner class name */
    /* compiled from: AttributionIdentifiers */
    private static final class C0121b implements ServiceConnection {
        private AtomicBoolean a;
        private final BlockingQueue<IBinder> b;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private C0121b() {
            this.a = new AtomicBoolean(false);
            this.b = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.b.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }
        }

        public IBinder a() throws InterruptedException {
            if (!this.a.compareAndSet(true, true)) {
                return (IBinder) this.b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static b b(Context context) {
        b c2 = c(context);
        if (c2 != null) {
            return c2;
        }
        b d2 = d(context);
        return d2 == null ? new b() : d2;
    }

    private static b c(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method a2 = z.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
            if (a2 == null) {
                return null;
            }
            Object a3 = z.a((Object) null, a2, context);
            if (a3 instanceof Integer) {
                if (((Integer) a3).intValue() == 0) {
                    Method a4 = z.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
                    if (a4 == null) {
                        return null;
                    }
                    Object a5 = z.a((Object) null, a4, context);
                    if (a5 == null) {
                        return null;
                    }
                    Method a6 = z.a(a5.getClass(), "getId", (Class<?>[]) new Class[0]);
                    Method a7 = z.a(a5.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
                    if (a6 != null) {
                        if (a7 != null) {
                            b bVar = new b();
                            bVar.c = (String) z.a(a5, a6, new Object[0]);
                            bVar.e = ((Boolean) z.a(a5, a7, new Object[0])).booleanValue();
                            return bVar;
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e2) {
            z.a("android_id", e2);
            return null;
        }
    }

    private static b d(Context context) {
        C0121b bVar = new C0121b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, bVar, 1)) {
            try {
                a aVar = new a(bVar.a());
                b bVar2 = new b();
                bVar2.c = aVar.a();
                bVar2.e = aVar.b();
                return bVar2;
            } catch (Exception e2) {
                z.a("android_id", e2);
            } finally {
                context.unbindService(bVar);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006e A[Catch:{ Exception -> 0x00dc, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0072 A[Catch:{ Exception -> 0x00dc, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0077 A[Catch:{ Exception -> 0x00dc, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.b a(android.content.Context r10) {
        /*
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 != r1) goto L_0x0011
            java.lang.String r0 = a
            java.lang.String r1 = "getAttributionIdentifiers should not be called from the main thread"
            android.util.Log.e(r0, r1)
        L_0x0011:
            com.facebook.internal.b r0 = g
            if (r0 == 0) goto L_0x0029
            long r0 = java.lang.System.currentTimeMillis()
            com.facebook.internal.b r2 = g
            long r2 = r2.f
            long r4 = r0 - r2
            r0 = 3600000(0x36ee80, double:1.7786363E-317)
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0029
            com.facebook.internal.b r10 = g
            return r10
        L_0x0029:
            com.facebook.internal.b r0 = b(r10)
            r1 = 3
            r2 = 0
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            java.lang.String r1 = "aid"
            r3 = 0
            r5[r3] = r1     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            r1 = 1
            java.lang.String r4 = "androidid"
            r5[r1] = r4     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            r1 = 2
            java.lang.String r4 = "limit_tracking"
            r5[r1] = r4     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            java.lang.String r4 = "com.facebook.katana.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r1 = r1.resolveContentProvider(r4, r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            if (r1 == 0) goto L_0x0054
            java.lang.String r1 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
        L_0x0052:
            r4 = r1
            goto L_0x0068
        L_0x0054:
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            java.lang.String r4 = "com.facebook.wakizashi.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r1 = r1.resolveContentProvider(r4, r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            if (r1 == 0) goto L_0x0067
            java.lang.String r1 = "content://com.facebook.wakizashi.provider.AttributionIdProvider"
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            goto L_0x0052
        L_0x0067:
            r4 = r2
        L_0x0068:
            java.lang.String r1 = e(r10)     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            if (r1 == 0) goto L_0x0070
            r0.d = r1     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
        L_0x0070:
            if (r4 != 0) goto L_0x0077
            com.facebook.internal.b r10 = a(r0)     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            return r10
        L_0x0077:
            android.content.ContentResolver r3 = r10.getContentResolver()     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00dc, all -> 0x00da }
            if (r10 == 0) goto L_0x00d0
            boolean r1 = r10.moveToFirst()     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            if (r1 != 0) goto L_0x008b
            goto L_0x00d0
        L_0x008b:
            java.lang.String r1 = "aid"
            int r1 = r10.getColumnIndex(r1)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            java.lang.String r3 = "androidid"
            int r3 = r10.getColumnIndex(r3)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            java.lang.String r4 = "limit_tracking"
            int r4 = r10.getColumnIndex(r4)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            java.lang.String r1 = r10.getString(r1)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            r0.b = r1     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            if (r3 <= 0) goto L_0x00bd
            if (r4 <= 0) goto L_0x00bd
            java.lang.String r1 = r0.b()     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            if (r1 != 0) goto L_0x00bd
            java.lang.String r1 = r10.getString(r3)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            r0.c = r1     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            java.lang.String r1 = r10.getString(r4)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            r0.e = r1     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        L_0x00bd:
            if (r10 == 0) goto L_0x00c2
            r10.close()
        L_0x00c2:
            com.facebook.internal.b r10 = a(r0)
            return r10
        L_0x00c7:
            r0 = move-exception
            r2 = r10
            r10 = r0
            goto L_0x0100
        L_0x00cb:
            r0 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x00de
        L_0x00d0:
            com.facebook.internal.b r0 = a(r0)     // Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
            if (r10 == 0) goto L_0x00d9
            r10.close()
        L_0x00d9:
            return r0
        L_0x00da:
            r10 = move-exception
            goto L_0x0100
        L_0x00dc:
            r10 = move-exception
            r0 = r2
        L_0x00de:
            java.lang.String r1 = a     // Catch:{ all -> 0x00fe }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fe }
            r3.<init>()     // Catch:{ all -> 0x00fe }
            java.lang.String r4 = "Caught unexpected exception in getAttributionId(): "
            r3.append(r4)     // Catch:{ all -> 0x00fe }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x00fe }
            r3.append(r10)     // Catch:{ all -> 0x00fe }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x00fe }
            com.facebook.internal.z.b(r1, r10)     // Catch:{ all -> 0x00fe }
            if (r0 == 0) goto L_0x00fd
            r0.close()
        L_0x00fd:
            return r2
        L_0x00fe:
            r10 = move-exception
            r2 = r0
        L_0x0100:
            if (r2 == 0) goto L_0x0105
            r2.close()
        L_0x0105:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.b.a(android.content.Context):com.facebook.internal.b");
    }

    private static b a(b bVar) {
        bVar.f = System.currentTimeMillis();
        g = bVar;
        return bVar;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }

    @Nullable
    private static String e(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
}
