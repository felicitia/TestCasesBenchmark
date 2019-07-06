package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.ShareOpenGraphValueContainer.Builder;
import java.util.Set;

public abstract class ShareOpenGraphValueContainer<P extends ShareOpenGraphValueContainer, E extends Builder> implements ShareModel {
    private final Bundle bundle;

    public static abstract class Builder<P extends ShareOpenGraphValueContainer, E extends Builder> {
        /* access modifiers changed from: private */
        public Bundle bundle = new Bundle();

        public E putString(String str, String str2) {
            this.bundle.putString(str, str2);
            return this;
        }

        public E readFrom(P p) {
            if (p != null) {
                this.bundle.putAll(p.getBundle());
            }
            return this;
        }
    }

    public int describeContents() {
        return 0;
    }

    protected ShareOpenGraphValueContainer(Builder<P, E> builder) {
        this.bundle = (Bundle) builder.bundle.clone();
    }

    ShareOpenGraphValueContainer(Parcel parcel) {
        this.bundle = parcel.readBundle(Builder.class.getClassLoader());
    }

    public Object get(String str) {
        return this.bundle.get(str);
    }

    public String getString(String str) {
        return this.bundle.getString(str);
    }

    public Bundle getBundle() {
        return (Bundle) this.bundle.clone();
    }

    public Set<String> keySet() {
        return this.bundle.keySet();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.bundle);
    }
}
