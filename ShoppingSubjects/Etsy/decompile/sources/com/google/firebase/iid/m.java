package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.tasks.g;
import com.google.android.gms.tasks.i;
import com.google.firebase.iid.zzi.a;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class m {
    private static int a;
    private static PendingIntent b;
    private final SimpleArrayMap<String, g<Bundle>> c = new SimpleArrayMap<>();
    private final Context d;
    private final g e;
    private Messenger f;
    private Messenger g;
    private zzi h;

    public m(Context context, g gVar) {
        this.d = context;
        this.e = gVar;
        this.f = new Messenger(new n(this, Looper.getMainLooper()));
    }

    private static synchronized String a() {
        String num;
        synchronized (m.class) {
            int i = a;
            a = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private static synchronized void a(Context context, Intent intent) {
        synchronized (m.class) {
            if (b == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                b = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", b);
        }
    }

    /* access modifiers changed from: private */
    public final void a(Message message) {
        String str;
        String str2;
        if (message == null || !(message.obj instanceof Intent)) {
            str = "FirebaseInstanceId";
            str2 = "Dropping invalid message";
        } else {
            Intent intent = (Intent) message.obj;
            intent.setExtrasClassLoader(new a());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzi) {
                    this.h = (zzi) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.g = (Messenger) parcelableExtra;
                }
            }
            Intent intent2 = (Intent) message.obj;
            String action = intent2.getAction();
            if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String str3 = "FirebaseInstanceId";
                    String str4 = "Unexpected response action: ";
                    String valueOf = String.valueOf(action);
                    Log.d(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                }
                return;
            }
            String stringExtra = intent2.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent2.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                String stringExtra2 = intent2.getStringExtra("error");
                if (stringExtra2 == null) {
                    String valueOf2 = String.valueOf(intent2.getExtras());
                    StringBuilder sb = new StringBuilder(49 + String.valueOf(valueOf2).length());
                    sb.append("Unexpected response, no error or registration id ");
                    sb.append(valueOf2);
                    Log.w("FirebaseInstanceId", sb.toString());
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String str5 = "FirebaseInstanceId";
                    String str6 = "Received InstanceID error ";
                    String valueOf3 = String.valueOf(stringExtra2);
                    Log.d(str5, valueOf3.length() != 0 ? str6.concat(valueOf3) : new String(str6));
                }
                if (stringExtra2.startsWith("|")) {
                    String[] split = stringExtra2.split("\\|");
                    if (split.length <= 2 || !"ID".equals(split[1])) {
                        str = "FirebaseInstanceId";
                        String str7 = "Unexpected structured response ";
                        String valueOf4 = String.valueOf(stringExtra2);
                        str2 = valueOf4.length() != 0 ? str7.concat(valueOf4) : new String(str7);
                    } else {
                        String str8 = split[2];
                        String str9 = split[3];
                        if (str9.startsWith(Draft.IMAGE_DELIMITER)) {
                            str9 = str9.substring(1);
                        }
                        a(str8, intent2.putExtra("error", str9).getExtras());
                        return;
                    }
                } else {
                    synchronized (this.c) {
                        for (int i = 0; i < this.c.size(); i++) {
                            a((String) this.c.keyAt(i), intent2.getExtras());
                        }
                    }
                    return;
                }
            } else {
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (!matcher.matches()) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        String str10 = "FirebaseInstanceId";
                        String str11 = "Unexpected response string: ";
                        String valueOf5 = String.valueOf(stringExtra);
                        Log.d(str10, valueOf5.length() != 0 ? str11.concat(valueOf5) : new String(str11));
                    }
                    return;
                }
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                Bundle extras = intent2.getExtras();
                extras.putString("registration_id", group2);
                a(group, extras);
                return;
            }
        }
        Log.w(str, str2);
    }

    private final void a(String str, Bundle bundle) {
        synchronized (this.c) {
            g gVar = (g) this.c.remove(str);
            if (gVar == null) {
                String str2 = "FirebaseInstanceId";
                String str3 = "Missing callback for ";
                String valueOf = String.valueOf(str);
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return;
            }
            gVar.a(bundle);
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

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.os.Bundle c(android.os.Bundle r9) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String r0 = a()
            com.google.android.gms.tasks.g r1 = new com.google.android.gms.tasks.g
            r1.<init>()
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.g<android.os.Bundle>> r2 = r8.c
            monitor-enter(r2)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.g<android.os.Bundle>> r3 = r8.c     // Catch:{ all -> 0x0124 }
            r3.put(r0, r1)     // Catch:{ all -> 0x0124 }
            monitor-exit(r2)     // Catch:{ all -> 0x0124 }
            com.google.firebase.iid.g r2 = r8.e
            int r2 = r2.a()
            if (r2 != 0) goto L_0x0022
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r0 = "MISSING_INSTANCEID_SERVICE"
            r9.<init>(r0)
            throw r9
        L_0x0022:
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            java.lang.String r3 = "com.google.android.gms"
            r2.setPackage(r3)
            com.google.firebase.iid.g r3 = r8.e
            int r3 = r3.a()
            r4 = 2
            if (r3 != r4) goto L_0x003b
            java.lang.String r3 = "com.google.iid.TOKEN_REQUEST"
        L_0x0037:
            r2.setAction(r3)
            goto L_0x003e
        L_0x003b:
            java.lang.String r3 = "com.google.android.c2dm.intent.REGISTER"
            goto L_0x0037
        L_0x003e:
            r2.putExtras(r9)
            android.content.Context r9 = r8.d
            a(r9, r2)
            java.lang.String r9 = "kid"
            r3 = 5
            java.lang.String r5 = java.lang.String.valueOf(r0)
            int r5 = r5.length()
            int r3 = r3 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "|ID|"
            r5.append(r3)
            r5.append(r0)
            java.lang.String r3 = "|"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r2.putExtra(r9, r3)
            java.lang.String r9 = "FirebaseInstanceId"
            r3 = 3
            boolean r9 = android.util.Log.isLoggable(r9, r3)
            if (r9 == 0) goto L_0x009d
            java.lang.String r9 = "FirebaseInstanceId"
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
            android.util.Log.d(r9, r5)
        L_0x009d:
            java.lang.String r9 = "google.messenger"
            android.os.Messenger r5 = r8.f
            r2.putExtra(r9, r5)
            android.os.Messenger r9 = r8.g
            if (r9 != 0) goto L_0x00ac
            com.google.firebase.iid.zzi r9 = r8.h
            if (r9 == 0) goto L_0x00d1
        L_0x00ac:
            android.os.Message r9 = android.os.Message.obtain()
            r9.obj = r2
            android.os.Messenger r5 = r8.g     // Catch:{ RemoteException -> 0x00c2 }
            if (r5 == 0) goto L_0x00bc
            android.os.Messenger r5 = r8.g     // Catch:{ RemoteException -> 0x00c2 }
            r5.send(r9)     // Catch:{ RemoteException -> 0x00c2 }
            goto L_0x00e4
        L_0x00bc:
            com.google.firebase.iid.zzi r5 = r8.h     // Catch:{ RemoteException -> 0x00c2 }
            r5.send(r9)     // Catch:{ RemoteException -> 0x00c2 }
            goto L_0x00e4
        L_0x00c2:
            java.lang.String r9 = "FirebaseInstanceId"
            boolean r9 = android.util.Log.isLoggable(r9, r3)
            if (r9 == 0) goto L_0x00d1
            java.lang.String r9 = "FirebaseInstanceId"
            java.lang.String r3 = "Messenger failed, fallback to startService"
            android.util.Log.d(r9, r3)
        L_0x00d1:
            com.google.firebase.iid.g r9 = r8.e
            int r9 = r9.a()
            if (r9 != r4) goto L_0x00df
            android.content.Context r9 = r8.d
            r9.sendBroadcast(r2)
            goto L_0x00e4
        L_0x00df:
            android.content.Context r9 = r8.d
            r9.startService(r2)
        L_0x00e4:
            com.google.android.gms.tasks.f r9 = r1.a()     // Catch:{ InterruptedException | TimeoutException -> 0x0108, ExecutionException -> 0x0101 }
            r1 = 30000(0x7530, double:1.4822E-319)
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException | TimeoutException -> 0x0108, ExecutionException -> 0x0101 }
            java.lang.Object r9 = com.google.android.gms.tasks.i.a(r9, r1, r3)     // Catch:{ InterruptedException | TimeoutException -> 0x0108, ExecutionException -> 0x0101 }
            android.os.Bundle r9 = (android.os.Bundle) r9     // Catch:{ InterruptedException | TimeoutException -> 0x0108, ExecutionException -> 0x0101 }
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.g<android.os.Bundle>> r1 = r8.c
            monitor-enter(r1)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.g<android.os.Bundle>> r2 = r8.c     // Catch:{ all -> 0x00fc }
            r2.remove(r0)     // Catch:{ all -> 0x00fc }
            monitor-exit(r1)     // Catch:{ all -> 0x00fc }
            return r9
        L_0x00fc:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00fc }
            throw r9
        L_0x00ff:
            r9 = move-exception
            goto L_0x0117
        L_0x0101:
            r9 = move-exception
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x00ff }
            r1.<init>(r9)     // Catch:{ all -> 0x00ff }
            throw r1     // Catch:{ all -> 0x00ff }
        L_0x0108:
            java.lang.String r9 = "FirebaseInstanceId"
            java.lang.String r1 = "No response"
            android.util.Log.w(r9, r1)     // Catch:{ all -> 0x00ff }
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x00ff }
            java.lang.String r1 = "TIMEOUT"
            r9.<init>(r1)     // Catch:{ all -> 0x00ff }
            throw r9     // Catch:{ all -> 0x00ff }
        L_0x0117:
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.g<android.os.Bundle>> r1 = r8.c
            monitor-enter(r1)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.tasks.g<android.os.Bundle>> r2 = r8.c     // Catch:{ all -> 0x0121 }
            r2.remove(r0)     // Catch:{ all -> 0x0121 }
            monitor-exit(r1)     // Catch:{ all -> 0x0121 }
            throw r9
        L_0x0121:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0121 }
            throw r9
        L_0x0124:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0124 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.m.c(android.os.Bundle):android.os.Bundle");
    }

    /* access modifiers changed from: 0000 */
    public final Bundle a(Bundle bundle) throws IOException {
        if (this.e.d() < 12000000) {
            return b(bundle);
        }
        try {
            return (Bundle) i.a(aj.a(this.d).a(1, bundle));
        } catch (InterruptedException | ExecutionException e2) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(22 + String.valueOf(valueOf).length());
                sb.append("Error making request: ");
                sb.append(valueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            if (!(e2.getCause() instanceof zzaf) || ((zzaf) e2.getCause()).getErrorCode() != 4) {
                return null;
            }
            return b(bundle);
        }
    }
}
