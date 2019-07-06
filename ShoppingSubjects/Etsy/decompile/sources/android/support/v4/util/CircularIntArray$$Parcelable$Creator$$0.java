package android.support.v4.util;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.parceler.a;

/* compiled from: CircularIntArray$$Parcelable */
public final class CircularIntArray$$Parcelable$Creator$$0 implements Creator<CircularIntArray$$Parcelable> {
    public CircularIntArray$$Parcelable createFromParcel(Parcel parcel) {
        return new CircularIntArray$$Parcelable(CircularIntArray$$Parcelable.read(parcel, new a()));
    }

    public CircularIntArray$$Parcelable[] newArray(int i) {
        return new CircularIntArray$$Parcelable[i];
    }
}
