package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzv extends zzi<zzv> {
    private String name;
    private String zzno;
    private String zztj;
    private String zztk;
    private String zztl;
    private String zztm;
    private String zztn;
    private String zzto;
    private String zztp;
    private String zztq;

    public final String getId() {
        return this.zzno;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSource() {
        return this.zztj;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.name);
        hashMap.put("source", this.zztj);
        hashMap.put("medium", this.zztk);
        hashMap.put("keyword", this.zztl);
        hashMap.put("content", this.zztm);
        hashMap.put("id", this.zzno);
        hashMap.put("adNetworkId", this.zztn);
        hashMap.put("gclid", this.zzto);
        hashMap.put("dclid", this.zztp);
        hashMap.put("aclid", this.zztq);
        return zza((Object) hashMap);
    }

    public final String zzaj() {
        return this.zztk;
    }

    public final String zzak() {
        return this.zztl;
    }

    public final String zzal() {
        return this.zztm;
    }

    public final String zzam() {
        return this.zztn;
    }

    public final String zzan() {
        return this.zzto;
    }

    public final String zzao() {
        return this.zztp;
    }

    public final String zzap() {
        return this.zztq;
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzv zzv = (zzv) zzi;
        if (!TextUtils.isEmpty(this.name)) {
            zzv.name = this.name;
        }
        if (!TextUtils.isEmpty(this.zztj)) {
            zzv.zztj = this.zztj;
        }
        if (!TextUtils.isEmpty(this.zztk)) {
            zzv.zztk = this.zztk;
        }
        if (!TextUtils.isEmpty(this.zztl)) {
            zzv.zztl = this.zztl;
        }
        if (!TextUtils.isEmpty(this.zztm)) {
            zzv.zztm = this.zztm;
        }
        if (!TextUtils.isEmpty(this.zzno)) {
            zzv.zzno = this.zzno;
        }
        if (!TextUtils.isEmpty(this.zztn)) {
            zzv.zztn = this.zztn;
        }
        if (!TextUtils.isEmpty(this.zzto)) {
            zzv.zzto = this.zzto;
        }
        if (!TextUtils.isEmpty(this.zztp)) {
            zzv.zztp = this.zztp;
        }
        if (!TextUtils.isEmpty(this.zztq)) {
            zzv.zztq = this.zztq;
        }
    }

    public final void zzc(String str) {
        this.zztj = str;
    }

    public final void zzd(String str) {
        this.zztk = str;
    }

    public final void zze(String str) {
        this.zztl = str;
    }

    public final void zzf(String str) {
        this.zztm = str;
    }

    public final void zzg(String str) {
        this.zzno = str;
    }

    public final void zzh(String str) {
        this.zztn = str;
    }

    public final void zzi(String str) {
        this.zzto = str;
    }

    public final void zzj(String str) {
        this.zztp = str;
    }

    public final void zzk(String str) {
        this.zztq = str;
    }
}
