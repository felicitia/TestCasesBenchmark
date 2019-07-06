package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishReturnPolicyInfo extends BaseModel implements Parcelable {
    public static final Creator<WishReturnPolicyInfo> CREATOR = new Creator<WishReturnPolicyInfo>() {
        public WishReturnPolicyInfo createFromParcel(Parcel parcel) {
            return new WishReturnPolicyInfo(parcel);
        }

        public WishReturnPolicyInfo[] newArray(int i) {
            return new WishReturnPolicyInfo[i];
        }
    };
    private ArrayList<WishRewardsReturnPolicyInformation> mInformationRows;
    private String mTitle1;
    private String mTitle2;

    public int describeContents() {
        return 0;
    }

    protected WishReturnPolicyInfo(Parcel parcel) {
        this.mTitle1 = parcel.readString();
        this.mTitle2 = parcel.readString();
        this.mInformationRows = parcel.createTypedArrayList(WishRewardsReturnPolicyInformation.CREATOR);
    }

    public WishReturnPolicyInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle1);
        parcel.writeString(this.mTitle2);
        parcel.writeTypedList(this.mInformationRows);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle1 = jSONObject.optString("title_1");
        this.mTitle2 = jSONObject.optString("title_2");
        this.mInformationRows = JsonUtil.parseArray(jSONObject, "information_rows", new DataParser<WishRewardsReturnPolicyInformation, JSONObject>() {
            public WishRewardsReturnPolicyInformation parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishRewardsReturnPolicyInformation(jSONObject);
            }
        });
    }

    public String getTitle1() {
        return this.mTitle1;
    }

    public String getTitle2() {
        return this.mTitle2;
    }

    public ArrayList<WishRewardsReturnPolicyInformation> getInformationRows() {
        return this.mInformationRows;
    }
}
