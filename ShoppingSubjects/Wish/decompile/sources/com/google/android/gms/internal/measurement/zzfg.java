package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import java.util.concurrent.atomic.AtomicReference;

public final class zzfg extends zzhj {
    private static final AtomicReference<String[]> zzaki = new AtomicReference<>();
    private static final AtomicReference<String[]> zzakj = new AtomicReference<>();
    private static final AtomicReference<String[]> zzakk = new AtomicReference<>();

    zzfg(zzgn zzgn) {
        super(zzgn);
    }

    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzkd.zzs(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = (String[]) atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(strArr2[i]);
                        sb.append("(");
                        sb.append(strArr[i]);
                        sb.append(")");
                        strArr3[i] = sb.toString();
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    private final String zzb(zzeu zzeu) {
        if (zzeu == null) {
            return null;
        }
        return !zziu() ? zzeu.toString() : zzb(zzeu.zzin());
    }

    private final boolean zziu() {
        return this.zzacv.zzgi().isLoggable(3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: protected */
    public final String zza(zzes zzes) {
        if (zzes == null) {
            return null;
        }
        if (!zziu()) {
            return zzes.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Event{appId='");
        sb.append(zzes.zzth);
        sb.append("', name='");
        sb.append(zzbm(zzes.name));
        sb.append("', params=");
        sb.append(zzb(zzes.zzahg));
        sb.append("}");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: protected */
    public final String zzb(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zziu()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            sb.append(sb.length() != 0 ? ", " : "Bundle[{");
            sb.append(zzbn(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zzb(zzex zzex) {
        if (zzex == null) {
            return null;
        }
        if (!zziu()) {
            return zzex.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(zzex.origin);
        sb.append(",name=");
        sb.append(zzbm(zzex.name));
        sb.append(",params=");
        sb.append(zzb(zzex.zzahg));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zzbm(String str) {
        if (str == null) {
            return null;
        }
        return !zziu() ? str : zza(str, Event.zzacx, Event.zzacw, zzaki);
    }

    /* access modifiers changed from: protected */
    public final String zzbn(String str) {
        if (str == null) {
            return null;
        }
        return !zziu() ? str : zza(str, Param.zzacz, Param.zzacy, zzakj);
    }

    /* access modifiers changed from: protected */
    public final String zzbo(String str) {
        if (str == null) {
            return null;
        }
        if (!zziu()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, UserProperty.zzadb, UserProperty.zzada, zzakk);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("experiment_id");
        sb.append("(");
        sb.append(str);
        sb.append(")");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    public final boolean zzgn() {
        return false;
    }
}
