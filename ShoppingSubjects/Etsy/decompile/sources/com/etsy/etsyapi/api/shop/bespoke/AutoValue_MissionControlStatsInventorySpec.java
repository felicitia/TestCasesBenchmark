package com.etsy.etsyapi.api.shop.bespoke;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;

final class AutoValue_MissionControlStatsInventorySpec extends C$AutoValue_MissionControlStatsInventorySpec {
    private static final ClassLoader CL = AutoValue_MissionControlStatsInventorySpec.class.getClassLoader();
    public static final Creator<AutoValue_MissionControlStatsInventorySpec> CREATOR = new Creator<AutoValue_MissionControlStatsInventorySpec>() {
        public AutoValue_MissionControlStatsInventorySpec createFromParcel(Parcel parcel) {
            return new AutoValue_MissionControlStatsInventorySpec(parcel);
        }

        public AutoValue_MissionControlStatsInventorySpec[] newArray(int i) {
            return new AutoValue_MissionControlStatsInventorySpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_MissionControlStatsInventorySpec(EtsyId etsyId, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, Boolean bool, Boolean bool2, Integer num4, Integer num5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        super(etsyId, num, str, str2, num2, str3, str4, str5, num3, bool, bool2, num4, num5, str6, str7, str8, str9, str10, str11, str12);
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
        parcel.writeValue(inv_limit());
        parcel.writeValue(inv_offset());
        parcel.writeValue(inv_sort());
        parcel.writeValue(inv_sort_by());
        parcel.writeValue(referrer_group());
        parcel.writeValue(referrer());
        parcel.writeValue(domain());
        parcel.writeValue(internal_page());
        parcel.writeValue(listings_filter());
    }

    private AutoValue_MissionControlStatsInventorySpec(Parcel parcel) {
        Parcel parcel2 = parcel;
        this((EtsyId) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL));
    }
}
