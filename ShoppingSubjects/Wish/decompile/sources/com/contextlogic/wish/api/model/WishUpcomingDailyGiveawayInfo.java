package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayGridAdapter.WishProductPair;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishUpcomingDailyGiveawayInfo extends BaseModel implements Parcelable {
    public static final Creator<WishUpcomingDailyGiveawayInfo> CREATOR = new Creator<WishUpcomingDailyGiveawayInfo>() {
        public WishUpcomingDailyGiveawayInfo createFromParcel(Parcel parcel) {
            return new WishUpcomingDailyGiveawayInfo(parcel);
        }

        public WishUpcomingDailyGiveawayInfo[] newArray(int i) {
            return new WishUpcomingDailyGiveawayInfo[i];
        }
    };
    private String mMessage;
    private ArrayList<WishProductPair> mProducts;
    private String mSubtitle;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishUpcomingDailyGiveawayInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public ArrayList<WishProductPair> getProducts() {
        return this.mProducts;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "giveaways")) {
            ArrayList parseArray = JsonUtil.parseArray(jSONObject, "giveaways", new DataParser<WishProduct, JSONObject>() {
                public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishProduct(jSONObject);
                }
            });
            this.mProducts = new ArrayList<>();
            for (int i = 0; i < parseArray.size(); i += 2) {
                WishProductPair wishProductPair = new WishProductPair();
                wishProductPair.leftProduct = (WishProduct) parseArray.get(i);
                int i2 = i + 1;
                if (i2 < parseArray.size()) {
                    wishProductPair.rightProduct = (WishProduct) parseArray.get(i2);
                } else {
                    wishProductPair.rightProduct = null;
                }
                this.mProducts.add(wishProductPair);
            }
        }
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.optString("message");
        this.mSubtitle = jSONObject.optString("subtitle");
    }

    protected WishUpcomingDailyGiveawayInfo(Parcel parcel) {
        this.mProducts = parcel.readArrayList(WishProduct.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mSubtitle = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mProducts);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mSubtitle);
    }
}
