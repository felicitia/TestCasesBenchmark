package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.i;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class f {
    private static final j<Boolean> a = i.a().a("gcm_iid_use_messenger_ipc", true);
    private static String b = null;
    private static boolean c = false;
    private static int d;
    private static int e;
    private static int f;
    private static BroadcastReceiver g;
    private Context h;
    private Map<String, Object> i = new ArrayMap();
    private Messenger j;
    private Messenger k;
    private MessengerCompat l;
    private PendingIntent m;

    public f(Context context) {
        this.h = context;
    }

    private static synchronized String a() {
        String num;
        synchronized (f.class) {
            int i2 = f;
            f = i2 + 1;
            num = Integer.toString(i2);
        }
        return num;
    }

    public static String a(Context context) {
        boolean z;
        if (b != null) {
            return b;
        }
        d = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        boolean z2 = true;
        if (!PlatformVersion.isAtLeastO()) {
            Iterator it = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
            while (true) {
                if (it.hasNext()) {
                    if (a(packageManager, ((ResolveInfo) it.next()).serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                        c = false;
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return b;
            }
        }
        Iterator it2 = packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
        while (true) {
            if (it2.hasNext()) {
                if (a(packageManager, ((ResolveInfo) it2.next()).activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                    c = true;
                    break;
                }
            } else {
                z2 = false;
                break;
            }
        }
        if (z2) {
            return b;
        }
        Log.w("InstanceID", "Failed to resolve IID implementation package, falling back");
        if (a(packageManager, "com.google.android.gms")) {
            c = PlatformVersion.isAtLeastO();
            return b;
        } else if (PlatformVersion.isAtLeastLollipop() || !a(packageManager, "com.google.android.gsf")) {
            Log.w("InstanceID", "Google Play services is missing, unable to get tokens");
            return null;
        } else {
            c = false;
            return b;
        }
    }

    static String a(Bundle bundle) throws IOException {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String string = bundle.getString("registration_id");
        if (string == null) {
            string = bundle.getString("unregistered");
        }
        if (string != null) {
            return string;
        }
        String string2 = bundle.getString("error");
        if (string2 != null) {
            throw new IOException(string2);
        }
        String valueOf = String.valueOf(bundle);
        StringBuilder sb = new StringBuilder(29 + String.valueOf(valueOf).length());
        sb.append("Unexpected response from GCM ");
        sb.append(valueOf);
        Log.w("InstanceID", sb.toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    private static void a(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e2) {
                String valueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(24 + String.valueOf(valueOf).length());
                sb.append("Failed to send response ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
            }
        }
    }

    private final void a(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.i.get(str);
            this.i.put(str, obj);
            a(obj2, obj);
        }
    }

    private static boolean a(PackageManager packageManager, String str) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            b = applicationInfo.packageName;
            e = applicationInfo.uid;
            return true;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    private static boolean a(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return a(packageManager, str);
        }
        StringBuilder sb = new StringBuilder(56 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("Possible malicious package ");
        sb.append(str);
        sb.append(" declares ");
        sb.append(str2);
        sb.append(" without permission");
        Log.w("InstanceID", sb.toString());
        return false;
    }

    private static int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a(context), 0).versionCode;
        } catch (NameNotFoundException unused) {
            return -1;
        }
    }

    private final Bundle b(Bundle bundle) throws IOException {
        Bundle c2 = c(bundle);
        if (c2 == null || !c2.containsKey("google.messenger")) {
            return c2;
        }
        Bundle c3 = c(bundle);
        if (c3 == null || !c3.containsKey("google.messenger")) {
            return c3;
        }
        return null;
    }

    private final synchronized void b(Intent intent) {
        if (this.m == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.m = PendingIntent.getBroadcast(this.h, 0, intent2, 0);
        }
        intent.putExtra("app", this.m);
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x018c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.os.Bundle c(android.os.Bundle r9) throws java.io.IOException {
        /*
            r8 = this;
            android.os.ConditionVariable r0 = new android.os.ConditionVariable
            r0.<init>()
            java.lang.String r1 = a()
            java.lang.Class r2 = r8.getClass()
            monitor-enter(r2)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r8.i     // Catch:{ all -> 0x01d6 }
            r3.put(r1, r0)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r2)     // Catch:{ all -> 0x01d6 }
            android.os.Messenger r2 = r8.j
            if (r2 != 0) goto L_0x002d
            android.content.Context r2 = r8.h
            a(r2)
            android.os.Messenger r2 = new android.os.Messenger
            com.google.android.gms.iid.g r3 = new com.google.android.gms.iid.g
            android.os.Looper r4 = android.os.Looper.getMainLooper()
            r3.<init>(r8, r4)
            r2.<init>(r3)
            r8.j = r2
        L_0x002d:
            java.lang.String r2 = b
            if (r2 != 0) goto L_0x0039
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r0 = "MISSING_INSTANCEID_SERVICE"
            r9.<init>(r0)
            throw r9
        L_0x0039:
            android.content.Intent r2 = new android.content.Intent
            boolean r3 = c
            if (r3 == 0) goto L_0x0042
            java.lang.String r3 = "com.google.iid.TOKEN_REQUEST"
            goto L_0x0044
        L_0x0042:
            java.lang.String r3 = "com.google.android.c2dm.intent.REGISTER"
        L_0x0044:
            r2.<init>(r3)
            java.lang.String r3 = b
            r2.setPackage(r3)
            r2.putExtras(r9)
            r8.b(r2)
            java.lang.String r9 = "kid"
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            r4 = 5
            int r3 = r3 + r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "|ID|"
            r5.append(r3)
            r5.append(r1)
            java.lang.String r3 = "|"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r2.putExtra(r9, r3)
            java.lang.String r9 = "X-kid"
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r4 = r4 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r4)
            java.lang.String r4 = "|ID|"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = "|"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.putExtra(r9, r3)
            java.lang.String r9 = "com.google.android.gsf"
            java.lang.String r3 = b
            boolean r9 = r9.equals(r3)
            java.lang.String r3 = "useGsf"
            java.lang.String r3 = r2.getStringExtra(r3)
            if (r3 == 0) goto L_0x00b1
            java.lang.String r9 = "1"
            boolean r9 = r9.equals(r3)
        L_0x00b1:
            java.lang.String r3 = "InstanceID"
            r4 = 3
            boolean r3 = android.util.Log.isLoggable(r3, r4)
            if (r3 == 0) goto L_0x00e3
            java.lang.String r3 = "InstanceID"
            android.os.Bundle r5 = r2.getExtras()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r6 = 8
            java.lang.String r7 = java.lang.String.valueOf(r5)
            int r7 = r7.length()
            int r6 = r6 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Sending "
            r7.append(r6)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            android.util.Log.d(r3, r5)
        L_0x00e3:
            android.os.Messenger r3 = r8.k
            if (r3 == 0) goto L_0x010a
            java.lang.String r3 = "google.messenger"
            android.os.Messenger r5 = r8.j
            r2.putExtra(r3, r5)
            android.os.Message r3 = android.os.Message.obtain()
            r3.obj = r2
            android.os.Messenger r5 = r8.k     // Catch:{ RemoteException -> 0x00fb }
            r5.send(r3)     // Catch:{ RemoteException -> 0x00fb }
            goto L_0x0182
        L_0x00fb:
            java.lang.String r3 = "InstanceID"
            boolean r3 = android.util.Log.isLoggable(r3, r4)
            if (r3 == 0) goto L_0x010a
            java.lang.String r3 = "InstanceID"
            java.lang.String r5 = "Messenger failed, fallback to startService"
            android.util.Log.d(r3, r5)
        L_0x010a:
            if (r9 == 0) goto L_0x0146
            monitor-enter(r8)
            android.content.BroadcastReceiver r9 = g     // Catch:{ all -> 0x0143 }
            if (r9 != 0) goto L_0x0141
            com.google.android.gms.iid.h r9 = new com.google.android.gms.iid.h     // Catch:{ all -> 0x0143 }
            r9.<init>(r8)     // Catch:{ all -> 0x0143 }
            g = r9     // Catch:{ all -> 0x0143 }
            java.lang.String r9 = "InstanceID"
            boolean r9 = android.util.Log.isLoggable(r9, r4)     // Catch:{ all -> 0x0143 }
            if (r9 == 0) goto L_0x0127
            java.lang.String r9 = "InstanceID"
            java.lang.String r3 = "Registered GSF callback receiver"
            android.util.Log.d(r9, r3)     // Catch:{ all -> 0x0143 }
        L_0x0127:
            android.content.IntentFilter r9 = new android.content.IntentFilter     // Catch:{ all -> 0x0143 }
            java.lang.String r3 = "com.google.android.c2dm.intent.REGISTRATION"
            r9.<init>(r3)     // Catch:{ all -> 0x0143 }
            android.content.Context r3 = r8.h     // Catch:{ all -> 0x0143 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ all -> 0x0143 }
            r9.addCategory(r3)     // Catch:{ all -> 0x0143 }
            android.content.Context r3 = r8.h     // Catch:{ all -> 0x0143 }
            android.content.BroadcastReceiver r4 = g     // Catch:{ all -> 0x0143 }
            java.lang.String r5 = "com.google.android.c2dm.permission.SEND"
            r6 = 0
            r3.registerReceiver(r4, r9, r5, r6)     // Catch:{ all -> 0x0143 }
        L_0x0141:
            monitor-exit(r8)     // Catch:{ all -> 0x0143 }
            goto L_0x0177
        L_0x0143:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0143 }
            throw r9
        L_0x0146:
            java.lang.String r9 = "google.messenger"
            android.os.Messenger r3 = r8.j
            r2.putExtra(r9, r3)
            java.lang.String r9 = "messenger2"
            java.lang.String r3 = "1"
            r2.putExtra(r9, r3)
            com.google.android.gms.iid.MessengerCompat r9 = r8.l
            if (r9 == 0) goto L_0x0173
            android.os.Message r9 = android.os.Message.obtain()
            r9.obj = r2
            com.google.android.gms.iid.MessengerCompat r3 = r8.l     // Catch:{ RemoteException -> 0x0164 }
            r3.send(r9)     // Catch:{ RemoteException -> 0x0164 }
            goto L_0x0182
        L_0x0164:
            java.lang.String r9 = "InstanceID"
            boolean r9 = android.util.Log.isLoggable(r9, r4)
            if (r9 == 0) goto L_0x0173
            java.lang.String r9 = "InstanceID"
            java.lang.String r3 = "Messenger failed, fallback to startService"
            android.util.Log.d(r9, r3)
        L_0x0173:
            boolean r9 = c
            if (r9 == 0) goto L_0x017d
        L_0x0177:
            android.content.Context r9 = r8.h
            r9.sendBroadcast(r2)
            goto L_0x0182
        L_0x017d:
            android.content.Context r9 = r8.h
            r9.startService(r2)
        L_0x0182:
            r2 = 30000(0x7530, double:1.4822E-319)
            r0.block(r2)
            java.lang.Class r9 = r8.getClass()
            monitor-enter(r9)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r8.i     // Catch:{ all -> 0x01d3 }
            java.lang.Object r0 = r0.remove(r1)     // Catch:{ all -> 0x01d3 }
            boolean r1 = r0 instanceof android.os.Bundle     // Catch:{ all -> 0x01d3 }
            if (r1 == 0) goto L_0x019a
            android.os.Bundle r0 = (android.os.Bundle) r0     // Catch:{ all -> 0x01d3 }
            monitor-exit(r9)     // Catch:{ all -> 0x01d3 }
            return r0
        L_0x019a:
            boolean r1 = r0 instanceof java.lang.String     // Catch:{ all -> 0x01d3 }
            if (r1 == 0) goto L_0x01a6
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x01d3 }
            r1.<init>(r0)     // Catch:{ all -> 0x01d3 }
            throw r1     // Catch:{ all -> 0x01d3 }
        L_0x01a6:
            java.lang.String r1 = "InstanceID"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01d3 }
            r2 = 12
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01d3 }
            int r3 = r3.length()     // Catch:{ all -> 0x01d3 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d3 }
            r3.<init>(r2)     // Catch:{ all -> 0x01d3 }
            java.lang.String r2 = "No response "
            r3.append(r2)     // Catch:{ all -> 0x01d3 }
            r3.append(r0)     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x01d3 }
            android.util.Log.w(r1, r0)     // Catch:{ all -> 0x01d3 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01d3 }
            java.lang.String r1 = "TIMEOUT"
            r0.<init>(r1)     // Catch:{ all -> 0x01d3 }
            throw r0     // Catch:{ all -> 0x01d3 }
        L_0x01d3:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x01d3 }
            throw r0
        L_0x01d6:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01d6 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.f.c(android.os.Bundle):android.os.Bundle");
    }

    /* access modifiers changed from: 0000 */
    public final Bundle a(Bundle bundle, KeyPair keyPair) throws IOException {
        int b2 = b(this.h);
        bundle.putString("gmsv", Integer.toString(b2));
        bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(a.a(this.h)));
        bundle.putString("app_ver_name", a.b(this.h));
        bundle.putString("cliv", "iid-12451000");
        bundle.putString("appid", a.a(keyPair));
        if (b2 < 12000000 || !((Boolean) a.a()).booleanValue()) {
            return b(bundle);
        }
        try {
            return (Bundle) i.a(new t(this.h).a(1, bundle));
        } catch (InterruptedException | ExecutionException e2) {
            if (Log.isLoggable("InstanceID", 3)) {
                String valueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(22 + String.valueOf(valueOf).length());
                sb.append("Error making request: ");
                sb.append(valueOf);
                Log.d("InstanceID", sb.toString());
            }
            if (!(e2.getCause() instanceof zzaa) || ((zzaa) e2.getCause()).getErrorCode() != 4) {
                return null;
            }
            return b(bundle);
        }
    }

    public final void a(Intent intent) {
        String str;
        if (intent == null) {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Unexpected response: null");
            }
            return;
        }
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action) || "com.google.android.gms.iid.InstanceID".equals(action)) {
            String stringExtra = intent.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                String stringExtra2 = intent.getStringExtra("error");
                if (stringExtra2 == null) {
                    String valueOf = String.valueOf(intent.getExtras());
                    StringBuilder sb = new StringBuilder(49 + String.valueOf(valueOf).length());
                    sb.append("Unexpected response, no error or registration id ");
                    sb.append(valueOf);
                    Log.w("InstanceID", sb.toString());
                    return;
                }
                if (Log.isLoggable("InstanceID", 3)) {
                    String str2 = "InstanceID";
                    String str3 = "Received InstanceID error ";
                    String valueOf2 = String.valueOf(stringExtra2);
                    Log.d(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                }
                String str4 = null;
                if (stringExtra2.startsWith("|")) {
                    String[] split = stringExtra2.split("\\|");
                    if (!"ID".equals(split[1])) {
                        String str5 = "InstanceID";
                        String str6 = "Unexpected structured response ";
                        String valueOf3 = String.valueOf(stringExtra2);
                        Log.w(str5, valueOf3.length() != 0 ? str6.concat(valueOf3) : new String(str6));
                    }
                    if (split.length > 2) {
                        String str7 = split[2];
                        str = split[3];
                        if (str.startsWith(Draft.IMAGE_DELIMITER)) {
                            str = str.substring(1);
                        }
                        str4 = str7;
                    } else {
                        str = "UNKNOWN";
                    }
                    stringExtra2 = str;
                    intent.putExtra("error", stringExtra2);
                }
                if (str4 == null) {
                    synchronized (getClass()) {
                        for (String str8 : this.i.keySet()) {
                            Object obj = this.i.get(str8);
                            this.i.put(str8, stringExtra2);
                            a(obj, (Object) stringExtra2);
                        }
                    }
                    return;
                }
                a(str4, (Object) stringExtra2);
                return;
            }
            Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
            if (!matcher.matches()) {
                if (Log.isLoggable("InstanceID", 3)) {
                    String str9 = "InstanceID";
                    String str10 = "Unexpected response string: ";
                    String valueOf4 = String.valueOf(stringExtra);
                    Log.d(str9, valueOf4.length() != 0 ? str10.concat(valueOf4) : new String(str10));
                }
                return;
            }
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            Bundle extras = intent.getExtras();
            extras.putString("registration_id", group2);
            a(group, (Object) extras);
            return;
        }
        if (Log.isLoggable("InstanceID", 3)) {
            String str11 = "InstanceID";
            String str12 = "Unexpected response ";
            String valueOf5 = String.valueOf(intent.getAction());
            Log.d(str11, valueOf5.length() != 0 ? str12.concat(valueOf5) : new String(str12));
        }
    }

    public final void a(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.l = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.k = (Messenger) parcelableExtra;
                    }
                }
                a((Intent) message.obj);
                return;
            }
            Log.w("InstanceID", "Dropping invalid message");
        }
    }
}
