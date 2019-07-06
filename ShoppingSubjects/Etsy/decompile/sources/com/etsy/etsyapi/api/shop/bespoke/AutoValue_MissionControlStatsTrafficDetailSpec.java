package com.etsy.etsyapi.api.shop.bespoke;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;

final class AutoValue_MissionControlStatsTrafficDetailSpec extends C$AutoValue_MissionControlStatsTrafficDetailSpec {
    private static final ClassLoader CL = AutoValue_MissionControlStatsTrafficDetailSpec.class.getClassLoader();
    public static final Creator<AutoValue_MissionControlStatsTrafficDetailSpec> CREATOR = new Creator<AutoValue_MissionControlStatsTrafficDetailSpec>() {
        public AutoValue_MissionControlStatsTrafficDetailSpec createFromParcel(Parcel parcel) {
            return new AutoValue_MissionControlStatsTrafficDetailSpec(parcel);
        }

        public AutoValue_MissionControlStatsTrafficDetailSpec[] newArray(int i) {
            return new AutoValue_MissionControlStatsTrafficDetailSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_MissionControlStatsTrafficDetailSpec(EtsyId etsyId, String str, String str2, String str3, String str4, Integer num, String str5, String str6, Integer num2, String str7, String str8, String str9, Boolean bool) {
        super(etsyId, str, str2, str3, str4, num, str5, str6, num2, str7, str8, str9, bool);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(shop_id());
        parcel.writeValue(referrer_group());
        parcel.writeValue(referrer());
        parcel.writeValue(domain());
        parcel.writeValue(internal_page());
        parcel.writeValue(start_date());
        parcel.writeValue(start_date_str());
        parcel.writeValue(date_range());
        parcel.writeValue(end_date());
        parcel.writeValue(end_date_str());
        parcel.writeValue(end_date_str_inclusive());
        parcel.writeValue(channel());
        parcel.writeValue(include_yoy_visits());
    }

    private AutoValue_MissionControlStatsTrafficDetailSpec(Parcel parcel) {
        Parcel parcel2 = parcel;
        this((EtsyId) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Integer) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (String) parcel2.readValue(CL), (Boolean) parcel2.readValue(CL));
    }
}
