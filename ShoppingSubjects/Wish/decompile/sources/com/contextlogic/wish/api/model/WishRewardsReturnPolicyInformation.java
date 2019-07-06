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

public class WishRewardsReturnPolicyInformation extends BaseModel implements Parcelable {
    public static final Creator<WishRewardsReturnPolicyInformation> CREATOR = new Creator<WishRewardsReturnPolicyInformation>() {
        public WishRewardsReturnPolicyInformation createFromParcel(Parcel parcel) {
            return new WishRewardsReturnPolicyInformation(parcel);
        }

        public WishRewardsReturnPolicyInformation[] newArray(int i) {
            return new WishRewardsReturnPolicyInformation[i];
        }
    };
    ArrayList<String> mParagraphs;
    String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishRewardsReturnPolicyInformation(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishRewardsReturnPolicyInformation(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mParagraphs = parcel.createStringArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeStringList(this.mParagraphs);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public ArrayList<String> getParagraphs() {
        return this.mParagraphs;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mParagraphs = JsonUtil.parseArray(jSONObject, "paragraphs", new DataParser<String, String>() {
            public String parseData(String str) throws JSONException {
                return str;
            }
        });
    }
}
