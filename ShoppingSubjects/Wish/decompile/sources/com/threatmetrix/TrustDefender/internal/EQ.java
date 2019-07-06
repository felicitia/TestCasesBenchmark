package com.threatmetrix.TrustDefender.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

class EQ extends JR {

    /* renamed from: do reason: not valid java name */
    private static final String f157do = TL.m331if(EQ.class);

    /* renamed from: byte reason: not valid java name */
    private final AP f158byte;

    /* renamed from: char reason: not valid java name */
    private int f159char = -1;

    /* renamed from: else reason: not valid java name */
    private URL f160else = null;

    /* renamed from: try reason: not valid java name */
    private HttpURLConnection f161try = null;

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final void m62new() {
    }

    EQ(TN tn, Y y, List<String> list, AP ap) {
        super(tn, y, list);
        this.f158byte = ap;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r6v1 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r5v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r6v5, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: type inference failed for: r5v12, types: [java.net.HttpURLConnection, java.net.URLConnection] */
    /* JADX WARNING: type inference failed for: r6v17, types: [com.threatmetrix.TrustDefender.THMStatusCode] */
    /* JADX WARNING: type inference failed for: r6v19, types: [java.lang.Object, java.lang.RuntimeException] */
    /* JADX WARNING: type inference failed for: r6v23 */
    /* JADX WARNING: type inference failed for: r6v25 */
    /* JADX WARNING: type inference failed for: r6v27 */
    /* JADX WARNING: type inference failed for: r6v40, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r6v41 */
    /* JADX WARNING: type inference failed for: r6v42 */
    /* JADX WARNING: type inference failed for: r6v43 */
    /* JADX WARNING: type inference failed for: r6v44 */
    /* JADX WARNING: type inference failed for: r6v45 */
    /* JADX WARNING: type inference failed for: r6v46 */
    /* JADX WARNING: type inference failed for: r6v47 */
    /* JADX WARNING: type inference failed for: r6v48 */
    /* JADX WARNING: type inference failed for: r6v49 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v1
      assigns: []
      uses: []
      mth insns count: 219
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x014b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x002d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x002d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x002d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x013c A[Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0160 A[Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150, all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0173 A[Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150, all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0181 A[Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150, all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0184 A[Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150, all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01cd  */
    /* JADX WARNING: Unknown variable types count: 11 */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.HttpURLConnection m53do(java.lang.String r13, byte[] r14, boolean r15) {
        /*
            r12 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            r1.<init>(r13)     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            java.net.URI r2 = new java.net.URI     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            r2.<init>(r13)     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            java.net.ProxySelector r13 = java.net.ProxySelector.getDefault()     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            r3 = 1
            if (r13 != 0) goto L_0x0024
            java.lang.String r13 = f157do     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            java.lang.String r2 = "Default proxy is null"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r13, r2)     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            r13.<init>(r3)     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            java.net.Proxy r2 = java.net.Proxy.NO_PROXY     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            r13.add(r2)     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
            goto L_0x0028
        L_0x0024:
            java.util.List r13 = r13.select(r2)     // Catch:{ MalformedURLException -> 0x01f0, URISyntaxException -> 0x01e7, SecurityException -> 0x01de }
        L_0x0028:
            java.util.Iterator r13 = r13.iterator()
            r2 = r0
        L_0x002d:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x01d1
            java.lang.Object r4 = r13.next()
            java.net.Proxy r4 = (java.net.Proxy) r4
            java.net.URLConnection r5 = r1.openConnection(r4)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.Object r5 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.net.URLConnection r5 = (java.net.URLConnection) r5     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            boolean r6 = r5 instanceof java.net.HttpURLConnection     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            if (r6 != 0) goto L_0x0068
            java.lang.String r6 = f157do     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r8 = "Invalid connection type "
            r7.<init>(r8)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r5 = r5.getName()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r7.append(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r5 = " ignore it."
            r7.append(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r5 = r7.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r6, r5)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            goto L_0x002d
        L_0x0068:
            boolean r6 = r5 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            if (r6 == 0) goto L_0x0078
            com.threatmetrix.TrustDefender.internal.AP r6 = r12.f158byte     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            if (r6 == 0) goto L_0x0078
            r6 = r5
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            com.threatmetrix.TrustDefender.internal.AP r7 = r12.f158byte     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r6.setSSLSocketFactory(r7)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
        L_0x0078:
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            com.threatmetrix.TrustDefender.internal.HF r6 = r12.f251for     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            com.threatmetrix.TrustDefender.internal.TN r6 = (com.threatmetrix.TrustDefender.internal.TN) r6     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            int r7 = r6.f543int     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setConnectTimeout(r7)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            int r7 = r6.f543int     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setReadTimeout(r7)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setInstanceFollowRedirects(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r7 = 0
            r5.setUseCaches(r7)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setDoInput(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.util.Map r8 = r12.f254new     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.util.Set r8 = r8.entrySet()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
        L_0x009c:
            boolean r9 = r8.hasNext()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            if (r9 == 0) goto L_0x00b8
            java.lang.Object r9 = r8.next()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.Object r10 = r9.getKey()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.Object r9 = r9.getValue()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setRequestProperty(r10, r9)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            goto L_0x009c
        L_0x00b8:
            java.lang.String r6 = r6.m66do()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            if (r6 == 0) goto L_0x00c3
            java.lang.String r8 = "User-Agent"
            r5.setRequestProperty(r8, r6)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
        L_0x00c3:
            if (r14 == 0) goto L_0x00f5
            java.lang.String r6 = "POST"
            r5.setRequestMethod(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setDoOutput(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            if (r15 == 0) goto L_0x00d6
            java.lang.String r6 = "Content-Encoding"
            java.lang.String r8 = "gzip"
            r5.setRequestProperty(r6, r8)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
        L_0x00d6:
            java.lang.String r6 = "Content-Type"
            java.lang.String r8 = "application/x-www-form-urlencoded"
            r5.setRequestProperty(r6, r8)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r6 = "Content-Length"
            int r8 = r14.length     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.lang.String r8 = java.lang.Integer.toString(r8)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setRequestProperty(r6, r8)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            java.io.OutputStream r6 = r5.getOutputStream()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r6.write(r14)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r6.flush()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r6.close()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            goto L_0x00fd
        L_0x00f5:
            java.lang.String r6 = "GET"
            r5.setRequestMethod(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            r5.setDoOutput(r7)     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
        L_0x00fd:
            r5.connect()     // Catch:{ SSLPeerUnverifiedException -> 0x01b7, IOException -> 0x01ab, SecurityException -> 0x0185, IllegalArgumentException -> 0x0153, all -> 0x0150 }
            boolean r6 = r5 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ RuntimeException -> 0x0121 }
            if (r6 == 0) goto L_0x0139
            r6 = r5
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ RuntimeException -> 0x0121 }
            java.security.cert.Certificate[] r6 = r6.getServerCertificates()     // Catch:{ RuntimeException -> 0x0121 }
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch:{ RuntimeException -> 0x0121 }
            goto L_0x013a
        L_0x0110:
            r6 = move-exception
            r11 = r6
            r6 = r5
            r5 = r11
            goto L_0x0155
        L_0x0115:
            r6 = move-exception
            r11 = r6
            r6 = r5
            r5 = r11
            goto L_0x0187
        L_0x011b:
            r4 = move-exception
            goto L_0x01ad
        L_0x011e:
            r13 = move-exception
            goto L_0x01b9
        L_0x0121:
            r6 = move-exception
            java.lang.String r8 = f157do     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            java.lang.String r10 = "Unable to get server certificate"
            r9.<init>(r10)     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            java.lang.String r6 = r6.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            r9.append(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            java.lang.String r6 = r9.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r8, r6)     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
        L_0x0139:
            r6 = r0
        L_0x013a:
            if (r6 == 0) goto L_0x013d
            r7 = 1
        L_0x013d:
            java.lang.String r8 = f157do     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            com.threatmetrix.TrustDefender.THMStatusCode r6 = r12.m115do(r7, r6, r8)     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            r12.f252if = r6     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            com.threatmetrix.TrustDefender.THMStatusCode r6 = r12.f252if     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            com.threatmetrix.TrustDefender.THMStatusCode r7 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK     // Catch:{ SSLPeerUnverifiedException -> 0x011e, IOException -> 0x011b, SecurityException -> 0x0115, IllegalArgumentException -> 0x0110 }
            if (r6 != r7) goto L_0x014c
            return r5
        L_0x014c:
            if (r5 == 0) goto L_0x002d
            goto L_0x01b2
        L_0x0150:
            r13 = move-exception
            goto L_0x01cb
        L_0x0153:
            r5 = move-exception
            r6 = r0
        L_0x0155:
            java.lang.String r7 = f157do     // Catch:{ all -> 0x01a8 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a8 }
            java.lang.String r9 = "Connection failure: Invalid proxy "
            r8.<init>(r9)     // Catch:{ all -> 0x01a8 }
            if (r4 == 0) goto L_0x0173
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a8 }
            java.lang.String r10 = "type "
            r9.<init>(r10)     // Catch:{ all -> 0x01a8 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01a8 }
            r9.append(r4)     // Catch:{ all -> 0x01a8 }
            java.lang.String r4 = r9.toString()     // Catch:{ all -> 0x01a8 }
            goto L_0x0175
        L_0x0173:
            java.lang.String r4 = "proxy is null."
        L_0x0175:
            r8.append(r4)     // Catch:{ all -> 0x01a8 }
            java.lang.String r4 = r8.toString()     // Catch:{ all -> 0x01a8 }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r7, r4)     // Catch:{ all -> 0x01a8 }
            if (r2 != 0) goto L_0x0182
            r2 = r5
        L_0x0182:
            if (r6 == 0) goto L_0x002d
            goto L_0x01a3
        L_0x0185:
            r5 = move-exception
            r6 = r0
        L_0x0187:
            java.lang.String r7 = f157do     // Catch:{ all -> 0x01a8 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a8 }
            java.lang.String r9 = "Connection failure: Don't have permission to use this proxy."
            r8.<init>(r9)     // Catch:{ all -> 0x01a8 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01a8 }
            r8.append(r4)     // Catch:{ all -> 0x01a8 }
            java.lang.String r4 = r8.toString()     // Catch:{ all -> 0x01a8 }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r7, r4)     // Catch:{ all -> 0x01a8 }
            if (r2 != 0) goto L_0x01a1
            r2 = r5
        L_0x01a1:
            if (r6 == 0) goto L_0x002d
        L_0x01a3:
            r6.disconnect()
            goto L_0x002d
        L_0x01a8:
            r13 = move-exception
            r0 = r6
            goto L_0x01cb
        L_0x01ab:
            r4 = move-exception
            r5 = r0
        L_0x01ad:
            if (r2 != 0) goto L_0x01b0
            r2 = r4
        L_0x01b0:
            if (r5 == 0) goto L_0x002d
        L_0x01b2:
            r5.disconnect()
            goto L_0x002d
        L_0x01b7:
            r13 = move-exception
            r5 = r0
        L_0x01b9:
            java.lang.String r14 = f157do     // Catch:{ all -> 0x01c9 }
            java.lang.String r15 = "Peer unverified"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r14, r15, r13)     // Catch:{ all -> 0x01c9 }
            r12.m121if(r13)     // Catch:{ all -> 0x01c9 }
            if (r5 == 0) goto L_0x01c8
            r5.disconnect()
        L_0x01c8:
            return r0
        L_0x01c9:
            r13 = move-exception
            r0 = r5
        L_0x01cb:
            if (r0 == 0) goto L_0x01d0
            r0.disconnect()
        L_0x01d0:
            throw r13
        L_0x01d1:
            if (r2 == 0) goto L_0x01dd
            java.lang.String r13 = f157do
            java.lang.String r14 = "Cannot connect to remote host"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r13, r14, r2)
            r12.m121if(r2)
        L_0x01dd:
            return r0
        L_0x01de:
            r13 = move-exception
            java.lang.String r14 = f157do
            java.lang.String r15 = "Security manager denied access"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r14, r15, r13)
            return r0
        L_0x01e7:
            r13 = move-exception
            java.lang.String r14 = f157do
            java.lang.String r15 = "Invalid URL"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r14, r15, r13)
            return r0
        L_0x01f0:
            r13 = move-exception
            java.lang.String r14 = f157do
            java.lang.String r15 = "Invalid URL"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r14, r15, r13)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.EQ.m53do(java.lang.String, byte[], boolean):java.net.HttpURLConnection");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final long m59if(String str) {
        HttpURLConnection httpURLConnection = m53do(str, null, false);
        if (httpURLConnection == null) {
            TL.m332if(f157do, "Connection failure: can not create connection to ".concat(String.valueOf(str)));
            return -1;
        }
        this.f160else = httpURLConnection.getURL();
        this.f161try = httpURLConnection;
        try {
            long responseCode = (long) httpURLConnection.getResponseCode();
            this.f159char = (int) responseCode;
            return responseCode;
        } catch (IOException e) {
            String str2 = f157do;
            StringBuilder sb = new StringBuilder("Connection failure (");
            sb.append(str);
            sb.append("): ");
            TL.m337int(str2, sb.toString(), e);
            m121if((Exception) e);
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024  */
    /* renamed from: for reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] m54for(java.lang.String r3) throws java.io.IOException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            int r1 = r3.length()
            r0.<init>(r1)
            r1 = 0
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x0021 }
            r2.<init>(r0)     // Catch:{ all -> 0x0021 }
            byte[] r3 = r3.getBytes()     // Catch:{ all -> 0x001e }
            r2.write(r3)     // Catch:{ all -> 0x001e }
            r2.close()
            byte[] r3 = r0.toByteArray()
            return r3
        L_0x001e:
            r3 = move-exception
            r1 = r2
            goto L_0x0022
        L_0x0021:
            r3 = move-exception
        L_0x0022:
            if (r1 == 0) goto L_0x0027
            r1.close()
        L_0x0027:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.EQ.m54for(java.lang.String):byte[]");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final String m61int() {
        URL url = this.f160else;
        if (url == null) {
            return null;
        }
        return url.toString();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final String m58for() {
        URL url = this.f160else;
        if (url == null) {
            return null;
        }
        return url.getHost();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final InputStream m56do() throws IOException {
        if (this.f161try == null) {
            return null;
        }
        return this.f161try.getInputStream();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final void m60if() {
        if (this.f161try != null) {
            this.f161try.disconnect();
            this.f161try = null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: case reason: not valid java name */
    public final int m55case() {
        return this.f159char;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final long m57for(String str, X x) {
        String str2;
        boolean z = false;
        if (x == null) {
            str2 = "";
        } else {
            try {
                StringBuilder sb = new StringBuilder();
                boolean z2 = true;
                for (Entry entry : x.entrySet()) {
                    if (z2) {
                        z2 = false;
                    } else {
                        sb.append("&");
                    }
                    sb.append(NK.m223new((String) entry.getKey()));
                    sb.append("=");
                    sb.append(NK.m223new((String) entry.getValue()));
                }
                str2 = sb.toString();
            } catch (InterruptedException unused) {
                return -1;
            }
        }
        byte[] bArr = null;
        if (((TN) this.f251for).f541for) {
            try {
                bArr = m54for(str2);
                z = true;
            } catch (IOException e) {
                TL.m337int(f157do, "Cannot compress!", e);
            }
        }
        if (bArr == null) {
            bArr = str2.getBytes();
        }
        HttpURLConnection httpURLConnection = m53do(str, bArr, z);
        if (httpURLConnection == null) {
            return -1;
        }
        this.f160else = httpURLConnection.getURL();
        this.f161try = httpURLConnection;
        try {
            long responseCode = (long) httpURLConnection.getResponseCode();
            this.f159char = (int) responseCode;
            return responseCode;
        } catch (IOException e2) {
            TL.m337int(f157do, "Cannot post!", e2);
            return -1;
        }
    }
}
