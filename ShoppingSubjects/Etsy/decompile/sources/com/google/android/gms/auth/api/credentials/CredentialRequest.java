package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Class(creator = "CredentialRequestCreator")
public final class CredentialRequest extends AbstractSafeParcelable {
    public static final Creator<CredentialRequest> CREATOR = new zzh();
    @Field(getter = "isPasswordLoginSupported", id = 1)
    private final boolean zzcu;
    @Field(getter = "getAccountTypes", id = 2)
    private final String[] zzcv;
    @Field(getter = "getCredentialPickerConfig", id = 3)
    private final CredentialPickerConfig zzcw;
    @Field(getter = "getCredentialHintPickerConfig", id = 4)
    private final CredentialPickerConfig zzcx;
    @Field(getter = "isIdTokenRequested", id = 5)
    private final boolean zzcy;
    @Field(getter = "getServerClientId", id = 6)
    private final String zzcz;
    @Field(getter = "getIdTokenNonce", id = 7)
    private final String zzda;
    @Field(getter = "getRequireUserMediation", id = 8)
    private final boolean zzdb;
    @Field(id = 1000)
    private final int zzy;

    public static final class a {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public String[] b;
        /* access modifiers changed from: private */
        public CredentialPickerConfig c;
        /* access modifiers changed from: private */
        public CredentialPickerConfig d;
        /* access modifiers changed from: private */
        public boolean e;
        /* access modifiers changed from: private */
        @Nullable
        public String f;
        /* access modifiers changed from: private */
        @Nullable
        public String g;
    }

    @Constructor
    CredentialRequest(@Param(id = 1000) int i, @Param(id = 1) boolean z, @Param(id = 2) String[] strArr, @Param(id = 3) CredentialPickerConfig credentialPickerConfig, @Param(id = 4) CredentialPickerConfig credentialPickerConfig2, @Param(id = 5) boolean z2, @Param(id = 6) String str, @Param(id = 7) String str2, @Param(id = 8) boolean z3) {
        this.zzy = i;
        this.zzcu = z;
        this.zzcv = (String[]) Preconditions.checkNotNull(strArr);
        if (credentialPickerConfig == null) {
            credentialPickerConfig = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.a().a();
        }
        this.zzcw = credentialPickerConfig;
        if (credentialPickerConfig2 == null) {
            credentialPickerConfig2 = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.a().a();
        }
        this.zzcx = credentialPickerConfig2;
        if (i < 3) {
            this.zzcy = true;
            this.zzcz = null;
            this.zzda = null;
        } else {
            this.zzcy = z2;
            this.zzcz = str;
            this.zzda = str2;
        }
        this.zzdb = z3;
    }

    private CredentialRequest(a aVar) {
        this(4, aVar.a, aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, aVar.g, false);
    }

    @NonNull
    public final String[] getAccountTypes() {
        return this.zzcv;
    }

    @NonNull
    public final Set<String> getAccountTypesSet() {
        return new HashSet(Arrays.asList(this.zzcv));
    }

    @NonNull
    public final CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzcx;
    }

    @NonNull
    public final CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzcw;
    }

    @Nullable
    public final String getIdTokenNonce() {
        return this.zzda;
    }

    @Nullable
    public final String getServerClientId() {
        return this.zzcz;
    }

    @Deprecated
    public final boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public final boolean isIdTokenRequested() {
        return this.zzcy;
    }

    public final boolean isPasswordLoginSupported() {
        return this.zzcu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, isPasswordLoginSupported());
        SafeParcelWriter.writeStringArray(parcel, 2, getAccountTypes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getCredentialPickerConfig(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getCredentialHintPickerConfig(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzy);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
