package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zze;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbv implements zzfw {
    private final zzbt zzhv;

    public static zzbv zza(zzbt zzbt) {
        if (zzbt.zzil != null) {
            return zzbt.zzil;
        }
        return new zzbv(zzbt);
    }

    private zzbv(zzbt zzbt) {
        this.zzhv = (zzbt) zzco.zza(zzbt, "output");
    }

    public final int zzcv() {
        return zze.zzmu;
    }

    public final void zzm(int i, int i2) throws IOException {
        this.zzhv.zzf(i, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzhv.zza(i, j);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzhv.zzc(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzhv.zza(i, f);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzhv.zza(i, d);
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzhv.zzc(i, i2);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzhv.zza(i, j);
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zzhv.zzc(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzhv.zzc(i, j);
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzhv.zzf(i, i2);
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zzhv.zzb(i, z);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzhv.zza(i, str);
    }

    public final void zza(int i, zzbd zzbd) throws IOException {
        this.zzhv.zza(i, zzbd);
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zzhv.zzd(i, i2);
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzhv.zze(i, i2);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzhv.zzb(i, j);
    }

    public final void zza(int i, Object obj, zzej zzej) throws IOException {
        this.zzhv.zza(i, (zzdt) obj, zzej);
    }

    public final void zzb(int i, Object obj, zzej zzej) throws IOException {
        zzbt zzbt = this.zzhv;
        zzdt zzdt = (zzdt) obj;
        zzbt.zzb(i, 3);
        zzej.zza(zzdt, zzbt.zzil);
        zzbt.zzb(i, 4);
    }

    public final void zzai(int i) throws IOException {
        this.zzhv.zzb(i, 3);
    }

    public final void zzaj(int i) throws IOException {
        this.zzhv.zzb(i, 4);
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbd) {
            this.zzhv.zzb(i, (zzbd) obj);
        } else {
            this.zzhv.zzb(i, (zzdt) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzaa(((Integer) list.get(i4)).intValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzv(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzad(((Integer) list.get(i4)).intValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzn(((Long) list.get(i4)).longValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzk(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzo(((Long) list.get(i4)).longValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzk(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzq(((Long) list.get(i4)).longValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzm(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzb(((Float) list.get(i4)).floatValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zza(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzb(((Double) list.get(i4)).doubleValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zza(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzaf(((Integer) list.get(i4)).intValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzv(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zze(((Boolean) list.get(i4)).booleanValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzd(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzb(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            while (i2 < list.size()) {
                Object raw = zzdc.getRaw(i2);
                if (raw instanceof String) {
                    this.zzhv.zza(i, (String) raw);
                } else {
                    this.zzhv.zza(i, (zzbd) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zza(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzbd> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzhv.zza(i, (zzbd) list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzab(((Integer) list.get(i4)).intValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzw(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzd(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzae(((Integer) list.get(i4)).intValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzr(((Long) list.get(i4)).longValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzm(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzac(((Integer) list.get(i4)).intValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzx(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhv.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbt.zzp(((Long) list.get(i4)).longValue());
            }
            this.zzhv.zzw(i3);
            while (i2 < list.size()) {
                this.zzhv.zzl(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhv.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzej zzej) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzej);
        }
    }

    public final void zzb(int i, List<?> list, zzej zzej) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzej);
        }
    }

    public final <K, V> void zza(int i, zzdm<K, V> zzdm, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzhv.zzb(i, 2);
            this.zzhv.zzw(zzdl.zza(zzdm, entry.getKey(), entry.getValue()));
            zzdl.zza(this.zzhv, zzdm, entry.getKey(), entry.getValue());
        }
    }
}
