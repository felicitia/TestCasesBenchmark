package com.etsy.android.vespa;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.parceler.a;

/* compiled from: PositionList$$Parcelable */
public final class PositionList$$Parcelable$Creator$$245 implements Creator<PositionList$$Parcelable> {
    public PositionList$$Parcelable createFromParcel(Parcel parcel) {
        return new PositionList$$Parcelable(PositionList$$Parcelable.read(parcel, new a()));
    }

    public PositionList$$Parcelable[] newArray(int i) {
        return new PositionList$$Parcelable[i];
    }
}
