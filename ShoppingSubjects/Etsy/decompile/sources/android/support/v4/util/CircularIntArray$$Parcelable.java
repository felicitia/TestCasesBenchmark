package android.support.v4.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.etsy.android.lib.util.v.d;
import org.parceler.ParcelerRuntimeException;
import org.parceler.a;
import org.parceler.c;

public class CircularIntArray$$Parcelable implements Parcelable, c<CircularIntArray> {
    public static final CircularIntArray$$Parcelable$Creator$$0 CREATOR = new CircularIntArray$$Parcelable$Creator$$0();
    private CircularIntArray circularIntArray$$0;

    public int describeContents() {
        return 0;
    }

    public CircularIntArray$$Parcelable(CircularIntArray circularIntArray) {
        this.circularIntArray$$0 = circularIntArray;
    }

    public void writeToParcel(Parcel parcel, int i) {
        write(this.circularIntArray$$0, parcel, i, new a());
    }

    public static void write(CircularIntArray circularIntArray, Parcel parcel, int i, a aVar) {
        int b = aVar.b((Object) circularIntArray);
        if (b != -1) {
            parcel.writeInt(b);
            return;
        }
        parcel.writeInt(aVar.a((Object) circularIntArray));
        new d().toParcel(circularIntArray, parcel);
    }

    public CircularIntArray getParcel() {
        return this.circularIntArray$$0;
    }

    public static CircularIntArray read(Parcel parcel, a aVar) {
        int readInt = parcel.readInt();
        if (!aVar.a(readInt)) {
            return new d().fromParcel(parcel);
        }
        if (!aVar.b(readInt)) {
            return (CircularIntArray) aVar.c(readInt);
        }
        throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
    }
}
