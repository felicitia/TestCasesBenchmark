package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.ArrayList;

@Class(creator = "ShippingAddressRequirementsCreator")
public final class ShippingAddressRequirements extends AbstractSafeParcelable {
    public static final Creator<ShippingAddressRequirements> CREATOR = new zzam();
    @Field(id = 1)
    ArrayList<String> zzel;

    public final class a {
        private a() {
        }
    }

    private ShippingAddressRequirements() {
    }

    @Constructor
    ShippingAddressRequirements(@Param(id = 1) ArrayList<String> arrayList) {
        this.zzel = arrayList;
    }

    public static a newBuilder() {
        return new a();
    }

    @Nullable
    public final ArrayList<String> getAllowedCountryCodes() {
        return this.zzel;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, this.zzel, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
