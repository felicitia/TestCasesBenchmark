package com.google.android.gms.internal.firebase-perf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzdb extends zzaz<String> implements zzdc, RandomAccess {
    private static final zzdb zznv;
    private static final zzdc zznw = zznv;
    private final List<Object> zznx;

    public zzdb() {
        this(10);
    }

    public zzdb(int i) {
        this(new ArrayList<>(i));
    }

    private zzdb(ArrayList<Object> arrayList) {
        this.zznx = arrayList;
    }

    public final int size() {
        return this.zznx.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzbj();
        if (collection instanceof zzdc) {
            collection = ((zzdc) collection).zzei();
        }
        boolean addAll = this.zznx.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzbj();
        this.zznx.clear();
        this.modCount++;
    }

    public final void zzc(zzbd zzbd) {
        zzbj();
        this.zznx.add(zzbd);
        this.modCount++;
    }

    public final Object getRaw(int i) {
        return this.zznx.get(i);
    }

    private static String zze(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbd) {
            return ((zzbd) obj).zzbm();
        }
        return zzco.zzf((byte[]) obj);
    }

    public final List<?> zzei() {
        return Collections.unmodifiableList(this.zznx);
    }

    public final zzdc zzej() {
        return zzbh() ? new zzfe(this) : this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        zzbj();
        return zze(this.zznx.set(i, str));
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbj();
        Object remove = this.zznx.remove(i);
        this.modCount++;
        return zze(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzbh() {
        return super.zzbh();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        zzbj();
        this.zznx.add(i, str);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ zzcs zzi(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zznx);
        return new zzdb(arrayList);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zznx.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbd) {
            zzbd zzbd = (zzbd) obj;
            String zzbm = zzbd.zzbm();
            if (zzbd.zzbn()) {
                this.zznx.set(i, zzbm);
            }
            return zzbm;
        }
        byte[] bArr = (byte[]) obj;
        String zzf = zzco.zzf(bArr);
        if (zzco.zze(bArr)) {
            this.zznx.set(i, zzf);
        }
        return zzf;
    }

    static {
        zzdb zzdb = new zzdb();
        zznv = zzdb;
        zzdb.zzbi();
    }
}
