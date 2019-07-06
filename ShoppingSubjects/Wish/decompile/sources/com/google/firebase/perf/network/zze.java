package com.google.firebase.perf.network;

import android.util.Log;
import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;

final class zze {
    private final zzaa zzdo;
    private final zzc zzeb;
    private final HttpURLConnection zzei;
    private long zzej = -1;
    private long zzs = -1;

    public zze(HttpURLConnection httpURLConnection, zzaa zzaa, zzc zzc) {
        this.zzei = httpURLConnection;
        this.zzeb = zzc;
        this.zzdo = zzaa;
        this.zzeb.zza(this.zzei.getURL().toString());
    }

    public final void connect() throws IOException {
        if (this.zzej == -1) {
            this.zzdo.reset();
            this.zzej = this.zzdo.zzar();
            this.zzeb.zzc(this.zzej);
        }
        try {
            this.zzei.connect();
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void disconnect() {
        this.zzeb.zzf(this.zzdo.zzas());
        this.zzeb.zzf();
        this.zzei.disconnect();
    }

    public final Object getContent() throws IOException {
        zzap();
        this.zzeb.zza(this.zzei.getResponseCode());
        try {
            Object content = this.zzei.getContent();
            if (content instanceof InputStream) {
                this.zzeb.zzc(this.zzei.getContentType());
                return new zza((InputStream) content, this.zzeb, this.zzdo);
            }
            this.zzeb.zzc(this.zzei.getContentType());
            this.zzeb.zzb((long) this.zzei.getContentLength());
            this.zzeb.zzf(this.zzdo.zzas());
            this.zzeb.zzf();
            return content;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final Object getContent(Class[] clsArr) throws IOException {
        zzap();
        this.zzeb.zza(this.zzei.getResponseCode());
        try {
            Object content = this.zzei.getContent(clsArr);
            if (content instanceof InputStream) {
                this.zzeb.zzc(this.zzei.getContentType());
                return new zza((InputStream) content, this.zzeb, this.zzdo);
            }
            this.zzeb.zzc(this.zzei.getContentType());
            this.zzeb.zzb((long) this.zzei.getContentLength());
            this.zzeb.zzf(this.zzdo.zzas());
            this.zzeb.zzf();
            return content;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final InputStream getInputStream() throws IOException {
        zzap();
        this.zzeb.zza(this.zzei.getResponseCode());
        this.zzeb.zzc(this.zzei.getContentType());
        try {
            return new zza(this.zzei.getInputStream(), this.zzeb, this.zzdo);
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final long getLastModified() {
        zzap();
        return this.zzei.getLastModified();
    }

    public final OutputStream getOutputStream() throws IOException {
        try {
            return new zzb(this.zzei.getOutputStream(), this.zzeb, this.zzdo);
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final Permission getPermission() throws IOException {
        try {
            return this.zzei.getPermission();
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final int getResponseCode() throws IOException {
        zzap();
        if (this.zzs == -1) {
            this.zzs = this.zzdo.zzas();
            this.zzeb.zze(this.zzs);
        }
        try {
            int responseCode = this.zzei.getResponseCode();
            this.zzeb.zza(responseCode);
            return responseCode;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final String getResponseMessage() throws IOException {
        zzap();
        if (this.zzs == -1) {
            this.zzs = this.zzdo.zzas();
            this.zzeb.zze(this.zzs);
        }
        try {
            String responseMessage = this.zzei.getResponseMessage();
            this.zzeb.zza(this.zzei.getResponseCode());
            return responseMessage;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final long getExpiration() {
        zzap();
        return this.zzei.getExpiration();
    }

    public final String getHeaderField(int i) {
        zzap();
        return this.zzei.getHeaderField(i);
    }

    public final String getHeaderField(String str) {
        zzap();
        return this.zzei.getHeaderField(str);
    }

    public final long getHeaderFieldDate(String str, long j) {
        zzap();
        return this.zzei.getHeaderFieldDate(str, j);
    }

    public final int getHeaderFieldInt(String str, int i) {
        zzap();
        return this.zzei.getHeaderFieldInt(str, i);
    }

    public final long getHeaderFieldLong(String str, long j) {
        zzap();
        return this.zzei.getHeaderFieldLong(str, j);
    }

    public final String getHeaderFieldKey(int i) {
        zzap();
        return this.zzei.getHeaderFieldKey(i);
    }

    public final Map<String, List<String>> getHeaderFields() {
        zzap();
        return this.zzei.getHeaderFields();
    }

    public final String getContentEncoding() {
        zzap();
        return this.zzei.getContentEncoding();
    }

    public final int getContentLength() {
        zzap();
        return this.zzei.getContentLength();
    }

    public final long getContentLengthLong() {
        zzap();
        return this.zzei.getContentLengthLong();
    }

    public final String getContentType() {
        zzap();
        return this.zzei.getContentType();
    }

    public final long getDate() {
        zzap();
        return this.zzei.getDate();
    }

    public final void addRequestProperty(String str, String str2) {
        this.zzei.addRequestProperty(str, str2);
    }

    public final boolean equals(Object obj) {
        return this.zzei.equals(obj);
    }

    public final boolean getAllowUserInteraction() {
        return this.zzei.getAllowUserInteraction();
    }

    public final int getConnectTimeout() {
        return this.zzei.getConnectTimeout();
    }

    public final boolean getDefaultUseCaches() {
        return this.zzei.getDefaultUseCaches();
    }

    public final boolean getDoInput() {
        return this.zzei.getDoInput();
    }

    public final boolean getDoOutput() {
        return this.zzei.getDoOutput();
    }

    public final InputStream getErrorStream() {
        zzap();
        try {
            this.zzeb.zza(this.zzei.getResponseCode());
        } catch (IOException unused) {
            Log.d("FirebasePerformance", "IOException thrown trying to obtain the response code");
        }
        InputStream errorStream = this.zzei.getErrorStream();
        return errorStream != null ? new zza(errorStream, this.zzeb, this.zzdo) : errorStream;
    }

    public final long getIfModifiedSince() {
        return this.zzei.getIfModifiedSince();
    }

    public final boolean getInstanceFollowRedirects() {
        return this.zzei.getInstanceFollowRedirects();
    }

    public final int getReadTimeout() {
        return this.zzei.getReadTimeout();
    }

    public final String getRequestMethod() {
        return this.zzei.getRequestMethod();
    }

    public final Map<String, List<String>> getRequestProperties() {
        return this.zzei.getRequestProperties();
    }

    public final String getRequestProperty(String str) {
        return this.zzei.getRequestProperty(str);
    }

    public final URL getURL() {
        return this.zzei.getURL();
    }

    public final boolean getUseCaches() {
        return this.zzei.getUseCaches();
    }

    public final int hashCode() {
        return this.zzei.hashCode();
    }

    public final void setAllowUserInteraction(boolean z) {
        this.zzei.setAllowUserInteraction(z);
    }

    public final void setChunkedStreamingMode(int i) {
        this.zzei.setChunkedStreamingMode(i);
    }

    public final void setConnectTimeout(int i) {
        this.zzei.setConnectTimeout(i);
    }

    public final void setDefaultUseCaches(boolean z) {
        this.zzei.setDefaultUseCaches(z);
    }

    public final void setDoInput(boolean z) {
        this.zzei.setDoInput(z);
    }

    public final void setDoOutput(boolean z) {
        this.zzei.setDoOutput(z);
    }

    public final void setFixedLengthStreamingMode(int i) {
        this.zzei.setFixedLengthStreamingMode(i);
    }

    public final void setFixedLengthStreamingMode(long j) {
        this.zzei.setFixedLengthStreamingMode(j);
    }

    public final void setIfModifiedSince(long j) {
        this.zzei.setIfModifiedSince(j);
    }

    public final void setInstanceFollowRedirects(boolean z) {
        this.zzei.setInstanceFollowRedirects(z);
    }

    public final void setReadTimeout(int i) {
        this.zzei.setReadTimeout(i);
    }

    public final void setRequestMethod(String str) throws ProtocolException {
        this.zzei.setRequestMethod(str);
    }

    public final void setRequestProperty(String str, String str2) {
        this.zzei.setRequestProperty(str, str2);
    }

    public final void setUseCaches(boolean z) {
        this.zzei.setUseCaches(z);
    }

    public final String toString() {
        return this.zzei.toString();
    }

    public final boolean usingProxy() {
        return this.zzei.usingProxy();
    }

    private final void zzap() {
        if (this.zzej == -1) {
            this.zzdo.reset();
            this.zzej = this.zzdo.zzar();
            this.zzeb.zzc(this.zzej);
        }
        String requestMethod = this.zzei.getRequestMethod();
        if (requestMethod != null) {
            this.zzeb.zzb(requestMethod);
        } else if (this.zzei.getDoOutput()) {
            this.zzeb.zzb("POST");
        } else {
            this.zzeb.zzb("GET");
        }
    }
}
