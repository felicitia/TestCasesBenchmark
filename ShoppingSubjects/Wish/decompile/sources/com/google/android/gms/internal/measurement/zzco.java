package com.google.android.gms.internal.measurement;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map.Entry;

final class zzco extends zzar {
    /* access modifiers changed from: private */
    public static final byte[] zzaba = "\n".getBytes();
    private final String zzaay = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleAnalytics", zzas.VERSION, VERSION.RELEASE, zzdd.zza(Locale.getDefault()), Build.MODEL, Build.ID});
    private final zzcz zzaaz;

    zzco(zzat zzat) {
        super(zzat);
        this.zzaaz = new zzcz(zzat.zzbt());
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.net.URL r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.lang.String r0 = "GET request"
            r4.zzb(r0, r5)
            r0 = 0
            java.net.HttpURLConnection r5 = r4.zzb(r5)     // Catch:{ IOException -> 0x003a, all -> 0x0035 }
            r5.connect()     // Catch:{ IOException -> 0x0033 }
            r4.zza(r5)     // Catch:{ IOException -> 0x0033 }
            int r0 = r5.getResponseCode()     // Catch:{ IOException -> 0x0033 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 != r1) goto L_0x0022
            com.google.android.gms.internal.measurement.zzai r1 = r4.zzby()     // Catch:{ IOException -> 0x0033 }
            r1.zzbr()     // Catch:{ IOException -> 0x0033 }
        L_0x0022:
            java.lang.String r1 = "GET status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0033 }
            r4.zzb(r1, r2)     // Catch:{ IOException -> 0x0033 }
            if (r5 == 0) goto L_0x0030
            r5.disconnect()
        L_0x0030:
            return r0
        L_0x0031:
            r0 = move-exception
            goto L_0x004a
        L_0x0033:
            r0 = move-exception
            goto L_0x003e
        L_0x0035:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x004a
        L_0x003a:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L_0x003e:
            java.lang.String r1 = "Network GET connection error"
            r4.zzd(r1, r0)     // Catch:{ all -> 0x0031 }
            if (r5 == 0) goto L_0x0048
            r5.disconnect()
        L_0x0048:
            r5 = 0
            return r5
        L_0x004a:
            if (r5 == 0) goto L_0x004f
            r5.disconnect()
        L_0x004f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zza(java.net.URL):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x007f A[SYNTHETIC, Splitter:B:35:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0093 A[SYNTHETIC, Splitter:B:45:0x0093] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.net.URL r4, byte[] r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.lang.String r0 = "POST bytes, url"
            int r1 = r5.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.zzb(r0, r1, r4)
            boolean r0 = zzcg()
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = "Post payload\n"
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            r3.zza(r0, r1)
        L_0x0020:
            r0 = 0
            android.content.Context r1 = r3.getContext()     // Catch:{ IOException -> 0x0076, all -> 0x0073 }
            r1.getPackageName()     // Catch:{ IOException -> 0x0076, all -> 0x0073 }
            java.net.HttpURLConnection r4 = r3.zzb(r4)     // Catch:{ IOException -> 0x0076, all -> 0x0073 }
            r1 = 1
            r4.setDoOutput(r1)     // Catch:{ IOException -> 0x0071 }
            int r1 = r5.length     // Catch:{ IOException -> 0x0071 }
            r4.setFixedLengthStreamingMode(r1)     // Catch:{ IOException -> 0x0071 }
            r4.connect()     // Catch:{ IOException -> 0x0071 }
            java.io.OutputStream r1 = r4.getOutputStream()     // Catch:{ IOException -> 0x0071 }
            r1.write(r5)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r3.zza(r4)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            int r5 = r4.getResponseCode()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r0 = 200(0xc8, float:2.8E-43)
            if (r5 != r0) goto L_0x0050
            com.google.android.gms.internal.measurement.zzai r0 = r3.zzby()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r0.zzbr()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
        L_0x0050:
            java.lang.String r0 = "POST status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r3.zzb(r0, r2)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            if (r1 == 0) goto L_0x0065
            r1.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0065
        L_0x005f:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r3.zze(r1, r0)
        L_0x0065:
            if (r4 == 0) goto L_0x006a
            r4.disconnect()
        L_0x006a:
            return r5
        L_0x006b:
            r5 = move-exception
            r0 = r1
            goto L_0x0091
        L_0x006e:
            r5 = move-exception
            r0 = r1
            goto L_0x0078
        L_0x0071:
            r5 = move-exception
            goto L_0x0078
        L_0x0073:
            r5 = move-exception
            r4 = r0
            goto L_0x0091
        L_0x0076:
            r5 = move-exception
            r4 = r0
        L_0x0078:
            java.lang.String r1 = "Network POST connection error"
            r3.zzd(r1, r5)     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x0089
            r0.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0089
        L_0x0083:
            r5 = move-exception
            java.lang.String r0 = "Error closing http post connection output stream"
            r3.zze(r0, r5)
        L_0x0089:
            if (r4 == 0) goto L_0x008e
            r4.disconnect()
        L_0x008e:
            r4 = 0
            return r4
        L_0x0090:
            r5 = move-exception
        L_0x0091:
            if (r0 == 0) goto L_0x009d
            r0.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x009d
        L_0x0097:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r3.zze(r1, r0)
        L_0x009d:
            if (r4 == 0) goto L_0x00a2
            r4.disconnect()
        L_0x00a2:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zza(java.net.URL, byte[]):int");
    }

    private static void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0021 A[SYNTHETIC, Splitter:B:18:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.net.HttpURLConnection r3) throws java.io.IOException {
        /*
            r2 = this;
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ all -> 0x001d }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x001b }
        L_0x0008:
            int r1 = r3.read(r0)     // Catch:{ all -> 0x001b }
            if (r1 > 0) goto L_0x0008
            if (r3 == 0) goto L_0x001a
            r3.close()     // Catch:{ IOException -> 0x0014 }
            return
        L_0x0014:
            r3 = move-exception
            java.lang.String r0 = "Error closing http connection input stream"
            r2.zze(r0, r3)
        L_0x001a:
            return
        L_0x001b:
            r0 = move-exception
            goto L_0x001f
        L_0x001d:
            r0 = move-exception
            r3 = 0
        L_0x001f:
            if (r3 == 0) goto L_0x002b
            r3.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x002b
        L_0x0025:
            r3 = move-exception
            java.lang.String r1 = "Error closing http connection input stream"
            r2.zze(r1, r3)
        L_0x002b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zza(java.net.HttpURLConnection):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d8 A[SYNTHETIC, Splitter:B:42:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ec A[SYNTHETIC, Splitter:B:52:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzb(java.net.URL r10, byte[] r11) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)
            r0 = 0
            android.content.Context r1 = r9.getContext()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r1.getPackageName()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r1.<init>()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r2.<init>(r1)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r2.write(r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r2.close()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r1.close()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            byte[] r1 = r1.toByteArray()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.String r2 = "POST compressed size, ratio %, url"
            int r3 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r4 = 100
            int r6 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r6 = (long) r6     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r6 = r6 * r4
            int r4 = r11.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r4 = (long) r4     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r6 = r6 / r4
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r9.zza(r2, r3, r4, r10)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r2 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r3 = r11.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            if (r2 <= r3) goto L_0x004f
            java.lang.String r2 = "Compressed payload is larger then uncompressed. compressed, uncompressed"
            int r3 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r4 = r11.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r9.zzc(r2, r3, r4)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
        L_0x004f:
            boolean r2 = zzcg()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            if (r2 == 0) goto L_0x0075
            java.lang.String r2 = "Post payload"
            java.lang.String r3 = "\n"
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r4.<init>(r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.String r11 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r4 = r11.length()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            if (r4 == 0) goto L_0x006d
            java.lang.String r11 = r3.concat(r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            goto L_0x0072
        L_0x006d:
            java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r11.<init>(r3)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
        L_0x0072:
            r9.zza(r2, r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
        L_0x0075:
            java.net.HttpURLConnection r10 = r9.zzb(r10)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r11 = 1
            r10.setDoOutput(r11)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            java.lang.String r11 = "Content-Encoding"
            java.lang.String r2 = "gzip"
            r10.addRequestProperty(r11, r2)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            int r11 = r1.length     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r10.setFixedLengthStreamingMode(r11)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r10.connect()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            java.io.OutputStream r11 = r10.getOutputStream()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r11.write(r1)     // Catch:{ IOException -> 0x00bc, all -> 0x00b6 }
            r11.close()     // Catch:{ IOException -> 0x00bc, all -> 0x00b6 }
            r9.zza(r10)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            int r11 = r10.getResponseCode()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r11 != r1) goto L_0x00a7
            com.google.android.gms.internal.measurement.zzai r1 = r9.zzby()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r1.zzbr()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
        L_0x00a7:
            java.lang.String r1 = "POST status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r9.zzb(r1, r2)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            if (r10 == 0) goto L_0x00b5
            r10.disconnect()
        L_0x00b5:
            return r11
        L_0x00b6:
            r0 = move-exception
            r8 = r11
            r11 = r10
            r10 = r0
            r0 = r8
            goto L_0x00ea
        L_0x00bc:
            r0 = move-exception
            r8 = r11
            r11 = r10
            r10 = r0
            r0 = r8
            goto L_0x00d1
        L_0x00c2:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00ea
        L_0x00c7:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00d1
        L_0x00cc:
            r10 = move-exception
            r11 = r0
            goto L_0x00ea
        L_0x00cf:
            r10 = move-exception
            r11 = r0
        L_0x00d1:
            java.lang.String r1 = "Network compressed POST connection error"
            r9.zzd(r1, r10)     // Catch:{ all -> 0x00e9 }
            if (r0 == 0) goto L_0x00e2
            r0.close()     // Catch:{ IOException -> 0x00dc }
            goto L_0x00e2
        L_0x00dc:
            r10 = move-exception
            java.lang.String r0 = "Error closing http compressed post connection output stream"
            r9.zze(r0, r10)
        L_0x00e2:
            if (r11 == 0) goto L_0x00e7
            r11.disconnect()
        L_0x00e7:
            r10 = 0
            return r10
        L_0x00e9:
            r10 = move-exception
        L_0x00ea:
            if (r0 == 0) goto L_0x00f6
            r0.close()     // Catch:{ IOException -> 0x00f0 }
            goto L_0x00f6
        L_0x00f0:
            r0 = move-exception
            java.lang.String r1 = "Error closing http compressed post connection output stream"
            r9.zze(r1, r0)
        L_0x00f6:
            if (r11 == 0) goto L_0x00fb
            r11.disconnect()
        L_0x00fb:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zzb(java.net.URL, byte[]):int");
    }

    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(((Integer) zzcc.zzzn.get()).intValue());
        httpURLConnection.setReadTimeout(((Integer) zzcc.zzzo.get()).intValue());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzaay);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private final URL zzb(zzch zzch, String str) {
        String zzea;
        String zzeb;
        StringBuilder sb;
        if (zzch.zzep()) {
            zzea = zzbu.zzdz();
            zzeb = zzbu.zzeb();
            sb = new StringBuilder(String.valueOf(zzea).length() + 1 + String.valueOf(zzeb).length() + String.valueOf(str).length());
        } else {
            zzea = zzbu.zzea();
            zzeb = zzbu.zzeb();
            sb = new StringBuilder(String.valueOf(zzea).length() + 1 + String.valueOf(zzeb).length() + String.valueOf(str).length());
        }
        sb.append(zzea);
        sb.append(zzeb);
        sb.append("?");
        sb.append(str);
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzd(zzch zzch) {
        String str;
        String valueOf;
        String valueOf2;
        String str2;
        if (zzch.zzep()) {
            valueOf = String.valueOf(zzbu.zzdz());
            valueOf2 = String.valueOf(zzbu.zzeb());
            if (valueOf2.length() == 0) {
                str2 = new String(valueOf);
                str = str2;
                return new URL(str);
            }
        } else {
            valueOf = String.valueOf(zzbu.zzea());
            valueOf2 = String.valueOf(zzbu.zzeb());
            if (valueOf2.length() == 0) {
                str2 = new String(valueOf);
                str = str2;
                return new URL(str);
            }
        }
        str = valueOf.concat(valueOf2);
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzey() {
        String valueOf = String.valueOf(zzbu.zzdz());
        String valueOf2 = String.valueOf((String) zzcc.zzzc.get());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzch zzch, boolean z) {
        Preconditions.checkNotNull(zzch);
        StringBuilder sb = new StringBuilder();
        try {
            for (Entry entry : zzch.zzcs().entrySet()) {
                String str = (String) entry.getKey();
                if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str) && !"z".equals(str) && !"_gmsv".equals(str)) {
                    zza(sb, str, (String) entry.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzch.zzen()));
            zza(sb, "qt", String.valueOf(zzbt().currentTimeMillis() - zzch.zzen()));
            if (z) {
                long zzeq = zzch.zzeq();
                zza(sb, "z", zzeq != 0 ? String.valueOf(zzeq) : String.valueOf(zzch.zzem()));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        zza("Network initialized. User agent", this.zzaay);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0159, code lost:
        if (zza(r5) == 200) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0190, code lost:
        if (zza(r6, r5) == 200) goto L_0x0135;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01ab A[EDGE_INSN: B:72:0x01ab->B:69:0x01ab ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.Long> zzb(java.util.List<com.google.android.gms.internal.measurement.zzch> r9) {
        /*
            r8 = this;
            com.google.android.gms.analytics.zzk.zzab()
            r8.zzch()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            com.google.android.gms.internal.measurement.zzbu r0 = r8.zzbv()
            java.util.Set r0 = r0.zzec()
            boolean r0 = r0.isEmpty()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0059
            com.google.android.gms.internal.measurement.zzcz r0 = r8.zzaaz
            com.google.android.gms.internal.measurement.zzcd<java.lang.Integer> r3 = com.google.android.gms.internal.measurement.zzcc.zzzl
            java.lang.Object r3 = r3.get()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            long r3 = (long) r3
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r5
            boolean r0 = r0.zzj(r3)
            if (r0 != 0) goto L_0x0033
            goto L_0x0059
        L_0x0033:
            com.google.android.gms.internal.measurement.zzcd<java.lang.String> r0 = com.google.android.gms.internal.measurement.zzcc.zzze
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            com.google.android.gms.internal.measurement.zzbk r0 = com.google.android.gms.internal.measurement.zzbk.zzz(r0)
            com.google.android.gms.internal.measurement.zzbk r3 = com.google.android.gms.internal.measurement.zzbk.NONE
            if (r0 == r3) goto L_0x0045
            r0 = 1
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            com.google.android.gms.internal.measurement.zzcd<java.lang.String> r3 = com.google.android.gms.internal.measurement.zzcc.zzzf
            java.lang.Object r3 = r3.get()
            java.lang.String r3 = (java.lang.String) r3
            com.google.android.gms.internal.measurement.zzbq r3 = com.google.android.gms.internal.measurement.zzbq.zzaa(r3)
            com.google.android.gms.internal.measurement.zzbq r4 = com.google.android.gms.internal.measurement.zzbq.GZIP
            if (r3 != r4) goto L_0x005a
            r3 = r0
            r0 = 1
            goto L_0x005c
        L_0x0059:
            r0 = 0
        L_0x005a:
            r3 = r0
            r0 = 0
        L_0x005c:
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 == 0) goto L_0x0105
            boolean r1 = r9.isEmpty()
            r1 = r1 ^ r2
            com.google.android.gms.common.internal.Preconditions.checkArgument(r1)
            java.lang.String r1 = "Uploading batched hits. compression, count"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
            int r3 = r9.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r8.zza(r1, r2, r3)
            com.google.android.gms.internal.measurement.zzcp r1 = new com.google.android.gms.internal.measurement.zzcp
            r1.<init>(r8)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r9 = r9.iterator()
        L_0x0087:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L_0x00a5
            java.lang.Object r3 = r9.next()
            com.google.android.gms.internal.measurement.zzch r3 = (com.google.android.gms.internal.measurement.zzch) r3
            boolean r5 = r1.zze(r3)
            if (r5 == 0) goto L_0x00a5
            long r5 = r3.zzem()
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r2.add(r3)
            goto L_0x0087
        L_0x00a5:
            int r9 = r1.zzfa()
            if (r9 != 0) goto L_0x00ac
            return r2
        L_0x00ac:
            java.net.URL r9 = r8.zzey()
            if (r9 != 0) goto L_0x00b8
            java.lang.String r9 = "Failed to build batching endpoint url"
            r8.zzu(r9)
            goto L_0x0100
        L_0x00b8:
            if (r0 == 0) goto L_0x00c3
            byte[] r0 = r1.getPayload()
            int r9 = r8.zzb(r9, r0)
            goto L_0x00cb
        L_0x00c3:
            byte[] r0 = r1.getPayload()
            int r9 = r8.zza(r9, r0)
        L_0x00cb:
            if (r4 != r9) goto L_0x00db
            java.lang.String r9 = "Batched upload completed. Hits batched"
            int r0 = r1.zzfa()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.zza(r9, r0)
            return r2
        L_0x00db:
            java.lang.String r0 = "Network error uploading hits. status code"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            r8.zza(r0, r1)
            com.google.android.gms.internal.measurement.zzbu r0 = r8.zzbv()
            java.util.Set r0 = r0.zzec()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            boolean r9 = r0.contains(r9)
            if (r9 == 0) goto L_0x0100
            java.lang.String r9 = "Server instructed the client to stop batching"
            r8.zzt(r9)
            com.google.android.gms.internal.measurement.zzcz r9 = r8.zzaaz
            r9.start()
        L_0x0100:
            java.util.List r9 = java.util.Collections.emptyList()
            return r9
        L_0x0105:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r3 = r9.size()
            r0.<init>(r3)
            java.util.Iterator r9 = r9.iterator()
        L_0x0112:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L_0x01ab
            java.lang.Object r3 = r9.next()
            com.google.android.gms.internal.measurement.zzch r3 = (com.google.android.gms.internal.measurement.zzch) r3
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            boolean r5 = r3.zzep()
            r5 = r5 ^ r2
            java.lang.String r5 = r8.zza(r3, r5)
            if (r5 != 0) goto L_0x0137
            com.google.android.gms.internal.measurement.zzcm r5 = r8.zzbu()
            java.lang.String r6 = "Error formatting hit for upload"
        L_0x0132:
            r5.zza(r3, r6)
        L_0x0135:
            r5 = 1
            goto L_0x0194
        L_0x0137:
            int r6 = r5.length()
            com.google.android.gms.internal.measurement.zzcd<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzcc.zzzd
            java.lang.Object r7 = r7.get()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r6 > r7) goto L_0x015c
            java.net.URL r5 = r8.zzb(r3, r5)
            if (r5 != 0) goto L_0x0155
            java.lang.String r5 = "Failed to build collect GET endpoint url"
        L_0x0151:
            r8.zzu(r5)
            goto L_0x0193
        L_0x0155:
            int r5 = r8.zza(r5)
            if (r5 != r4) goto L_0x0193
        L_0x015b:
            goto L_0x0135
        L_0x015c:
            java.lang.String r5 = r8.zza(r3, r1)
            if (r5 != 0) goto L_0x0169
            com.google.android.gms.internal.measurement.zzcm r5 = r8.zzbu()
            java.lang.String r6 = "Error formatting hit for POST upload"
            goto L_0x0132
        L_0x0169:
            byte[] r5 = r5.getBytes()
            int r6 = r5.length
            com.google.android.gms.internal.measurement.zzcd<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzcc.zzzi
            java.lang.Object r7 = r7.get()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r6 <= r7) goto L_0x0183
            com.google.android.gms.internal.measurement.zzcm r5 = r8.zzbu()
            java.lang.String r6 = "Hit payload exceeds size limit"
            goto L_0x0132
        L_0x0183:
            java.net.URL r6 = r8.zzd(r3)
            if (r6 != 0) goto L_0x018c
            java.lang.String r5 = "Failed to build collect POST endpoint url"
            goto L_0x0151
        L_0x018c:
            int r5 = r8.zza(r6, r5)
            if (r5 != r4) goto L_0x0193
            goto L_0x015b
        L_0x0193:
            r5 = 0
        L_0x0194:
            if (r5 == 0) goto L_0x01ab
            long r5 = r3.zzem()
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r0.add(r3)
            int r3 = r0.size()
            int r5 = com.google.android.gms.internal.measurement.zzbu.zzdx()
            if (r3 < r5) goto L_0x0112
        L_0x01ab:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zzb(java.util.List):java.util.List");
    }

    public final boolean zzex() {
        NetworkInfo networkInfo;
        zzk.zzab();
        zzch();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzq("No network connectivity");
        return false;
    }
}
