package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class RedeemRewardProductService extends SingleApiService {
    private final String ENDPOINT = "rewards/redeem-product";

    public interface SuccessCallback {
        void onSuccess(String str, String str2, String str3, String str4, WishShippingInfo wishShippingInfo);
    }

    public void requestService(String str, String str2, String str3, final SuccessCallback successCallback, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("rewards/redeem-product");
        apiRequest.addParameter("product_id", (Object) str);
        apiRequest.addParameter("variation_id", (Object) str2);
        apiRequest.addParameter("shipping_option_id", (Object) str3);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return "rewards/redeem-product";
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (defaultCodeFailureCallback != null) {
                    RedeemRewardProductService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultCodeFailureCallback.onFailure(str, apiResponse == null ? -1 : apiResponse.getCode());
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    WishShippingInfo wishShippingInfo = null;
                    final String optString = JsonUtil.optString(apiResponse.getData(), "success_dialog_image_url", null);
                    final String optString2 = JsonUtil.optString(apiResponse.getData(), "success_dialog_title", null);
                    final String optString3 = JsonUtil.optString(apiResponse.getData(), "success_dialog_subtitle", null);
                    final String optString4 = JsonUtil.optString(apiResponse.getData(), "email", null);
                    if (JsonUtil.hasValue(apiResponse.getData(), "shipping_info")) {
                        wishShippingInfo = new WishShippingInfo(apiResponse.getData().getJSONObject("shipping_info"));
                    }
                    final WishShippingInfo wishShippingInfo2 = wishShippingInfo;
                    RedeemRewardProductService redeemRewardProductService = RedeemRewardProductService.this;
                    AnonymousClass2 r3 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(optString, optString2, optString3, optString4, wishShippingInfo2);
                        }
                    };
                    redeemRewardProductService.postRunnable(r3);
                }
            }
        });
    }
}
