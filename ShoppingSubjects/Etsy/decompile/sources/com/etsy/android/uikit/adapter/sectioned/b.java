package com.etsy.android.uikit.adapter.sectioned;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/* compiled from: IRecyclerViewSection */
public interface b<ItemType> {

    /* compiled from: IRecyclerViewSection */
    public static class a {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (!(this.a == aVar.a && this.b == aVar.b)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return new HashCodeBuilder().append(this.a).append(this.b).build().intValue();
        }
    }

    ItemType a(int i);

    @NonNull
    String a();

    void a(int i, int i2);

    void a(int i, @NonNull ItemType itemtype);

    void a(int i, @NonNull Collection<ItemType> collection);

    int b();

    @Nullable
    ItemType b(int i);

    void b(int i, @NonNull ItemType itemtype);

    void b(int i, @NonNull Collection<ItemType> collection);

    @NonNull
    List<ItemType> c();
}
