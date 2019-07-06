package com.etsy.android.androidpay;

import android.os.Parcel;
import android.os.Parcelable;
import com.etsy.android.lib.core.EtsyMoney$$Parcelable;
import com.etsy.android.lib.models.PaymentMethod;
import java.util.ArrayList;
import org.parceler.ParcelerRuntimeException;
import org.parceler.a;
import org.parceler.c;

public class LocalAndroidPayData$$Parcelable implements Parcelable, c<LocalAndroidPayData> {
    public static final LocalAndroidPayData$$Parcelable$Creator$$1 CREATOR = new LocalAndroidPayData$$Parcelable$Creator$$1();
    private LocalAndroidPayData localAndroidPayData$$0;

    public int describeContents() {
        return 0;
    }

    public LocalAndroidPayData$$Parcelable(LocalAndroidPayData localAndroidPayData) {
        this.localAndroidPayData$$0 = localAndroidPayData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        write(this.localAndroidPayData$$0, parcel, i, new a());
    }

    public static void write(LocalAndroidPayData localAndroidPayData, Parcel parcel, int i, a aVar) {
        int b = aVar.b((Object) localAndroidPayData);
        if (b != -1) {
            parcel.writeInt(b);
            return;
        }
        parcel.writeInt(aVar.a((Object) localAndroidPayData));
        if (localAndroidPayData.lineItems == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(localAndroidPayData.lineItems.size());
            for (LocalAndroidPayLineItem write : localAndroidPayData.lineItems) {
                LocalAndroidPayLineItem$$Parcelable.write(write, parcel, i, aVar);
            }
        }
        EtsyMoney$$Parcelable.write(localAndroidPayData.total, parcel, i, aVar);
        parcel.writeInt(localAndroidPayData.cartId);
        parcel.writeString(localAndroidPayData.shopName);
        parcel.writeSerializable(localAndroidPayData.paymentMethod);
    }

    public LocalAndroidPayData getParcel() {
        return this.localAndroidPayData$$0;
    }

    public static LocalAndroidPayData read(Parcel parcel, a aVar) {
        ArrayList arrayList;
        int readInt = parcel.readInt();
        if (!aVar.a(readInt)) {
            int a = aVar.a();
            LocalAndroidPayData localAndroidPayData = new LocalAndroidPayData();
            aVar.a(a, localAndroidPayData);
            int readInt2 = parcel.readInt();
            if (readInt2 < 0) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList(readInt2);
                for (int i = 0; i < readInt2; i++) {
                    arrayList2.add(LocalAndroidPayLineItem$$Parcelable.read(parcel, aVar));
                }
                arrayList = arrayList2;
            }
            localAndroidPayData.lineItems = arrayList;
            localAndroidPayData.total = EtsyMoney$$Parcelable.read(parcel, aVar);
            localAndroidPayData.cartId = parcel.readInt();
            localAndroidPayData.shopName = parcel.readString();
            localAndroidPayData.paymentMethod = (PaymentMethod) parcel.readSerializable();
            return localAndroidPayData;
        } else if (!aVar.b(readInt)) {
            return (LocalAndroidPayData) aVar.c(readInt);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
