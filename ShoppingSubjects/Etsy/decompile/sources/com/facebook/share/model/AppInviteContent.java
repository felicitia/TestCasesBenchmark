package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.models.ShopAbout.Link;

@Deprecated
public final class AppInviteContent implements ShareModel {
    @Deprecated
    public static final Creator<AppInviteContent> CREATOR = new Creator<AppInviteContent>() {
        /* renamed from: a */
        public AppInviteContent createFromParcel(Parcel parcel) {
            return new AppInviteContent(parcel);
        }

        /* renamed from: a */
        public AppInviteContent[] newArray(int i) {
            return new AppInviteContent[i];
        }
    };
    private final String applinkUrl;
    private final Destination destination;
    private final String previewImageUrl;
    private final String promoCode;
    private final String promoText;

    @Deprecated
    public static class Builder {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public Destination e;

        @Deprecated
        public enum Destination {
            FACEBOOK(Link.FACEBOOK_TITLE),
            MESSENGER("messenger");
            
            private final String name;

            private Destination(String str) {
                this.name = str;
            }

            public boolean equalsName(String str) {
                if (str == null) {
                    return false;
                }
                return this.name.equals(str);
            }

            public String toString() {
                return this.name;
            }
        }
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    private AppInviteContent(Builder builder) {
        this.applinkUrl = builder.a;
        this.previewImageUrl = builder.b;
        this.promoCode = builder.c;
        this.promoText = builder.d;
        this.destination = builder.e;
    }

    @Deprecated
    AppInviteContent(Parcel parcel) {
        this.applinkUrl = parcel.readString();
        this.previewImageUrl = parcel.readString();
        this.promoText = parcel.readString();
        this.promoCode = parcel.readString();
        String readString = parcel.readString();
        if (readString.length() > 0) {
            this.destination = Destination.valueOf(readString);
        } else {
            this.destination = Destination.FACEBOOK;
        }
    }

    @Deprecated
    public String getApplinkUrl() {
        return this.applinkUrl;
    }

    @Deprecated
    public String getPreviewImageUrl() {
        return this.previewImageUrl;
    }

    @Deprecated
    public String getPromotionCode() {
        return this.promoCode;
    }

    @Deprecated
    public String getPromotionText() {
        return this.promoText;
    }

    @Deprecated
    public Destination getDestination() {
        if (this.destination != null) {
            return this.destination;
        }
        return Destination.FACEBOOK;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.applinkUrl);
        parcel.writeString(this.previewImageUrl);
        parcel.writeString(this.promoText);
        parcel.writeString(this.promoCode);
        parcel.writeString(this.destination.toString());
    }
}
