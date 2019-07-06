package com.google.firebase.perf.network;

import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

public final class zzd extends HttpsURLConnection {
    private final zze zzeg;
    private final HttpsURLConnection zzeh;

    zzd(HttpsURLConnection httpsURLConnection, zzaa zzaa, zzc zzc) {
        super(httpsURLConnection.getURL());
        this.zzeh = httpsURLConnection;
        this.zzeg = new zze(httpsURLConnection, zzaa, zzc);
    }

    public final void connect() throws IOException {
        this.zzeg.connect();
    }

    public final void disconnect() {
        this.zzeg.disconnect();
    }

    public final Object getContent() throws IOException {
        return this.zzeg.getContent();
    }

    public final Object getContent(Class[] clsArr) throws IOException {
        return this.zzeg.getContent(clsArr);
    }

    public final InputStream getInputStream() throws IOException {
        return this.zzeg.getInputStream();
    }

    public final long getLastModified() {
        return this.zzeg.getLastModified();
    }

    public final OutputStream getOutputStream() throws IOException {
        return this.zzeg.getOutputStream();
    }

    public final Permission getPermission() throws IOException {
        return this.zzeg.getPermission();
    }

    public final int getResponseCode() throws IOException {
        return this.zzeg.getResponseCode();
    }

    public final String getResponseMessage() throws IOException {
        return this.zzeg.getResponseMessage();
    }

    public final long getExpiration() {
        return this.zzeg.getExpiration();
    }

    public final String getHeaderField(int i) {
        return this.zzeg.getHeaderField(i);
    }

    public final String getHeaderField(String str) {
        return this.zzeg.getHeaderField(str);
    }

    public final long getHeaderFieldDate(String str, long j) {
        return this.zzeg.getHeaderFieldDate(str, j);
    }

    public final int getHeaderFieldInt(String str, int i) {
        return this.zzeg.getHeaderFieldInt(str, i);
    }

    public final long getHeaderFieldLong(String str, long j) {
        return this.zzeg.getHeaderFieldLong(str, j);
    }

    public final String getHeaderFieldKey(int i) {
        return this.zzeg.getHeaderFieldKey(i);
    }

    public final Map<String, List<String>> getHeaderFields() {
        return this.zzeg.getHeaderFields();
    }

    public final String getContentEncoding() {
        return this.zzeg.getContentEncoding();
    }

    public final int getContentLength() {
        return this.zzeg.getContentLength();
    }

    public final long getContentLengthLong() {
        return this.zzeg.getContentLengthLong();
    }

    public final String getContentType() {
        return this.zzeg.getContentType();
    }

    public final long getDate() {
        return this.zzeg.getDate();
    }

    public final void addRequestProperty(String str, String str2) {
        this.zzeg.addRequestProperty(str, str2);
    }

    public final boolean equals(Object obj) {
        return this.zzeg.equals(obj);
    }

    public final boolean getAllowUserInteraction() {
        return this.zzeg.getAllowUserInteraction();
    }

    public final int getConnectTimeout() {
        return this.zzeg.getConnectTimeout();
    }

    public final boolean getDefaultUseCaches() {
        return this.zzeg.getDefaultUseCaches();
    }

    public final boolean getDoInput() {
        return this.zzeg.getDoInput();
    }

    public final boolean getDoOutput() {
        return this.zzeg.getDoOutput();
    }

    public final InputStream getErrorStream() {
        return this.zzeg.getErrorStream();
    }

    public final long getIfModifiedSince() {
        return this.zzeg.getIfModifiedSince();
    }

    public final boolean getInstanceFollowRedirects() {
        return this.zzeg.getInstanceFollowRedirects();
    }

    public final int getReadTimeout() {
        return this.zzeg.getReadTimeout();
    }

    public final String getRequestMethod() {
        return this.zzeg.getRequestMethod();
    }

    public final Map<String, List<String>> getRequestProperties() {
        return this.zzeg.getRequestProperties();
    }

    public final String getRequestProperty(String str) {
        return this.zzeg.getRequestProperty(str);
    }

    public final URL getURL() {
        return this.zzeg.getURL();
    }

    public final boolean getUseCaches() {
        return this.zzeg.getUseCaches();
    }

    public final int hashCode() {
        return this.zzeg.hashCode();
    }

    public final void setAllowUserInteraction(boolean z) {
        this.zzeg.setAllowUserInteraction(z);
    }

    public final void setChunkedStreamingMode(int i) {
        this.zzeg.setChunkedStreamingMode(i);
    }

    public final void setConnectTimeout(int i) {
        this.zzeg.setConnectTimeout(i);
    }

    public final void setDefaultUseCaches(boolean z) {
        this.zzeg.setDefaultUseCaches(z);
    }

    public final void setDoInput(boolean z) {
        this.zzeg.setDoInput(z);
    }

    public final void setDoOutput(boolean z) {
        this.zzeg.setDoOutput(z);
    }

    public final void setFixedLengthStreamingMode(int i) {
        this.zzeg.setFixedLengthStreamingMode(i);
    }

    public final void setFixedLengthStreamingMode(long j) {
        this.zzeg.setFixedLengthStreamingMode(j);
    }

    public final void setIfModifiedSince(long j) {
        this.zzeg.setIfModifiedSince(j);
    }

    public final void setInstanceFollowRedirects(boolean z) {
        this.zzeg.setInstanceFollowRedirects(z);
    }

    public final void setReadTimeout(int i) {
        this.zzeg.setReadTimeout(i);
    }

    public final void setRequestMethod(String str) throws ProtocolException {
        this.zzeg.setRequestMethod(str);
    }

    public final void setRequestProperty(String str, String str2) {
        this.zzeg.setRequestProperty(str, str2);
    }

    public final void setUseCaches(boolean z) {
        this.zzeg.setUseCaches(z);
    }

    public final String toString() {
        return this.zzeg.toString();
    }

    public final boolean usingProxy() {
        return this.zzeg.usingProxy();
    }

    public final String getCipherSuite() {
        return this.zzeh.getCipherSuite();
    }

    public final HostnameVerifier getHostnameVerifier() {
        return this.zzeh.getHostnameVerifier();
    }

    public final Certificate[] getLocalCertificates() {
        return this.zzeh.getLocalCertificates();
    }

    public final Principal getLocalPrincipal() {
        return this.zzeh.getLocalPrincipal();
    }

    public final Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.zzeh.getPeerPrincipal();
    }

    public final Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.zzeh.getServerCertificates();
    }

    public final SSLSocketFactory getSSLSocketFactory() {
        return this.zzeh.getSSLSocketFactory();
    }

    public final void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.zzeh.setHostnameVerifier(hostnameVerifier);
    }

    public final void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.zzeh.setSSLSocketFactory(sSLSocketFactory);
    }
}
