package com.google.firebase.perf.network;

import android.support.annotation.Keep;
import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import com.google.android.gms.internal.firebase-perf.zzh;
import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class FirebasePerfHttpClient {
    private FirebasePerfHttpClient() {
    }

    @Keep
    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        return zza(httpClient, httpUriRequest, new zzaa(), zzh.zzo());
    }

    @Keep
    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        return zza(httpClient, httpUriRequest, httpContext, new zzaa(), zzh.zzo());
    }

    @Keep
    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<T> responseHandler) throws IOException {
        return zza(httpClient, httpUriRequest, responseHandler, new zzaa(), zzh.zzo());
    }

    @Keep
    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<T> responseHandler, HttpContext httpContext) throws IOException {
        return zza(httpClient, httpUriRequest, responseHandler, httpContext, new zzaa(), zzh.zzo());
    }

    @Keep
    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return zza(httpClient, httpHost, httpRequest, new zzaa(), zzh.zzo());
    }

    @Keep
    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        return zza(httpClient, httpHost, httpRequest, httpContext, new zzaa(), zzh.zzo());
    }

    @Keep
    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return zza(httpClient, httpHost, httpRequest, responseHandler, new zzaa(), zzh.zzo());
    }

    @Keep
    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        return zza(httpClient, httpHost, httpRequest, responseHandler, httpContext, new zzaa(), zzh.zzo());
    }

    private static HttpResponse zza(HttpClient httpClient, HttpUriRequest httpUriRequest, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            zza.zza(httpUriRequest.getURI().toString()).zzb(httpUriRequest.getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpUriRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            HttpResponse execute = httpClient.execute(httpUriRequest);
            zza.zzf(zzaa.zzas());
            zza.zza(execute.getStatusLine().getStatusCode());
            Long zza3 = zzh.zza((HttpMessage) execute);
            if (zza3 != null) {
                zza.zzb(zza3.longValue());
            }
            String zza4 = zzh.zza(execute);
            if (zza4 != null) {
                zza.zzc(zza4);
            }
            zza.zzf();
            return execute;
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static HttpResponse zza(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            zza.zza(httpUriRequest.getURI().toString()).zzb(httpUriRequest.getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpUriRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            HttpResponse execute = httpClient.execute(httpUriRequest, httpContext);
            zza.zzf(zzaa.zzas());
            zza.zza(execute.getStatusLine().getStatusCode());
            Long zza3 = zzh.zza((HttpMessage) execute);
            if (zza3 != null) {
                zza.zzb(zza3.longValue());
            }
            String zza4 = zzh.zza(execute);
            if (zza4 != null) {
                zza.zzc(zza4);
            }
            zza.zzf();
            return execute;
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static <T> T zza(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<T> responseHandler, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            zza.zza(httpUriRequest.getURI().toString()).zzb(httpUriRequest.getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpUriRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            return httpClient.execute(httpUriRequest, new zzf(responseHandler, zzaa, zza));
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static <T> T zza(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<T> responseHandler, HttpContext httpContext, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            zza.zza(httpUriRequest.getURI().toString()).zzb(httpUriRequest.getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpUriRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            return httpClient.execute(httpUriRequest, new zzf(responseHandler, zzaa, zza), httpContext);
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static HttpResponse zza(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            String valueOf = String.valueOf(httpHost.toURI());
            String valueOf2 = String.valueOf(httpRequest.getRequestLine().getUri());
            zza.zza(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).zzb(httpRequest.getRequestLine().getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            HttpResponse execute = httpClient.execute(httpHost, httpRequest);
            zza.zzf(zzaa.zzas());
            zza.zza(execute.getStatusLine().getStatusCode());
            Long zza3 = zzh.zza((HttpMessage) execute);
            if (zza3 != null) {
                zza.zzb(zza3.longValue());
            }
            String zza4 = zzh.zza(execute);
            if (zza4 != null) {
                zza.zzc(zza4);
            }
            zza.zzf();
            return execute;
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static HttpResponse zza(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            String valueOf = String.valueOf(httpHost.toURI());
            String valueOf2 = String.valueOf(httpRequest.getRequestLine().getUri());
            zza.zza(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).zzb(httpRequest.getRequestLine().getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            HttpResponse execute = httpClient.execute(httpHost, httpRequest, httpContext);
            zza.zzf(zzaa.zzas());
            zza.zza(execute.getStatusLine().getStatusCode());
            Long zza3 = zzh.zza((HttpMessage) execute);
            if (zza3 != null) {
                zza.zzb(zza3.longValue());
            }
            String zza4 = zzh.zza(execute);
            if (zza4 != null) {
                zza.zzc(zza4);
            }
            zza.zzf();
            return execute;
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static <T> T zza(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            String valueOf = String.valueOf(httpHost.toURI());
            String valueOf2 = String.valueOf(httpRequest.getRequestLine().getUri());
            zza.zza(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).zzb(httpRequest.getRequestLine().getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            return httpClient.execute(httpHost, httpRequest, new zzf(responseHandler, zzaa, zza));
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }

    private static <T> T zza(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext, zzaa zzaa, zzh zzh) throws IOException {
        zzc zza = zzc.zza(zzh);
        try {
            String valueOf = String.valueOf(httpHost.toURI());
            String valueOf2 = String.valueOf(httpRequest.getRequestLine().getUri());
            zza.zza(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).zzb(httpRequest.getRequestLine().getMethod());
            Long zza2 = zzh.zza((HttpMessage) httpRequest);
            if (zza2 != null) {
                zza.zza(zza2.longValue());
            }
            zzaa.reset();
            zza.zzc(zzaa.zzar());
            return httpClient.execute(httpHost, httpRequest, new zzf(responseHandler, zzaa, zza), httpContext);
        } catch (IOException e) {
            zza.zzf(zzaa.zzas());
            zzh.zzb(zza);
            throw e;
        }
    }
}
