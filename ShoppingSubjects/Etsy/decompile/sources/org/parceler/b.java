package org.parceler;

import android.os.Parcel;

public interface b extends f {

    /* compiled from: ParcelConverter */
    public static class a implements b<Object> {
        public void toParcel(Object obj, Parcel parcel) {
            throw new ParcelerRuntimeException("Empty Converter should not be used.");
        }

        public Object fromParcel(Parcel parcel) {
            throw new ParcelerRuntimeException("Empty Converter should not be used.");
        }
    }
}
