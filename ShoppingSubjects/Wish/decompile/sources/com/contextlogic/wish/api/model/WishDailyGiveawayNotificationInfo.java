package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishDailyGiveawayNotificationInfo extends BaseModel implements Parcelable {
    public static final Creator<WishDailyGiveawayNotificationInfo> CREATOR = new Creator<WishDailyGiveawayNotificationInfo>() {
        public WishDailyGiveawayNotificationInfo createFromParcel(Parcel parcel) {
            return new WishDailyGiveawayNotificationInfo(parcel);
        }

        public WishDailyGiveawayNotificationInfo[] newArray(int i) {
            return new WishDailyGiveawayNotificationInfo[i];
        }
    };
    private String mDescription;
    private ArrayList<WishImage> mGiveaways;
    private String mMainActionText;
    private String mSeenKey;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishDailyGiveawayNotificationInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mDescription = jSONObject.optString("description");
        this.mMainActionText = jSONObject.optString("main_action");
        this.mSeenKey = jSONObject.optString("seen_key");
        this.mGiveaways = JsonUtil.parseArray(jSONObject, "giveaways", new DataParser<WishImage, JSONObject>() {
            public WishImage parseData(JSONObject jSONObject) throws JSONException, ParseException {
                WishImage wishImage = new WishImage(jSONObject.getString("display_picture"), jSONObject.getString("small_picture"), jSONObject.getString("display_picture"), jSONObject.getString("contest_page_picture"), null);
                return wishImage;
            }
        });
    }

    protected WishDailyGiveawayNotificationInfo(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
        this.mMainActionText = parcel.readString();
        this.mSeenKey = parcel.readString();
        this.mGiveaways = parcel.createTypedArrayList(WishImage.CREATOR);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getMainActionText() {
        return this.mMainActionText;
    }

    public String getSeenKey() {
        return this.mSeenKey;
    }

    public ArrayList<WishImage> getGiveaways() {
        return this.mGiveaways;
    }

    public JSONObject getJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(StrongAuth.AUTH_TITLE, this.mTitle);
            jSONObject.put("description", this.mDescription);
            jSONObject.put("main_action", this.mMainActionText);
            jSONObject.put("seen_key", this.mSeenKey);
            jSONObject.put("giveaways", this.mGiveaways);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mMainActionText);
        parcel.writeString(this.mSeenKey);
        parcel.writeTypedList(this.mGiveaways);
    }
}
