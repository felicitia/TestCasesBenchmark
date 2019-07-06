package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

public final class Cart extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<Cart> CREATOR = new zzg();
    String zzan;
    String zzao;
    ArrayList<LineItem> zzap;

    Cart() {
        this.zzap = new ArrayList<>();
    }

    Cart(String str, String str2, ArrayList<LineItem> arrayList) {
        this.zzan = str;
        this.zzao = str2;
        this.zzap = arrayList;
    }

    public final String getCurrencyCode() {
        return this.zzao;
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
