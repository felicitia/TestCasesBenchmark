package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzcd<FieldDescriptorType extends zzcf<FieldDescriptorType>> {
    private static final zzcd zzji = new zzcd(true);
    private final zzem<FieldDescriptorType, Object> zzjf = zzem.zzas(16);
    private boolean zzjg;
    private boolean zzjh = false;

    private zzcd() {
    }

    private zzcd(boolean z) {
        zzbi();
    }

    public static <T extends zzcf<T>> zzcd<T> zzdf() {
        return zzji;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isEmpty() {
        return this.zzjf.isEmpty();
    }

    public final void zzbi() {
        if (!this.zzjg) {
            this.zzjf.zzbi();
            this.zzjg = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzjg;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcd)) {
            return false;
        }
        return this.zzjf.equals(((zzcd) obj).zzjf);
    }

    public final int hashCode() {
        return this.zzjf.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzjh) {
            return new zzcz(this.zzjf.entrySet().iterator());
        }
        return this.zzjf.entrySet().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzjh) {
            return new zzcz(this.zzjf.zzfl().iterator());
        }
        return this.zzjf.zzfl().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzjf.get(fielddescriptortype);
        return obj instanceof zzcw ? zzcw.zzeg() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzdl()) {
            zza(fielddescriptortype.zzdj(), obj);
            r7 = obj;
        } else if (!(obj instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzdj(), obj2);
            }
            r7 = arrayList;
        }
        if (r7 instanceof zzcw) {
            this.zzjh = true;
        }
        this.zzjf.put(fielddescriptortype, r7);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.firebase-perf.zzcw) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.firebase-perf.zzcp) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.firebase-perf.zzfq r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.firebase-perf.zzco.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.firebase-perf.zzce.zzjj
            com.google.android.gms.internal.firebase-perf.zzfv r2 = r2.zzgc()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x0040;
                case 2: goto L_0x003d;
                case 3: goto L_0x003a;
                case 4: goto L_0x0037;
                case 5: goto L_0x0034;
                case 6: goto L_0x0031;
                case 7: goto L_0x0028;
                case 8: goto L_0x001e;
                case 9: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0043
        L_0x0015:
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase-perf.zzdt
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase-perf.zzcw
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase-perf.zzcp
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = 1
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase-perf.zzbd
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x0031:
            boolean r0 = r3 instanceof java.lang.String
            goto L_0x0042
        L_0x0034:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L_0x0042
        L_0x0037:
            boolean r0 = r3 instanceof java.lang.Double
            goto L_0x0042
        L_0x003a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L_0x0042
        L_0x003d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L_0x0042
        L_0x0040:
            boolean r0 = r3 instanceof java.lang.Integer
        L_0x0042:
            r1 = r0
        L_0x0043:
            if (r1 != 0) goto L_0x004d
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzcd.zza(com.google.android.gms.internal.firebase-perf.zzfq, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzjf.zzfj(); i++) {
            if (!zzb(this.zzjf.zzat(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzjf.zzfk()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Entry<FieldDescriptorType, Object> entry) {
        zzcf zzcf = (zzcf) entry.getKey();
        if (zzcf.zzdk() == zzfv.MESSAGE) {
            if (zzcf.zzdl()) {
                for (zzdt isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzdt) {
                    if (!((zzdt) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzcw) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzcd<FieldDescriptorType> zzcd) {
        for (int i = 0; i < zzcd.zzjf.zzfj(); i++) {
            zzc(zzcd.zzjf.zzat(i));
        }
        for (Entry zzc : zzcd.zzjf.zzfk()) {
            zzc(zzc);
        }
    }

    private static Object zzd(Object obj) {
        if (obj instanceof zzdz) {
            return ((zzdz) obj).zzew();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Entry<FieldDescriptorType, Object> entry) {
        Object obj;
        zzcf zzcf = (zzcf) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzcw) {
            value = zzcw.zzeg();
        }
        if (zzcf.zzdl()) {
            Object zza = zza((FieldDescriptorType) zzcf);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzd : (List) value) {
                ((List) zza).add(zzd(zzd));
            }
            this.zzjf.put(zzcf, zza);
        } else if (zzcf.zzdk() == zzfv.MESSAGE) {
            Object zza2 = zza((FieldDescriptorType) zzcf);
            if (zza2 == null) {
                this.zzjf.put(zzcf, zzd(value));
                return;
            }
            if (zza2 instanceof zzdz) {
                obj = zzcf.zza((zzdz) zza2, (zzdz) value);
            } else {
                obj = zzcf.zza(((zzdt) zza2).zzdq(), (zzdt) value).zzdw();
            }
            this.zzjf.put(zzcf, obj);
        } else {
            this.zzjf.put(zzcf, zzd(value));
        }
    }

    static void zza(zzbt zzbt, zzfq zzfq, int i, Object obj) throws IOException {
        if (zzfq == zzfq.GROUP) {
            zzdt zzdt = (zzdt) obj;
            zzco.zzf(zzdt);
            zzbt.zzb(i, 3);
            zzdt.zzb(zzbt);
            zzbt.zzb(i, 4);
            return;
        }
        zzbt.zzb(i, zzfq.zzgd());
        switch (zzce.zzij[zzfq.ordinal()]) {
            case 1:
                zzbt.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzbt.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzbt.zzk(((Long) obj).longValue());
                return;
            case 4:
                zzbt.zzk(((Long) obj).longValue());
                return;
            case 5:
                zzbt.zzv(((Integer) obj).intValue());
                return;
            case 6:
                zzbt.zzm(((Long) obj).longValue());
                return;
            case 7:
                zzbt.zzy(((Integer) obj).intValue());
                return;
            case 8:
                zzbt.zzd(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzdt) obj).zzb(zzbt);
                return;
            case 10:
                zzbt.zzb((zzdt) obj);
                return;
            case 11:
                if (obj instanceof zzbd) {
                    zzbt.zza((zzbd) obj);
                    return;
                } else {
                    zzbt.zzm((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbd) {
                    zzbt.zza((zzbd) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzbt.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzbt.zzw(((Integer) obj).intValue());
                return;
            case 14:
                zzbt.zzy(((Integer) obj).intValue());
                return;
            case 15:
                zzbt.zzm(((Long) obj).longValue());
                return;
            case 16:
                zzbt.zzx(((Integer) obj).intValue());
                return;
            case 17:
                zzbt.zzl(((Long) obj).longValue());
                return;
            case 18:
                if (!(obj instanceof zzcp)) {
                    zzbt.zzv(((Integer) obj).intValue());
                    break;
                } else {
                    zzbt.zzv(((zzcp) obj).zzdi());
                    return;
                }
        }
    }

    public final int zzdg() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzjf.zzfj(); i2++) {
            Entry zzat = this.zzjf.zzat(i2);
            i += zzb((zzcf) zzat.getKey(), zzat.getValue());
        }
        for (Entry entry : this.zzjf.zzfk()) {
            i += zzb((zzcf) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzdh() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzjf.zzfj(); i2++) {
            i += zzd(this.zzjf.zzat(i2));
        }
        for (Entry zzd : this.zzjf.zzfk()) {
            i += zzd(zzd);
        }
        return i;
    }

    private static int zzd(Entry<FieldDescriptorType, Object> entry) {
        zzcf zzcf = (zzcf) entry.getKey();
        Object value = entry.getValue();
        if (zzcf.zzdk() != zzfv.MESSAGE || zzcf.zzdl() || zzcf.zzdm()) {
            return zzb(zzcf, value);
        }
        if (value instanceof zzcw) {
            return zzbt.zzb(((zzcf) entry.getKey()).zzdi(), (zzda) (zzcw) value);
        }
        return zzbt.zzd(((zzcf) entry.getKey()).zzdi(), (zzdt) value);
    }

    static int zza(zzfq zzfq, int i, Object obj) {
        int zzz = zzbt.zzz(i);
        if (zzfq == zzfq.GROUP) {
            zzco.zzf((zzdt) obj);
            zzz <<= 1;
        }
        return zzz + zzb(zzfq, obj);
    }

    private static int zzb(zzfq zzfq, Object obj) {
        switch (zzce.zzij[zzfq.ordinal()]) {
            case 1:
                return zzbt.zzb(((Double) obj).doubleValue());
            case 2:
                return zzbt.zzb(((Float) obj).floatValue());
            case 3:
                return zzbt.zzn(((Long) obj).longValue());
            case 4:
                return zzbt.zzo(((Long) obj).longValue());
            case 5:
                return zzbt.zzaa(((Integer) obj).intValue());
            case 6:
                return zzbt.zzq(((Long) obj).longValue());
            case 7:
                return zzbt.zzad(((Integer) obj).intValue());
            case 8:
                return zzbt.zze(((Boolean) obj).booleanValue());
            case 9:
                return zzbt.zzd((zzdt) obj);
            case 10:
                if (obj instanceof zzcw) {
                    return zzbt.zza((zzda) (zzcw) obj);
                }
                return zzbt.zzc((zzdt) obj);
            case 11:
                if (obj instanceof zzbd) {
                    return zzbt.zzb((zzbd) obj);
                }
                return zzbt.zzn((String) obj);
            case 12:
                if (obj instanceof zzbd) {
                    return zzbt.zzb((zzbd) obj);
                }
                return zzbt.zzd((byte[]) obj);
            case 13:
                return zzbt.zzab(((Integer) obj).intValue());
            case 14:
                return zzbt.zzae(((Integer) obj).intValue());
            case 15:
                return zzbt.zzr(((Long) obj).longValue());
            case 16:
                return zzbt.zzac(((Integer) obj).intValue());
            case 17:
                return zzbt.zzp(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzcp) {
                    return zzbt.zzaf(((zzcp) obj).zzdi());
                }
                return zzbt.zzaf(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static int zzb(zzcf<?> zzcf, Object obj) {
        zzfq zzdj = zzcf.zzdj();
        int zzdi = zzcf.zzdi();
        if (!zzcf.zzdl()) {
            return zza(zzdj, zzdi, obj);
        }
        int i = 0;
        if (zzcf.zzdm()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzdj, zzb);
            }
            return zzbt.zzz(zzdi) + i + zzbt.zzah(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzdj, zzdi, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzcd zzcd = new zzcd();
        for (int i = 0; i < this.zzjf.zzfj(); i++) {
            Entry zzat = this.zzjf.zzat(i);
            zzcd.zza((FieldDescriptorType) (zzcf) zzat.getKey(), zzat.getValue());
        }
        for (Entry entry : this.zzjf.zzfk()) {
            zzcd.zza((FieldDescriptorType) (zzcf) entry.getKey(), entry.getValue());
        }
        zzcd.zzjh = this.zzjh;
        return zzcd;
    }
}
