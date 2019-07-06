package com.crittercism.internal;

import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class b implements bi {
    public long a = Long.MAX_VALUE;
    long b = Long.MAX_VALUE;
    boolean c = false;
    String d = bh.a.a();
    float e = 1.0f;
    c f = c.NOT_LOGGED_YET;
    long g = 0;
    public long h = 0;
    public int i = 0;
    public String j = "";
    public bm k = new bm(null);
    double[] l;
    public f m = new f();
    public String n;
    public a o = a.MOBILE;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;

    public static class a extends ce {
        public a(av avVar) {
            super(avVar);
        }

        public final bz a(as asVar, List<? extends bi> list) {
            if (list.size() == 0) {
                throw new IOException("No events provided");
            }
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(this.a.e);
            jSONArray2.put(this.a.a.a);
            jSONArray2.put(this.a.h());
            jSONArray2.put("5.8.10");
            jSONArray2.put(this.a.f.b.getInt("sessionIDSetting", 0));
            jSONArray.put(jSONArray2);
            JSONArray jSONArray3 = new JSONArray();
            jSONArray3.put(cp.a.a());
            jSONArray3.put(this.a.b());
            jSONArray3.put(Build.MODEL);
            jSONArray3.put("Android");
            jSONArray3.put(VERSION.RELEASE);
            jSONArray3.put(this.a.c());
            jSONArray3.put(this.a.d());
            jSONArray.put(jSONArray3);
            JSONArray jSONArray4 = new JSONArray();
            for (bi biVar : list) {
                jSONArray4.put(((b) biVar).g());
            }
            jSONArray.put(jSONArray4);
            try {
                jSONObject.put("d", jSONArray);
                return bz.a(new URL(asVar.a, "/api/apm/network"), jSONObject, this.b);
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    /* renamed from: com.crittercism.internal.b$b reason: collision with other inner class name */
    public static class C0017b implements com.crittercism.internal.az.b<b> {
        private C0017b() {
        }

        public /* synthetic */ C0017b(byte b) {
            this();
        }

        public final /* synthetic */ bi a(File file) {
            return b(file);
        }

        public final /* synthetic */ void a(bi biVar, OutputStream outputStream) {
            b bVar = (b) biVar;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("Sequence Number", bVar.d).put(ResponseConstants.RATE, (double) bVar.e).put("Request Method", bVar.j).put("Uri", bVar.a()).put("Time Stamp", cp.a.a(new Date(bVar.a))).put("Response Time", bVar.b()).put("Network Status", bVar.o.e).put("Bytes In", bVar.g).put("Bytes Out", bVar.h).put("Return Code", bVar.i).put("Error Type", bVar.k.a).put("Error Code", bVar.k.b);
                if (bVar.l != null) {
                    JSONArray jSONArray = new JSONArray();
                    if (bVar.l.length == 2) {
                        jSONArray.put(bVar.l[0]);
                        jSONArray.put(bVar.l[1]);
                        jSONObject.put(Includes.LOCATION, jSONArray);
                    }
                }
                outputStream.write(jSONObject.toString().getBytes());
            } catch (JSONException unused) {
                throw new IOException("Bad values pased to write to stream");
            }
        }

        private static b b(File file) {
            String b = cn.b(file);
            b bVar = new b();
            try {
                JSONObject jSONObject = new JSONObject(b);
                bVar.d = jSONObject.getString("Sequence Number");
                bVar.j = jSONObject.getString("Request Method");
                bVar.n = jSONObject.getString("Uri");
                bVar.a = cp.a.a(jSONObject.getString("Time Stamp"));
                bVar.b = bVar.a + jSONObject.getLong("Response Time");
                bVar.o = a.a(jSONObject.getInt("Network Status"));
                bVar.g = jSONObject.getLong("Bytes In");
                bVar.h = jSONObject.getLong("Bytes Out");
                bVar.i = jSONObject.getInt("Return Code");
                bVar.k = new bm(jSONObject.getInt("Error Type"), jSONObject.getInt("Error Code"));
                if (jSONObject.has(Includes.LOCATION)) {
                    JSONArray jSONArray = jSONObject.getJSONArray(Includes.LOCATION);
                    bVar.l = new double[]{jSONArray.getDouble(0), jSONArray.getDouble(1)};
                }
                bVar.e = (float) jSONObject.getDouble(ResponseConstants.RATE);
                return bVar;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            } catch (ParseException e2) {
                throw new IOException(e2.getMessage());
            }
        }
    }

    public enum c {
        NOT_LOGGED_YET("Not logged"),
        INPUT_STREAM_READ("InputStream.read()"),
        INPUT_STREAM_CLOSE("InputStream.close()"),
        SOCKET_CLOSE("Socket.close()"),
        LEGACY_JAVANET("Legacy java.net"),
        HTTP_CONTENT_LENGTH_PARSER("parse()"),
        INPUT_STREAM_FINISHED("finishedMessage()"),
        PARSING_INPUT_STREAM_LOG_ERROR("logError()"),
        SOCKET_IMPL_CONNECT("MonitoredSocketImpl.connect()"),
        SSL_SOCKET_START_HANDSHAKE("MonitoredSSLSocketKK.startHandshake"),
        UNIT_TEST("Unit test"),
        LOG_ENDPOINT("logEndpoint"),
        WEBVIEW_CLIENT_ON_PAGE_FINISHED("onPageFinished"),
        WEBVIEW_CLIENT_ON_RECEIVED_ERROR("onReceivedError");
        
        private String o;

        private c(String str) {
            this.o = str;
        }

        public final String toString() {
            return this.o;
        }
    }

    public b() {
    }

    public b(String str) {
        if (str != null) {
            this.n = str;
        }
    }

    public final void a(long j2) {
        this.r = true;
        this.g = j2;
    }

    public final void b(long j2) {
        this.s = true;
        this.h = j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
        if (r8.regionMatches(true, 0, "https:", 0, 6) != false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f6, code lost:
        if (r1.endsWith(r0) == false) goto L_0x00fa;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a() {
        /*
            r10 = this;
            java.lang.String r0 = r10.n
            if (r0 != 0) goto L_0x0111
            com.crittercism.internal.f r0 = r10.m
            java.lang.String r1 = "unknown-host"
            java.lang.String r2 = r0.b
            if (r2 == 0) goto L_0x000f
            java.lang.String r1 = r0.b
            goto L_0x0019
        L_0x000f:
            java.net.InetAddress r2 = r0.a
            if (r2 == 0) goto L_0x0019
            java.net.InetAddress r1 = r0.a
            java.lang.String r1 = r1.getHostName()
        L_0x0019:
            boolean r2 = r0.f
            if (r2 == 0) goto L_0x0049
            int r0 = r0.e
            if (r0 <= 0) goto L_0x0046
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = ":"
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            boolean r2 = r1.endsWith(r0)
            if (r2 != 0) goto L_0x0046
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            goto L_0x010f
        L_0x0046:
            r0 = r1
            goto L_0x010f
        L_0x0049:
            java.lang.String r8 = r0.c
            java.lang.String r9 = ""
            if (r8 == 0) goto L_0x006b
            r3 = 1
            r4 = 0
            java.lang.String r5 = "http:"
            r6 = 0
            r7 = 5
            r2 = r8
            boolean r2 = r2.regionMatches(r3, r4, r5, r6, r7)
            if (r2 != 0) goto L_0x0069
            r3 = 1
            r4 = 0
            java.lang.String r5 = "https:"
            r6 = 0
            r7 = 6
            r2 = r8
            boolean r2 = r2.regionMatches(r3, r4, r5, r6, r7)
            if (r2 == 0) goto L_0x006b
        L_0x0069:
            r2 = 1
            goto L_0x006c
        L_0x006b:
            r2 = 0
        L_0x006c:
            if (r2 == 0) goto L_0x0071
            r0 = r8
            goto L_0x010f
        L_0x0071:
            com.crittercism.internal.f$a r2 = r0.d
            if (r2 == 0) goto L_0x008f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r9)
            com.crittercism.internal.f$a r3 = r0.d
            java.lang.String r3 = r3.c
            r2.append(r3)
            java.lang.String r3 = ":"
            r2.append(r3)
            java.lang.String r9 = r2.toString()
        L_0x008f:
            java.lang.String r2 = "//"
            boolean r2 = r8.startsWith(r2)
            if (r2 == 0) goto L_0x00a7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r9)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            goto L_0x010f
        L_0x00a7:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r9)
            java.lang.String r3 = "//"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            boolean r3 = r8.startsWith(r1)
            if (r3 == 0) goto L_0x00ce
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            goto L_0x010f
        L_0x00ce:
            java.lang.String r3 = ""
            int r4 = r0.e
            if (r4 <= 0) goto L_0x00f9
            com.crittercism.internal.f$a r4 = r0.d
            if (r4 == 0) goto L_0x00e2
            com.crittercism.internal.f$a r4 = r0.d
            int r4 = r4.d
            int r5 = r0.e
            if (r4 == r5) goto L_0x00f9
        L_0x00e2:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = ":"
            r4.<init>(r5)
            int r0 = r0.e
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            boolean r4 = r1.endsWith(r0)
            if (r4 != 0) goto L_0x00f9
            goto L_0x00fa
        L_0x00f9:
            r0 = r3
        L_0x00fa:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            r3.append(r1)
            r3.append(r0)
            r3.append(r8)
            java.lang.String r0 = r3.toString()
        L_0x010f:
            r10.n = r0
        L_0x0111:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.b.a():java.lang.String");
    }

    public final void a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.n = str;
    }

    public final long b() {
        if (this.a == Long.MAX_VALUE || this.b == Long.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        return this.b - this.a;
    }

    public final void c(long j2) {
        this.a = j2;
        this.p = true;
    }

    public final void c() {
        if (!this.p && this.a == Long.MAX_VALUE) {
            this.a = System.currentTimeMillis();
        }
    }

    public final void d(long j2) {
        this.b = j2;
        this.q = true;
    }

    public final void d() {
        if (!this.q && this.b == Long.MAX_VALUE) {
            this.b = System.currentTimeMillis();
        }
    }

    public final void a(Location location) {
        this.l = new double[]{location.getLatitude(), location.getLongitude()};
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("URI            : ");
        sb.append(this.n);
        sb.append("\n");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append("URI Builder    : ");
        sb3.append(this.m.toString());
        sb3.append("\n");
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb4);
        sb5.append("\n");
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(sb6);
        sb7.append("Logged by      : ");
        sb7.append(this.f.toString());
        sb7.append("\n");
        String sb8 = sb7.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append(sb8);
        sb9.append("Error type:         : ");
        sb9.append(this.k.a);
        sb9.append("\n");
        String sb10 = sb9.toString();
        StringBuilder sb11 = new StringBuilder();
        sb11.append(sb10);
        sb11.append("Error code:         : ");
        sb11.append(this.k.b);
        sb11.append("\n");
        String sb12 = sb11.toString();
        StringBuilder sb13 = new StringBuilder();
        sb13.append(sb12);
        sb13.append("\n");
        String sb14 = sb13.toString();
        StringBuilder sb15 = new StringBuilder();
        sb15.append(sb14);
        sb15.append("Response time  : ");
        sb15.append(b());
        sb15.append("\n");
        String sb16 = sb15.toString();
        StringBuilder sb17 = new StringBuilder();
        sb17.append(sb16);
        sb17.append("Start time     : ");
        sb17.append(this.a);
        sb17.append("\n");
        String sb18 = sb17.toString();
        StringBuilder sb19 = new StringBuilder();
        sb19.append(sb18);
        sb19.append("End time       : ");
        sb19.append(this.b);
        sb19.append("\n");
        String sb20 = sb19.toString();
        StringBuilder sb21 = new StringBuilder();
        sb21.append(sb20);
        sb21.append("\n");
        String sb22 = sb21.toString();
        StringBuilder sb23 = new StringBuilder();
        sb23.append(sb22);
        sb23.append("Bytes out    : ");
        sb23.append(this.h);
        sb23.append("\n");
        String sb24 = sb23.toString();
        StringBuilder sb25 = new StringBuilder();
        sb25.append(sb24);
        sb25.append("Bytes in     : ");
        sb25.append(this.g);
        sb25.append("\n");
        String sb26 = sb25.toString();
        StringBuilder sb27 = new StringBuilder();
        sb27.append(sb26);
        sb27.append("\n");
        String sb28 = sb27.toString();
        StringBuilder sb29 = new StringBuilder();
        sb29.append(sb28);
        sb29.append("Response code  : ");
        sb29.append(this.i);
        sb29.append("\n");
        String sb30 = sb29.toString();
        StringBuilder sb31 = new StringBuilder();
        sb31.append(sb30);
        sb31.append("Request method : ");
        sb31.append(this.j);
        sb31.append("\n");
        String sb32 = sb31.toString();
        if (this.l == null) {
            return sb32;
        }
        StringBuilder sb33 = new StringBuilder();
        sb33.append(sb32);
        sb33.append("Location       : ");
        sb33.append(Arrays.toString(this.l));
        sb33.append("\n");
        return sb33.toString();
    }

    /* renamed from: e */
    public final JSONArray g() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(this.j);
            jSONArray.put(a());
            jSONArray.put(cp.a.a(new Date(this.a)));
            jSONArray.put(b());
            jSONArray.put(this.o.e);
            jSONArray.put(this.g);
            jSONArray.put(this.h);
            jSONArray.put(this.i);
            jSONArray.put(this.k.a);
            jSONArray.put(this.k.b);
            if (this.l == null) {
                return jSONArray;
            }
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(this.l[0]);
            jSONArray2.put(this.l[1]);
            jSONArray.put(jSONArray2);
            return jSONArray;
        } catch (Exception e2) {
            System.out.println("Failed to create statsArray");
            com.google.a.a.a.a.a.a.a(e2);
            return null;
        }
    }

    public final void a(Throwable th) {
        this.k = new bm(th);
    }

    public final void b(String str) {
        this.n = null;
        this.m.b = str;
    }

    public final String f() {
        return this.d;
    }
}
