package com.salesforce.marketingcloud.analytics;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

final class f extends a {
    public static final Creator<f> CREATOR = new Creator<f>() {
        /* renamed from: a */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel.readArrayList(PiCartItem.class.getClassLoader()));
        }

        /* renamed from: a */
        public f[] newArray(int i) {
            return new f[i];
        }
    };

    f(List<PiCartItem> list) {
        super(list);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(cartItems());
    }
}
