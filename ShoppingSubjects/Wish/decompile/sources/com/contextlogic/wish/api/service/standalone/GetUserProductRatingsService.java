package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUserProductRatingsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishRating> arrayList, int i, boolean z);
    }

    public void requestService(String str, int i, int i2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user-product-ratings/get");
        apiRequest.addParameter("user_id", (Object) str);
        apiRequest.addParameter("start", i);
        apiRequest.addParameter("count", i2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetUserProductRatingsService.this.postRunnable(new Runnable() {
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
                final int i = apiResponse.getData().getInt("next_offset");
                final boolean z = apiResponse.getData().getBoolean("no_more_ratings");
                if (successCallback != null) {
                    GetUserProductRatingsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, i, z);
                        }
                    });
                }
            }
        });
    }
}
