package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@bu
@TargetApi(21)
final class fo {
    private static final Map<String, String> a;
    private final Context b;
    private final List<String> c;
    private final fc d;

    static {
        HashMap hashMap = new HashMap();
        if (PlatformVersion.isAtLeastLollipop()) {
            hashMap.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            hashMap.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        a = hashMap;
    }

    fo(Context context, List<String> list, fc fcVar) {
        this.b = context;
        this.c = list;
        this.d = fcVar;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r5 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> a(java.lang.String[] r11) {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            int r2 = r11.length
            r3 = r1
        L_0x0008:
            if (r3 >= r2) goto L_0x007c
            r4 = r11[r3]
            java.util.List<java.lang.String> r5 = r10.c
            java.util.Iterator r5 = r5.iterator()
        L_0x0012:
            boolean r6 = r5.hasNext()
            r7 = 1
            if (r6 == 0) goto L_0x0048
            java.lang.Object r6 = r5.next()
            java.lang.String r6 = (java.lang.String) r6
            boolean r8 = r6.equals(r4)
            if (r8 == 0) goto L_0x0027
        L_0x0025:
            r5 = r7
            goto L_0x0049
        L_0x0027:
            java.lang.String r8 = "android.webkit.resource."
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r9 = r6.length()
            if (r9 == 0) goto L_0x003c
            java.lang.String r6 = r8.concat(r6)
            goto L_0x0041
        L_0x003c:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r8)
        L_0x0041:
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x0012
            goto L_0x0025
        L_0x0048:
            r5 = r1
        L_0x0049:
            if (r5 == 0) goto L_0x0074
            java.util.Map<java.lang.String, java.lang.String> r5 = a
            boolean r5 = r5.containsKey(r4)
            if (r5 == 0) goto L_0x0068
            com.google.android.gms.ads.internal.ao.e()
            android.content.Context r5 = r10.b
            java.util.Map<java.lang.String, java.lang.String> r6 = a
            java.lang.Object r6 = r6.get(r4)
            java.lang.String r6 = (java.lang.String) r6
            boolean r5 = com.google.android.gms.internal.ads.hd.a(r5, r6)
            if (r5 == 0) goto L_0x0067
            goto L_0x0068
        L_0x0067:
            r7 = r1
        L_0x0068:
            if (r7 == 0) goto L_0x006e
            r0.add(r4)
            goto L_0x0079
        L_0x006e:
            com.google.android.gms.internal.ads.fc r5 = r10.d
            r5.c(r4)
            goto L_0x0079
        L_0x0074:
            com.google.android.gms.internal.ads.fc r5 = r10.d
            r5.b(r4)
        L_0x0079:
            int r3 = r3 + 1
            goto L_0x0008
        L_0x007c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.fo.a(java.lang.String[]):java.util.List");
    }
}
