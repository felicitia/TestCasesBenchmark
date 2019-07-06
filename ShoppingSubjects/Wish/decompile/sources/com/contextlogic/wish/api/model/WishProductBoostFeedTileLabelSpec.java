package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishProductBoostFeedTileLabelSpec extends BaseModel implements Parcelable {
    public static final Creator<WishProductBoostFeedTileLabelSpec> CREATOR = new Creator<WishProductBoostFeedTileLabelSpec>() {
        public WishProductBoostFeedTileLabelSpec createFromParcel(Parcel parcel) {
            return new WishProductBoostFeedTileLabelSpec(parcel);
        }

        public WishProductBoostFeedTileLabelSpec[] newArray(int i) {
            return new WishProductBoostFeedTileLabelSpec[i];
        }
    };
    private String mLabelText;
    private LabelType mLabelType;

    public enum LabelType {
        BADGE,
        BANNER,
        NONE;

        public static LabelType fromInt(int i) {
            switch (i) {
                case 0:
                    return BADGE;
                case 1:
                    return BANNER;
                default:
                    return NONE;
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishProductBoostFeedTileLabelSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishProductBoostFeedTileLabelSpec(Parcel parcel) {
        this.mLabelText = parcel.readString();
        this.mLabelType = LabelType.fromInt(parcel.readInt());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mLabelText);
        parcel.writeInt(this.mLabelType.ordinal());
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mLabelText = JsonUtil.optString(jSONObject, "label_text");
        this.mLabelType = LabelType.fromInt(jSONObject.optInt("label_type", -1));
    }

    public String getLabelText() {
        return this.mLabelText;
    }

    public LabelType getLabelType() {
        return this.mLabelType;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishProductBoostFeedTileLabelSpec wishProductBoostFeedTileLabelSpec = (WishProductBoostFeedTileLabelSpec) obj;
        if (this.mLabelType != wishProductBoostFeedTileLabelSpec.mLabelType) {
            return false;
        }
        if (this.mLabelText != null) {
            z = this.mLabelText.equals(wishProductBoostFeedTileLabelSpec.mLabelText);
        } else if (wishProductBoostFeedTileLabelSpec.mLabelText != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.mLabelType != null ? this.mLabelType.hashCode() : 0) * 31;
        if (this.mLabelText != null) {
            i = this.mLabelText.hashCode();
        }
        return hashCode + i;
    }
}
