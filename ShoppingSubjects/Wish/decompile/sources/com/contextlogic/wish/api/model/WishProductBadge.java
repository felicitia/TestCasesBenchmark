package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class WishProductBadge extends BaseModel implements Parcelable {
    public static final Set<Integer> ACCEPTABLE_BADGES = new HashSet<Integer>() {
        {
            add(Integer.valueOf(1));
            add(Integer.valueOf(2));
        }
    };
    public static final Creator<WishProductBadge> CREATOR = new Creator<WishProductBadge>() {
        public WishProductBadge createFromParcel(Parcel parcel) {
            return new WishProductBadge(parcel);
        }

        public WishProductBadge[] newArray(int i) {
            return new WishProductBadge[i];
        }
    };
    private String mExtraMessage;
    private String mMessage;
    private String mShortDescription;
    private String mTitle;
    private int mType;

    public int describeContents() {
        return 0;
    }

    public WishProductBadge(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishProductBadge(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mExtraMessage = parcel.readString();
        this.mShortDescription = parcel.readString();
    }

    public int getBadgeIcon() {
        switch (this.mType) {
            case 1:
                return R.drawable.verified_icon;
            case 2:
                return R.drawable.fast_shipping_icon;
            default:
                return -1;
        }
    }

    public int getCondensedBadgeIcon() {
        switch (this.mType) {
            case 1:
                return R.drawable.verified_14;
            case 2:
                return R.drawable.express_16x10;
            default:
                return -1;
        }
    }

    public int getBadgeColor() {
        switch (this.mType) {
            case 1:
                return R.color.verified_wish_badge;
            case 2:
                return R.color.fast_shipping_badge;
            default:
                return -1;
        }
    }

    public int getCondensedBadgeColor() {
        switch (this.mType) {
            case 1:
                return R.color.verified_wish_badge;
            case 2:
                return R.color.new_orange;
            default:
                return -1;
        }
    }

    public int getPriority() {
        switch (this.mType) {
            case 1:
                return 3;
            case 2:
                return 2;
            default:
                return -1;
        }
    }

    public int getBackgroundBadgeColor() {
        switch (this.mType) {
            case 1:
                return R.color.verified_wish_badge_background;
            case 2:
                return R.color.fast_shipping_badge_background;
            default:
                return -1;
        }
    }

    public int getType() {
        return this.mType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mExtraMessage);
        parcel.writeString(this.mShortDescription);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mType = jSONObject.getInt("badge_type");
        this.mTitle = jSONObject.getString("badge_title");
        this.mMessage = jSONObject.getString("badge_message");
        this.mExtraMessage = jSONObject.optString("badge_extra_message");
        this.mShortDescription = jSONObject.optString("badge_short_description");
        if (!ACCEPTABLE_BADGES.contains(Integer.valueOf(this.mType))) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mType);
            sb.append(" is not a valid badge type.");
            throw new JSONException(sb.toString());
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishProductBadge wishProductBadge = (WishProductBadge) obj;
        if (this.mType != wishProductBadge.mType) {
            return false;
        }
        if (this.mTitle == null ? wishProductBadge.mTitle != null : !this.mTitle.equals(wishProductBadge.mTitle)) {
            return false;
        }
        if (this.mMessage == null ? wishProductBadge.mMessage != null : !this.mMessage.equals(wishProductBadge.mMessage)) {
            return false;
        }
        if (this.mShortDescription == null ? wishProductBadge.mShortDescription != null : !this.mShortDescription.equals(wishProductBadge.mShortDescription)) {
            return false;
        }
        if (this.mExtraMessage != null) {
            z = this.mExtraMessage.equals(wishProductBadge.mExtraMessage);
        } else if (wishProductBadge.mExtraMessage != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.mType * 31) + (this.mTitle != null ? this.mTitle.hashCode() : 0)) * 31) + (this.mMessage != null ? this.mMessage.hashCode() : 0)) * 31;
        if (this.mExtraMessage != null) {
            i = this.mExtraMessage.hashCode();
        }
        return hashCode + i;
    }

    public String getCondensedBadgeShortenedDescription() {
        return this.mShortDescription;
    }
}
