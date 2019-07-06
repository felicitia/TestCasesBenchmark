package com.etsy.etsyapi.api.pub;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.etsyapi.models.EtsyId;
import java.io.File;

final class AutoValue_BugHuntReportCreatePostSpec extends C$AutoValue_BugHuntReportCreatePostSpec {
    private static final ClassLoader CL = AutoValue_BugHuntReportCreatePostSpec.class.getClassLoader();
    public static final Creator<AutoValue_BugHuntReportCreatePostSpec> CREATOR = new Creator<AutoValue_BugHuntReportCreatePostSpec>() {
        public AutoValue_BugHuntReportCreatePostSpec createFromParcel(Parcel parcel) {
            return new AutoValue_BugHuntReportCreatePostSpec(parcel);
        }

        public AutoValue_BugHuntReportCreatePostSpec[] newArray(int i) {
            return new AutoValue_BugHuntReportCreatePostSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_BugHuntReportCreatePostSpec(String str, String str2, String str3, String str4, String str5, EtsyId etsyId, File file, File file2, File file3) {
        super(str, str2, str3, str4, str5, etsyId, file, file2, file3);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(message());
        parcel.writeValue(platform());
        parcel.writeValue(platform_version());
        parcel.writeValue(etsy_version());
        parcel.writeValue(device_type());
        parcel.writeValue(user_id());
        parcel.writeValue(image01());
        parcel.writeValue(image02());
        parcel.writeValue(image03());
    }

    private AutoValue_BugHuntReportCreatePostSpec(Parcel parcel) {
        this((String) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL), (String) parcel.readValue(CL), (EtsyId) parcel.readValue(CL), (File) parcel.readValue(CL), (File) parcel.readValue(CL), (File) parcel.readValue(CL));
    }
}
