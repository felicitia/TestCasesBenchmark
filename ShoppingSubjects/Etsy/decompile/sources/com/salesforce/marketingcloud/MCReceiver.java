package com.salesforce.marketingcloud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

public class MCReceiver extends BroadcastReceiver {
    @RestrictTo({Scope.LIBRARY})
    public static final String a = "com.salesforce.marketingcloud.WAKE_FOR_ALARM";
    private static final String b = "alarmName";
    private static final String c = j.a(MCReceiver.class);

    @RestrictTo({Scope.LIBRARY})
    public static final Intent a(@NonNull Context context, String str) {
        Intent intent = new Intent(context, MCReceiver.class);
        StringBuilder sb = new StringBuilder();
        sb.append(context.getApplicationContext().getPackageName());
        sb.append(".");
        sb.append(a);
        return intent.setAction(sb.toString()).putExtra(b, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0055, code lost:
        if (r0.equals("android.intent.action.ACTION_SHUTDOWN") != false) goto L_0x008b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onReceive(android.content.Context r7, android.content.Intent r8) {
        /*
            r6 = this;
            java.lang.String r0 = r8.getAction()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x001c
            java.lang.String r7 = c
            java.lang.String r0 = "Action was empty %s"
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r8 = r8.toString()
            r1[r2] = r8
            com.salesforce.marketingcloud.j.b(r7, r0, r1)
            return
        L_0x001c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            android.content.Context r4 = r7.getApplicationContext()
            java.lang.String r4 = r4.getPackageName()
            r1.append(r4)
            java.lang.String r4 = "."
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.String r4 = ""
            java.lang.String r0 = r0.replaceFirst(r1, r4)
            java.lang.String r1 = c
            java.lang.String r4 = "onReceive with action: %s"
            java.lang.Object[] r5 = new java.lang.Object[r3]
            r5[r2] = r0
            com.salesforce.marketingcloud.j.a(r1, r4, r5)
            r1 = -1
            int r4 = r0.hashCode()
            switch(r4) {
                case -1076576821: goto L_0x0080;
                case 487459773: goto L_0x0076;
                case 502473491: goto L_0x006c;
                case 798292259: goto L_0x0062;
                case 1737074039: goto L_0x0058;
                case 1947666138: goto L_0x004f;
                default: goto L_0x004e;
            }
        L_0x004e:
            goto L_0x008a
        L_0x004f:
            java.lang.String r3 = "android.intent.action.ACTION_SHUTDOWN"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x008a
            goto L_0x008b
        L_0x0058:
            java.lang.String r2 = "android.intent.action.MY_PACKAGE_REPLACED"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x008a
            r2 = 4
            goto L_0x008b
        L_0x0062:
            java.lang.String r2 = "android.intent.action.BOOT_COMPLETED"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x008a
            r2 = r3
            goto L_0x008b
        L_0x006c:
            java.lang.String r2 = "android.intent.action.TIMEZONE_CHANGED"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x008a
            r2 = 3
            goto L_0x008b
        L_0x0076:
            java.lang.String r2 = "com.salesforce.marketingcloud.WAKE_FOR_ALARM"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x008a
            r2 = 5
            goto L_0x008b
        L_0x0080:
            java.lang.String r2 = "android.intent.action.AIRPLANE_MODE"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x008a
            r2 = 2
            goto L_0x008b
        L_0x008a:
            r2 = r1
        L_0x008b:
            switch(r2) {
                case 0: goto L_0x0099;
                case 1: goto L_0x0099;
                case 2: goto L_0x0099;
                case 3: goto L_0x0099;
                case 4: goto L_0x0099;
                case 5: goto L_0x008f;
                default: goto L_0x008e;
            }
        L_0x008e:
            return
        L_0x008f:
            java.lang.String r0 = "alarmName"
            java.lang.String r8 = r8.getStringExtra(r0)
            com.salesforce.marketingcloud.k.a(r7, r8)
            return
        L_0x0099:
            com.salesforce.marketingcloud.b.a r0 = com.salesforce.marketingcloud.b.a.a(r0)
            if (r0 == 0) goto L_0x00a6
            android.os.Bundle r8 = r8.getExtras()
            com.salesforce.marketingcloud.k.a(r7, r0, r8)
        L_0x00a6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MCReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
