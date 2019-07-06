package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

public final class PaymentDataRequest extends AbstractSafeParcelable {
    public static final Creator<PaymentDataRequest> CREATOR = new zzae();
    ArrayList<Integer> zzbw;
    String zzby;
    boolean zzdd;
    boolean zzde;
    PaymentMethodTokenizationParameters zzdo;
    boolean zzdv;
    CardRequirements zzdw;
    ShippingAddressRequirements zzdx;
    TransactionInfo zzdy;
    boolean zzdz;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedPaymentMethod(int i) {
            if (PaymentDataRequest.this.zzbw == null) {
                PaymentDataRequest.this.zzbw = new ArrayList<>();
            }
            PaymentDataRequest.this.zzbw.add(Integer.valueOf(i));
            return this;
        }

        public final PaymentDataRequest build() {
            if (PaymentDataRequest.this.zzby == null) {
                Preconditions.checkNotNull(PaymentDataRequest.this.zzbw, "Allowed payment methods must be set! You can set it through addAllowedPaymentMethod() or addAllowedPaymentMethods() in the PaymentDataRequest Builder.");
                Preconditions.checkNotNull(PaymentDataRequest.this.zzdw, "Card requirements must be set!");
                if (PaymentDataRequest.this.zzdo != null) {
                    Preconditions.checkNotNull(PaymentDataRequest.this.zzdy, "Transaction info must be set if paymentMethodTokenizationParameters is set!");
                }
            }
            return PaymentDataRequest.this;
        }

        public final Builder setCardRequirements(CardRequirements cardRequirements) {
            PaymentDataRequest.this.zzdw = cardRequirements;
            return this;
        }

        public final Builder setEmailRequired(boolean z) {
            PaymentDataRequest.this.zzdv = z;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            PaymentDataRequest.this.zzdo = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            PaymentDataRequest.this.zzdd = z;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            PaymentDataRequest.this.zzde = z;
            return this;
        }

        public final Builder setShippingAddressRequirements(ShippingAddressRequirements shippingAddressRequirements) {
            PaymentDataRequest.this.zzdx = shippingAddressRequirements;
            return this;
        }

        public final Builder setTransactionInfo(TransactionInfo transactionInfo) {
            PaymentDataRequest.this.zzdy = transactionInfo;
            return this;
        }
    }

    private PaymentDataRequest() {
        this.zzdz = true;
    }

    PaymentDataRequest(boolean z, boolean z2, CardRequirements cardRequirements, boolean z3, ShippingAddressRequirements shippingAddressRequirements, ArrayList<Integer> arrayList, PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, TransactionInfo transactionInfo, boolean z4, String str) {
        this.zzdv = z;
        this.zzdd = z2;
        this.zzdw = cardRequirements;
        this.zzde = z3;
        this.zzdx = shippingAddressRequirements;
        this.zzbw = arrayList;
        this.zzdo = paymentMethodTokenizationParameters;
        this.zzdy = transactionInfo;
        this.zzdz = z4;
        this.zzby = str;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzdv);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdd);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdw, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzde);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzdx, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 6, this.zzbw, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzdy, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdz);
        SafeParcelWriter.writeString(parcel, 10, this.zzby, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
