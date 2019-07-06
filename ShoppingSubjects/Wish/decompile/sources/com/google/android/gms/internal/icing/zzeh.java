package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.List;

final class zzeh {
    private static final Class<?> zzkv = zzcg();
    private static final zzex<?, ?> zzkw = zzh(false);
    private static final zzex<?, ?> zzkx = zzh(true);
    private static final zzex<?, ?> zzky = new zzez();

    static int zza(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdg) {
            zzdg zzdg = (zzdg) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.zze(zzdg.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.zze(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void zza(int i, List<String> list, zzfr zzfr) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzfr zzfr, zzef zzef) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list, zzef);
        }
    }

    public static void zza(int i, List<Double> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzg(i, list, z);
        }
    }

    static <T, FT extends zzcf<FT>> void zza(zzca<FT> zzca, T t, T t2) {
        zzcd zza = zzca.zza((Object) t2);
        if (!zza.isEmpty()) {
            zzca.zzb(t).zza(zza);
        }
    }

    static <T> void zza(zzdm zzdm, T t, T t2, long j) {
        zzfd.zza((Object) t, j, zzdm.zzb(zzfd.zzo(t, j), zzfd.zzo(t2, j)));
    }

    static <T, UT, UB> void zza(zzex<UT, UB> zzex, T t, T t2) {
        zzex.zze(t, zzex.zzf(zzex.zzm(t), zzex.zzm(t2)));
    }

    static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdg) {
            zzdg zzdg = (zzdg) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.zzf(zzdg.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.zzf(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzb(int i, List<zzbi> list, zzfr zzfr) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzfr zzfr, zzef zzef) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list, zzef);
        }
    }

    public static void zzb(int i, List<Float> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzf(i, list, z);
        }
    }

    static int zzc(int i, Object obj, zzef zzef) {
        return obj instanceof zzcz ? zzbu.zza(i, (zzcz) obj) : zzbu.zzb(i, (zzdr) obj, zzef);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzp = zzbu.zzp(i) * size;
        if (list instanceof zzdb) {
            zzdb zzdb = (zzdb) list;
            while (i2 < size) {
                Object raw = zzdb.getRaw(i2);
                zzp += raw instanceof zzbi ? zzbu.zzb((zzbi) raw) : zzbu.zzi((String) raw);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzp += obj instanceof zzbi ? zzbu.zzb((zzbi) obj) : zzbu.zzi((String) obj);
                i2++;
            }
        }
        return zzp;
    }

    static int zzc(int i, List<?> list, zzef zzef) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzp = zzbu.zzp(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzp += obj instanceof zzcz ? zzbu.zza((zzcz) obj) : zzbu.zza((zzdr) obj, zzef);
        }
        return zzp;
    }

    static int zzc(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdg) {
            zzdg zzdg = (zzdg) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.zzg(zzdg.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.zzg(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzc(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzc(i, list, z);
        }
    }

    public static zzex<?, ?> zzcd() {
        return zzkw;
    }

    public static zzex<?, ?> zzce() {
        return zzkx;
    }

    public static zzex<?, ?> zzcf() {
        return zzky;
    }

    private static Class<?> zzcg() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzch() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static int zzd(int i, List<zzbi> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzp = size * zzbu.zzp(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzp += zzbu.zzb((zzbi) list.get(i2));
        }
        return zzp;
    }

    static int zzd(int i, List<zzdr> list, zzef zzef) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbu.zzc(i, (zzdr) list.get(i3), zzef);
        }
        return i2;
    }

    static int zzd(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcl) {
            zzcl zzcl = (zzcl) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.zzv(zzcl.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.zzv(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzd(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzd(i, list, z);
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcl) {
            zzcl zzcl = (zzcl) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.zzq(zzcl.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void zze(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzn(i, list, z);
        }
    }

    static int zzf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcl) {
            zzcl zzcl = (zzcl) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.zzr(zzcl.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.zzr(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzf(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zze(i, list, z);
        }
    }

    public static void zzf(Class<?> cls) {
        if (!zzck.class.isAssignableFrom(cls) && zzkv != null && !zzkv.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcl) {
            zzcl zzcl = (zzcl) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.zzs(zzcl.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.zzs(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzg(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzl(i, list, z);
        }
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    private static zzex<?, ?> zzh(boolean z) {
        try {
            Class zzch = zzch();
            if (zzch == null) {
                return null;
            }
            return (zzex) zzch.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzh(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list, z);
        }
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    public static void zzi(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzj(i, list, z);
        }
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    public static void zzj(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzi(i, list, z);
        }
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzbu.zzp(i));
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzbu.zzp(i));
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzbu.zzp(i));
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzbu.zzp(i));
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzbu.zzp(i));
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzbu.zzp(i));
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzbu.zzp(i));
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbu.zzj(i, 0);
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbu.zzg(i, 0);
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbu.zzb(i, true);
    }
}
