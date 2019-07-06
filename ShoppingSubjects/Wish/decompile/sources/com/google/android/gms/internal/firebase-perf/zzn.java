package com.google.android.gms.internal.firebase-perf;

import android.util.Log;

final class zzn extends zzo {
    private zzat zzbs;

    zzn(zzat zzat) {
        this.zzbs = zzat;
    }

    public final boolean zzn() {
        boolean z;
        if (!zzc(this.zzbs, 0)) {
            String str = "FirebasePerformance";
            String str2 = "Invalid Trace:";
            String valueOf = String.valueOf(this.zzbs.name);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return false;
        }
        zzat zzat = this.zzbs;
        if (!(zzat.zzha.length > 0)) {
            zzat[] zzatArr = zzat.zzhb;
            int length = zzatArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                if (zzatArr[i].zzha.length > 0) {
                    break;
                }
                i++;
            }
            if (z || zzd(this.zzbs, 0)) {
                return true;
            }
            String str3 = "FirebasePerformance";
            String str4 = "Invalid Counters for Trace:";
            String valueOf2 = String.valueOf(this.zzbs.name);
            Log.w(str3, valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
            return false;
        }
        z = true;
        if (z) {
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzc(com.google.android.gms.internal.firebase-perf.zzat r8, int r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L_0x000b
            java.lang.String r8 = "FirebasePerformance"
            java.lang.String r9 = "TraceMetric is null"
            android.util.Log.w(r8, r9)
            return r0
        L_0x000b:
            r1 = 1
            if (r9 <= r1) goto L_0x0016
            java.lang.String r8 = "FirebasePerformance"
            java.lang.String r9 = "Exceed MAX_SUBTRACE_DEEP:1"
            android.util.Log.w(r8, r9)
            return r0
        L_0x0016:
            java.lang.String r2 = r8.name
            if (r2 == 0) goto L_0x002e
            java.lang.String r2 = r2.trim()
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x002e
            int r2 = r2.length()
            r3 = 100
            if (r2 > r3) goto L_0x002e
            r2 = 1
            goto L_0x002f
        L_0x002e:
            r2 = 0
        L_0x002f:
            if (r2 != 0) goto L_0x004f
            java.lang.String r9 = "FirebasePerformance"
            java.lang.String r1 = "invalid TraceId:"
            java.lang.String r8 = r8.name
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r2 = r8.length()
            if (r2 == 0) goto L_0x0046
            java.lang.String r8 = r1.concat(r8)
            goto L_0x004b
        L_0x0046:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r1)
        L_0x004b:
            android.util.Log.w(r9, r8)
            return r0
        L_0x004f:
            if (r8 == 0) goto L_0x0063
            java.lang.Long r2 = r8.zzgz
            if (r2 == 0) goto L_0x0063
            java.lang.Long r2 = r8.zzgz
            long r2 = r2.longValue()
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0063
            r2 = 1
            goto L_0x0064
        L_0x0063:
            r2 = 0
        L_0x0064:
            if (r2 != 0) goto L_0x008d
            java.lang.String r9 = "FirebasePerformance"
            java.lang.Long r8 = r8.zzgz
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r1 = java.lang.String.valueOf(r8)
            int r1 = r1.length()
            int r1 = r1 + 22
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "invalid TraceDuration:"
            r2.append(r1)
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            android.util.Log.w(r9, r8)
            return r0
        L_0x008d:
            java.lang.Long r2 = r8.zzgk
            if (r2 != 0) goto L_0x0099
            java.lang.String r8 = "FirebasePerformance"
            java.lang.String r9 = "clientStartTimeUs is null."
            android.util.Log.w(r8, r9)
            return r0
        L_0x0099:
            com.google.android.gms.internal.firebase-perf.zzat[] r2 = r8.zzhb
            int r3 = r2.length
            r4 = 0
        L_0x009d:
            if (r4 >= r3) goto L_0x00ad
            r5 = r2[r4]
            int r6 = r9 + 1
            boolean r5 = r7.zzc(r5, r6)
            if (r5 != 0) goto L_0x00aa
            return r0
        L_0x00aa:
            int r4 = r4 + 1
            goto L_0x009d
        L_0x00ad:
            com.google.android.gms.internal.firebase-perf.zzav[] r9 = r8.zzhc
            if (r9 == 0) goto L_0x00d6
            com.google.android.gms.internal.firebase-perf.zzav[] r8 = r8.zzhc
            int r9 = r8.length
            r2 = 0
        L_0x00b5:
            if (r2 >= r9) goto L_0x00d2
            r3 = r8[r2]
            java.util.AbstractMap$SimpleEntry r4 = new java.util.AbstractMap$SimpleEntry
            java.lang.String r5 = r3.key
            java.lang.String r3 = r3.value
            r4.<init>(r5, r3)
            java.lang.String r3 = com.google.android.gms.internal.firebase-perf.zzo.zza(r4)
            if (r3 == 0) goto L_0x00cf
            java.lang.String r8 = "FirebasePerformance"
            android.util.Log.w(r8, r3)
            r8 = 0
            goto L_0x00d3
        L_0x00cf:
            int r2 = r2 + 1
            goto L_0x00b5
        L_0x00d2:
            r8 = 1
        L_0x00d3:
            if (r8 != 0) goto L_0x00d6
            return r0
        L_0x00d6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzn.zzc(com.google.android.gms.internal.firebase-perf.zzat, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0042 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(com.google.android.gms.internal.firebase-perf.zzat r9, int r10) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 1
            if (r10 <= r1) goto L_0x000f
            java.lang.String r9 = "FirebasePerformance"
            java.lang.String r10 = "Exceed MAX_SUBTRACE_DEEP:1"
            android.util.Log.w(r9, r10)
            return r0
        L_0x000f:
            com.google.android.gms.internal.firebase-perf.zzau[] r2 = r9.zzha
            int r3 = r2.length
            r4 = 0
        L_0x0013:
            if (r4 >= r3) goto L_0x0095
            r5 = r2[r4]
            java.lang.String r6 = r5.key
            if (r6 != 0) goto L_0x001d
        L_0x001b:
            r6 = 0
            goto L_0x0040
        L_0x001d:
            java.lang.String r6 = r6.trim()
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L_0x002f
            java.lang.String r6 = "FirebasePerformance"
            java.lang.String r7 = "counterId is empty"
            android.util.Log.w(r6, r7)
            goto L_0x001b
        L_0x002f:
            int r6 = r6.length()
            r7 = 100
            if (r6 <= r7) goto L_0x003f
            java.lang.String r6 = "FirebasePerformance"
            java.lang.String r7 = "counterId exceeded max length 100"
            android.util.Log.w(r6, r7)
            goto L_0x001b
        L_0x003f:
            r6 = 1
        L_0x0040:
            if (r6 != 0) goto L_0x0061
            java.lang.String r9 = "FirebasePerformance"
            java.lang.String r10 = "invalid CounterId:"
            java.lang.String r1 = r5.key
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0057
            java.lang.String r10 = r10.concat(r1)
            goto L_0x005d
        L_0x0057:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r10)
            r10 = r1
        L_0x005d:
            android.util.Log.w(r9, r10)
            return r0
        L_0x0061:
            java.lang.Long r6 = r5.zzhe
            if (r6 == 0) goto L_0x0067
            r6 = 1
            goto L_0x0068
        L_0x0067:
            r6 = 0
        L_0x0068:
            if (r6 != 0) goto L_0x0091
            java.lang.String r9 = "FirebasePerformance"
            java.lang.Long r10 = r5.zzhe
            java.lang.String r10 = java.lang.String.valueOf(r10)
            java.lang.String r1 = java.lang.String.valueOf(r10)
            int r1 = r1.length()
            int r1 = r1 + 21
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "invalid CounterValue:"
            r2.append(r1)
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            android.util.Log.w(r9, r10)
            return r0
        L_0x0091:
            int r4 = r4 + 1
            goto L_0x0013
        L_0x0095:
            com.google.android.gms.internal.firebase-perf.zzat[] r9 = r9.zzhb
            int r2 = r9.length
            r3 = 0
        L_0x0099:
            if (r3 >= r2) goto L_0x00a9
            r4 = r9[r3]
            int r5 = r10 + 1
            boolean r4 = r8.zzd(r4, r5)
            if (r4 != 0) goto L_0x00a6
            return r0
        L_0x00a6:
            int r3 = r3 + 1
            goto L_0x0099
        L_0x00a9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzn.zzd(com.google.android.gms.internal.firebase-perf.zzat, int):boolean");
    }
}
