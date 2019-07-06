package com.facebook.share.model;

import android.os.Parcel;

public abstract class ShareMessengerActionButton implements ShareModel {
    private final String title;

    public static abstract class a<M extends ShareMessengerActionButton, B extends a> {
        /* access modifiers changed from: private */
        public String a;
    }

    public int describeContents() {
        return 0;
    }

    protected ShareMessengerActionButton(a aVar) {
        this.title = aVar.a;
    }

    ShareMessengerActionButton(Parcel parcel) {
        this.title = parcel.readString();
    }

    public String getTitle() {
        return this.title;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
    }
}
