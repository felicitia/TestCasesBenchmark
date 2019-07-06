package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzcf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzcd<FieldDescriptorType extends zzcf<FieldDescriptorType>> {
    private static final zzcd zzeu = new zzcd(true);
    private final zzei<FieldDescriptorType, Object> zzer = zzei.zzae(16);
    private boolean zzes;
    private boolean zzet = false;

    private zzcd() {
    }

    private zzcd(boolean z) {
        zzp();
    }

    static int zza(zzfl zzfl, int i, Object obj) {
        int zzp = zzbu.zzp(i);
        if (zzfl == zzfl.GROUP) {
            zzcm.zzf((zzdr) obj);
            zzp <<= 1;
        }
        return zzp + zzb(zzfl, obj);
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzer.get(fielddescriptortype);
        return obj instanceof zzcv ? zzcv.zzbf() : obj;
    }

    static void zza(zzbu zzbu, zzfl zzfl, int i, Object obj) throws IOException {
        if (zzfl == zzfl.GROUP) {
            zzdr zzdr = (zzdr) obj;
            zzcm.zzf(zzdr);
            zzbu.zzb(i, 3);
            zzdr.zzb(zzbu);
            zzbu.zzb(i, 4);
            return;
        }
        zzbu.zzb(i, zzfl.zzcz());
        switch (zzce.zzew[zzfl.ordinal()]) {
            case 1:
                zzbu.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzbu.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzbu.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzbu.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzbu.zzl(((Integer) obj).intValue());
                return;
            case 6:
                zzbu.zzd(((Long) obj).longValue());
                return;
            case 7:
                zzbu.zzo(((Integer) obj).intValue());
                return;
            case 8:
                zzbu.zze(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzdr) obj).zzb(zzbu);
                return;
            case 10:
                zzbu.zzb((zzdr) obj);
                return;
            case 11:
                if (obj instanceof zzbi) {
                    zzbu.zza((zzbi) obj);
                    return;
                } else {
                    zzbu.zzh((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbi) {
                    zzbu.zza((zzbi) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzbu.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzbu.zzm(((Integer) obj).intValue());
                return;
            case 14:
                zzbu.zzo(((Integer) obj).intValue());
                return;
            case 15:
                zzbu.zzd(((Long) obj).longValue());
                return;
            case 16:
                zzbu.zzn(((Integer) obj).intValue());
                return;
            case 17:
                zzbu.zzc(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzcp) {
                    zzbu.zzl(((zzcp) obj).zzap());
                    return;
                } else {
                    zzbu.zzl(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzas()) {
            zza(fielddescriptortype.zzaq(), obj);
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
                zza(fielddescriptortype.zzaq(), obj2);
            }
            r7 = arrayList;
        }
        if (r7 instanceof zzcv) {
            this.zzet = true;
        }
        this.zzer.put(fielddescriptortype, r7);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.icing.zzcv) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.icing.zzcp) == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.icing.zzfl r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.icing.zzcm.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.icing.zzce.zzev
            com.google.android.gms.internal.icing.zzfq r2 = r2.zzcy()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzdr
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzcv
            if (r2 == 0) goto L_0x0043
            goto L_0x0026
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0026
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzcp
            if (r2 == 0) goto L_0x0043
        L_0x0026:
            r1 = 1
            goto L_0x0043
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzbi
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzcd.zza(com.google.android.gms.internal.icing.zzfl, java.lang.Object):void");
    }

    public static <T extends zzcf<T>> zzcd<T> zzam() {
        return zzeu;
    }

    private static int zzb(zzcf<?> zzcf, Object obj) {
        zzfl zzaq = zzcf.zzaq();
        int zzap = zzcf.zzap();
        if (!zzcf.zzas()) {
            return zza(zzaq, zzap, obj);
        }
        int i = 0;
        if (zzcf.zzat()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzaq, zzb);
            }
            return zzbu.zzp(zzap) + i + zzbu.zzx(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzaq, zzap, zza);
        }
        return i;
    }

    private static int zzb(zzfl zzfl, Object obj) {
        switch (zzce.zzew[zzfl.ordinal()]) {
            case 1:
                return zzbu.zzb(((Double) obj).doubleValue());
            case 2:
                return zzbu.zzb(((Float) obj).floatValue());
            case 3:
                return zzbu.zze(((Long) obj).longValue());
            case 4:
                return zzbu.zzf(((Long) obj).longValue());
            case 5:
                return zzbu.zzq(((Integer) obj).intValue());
            case 6:
                return zzbu.zzh(((Long) obj).longValue());
            case 7:
                return zzbu.zzt(((Integer) obj).intValue());
            case 8:
                return zzbu.zzf(((Boolean) obj).booleanValue());
            case 9:
                return zzbu.zzd((zzdr) obj);
            case 10:
                return obj instanceof zzcv ? zzbu.zza((zzcz) (zzcv) obj) : zzbu.zzc((zzdr) obj);
            case 11:
                return obj instanceof zzbi ? zzbu.zzb((zzbi) obj) : zzbu.zzi((String) obj);
            case 12:
                return obj instanceof zzbi ? zzbu.zzb((zzbi) obj) : zzbu.zzc((byte[]) obj);
            case 13:
                return zzbu.zzr(((Integer) obj).intValue());
            case 14:
                return zzbu.zzu(((Integer) obj).intValue());
            case 15:
                return zzbu.zzi(((Long) obj).longValue());
            case 16:
                return zzbu.zzs(((Integer) obj).intValue());
            case 17:
                return zzbu.zzg(((Long) obj).longValue());
            case 18:
                return obj instanceof zzcp ? zzbu.zzv(((zzcp) obj).zzap()) : zzbu.zzv(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Entry<FieldDescriptorType, Object> entry) {
        zzcf zzcf = (zzcf) entry.getKey();
        if (zzcf.zzar() == zzfq.MESSAGE) {
            if (zzcf.zzas()) {
                for (zzdr isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzdr) {
                    if (!((zzdr) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzcv) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    private final void zzc(Entry<FieldDescriptorType, Object> entry) {
        zzcf zzcf = (zzcf) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzcv) {
            value = zzcv.zzbf();
        }
        if (zzcf.zzas()) {
            Object zza = zza((FieldDescriptorType) zzcf);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzd : (List) value) {
                ((List) zza).add(zzd(zzd));
            }
            this.zzer.put(zzcf, zza);
        } else if (zzcf.zzar() == zzfq.MESSAGE) {
            Object zza2 = zza((FieldDescriptorType) zzcf);
            if (zza2 == null) {
                this.zzer.put(zzcf, zzd(value));
            } else {
                this.zzer.put(zzcf, zza2 instanceof zzdx ? zzcf.zza((zzdx) zza2, (zzdx) value) : zzcf.zza(((zzdr) zza2).zzaz(), (zzdr) value).zzbc());
            }
        } else {
            this.zzer.put(zzcf, zzd(value));
        }
    }

    private static int zzd(Entry<FieldDescriptorType, Object> entry) {
        zzcf zzcf = (zzcf) entry.getKey();
        Object value = entry.getValue();
        return (zzcf.zzar() != zzfq.MESSAGE || zzcf.zzas() || zzcf.zzat()) ? zzb(zzcf, value) : value instanceof zzcv ? zzbu.zzb(((zzcf) entry.getKey()).zzap(), (zzcz) (zzcv) value) : zzbu.zzb(((zzcf) entry.getKey()).zzap(), (zzdr) value);
    }

    private static Object zzd(Object obj) {
        if (obj instanceof zzdx) {
            return ((zzdx) obj).zzbv();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzcd zzcd = new zzcd();
        for (int i = 0; i < this.zzer.zzci(); i++) {
            Entry zzaf = this.zzer.zzaf(i);
            zzcd.zza((FieldDescriptorType) (zzcf) zzaf.getKey(), zzaf.getValue());
        }
        for (Entry entry : this.zzer.zzcj()) {
            zzcd.zza((FieldDescriptorType) (zzcf) entry.getKey(), entry.getValue());
        }
        zzcd.zzet = this.zzet;
        return zzcd;
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        return this.zzet ? new zzcy(this.zzer.zzck().iterator()) : this.zzer.zzck().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcd)) {
            return false;
        }
        return this.zzer.equals(((zzcd) obj).zzer);
    }

    public final int hashCode() {
        return this.zzer.hashCode();
    }

    /* access modifiers changed from: 0000 */
    public final boolean isEmpty() {
        return this.zzer.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzes;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzer.zzci(); i++) {
            if (!zzb(this.zzer.zzaf(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzer.zzcj()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzet ? new zzcy(this.zzer.entrySet().iterator()) : this.zzer.entrySet().iterator();
    }

    public final void zza(zzcd<FieldDescriptorType> zzcd) {
        for (int i = 0; i < zzcd.zzer.zzci(); i++) {
            zzc(zzcd.zzer.zzaf(i));
        }
        for (Entry zzc : zzcd.zzer.zzcj()) {
            zzc(zzc);
        }
    }

    public final int zzan() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzer.zzci(); i2++) {
            Entry zzaf = this.zzer.zzaf(i2);
            i += zzb((zzcf) zzaf.getKey(), zzaf.getValue());
        }
        for (Entry entry : this.zzer.zzcj()) {
            i += zzb((zzcf) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzao() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzer.zzci(); i2++) {
            i += zzd(this.zzer.zzaf(i2));
        }
        for (Entry zzd : this.zzer.zzcj()) {
            i += zzd(zzd);
        }
        return i;
    }

    public final void zzp() {
        if (!this.zzes) {
            this.zzer.zzp();
            this.zzes = true;
        }
    }
}
