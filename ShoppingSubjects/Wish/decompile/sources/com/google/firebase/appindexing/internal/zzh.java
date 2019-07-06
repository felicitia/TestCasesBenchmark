package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.FirebaseAppIndex;

public final class zzh extends FirebaseAppIndex {
    private static String[] zzcm = {"com.google.android.googlequicksearchbox", "com.google.android.gms"};
    private final GoogleApi<?> zzcn;
    private final zzj zzco;
    private final Context zzcp;

    public zzh(Context context) {
        this(context, new zzi(context));
    }

    private zzh(Context context, GoogleApi<Object> googleApi) {
        this.zzcn = googleApi;
        this.zzcp = context;
        this.zzco = new zzj(googleApi);
    }

    private final Task<Void> zza(zzx zzx) {
        return this.zzco.zzb(zzx);
    }

    public final Task<Void> removeAll() {
        return zza(new zzx(4, null, null, null));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0049 A[Catch:{ ArrayStoreException -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0061 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> update(com.google.firebase.appindexing.Indexable... r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            if (r13 != 0) goto L_0x0006
            r2 = r0
            goto L_0x000d
        L_0x0006:
            int r2 = r13.length     // Catch:{ ArrayStoreException -> 0x006f }
            com.google.firebase.appindexing.internal.Thing[] r2 = new com.google.firebase.appindexing.internal.Thing[r2]     // Catch:{ ArrayStoreException -> 0x006f }
            int r3 = r13.length     // Catch:{ ArrayStoreException -> 0x006f }
            java.lang.System.arraycopy(r13, r1, r2, r1, r3)     // Catch:{ ArrayStoreException -> 0x006f }
        L_0x000d:
            boolean r13 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat()     // Catch:{ ArrayStoreException -> 0x006f }
            if (r13 == 0) goto L_0x0064
            android.content.Context r13 = r12.zzcp     // Catch:{ ArrayStoreException -> 0x006f }
            if (r13 == 0) goto L_0x0064
            if (r2 == 0) goto L_0x0064
            int r13 = r2.length     // Catch:{ ArrayStoreException -> 0x006f }
            if (r13 <= 0) goto L_0x0064
            int r13 = r2.length     // Catch:{ ArrayStoreException -> 0x006f }
            r3 = 0
        L_0x001e:
            if (r3 >= r13) goto L_0x0064
            r4 = r2[r3]     // Catch:{ ArrayStoreException -> 0x006f }
            if (r4 == 0) goto L_0x0061
            com.google.firebase.appindexing.internal.Thing$zza r4 = r4.zzk()     // Catch:{ ArrayStoreException -> 0x006f }
            java.lang.String r5 = "sliceUri"
            android.os.Bundle r6 = r4.zzd()     // Catch:{ ArrayStoreException -> 0x006f }
            if (r6 == 0) goto L_0x0046
            android.os.Bundle r6 = r4.zzd()     // Catch:{ ArrayStoreException -> 0x006f }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ ArrayStoreException -> 0x006f }
            boolean r6 = r6 instanceof java.lang.String[]     // Catch:{ ArrayStoreException -> 0x006f }
            if (r6 != 0) goto L_0x003d
            goto L_0x0046
        L_0x003d:
            android.os.Bundle r4 = r4.zzd()     // Catch:{ ArrayStoreException -> 0x006f }
            java.lang.String[] r4 = r4.getStringArray(r5)     // Catch:{ ArrayStoreException -> 0x006f }
            goto L_0x0047
        L_0x0046:
            r4 = r0
        L_0x0047:
            if (r4 == 0) goto L_0x0061
            r4 = r4[r1]     // Catch:{ ArrayStoreException -> 0x006f }
            java.lang.String[] r5 = zzcm     // Catch:{ ArrayStoreException -> 0x006f }
            int r6 = r5.length     // Catch:{ ArrayStoreException -> 0x006f }
            r7 = 0
        L_0x004f:
            if (r7 >= r6) goto L_0x0061
            r8 = r5[r7]     // Catch:{ ArrayStoreException -> 0x006f }
            android.content.Context r9 = r12.zzcp     // Catch:{ ArrayStoreException -> 0x006f }
            android.net.Uri r10 = android.net.Uri.parse(r4)     // Catch:{ ArrayStoreException -> 0x006f }
            r11 = 66
            r9.grantUriPermission(r8, r10, r11)     // Catch:{ ArrayStoreException -> 0x006f }
            int r7 = r7 + 1
            goto L_0x004f
        L_0x0061:
            int r3 = r3 + 1
            goto L_0x001e
        L_0x0064:
            com.google.firebase.appindexing.internal.zzx r13 = new com.google.firebase.appindexing.internal.zzx
            r1 = 1
            r13.<init>(r1, r2, r0, r0)
            com.google.android.gms.tasks.Task r13 = r12.zza(r13)
            return r13
        L_0x006f:
            com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException r13 = new com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException
            java.lang.String r0 = "Custom Indexable-objects are not allowed. Please use the 'Indexables'-class for creating the objects."
            r13.<init>(r0)
            com.google.android.gms.tasks.Task r13 = com.google.android.gms.tasks.Tasks.forException(r13)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appindexing.internal.zzh.update(com.google.firebase.appindexing.Indexable[]):com.google.android.gms.tasks.Task");
    }
}
