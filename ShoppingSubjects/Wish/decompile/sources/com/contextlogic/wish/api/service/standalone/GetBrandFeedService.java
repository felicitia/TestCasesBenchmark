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
import org.json.JSONException;
import org.json.JSONObject;

public class GetBrandFeedService extends BaseFeedApiService<WishProduct> {
    public void requestService(WishBrandFilter wishBrandFilter, int i, int i2, final SuccessCallback<Object> successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("brand");
        apiRequest.addParameter("query", (Object) wishBrandFilter.getQuery());
        apiRequest.addParameter("start", (Object) Integer.toString(i));
        apiRequest.addParameter("count", (Object) Integer.toString(i2));
        String tag = wishBrandFilter.getTag();
        if (tag != null) {
            apiRequest.addParameter("tag_ids[]", (T[]) tag.split(","));
        }
        String products = i == 0 ? wishBrandFilter.getProducts() : null;
        if (products != null) {
            apiRequest.addParameter("cids[]", (T[]) products.split(","));
        }
        if (wishBrandFilter.getPrice() != null) {
            apiRequest.addParameter("price", (Object) wishBrandFilter.getPrice());
        }
        if (wishBrandFilter.getCredit() != null) {
            apiRequest.addParameter("credit", (Object) wishBrandFilter.getCredit());
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                GetBrandFeedService.this.parseFailure(apiResponse, str, defaultFailureCallback);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                int optInt = apiResponse.getData().optInt("next_offset");
                boolean optBoolean = apiResponse.getData().optBoolean("feed_ended");
                ApiResponse apiResponse2 = apiResponse;
                GetBrandFeedService.this.parseSuccess(apiResponse2, JsonUtil.parseArray(apiResponse.getData(), "results", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                }), optInt, optBoolean, successCallback);
            }
        });
    }
}
