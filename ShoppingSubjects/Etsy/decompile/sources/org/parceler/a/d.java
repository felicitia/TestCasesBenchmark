package org.parceler.a;

import android.os.Parcel;
import java.util.Collection;
import org.parceler.f;

/* compiled from: CollectionParcelConverter */
public abstract class d<T, C extends Collection<T>> implements f<Collection<T>, C> {
    public abstract T a(Parcel parcel);

    public abstract void a(T t, Parcel parcel);

    public abstract C b();

    /* renamed from: a */
    public void toParcel(Collection<T> collection, Parcel parcel) {
        if (collection == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(collection.size());
        for (T a : collection) {
            a(a, parcel);
        }
    }

    /* renamed from: b */
    public C fromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1) {
            return null;
        }
        Collection b = b();
        for (int i = 0; i < readInt; i++) {
            b.add(a(parcel));
        }
        return b;
    }
}
