package com.etsy.etsyapi.api.shop.bespoke;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;

final class AutoValue_SellerDashboardGetSpec extends C$AutoValue_SellerDashboardGetSpec {
    private static final ClassLoader CL = AutoValue_SellerDashboardGetSpec.class.getClassLoader();
    public static final Creator<AutoValue_SellerDashboardGetSpec> CREATOR = new Creator<AutoValue_SellerDashboardGetSpec>() {
        public AutoValue_SellerDashboardGetSpec createFromParcel(Parcel parcel) {
            return new AutoValue_SellerDashboardGetSpec(parcel);
        }

        public AutoValue_SellerDashboardGetSpec[] newArray(int i) {
            return new AutoValue_SellerDashboardGetSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_SellerDashboardGetSpec(EtsyId etsyId, String str, String str2, Boolean bool) {
        super(etsyId, str, str2, bool);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(shop_id());
        parcel.writeValue(statsRange());
        parcel.writeValue(statsChannel());
        parcel.writeValue(includeShopManager());
    }

    private AutoValue_SellerDashboardGetSpec(Parcel parcel) {
        this((EtsyId) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL), (Boolean) parcel.readValue(CL));
    }
}
