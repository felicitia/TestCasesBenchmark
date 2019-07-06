package com.google.android.gms.internal.firebase-perf;

final class zzby {
    private static final Class<?> zzix = zzcw();

    private static Class<?> zzcw() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzbz zzcx() {
        if (zzix != null) {
            try {
                return zzp("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzbz.zzjb;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.firebase-perf.zzbz zzcy() {
        /*
            java.lang.Class<?> r0 = zzix
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = "loadGeneratedRegistry"
            com.google.android.gms.internal.firebase-perf.zzbz r0 = zzp(r0)     // Catch:{ Exception -> 0x000b }
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            if (r0 != 0) goto L_0x0012
            com.google.android.gms.internal.firebase-perf.zzbz r0 = com.google.android.gms.internal.firebase-perf.zzbz.zzcy()
        L_0x0012:
            if (r0 != 0) goto L_0x0018
            com.google.android.gms.internal.firebase-perf.zzbz r0 = zzcx()
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzby.zzcy():com.google.android.gms.internal.firebase-perf.zzbz");
    }

    private static final zzbz zzp(String str) throws Exception {
        return (zzbz) zzix.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
