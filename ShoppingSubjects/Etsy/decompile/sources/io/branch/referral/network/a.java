package io.branch.referral.network;

import android.content.Context;
import io.branch.referral.m;
import io.branch.referral.network.BranchRemoteInterface.BranchRemoteException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* compiled from: BranchRemoteInterfaceUrlConnection */
public class a extends BranchRemoteInterface {
    m a;

    a(Context context) {
        this.a = m.a(context);
    }

    public io.branch.referral.network.BranchRemoteInterface.a a(String str) throws BranchRemoteException {
        return a(str, 0);
    }

    public io.branch.referral.network.BranchRemoteInterface.a a(String str, JSONObject jSONObject) throws BranchRemoteException {
        return a(str, jSONObject, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00cf, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x010c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        com.google.a.a.a.a.a.a.a(r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:55:0x00d3, B:61:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0101 A[SYNTHETIC, Splitter:B:61:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x011c A[SYNTHETIC, Splitter:B:70:0x011c] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x014d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.network.BranchRemoteInterface.a a(java.lang.String r8, int r9) throws io.branch.referral.network.BranchRemoteInterface.BranchRemoteException {
        /*
            r7 = this;
            r0 = -113(0xffffffffffffff8f, float:NaN)
            r1 = 0
            io.branch.referral.m r2 = r7.a     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            int r2 = r2.b()     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            if (r2 > 0) goto L_0x000d
            r2 = 3000(0xbb8, float:4.204E-42)
        L_0x000d:
            java.lang.String r3 = "?"
            boolean r3 = r8.contains(r3)     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            if (r3 == 0) goto L_0x0018
            java.lang.String r3 = "&"
            goto L_0x001a
        L_0x0018:
            java.lang.String r3 = "?"
        L_0x001a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            r4.<init>()     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            r4.append(r8)     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            r4.append(r3)     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            java.lang.String r3 = "retryNumber"
            r4.append(r3)     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            java.lang.String r3 = "="
            r4.append(r3)     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            r4.append(r9)     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            java.lang.String r3 = r4.toString()     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            java.net.URL r4 = new java.net.URL     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            r4.<init>(r3)     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            java.net.URLConnection r3 = r4.openConnection()     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            javax.net.ssl.HttpsURLConnection r3 = (javax.net.ssl.HttpsURLConnection) r3     // Catch:{ SocketException -> 0x0124, SocketTimeoutException -> 0x00f9, IOException -> 0x00d2 }
            r3.setConnectTimeout(r2)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            r3.setReadTimeout(r2)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            int r2 = r3.getResponseCode()     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            r4 = 500(0x1f4, float:7.0E-43)
            if (r2 < r4) goto L_0x0072
            io.branch.referral.m r4 = r7.a     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            int r4 = r4.c()     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            if (r9 >= r4) goto L_0x0072
            io.branch.referral.m r1 = r7.a     // Catch:{ InterruptedException -> 0x0062 }
            int r1 = r1.d()     // Catch:{ InterruptedException -> 0x0062 }
            long r1 = (long) r1     // Catch:{ InterruptedException -> 0x0062 }
            java.lang.Thread.sleep(r1)     // Catch:{ InterruptedException -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r1 = move-exception
            com.google.a.a.a.a.a.a.a(r1)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
        L_0x0066:
            int r9 = r9 + 1
            io.branch.referral.network.BranchRemoteInterface$a r1 = r7.a(r8, r9)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            if (r3 == 0) goto L_0x0071
            r3.disconnect()
        L_0x0071:
            return r1
        L_0x0072:
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 == r4) goto L_0x008f
            java.io.InputStream r4 = r3.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00a2 }
            if (r4 == 0) goto L_0x008f
            io.branch.referral.network.BranchRemoteInterface$a r4 = new io.branch.referral.network.BranchRemoteInterface$a     // Catch:{ FileNotFoundException -> 0x00a2 }
            java.io.InputStream r5 = r3.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00a2 }
            java.lang.String r5 = r7.a(r5)     // Catch:{ FileNotFoundException -> 0x00a2 }
            r4.<init>(r5, r2)     // Catch:{ FileNotFoundException -> 0x00a2 }
            if (r3 == 0) goto L_0x008e
            r3.disconnect()
        L_0x008e:
            return r4
        L_0x008f:
            io.branch.referral.network.BranchRemoteInterface$a r4 = new io.branch.referral.network.BranchRemoteInterface$a     // Catch:{ FileNotFoundException -> 0x00a2 }
            java.io.InputStream r5 = r3.getInputStream()     // Catch:{ FileNotFoundException -> 0x00a2 }
            java.lang.String r5 = r7.a(r5)     // Catch:{ FileNotFoundException -> 0x00a2 }
            r4.<init>(r5, r2)     // Catch:{ FileNotFoundException -> 0x00a2 }
            if (r3 == 0) goto L_0x00a1
            r3.disconnect()
        L_0x00a1:
            return r4
        L_0x00a2:
            java.lang.String r4 = "BranchSDK"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            r5.<init>()     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            java.lang.String r6 = "A resource conflict occurred with this request "
            r5.append(r6)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            r5.append(r8)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            java.lang.String r5 = r5.toString()     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            io.branch.referral.m.b(r4, r5)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            io.branch.referral.network.BranchRemoteInterface$a r4 = new io.branch.referral.network.BranchRemoteInterface$a     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            r4.<init>(r1, r2)     // Catch:{ SocketException -> 0x00cc, SocketTimeoutException -> 0x00ca, IOException -> 0x00c7, all -> 0x00c3 }
            if (r3 == 0) goto L_0x00c2
            r3.disconnect()
        L_0x00c2:
            return r4
        L_0x00c3:
            r8 = move-exception
            r1 = r3
            goto L_0x014b
        L_0x00c7:
            r8 = move-exception
            r1 = r3
            goto L_0x00d3
        L_0x00ca:
            r1 = r3
            goto L_0x00f9
        L_0x00cc:
            r8 = move-exception
            r1 = r3
            goto L_0x0125
        L_0x00cf:
            r8 = move-exception
            goto L_0x014b
        L_0x00d2:
            r8 = move-exception
        L_0x00d3:
            java.lang.Class r9 = r7.getClass()     // Catch:{ all -> 0x00cf }
            java.lang.String r9 = r9.getSimpleName()     // Catch:{ all -> 0x00cf }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cf }
            r2.<init>()     // Catch:{ all -> 0x00cf }
            java.lang.String r3 = "Branch connect exception: "
            r2.append(r3)     // Catch:{ all -> 0x00cf }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x00cf }
            r2.append(r8)     // Catch:{ all -> 0x00cf }
            java.lang.String r8 = r2.toString()     // Catch:{ all -> 0x00cf }
            io.branch.referral.m.b(r9, r8)     // Catch:{ all -> 0x00cf }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r8 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00cf }
            r8.<init>(r0)     // Catch:{ all -> 0x00cf }
            throw r8     // Catch:{ all -> 0x00cf }
        L_0x00f9:
            io.branch.referral.m r0 = r7.a     // Catch:{ all -> 0x00cf }
            int r0 = r0.c()     // Catch:{ all -> 0x00cf }
            if (r9 >= r0) goto L_0x011c
            io.branch.referral.m r0 = r7.a     // Catch:{ InterruptedException -> 0x010c }
            int r0 = r0.d()     // Catch:{ InterruptedException -> 0x010c }
            long r2 = (long) r0     // Catch:{ InterruptedException -> 0x010c }
            java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x010c }
            goto L_0x0110
        L_0x010c:
            r0 = move-exception
            com.google.a.a.a.a.a.a.a(r0)     // Catch:{ all -> 0x00cf }
        L_0x0110:
            int r9 = r9 + 1
            io.branch.referral.network.BranchRemoteInterface$a r8 = r7.a(r8, r9)     // Catch:{ all -> 0x00cf }
            if (r1 == 0) goto L_0x011b
            r1.disconnect()
        L_0x011b:
            return r8
        L_0x011c:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r8 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00cf }
            r9 = -111(0xffffffffffffff91, float:NaN)
            r8.<init>(r9)     // Catch:{ all -> 0x00cf }
            throw r8     // Catch:{ all -> 0x00cf }
        L_0x0124:
            r8 = move-exception
        L_0x0125:
            java.lang.Class r9 = r7.getClass()     // Catch:{ all -> 0x00cf }
            java.lang.String r9 = r9.getSimpleName()     // Catch:{ all -> 0x00cf }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cf }
            r2.<init>()     // Catch:{ all -> 0x00cf }
            java.lang.String r3 = "Http connect exception: "
            r2.append(r3)     // Catch:{ all -> 0x00cf }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x00cf }
            r2.append(r8)     // Catch:{ all -> 0x00cf }
            java.lang.String r8 = r2.toString()     // Catch:{ all -> 0x00cf }
            io.branch.referral.m.b(r9, r8)     // Catch:{ all -> 0x00cf }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r8 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00cf }
            r8.<init>(r0)     // Catch:{ all -> 0x00cf }
            throw r8     // Catch:{ all -> 0x00cf }
        L_0x014b:
            if (r1 == 0) goto L_0x0150
            r1.disconnect()
        L_0x0150:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.a.a(java.lang.String, int):io.branch.referral.network.BranchRemoteInterface$a");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d1, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d2, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0151, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        com.google.a.a.a.a.a.a.a(r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:66:0x0116, B:72:0x0146] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0146 A[SYNTHETIC, Splitter:B:72:0x0146] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0160 A[SYNTHETIC, Splitter:B:81:0x0160] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.network.BranchRemoteInterface.a a(java.lang.String r9, org.json.JSONObject r10, int r11) throws io.branch.referral.network.BranchRemoteInterface.BranchRemoteException {
        /*
            r8 = this;
            io.branch.referral.m r0 = r8.a
            int r0 = r0.b()
            if (r0 > 0) goto L_0x000a
            r0 = 3000(0xbb8, float:4.204E-42)
        L_0x000a:
            java.lang.String r1 = "retryNumber"
            r10.put(r1, r11)     // Catch:{ JSONException -> 0x000f }
        L_0x000f:
            r1 = 500(0x1f4, float:7.0E-43)
            r2 = 0
            r3 = 1
            java.net.URL r4 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x013e, IOException -> 0x0115, Exception -> 0x00d5 }
            r4.<init>(r9)     // Catch:{ SocketTimeoutException -> 0x013e, IOException -> 0x0115, Exception -> 0x00d5 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ SocketTimeoutException -> 0x013e, IOException -> 0x0115, Exception -> 0x00d5 }
            javax.net.ssl.HttpsURLConnection r4 = (javax.net.ssl.HttpsURLConnection) r4     // Catch:{ SocketTimeoutException -> 0x013e, IOException -> 0x0115, Exception -> 0x00d5 }
            r4.setConnectTimeout(r0)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r4.setReadTimeout(r0)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r4.setDoInput(r3)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r4.setDoOutput(r3)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.lang.String r0 = "Content-Type"
            java.lang.String r5 = "application/json"
            r4.setRequestProperty(r0, r5)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.lang.String r0 = "Accept"
            java.lang.String r5 = "application/json"
            r4.setRequestProperty(r0, r5)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.lang.String r0 = "POST"
            r4.setRequestMethod(r0)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.io.OutputStream r5 = r4.getOutputStream()     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r0.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.lang.String r5 = r10.toString()     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r0.write(r5)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r0.flush()     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            int r0 = r4.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            if (r0 < r1) goto L_0x0079
            io.branch.referral.m r5 = r8.a     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            int r5 = r5.c()     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            if (r11 >= r5) goto L_0x0079
            io.branch.referral.m r0 = r8.a     // Catch:{ InterruptedException -> 0x0069 }
            int r0 = r0.d()     // Catch:{ InterruptedException -> 0x0069 }
            long r5 = (long) r0     // Catch:{ InterruptedException -> 0x0069 }
            java.lang.Thread.sleep(r5)     // Catch:{ InterruptedException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r0 = move-exception
            com.google.a.a.a.a.a.a.a(r0)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
        L_0x006d:
            int r11 = r11 + 1
            io.branch.referral.network.BranchRemoteInterface$a r0 = r8.a(r9, r10, r11)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            if (r4 == 0) goto L_0x0078
            r4.disconnect()
        L_0x0078:
            return r0
        L_0x0079:
            r5 = 200(0xc8, float:2.8E-43)
            if (r0 == r5) goto L_0x0096
            java.io.InputStream r5 = r4.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00a9 }
            if (r5 == 0) goto L_0x0096
            io.branch.referral.network.BranchRemoteInterface$a r5 = new io.branch.referral.network.BranchRemoteInterface$a     // Catch:{ FileNotFoundException -> 0x00a9 }
            java.io.InputStream r6 = r4.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00a9 }
            java.lang.String r6 = r8.a(r6)     // Catch:{ FileNotFoundException -> 0x00a9 }
            r5.<init>(r6, r0)     // Catch:{ FileNotFoundException -> 0x00a9 }
            if (r4 == 0) goto L_0x0095
            r4.disconnect()
        L_0x0095:
            return r5
        L_0x0096:
            io.branch.referral.network.BranchRemoteInterface$a r5 = new io.branch.referral.network.BranchRemoteInterface$a     // Catch:{ FileNotFoundException -> 0x00a9 }
            java.io.InputStream r6 = r4.getInputStream()     // Catch:{ FileNotFoundException -> 0x00a9 }
            java.lang.String r6 = r8.a(r6)     // Catch:{ FileNotFoundException -> 0x00a9 }
            r5.<init>(r6, r0)     // Catch:{ FileNotFoundException -> 0x00a9 }
            if (r4 == 0) goto L_0x00a8
            r4.disconnect()
        L_0x00a8:
            return r5
        L_0x00a9:
            java.lang.String r5 = "BranchSDK"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.lang.String r7 = "A resource conflict occurred with this request "
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r6.append(r9)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            java.lang.String r6 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            io.branch.referral.m.b(r5, r6)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            io.branch.referral.network.BranchRemoteInterface$a r5 = new io.branch.referral.network.BranchRemoteInterface$a     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            r5.<init>(r2, r0)     // Catch:{ SocketTimeoutException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00ca }
            if (r4 == 0) goto L_0x00c9
            r4.disconnect()
        L_0x00c9:
            return r5
        L_0x00ca:
            r9 = move-exception
            goto L_0x00d7
        L_0x00cc:
            r9 = move-exception
            r2 = r4
            goto L_0x0116
        L_0x00cf:
            r2 = r4
            goto L_0x013e
        L_0x00d1:
            r9 = move-exception
            r4 = r2
            goto L_0x0168
        L_0x00d5:
            r9 = move-exception
            r4 = r2
        L_0x00d7:
            java.lang.Class r10 = r8.getClass()     // Catch:{ all -> 0x0113 }
            java.lang.String r10 = r10.getSimpleName()     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r11.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = "Exception: "
            r11.append(r0)     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = r9.getMessage()     // Catch:{ all -> 0x0113 }
            r11.append(r0)     // Catch:{ all -> 0x0113 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0113 }
            io.branch.referral.m.b(r10, r11)     // Catch:{ all -> 0x0113 }
            int r10 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0113 }
            r11 = 11
            if (r10 < r11) goto L_0x0108
            boolean r9 = r9 instanceof android.os.NetworkOnMainThreadException     // Catch:{ all -> 0x0113 }
            if (r9 == 0) goto L_0x0108
            java.lang.String r9 = "BranchSDK"
            java.lang.String r10 = "Branch Error: Don't call our synchronous methods on the main thread!!!"
            android.util.Log.i(r9, r10)     // Catch:{ all -> 0x0113 }
        L_0x0108:
            io.branch.referral.network.BranchRemoteInterface$a r9 = new io.branch.referral.network.BranchRemoteInterface$a     // Catch:{ all -> 0x0113 }
            r9.<init>(r2, r1)     // Catch:{ all -> 0x0113 }
            if (r4 == 0) goto L_0x0112
            r4.disconnect()
        L_0x0112:
            return r9
        L_0x0113:
            r9 = move-exception
            goto L_0x0168
        L_0x0115:
            r9 = move-exception
        L_0x0116:
            java.lang.Class r10 = r8.getClass()     // Catch:{ all -> 0x00d1 }
            java.lang.String r10 = r10.getSimpleName()     // Catch:{ all -> 0x00d1 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d1 }
            r11.<init>()     // Catch:{ all -> 0x00d1 }
            java.lang.String r0 = "Http connect exception: "
            r11.append(r0)     // Catch:{ all -> 0x00d1 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x00d1 }
            r11.append(r9)     // Catch:{ all -> 0x00d1 }
            java.lang.String r9 = r11.toString()     // Catch:{ all -> 0x00d1 }
            io.branch.referral.m.b(r10, r9)     // Catch:{ all -> 0x00d1 }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r9 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00d1 }
            r10 = -113(0xffffffffffffff8f, float:NaN)
            r9.<init>(r10)     // Catch:{ all -> 0x00d1 }
            throw r9     // Catch:{ all -> 0x00d1 }
        L_0x013e:
            io.branch.referral.m r0 = r8.a     // Catch:{ all -> 0x00d1 }
            int r0 = r0.c()     // Catch:{ all -> 0x00d1 }
            if (r11 >= r0) goto L_0x0160
            io.branch.referral.m r0 = r8.a     // Catch:{ InterruptedException -> 0x0151 }
            int r0 = r0.d()     // Catch:{ InterruptedException -> 0x0151 }
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0151 }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0151 }
            goto L_0x0155
        L_0x0151:
            r0 = move-exception
            com.google.a.a.a.a.a.a.a(r0)     // Catch:{ all -> 0x00d1 }
        L_0x0155:
            int r11 = r11 + r3
            io.branch.referral.network.BranchRemoteInterface$a r9 = r8.a(r9, r10, r11)     // Catch:{ all -> 0x00d1 }
            if (r2 == 0) goto L_0x015f
            r2.disconnect()
        L_0x015f:
            return r9
        L_0x0160:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r9 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x00d1 }
            r10 = -111(0xffffffffffffff91, float:NaN)
            r9.<init>(r10)     // Catch:{ all -> 0x00d1 }
            throw r9     // Catch:{ all -> 0x00d1 }
        L_0x0168:
            if (r4 == 0) goto L_0x016d
            r4.disconnect()
        L_0x016d:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.a.a(java.lang.String, org.json.JSONObject, int):io.branch.referral.network.BranchRemoteInterface$a");
    }

    private String a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                return new BufferedReader(new InputStreamReader(inputStream)).readLine();
            } catch (IOException unused) {
            }
        }
        return null;
    }
}
