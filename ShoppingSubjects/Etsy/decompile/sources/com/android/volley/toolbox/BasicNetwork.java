package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    public static final String ENCODING_GZIP = "gzip";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        r1 = r0;
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008d, code lost:
        r1 = r0;
        r5 = r13;
        r13 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009d, code lost:
        r2 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x009f, code lost:
        r1 = r0;
        r5 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a3, code lost:
        r2 = r4;
        r13 = r5;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a6, code lost:
        r13 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a9, code lost:
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ab, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ac, code lost:
        r5 = r1;
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00b6, code lost:
        r1 = r14.getStatusLine().getStatusCode();
        com.android.volley.VolleyLog.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r1), r21.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00d2, code lost:
        if (r13 != null) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00d4, code lost:
        r2 = new com.android.volley.NetworkResponse(r1, r13, r5, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00db, code lost:
        if (r1 == 401) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00e7, code lost:
        throw new com.android.volley.ServerError(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e8, code lost:
        attemptRetryOnException("auth", r8, new com.android.volley.AuthFailureError(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00fa, code lost:
        throw new com.android.volley.NetworkError((com.android.volley.NetworkResponse) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0100, code lost:
        throw new com.android.volley.NoConnectionError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0101, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0102, code lost:
        r1 = r0;
        r3 = new java.lang.StringBuilder();
        r3.append("Bad URL ");
        r3.append(r21.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x011d, code lost:
        throw new java.lang.RuntimeException(r3.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x011e, code lost:
        attemptRetryOnException("connection", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x012a, code lost:
        attemptRetryOnException("socket", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0101 A[ExcHandler: MalformedURLException (r0v0 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00fb A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r21) throws com.android.volley.VolleyError {
        /*
            r20 = this;
            r7 = r20
            r8 = r21
            long r9 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r11 = 1
            r12 = 0
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00af }
            r2.<init>()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00af }
            com.android.volley.Cache$Entry r3 = r21.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00af }
            r7.addCacheHeaders(r2, r3)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00af }
            com.android.volley.toolbox.HttpStack r3 = r7.mHttpStack     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00af }
            org.apache.http.HttpResponse r14 = r3.performRequest(r8, r2)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00af }
            org.apache.http.StatusLine r6 = r14.getStatusLine()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00ab }
            int r15 = r6.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00ab }
            org.apache.http.Header[] r2 = r14.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00ab }
            java.util.Map r5 = convertHeaders(r2)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00ab }
            r1 = 304(0x130, float:4.26E-43)
            if (r15 != r1) goto L_0x004e
            com.android.volley.NetworkResponse r2 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
            com.android.volley.Cache$Entry r3 = r21.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
            if (r3 != 0) goto L_0x003f
            r3 = 0
            goto L_0x0045
        L_0x003f:
            com.android.volley.Cache$Entry r3 = r21.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
            byte[] r3 = r3.data     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
        L_0x0045:
            r2.<init>(r1, r3, r5, r11)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
            return r2
        L_0x0049:
            r0 = move-exception
        L_0x004a:
            r1 = r0
            r13 = 0
            goto L_0x00b4
        L_0x004e:
            org.apache.http.HttpEntity r1 = r14.getEntity()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00a8 }
            if (r1 == 0) goto L_0x0061
            org.apache.http.HttpEntity r1 = r14.getEntity()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
            boolean r2 = r7.isGzipResponse(r5)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
            byte[] r1 = r7.entityToBytes(r1, r2)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x0049 }
            goto L_0x0063
        L_0x0061:
            byte[] r1 = new byte[r12]     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00a8 }
        L_0x0063:
            r4 = r1
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x00a2 }
            long r16 = r1 - r9
            r1 = r7
            r2 = r16
            r18 = r4
            r4 = r8
            r13 = r5
            r5 = r18
            r1.logSlowRequests(r2, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x009c }
            r1 = 200(0xc8, float:2.8E-43)
            if (r15 == r1) goto L_0x0092
            r1 = 201(0xc9, float:2.82E-43)
            if (r15 == r1) goto L_0x0092
            r1 = 202(0xca, float:2.83E-43)
            if (r15 == r1) goto L_0x0092
            r1 = 204(0xcc, float:2.86E-43)
            if (r15 == r1) goto L_0x0092
            java.io.IOException r1 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x008c }
            r1.<init>()     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x008c }
            throw r1     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x008c }
        L_0x008c:
            r0 = move-exception
            r1 = r0
            r5 = r13
            r13 = r18
            goto L_0x00b4
        L_0x0092:
            com.android.volley.NetworkResponse r1 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x009c }
            r2 = r18
            r1.<init>(r15, r2, r13, r12)     // Catch:{ SocketTimeoutException -> 0x012a, ConnectTimeoutException -> 0x011e, MalformedURLException -> 0x0101, IOException -> 0x009a }
            return r1
        L_0x009a:
            r0 = move-exception
            goto L_0x009f
        L_0x009c:
            r0 = move-exception
            r2 = r18
        L_0x009f:
            r1 = r0
            r5 = r13
            goto L_0x00a6
        L_0x00a2:
            r0 = move-exception
            r2 = r4
            r13 = r5
            r1 = r0
        L_0x00a6:
            r13 = r2
            goto L_0x00b4
        L_0x00a8:
            r0 = move-exception
            r13 = r5
            goto L_0x004a
        L_0x00ab:
            r0 = move-exception
            r5 = r1
            r13 = 0
            goto L_0x00b3
        L_0x00af:
            r0 = move-exception
            r5 = r1
            r13 = 0
            r14 = 0
        L_0x00b3:
            r1 = r0
        L_0x00b4:
            if (r14 == 0) goto L_0x00fb
            org.apache.http.StatusLine r1 = r14.getStatusLine()
            int r1 = r1.getStatusCode()
            java.lang.String r2 = "Unexpected response code %d for %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            r3[r12] = r4
            java.lang.String r4 = r21.getUrl()
            r3[r11] = r4
            com.android.volley.VolleyLog.e(r2, r3)
            if (r13 == 0) goto L_0x00f4
            com.android.volley.NetworkResponse r2 = new com.android.volley.NetworkResponse
            r2.<init>(r1, r13, r5, r12)
            r3 = 401(0x191, float:5.62E-43)
            if (r1 == r3) goto L_0x00e8
            r3 = 403(0x193, float:5.65E-43)
            if (r1 != r3) goto L_0x00e2
            goto L_0x00e8
        L_0x00e2:
            com.android.volley.ServerError r1 = new com.android.volley.ServerError
            r1.<init>(r2)
            throw r1
        L_0x00e8:
            java.lang.String r1 = "auth"
            com.android.volley.AuthFailureError r3 = new com.android.volley.AuthFailureError
            r3.<init>(r2)
            attemptRetryOnException(r1, r8, r3)
            goto L_0x0008
        L_0x00f4:
            com.android.volley.NetworkError r1 = new com.android.volley.NetworkError
            r2 = 0
            r1.<init>(r2)
            throw r1
        L_0x00fb:
            com.android.volley.NoConnectionError r2 = new com.android.volley.NoConnectionError
            r2.<init>(r1)
            throw r2
        L_0x0101:
            r0 = move-exception
            r1 = r0
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Bad URL "
            r3.append(r4)
            java.lang.String r4 = r21.getUrl()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3, r1)
            throw r2
        L_0x011e:
            java.lang.String r1 = "connection"
            com.android.volley.TimeoutError r2 = new com.android.volley.TimeoutError
            r2.<init>()
            attemptRetryOnException(r1, r8, r2)
            goto L_0x0008
        L_0x012a:
            java.lang.String r1 = "socket"
            com.android.volley.TimeoutError r2 = new com.android.volley.TimeoutError
            r2.<init>()
            attemptRetryOnException(r1, r8, r2)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private boolean isGzipResponse(Map<String, String> map) {
        boolean z = false;
        if (map == null || !map.containsKey(HEADER_CONTENT_ENCODING)) {
            return false;
        }
        String str = (String) map.get(HEADER_CONTENT_ENCODING);
        if (!TextUtils.isEmpty(str) && str.contains(ENCODING_GZIP)) {
            z = true;
        }
        return z;
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d(str, objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.serverDate > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.serverDate)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void logError(String str, String str2, long j) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(SystemClock.elapsedRealtime() - j), str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0067 A[Catch:{ IOException -> 0x006b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] entityToBytes(org.apache.http.HttpEntity r7, boolean r8) throws java.io.IOException, com.android.volley.ServerError {
        /*
            r6 = this;
            com.android.volley.toolbox.PoolingByteArrayOutputStream r0 = new com.android.volley.toolbox.PoolingByteArrayOutputStream
            com.android.volley.toolbox.ByteArrayPool r1 = r6.mPool
            long r2 = r7.getContentLength()
            int r2 = (int) r2
            r0.<init>(r1, r2)
            r1 = 0
            r2 = 0
            java.io.InputStream r3 = r7.getContent()     // Catch:{ all -> 0x0060 }
            if (r3 != 0) goto L_0x001c
            com.android.volley.ServerError r8 = new com.android.volley.ServerError     // Catch:{ all -> 0x001a }
            r8.<init>()     // Catch:{ all -> 0x001a }
            throw r8     // Catch:{ all -> 0x001a }
        L_0x001a:
            r8 = move-exception
            goto L_0x0062
        L_0x001c:
            if (r8 == 0) goto L_0x0024
            java.util.zip.GZIPInputStream r8 = new java.util.zip.GZIPInputStream     // Catch:{ all -> 0x001a }
            r8.<init>(r3)     // Catch:{ all -> 0x001a }
            goto L_0x0025
        L_0x0024:
            r8 = r3
        L_0x0025:
            com.android.volley.toolbox.ByteArrayPool r3 = r6.mPool     // Catch:{ all -> 0x005b }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r3 = r3.getBuf(r4)     // Catch:{ all -> 0x005b }
        L_0x002d:
            int r1 = r8.read(r3)     // Catch:{ all -> 0x0055 }
            r4 = -1
            if (r1 == r4) goto L_0x0038
            r0.write(r3, r2, r1)     // Catch:{ all -> 0x0055 }
            goto L_0x002d
        L_0x0038:
            byte[] r1 = r0.toByteArray()     // Catch:{ all -> 0x0055 }
            r7.consumeContent()     // Catch:{ IOException -> 0x0045 }
            if (r8 == 0) goto L_0x004c
            r8.close()     // Catch:{ IOException -> 0x0045 }
            goto L_0x004c
        L_0x0045:
            java.lang.String r7 = "Error occured when calling consumingContent"
            java.lang.Object[] r8 = new java.lang.Object[r2]
            com.android.volley.VolleyLog.v(r7, r8)
        L_0x004c:
            com.android.volley.toolbox.ByteArrayPool r7 = r6.mPool
            r7.returnBuf(r3)
            r0.close()
            return r1
        L_0x0055:
            r1 = move-exception
            r5 = r3
            r3 = r8
            r8 = r1
            r1 = r5
            goto L_0x0062
        L_0x005b:
            r3 = move-exception
            r5 = r3
            r3 = r8
            r8 = r5
            goto L_0x0062
        L_0x0060:
            r8 = move-exception
            r3 = r1
        L_0x0062:
            r7.consumeContent()     // Catch:{ IOException -> 0x006b }
            if (r3 == 0) goto L_0x0072
            r3.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x0072
        L_0x006b:
            java.lang.String r7 = "Error occured when calling consumingContent"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.android.volley.VolleyLog.v(r7, r2)
        L_0x0072:
            com.android.volley.toolbox.ByteArrayPool r7 = r6.mPool
            r7.returnBuf(r1)
            r0.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.entityToBytes(org.apache.http.HttpEntity, boolean):byte[]");
    }

    private static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
