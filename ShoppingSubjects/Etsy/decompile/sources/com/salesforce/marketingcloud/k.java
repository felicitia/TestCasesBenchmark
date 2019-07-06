package com.salesforce.marketingcloud;

import android.annotation.SuppressLint;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.b.a;
import com.salesforce.marketingcloud.b.c;
import com.salesforce.marketingcloud.c.e;
import com.salesforce.marketingcloud.e.g;

@RestrictTo({Scope.LIBRARY})
public final class k {
    private static final String a = j.a(k.class);

    private k() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        if (r0.equals("com.salesforce.marketingcloud.SYSTEM_BEHAVIOR") == false) goto L_0x007a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r6, android.content.Intent r7) {
        /*
            java.lang.String r0 = r7.getAction()
            r1 = 0
            if (r0 != 0) goto L_0x0011
            java.lang.String r6 = a
            java.lang.String r7 = "handleWorkFromJob - action was null"
            java.lang.Object[] r0 = new java.lang.Object[r1]
            com.salesforce.marketingcloud.j.b(r6, r7, r0)
            return
        L_0x0011:
            java.lang.String r2 = a
            java.lang.String r3 = "handleWorkFromJob - %s"
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r5[r1] = r0
            com.salesforce.marketingcloud.j.b(r2, r3, r5)
            boolean r2 = com.salesforce.marketingcloud.c.c()
            if (r2 != 0) goto L_0x0033
            boolean r2 = com.salesforce.marketingcloud.c.b()
            if (r2 != 0) goto L_0x0033
            java.lang.String r6 = a
            java.lang.String r7 = "MarketingCloudSdk#init must be called in your application's onCreate"
            java.lang.Object[] r0 = new java.lang.Object[r1]
            com.salesforce.marketingcloud.j.d(r6, r7, r0)
            return
        L_0x0033:
            com.salesforce.marketingcloud.c r2 = com.salesforce.marketingcloud.c.a()
            if (r2 == 0) goto L_0x00b4
            r2 = -1
            int r3 = r0.hashCode()
            r5 = -1341919505(0xffffffffb003eeef, float:-4.799707E-10)
            if (r3 == r5) goto L_0x0070
            r5 = -525195028(0xffffffffe0b228ec, float:-1.0270216E20)
            if (r3 == r5) goto L_0x0066
            r5 = 352488053(0x15028a75, float:2.6362514E-26)
            if (r3 == r5) goto L_0x005c
            r4 = 848031877(0x328bf085, float:1.6291105E-8)
            if (r3 == r4) goto L_0x0053
            goto L_0x007a
        L_0x0053:
            java.lang.String r3 = "com.salesforce.marketingcloud.SYSTEM_BEHAVIOR"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x007a
            goto L_0x007b
        L_0x005c:
            java.lang.String r1 = "com.salesforce.marketingcloud.HTTP_REQUEST"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007a
            r1 = r4
            goto L_0x007b
        L_0x0066:
            java.lang.String r1 = "com.salesforce.marketingcloud.TOKEN_REQUEST"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007a
            r1 = 3
            goto L_0x007b
        L_0x0070:
            java.lang.String r1 = "com.salesforce.marketingcloud.ALARM_WAKE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007a
            r1 = 2
            goto L_0x007b
        L_0x007a:
            r1 = r2
        L_0x007b:
            switch(r1) {
                case 0: goto L_0x00a1;
                case 1: goto L_0x0093;
                case 2: goto L_0x0089;
                case 3: goto L_0x007f;
                default: goto L_0x007e;
            }
        L_0x007e:
            return
        L_0x007f:
            java.lang.String r0 = "senderId"
            java.lang.String r7 = r7.getStringExtra(r0)
            c(r6, r7)
            return
        L_0x0089:
            java.lang.String r0 = "alarmName"
            java.lang.String r7 = r7.getStringExtra(r0)
            d(r6, r7)
            return
        L_0x0093:
            android.os.Bundle r7 = r7.getExtras()
            if (r7 == 0) goto L_0x00b4
            com.salesforce.marketingcloud.c.e r7 = com.salesforce.marketingcloud.c.e.a(r7)
            b(r6, r7)
            return
        L_0x00a1:
            java.lang.String r0 = "behavior"
            java.lang.String r0 = r7.getStringExtra(r0)
            com.salesforce.marketingcloud.b.a r0 = com.salesforce.marketingcloud.b.a.a(r0)
            java.lang.String r1 = "data"
            android.os.Bundle r7 = r7.getBundleExtra(r1)
            b(r6, r0, r7)
        L_0x00b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.k.a(android.content.Context, android.content.Intent):void");
    }

    static void a(@NonNull Context context, @NonNull a aVar, @Nullable Bundle bundle) {
        j.a(a, "enqueueSystemBehavior - %s", aVar);
        Bundle bundle2 = new Bundle();
        bundle2.putString("behavior", aVar.n);
        bundle2.putBundle("data", bundle);
        a(context, "com.salesforce.marketingcloud.SYSTEM_BEHAVIOR", bundle2);
    }

    public static void a(@NonNull Context context, @NonNull e eVar) {
        j.a(a, "handleHttpRequest - %s", eVar.f());
        a(context, "com.salesforce.marketingcloud.HTTP_REQUEST", eVar.k());
    }

    public static void a(@NonNull Context context, String str) {
        j.a(a, "enqueueAlarmWake - %s", str);
        Bundle bundle = new Bundle();
        bundle.putString("alarmName", str);
        a(context, "com.salesforce.marketingcloud.ALARM_WAKE", bundle);
    }

    @SuppressLint({"NewApi"})
    private static void a(Context context, String str, Bundle bundle) {
        if (g.a()) {
            j.b(a, "Handling %s with JobScheduler", str);
            ((JobScheduler) context.getApplicationContext().getSystemService("jobscheduler")).enqueue(new Builder(PathInterpolatorCompat.MAX_NUM_POINTS, new ComponentName(context, MCJobService.class)).setOverrideDeadline(0).build(), new JobWorkItem(new Intent(str).putExtras(bundle)));
            return;
        }
        Intent a2 = MCService.a(context, str, bundle);
        if (a2 != null) {
            context.startService(a2);
        }
    }

    private static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    private static void b(Context context, a aVar, Bundle bundle) {
        if (aVar == null) {
            j.a(a, "Behavior was null", new Object[0]);
            return;
        }
        j.a(a, "handleSystemBehavior - %s", aVar);
        c.a(context, aVar, bundle);
    }

    private static void b(Context context, e eVar) {
        if (eVar == null) {
            j.a(a, "request was null", new Object[0]);
            return;
        }
        j.a(a, "handleHttpRequest - %s", eVar.f());
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.salesforce.marketingcloud.http.RESPONSE").putExtra("http_request", eVar.k()).putExtra("http_response", a(context) ? eVar.l() : com.salesforce.marketingcloud.c.g.a("No connectivity", -1)));
    }

    public static void b(@NonNull Context context, @NonNull String str) {
        j.a(a, "enqueueTokenRequest", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putString("senderId", str);
        a(context, "com.salesforce.marketingcloud.TOKEN_REQUEST", bundle);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.Bundle, java.lang.CharSequence, java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v2, types: [android.os.Bundle, java.lang.CharSequence, java.lang.String]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [java.lang.CharSequence, java.lang.String, android.os.Bundle]
      mth insns count: 34
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.Context r5, java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0011
            java.lang.String r5 = a
            java.lang.String r6 = "Unable to refresh system token.  SenderId was invalid"
            java.lang.Object[] r0 = new java.lang.Object[r1]
            com.salesforce.marketingcloud.j.b(r5, r6, r0)
            return
        L_0x0011:
            java.lang.String r0 = a
            java.lang.String r2 = "handlerTokenRequest"
            java.lang.Object[] r3 = new java.lang.Object[r1]
            com.salesforce.marketingcloud.j.a(r0, r2, r3)
            r0 = 0
            com.google.android.gms.iid.a r2 = com.google.android.gms.iid.a.c(r5)     // Catch:{ Exception -> 0x0031 }
            java.lang.String r3 = "GCM"
            java.lang.String r2 = r2.b(r6, r3, r0)     // Catch:{ Exception -> 0x0031 }
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            r0 = r0 ^ 1
            com.salesforce.marketingcloud.messages.push.b.a(r5, r0, r6, r2)
            return
        L_0x002f:
            r1 = move-exception
            goto L_0x0045
        L_0x0031:
            r2 = move-exception
            java.lang.String r3 = a     // Catch:{ all -> 0x002f }
            java.lang.String r4 = "Failed to complete token refresh"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x002f }
            com.salesforce.marketingcloud.j.c(r3, r2, r4, r1)     // Catch:{ all -> 0x002f }
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r1 = r1 ^ 1
            com.salesforce.marketingcloud.messages.push.b.a(r5, r1, r6, r0)
            return
        L_0x0045:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            r2 = r2 ^ 1
            com.salesforce.marketingcloud.messages.push.b.a(r5, r2, r6, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.k.c(android.content.Context, java.lang.String):void");
    }

    private static void d(Context context, String str) {
        if (str == null) {
            j.a(a, "alarm name not provided", new Object[0]);
            return;
        }
        j.a(a, "handleAlarmWakeup - %s", str);
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.salesforce.marketingcloud.ACTION_ALARM_WAKE_EVENT").putExtra(MCReceiver.a, str));
    }
}
