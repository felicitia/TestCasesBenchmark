package com.etsy.etsyapi.api.shop.bespoke;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;

final class AutoValue_MissionControlStatsDashboardListingSpec extends C$AutoValue_MissionControlStatsDashboardListingSpec {
    private static final ClassLoader CL = AutoValue_MissionControlStatsDashboardListingSpec.class.getClassLoader();
    public static final Creator<AutoValue_MissionControlStatsDashboardListingSpec> CREATOR = new Creator<AutoValue_MissionControlStatsDashboardListingSpec>() {
        public AutoValue_MissionControlStatsDashboardListingSpec createFromParcel(Parcel parcel) {
            return new AutoValue_MissionControlStatsDashboardListingSpec(parcel);
        }

        public AutoValue_MissionControlStatsDashboardListingSpec[] newArray(int i) {
            return new AutoValue_MissionControlStatsDashboardListingSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_MissionControlStatsDashboardListingSpec(EtsyId etsyId, EtsyId etsyId2, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, Boolean bool, Boolean bool2, String str6) {
        super(etsyId, etsyId2, num, str, str2, num2, str3, str4, str5, num3, bool, bool2, str6);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(shop_id());
        parcel.writeValue(listing_id());
        parcel.writeValue(start_date());
        parcel.writeValue(start_date_str());
        parcel.writeValue(date_range());
        parcel.writeValue(end_date());
        parcel.writeValue(end_date_str());
        parcel.writeValue(end_date_str_inclusive());
        parcel.writeValue(channel());
        parcel.writeValue(prod_shop_id());
        parcel.writeValue(prod_dataset_override());
        parcel.writeValue(include_yoy_visits());
        parcel.writeValue(currency_filter());
    }

    private AutoValue_MissionControlStatsDashboardListingSpec(Parcel parcel) {
        Parcel parcel2 = parcel;
        this((EtsyId) parcel2.readValue(CL), (EtsyId) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL), (String) parcel2.readValue(CL));
    }
}
