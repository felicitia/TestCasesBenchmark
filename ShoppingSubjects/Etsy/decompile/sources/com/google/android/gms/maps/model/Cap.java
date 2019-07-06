package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

@Class(creator = "CapCreator")
@Reserved({1})
public class Cap extends AbstractSafeParcelable {
    public static final Creator<Cap> CREATOR = new zzb();
    private static final String TAG = "Cap";
    @Nullable
    @Field(getter = "getWrappedBitmapDescriptorImplBinder", id = 3, type = "android.os.IBinder")
    private final a bitmapDescriptor;
    @Field(getter = "getType", id = 2)
    private final int type;
    @Nullable
    @Field(getter = "getBitmapRefWidth", id = 4)
    private final Float zzcm;

    protected Cap(int i) {
        this(i, (a) null, (Float) null);
    }

    @Constructor
    Cap(@Param(id = 2) int i, @Nullable @Param(id = 3) IBinder iBinder, @Nullable @Param(id = 4) Float f) {
        this(i, iBinder == null ? null : new a(Stub.asInterface(iBinder)), f);
    }

    private Cap(int i, @Nullable a aVar, @Nullable Float f) {
        Preconditions.checkArgument(i != 3 || (aVar != null && (f != null && (f.floatValue() > 0.0f ? 1 : (f.floatValue() == 0.0f ? 0 : -1)) > 0)), String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[]{Integer.valueOf(i), aVar, f}));
        this.type = i;
        this.bitmapDescriptor = aVar;
        this.zzcm = f;
    }

    protected Cap(@NonNull a aVar, float f) {
        this(3, aVar, Float.valueOf(f));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && Objects.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && Objects.equal(this.zzcm, cap.zzcm);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcm);
    }

    public String toString() {
        int i = this.type;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeIBinder(parcel, 3, this.bitmapDescriptor == null ? null : this.bitmapDescriptor.a().asBinder(), false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzcm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: 0000 */
    public final Cap zzg() {
        switch (this.type) {
            case 0:
                return new ButtCap();
            case 1:
                return new SquareCap();
            case 2:
                return new RoundCap();
            case 3:
                return new CustomCap(this.bitmapDescriptor, this.zzcm.floatValue());
            default:
                String str = TAG;
                int i = this.type;
                StringBuilder sb = new StringBuilder(29);
                sb.append("Unknown Cap type: ");
                sb.append(i);
                Log.w(str, sb.toString());
                return this;
        }
    }
}
