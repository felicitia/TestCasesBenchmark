package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkk;
import com.google.android.gms.internal.ads.zzko;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzxn;

@bu
public final class zzak extends zzko {
    private final Context mContext;
    private final bg zzwc;
    private final zzxn zzwh;
    private zzkh zzxs;
    private zzjn zzxx;
    private PublisherAdViewOptions zzxy;
    private zzpl zzyb;
    private zzlg zzyd;
    private final String zzye;
    private final zzang zzyf;
    private zzqw zzyk;
    private zzrl zzyl;
    private zzqz zzym;
    private SimpleArrayMap<String, zzrc> zzyn = new SimpleArrayMap<>();
    private SimpleArrayMap<String, zzrf> zzyo = new SimpleArrayMap<>();
    private zzri zzyp;

    public zzak(Context context, String str, zzxn zzxn, zzang zzang, bg bgVar) {
        this.mContext = context;
        this.zzye = str;
        this.zzwh = zzxn;
        this.zzyf = zzang;
        this.zzwc = bgVar;
    }

    public final void zza(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzxy = publisherAdViewOptions;
    }

    public final void zza(zzpl zzpl) {
        this.zzyb = zzpl;
    }

    public final void zza(zzqw zzqw) {
        this.zzyk = zzqw;
    }

    public final void zza(zzqz zzqz) {
        this.zzym = zzqz;
    }

    public final void zza(zzri zzri, zzjn zzjn) {
        this.zzyp = zzri;
        this.zzxx = zzjn;
    }

    public final void zza(zzrl zzrl) {
        this.zzyl = zzrl;
    }

    public final void zza(String str, zzrf zzrf, zzrc zzrc) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzyo.put(str, zzrf);
        this.zzyn.put(str, zzrc);
    }

    public final void zzb(zzkh zzkh) {
        this.zzxs = zzkh;
    }

    public final void zzb(zzlg zzlg) {
        this.zzyd = zzlg;
    }

    public final zzkk zzdh() {
        Context context = this.mContext;
        String str = this.zzye;
        zzxn zzxn = this.zzwh;
        zzang zzang = this.zzyf;
        zzkh zzkh = this.zzxs;
        zzqw zzqw = this.zzyk;
        zzrl zzrl = this.zzyl;
        zzqz zzqz = this.zzym;
        SimpleArrayMap<String, zzrf> simpleArrayMap = this.zzyo;
        SimpleArrayMap<String, zzrc> simpleArrayMap2 = this.zzyn;
        zzpl zzpl = this.zzyb;
        zzlg zzlg = this.zzyd;
        bg bgVar = this.zzwc;
        zzri zzri = this.zzyp;
        zzri zzri2 = zzri;
        zzri zzri3 = zzri2;
        zzah zzah = new zzah(context, str, zzxn, zzang, zzkh, zzqw, zzrl, zzqz, simpleArrayMap, simpleArrayMap2, zzpl, zzlg, bgVar, zzri3, this.zzxx, this.zzxy);
        return zzah;
    }
}
