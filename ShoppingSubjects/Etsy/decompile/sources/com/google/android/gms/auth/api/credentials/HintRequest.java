package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "HintRequestCreator")
public final class HintRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<HintRequest> CREATOR = new zzk();
    @Field(getter = "getAccountTypes", id = 4)
    private final String[] zzcv;
    @Field(getter = "isIdTokenRequested", id = 5)
    private final boolean zzcy;
    @Field(getter = "getServerClientId", id = 6)
    private final String zzcz;
    @Field(getter = "getIdTokenNonce", id = 7)
    private final String zzda;
    @Field(getter = "getHintPickerConfig", id = 1)
    private final CredentialPickerConfig zzdc;
    @Field(getter = "isEmailAddressIdentifierSupported", id = 2)
    private final boolean zzdd;
    @Field(getter = "isPhoneNumberIdentifierSupported", id = 3)
    private final boolean zzde;
    @Field(id = 1000)
    private final int zzy;

    public static final class a {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public boolean b;
        /* access modifiers changed from: private */
        public String[] c;
        /* access modifiers changed from: private */
        public CredentialPickerConfig d;
        /* access modifiers changed from: private */
        public boolean e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public String g;
    }

    @Constructor
    HintRequest(@Param(id = 1000) int i, @Param(id = 1) CredentialPickerConfig credentialPickerConfig, @Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) String[] strArr, @Param(id = 5) boolean z3, @Param(id = 6) String str, @Param(id = 7) String str2) {
        this.zzy = i;
        this.zzdc = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
        this.zzdd = z;
        this.zzde = z2;
        this.zzcv = (String[]) Preconditions.checkNotNull(strArr);
        if (this.zzy < 2) {
            this.zzcy = true;
            this.zzcz = null;
            this.zzda = null;
            return;
        }
        this.zzcy = z3;
        this.zzcz = str;
        this.zzda = str2;
    }

    private HintRequest(a aVar) {
        this(2, aVar.d, aVar.a, aVar.b, aVar.c, aVar.e, aVar.f, aVar.g);
    }

    @NonNull
    public final String[] getAccountTypes() {
        return this.zzcv;
    }

    @NonNull
    public final CredentialPickerConfig getHintPickerConfig() {
        return this.zzdc;
    }

    @Nullable
    public final String getIdTokenNonce() {
        return this.zzda;
    }

    @Nullable
    public final String getServerClientId() {
        return this.zzcz;
    }

    public final boolean isEmailAddressIdentifierSupported() {
        return this.zzdd;
    }

    public final boolean isIdTokenRequested() {
        return this.zzcy;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getHintPickerConfig(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, isEmailAddressIdentifierSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzde);
        SafeParcelWriter.writeStringArray(parcel, 4, getAccountTypes(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
