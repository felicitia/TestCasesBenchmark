package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "GeofencingRequestCreator")
@Reserved({1000})
public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Creator<GeofencingRequest> CREATOR = new zzq();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    @Field(defaultValue = "", getter = "getTag", id = 3)
    private final String tag;
    @Field(getter = "getParcelableGeofences", id = 1)
    private final List<zzbh> zzap;
    @Field(getter = "getInitialTrigger", id = 2)
    private final int zzaq;

    public static final class a {
        private final List<zzbh> a = new ArrayList();
        private int b = 5;
        private String c = "";

        public final a a(int i) {
            this.b = i & 7;
            return this;
        }

        public final a a(b bVar) {
            Preconditions.checkNotNull(bVar, "geofence can't be null.");
            Preconditions.checkArgument(bVar instanceof zzbh, "Geofence must be created using Geofence.Builder.");
            this.a.add((zzbh) bVar);
            return this;
        }

        public final GeofencingRequest a() {
            Preconditions.checkArgument(!this.a.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(this.a, this.b, this.c);
        }
    }

    @Constructor
    GeofencingRequest(@Param(id = 1) List<zzbh> list, @Param(id = 2) int i, @Param(id = 3) String str) {
        this.zzap = list;
        this.zzaq = i;
        this.tag = str;
    }

    public List<b> getGeofences() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzap);
        return arrayList;
    }

    public int getInitialTrigger() {
        return this.zzaq;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GeofencingRequest[");
        sb.append("geofences=");
        sb.append(this.zzap);
        int i = this.zzaq;
        StringBuilder sb2 = new StringBuilder(30);
        sb2.append(", initialTrigger=");
        sb2.append(i);
        sb2.append(", ");
        sb.append(sb2.toString());
        String str = "tag=";
        String valueOf = String.valueOf(this.tag);
        sb.append(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzap, false);
        SafeParcelWriter.writeInt(parcel, 2, getInitialTrigger());
        SafeParcelWriter.writeString(parcel, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
