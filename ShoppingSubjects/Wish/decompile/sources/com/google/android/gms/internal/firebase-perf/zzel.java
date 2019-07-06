package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzel {
    private static final Class<?> zzpm = zzfh();
    private static final zzfb<?, ?> zzpn = zzg(false);
    private static final zzfb<?, ?> zzpo = zzg(true);
    private static final zzfb<?, ?> zzpp = new zzfd();

    public static void zzg(Class<?> cls) {
        if (!zzcm.class.isAssignableFrom(cls) && zzpm != null && !zzpm.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzfw zzfw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzfw zzfw) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzbd> list, zzfw zzfw) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzfw zzfw, zzej zzej) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zza(i, list, zzej);
        }
    }

    public static void zzb(int i, List<?> list, zzfw zzfw, zzej zzej) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfw.zzb(i, list, zzej);
        }
    }

    static int zzr(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            i = 0;
            while (i2 < size) {
                i += zzbt.zzn(zzdh.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbt.zzn(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzr(list) + (list.size() * zzbt.zzz(i));
    }

    static int zzs(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            i = 0;
            while (i2 < size) {
                i += zzbt.zzo(zzdh.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbt.zzo(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzbt.zzz(i));
    }

    static int zzt(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            i = 0;
            while (i2 < size) {
                i += zzbt.zzp(zzdh.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbt.zzp(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzbt.zzz(i));
    }

    static int zzu(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            i = 0;
            while (i2 < size) {
                i += zzbt.zzaf(zzcn.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbt.zzaf(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzu(list) + (size * zzbt.zzz(i));
    }

    static int zzv(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            i = 0;
            while (i2 < size) {
                i += zzbt.zzaa(zzcn.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbt.zzaa(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzbt.zzz(i));
    }

    static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            i = 0;
            while (i2 < size) {
                i += zzbt.zzab(zzcn.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbt.zzab(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzbt.zzz(i));
    }

    static int zzx(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            i = 0;
            while (i2 < size) {
                i += zzbt.zzac(zzcn.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbt.zzac(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzbt.zzz(i));
    }

    static int zzy(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbt.zzj(i, 0);
    }

    static int zzz(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbt.zzg(i, 0);
    }

    static int zzaa(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbt.zzc(i, true);
    }

    static int zzc(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzz = zzbt.zzz(i) * size;
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            while (i4 < size) {
                Object raw = zzdc.getRaw(i4);
                if (raw instanceof zzbd) {
                    i3 = zzbt.zzb((zzbd) raw);
                } else {
                    i3 = zzbt.zzn((String) raw);
                }
                zzz += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzbd) {
                    i2 = zzbt.zzb((zzbd) obj);
                } else {
                    i2 = zzbt.zzn((String) obj);
                }
                zzz += i2;
                i4++;
            }
        }
        return zzz;
    }

    static int zzc(int i, Object obj, zzej zzej) {
        if (obj instanceof zzda) {
            return zzbt.zza(i, (zzda) obj);
        }
        return zzbt.zzb(i, (zzdt) obj, zzej);
    }

    static int zzc(int i, List<?> list, zzej zzej) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzbt.zzz(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzda) {
                i2 = zzbt.zza((zzda) obj);
            } else {
                i2 = zzbt.zzb((zzdt) obj, zzej);
            }
            zzz += i2;
        }
        return zzz;
    }

    static int zzd(int i, List<zzbd> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = size * zzbt.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzz += zzbt.zzb((zzbd) list.get(i2));
        }
        return zzz;
    }

    static int zzd(int i, List<zzdt> list, zzej zzej) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbt.zzc(i, (zzdt) list.get(i3), zzej);
        }
        return i2;
    }

    public static zzfb<?, ?> zzfe() {
        return zzpn;
    }

    public static zzfb<?, ?> zzff() {
        return zzpo;
    }

    public static zzfb<?, ?> zzfg() {
        return zzpp;
    }

    private static zzfb<?, ?> zzg(boolean z) {
        try {
            Class zzfi = zzfi();
            if (zzfi == null) {
                return null;
            }
            return (zzfb) zzfi.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzfh() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzfi() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzdo zzdo, T t, T t2, long j) {
        zzfh.zza((Object) t, j, zzdo.zzb(zzfh.zzp(t, j), zzfh.zzp(t2, j)));
    }

    static <T, FT extends zzcf<FT>> void zza(zzca<FT> zzca, T t, T t2) {
        zzcd zza = zzca.zza((Object) t2);
        if (!zza.isEmpty()) {
            zzca.zzb(t).zza(zza);
        }
    }

    static <T, UT, UB> void zza(zzfb<UT, UB> zzfb, T t, T t2) {
        zzfb.zze(t, zzfb.zzg(zzfb.zzp(t), zzfb.zzp(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzcr zzcr, UB ub, zzfb<UT, UB> zzfb) {
        UB ub2;
        if (zzcr == null) {
            return ub;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int intValue = ((Integer) it.next()).intValue();
                    if (!zzcr.zzal(intValue)) {
                        ub = zza(i, intValue, ub2, zzfb);
                        it.remove();
                    }
                }
                break loop1;
            }
        } else {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue2 = ((Integer) list.get(i3)).intValue();
                if (zzcr.zzal(intValue2)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = zza(i, intValue2, ub2, zzfb);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzfb<UT, UB> zzfb) {
        if (ub == null) {
            ub = zzfb.zzfr();
        }
        zzfb.zza(ub, i, (long) i2);
        return ub;
    }
}
