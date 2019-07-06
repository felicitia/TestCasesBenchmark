package com.etsy.etsyapi.api.shop.bespoke;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;

final class AutoValue_MissionControlStatsDashboardSpec extends C$AutoValue_MissionControlStatsDashboardSpec {
    private static final ClassLoader CL = AutoValue_MissionControlStatsDashboardSpec.class.getClassLoader();
    public static final Creator<AutoValue_MissionControlStatsDashboardSpec> CREATOR = new Creator<AutoValue_MissionControlStatsDashboardSpec>() {
        public AutoValue_MissionControlStatsDashboardSpec createFromParcel(Parcel parcel) {
            return new AutoValue_MissionControlStatsDashboardSpec(parcel);
        }

        public AutoValue_MissionControlStatsDashboardSpec[] newArray(int i) {
            return new AutoValue_MissionControlStatsDashboardSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_MissionControlStatsDashboardSpec(EtsyId etsyId, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, Boolean bool, Boolean bool2, String str6, Boolean bool3, String str7, Integer num4, Integer num5, String str8, String str9, String str10, Boolean bool4) {
        super(etsyId, num, str, str2, num2, str3, str4, str5, num3, bool, bool2, str6, bool3, str7, num4, num5, str8, str9, str10, bool4);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(shop_id());
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
        parcel.writeValue(page_id());
        parcel.writeValue(include_all_listings());
        parcel.writeValue(currency_filter());
        parcel.writeValue(inv_limit());
        parcel.writeValue(inv_offset());
        parcel.writeValue(inv_sort());
        parcel.writeValue(inv_sort_by());
        parcel.writeValue(listings_filter());
        parcel.writeValue(show_onboarding());
    }

    private AutoValue_MissionControlStatsDashboardSpec(Parcel parcel) {
        Parcel parcel2 = parcel;
        this((EtsyId) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL));
    }
}
