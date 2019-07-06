package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService.SuccessCallback;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class GetRelatedProductFeedService extends BaseFeedApiService<WishProduct> {

    public interface ExpressProductSuccessCallback {
        void onSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec, int i, boolean z);
    }

    public void requestService(String str, int i, long j, SuccessCallback<Object> successCallback, DefaultFailureCallback defaultFailureCallback) {
        requestService(str, i, j, "similar", successCallback, defaultFailureCallback);
    }

    public void requestService(String str, int i, long j, String str2, final SuccessCallback<Object> successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("related-feed/get");
        apiRequest.addParameter("contest_id", (Object) str);
        apiRequest.addParameter("offset", i);
        apiRequest.addParameter("count", j);
        apiRequest.addParameter("feed_mode", (Object) str2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                GetRelatedProductFeedService.this.parseFailure(apiResponse, str, defaultFailureCallback);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                int optInt = apiResponse.getData().optInt("next_offset");
                boolean optBoolean = apiResponse.getData().optBoolean("feed_ended");
                ApiResponse apiResponse2 = apiResponse;
                GetRelatedProductFeedService.this.parseSuccess(apiResponse2, JsonUtil.parseArray(apiResponse.getData(), "items", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                }), optInt, optBoolean, successCallback);
            }
        });
    }

    public void requestService(String str, int i, long j, String str2, final ExpressProductSuccessCallback expressProductSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("related-feed/get");
        apiRequest.addParameter("contest_id", (Object) str);
        apiRequest.addParameter("offset", i);
        apiRequest.addParameter("count", j);
        apiRequest.addParameter("feed_mode", (Object) str2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                GetRelatedProductFeedService.this.parseFailure(apiResponse, str, defaultFailureCallback);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec = new ProductDetailsRelatedRowSpec(apiResponse.getData());
                if (expressProductSuccessCallback != null) {
                    final int optInt = apiResponse.getData().optInt("next_offset");
                    final boolean optBoolean = apiResponse.getData().optBoolean("feed_ended");
                    GetRelatedProductFeedService.this.postRunnable(new Runnable() {
                        public void run() {
                            expressProductSuccessCallback.onSuccess(productDetailsRelatedRowSpec, optInt, optBoolean);
                        }
                    });
                }
            }
        });
    }
}
