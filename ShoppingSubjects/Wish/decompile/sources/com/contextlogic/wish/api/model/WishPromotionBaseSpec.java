package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class WishPromotionBaseSpec extends BaseModel implements Parcelable {

    public enum PromoActionType implements Parcelable {
        UNKNOWN(0),
        FILTER_ID(1),
        DEEP_LINK(2);
        
        public static final Creator<PromoActionType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<PromoActionType>() {
                public PromoActionType createFromParcel(Parcel parcel) {
                    return PromoActionType.values()[parcel.readInt()];
                }

                public PromoActionType[] newArray(int i) {
                    return new PromoActionType[i];
                }
            };
        }

        private PromoActionType(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mValue);
        }
    }

    public BaseFeedHeaderView getFeedBannerSmallHeaderView(BaseFragment baseFragment) {
        return null;
    }

    public BaseFeedHeaderView getFeedBannerView(ProductFeedFragment productFeedFragment, WishAnalyticsEvent wishAnalyticsEvent, WishAnalyticsEvent wishAnalyticsEvent2) {
        return null;
    }

    public String getFilterId() {
        return null;
    }

    public View getProductOverviewBannerView(BaseFragment baseFragment, WishAnalyticsEvent wishAnalyticsEvent, WishAnalyticsEvent wishAnalyticsEvent2) {
        return null;
    }

    public View getSplashView(BaseDialogFragment baseDialogFragment) {
        return null;
    }

    WishPromotionBaseSpec() {
    }

    WishPromotionBaseSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }
}
