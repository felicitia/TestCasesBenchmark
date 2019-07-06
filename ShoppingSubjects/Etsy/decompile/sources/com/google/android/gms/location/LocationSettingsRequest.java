package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Collections;
import java.util.List;

@Class(creator = "LocationSettingsRequestCreator")
@Reserved({1000})
public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public static final Creator<LocationSettingsRequest> CREATOR = new zzag();
    @Field(getter = "getLocationRequests", id = 1)
    private final List<LocationRequest> zzbg;
    @Field(defaultValue = "false", getter = "alwaysShow", id = 2)
    private final boolean zzbh;
    @Field(getter = "needBle", id = 3)
    private final boolean zzbi;
    @Field(getter = "getConfiguration", id = 5)
    private zzae zzbj;

    @Constructor
    LocationSettingsRequest(@Param(id = 1) List<LocationRequest> list, @Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 5) zzae zzae) {
        this.zzbg = list;
        this.zzbh = z;
        this.zzbi = z2;
        this.zzbj = zzae;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, Collections.unmodifiableList(this.zzbg), false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbh);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbi);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzbj, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
