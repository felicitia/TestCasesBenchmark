package com.google.android.gms.internal.firebase-perf;

import android.content.Context;
import android.util.Log;
import java.net.URI;

final class zzm extends zzo {
    private final Context zzbf;
    private final zzap zzbr;

    zzm(zzap zzap, Context context) {
        this.zzbf = context;
        this.zzbr = zzap;
    }

    private static boolean zzg(long j) {
        return j >= 0;
    }

    private static boolean zzh(long j) {
        return j >= 0;
    }

    public final boolean zzn() {
        boolean z;
        boolean z2 = false;
        if (zze(this.zzbr.url)) {
            String str = "FirebasePerformance";
            String str2 = "URL is missing:";
            String valueOf = String.valueOf(this.zzbr.url);
            Log.i(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return false;
        }
        URI zzd = zzd(this.zzbr.url);
        if (zzd == null) {
            Log.i("FirebasePerformance", "URL cannot be parsed");
            return false;
        }
        Context context = this.zzbf;
        if (zzd == null) {
            z = false;
        } else {
            z = zzac.zza(zzd, context);
        }
        if (!z) {
            String valueOf2 = String.valueOf(zzd);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 26);
            sb.append("URL fails whitelist rule: ");
            sb.append(valueOf2);
            Log.i("FirebasePerformance", sb.toString());
            return false;
        }
        String host = zzd.getHost();
        if (!(host != null && !zze(host) && host.length() <= 255)) {
            Log.i("FirebasePerformance", "URL host is null or invalid");
            return false;
        }
        String scheme = zzd.getScheme();
        if (!(scheme != null && ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)))) {
            Log.i("FirebasePerformance", "URL scheme is null or invalid");
            return false;
        }
        if (!(zzd.getUserInfo() == null)) {
            Log.i("FirebasePerformance", "URL user info is null");
            return false;
        }
        int port = zzd.getPort();
        if (!(port == -1 || port > 0)) {
            Log.i("FirebasePerformance", "URL port is less than or equal to 0");
            return false;
        }
        Integer num = this.zzbr.zzge;
        if (!((num == null || num.intValue() == 0) ? false : true)) {
            String valueOf3 = String.valueOf(this.zzbr.zzge);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 32);
            sb2.append("HTTP Method is null or invalid: ");
            sb2.append(valueOf3);
            Log.i("FirebasePerformance", sb2.toString());
            return false;
        }
        if (this.zzbr.zzgi != null) {
            if (!(this.zzbr.zzgi.intValue() > 0)) {
                String valueOf4 = String.valueOf(this.zzbr.zzgi);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf4).length() + 38);
                sb3.append("HTTP ResponseCode is a negative value:");
                sb3.append(valueOf4);
                Log.i("FirebasePerformance", sb3.toString());
                return false;
            }
        }
        if (this.zzbr.zzgf != null && !zzh(this.zzbr.zzgf.longValue())) {
            String valueOf5 = String.valueOf(this.zzbr.zzgf);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf5).length() + 36);
            sb4.append("Request Payload is a negative value:");
            sb4.append(valueOf5);
            Log.i("FirebasePerformance", sb4.toString());
            return false;
        } else if (this.zzbr.zzgg != null && !zzh(this.zzbr.zzgg.longValue())) {
            String valueOf6 = String.valueOf(this.zzbr.zzgg);
            StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf6).length() + 37);
            sb5.append("Response Payload is a negative value:");
            sb5.append(valueOf6);
            Log.i("FirebasePerformance", sb5.toString());
            return false;
        } else if (this.zzbr.zzgk == null || this.zzbr.zzgk.longValue() <= 0) {
            String valueOf7 = String.valueOf(this.zzbr.zzgk);
            StringBuilder sb6 = new StringBuilder(String.valueOf(valueOf7).length() + 64);
            sb6.append("Start time of the request is null, or zero, or a negative value:");
            sb6.append(valueOf7);
            Log.i("FirebasePerformance", sb6.toString());
            return false;
        } else if (this.zzbr.zzgl != null && !zzg(this.zzbr.zzgl.longValue())) {
            String valueOf8 = String.valueOf(this.zzbr.zzgl);
            StringBuilder sb7 = new StringBuilder(String.valueOf(valueOf8).length() + 49);
            sb7.append("Time to complete the request is a negative value:");
            sb7.append(valueOf8);
            Log.i("FirebasePerformance", sb7.toString());
            return false;
        } else if (this.zzbr.zzgm != null && !zzg(this.zzbr.zzgm.longValue())) {
            String valueOf9 = String.valueOf(this.zzbr.zzgm);
            StringBuilder sb8 = new StringBuilder(String.valueOf(valueOf9).length() + 92);
            sb8.append("Time from the start of the request to the start of the response is null or a negative value:");
            sb8.append(valueOf9);
            Log.i("FirebasePerformance", sb8.toString());
            return false;
        } else if (this.zzbr.zzgn == null || this.zzbr.zzgn.longValue() <= 0) {
            String valueOf10 = String.valueOf(this.zzbr.zzgn);
            StringBuilder sb9 = new StringBuilder(String.valueOf(valueOf10).length() + 88);
            sb9.append("Time from the start of the request to the end of the response is null, negative or zero:");
            sb9.append(valueOf10);
            Log.i("FirebasePerformance", sb9.toString());
            return false;
        } else if (this.zzbr.zzgi == null) {
            Log.i("FirebasePerformance", "Did not receive a HTTP Response Code");
            return false;
        } else {
            if (this.zzbr.zzgj != null) {
                String str3 = this.zzbr.zzgj;
                if (str3.length() <= 128) {
                    int i = 0;
                    while (true) {
                        if (i >= str3.length()) {
                            z2 = true;
                            break;
                        }
                        char charAt = str3.charAt(i);
                        if (charAt <= 31 || charAt > 127) {
                            break;
                        }
                        i++;
                    }
                }
                if (!z2) {
                    String str4 = "FirebasePerformance";
                    String str5 = "The content type of the response is not a valid content-type:";
                    String valueOf11 = String.valueOf(this.zzbr.zzgj);
                    Log.i(str4, valueOf11.length() != 0 ? str5.concat(valueOf11) : new String(str5));
                    this.zzbr.zzgj = null;
                }
            }
            return true;
        }
    }

    private static URI zzd(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URI.create(str);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Log.w("FirebasePerformance", "getResultUrl throws exception", e);
            return null;
        }
    }

    private static boolean zze(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().isEmpty();
    }
}
