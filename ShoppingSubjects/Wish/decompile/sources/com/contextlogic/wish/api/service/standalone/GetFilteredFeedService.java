package com.contextlogic.wish.api.service.standalone;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishDealDashInfo;
import com.contextlogic.wish.api.model.WishFilter;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.api.model.WishHomePageInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishPromotionBaseSpec;
import com.contextlogic.wish.api.model.WishPromotionSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GetFilteredFeedService extends SingleApiService {

    public static class FeedContext {
        public boolean featuredProductCollectionClicked;
        public ArrayList<WishFilter> filters;
        public boolean requestBrandedFilter;
        public boolean requestCategories;
        public String requestId;
        public RequestSourceType requestSourceType;
    }

    public static class FeedExtraInfo implements Parcelable {
        public static final Creator<FeedExtraInfo> CREATOR = new Creator<FeedExtraInfo>() {
            public FeedExtraInfo createFromParcel(Parcel parcel) {
                return new FeedExtraInfo(parcel);
            }

            public FeedExtraInfo[] newArray(int i) {
                return new FeedExtraInfo[i];
            }
        };
        public WishFilter brandedFilter;
        public boolean dailyGiveawayIsOngoing;
        public WishDealDashInfo dealDashInfo;
        public WishFreeGiftTabInfo freeGiftTabInfo;
        public WishHomePageInfo homePageInfo;
        public String initialCategoryId;
        public ArrayList<WishFilter> mainCategories;
        public WishPromotionBaseSpec promotionSpec;

        public int describeContents() {
            return 0;
        }

        public FeedExtraInfo() {
        }

        protected FeedExtraInfo(Parcel parcel) {
            this.mainCategories = parcel.createTypedArrayList(WishFilter.CREATOR);
            this.dealDashInfo = (WishDealDashInfo) parcel.readParcelable(WishDealDashInfo.class.getClassLoader());
            this.brandedFilter = (WishFilter) parcel.readParcelable(WishFilter.class.getClassLoader());
            this.initialCategoryId = parcel.readString();
            this.dailyGiveawayIsOngoing = parcel.readByte() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(this.mainCategories);
            parcel.writeParcelable(this.dealDashInfo, 0);
            parcel.writeParcelable(this.brandedFilter, 0);
            parcel.writeString(this.initialCategoryId);
            parcel.writeByte(this.dailyGiveawayIsOngoing ? (byte) 1 : 0);
        }
    }

    public enum RequestSourceType implements Parcelable {
        FIRST_LOAD(1),
        TIMED_REFRESH(2),
        USER_FORCE_REFRESH(3);
        
        public static final Creator<RequestSourceType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<RequestSourceType>() {
                public RequestSourceType createFromParcel(Parcel parcel) {
                    return RequestSourceType.fromInteger(parcel.readInt());
                }

                public RequestSourceType[] newArray(int i) {
                    return new RequestSourceType[i];
                }
            };
        }

        private RequestSourceType(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mValue);
        }

        public int getValue() {
            return this.mValue;
        }

        public static RequestSourceType fromInteger(int i) {
            switch (i) {
                case 1:
                    return FIRST_LOAD;
                case 2:
                    return TIMED_REFRESH;
                case 3:
                    return USER_FORCE_REFRESH;
                default:
                    return null;
            }
        }
    }

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishProduct> arrayList, int i, boolean z, FeedExtraInfo feedExtraInfo);
    }

    public void requestService(int i, int i2, FeedContext feedContext, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ArrayList arrayList = new ArrayList();
        if (feedContext.filters != null && feedContext.filters.size() > 0) {
            Iterator it = feedContext.filters.iterator();
            while (it.hasNext()) {
                arrayList.add(((WishFilter) it.next()).getFilterId());
            }
        }
        ApiRequest apiRequest = new ApiRequest("feed/get-filtered-feed");
        if (feedContext.requestCategories) {
            apiRequest.addParameter("request_categories", (Object) "true");
        }
        if (feedContext.requestBrandedFilter) {
            apiRequest.addParameter("request_branded_filter", (Object) "true");
        }
        if (arrayList.size() > 0) {
            apiRequest.addParameter("filters[]", arrayList);
        }
        if (feedContext.requestSourceType != null) {
            apiRequest.addParameter("request_source_type", feedContext.requestSourceType.getValue());
        }
        apiRequest.addParameter("request_id", (Object) feedContext.requestId);
        apiRequest.addParameter("offset", (Object) Integer.toString(i));
        apiRequest.addParameter("count", (Object) Integer.toString(i2));
        if (feedContext.featuredProductCollectionClicked) {
            apiRequest.addParameter("featured_product_collection_click", (Object) "true");
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetFilteredFeedService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                WishHomePageInfo wishHomePageInfo;
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "products", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                });
                ArrayList<WishFilter> parseArray2 = JsonUtil.parseArray(apiResponse.getData(), "categories", new DataParser<WishFilter, JSONObject>() {
                    public WishFilter parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishFilter(jSONObject);
                    }
                });
                String str = null;
                WishDealDashInfo wishDealDashInfo = JsonUtil.hasValue(apiResponse.getData(), "deal_dash_info") ? new WishDealDashInfo(apiResponse.getData().getJSONObject("deal_dash_info")) : null;
                WishFreeGiftTabInfo wishFreeGiftTabInfo = JsonUtil.hasValue(apiResponse.getData(), "free_gifts_tab_info") ? new WishFreeGiftTabInfo(apiResponse.getData().getJSONObject("free_gifts_tab_info")) : null;
                if (JsonUtil.hasValue(apiResponse.getData(), "home_page_info")) {
                    WishHomePageInfo.getInstance().updateHomePageInfo(apiResponse.getData().getJSONObject("home_page_info"));
                    wishHomePageInfo = WishHomePageInfo.getInstance();
                } else {
                    wishHomePageInfo = null;
                }
                WishPromotionBaseSpec wishPromotionDeal = JsonUtil.hasValue(apiResponse.getData(), "promo_deal_spec") ? new WishPromotionSpec(apiResponse.getData().getJSONObject("promo_deal_spec")).getWishPromotionDeal() : null;
                WishFilter wishFilter = JsonUtil.hasValue(apiResponse.getData(), "branded_filter") ? new WishFilter(apiResponse.getData().getJSONObject("branded_filter")) : null;
                if (JsonUtil.hasValue(apiResponse.getData(), "initial_category_id")) {
                    str = apiResponse.getData().getString("initial_category_id");
                }
                boolean optBoolean = apiResponse.getData().optBoolean("daily_giveaway_is_ongoing", false);
                int i = apiResponse.getData().getInt("next_offset");
                boolean z = apiResponse.getData().getBoolean("no_more_items");
                FeedExtraInfo feedExtraInfo = new FeedExtraInfo();
                feedExtraInfo.mainCategories = parseArray2;
                feedExtraInfo.dealDashInfo = wishDealDashInfo;
                feedExtraInfo.brandedFilter = wishFilter;
                feedExtraInfo.initialCategoryId = str;
                feedExtraInfo.freeGiftTabInfo = wishFreeGiftTabInfo;
                feedExtraInfo.homePageInfo = wishHomePageInfo;
                feedExtraInfo.promotionSpec = wishPromotionDeal;
                feedExtraInfo.dailyGiveawayIsOngoing = optBoolean;
                if (successCallback != null) {
                    GetFilteredFeedService getFilteredFeedService = GetFilteredFeedService.this;
                    final int i2 = i;
                    final boolean z2 = z;
                    final FeedExtraInfo feedExtraInfo2 = feedExtraInfo;
                    AnonymousClass4 r3 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, i2, z2, feedExtraInfo2);
                        }
                    };
                    getFilteredFeedService.postRunnable(r3);
                }
            }
        });
    }
}
