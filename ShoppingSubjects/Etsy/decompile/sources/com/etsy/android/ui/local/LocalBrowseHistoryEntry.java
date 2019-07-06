package com.etsy.android.ui.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

class LocalBrowseHistoryEntry implements Parcelable {
    public static final Creator<LocalBrowseHistoryEntry> CREATOR = new Creator<LocalBrowseHistoryEntry>() {
        /* renamed from: a */
        public LocalBrowseHistoryEntry createFromParcel(Parcel parcel) {
            return new LocalBrowseHistoryEntry(parcel);
        }

        /* renamed from: a */
        public LocalBrowseHistoryEntry[] newArray(int i) {
            return new LocalBrowseHistoryEntry[i];
        }
    };
    private final int a;
    private final String b;
    private final LocalSearchQuery c;
    private final String d;

    public int describeContents() {
        return 0;
    }

    public LocalBrowseHistoryEntry(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = null;
        this.d = str2;
    }

    public LocalBrowseHistoryEntry(int i, LocalSearchQuery localSearchQuery, String str) {
        this.a = i;
        this.b = "";
        this.c = localSearchQuery;
        this.d = str;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public LocalSearchQuery c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public LocalBrowseHistoryEntry(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = (LocalSearchQuery) parcel.readParcelable(LocalSearchQuery.class.getClassLoader());
        this.d = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, i);
        parcel.writeString(this.d);
    }
}
