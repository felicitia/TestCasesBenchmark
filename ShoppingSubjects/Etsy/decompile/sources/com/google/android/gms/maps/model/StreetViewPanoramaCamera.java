package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "StreetViewPanoramaCameraCreator")
@Reserved({1})
public class StreetViewPanoramaCamera extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<StreetViewPanoramaCamera> CREATOR = new zzm();
    @Field(id = 4)
    public final float bearing;
    @Field(id = 3)
    public final float tilt;
    @Field(id = 2)
    public final float zoom;
    @NonNull
    private final StreetViewPanoramaOrientation zzef;

    public static final class a {
        public float a;
        public float b;
        public float c;

        public a() {
        }

        public a(@NonNull StreetViewPanoramaCamera streetViewPanoramaCamera) {
            Preconditions.checkNotNull(streetViewPanoramaCamera, "StreetViewPanoramaCamera");
            this.c = streetViewPanoramaCamera.zoom;
            this.a = streetViewPanoramaCamera.bearing;
            this.b = streetViewPanoramaCamera.tilt;
        }
    }

    @Constructor
    public StreetViewPanoramaCamera(@Param(id = 2) float f, @Param(id = 3) float f2, @Param(id = 4) float f3) {
        boolean z = -90.0f <= f2 && f2 <= 90.0f;
        StringBuilder sb = new StringBuilder(62);
        sb.append("Tilt needs to be between -90 and 90 inclusive: ");
        sb.append(f2);
        Preconditions.checkArgument(z, sb.toString());
        if (((double) f) <= 0.0d) {
            f = 0.0f;
        }
        this.zoom = f;
        this.tilt = 0.0f + f2;
        this.bearing = (((double) f3) <= 0.0d ? (f3 % 360.0f) + 360.0f : f3) % 360.0f;
        this.zzef = new com.google.android.gms.maps.model.StreetViewPanoramaOrientation.a().a(f2).b(f3).a();
    }

    public static a builder() {
        return new a();
    }

    public static a builder(@NonNull StreetViewPanoramaCamera streetViewPanoramaCamera) {
        return new a(streetViewPanoramaCamera);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaCamera)) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) obj;
        return Float.floatToIntBits(this.zoom) == Float.floatToIntBits(streetViewPanoramaCamera.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(streetViewPanoramaCamera.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaCamera.bearing);
    }

    @NonNull
    public StreetViewPanoramaOrientation getOrientation() {
        return this.zzef;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zoom);
        SafeParcelWriter.writeFloat(parcel, 3, this.tilt);
        SafeParcelWriter.writeFloat(parcel, 4, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
