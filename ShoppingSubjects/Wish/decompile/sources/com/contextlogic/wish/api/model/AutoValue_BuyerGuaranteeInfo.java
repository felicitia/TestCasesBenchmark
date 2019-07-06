package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

final class AutoValue_BuyerGuaranteeInfo extends C$AutoValue_BuyerGuaranteeInfo {
    public static final Creator<AutoValue_BuyerGuaranteeInfo> CREATOR = new Creator<AutoValue_BuyerGuaranteeInfo>() {
        public AutoValue_BuyerGuaranteeInfo createFromParcel(Parcel parcel) {
            AutoValue_BuyerGuaranteeInfo autoValue_BuyerGuaranteeInfo = new AutoValue_BuyerGuaranteeInfo(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readArrayList(BuyerGuaranteeInfo.class.getClassLoader()));
            return autoValue_BuyerGuaranteeInfo;
        }

        public AutoValue_BuyerGuaranteeInfo[] newArray(int i) {
            return new AutoValue_BuyerGuaranteeInfo[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    AutoValue_BuyerGuaranteeInfo(String str, String str2, String str3, String str4, String str5, List<PageItemHolder> list) {
        super(str, str2, str3, str4, str5, list);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getSectionTitle());
        parcel.writeString(getSectionSubtitle());
        parcel.writeString(getSectionBody());
        parcel.writeString(getPageTitle());
        parcel.writeString(getPageSubtitle());
        parcel.writeList(getPageItems());
    }
}
