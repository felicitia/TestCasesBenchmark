package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUserShippingDetailsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(List<WishShippingInfo> list, String str);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("shipping-details/get"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final String optString = apiResponse.getData().getJSONObject("shipping_details").optString("default_details_id", null);
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData().getJSONObject("shipping_details"), "addresses", new DataParser<WishShippingInfo, JSONObject>() {
                    public WishShippingInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishShippingInfo(jSONObject);
                    }
                });
                GetUserShippingDetailsService.this.postRunnable(new Runnable() {
                    public void run() {
                        successCallback.onSuccess(parseArray, optString);
                    }
                });
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetUserShippingDetailsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }
        });
    }
}
