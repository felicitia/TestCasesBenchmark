package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class UpdateEbanxBillingInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishUserBillingInfo wishUserBillingInfo, WishCart wishCart);
    }

    public void requestService(String str, String str2, String str3, String str4, String str5, WishShippingInfo wishShippingInfo, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(getApiRequest(str, str2, str3, str4, str5, wishShippingInfo), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UpdateEbanxBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishCart wishCart = null;
                final WishUserBillingInfo wishUserBillingInfo = JsonUtil.hasValue(apiResponse.getData(), "user_billing_details") ? new WishUserBillingInfo(apiResponse.getData().getJSONObject("user_billing_details")) : null;
                if (JsonUtil.hasValue(apiResponse.getData(), "cart_info")) {
                    wishCart = new WishCart(apiResponse.getData().getJSONObject("cart_info"));
                }
                if (successCallback != null) {
                    UpdateEbanxBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUserBillingInfo, wishCart);
                        }
                    });
                }
            }
        });
    }

    private ApiRequest getApiRequest(String str, String str2, String str3, String str4, String str5, WishShippingInfo wishShippingInfo) {
        ApiRequest apiRequest = new ApiRequest("billing-info/ebanx/add-or-update");
        apiRequest.addParameter("ebanx_token", (Object) str);
        apiRequest.addParameter("payment_type_code", (Object) str2);
        apiRequest.addParameter("masked_card_number", (Object) str3);
        apiRequest.addParameter("email", (Object) str5);
        if (str4 != null) {
            apiRequest.addParameter("cpf", (Object) str4);
        }
        if (wishShippingInfo.getName() != null) {
            apiRequest.addParameter("full_name", (Object) wishShippingInfo.getName());
        }
        if (wishShippingInfo.getStreetAddressLineOne() != null) {
            apiRequest.addParameter("street_address1", (Object) wishShippingInfo.getStreetAddressLineOne());
        }
        if (wishShippingInfo.getStreetAddressLineTwo() != null) {
            apiRequest.addParameter("street_address2", (Object) wishShippingInfo.getStreetAddressLineTwo());
        }
        if (wishShippingInfo.getCity() != null) {
            apiRequest.addParameter("city", (Object) wishShippingInfo.getCity());
        }
        if (wishShippingInfo.getState() != null) {
            apiRequest.addParameter("state", (Object) wishShippingInfo.getState());
        }
        if (wishShippingInfo.getZipCode() != null) {
            apiRequest.addParameter("zipcode", (Object) wishShippingInfo.getZipCode());
        }
        if (wishShippingInfo.getCountryCode() != null) {
            apiRequest.addParameter("country", (Object) wishShippingInfo.getCountryCode());
        }
        if (wishShippingInfo.getPhoneNumber() != null) {
            apiRequest.addParameter("phone_number", (Object) wishShippingInfo.getPhoneNumber());
        }
        return apiRequest;
    }
}
