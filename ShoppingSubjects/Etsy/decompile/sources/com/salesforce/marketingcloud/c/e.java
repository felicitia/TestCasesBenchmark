package com.salesforce.marketingcloud.c;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.j;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class e {
    /* access modifiers changed from: private */
    public static final String a = j.a(e.class);
    private String b;

    public static abstract class a {
        private Map<String, String> a;

        /* access modifiers changed from: 0000 */
        public abstract a a(long j);

        public abstract a a(d dVar);

        public abstract a a(String str);

        public final a a(String str, String str2) {
            if (str == null || str2 == null) {
                j.b(e.a, "header [%s:%s] had null key or value.", str, str2);
                return this;
            }
            if (this.a == null) {
                this.a = new HashMap();
            }
            this.a.put(str, str2.trim());
            return this;
        }

        /* access modifiers changed from: 0000 */
        public abstract a a(List<String> list);

        public abstract a a(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract String a();

        public abstract a b(String str);

        /* access modifiers changed from: 0000 */
        public abstract e b();

        public abstract a c(String str);

        public final e c() {
            ArrayList arrayList;
            int i = 0;
            if (this.a != null) {
                arrayList = new ArrayList(this.a.size() * 2);
                for (Entry entry : this.a.entrySet()) {
                    int i2 = i * 2;
                    arrayList.add(i2, entry.getKey());
                    arrayList.add(i2 + 1, entry.getValue());
                    i++;
                }
            } else {
                arrayList = new ArrayList(0);
            }
            a((List<String>) arrayList);
            if (a() == null) {
                c("");
            }
            return b();
        }

        public abstract a d(String str);
    }

    public static e a(Bundle bundle) {
        return i().a(bundle.getString(ResponseConstants.METHOD)).b(bundle.getString("requestBody")).a(bundle.getLong("connectionTimeout")).c(bundle.getString("contentType")).a(bundle.getBoolean("gzipRequest")).d(bundle.getString("url")).a((List<String>) bundle.getStringArrayList("headers")).a(d.values()[bundle.getInt("mcRequestId", 0)]).b().a(bundle.getString("tag"));
    }

    private static String a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append(10);
                } else {
                    try {
                        break;
                    } catch (Exception e) {
                        j.c(a, e, "Failed while closing stream.", new Object[0]);
                    }
                }
            } finally {
                try {
                    inputStream.close();
                    bufferedReader.close();
                } catch (Exception e2) {
                    j.c(a, e2, "Failed while closing stream.", new Object[0]);
                }
            }
        }
        return sb.toString();
    }

    private static void a(HttpURLConnection httpURLConnection, List<String> list) {
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                httpURLConnection.setRequestProperty((String) list.get(i), (String) list.get(i + 1));
            }
        }
    }

    public static a i() {
        return new a().a(30000).a(false);
    }

    public e a(String str) {
        this.b = str;
        return this;
    }

    public abstract String a();

    @Nullable
    public abstract String b();

    public abstract long c();

    public abstract String d();

    public abstract boolean e();

    public abstract String f();

    public abstract List<String> g();

    public abstract d h();

    @Nullable
    public String j() {
        return this.b;
    }

    public Bundle k() {
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.METHOD, a());
        bundle.putString("requestBody", b());
        bundle.putLong("connectionTimeout", c());
        bundle.putString("contentType", d());
        bundle.putBoolean("gzipRequest", e());
        bundle.putString("url", f());
        bundle.putStringArrayList("headers", (ArrayList) g());
        bundle.putString("tag", j());
        bundle.putInt("mcRequestId", h().ordinal());
        return bundle;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(15:3|4|(3:8|9|10)|14|(1:20)(1:18)|19|21|(3:23|(1:25)(1:27)|26)|28|29|30|31|32|33|(2:35|36)) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        com.salesforce.marketingcloud.j.c(a, r3, "Exception thrown while setting SSL socket factory.", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x010f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0142, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00ee */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0015] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0142  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.salesforce.marketingcloud.c.g l() {
        /*
            r9 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x0117 }
            java.lang.String r5 = r9.f()     // Catch:{ Exception -> 0x0117 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0117 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ Exception -> 0x0117 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ Exception -> 0x0117 }
            boolean r3 = r4 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5 = 21
            if (r3 == 0) goto L_0x0035
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r3 >= r5) goto L_0x0035
            r3 = r4
            javax.net.ssl.HttpsURLConnection r3 = (javax.net.ssl.HttpsURLConnection) r3     // Catch:{ Exception -> 0x002b, all -> 0x010f }
            com.salesforce.marketingcloud.c.h r6 = new com.salesforce.marketingcloud.c.h     // Catch:{ Exception -> 0x002b, all -> 0x010f }
            r6.<init>()     // Catch:{ Exception -> 0x002b, all -> 0x010f }
            r3.setSSLSocketFactory(r6)     // Catch:{ Exception -> 0x002b, all -> 0x010f }
            goto L_0x0035
        L_0x002b:
            r3 = move-exception
            java.lang.String r6 = a     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r7 = "Exception thrown while setting SSL socket factory."
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.salesforce.marketingcloud.j.c(r6, r3, r7, r8)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
        L_0x0035:
            java.lang.String r3 = r9.a()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r6 = "PATCH"
            boolean r3 = r3.equals(r6)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r3 == 0) goto L_0x0052
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r3 >= r5) goto L_0x0052
            java.lang.String r3 = "X-HTTP-Method-Override"
            java.lang.String r5 = "PATCH"
            r4.setRequestProperty(r3, r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r3 = "POST"
        L_0x004e:
            r4.setRequestMethod(r3)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            goto L_0x0057
        L_0x0052:
            java.lang.String r3 = r9.a()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            goto L_0x004e
        L_0x0057:
            r3 = 1
            r4.setDoInput(r3)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r4.setUseCaches(r2)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r4.setAllowUserInteraction(r2)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            long r5 = r9.c()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            int r5 = (int) r5     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r4.setConnectTimeout(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.util.List r5 = r9.g()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            a(r4, r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r5 = r9.b()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r5 == 0) goto L_0x00c6
            r4.setDoOutput(r3)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r3 = "Content-Type"
            java.lang.String r5 = r9.d()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r4.setRequestProperty(r3, r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            boolean r3 = r9.e()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r3 == 0) goto L_0x00b0
            java.lang.String r3 = "Content-Encoding"
            java.lang.String r5 = "gzip"
            r4.setRequestProperty(r3, r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.io.OutputStream r3 = r4.getOutputStream()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.util.zip.GZIPOutputStream r5 = new java.util.zip.GZIPOutputStream     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5.<init>(r3)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.io.OutputStreamWriter r6 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r7 = "UTF-8"
            r6.<init>(r5, r7)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r7 = r9.b()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r6.write(r7)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r6.close()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5.close()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
        L_0x00ac:
            r3.close()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            goto L_0x00c6
        L_0x00b0:
            java.io.OutputStream r3 = r4.getOutputStream()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r6 = "UTF-8"
            r5.<init>(r3, r6)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r6 = r9.b()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5.write(r6)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5.close()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            goto L_0x00ac
        L_0x00c6:
            com.salesforce.marketingcloud.c.g$a r3 = com.salesforce.marketingcloud.c.g.g()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            int r5 = r4.getResponseCode()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.salesforce.marketingcloud.c.g$a r3 = r3.a(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r5 = r4.getResponseMessage()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.salesforce.marketingcloud.c.g$a r3 = r3.b(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.util.Map r5 = r4.getHeaderFields()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.salesforce.marketingcloud.c.g$a r3 = r3.a(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ IOException -> 0x00ee }
            java.lang.String r5 = a(r5)     // Catch:{ IOException -> 0x00ee }
            r3.a(r5)     // Catch:{ IOException -> 0x00ee }
            goto L_0x00f9
        L_0x00ee:
            java.io.InputStream r5 = r4.getErrorStream()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r5 = a(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r3.a(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
        L_0x00f9:
            com.salesforce.marketingcloud.c.g$a r0 = r3.a(r0)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.salesforce.marketingcloud.c.g$a r0 = r0.b(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.salesforce.marketingcloud.c.g r0 = r0.a()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r4 == 0) goto L_0x013f
            r4.disconnect()
            return r0
        L_0x010f:
            r0 = move-exception
            goto L_0x0140
        L_0x0111:
            r0 = move-exception
            r3 = r4
            goto L_0x0118
        L_0x0114:
            r0 = move-exception
            r4 = r3
            goto L_0x0140
        L_0x0117:
            r0 = move-exception
        L_0x0118:
            java.lang.String r1 = "Unable to complete request: "
            java.lang.String r4 = a     // Catch:{ all -> 0x0114 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0114 }
            com.salesforce.marketingcloud.j.c(r4, r0, r1, r2)     // Catch:{ all -> 0x0114 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0114 }
            r2.<init>()     // Catch:{ all -> 0x0114 }
            r2.append(r1)     // Catch:{ all -> 0x0114 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0114 }
            r2.append(r0)     // Catch:{ all -> 0x0114 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0114 }
            r1 = -100
            com.salesforce.marketingcloud.c.g r0 = com.salesforce.marketingcloud.c.g.a(r0, r1)     // Catch:{ all -> 0x0114 }
            if (r3 == 0) goto L_0x013f
            r3.disconnect()
        L_0x013f:
            return r0
        L_0x0140:
            if (r4 == 0) goto L_0x0145
            r4.disconnect()
        L_0x0145:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.c.e.l():com.salesforce.marketingcloud.c.g");
    }
}
