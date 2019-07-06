package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRatingSizeSummaryBar extends BaseModel implements Parcelable {
    public static final Creator<WishRatingSizeSummaryBar> CREATOR = new Creator<WishRatingSizeSummaryBar>() {
        public WishRatingSizeSummaryBar createFromParcel(Parcel parcel) {
            return new WishRatingSizeSummaryBar(parcel);
        }

        public WishRatingSizeSummaryBar[] newArray(int i) {
            return new WishRatingSizeSummaryBar[i];
        }
    };
    private String mName;
    private double mRatio;

    public int describeContents() {
        return 0;
    }

    public WishRatingSizeSummaryBar(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mRatio = jSONObject.getDouble("ratio");
        this.mName = jSONObject.getString("name");
    }

    protected WishRatingSizeSummaryBar(Parcel parcel) {
        this.mRatio = parcel.readDouble();
        this.mName = parcel.readString();
    }

    public String getName() {
        return this.mName;
    }

    public double getRatio() {
        return this.mRatio;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mRatio);
        parcel.writeString(this.mName);
    }
}
