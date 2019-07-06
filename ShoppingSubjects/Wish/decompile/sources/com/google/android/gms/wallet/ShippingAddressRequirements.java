package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collection;

public final class ShippingAddressRequirements extends AbstractSafeParcelable {
    public static final Creator<ShippingAddressRequirements> CREATOR = new zzam();
    ArrayList<String> zzel;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCountryCodes(Collection<String> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("allowedCountryCodes can't be null or empty! If you don't have restrictions, just leave it unset.");
            }
            if (ShippingAddressRequirements.this.zzel == null) {
                ShippingAddressRequirements.this.zzel = new ArrayList<>();
            }
            ShippingAddressRequirements.this.zzel.addAll(collection);
            return this;
        }

        public final ShippingAddressRequirements build() {
            return ShippingAddressRequirements.this;
        }
    }

    private ShippingAddressRequirements() {
    }

    ShippingAddressRequirements(ArrayList<String> arrayList) {
        this.zzel = arrayList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, this.zzel, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
