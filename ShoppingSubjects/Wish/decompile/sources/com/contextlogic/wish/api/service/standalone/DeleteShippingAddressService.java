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
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteShippingAddressService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishShippingInfo> arrayList, String str);
    }

    public void requestService(WishShippingInfo wishShippingInfo, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("shipping-address/delete");
        apiRequest.addParameter("id", (Object) wishShippingInfo.getId());
        startService(apiRequest, new ApiCallback() {
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
                if (successCallback != null) {
                    DeleteShippingAddressService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, optString);
                        }
                    });
                }
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    DeleteShippingAddressService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }
        });
    }
}
