package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "CredentialPickerConfigCreator")
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<CredentialPickerConfig> CREATOR = new zzf();
    @Field(getter = "shouldShowCancelButton", id = 2)
    private final boolean mShowCancelButton;
    @Field(getter = "shouldShowAddAccountButton", id = 1)
    private final boolean zzcq;
    @Field(getter = "isForNewAccount", id = 3)
    @Deprecated
    private final boolean zzcr;
    @Field(getter = "getPromptInternalId", id = 4)
    private final int zzcs;
    @Field(id = 1000)
    private final int zzy;

    public static class a {
        /* access modifiers changed from: private */
        public boolean a = false;
        /* access modifiers changed from: private */
        public boolean b = true;
        /* access modifiers changed from: private */
        public int c = 1;

        public CredentialPickerConfig a() {
            return new CredentialPickerConfig(this);
        }
    }

    @Constructor
    CredentialPickerConfig(@Param(id = 1000) int i, @Param(id = 1) boolean z, @Param(id = 2) boolean z2, @Param(id = 3) boolean z3, @Param(id = 4) int i2) {
        this.zzy = i;
        this.zzcq = z;
        this.mShowCancelButton = z2;
        boolean z4 = true;
        if (i < 2) {
            this.zzcr = z3;
            if (z3) {
                z4 = true;
            }
            this.zzcs = z4 ? 1 : 0;
            return;
        }
        if (i2 != 3) {
            z4 = false;
        }
        this.zzcr = z4;
        this.zzcs = i2;
    }

    private CredentialPickerConfig(a aVar) {
        this(2, aVar.a, aVar.b, false, aVar.c);
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzcs == 3;
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzcq;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.zzcs);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
