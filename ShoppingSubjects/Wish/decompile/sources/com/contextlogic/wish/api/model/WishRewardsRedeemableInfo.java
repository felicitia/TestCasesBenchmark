package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRewardsRedeemableInfo extends BaseModel implements Parcelable {
    public static final Creator<WishRewardsRedeemableInfo> CREATOR = new Creator<WishRewardsRedeemableInfo>() {
        public WishRewardsRedeemableInfo createFromParcel(Parcel parcel) {
            return new WishRewardsRedeemableInfo(parcel);
        }

        public WishRewardsRedeemableInfo[] newArray(int i) {
            return new WishRewardsRedeemableInfo[i];
        }
    };
    private int mAvailablePoints;
    private String mDescription;
    private String mFooterAvailablePoints;
    private String mFooterText;
    private String mLinkText;
    private List<WishProduct> mRedeemableProducts;
    private String mRedeemedDescription;
    private String mRedeemedTitle;
    private String mTitle;
    private List<WishRedeemableRewardItem> mWishRedeemableRewardItems;

    public int describeContents() {
        return 0;
    }

    public WishRewardsRedeemableInfo(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
        this.mFooterText = parcel.readString();
        this.mFooterAvailablePoints = parcel.readString();
        this.mWishRedeemableRewardItems = parcel.createTypedArrayList(WishRedeemableRewardItem.CREATOR);
        this.mLinkText = parcel.readString();
        if (parcel.readByte() == 1) {
            this.mRedeemableProducts = new ArrayList();
            parcel.readList(this.mRedeemableProducts, WishProduct.class.getClassLoader());
        } else {
            this.mRedeemableProducts = null;
        }
        this.mAvailablePoints = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mFooterText);
        parcel.writeString(this.mFooterAvailablePoints);
        parcel.writeString(this.mRedeemedTitle);
        parcel.writeString(this.mRedeemedDescription);
        parcel.writeTypedList(this.mWishRedeemableRewardItems);
        parcel.writeString(this.mLinkText);
        if (this.mRedeemableProducts == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.mRedeemableProducts);
        }
        parcel.writeInt(this.mAvailablePoints);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
        this.mDescription = JsonUtil.optString(jSONObject, "description");
        this.mFooterText = JsonUtil.optString(jSONObject, "footer_text");
        this.mFooterAvailablePoints = JsonUtil.optString(jSONObject, "footer_available_points");
        this.mRedeemedTitle = JsonUtil.optString(jSONObject, "redeemed_title");
        this.mRedeemedDescription = JsonUtil.optString(jSONObject, "redeemed_description");
        this.mWishRedeemableRewardItems = JsonUtil.parseArray(jSONObject, "redeemable_rewards", new DataParser<WishRedeemableRewardItem, JSONObject>() {
            public WishRedeemableRewardItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishRedeemableRewardItem(jSONObject);
            }
        });
        this.mLinkText = JsonUtil.optString(jSONObject, "redeemed_link");
        this.mRedeemableProducts = JsonUtil.parseArray(jSONObject, "redeemable_reward_products", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishProduct(jSONObject);
            }
        });
        this.mAvailablePoints = jSONObject.optInt("points_available", 0);
    }

    public WishRewardsRedeemableInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getFooterText() {
        return this.mFooterText;
    }

    public String getFooterAvailablePoints() {
        return this.mFooterAvailablePoints;
    }

    public String getRedeemTitle() {
        return this.mRedeemedTitle;
    }

    public String getmRedeemedDescription() {
        return this.mRedeemedDescription;
    }

    public List<WishRedeemableRewardItem> getRedeemableRewards() {
        return this.mWishRedeemableRewardItems == null ? Collections.emptyList() : this.mWishRedeemableRewardItems;
    }

    public List<WishProduct> getRedeemableProducts() {
        return this.mRedeemableProducts == null ? Collections.emptyList() : this.mRedeemableProducts;
    }

    public int getAvailablePoints() {
        return this.mAvailablePoints;
    }
}
