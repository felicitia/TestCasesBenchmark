package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator = "MaskedWalletCreator")
@Reserved({1})
public final class MaskedWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<MaskedWallet> CREATOR = new zzx();
    @Field(id = 2)
    String zzaw;
    @Field(id = 3)
    String zzax;
    @Field(id = 5)
    String zzaz;
    @Field(id = 6)
    private zza zzba;
    @Field(id = 7)
    private zza zzbb;
    @Field(id = 4)
    String[] zzbc;
    @Field(id = 10)
    UserAddress zzbd;
    @Field(id = 11)
    UserAddress zzbe;
    @Field(id = 12)
    InstrumentInfo[] zzbf;
    @Field(id = 8)
    private LoyaltyWalletObject[] zzda;
    @Field(id = 9)
    private OfferWalletObject[] zzdb;

    public final class a {
        private a() {
        }

        public final a a(UserAddress userAddress) {
            MaskedWallet.this.zzbd = userAddress;
            return this;
        }

        public final a a(String str) {
            MaskedWallet.this.zzaw = str;
            return this;
        }

        public final a a(InstrumentInfo[] instrumentInfoArr) {
            MaskedWallet.this.zzbf = instrumentInfoArr;
            return this;
        }

        public final a a(String[] strArr) {
            MaskedWallet.this.zzbc = strArr;
            return this;
        }

        public final a b(UserAddress userAddress) {
            MaskedWallet.this.zzbe = userAddress;
            return this;
        }

        public final a b(String str) {
            MaskedWallet.this.zzax = str;
            return this;
        }

        public final a c(String str) {
            MaskedWallet.this.zzaz = str;
            return this;
        }
    }

    private MaskedWallet() {
    }

    @Constructor
    MaskedWallet(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String[] strArr, @Param(id = 5) String str3, @Param(id = 6) zza zza, @Param(id = 7) zza zza2, @Param(id = 8) LoyaltyWalletObject[] loyaltyWalletObjectArr, @Param(id = 9) OfferWalletObject[] offerWalletObjectArr, @Param(id = 10) UserAddress userAddress, @Param(id = 11) UserAddress userAddress2, @Param(id = 12) InstrumentInfo[] instrumentInfoArr) {
        this.zzaw = str;
        this.zzax = str2;
        this.zzbc = strArr;
        this.zzaz = str3;
        this.zzba = zza;
        this.zzbb = zza2;
        this.zzda = loyaltyWalletObjectArr;
        this.zzdb = offerWalletObjectArr;
        this.zzbd = userAddress;
        this.zzbe = userAddress2;
        this.zzbf = instrumentInfoArr;
    }

    public static a newBuilderFrom(MaskedWallet maskedWallet) {
        Preconditions.checkNotNull(maskedWallet);
        a c = new a().a(maskedWallet.getGoogleTransactionId()).b(maskedWallet.getMerchantTransactionId()).a(maskedWallet.getPaymentDescriptions()).a(maskedWallet.getInstrumentInfos()).c(maskedWallet.getEmail());
        MaskedWallet.this.zzda = maskedWallet.zzda;
        MaskedWallet.this.zzdb = maskedWallet.zzdb;
        return c.a(maskedWallet.getBuyerBillingAddress()).b(maskedWallet.getBuyerShippingAddress());
    }

    public final UserAddress getBuyerBillingAddress() {
        return this.zzbd;
    }

    public final UserAddress getBuyerShippingAddress() {
        return this.zzbe;
    }

    public final String getEmail() {
        return this.zzaz;
    }

    public final String getGoogleTransactionId() {
        return this.zzaw;
    }

    public final InstrumentInfo[] getInstrumentInfos() {
        return this.zzbf;
    }

    public final String getMerchantTransactionId() {
        return this.zzax;
    }

    public final String[] getPaymentDescriptions() {
        return this.zzbc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzax, false);
        SafeParcelWriter.writeStringArray(parcel, 4, this.zzbc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzaz, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzba, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzbb, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzda, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzdb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzbd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzbe, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 12, this.zzbf, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
