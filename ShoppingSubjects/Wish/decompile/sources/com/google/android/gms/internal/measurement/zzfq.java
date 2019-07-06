package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

final class zzfq implements Runnable {
    private final String packageName;
    private final URL url;
    private final byte[] zzalk;
    private final zzfo zzall;
    private final Map<String, String> zzalm;
    private final /* synthetic */ zzfm zzaln;

    public zzfq(zzfm zzfm, String str, URL url2, byte[] bArr, Map<String, String> map, zzfo zzfo) {
        this.zzaln = zzfm;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url2);
        Preconditions.checkNotNull(zzfo);
        this.url = url2;
        this.zzalk = bArr;
        this.zzall = zzfo;
        this.packageName = str;
        this.zzalm = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c4 A[SYNTHETIC, Splitter:B:45:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0101 A[SYNTHETIC, Splitter:B:58:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r12 = this;
            com.google.android.gms.internal.measurement.zzfm r0 = r12.zzaln
            r0.zzfw()
            r0 = 0
            r1 = 0
            com.google.android.gms.internal.measurement.zzfm r2 = r12.zzaln     // Catch:{ IOException -> 0x00fa, all -> 0x00be }
            java.net.URL r3 = r12.url     // Catch:{ IOException -> 0x00fa, all -> 0x00be }
            java.net.HttpURLConnection r2 = r2.zzb(r3)     // Catch:{ IOException -> 0x00fa, all -> 0x00be }
            java.util.Map<java.lang.String, java.lang.String> r3 = r12.zzalm     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            if (r3 == 0) goto L_0x0039
            java.util.Map<java.lang.String, java.lang.String> r3 = r12.zzalm     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        L_0x001d:
            boolean r4 = r3.hasNext()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            if (r4 == 0) goto L_0x0039
            java.lang.Object r4 = r3.next()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.Object r5 = r4.getKey()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r2.addRequestProperty(r5, r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            goto L_0x001d
        L_0x0039:
            byte[] r3 = r12.zzalk     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            if (r3 == 0) goto L_0x0084
            com.google.android.gms.internal.measurement.zzfm r3 = r12.zzaln     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            com.google.android.gms.internal.measurement.zzjz r3 = r3.zzjf()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            byte[] r4 = r12.zzalk     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            byte[] r3 = r3.zzb(r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            com.google.android.gms.internal.measurement.zzfm r4 = r12.zzaln     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzgi()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zzjc()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.String r5 = "Uploading data. size"
            int r6 = r3.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r4.zzg(r5, r6)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r4 = 1
            r2.setDoOutput(r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.String r4 = "Content-Encoding"
            java.lang.String r5 = "gzip"
            r2.addRequestProperty(r4, r5)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            int r4 = r3.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r2.setFixedLengthStreamingMode(r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r2.connect()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.io.OutputStream r4 = r2.getOutputStream()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r4.write(r3)     // Catch:{ IOException -> 0x007e, all -> 0x007a }
            r4.close()     // Catch:{ IOException -> 0x007e, all -> 0x007a }
            goto L_0x0084
        L_0x007a:
            r3 = move-exception
            r10 = r0
            r0 = r4
            goto L_0x00c1
        L_0x007e:
            r3 = move-exception
            r10 = r0
            r8 = r3
            r0 = r4
            goto L_0x00fe
        L_0x0084:
            int r7 = r2.getResponseCode()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.util.Map r10 = r2.getHeaderFields()     // Catch:{ IOException -> 0x00b4, all -> 0x00b1 }
            com.google.android.gms.internal.measurement.zzfm r1 = r12.zzaln     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            byte[] r9 = com.google.android.gms.internal.measurement.zzfm.zzb(r2)     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            if (r2 == 0) goto L_0x0097
            r2.disconnect()
        L_0x0097:
            com.google.android.gms.internal.measurement.zzfm r0 = r12.zzaln
            com.google.android.gms.internal.measurement.zzgi r0 = r0.zzgh()
            com.google.android.gms.internal.measurement.zzfp r1 = new com.google.android.gms.internal.measurement.zzfp
            java.lang.String r5 = r12.packageName
            com.google.android.gms.internal.measurement.zzfo r6 = r12.zzall
            r8 = 0
            r11 = 0
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10)
        L_0x00a9:
            r0.zzc(r1)
            return
        L_0x00ad:
            r3 = move-exception
            goto L_0x00c2
        L_0x00af:
            r3 = move-exception
            goto L_0x00b6
        L_0x00b1:
            r3 = move-exception
            r10 = r0
            goto L_0x00c2
        L_0x00b4:
            r3 = move-exception
            r10 = r0
        L_0x00b6:
            r8 = r3
            goto L_0x00ff
        L_0x00b8:
            r3 = move-exception
            r10 = r0
            goto L_0x00c1
        L_0x00bb:
            r3 = move-exception
            r10 = r0
            goto L_0x00fd
        L_0x00be:
            r3 = move-exception
            r2 = r0
            r10 = r2
        L_0x00c1:
            r7 = 0
        L_0x00c2:
            if (r0 == 0) goto L_0x00de
            r0.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x00de
        L_0x00c8:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfm r1 = r12.zzaln
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()
            java.lang.String r4 = "Error closing HTTP compressed POST connection output stream. appId"
            java.lang.String r5 = r12.packageName
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfi.zzbp(r5)
            r1.zze(r4, r5, r0)
        L_0x00de:
            if (r2 == 0) goto L_0x00e3
            r2.disconnect()
        L_0x00e3:
            com.google.android.gms.internal.measurement.zzfm r0 = r12.zzaln
            com.google.android.gms.internal.measurement.zzgi r0 = r0.zzgh()
            com.google.android.gms.internal.measurement.zzfp r1 = new com.google.android.gms.internal.measurement.zzfp
            java.lang.String r5 = r12.packageName
            com.google.android.gms.internal.measurement.zzfo r6 = r12.zzall
            r8 = 0
            r9 = 0
            r11 = 0
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r0.zzc(r1)
            throw r3
        L_0x00fa:
            r3 = move-exception
            r2 = r0
            r10 = r2
        L_0x00fd:
            r8 = r3
        L_0x00fe:
            r7 = 0
        L_0x00ff:
            if (r0 == 0) goto L_0x011b
            r0.close()     // Catch:{ IOException -> 0x0105 }
            goto L_0x011b
        L_0x0105:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfm r1 = r12.zzaln
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()
            java.lang.String r3 = "Error closing HTTP compressed POST connection output stream. appId"
            java.lang.String r4 = r12.packageName
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r4)
            r1.zze(r3, r4, r0)
        L_0x011b:
            if (r2 == 0) goto L_0x0120
            r2.disconnect()
        L_0x0120:
            com.google.android.gms.internal.measurement.zzfm r0 = r12.zzaln
            com.google.android.gms.internal.measurement.zzgi r0 = r0.zzgh()
            com.google.android.gms.internal.measurement.zzfp r1 = new com.google.android.gms.internal.measurement.zzfp
            java.lang.String r5 = r12.packageName
            com.google.android.gms.internal.measurement.zzfo r6 = r12.zzall
            r9 = 0
            r11 = 0
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10)
            goto L_0x00a9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfq.run():void");
    }
}
