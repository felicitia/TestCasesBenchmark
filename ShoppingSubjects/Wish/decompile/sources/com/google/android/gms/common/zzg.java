package com.google.android.gms.common;

import android.util.Log;

class zzg {
    private static final zzg zzbk = new zzg(true, null, null);
    private final Throwable cause;
    final boolean zzbl;
    private final String zzbm;

    zzg(boolean z, String str, Throwable th) {
        this.zzbl = z;
        this.zzbm = str;
        this.cause = th;
    }

    static zzg zza(String str, CertData certData, boolean z, boolean z2) {
        zzi zzi = new zzi(str, certData, z, z2);
        return zzi;
    }

    static zzg zza(String str, Throwable th) {
        return new zzg(false, str, th);
    }

    static zzg zze(String str) {
        return new zzg(false, str, null);
    }

    static zzg zzg() {
        return zzbk;
    }

    /* access modifiers changed from: 0000 */
    public String getErrorMessage() {
        return this.zzbm;
    }

    /* access modifiers changed from: 0000 */
    public final void zzi() {
        if (!this.zzbl) {
            if (this.cause != null) {
                Log.d("GoogleCertificatesRslt", getErrorMessage(), this.cause);
                return;
            }
            Log.d("GoogleCertificatesRslt", getErrorMessage());
        }
    }
}
