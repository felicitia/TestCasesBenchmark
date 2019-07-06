package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzck.zzd;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbw implements zzfr {
    private final zzbu zzdw;

    private zzbw(zzbu zzbu) {
        this.zzdw = (zzbu) zzcm.zza(zzbu, "output");
    }

    public static zzbw zza(zzbu zzbu) {
        return zzbu.zzeh != null ? zzbu.zzeh : new zzbw(zzbu);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzdw.zza(i, d);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzdw.zza(i, f);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzdw.zza(i, j);
    }

    public final void zza(int i, zzbi zzbi) throws IOException {
        this.zzdw.zza(i, zzbi);
    }

    public final <K, V> void zza(int i, zzdk<K, V> zzdk, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzdw.zzb(i, 2);
            this.zzdw.zzm(zzcd.zza(zzdk.zzjr, 1, entry.getKey()) + zzcd.zza(zzdk.zzjs, 2, entry.getValue()));
            zzbu zzbu = this.zzdw;
            Object key = entry.getKey();
            Object value = entry.getValue();
            zzcd.zza(zzbu, zzdk.zzjr, 1, key);
            zzcd.zza(zzbu, zzdk.zzjs, 2, value);
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbi) {
            this.zzdw.zzb(i, (zzbi) obj);
        } else {
            this.zzdw.zza(i, (zzdr) obj);
        }
    }

    public final void zza(int i, Object obj, zzef zzef) throws IOException {
        this.zzdw.zza(i, (zzdr) obj, zzef);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzdw.zza(i, str);
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzdb) {
            zzdb zzdb = (zzdb) list;
            while (i2 < list.size()) {
                Object raw = zzdb.getRaw(i2);
                if (raw instanceof String) {
                    this.zzdw.zza(i, (String) raw);
                } else {
                    this.zzdw.zza(i, (zzbi) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zza(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzef zzef) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzef);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzq(((Integer) list.get(i4)).intValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzl(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zzdw.zza(i, z);
    }

    public final int zzad() {
        return zzd.zzie;
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzdw.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzef zzef) throws IOException {
        zzbu zzbu = this.zzdw;
        zzdr zzdr = (zzdr) obj;
        zzbu.zzb(i, 3);
        zzef.zza(zzdr, zzbu.zzeh);
        zzbu.zzb(i, 4);
    }

    public final void zzb(int i, List<zzbi> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzdw.zza(i, (zzbi) list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzef zzef) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzef);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzt(((Integer) list.get(i4)).intValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzo(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zzdw.zzc(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzdw.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zze(((Long) list.get(i4)).longValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zzdw.zzd(i, i2);
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzf(((Long) list.get(i4)).longValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzdw.zze(i, i2);
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzh(((Long) list.get(i4)).longValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzdw.zzf(i, i2);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzb(((Float) list.get(i4)).floatValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zza(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzb(((Double) list.get(i4)).doubleValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zza(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzv(((Integer) list.get(i4)).intValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzl(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzdw.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzf(((Boolean) list.get(i4)).booleanValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zze(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zza(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzdw.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzr(((Integer) list.get(i4)).intValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzm(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzd(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzu(((Integer) list.get(i4)).intValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzo(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzi(((Long) list.get(i4)).longValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, int i2) throws IOException {
        this.zzdw.zzf(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzs(((Integer) list.get(i4)).intValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzn(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzdw.zzc(i, i2);
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdw.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.zzg(((Long) list.get(i4)).longValue());
            }
            this.zzdw.zzm(i3);
            while (i2 < list.size()) {
                this.zzdw.zzc(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdw.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzy(int i) throws IOException {
        this.zzdw.zzb(i, 3);
    }

    public final void zzz(int i) throws IOException {
        this.zzdw.zzb(i, 4);
    }
}
