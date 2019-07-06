package com.etsy.android.ui.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LocalSearchQuery implements Parcelable {
    public static final Creator<LocalSearchQuery> CREATOR = new Creator<LocalSearchQuery>() {
        /* renamed from: a */
        public LocalSearchQuery createFromParcel(Parcel parcel) {
            return new LocalSearchQuery(parcel);
        }

        /* renamed from: a */
        public LocalSearchQuery[] newArray(int i) {
            return new LocalSearchQuery[i];
        }
    };
    private static final int RADIUS_DEFAULT = 50000;
    private final double mLatitude;
    private final double mLongitude;
    private final int mRadius;

    public int describeContents() {
        return 0;
    }

    public LocalSearchQuery() {
        this.mLatitude = 0.0d;
        this.mLongitude = 0.0d;
        this.mRadius = 0;
    }

    public LocalSearchQuery(double d, double d2) {
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mRadius = 0;
    }

    public LocalSearchQuery(double d, double d2, int i) {
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mRadius = i;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public int getOrDefaultRadius() {
        return this.mRadius > 0 ? this.mRadius : RADIUS_DEFAULT;
    }

    public LocalSearchQuery(Parcel parcel) {
        this.mLatitude = parcel.readDouble();
        this.mLongitude = parcel.readDouble();
        this.mRadius = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
        parcel.writeInt(this.mRadius);
    }
}
