package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class TransactionInfo extends AbstractSafeParcelable {
    public static final Creator<TransactionInfo> CREATOR = new zzao();
    String zzan;
    String zzao;
    int zzen;

    public final class Builder {
        private Builder() {
        }

        public final TransactionInfo build() {
            Preconditions.checkNotEmpty(TransactionInfo.this.zzao, "currencyCode must be set!");
            boolean z = true;
            if (!(TransactionInfo.this.zzen == 1 || TransactionInfo.this.zzen == 2 || TransactionInfo.this.zzen == 3)) {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException("totalPriceStatus must be set to one of WalletConstants.TotalPriceStatus!");
            }
            if (TransactionInfo.this.zzen == 2) {
                Preconditions.checkNotEmpty(TransactionInfo.this.zzan, "An estimated total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_ESTIMATED!");
            }
            if (TransactionInfo.this.zzen == 3) {
                Preconditions.checkNotEmpty(TransactionInfo.this.zzan, "An final total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_FINAL!");
            }
            return TransactionInfo.this;
        }

        public final Builder setCurrencyCode(String str) {
            TransactionInfo.this.zzao = str;
            return this;
        }

        public final Builder setTotalPrice(String str) {
            TransactionInfo.this.zzan = str;
            return this;
        }

        public final Builder setTotalPriceStatus(int i) {
            TransactionInfo.this.zzen = i;
            return this;
        }
    }

    private TransactionInfo() {
    }

    public TransactionInfo(int i, String str, String str2) {
        this.zzen = i;
        this.zzan = str;
        this.zzao = str2;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzen);
        SafeParcelWriter.writeString(parcel, 2, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzao, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
