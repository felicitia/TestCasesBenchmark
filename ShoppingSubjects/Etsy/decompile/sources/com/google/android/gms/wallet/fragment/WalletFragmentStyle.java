package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.wallet.c.C0147c;
import com.google.android.gms.wallet.c.b;

@Class(creator = "WalletFragmentStyleCreator")
@Reserved({1})
public final class WalletFragmentStyle extends AbstractSafeParcelable {
    public static final Creator<WalletFragmentStyle> CREATOR = new zzg();
    @Field(id = 2)
    private Bundle zzgb;
    @Field(id = 3)
    private int zzgc;

    public WalletFragmentStyle() {
        this.zzgb = new Bundle();
        this.zzgb.putInt("buyButtonAppearanceDefault", 4);
        this.zzgb.putInt("maskedWalletDetailsLogoImageTypeDefault", 3);
    }

    @Constructor
    WalletFragmentStyle(@Param(id = 2) Bundle bundle, @Param(id = 3) int i) {
        this.zzgb = bundle;
        this.zzgc = i;
    }

    private static long zza(int i) {
        if (i >= 0) {
            return zza(0, (float) i);
        }
        if (i == -1 || i == -2) {
            return zzc(GmsClientSupervisor.DEFAULT_BIND_FLAGS, i);
        }
        StringBuilder sb = new StringBuilder(39);
        sb.append("Unexpected dimension value: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    private static long zza(int i, float f) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return zzc(i, Float.floatToIntBits(f));
            default:
                StringBuilder sb = new StringBuilder(30);
                sb.append("Unrecognized unit: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private final void zza(TypedArray typedArray, int i, String str) {
        long j;
        if (!this.zzgb.containsKey(str)) {
            TypedValue peekValue = typedArray.peekValue(i);
            if (peekValue != null) {
                Bundle bundle = this.zzgb;
                int i2 = peekValue.type;
                if (i2 == 5) {
                    j = zzc(128, peekValue.data);
                } else if (i2 != 16) {
                    int i3 = peekValue.type;
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unexpected dimension type: ");
                    sb.append(i3);
                    throw new IllegalArgumentException(sb.toString());
                } else {
                    j = zza(peekValue.data);
                }
                bundle.putLong(str, j);
            }
        }
    }

    private final void zza(TypedArray typedArray, int i, String str, String str2) {
        if (!this.zzgb.containsKey(str) && !this.zzgb.containsKey(str2)) {
            TypedValue peekValue = typedArray.peekValue(i);
            if (peekValue != null) {
                if (peekValue.type < 28 || peekValue.type > 31) {
                    this.zzgb.putInt(str2, peekValue.resourceId);
                } else {
                    this.zzgb.putInt(str, peekValue.data);
                }
            }
        }
    }

    private final void zzb(TypedArray typedArray, int i, String str) {
        if (!this.zzgb.containsKey(str)) {
            TypedValue peekValue = typedArray.peekValue(i);
            if (peekValue != null) {
                this.zzgb.putInt(str, peekValue.data);
            }
        }
    }

    private static long zzc(int i, int i2) {
        return (((long) i) << 32) | (((long) i2) & 4294967295L);
    }

    public final WalletFragmentStyle setBuyButtonAppearance(int i) {
        this.zzgb.putInt("buyButtonAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int i) {
        this.zzgb.putLong("buyButtonHeight", zza(i));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int i, float f) {
        this.zzgb.putLong("buyButtonHeight", zza(i, f));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonText(int i) {
        this.zzgb.putInt("buyButtonText", i);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int i) {
        this.zzgb.putLong("buyButtonWidth", zza(i));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int i, float f) {
        this.zzgb.putLong("buyButtonWidth", zza(i, f));
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int i) {
        this.zzgb.remove("maskedWalletDetailsBackgroundResource");
        this.zzgb.putInt("maskedWalletDetailsBackgroundColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int i) {
        this.zzgb.remove("maskedWalletDetailsBackgroundColor");
        this.zzgb.putInt("maskedWalletDetailsBackgroundResource", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int i) {
        this.zzgb.remove("maskedWalletDetailsButtonBackgroundResource");
        this.zzgb.putInt("maskedWalletDetailsButtonBackgroundColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int i) {
        this.zzgb.remove("maskedWalletDetailsButtonBackgroundColor");
        this.zzgb.putInt("maskedWalletDetailsButtonBackgroundResource", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int i) {
        this.zzgb.putInt("maskedWalletDetailsButtonTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int i) {
        this.zzgb.putInt("maskedWalletDetailsHeaderTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int i) {
        this.zzgb.putInt("maskedWalletDetailsLogoImageType", i);
        return this;
    }

    @Deprecated
    public final WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int i) {
        this.zzgb.putInt("maskedWalletDetailsLogoTextColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int i) {
        this.zzgb.putInt("maskedWalletDetailsTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setStyleResourceId(int i) {
        this.zzgc = i;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzgb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzgc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza(String str, DisplayMetrics displayMetrics, int i) {
        int i2;
        if (!this.zzgb.containsKey(str)) {
            return i;
        }
        long j = this.zzgb.getLong(str);
        int i3 = (int) (j >>> 32);
        int i4 = (int) j;
        switch (i3) {
            case 0:
                i2 = 0;
                break;
            case 1:
                i2 = 1;
                break;
            case 2:
                i2 = 2;
                break;
            case 3:
                i2 = 3;
                break;
            case 4:
                i2 = 4;
                break;
            case 5:
                i2 = 5;
                break;
            default:
                switch (i3) {
                    case 128:
                        return TypedValue.complexToDimensionPixelSize(i4, displayMetrics);
                    case GmsClientSupervisor.DEFAULT_BIND_FLAGS /*129*/:
                        return i4;
                    default:
                        StringBuilder sb = new StringBuilder(36);
                        sb.append("Unexpected unit or type: ");
                        sb.append(i3);
                        throw new IllegalStateException(sb.toString());
                }
        }
        return Math.round(TypedValue.applyDimension(i2, Float.intBitsToFloat(i4), displayMetrics));
    }

    public final void zza(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.zzgc <= 0 ? b.WalletFragmentDefaultStyle : this.zzgc, C0147c.WalletFragmentStyle);
        zza(obtainStyledAttributes, C0147c.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
        zza(obtainStyledAttributes, C0147c.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
        zzb(obtainStyledAttributes, C0147c.WalletFragmentStyle_buyButtonText, "buyButtonText");
        zzb(obtainStyledAttributes, C0147c.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
        zzb(obtainStyledAttributes, C0147c.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
        zzb(obtainStyledAttributes, C0147c.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
        zza(obtainStyledAttributes, C0147c.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        zzb(obtainStyledAttributes, C0147c.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
        zza(obtainStyledAttributes, C0147c.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        zzb(obtainStyledAttributes, C0147c.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
        zzb(obtainStyledAttributes, C0147c.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
        obtainStyledAttributes.recycle();
    }
}
