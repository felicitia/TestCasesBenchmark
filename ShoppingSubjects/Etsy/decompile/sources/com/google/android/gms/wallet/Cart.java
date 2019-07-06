package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "CartCreator")
@Reserved({1})
public final class Cart extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<Cart> CREATOR = new zzg();
    @Field(id = 2)
    String zzan;
    @Field(id = 3)
    String zzao;
    @Field(defaultValueUnchecked = "new java.util.ArrayList<LineItem>()", id = 4)
    ArrayList<LineItem> zzap;

    public final class a {
        private a() {
        }

        public final a a(String str) {
            Cart.this.zzan = str;
            return this;
        }

        public final a a(List<LineItem> list) {
            Cart.this.zzap.clear();
            Cart.this.zzap.addAll(list);
            return this;
        }

        public final Cart a() {
            return Cart.this;
        }

        public final a b(String str) {
            Cart.this.zzao = str;
            return this;
        }
    }

    Cart() {
        this.zzap = new ArrayList<>();
    }

    @Constructor
    Cart(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) ArrayList<LineItem> arrayList) {
        this.zzan = str;
        this.zzao = str2;
        this.zzap = arrayList;
    }

    public static a newBuilder() {
        return new a();
    }

    public final String getCurrencyCode() {
        return this.zzao;
    }

    public final ArrayList<LineItem> getLineItems() {
        return this.zzap;
    }

    public final String getTotalPrice() {
        return this.zzan;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzao, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzap, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
