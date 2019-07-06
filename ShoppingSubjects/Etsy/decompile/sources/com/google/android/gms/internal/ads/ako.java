package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@bu
public final class ako {
    @VisibleForTesting
    private BlockingQueue<aky> a = new ArrayBlockingQueue(100);
    @VisibleForTesting
    private ExecutorService b;
    @VisibleForTesting
    private LinkedHashMap<String, String> c = new LinkedHashMap<>();
    @VisibleForTesting
    private Map<String, aks> d = new HashMap();
    @VisibleForTesting
    private String e;
    @VisibleForTesting
    private Context f;
    @VisibleForTesting
    private String g;
    private AtomicBoolean h;
    private File i;

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097 A[SYNTHETIC, Splitter:B:29:0x0097] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6 A[SYNTHETIC, Splitter:B:34:0x00a6] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0000 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r5 = this;
        L_0x0000:
            java.util.concurrent.BlockingQueue<com.google.android.gms.internal.ads.aky> r0 = r5.a     // Catch:{ InterruptedException -> 0x00c4 }
            java.lang.Object r0 = r0.take()     // Catch:{ InterruptedException -> 0x00c4 }
            com.google.android.gms.internal.ads.aky r0 = (com.google.android.gms.internal.ads.aky) r0     // Catch:{ InterruptedException -> 0x00c4 }
            java.lang.String r1 = r0.b()     // Catch:{ InterruptedException -> 0x00c4 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0000
            java.util.LinkedHashMap<java.lang.String, java.lang.String> r2 = r5.c
            java.util.Map r0 = r0.c()
            java.util.Map r0 = r5.a(r2, r0)
            java.lang.String r2 = r5.e
            android.net.Uri r2 = android.net.Uri.parse(r2)
            android.net.Uri$Builder r2 = r2.buildUpon()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x002e:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x004a
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r3 = r3.getValue()
            java.lang.String r3 = (java.lang.String) r3
            r2.appendQueryParameter(r4, r3)
            goto L_0x002e
        L_0x004a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            android.net.Uri r2 = r2.build()
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            java.lang.String r2 = "&it="
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.util.concurrent.atomic.AtomicBoolean r1 = r5.h
            boolean r1 = r1.get()
            if (r1 == 0) goto L_0x00b8
            java.io.File r1 = r5.i
            if (r1 == 0) goto L_0x00b1
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x008f }
            r4 = 1
            r3.<init>(r1, r4)     // Catch:{ IOException -> 0x008f }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x008a, all -> 0x0087 }
            r3.write(r0)     // Catch:{ IOException -> 0x008a, all -> 0x0087 }
            r0 = 10
            r3.write(r0)     // Catch:{ IOException -> 0x008a, all -> 0x0087 }
            r3.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x0000
        L_0x0087:
            r0 = move-exception
            r2 = r3
            goto L_0x00a4
        L_0x008a:
            r0 = move-exception
            r2 = r3
            goto L_0x0090
        L_0x008d:
            r0 = move-exception
            goto L_0x00a4
        L_0x008f:
            r0 = move-exception
        L_0x0090:
            java.lang.String r1 = "CsiReporter: Cannot write to file: sdk_csi_data.txt."
            com.google.android.gms.internal.ads.gv.c(r1, r0)     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x0000
            r2.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x0000
        L_0x009c:
            r0 = move-exception
            java.lang.String r1 = "CsiReporter: Cannot close file: sdk_csi_data.txt."
            com.google.android.gms.internal.ads.gv.c(r1, r0)
            goto L_0x0000
        L_0x00a4:
            if (r2 == 0) goto L_0x00b0
            r2.close()     // Catch:{ IOException -> 0x00aa }
            goto L_0x00b0
        L_0x00aa:
            r1 = move-exception
            java.lang.String r2 = "CsiReporter: Cannot close file: sdk_csi_data.txt."
            com.google.android.gms.internal.ads.gv.c(r2, r1)
        L_0x00b0:
            throw r0
        L_0x00b1:
            java.lang.String r0 = "CsiReporter: File doesn't exists. Cannot write CSI data to file."
            com.google.android.gms.internal.ads.gv.e(r0)
            goto L_0x0000
        L_0x00b8:
            com.google.android.gms.ads.internal.ao.e()
            android.content.Context r1 = r5.f
            java.lang.String r2 = r5.g
            com.google.android.gms.internal.ads.hd.a(r1, r2, r0)
            goto L_0x0000
        L_0x00c4:
            r0 = move-exception
            java.lang.String r1 = "CsiReporter:reporter interrupted"
            com.google.android.gms.internal.ads.gv.c(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ako.a():void");
    }

    public final aks a(String str) {
        aks aks = (aks) this.d.get(str);
        return aks != null ? aks : aks.a;
    }

    /* access modifiers changed from: 0000 */
    public final Map<String, String> a(Map<String, String> map, @Nullable Map<String, String> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, a(str).a(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public final void a(Context context, String str, String str2, Map<String, String> map) {
        this.f = context;
        this.g = str;
        this.e = str2;
        this.h = new AtomicBoolean(false);
        this.h.set(((Boolean) ajh.f().a(akl.P)).booleanValue());
        if (this.h.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.i = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Entry entry : map.entrySet()) {
            this.c.put((String) entry.getKey(), (String) entry.getValue());
        }
        this.b = Executors.newSingleThreadExecutor();
        this.b.execute(new akp(this));
        this.d.put(ResponseConstants.ACTION, aks.b);
        this.d.put("ad_format", aks.b);
        this.d.put("e", aks.c);
    }

    public final void a(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.c.put("e", TextUtils.join(",", list));
        }
    }

    public final boolean a(aky aky) {
        return this.a.offer(aky);
    }
}
