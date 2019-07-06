package com.google.android.gms.wallet.fragment;

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
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

@Class(creator = "WalletFragmentInitParamsCreator")
@Reserved({1})
public final class WalletFragmentInitParams extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<WalletFragmentInitParams> CREATOR = new zzd();
    /* access modifiers changed from: private */
    @Field(getter = "getAccountName", id = 2)
    public String zzci;
    /* access modifiers changed from: private */
    @Field(getter = "getMaskedWalletRequest", id = 3)
    public MaskedWalletRequest zzfi;
    /* access modifiers changed from: private */
    @Field(getter = "getMaskedWallet", id = 5)
    public MaskedWallet zzfj;
    /* access modifiers changed from: private */
    @Field(defaultValue = "-1", getter = "getMaskedWalletRequestCode", id = 4)
    public int zzfx;

    public final class a {
        private a() {
        }

        public final a a(int i) {
            WalletFragmentInitParams.this.zzfx = i;
            return this;
        }

        public final a a(MaskedWalletRequest maskedWalletRequest) {
            WalletFragmentInitParams.this.zzfi = maskedWalletRequest;
            return this;
        }

        public final WalletFragmentInitParams a() {
            boolean z = false;
            Preconditions.checkState((WalletFragmentInitParams.this.zzfj != null && WalletFragmentInitParams.this.zzfi == null) || (WalletFragmentInitParams.this.zzfj == null && WalletFragmentInitParams.this.zzfi != null), "Exactly one of MaskedWallet or MaskedWalletRequest is required");
            if (WalletFragmentInitParams.this.zzfx >= 0) {
                z = true;
            }
            Preconditions.checkState(z, "masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }
    }

    private WalletFragmentInitParams() {
        this.zzfx = -1;
    }

    @Constructor
    WalletFragmentInitParams(@Param(id = 2) String str, @Param(id = 3) MaskedWalletRequest maskedWalletRequest, @Param(id = 4) int i, @Param(id = 5) MaskedWallet maskedWallet) {
        this.zzci = str;
        this.zzfi = maskedWalletRequest;
        this.zzfx = i;
        this.zzfj = maskedWallet;
    }

    public static a newBuilder() {
        return new a();
    }

    public final String getAccountName() {
        return this.zzci;
    }

    public final MaskedWallet getMaskedWallet() {
        return this.zzfj;
    }

    public final MaskedWalletRequest getMaskedWalletRequest() {
        return this.zzfi;
    }

    public final int getMaskedWalletRequestCode() {
        return this.zzfx;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAccountName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getMaskedWalletRequest(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, getMaskedWalletRequestCode());
        SafeParcelWriter.writeParcelable(parcel, 5, getMaskedWallet(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
