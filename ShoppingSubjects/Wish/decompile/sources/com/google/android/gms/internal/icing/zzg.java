package com.google.android.gms.internal.icing;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

public final class zzg extends AbstractSafeParcelable {
    public static final Creator<zzg> CREATOR = new zzi();
    private final Account account;
    private final zzl[] zzi;
    private final String zzj;
    private final boolean zzk;

    zzg(zzl[] zzlArr, String str, boolean z, Account account2) {
        this.zzi = zzlArr;
        this.zzj = str;
        this.zzk = z;
        this.account = account2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzg) {
            zzg zzg = (zzg) obj;
            if (Objects.equal(this.zzj, zzg.zzj) && Objects.equal(Boolean.valueOf(this.zzk), Boolean.valueOf(zzg.zzk)) && Objects.equal(this.account, zzg.account) && Arrays.equals(this.zzi, zzg.zzi)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzj, Boolean.valueOf(this.zzk), this.account, Integer.valueOf(Arrays.hashCode(this.zzi)));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 1, this.zzi, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzj, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzk);
        SafeParcelWriter.writeParcelable(parcel, 4, this.account, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
