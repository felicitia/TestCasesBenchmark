package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishBrandFilter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService.SuccessCallback;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetMerchantFeedService extends BaseFeedApiService<WishProduct> {

    public static class FeedContext {
        public ArrayList<String> lastProductIds;
    }

    public void requestService(WishBrandFilter wishBrandFilter, int i, int i2, FeedContext feedContext, final SuccessCallback<Object> successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("merchant");
        apiRequest.addParameter("query", (Object) wishBrandFilter.getQuery());
        apiRequest.addParameter("start", i);
        apiRequest.addParameter("count", i2);
        if (wishBrandFilter.getTag() != null) {
            apiRequest.addParameter("tag_ids[]", (T[]) wishBrandFilter.getTag().split(","));
        }
        String products = i == 0 ? wishBrandFilter.getProducts() : null;
        if (products != null) {
            apiRequest.addParameter("cids[]", (T[]) products.split(","));
        }
        if (feedContext.lastProductIds != null && feedContext.lastProductIds.size() > 0) {
            apiRequest.addParameter("last_cids[]", feedContext.lastProductIds);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                GetMerchantFeedService.this.parseFailure(apiResponse, str, defaultFailureCallback);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                int optInt = apiResponse.getData().optInt("next_offset");
                boolean optBoolean = apiResponse.getData().optBoolean("feed_ended");
                ApiResponse apiResponse2 = apiResponse;
                GetMerchantFeedService.this.parseSuccess(apiResponse2, JsonUtil.parseArray(apiResponse.getData(), "results", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                }), optInt, optBoolean, successCallback);
            }
        });
    }
}
