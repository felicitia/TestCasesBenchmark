package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzda extends zzbe<String> implements zzdb, RandomAccess {
    private static final zzda zzjg;
    private static final zzdb zzjh = zzjg;
    private final List<Object> zzji;

    static {
        zzda zzda = new zzda();
        zzjg = zzda;
        zzda.zzp();
    }

    public zzda() {
        this(10);
    }

    public zzda(int i) {
        this(new ArrayList<>(i));
    }

    private zzda(ArrayList<Object> arrayList) {
        this.zzji = arrayList;
    }

    private static String zze(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbi ? ((zzbi) obj).zzu() : zzcm.zze((byte[]) obj);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        zzq();
        this.zzji.add(i, str);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzq();
        if (collection instanceof zzdb) {
            collection = ((zzdb) collection).zzbh();
        }
        boolean addAll = this.zzji.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final void clear() {
        zzq();
        this.zzji.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzji.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbi) {
            zzbi zzbi = (zzbi) obj;
            String zzu = zzbi.zzu();
            if (zzbi.zzv()) {
                this.zzji.set(i, zzu);
            }
            return zzu;
        }
        byte[] bArr = (byte[]) obj;
        String zze = zzcm.zze(bArr);
        if (zzcm.zzd(bArr)) {
            this.zzji.set(i, zze);
        }
        return zze;
    }

    public final Object getRaw(int i) {
        return this.zzji.get(i);
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        zzq();
        Object remove = this.zzji.remove(i);
        this.modCount++;
        return zze(remove);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        zzq();
        return zze(this.zzji.set(i, str));
    }

    public final int size() {
        return this.zzji.size();
    }

    public final List<?> zzbh() {
        return Collections.unmodifiableList(this.zzji);
    }

    public final zzdb zzbi() {
        return zzo() ? new zzfa(this) : this;
    }

    public final /* synthetic */ zzcr zzh(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzji);
        return new zzda(arrayList);
    }

    public final /* bridge */ /* synthetic */ boolean zzo() {
        return super.zzo();
    }
}
