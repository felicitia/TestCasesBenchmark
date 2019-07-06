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

public class WishRatingSizeSummary extends BaseModel implements Parcelable {
    public static final Creator<WishRatingSizeSummary> CREATOR = new Creator<WishRatingSizeSummary>() {
        public WishRatingSizeSummary createFromParcel(Parcel parcel) {
            return new WishRatingSizeSummary(parcel);
        }

        public WishRatingSizeSummary[] newArray(int i) {
            return new WishRatingSizeSummary[i];
        }
    };
    private int mNumRatings;
    private ArrayList<WishRatingSizeSummaryBar> mSizeBars;
    private String mSubtitle;

    public int describeContents() {
        return 0;
    }

    public WishRatingSizeSummary(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mSubtitle = jSONObject.getString("subtitle");
        this.mNumRatings = jSONObject.getInt("num_ratings");
        this.mSizeBars = JsonUtil.parseArray(jSONObject, "size_bars", new DataParser<WishRatingSizeSummaryBar, JSONObject>() {
            public WishRatingSizeSummaryBar parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishRatingSizeSummaryBar(jSONObject);
            }
        });
    }

    protected WishRatingSizeSummary(Parcel parcel) {
        this.mNumRatings = parcel.readInt();
        this.mSizeBars = parcel.createTypedArrayList(WishRatingSizeSummaryBar.CREATOR);
        this.mSubtitle = parcel.readString();
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public long getNumRatings() {
        return (long) this.mNumRatings;
    }

    public ArrayList<WishRatingSizeSummaryBar> getSizeBars() {
        return this.mSizeBars;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mNumRatings);
        parcel.writeTypedList(this.mSizeBars);
        parcel.writeString(this.mSubtitle);
    }
}
