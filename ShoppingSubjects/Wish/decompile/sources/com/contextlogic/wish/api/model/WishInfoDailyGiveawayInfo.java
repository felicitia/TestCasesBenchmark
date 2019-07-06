package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WishInfoDailyGiveawayInfo extends BaseModel implements Parcelable {
    public static final Creator<WishInfoDailyGiveawayInfo> CREATOR = new Creator<WishInfoDailyGiveawayInfo>() {
        public WishInfoDailyGiveawayInfo createFromParcel(Parcel parcel) {
            return new WishInfoDailyGiveawayInfo(parcel);
        }

        public WishInfoDailyGiveawayInfo[] newArray(int i) {
            return new WishInfoDailyGiveawayInfo[i];
        }
    };
    private String mMessage;
    private ArrayList<WishInfoCategory> mQuestions;
    private String mSubtitle;
    private String mTitle;

    public class WishInfoCategory {
        boolean mBulleted;
        ArrayList<String> mDescriptions = new ArrayList<>();
        String mInfoTitle;

        public String getTitle() {
            return this.mInfoTitle;
        }

        public ArrayList<String> getDescriptions() {
            return this.mDescriptions;
        }

        public boolean getBulleted() {
            return this.mBulleted;
        }

        public WishInfoCategory(JSONObject jSONObject) throws JSONException, ParseException {
            this.mInfoTitle = jSONObject.optString("q");
            if (JsonUtil.hasValue(jSONObject, "a")) {
                JSONArray jSONArray = jSONObject.getJSONArray("a");
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.mDescriptions.add(jSONArray.getString(i));
                }
            }
            this.mBulleted = jSONObject.optBoolean("bulleted");
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishInfoDailyGiveawayInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public ArrayList<WishInfoCategory> getQuestions() {
        return this.mQuestions;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.optString("message");
        this.mSubtitle = jSONObject.optString("subtitle");
        if (JsonUtil.hasValue(jSONObject, "questions")) {
            this.mQuestions = JsonUtil.parseArray(jSONObject, "questions", new DataParser<WishInfoCategory, JSONObject>() {
                public WishInfoCategory parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishInfoCategory(jSONObject);
                }
            });
        }
    }

    protected WishInfoDailyGiveawayInfo(Parcel parcel) {
        this.mQuestions = parcel.readArrayList(WishInfoCategory.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mSubtitle = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mQuestions);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mSubtitle);
    }
}
