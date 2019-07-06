package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;

public final class MaskedWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<MaskedWalletRequest> CREATOR = new zzz();
    ArrayList<Integer> zzai;
    String zzao;
    String zzax;
    Cart zzbh;
    boolean zzdd;
    boolean zzde;
    boolean zzdf;
    String zzdg;
    String zzdh;
    private boolean zzdi;
    boolean zzdj;
    private CountrySpecification[] zzdk;
    boolean zzdl;
    boolean zzdm;
    ArrayList<CountrySpecification> zzdn;
    PaymentMethodTokenizationParameters zzdo;
    String zzh;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzai == null) {
                    MaskedWalletRequest.this.zzai = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzai.addAll(collection);
            }
            return this;
        }

        public final Builder addAllowedCountrySpecificationsForShipping(Collection<CountrySpecification> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzdn == null) {
                    MaskedWalletRequest.this.zzdn = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzdn.addAll(collection);
            }
            return this;
        }

        public final MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }

        public final Builder setCart(Cart cart) {
            MaskedWalletRequest.this.zzbh = cart;
            return this;
        }

        public final Builder setCurrencyCode(String str) {
            MaskedWalletRequest.this.zzao = str;
            return this;
        }

        public final Builder setEstimatedTotalPrice(String str) {
            MaskedWalletRequest.this.zzdg = str;
            return this;
        }

        public final Builder setMerchantName(String str) {
            MaskedWalletRequest.this.zzdh = str;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            MaskedWalletRequest.this.zzdo = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            MaskedWalletRequest.this.zzdd = z;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            MaskedWalletRequest.this.zzde = z;
            return this;
        }
    }

    MaskedWalletRequest() {
        this.zzdl = true;
        this.zzdm = true;
    }

    MaskedWalletRequest(String str, boolean z, boolean z2, boolean z3, String str2, String str3, String str4, Cart cart, boolean z4, boolean z5, CountrySpecification[] countrySpecificationArr, boolean z6, boolean z7, ArrayList<CountrySpecification> arrayList, PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, ArrayList<Integer> arrayList2, String str5) {
        this.zzax = str;
        this.zzdd = z;
        this.zzde = z2;
        this.zzdf = z3;
        this.zzdg = str2;
        this.zzao = str3;
        this.zzdh = str4;
        this.zzbh = cart;
        this.zzdi = z4;
        this.zzdj = z5;
        this.zzdk = countrySpecificationArr;
        this.zzdl = z6;
        this.zzdm = z7;
        this.zzdn = arrayList;
        this.zzdo = paymentMethodTokenizationParameters;
        this.zzai = arrayList2;
        this.zzh = str5;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzax, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzdd);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzde);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzdf);
        SafeParcelWriter.writeString(parcel, 6, this.zzdg, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzao, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzdh, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzbh, i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzdi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzdj);
        SafeParcelWriter.writeTypedArray(parcel, 12, this.zzdk, i, false);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzdl);
        SafeParcelWriter.writeBoolean(parcel, 14, this.zzdm);
        SafeParcelWriter.writeTypedList(parcel, 15, this.zzdn, false);
        SafeParcelWriter.writeParcelable(parcel, 16, this.zzdo, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 17, this.zzai, false);
        SafeParcelWriter.writeString(parcel, 18, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
