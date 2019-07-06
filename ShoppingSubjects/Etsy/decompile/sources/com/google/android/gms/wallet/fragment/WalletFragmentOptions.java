package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.wallet.c.C0147c;

@Class(creator = "WalletFragmentOptionsCreator")
@Reserved({1})
public final class WalletFragmentOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<WalletFragmentOptions> CREATOR = new zzf();
    /* access modifiers changed from: private */
    @Field(defaultValueUnchecked = "com.google.android.gms.wallet.WalletConstants.ENVIRONMENT_PRODUCTION", getter = "getEnvironment", id = 2)
    public int environment;
    /* access modifiers changed from: private */
    @Field(defaultValueUnchecked = "com.google.android.gms.wallet.fragment.WalletFragmentMode.BUY_BUTTON", getter = "getMode", id = 5)
    public int mode;
    /* access modifiers changed from: private */
    @Field(defaultValueUnchecked = "com.google.android.gms.wallet.WalletConstants.THEME_DARK", getter = "getTheme", id = 3)
    public int theme;
    /* access modifiers changed from: private */
    @Field(getter = "getFragmentStyle", id = 4)
    public WalletFragmentStyle zzfz;

    public final class a {
        private a() {
        }

        public final a a(int i) {
            WalletFragmentOptions.this.environment = i;
            return this;
        }

        public final a a(WalletFragmentStyle walletFragmentStyle) {
            WalletFragmentOptions.this.zzfz = walletFragmentStyle;
            return this;
        }

        public final WalletFragmentOptions a() {
            return WalletFragmentOptions.this;
        }

        public final a b(int i) {
            WalletFragmentOptions.this.theme = i;
            return this;
        }

        public final a c(int i) {
            WalletFragmentOptions.this.mode = i;
            return this;
        }
    }

    private WalletFragmentOptions() {
        this.environment = 3;
        this.zzfz = new WalletFragmentStyle();
    }

    @Constructor
    WalletFragmentOptions(@Param(id = 2) int i, @Param(id = 3) int i2, @Param(id = 4) WalletFragmentStyle walletFragmentStyle, @Param(id = 5) int i3) {
        this.environment = i;
        this.theme = i2;
        this.zzfz = walletFragmentStyle;
        this.mode = i3;
    }

    public static a newBuilder() {
        return new a();
    }

    public static WalletFragmentOptions zza(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0147c.WalletFragmentOptions);
        int i = obtainStyledAttributes.getInt(C0147c.WalletFragmentOptions_appTheme, 0);
        int i2 = obtainStyledAttributes.getInt(C0147c.WalletFragmentOptions_environment, 1);
        int resourceId = obtainStyledAttributes.getResourceId(C0147c.WalletFragmentOptions_fragmentStyle, 0);
        int i3 = obtainStyledAttributes.getInt(C0147c.WalletFragmentOptions_fragmentMode, 1);
        obtainStyledAttributes.recycle();
        WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.theme = i;
        walletFragmentOptions.environment = i2;
        walletFragmentOptions.zzfz = new WalletFragmentStyle().setStyleResourceId(resourceId);
        walletFragmentOptions.zzfz.zza(context);
        walletFragmentOptions.mode = i3;
        return walletFragmentOptions;
    }

    public final int getEnvironment() {
        return this.environment;
    }

    public final WalletFragmentStyle getFragmentStyle() {
        return this.zzfz;
    }

    public final int getMode() {
        return this.mode;
    }

    public final int getTheme() {
        return this.theme;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getEnvironment());
        SafeParcelWriter.writeInt(parcel, 3, getTheme());
        SafeParcelWriter.writeParcelable(parcel, 4, getFragmentStyle(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getMode());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final void zza(Context context) {
        if (this.zzfz != null) {
            this.zzfz.zza(context);
        }
    }
}
