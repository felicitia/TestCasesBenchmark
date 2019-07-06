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
import com.facebook.FacebookException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static final class GoogleAdInfo implements IInterface {
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
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

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.queue.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return (IBinder) this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
        return androidIdViaService == null ? new AttributionIdentifiers() : androidIdViaService;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
            if (methodQuietly == null) {
                return null;
            }
            Object invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly, context);
            if (invokeMethodQuietly instanceof Integer) {
                if (((Integer) invokeMethodQuietly).intValue() == 0) {
                    Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
                    if (methodQuietly2 == null) {
                        return null;
                    }
                    Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly2, context);
                    if (invokeMethodQuietly2 == null) {
                        return null;
                    }
                    Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "getId", (Class<?>[]) new Class[0]);
                    Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
                    if (methodQuietly3 != null) {
                        if (methodQuietly4 != null) {
                            AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                            attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly3, new Object[0]);
                            attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly4, new Object[0])).booleanValue();
                            return attributionIdentifiers;
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                Utility.logd("android_id", e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005d A[Catch:{ Exception -> 0x00cb, all -> 0x00c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061 A[Catch:{ Exception -> 0x00cb, all -> 0x00c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0066 A[Catch:{ Exception -> 0x00cb, all -> 0x00c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r10) {
        /*
            com.facebook.internal.AttributionIdentifiers r0 = recentlyFetchedIdentifiers
            if (r0 == 0) goto L_0x0018
            long r0 = java.lang.System.currentTimeMillis()
            com.facebook.internal.AttributionIdentifiers r2 = recentlyFetchedIdentifiers
            long r2 = r2.fetchTime
            long r4 = r0 - r2
            r0 = 3600000(0x36ee80, double:1.7786363E-317)
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0018
            com.facebook.internal.AttributionIdentifiers r10 = recentlyFetchedIdentifiers
            return r10
        L_0x0018:
            com.facebook.internal.AttributionIdentifiers r0 = getAndroidId(r10)
            r1 = 3
            r2 = 0
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            java.lang.String r1 = "aid"
            r3 = 0
            r5[r3] = r1     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            r1 = 1
            java.lang.String r4 = "androidid"
            r5[r1] = r4     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            r1 = 2
            java.lang.String r4 = "limit_tracking"
            r5[r1] = r4     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            java.lang.String r4 = "com.facebook.katana.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r1 = r1.resolveContentProvider(r4, r3)     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            if (r1 == 0) goto L_0x0043
            java.lang.String r1 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
        L_0x0041:
            r4 = r1
            goto L_0x0057
        L_0x0043:
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            java.lang.String r4 = "com.facebook.wakizashi.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r1 = r1.resolveContentProvider(r4, r3)     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            if (r1 == 0) goto L_0x0056
            java.lang.String r1 = "content://com.facebook.wakizashi.provider.AttributionIdProvider"
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            goto L_0x0041
        L_0x0056:
            r4 = r2
        L_0x0057:
            java.lang.String r1 = getInstallerPackageName(r10)     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            if (r1 == 0) goto L_0x005f
            r0.androidInstallerPackage = r1     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
        L_0x005f:
            if (r4 != 0) goto L_0x0066
            com.facebook.internal.AttributionIdentifiers r10 = cacheAndReturnIdentifiers(r0)     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            return r10
        L_0x0066:
            android.content.ContentResolver r3 = r10.getContentResolver()     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00cb, all -> 0x00c9 }
            if (r10 == 0) goto L_0x00bf
            boolean r1 = r10.moveToFirst()     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            if (r1 != 0) goto L_0x007a
            goto L_0x00bf
        L_0x007a:
            java.lang.String r1 = "aid"
            int r1 = r10.getColumnIndex(r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            java.lang.String r3 = "androidid"
            int r3 = r10.getColumnIndex(r3)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            java.lang.String r4 = "limit_tracking"
            int r4 = r10.getColumnIndex(r4)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            java.lang.String r1 = r10.getString(r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            r0.attributionId = r1     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            if (r3 <= 0) goto L_0x00ac
            if (r4 <= 0) goto L_0x00ac
            java.lang.String r1 = r0.getAndroidAdvertiserId()     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            if (r1 != 0) goto L_0x00ac
            java.lang.String r1 = r10.getString(r3)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            r0.androidAdvertiserId = r1     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            java.lang.String r1 = r10.getString(r4)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            r0.limitTracking = r1     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
        L_0x00ac:
            if (r10 == 0) goto L_0x00b1
            r10.close()
        L_0x00b1:
            com.facebook.internal.AttributionIdentifiers r10 = cacheAndReturnIdentifiers(r0)
            return r10
        L_0x00b6:
            r0 = move-exception
            r2 = r10
            r10 = r0
            goto L_0x00ef
        L_0x00ba:
            r0 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x00cd
        L_0x00bf:
            com.facebook.internal.AttributionIdentifiers r0 = cacheAndReturnIdentifiers(r0)     // Catch:{ Exception -> 0x00ba, all -> 0x00b6 }
            if (r10 == 0) goto L_0x00c8
            r10.close()
        L_0x00c8:
            return r0
        L_0x00c9:
            r10 = move-exception
            goto L_0x00ef
        L_0x00cb:
            r10 = move-exception
            r0 = r2
        L_0x00cd:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x00ed }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ed }
            r3.<init>()     // Catch:{ all -> 0x00ed }
            java.lang.String r4 = "Caught unexpected exception in getAttributionId(): "
            r3.append(r4)     // Catch:{ all -> 0x00ed }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x00ed }
            r3.append(r10)     // Catch:{ all -> 0x00ed }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x00ed }
            android.util.Log.d(r1, r10)     // Catch:{ all -> 0x00ed }
            if (r0 == 0) goto L_0x00ec
            r0.close()
        L_0x00ec:
            return r2
        L_0x00ed:
            r10 = move-exception
            r2 = r0
        L_0x00ef:
            if (r2 == 0) goto L_0x00f4
            r2.close()
        L_0x00f4:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
}
