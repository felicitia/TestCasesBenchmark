package com.etsy.android.vespa;

import android.os.Parcel;
import android.os.Parcelable;
import org.parceler.ParcelerRuntimeException;
import org.parceler.a;
import org.parceler.c;

public class PositionList$$Parcelable implements Parcelable, c<PositionList> {
    public static final PositionList$$Parcelable$Creator$$245 CREATOR = new PositionList$$Parcelable$Creator$$245();
    private PositionList positionList$$0;

    public int describeContents() {
        return 0;
    }

    public PositionList$$Parcelable(PositionList positionList) {
        this.positionList$$0 = positionList;
    }

    public void writeToParcel(Parcel parcel, int i) {
        write(this.positionList$$0, parcel, i, new a());
    }

    public static void write(PositionList positionList, Parcel parcel, int i, a aVar) {
        int b = aVar.b((Object) positionList);
        if (b != -1) {
            parcel.writeInt(b);
            return;
        }
        parcel.writeInt(aVar.a((Object) positionList));
        parcel.writeInt(positionList.mParentPosition);
        parcel.writeInt(positionList.mChildPosition);
    }

    public PositionList getParcel() {
        return this.positionList$$0;
    }

    public static PositionList read(Parcel parcel, a aVar) {
        int readInt = parcel.readInt();
        if (!aVar.a(readInt)) {
            int a = aVar.a();
            PositionList positionList = new PositionList();
            aVar.a(a, positionList);
            positionList.mParentPosition = parcel.readInt();
            positionList.mChildPosition = parcel.readInt();
            return positionList;
        } else if (!aVar.b(readInt)) {
            return (PositionList) aVar.c(readInt);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
