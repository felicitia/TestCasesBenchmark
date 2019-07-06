package defpackage;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.WeakHashMap;

/* renamed from: w reason: default package */
/* compiled from: GA */
public final class w extends q {
    private final int b;

    public w(int i) {
        this.b = i;
    }

    public final void c() {
        WeakHashMap e = e();
        switch (this.b) {
            case 0:
                if (!e.isEmpty()) {
                    super.a((String) e.get(c.y[0]));
                    return;
                }
                break;
            case 1:
                ArrayList arrayList = new ArrayList();
                if (VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) {
                    arrayList.add(Build.CPU_ABI);
                    if (!Build.CPU_ABI2.isEmpty()) {
                        arrayList.add(Build.CPU_ABI2);
                    }
                } else {
                    arrayList.addAll(Arrays.asList(Build.SUPPORTED_ABIS));
                }
                if (!arrayList.isEmpty()) {
                    super.a(TextUtils.join(",", arrayList));
                    return;
                }
                break;
            case 2:
                if (!e.isEmpty()) {
                    super.a((String) e.get(c.y[1]));
                    return;
                }
                break;
            case 3:
                int d = d();
                if (d != -1) {
                    super.a(Integer.toString(d));
                    return;
                }
                break;
            case 4:
                if (!e.isEmpty()) {
                    super.a((String) e.get(c.y[2]));
                    return;
                }
                break;
            case 5:
                if (!e.isEmpty()) {
                    super.a((String) e.get(c.y[3]));
                    break;
                }
                break;
        }
    }

    private static int d() {
        try {
            int b2 = b(c.x[2]);
            if (b2 == -1) {
                b2 = b(c.x[3]);
            }
            if (b2 == -1) {
                File file = new File(c.x[1]);
                if (!file.exists() || !file.canRead() || !file.isDirectory()) {
                    return -1;
                }
                File[] listFiles = file.listFiles(new FileFilter() {
                    public final boolean accept(File file) {
                        return file != null && file.getName().matches("cpu[\\d]+$");
                    }
                });
                if (listFiles == null) {
                    return -1;
                }
                b2 = listFiles.length;
            }
            return b2;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:5|6|(2:8|(1:10)(2:11|12))|13|14|15|16|17) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0032 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0042 A[SYNTHETIC, Splitter:B:27:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0047 A[SYNTHETIC, Splitter:B:31:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x004e A[SYNTHETIC, Splitter:B:39:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0053 A[SYNTHETIC, Splitter:B:43:0x0053] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int b(java.lang.String r4) {
        /*
            r0 = -1
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x004b, all -> 0x003d }
            r2.<init>(r4)     // Catch:{ IOException -> 0x004b, all -> 0x003d }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x004c, all -> 0x003a }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x004c, all -> 0x003a }
            r3.<init>(r2)     // Catch:{ IOException -> 0x004c, all -> 0x003a }
            r4.<init>(r3)     // Catch:{ IOException -> 0x004c, all -> 0x003a }
            java.lang.String r1 = r4.readLine()     // Catch:{ IOException -> 0x0038, all -> 0x0036 }
            if (r1 == 0) goto L_0x002f
            java.lang.String r3 = "0-[\\d]+$"
            boolean r3 = r1.matches(r3)     // Catch:{ IOException -> 0x0038, all -> 0x0036 }
            if (r3 != 0) goto L_0x0020
            goto L_0x002f
        L_0x0020:
            r3 = 2
            java.lang.String r1 = r1.substring(r3)     // Catch:{ IOException -> 0x0038, all -> 0x0036 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ IOException -> 0x0038, all -> 0x0036 }
            int r1 = r1.intValue()     // Catch:{ IOException -> 0x0038, all -> 0x0036 }
            int r0 = r1 + 1
        L_0x002f:
            r4.close()     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            r2.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            return r0
        L_0x0036:
            r0 = move-exception
            goto L_0x0040
        L_0x0038:
            r1 = r4
            goto L_0x004c
        L_0x003a:
            r0 = move-exception
            r4 = r1
            goto L_0x0040
        L_0x003d:
            r0 = move-exception
            r4 = r1
            r2 = r4
        L_0x0040:
            if (r4 == 0) goto L_0x0045
            r4.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            if (r2 == 0) goto L_0x004a
            r2.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            throw r0
        L_0x004b:
            r2 = r1
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()     // Catch:{ IOException -> 0x0051 }
        L_0x0051:
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w.b(java.lang.String):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        if (r2 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006b, code lost:
        if (r2 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006e, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.WeakHashMap<java.lang.String, java.lang.String> e() {
        /*
            java.util.WeakHashMap r0 = new java.util.WeakHashMap
            r0.<init>()
            java.io.File r1 = new java.io.File
            java.lang.String[] r2 = defpackage.c.x
            r3 = 0
            r2 = r2[r3]
            r1.<init>(r2)
            r2 = 0
            boolean r4 = r1.exists()     // Catch:{ Exception -> 0x0059 }
            if (r4 == 0) goto L_0x0050
            boolean r4 = r1.canRead()     // Catch:{ Exception -> 0x0059 }
            if (r4 == 0) goto L_0x0050
            java.util.Scanner r4 = new java.util.Scanner     // Catch:{ Exception -> 0x0059 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0059 }
        L_0x0021:
            boolean r1 = r4.hasNextLine()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            if (r1 == 0) goto L_0x0049
            java.lang.String r1 = r4.nextLine()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.lang.String r2 = ":"
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            int r2 = r1.length     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r5 = 1
            if (r2 <= r5) goto L_0x0021
            r2 = r1[r3]     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.lang.String r2 = r2.trim()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.lang.String r2 = r2.toLowerCase()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r1 = r1[r5]     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r0.put(r2, r1)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            goto L_0x0021
        L_0x0049:
            r2 = r4
            goto L_0x0050
        L_0x004b:
            r0 = move-exception
            goto L_0x006f
        L_0x004d:
            r1 = move-exception
            r2 = r4
            goto L_0x005a
        L_0x0050:
            if (r2 == 0) goto L_0x006e
        L_0x0052:
            r2.close()
            goto L_0x006e
        L_0x0056:
            r0 = move-exception
            r4 = r2
            goto L_0x006f
        L_0x0059:
            r1 = move-exception
        L_0x005a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0056 }
            java.lang.String r4 = "M33"
            r3.<init>(r4)     // Catch:{ all -> 0x0056 }
            r3.append(r1)     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0056 }
            defpackage.al.b(r1)     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x006e
            goto L_0x0052
        L_0x006e:
            return r0
        L_0x006f:
            if (r4 == 0) goto L_0x0074
            r4.close()
        L_0x0074:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w.e():java.util.WeakHashMap");
    }
}
