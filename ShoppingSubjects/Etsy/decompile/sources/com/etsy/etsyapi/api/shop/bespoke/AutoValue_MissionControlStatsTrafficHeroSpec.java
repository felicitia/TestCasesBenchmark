package com.etsy.etsyapi.api.shop.bespoke;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;

final class AutoValue_MissionControlStatsTrafficHeroSpec extends C$AutoValue_MissionControlStatsTrafficHeroSpec {
    private static final ClassLoader CL = AutoValue_MissionControlStatsTrafficHeroSpec.class.getClassLoader();
    public static final Creator<AutoValue_MissionControlStatsTrafficHeroSpec> CREATOR = new Creator<AutoValue_MissionControlStatsTrafficHeroSpec>() {
        public AutoValue_MissionControlStatsTrafficHeroSpec createFromParcel(Parcel parcel) {
            return new AutoValue_MissionControlStatsTrafficHeroSpec(parcel);
        }

        public AutoValue_MissionControlStatsTrafficHeroSpec[] newArray(int i) {
            return new AutoValue_MissionControlStatsTrafficHeroSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_MissionControlStatsTrafficHeroSpec(EtsyId etsyId, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5) {
        super(etsyId, num, str, str2, num2, str3, str4, str5);
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
    }

    private AutoValue_MissionControlStatsTrafficHeroSpec(Parcel parcel) {
        this((EtsyId) parcel.readValue(CL), (Integer) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL), (Integer) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL));
    }
}
