package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.activity.productdetails.FilterType;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishRatingSummary;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetProductRatingsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishRatingSummary wishRatingSummary, ArrayList<WishRating> arrayList, int i, int i2, boolean z);
    }

    public void requestService(String str, int i, int i2, FilterType filterType, String str2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("product-ratings/get");
        apiRequest.addParameter("product_id", (Object) str);
        apiRequest.addParameter("start", i);
        apiRequest.addParameter("count", i2);
        if (str2 != null) {
            apiRequest.addParameter("product_rating_id", (Object) str2);
        }
        if (!(filterType == null || filterType.mFilterType == null)) {
            apiRequest.addParameter("filter_type", (Object) filterType.mFilterType);
            if (filterType.mRatingValue != 0) {
                apiRequest.addParameter("filter_rating", filterType.mRatingValue);
            }
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetProductRatingsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "results", new DataParser<WishRating, JSONObject>() {
                    public WishRating parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishRating(jSONObject);
                    }
                });
                final WishRatingSummary wishRatingSummary = new WishRatingSummary(apiResponse.getData().getJSONObject("product_info"));
                final int i = apiResponse.getData().getInt("num_results");
                final int i2 = apiResponse.getData().getInt("next_offset");
                final boolean z = apiResponse.getData().getBoolean("no_more_ratings");
                if (successCallback != null) {
                    GetProductRatingsService getProductRatingsService = GetProductRatingsService.this;
                    AnonymousClass3 r3 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishRatingSummary, parseArray, i, i2, z);
                        }
                    };
                    getProductRatingsService.postRunnable(r3);
                }
            }
        });
    }
}
