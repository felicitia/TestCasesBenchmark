package com.threatmetrix.TrustDefender.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import okio.Sink;

public class RI extends HF {

    /* renamed from: for reason: not valid java name */
    private static final String f518for = TL.m331if(RI.class);

    /* renamed from: int reason: not valid java name */
    private static final O f519int = new O();

    /* renamed from: do reason: not valid java name */
    OkHttpClient f520do;

    static final class O implements Interceptor {
        O() {
        }

        public final Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (request.body() == null || request.header("Content-Encoding") != null) {
                return chain.proceed(request);
            }
            Builder header = request.newBuilder().header("Content-Encoding", "gzip");
            String method = request.method();
            final RequestBody body = request.body();
            final AnonymousClass1 r3 = new RequestBody() {
                public final long contentLength() {
                    return -1;
                }

                public final MediaType contentType() {
                    return body.contentType();
                }

                public final void writeTo(BufferedSink bufferedSink) throws IOException {
                    BufferedSink buffer = Okio.buffer((Sink) new GzipSink(bufferedSink));
                    body.writeTo(buffer);
                    buffer.close();
                }
            };
            final Buffer buffer = new Buffer();
            r3.writeTo(buffer);
            return chain.proceed(header.method(method, new RequestBody() {
                public final MediaType contentType() {
                    return r3.contentType();
                }

                public final long contentLength() {
                    return buffer.size();
                }

                public final void writeTo(BufferedSink bufferedSink) throws IOException {
                    bufferedSink.write(buffer.snapshot());
                }
            }).build());
        }
    }

    public RI(List<String> list) {
        super(list);
    }

    /* renamed from: new reason: not valid java name */
    public final void m317new(int i, boolean z) {
        TL.m338new(f518for, "Creating OkHttpClient instance");
        OkHttpClient.Builder newBuilder = new OkHttpClient().newBuilder();
        long j = (long) i;
        newBuilder.connectTimeout(j, TimeUnit.MILLISECONDS).writeTimeout(j, TimeUnit.MILLISECONDS).readTimeout(j, TimeUnit.MILLISECONDS).followRedirects(true).followSslRedirects(true).connectionPool(new ConnectionPool(3, 30, TimeUnit.SECONDS));
        if (I.f375const && z) {
            newBuilder.sslSocketFactory(new AP());
        }
        newBuilder.interceptors().add(f519int);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Protocol.HTTP_1_1);
        newBuilder.protocols(arrayList).retryOnConnectionFailure(true);
        this.f520do = newBuilder.build();
    }

    /* renamed from: new reason: not valid java name */
    public final JR m316new(Y y) {
        return new IK(this, y, this.f176new);
    }
}
