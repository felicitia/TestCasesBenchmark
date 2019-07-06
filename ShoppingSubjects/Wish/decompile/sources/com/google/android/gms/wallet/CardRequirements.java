package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collection;

public final class CardRequirements extends AbstractSafeParcelable {
    public static final Creator<CardRequirements> CREATOR = new zze();
    ArrayList<Integer> zzai;
    boolean zzaj;
    boolean zzak;
    int zzal;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            Preconditions.checkArgument(collection != null && !collection.isEmpty(), "allowedCardNetworks can't be null or empty! You must provide a valid value from WalletConstants.CardNetwork.");
            if (CardRequirements.this.zzai == null) {
                CardRequirements.this.zzai = new ArrayList<>();
            }
            CardRequirements.this.zzai.addAll(collection);
            return this;
        }

        public final CardRequirements build() {
            Preconditions.checkNotNull(CardRequirements.this.zzai, "Allowed card networks must be non-empty! You can set it through addAllowedCardNetwork() or addAllowedCardNetworks() in the CardRequirements Builder.");
            return CardRequirements.this;
        }

        public final Builder setBillingAddressRequired(boolean z) {
            CardRequirements.this.zzak = z;
            return this;
        }
    }

    private CardRequirements() {
        this.zzaj = true;
    }

    CardRequirements(ArrayList<Integer> arrayList, boolean z, boolean z2, int i) {
        this.zzai = arrayList;
        this.zzaj = z;
        this.zzak = z2;
        this.zzal = i;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 1, this.zzai, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzaj);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzak);
        SafeParcelWriter.writeInt(parcel, 4, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
