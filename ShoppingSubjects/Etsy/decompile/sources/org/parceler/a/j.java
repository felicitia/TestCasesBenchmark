package org.parceler.a;

import android.os.Parcel;
import java.util.Map;
import java.util.Map.Entry;
import org.parceler.f;

/* compiled from: MapParcelConverter */
public abstract class j<K, V, M extends Map<K, V>> implements f<Map<K, V>, M> {
    public abstract K a(Parcel parcel);

    public abstract void a(K k, Parcel parcel);

    public abstract V b(Parcel parcel);

    public abstract M b();

    public abstract void b(V v, Parcel parcel);

    /* renamed from: a */
    public void toParcel(Map<K, V> map, Parcel parcel) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            a((K) entry.getKey(), parcel);
            b(entry.getValue(), parcel);
        }
    }

    /* renamed from: c */
    public M fromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1) {
            return null;
        }
        Map b = b();
        for (int i = 0; i < readInt; i++) {
            b.put(a(parcel), b(parcel));
        }
        return b;
    }
}
