package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "SignInConfigurationCreator")
@Reserved({1})
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<SignInConfiguration> CREATOR = new zzy();
    @Field(getter = "getConsumerPkgName", id = 2)
    private final String zzfe;
    @Field(getter = "getGoogleConfig", id = 5)
    private GoogleSignInOptions zzff;

    @Constructor
    public SignInConfiguration(@Param(id = 2) String str, @Param(id = 5) GoogleSignInOptions googleSignInOptions) {
        this.zzfe = Preconditions.checkNotEmpty(str);
        this.zzff = googleSignInOptions;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        if (r3.zzff.equals(r4.zzff) != false) goto L_0x0023;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.google.android.gms.auth.api.signin.internal.SignInConfiguration r4 = (com.google.android.gms.auth.api.signin.internal.SignInConfiguration) r4     // Catch:{ ClassCastException -> 0x0025 }
            java.lang.String r1 = r3.zzfe     // Catch:{ ClassCastException -> 0x0025 }
            java.lang.String r2 = r4.zzfe     // Catch:{ ClassCastException -> 0x0025 }
            boolean r1 = r1.equals(r2)     // Catch:{ ClassCastException -> 0x0025 }
            if (r1 == 0) goto L_0x0025
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r1 = r3.zzff     // Catch:{ ClassCastException -> 0x0025 }
            if (r1 != 0) goto L_0x0019
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = r4.zzff     // Catch:{ ClassCastException -> 0x0025 }
            if (r4 != 0) goto L_0x0025
            goto L_0x0023
        L_0x0019:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r1 = r3.zzff     // Catch:{ ClassCastException -> 0x0025 }
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = r4.zzff     // Catch:{ ClassCastException -> 0x0025 }
            boolean r4 = r1.equals(r4)     // Catch:{ ClassCastException -> 0x0025 }
            if (r4 == 0) goto L_0x0025
        L_0x0023:
            r4 = 1
            return r4
        L_0x0025:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.SignInConfiguration.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return new a().a((Object) this.zzfe).a((Object) this.zzff).a();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzfe, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzff, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final GoogleSignInOptions zzt() {
        return this.zzff;
    }
}
