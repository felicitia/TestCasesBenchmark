package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;

@Class(creator = "AccountChangeEventsResponseCreator")
public class AccountChangeEventsResponse extends AbstractSafeParcelable {
    public static final Creator<AccountChangeEventsResponse> CREATOR = new zzf();
    @VersionField(id = 1)
    private final int zzh;
    @Field(id = 2)
    private final List<AccountChangeEvent> zzo;

    @Constructor
    AccountChangeEventsResponse(@Param(id = 1) int i, @Param(id = 2) List<AccountChangeEvent> list) {
        this.zzh = i;
        this.zzo = (List) Preconditions.checkNotNull(list);
    }

    public AccountChangeEventsResponse(List<AccountChangeEvent> list) {
        this.zzh = 1;
        this.zzo = (List) Preconditions.checkNotNull(list);
    }

    public List<AccountChangeEvent> getEvents() {
        return this.zzo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzh);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzo, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
