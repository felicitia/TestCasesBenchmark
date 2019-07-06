package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import org.apache.commons.math3.geometry.VectorFormat;

@Class(creator = "AccountChangeEventCreator")
public class AccountChangeEvent extends AbstractSafeParcelable {
    public static final Creator<AccountChangeEvent> CREATOR = new zzd();
    @VersionField(id = 1)
    private final int zzh;
    @Field(id = 2)
    private final long zzi;
    @Field(id = 3)
    private final String zzj;
    @Field(id = 4)
    private final int zzk;
    @Field(id = 5)
    private final int zzl;
    @Field(id = 6)
    private final String zzm;

    @Constructor
    AccountChangeEvent(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 3) String str, @Param(id = 4) int i2, @Param(id = 5) int i3, @Param(id = 6) String str2) {
        this.zzh = i;
        this.zzi = j;
        this.zzj = (String) Preconditions.checkNotNull(str);
        this.zzk = i2;
        this.zzl = i3;
        this.zzm = str2;
    }

    public AccountChangeEvent(long j, String str, int i, int i2, String str2) {
        this.zzh = 1;
        this.zzi = j;
        this.zzj = (String) Preconditions.checkNotNull(str);
        this.zzk = i;
        this.zzl = i2;
        this.zzm = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AccountChangeEvent) {
            AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
            return this.zzh == accountChangeEvent.zzh && this.zzi == accountChangeEvent.zzi && Objects.equal(this.zzj, accountChangeEvent.zzj) && this.zzk == accountChangeEvent.zzk && this.zzl == accountChangeEvent.zzl && Objects.equal(this.zzm, accountChangeEvent.zzm);
        }
    }

    public String getAccountName() {
        return this.zzj;
    }

    public String getChangeData() {
        return this.zzm;
    }

    public int getChangeType() {
        return this.zzk;
    }

    public int getEventIndex() {
        return this.zzl;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzh), Long.valueOf(this.zzi), this.zzj, Integer.valueOf(this.zzk), Integer.valueOf(this.zzl), this.zzm);
    }

    public String toString() {
        String str = "UNKNOWN";
        switch (this.zzk) {
            case 1:
                str = "ADDED";
                break;
            case 2:
                str = "REMOVED";
                break;
            case 3:
                str = "RENAMED_FROM";
                break;
            case 4:
                str = "RENAMED_TO";
                break;
        }
        String str2 = this.zzj;
        String str3 = this.zzm;
        int i = this.zzl;
        StringBuilder sb = new StringBuilder(91 + String.valueOf(str2).length() + String.valueOf(str).length() + String.valueOf(str3).length());
        sb.append("AccountChangeEvent {accountName = ");
        sb.append(str2);
        sb.append(", changeType = ");
        sb.append(str);
        sb.append(", changeData = ");
        sb.append(str3);
        sb.append(", eventIndex = ");
        sb.append(i);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzh);
        SafeParcelWriter.writeLong(parcel, 2, this.zzi);
        SafeParcelWriter.writeString(parcel, 3, this.zzj, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzk);
        SafeParcelWriter.writeInt(parcel, 5, this.zzl);
        SafeParcelWriter.writeString(parcel, 6, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
