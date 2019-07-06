package com.etsy.etsyapi.api.shop.bespoke;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;

final class AutoValue_SellerDashboardSDLSpec extends C$AutoValue_SellerDashboardSDLSpec {
    private static final ClassLoader CL = AutoValue_SellerDashboardSDLSpec.class.getClassLoader();
    public static final Creator<AutoValue_SellerDashboardSDLSpec> CREATOR = new Creator<AutoValue_SellerDashboardSDLSpec>() {
        public AutoValue_SellerDashboardSDLSpec createFromParcel(Parcel parcel) {
            return new AutoValue_SellerDashboardSDLSpec(parcel);
        }

        public AutoValue_SellerDashboardSDLSpec[] newArray(int i) {
            return new AutoValue_SellerDashboardSDLSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_SellerDashboardSDLSpec(EtsyId etsyId, String str, String str2, Boolean bool) {
        super(etsyId, str, str2, bool);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(shop_id());
        parcel.writeValue(statsRange());
        parcel.writeValue(statsChannel());
        parcel.writeValue(includeShopManager());
    }

    private AutoValue_SellerDashboardSDLSpec(Parcel parcel) {
        this((EtsyId) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL), (Boolean) parcel.readValue(CL));
    }
}
