package com.etsy.android.androidpay;

import android.os.Parcel;
import android.os.Parcelable;
import com.etsy.android.lib.core.EtsyMoney$$Parcelable;
import com.etsy.android.lib.models.datatypes.EtsyId$$Parcelable;
import org.parceler.ParcelerRuntimeException;
import org.parceler.a;
import org.parceler.c;

public class LocalAndroidPayLineItem$$Parcelable implements Parcelable, c<LocalAndroidPayLineItem> {
    public static final LocalAndroidPayLineItem$$Parcelable$Creator$$0 CREATOR = new LocalAndroidPayLineItem$$Parcelable$Creator$$0();
    private LocalAndroidPayLineItem localAndroidPayLineItem$$0;

    public int describeContents() {
        return 0;
    }

    public LocalAndroidPayLineItem$$Parcelable(LocalAndroidPayLineItem localAndroidPayLineItem) {
        this.localAndroidPayLineItem$$0 = localAndroidPayLineItem;
    }

    public void writeToParcel(Parcel parcel, int i) {
        write(this.localAndroidPayLineItem$$0, parcel, i, new a());
    }

    public static void write(LocalAndroidPayLineItem localAndroidPayLineItem, Parcel parcel, int i, a aVar) {
        int b = aVar.b((Object) localAndroidPayLineItem);
        if (b != -1) {
            parcel.writeInt(b);
            return;
        }
        parcel.writeInt(aVar.a((Object) localAndroidPayLineItem));
        parcel.writeInt(localAndroidPayLineItem.quantity);
        parcel.writeInt(localAndroidPayLineItem.role);
        EtsyMoney$$Parcelable.write(localAndroidPayLineItem.price, parcel, i, aVar);
        parcel.writeString(localAndroidPayLineItem.description);
        EtsyId$$Parcelable.write(localAndroidPayLineItem.listingId, parcel, i, aVar);
        parcel.writeString(localAndroidPayLineItem.currencyCode);
    }

    public LocalAndroidPayLineItem getParcel() {
        return this.localAndroidPayLineItem$$0;
    }

    public static LocalAndroidPayLineItem read(Parcel parcel, a aVar) {
        int readInt = parcel.readInt();
        if (!aVar.a(readInt)) {
            int a = aVar.a();
            LocalAndroidPayLineItem localAndroidPayLineItem = new LocalAndroidPayLineItem();
            aVar.a(a, localAndroidPayLineItem);
            localAndroidPayLineItem.quantity = parcel.readInt();
            localAndroidPayLineItem.role = parcel.readInt();
            localAndroidPayLineItem.price = EtsyMoney$$Parcelable.read(parcel, aVar);
            localAndroidPayLineItem.description = parcel.readString();
            localAndroidPayLineItem.listingId = EtsyId$$Parcelable.read(parcel, aVar);
            localAndroidPayLineItem.currencyCode = parcel.readString();
            return localAndroidPayLineItem;
        } else if (!aVar.b(readInt)) {
            return (LocalAndroidPayLineItem) aVar.c(readInt);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
