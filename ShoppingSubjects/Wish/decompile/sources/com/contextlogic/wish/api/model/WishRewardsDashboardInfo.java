package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRewardsDashboardInfo extends BaseModel implements Parcelable {
    public static final Creator<WishRewardsDashboardInfo> CREATOR = new Creator<WishRewardsDashboardInfo>() {
        public WishRewardsDashboardInfo createFromParcel(Parcel parcel) {
            return new WishRewardsDashboardInfo(parcel);
        }

        public WishRewardsDashboardInfo[] newArray(int i) {
            return new WishRewardsDashboardInfo[i];
        }
    };
    private int mAvailablePoints;
    private String mAvailablePointsText;
    private String mBadgeText;
    private String mLifetimePointsText;
    private RewardSection mRewardSection;
    private String mSignupText;
    private String mTotalPointsText;

    public enum BadgeType {
        BLUE(1),
        BRONZE(2),
        SILVER(3),
        GOLD(4);
        
        private int mValue;

        private BadgeType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static BadgeType fromInteger(int i) {
            switch (i) {
                case 1:
                    return BLUE;
                case 2:
                    return BRONZE;
                case 3:
                    return SILVER;
                case 4:
                    return GOLD;
                default:
                    return null;
            }
        }

        public static int getBadgeResource(BadgeType badgeType) {
            switch (badgeType) {
                case BLUE:
                    return R.drawable.badge_blue;
                case BRONZE:
                    return R.drawable.badge_bronze;
                case SILVER:
                    return R.drawable.badge_silver;
                case GOLD:
                    return R.drawable.badge_gold;
                default:
                    return 0;
            }
        }
    }

    public enum RewardSection {
        DASHBOARD,
        REDEEM,
        INFORMATION;

        public static RewardSection fromInt(int i) {
            if (i == 0) {
                return DASHBOARD;
            }
            if (i == 1) {
                return REDEEM;
            }
            if (i == 2) {
                return INFORMATION;
            }
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishRewardsDashboardInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishRewardsDashboardInfo(Parcel parcel) {
        this.mBadgeText = parcel.readString();
        this.mAvailablePoints = parcel.readInt();
        this.mAvailablePointsText = parcel.readString();
        this.mTotalPointsText = parcel.readString();
        this.mLifetimePointsText = parcel.readString();
        this.mSignupText = parcel.readString();
        this.mRewardSection = RewardSection.fromInt(parcel.readInt());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mBadgeText);
        parcel.writeInt(this.mAvailablePoints);
        parcel.writeString(this.mAvailablePointsText);
        parcel.writeString(this.mTotalPointsText);
        parcel.writeString(this.mLifetimePointsText);
        parcel.writeString(this.mSignupText);
        parcel.writeInt(this.mRewardSection.ordinal());
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mAvailablePoints = jSONObject.optInt("available_points");
        this.mTotalPointsText = JsonUtil.optString(jSONObject, "total_points_text");
        this.mLifetimePointsText = JsonUtil.optString(jSONObject, "lifetime_points_text");
        this.mAvailablePointsText = JsonUtil.optString(jSONObject, "available_points_text");
        this.mSignupText = JsonUtil.optString(jSONObject, "signup_text");
        this.mRewardSection = RewardSection.fromInt(jSONObject.optInt("reward_section"));
    }

    public String getTotalPointsText() {
        return this.mTotalPointsText;
    }

    public String getSignupText() {
        return this.mSignupText;
    }

    public RewardSection getRewardSection() {
        return this.mRewardSection;
    }
}
