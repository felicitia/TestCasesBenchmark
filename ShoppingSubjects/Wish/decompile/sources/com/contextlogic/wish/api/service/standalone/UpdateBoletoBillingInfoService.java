package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class UpdateBoletoBillingInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishUserBillingInfo wishUserBillingInfo);
    }

    public void requestService(String str, String str2, String str3, WishShippingInfo wishShippingInfo, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("billing-info/boleto/add-or-update");
        apiRequest.addParameter("name", (Object) str);
        apiRequest.addParameter("identity_number", (Object) str2);
        apiRequest.addParameter("email", (Object) str3);
        addBillingAddressInformation(apiRequest, wishShippingInfo);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UpdateBoletoBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishUserBillingInfo wishUserBillingInfo = JsonUtil.hasValue(apiResponse.getData(), "user_billing_details") ? new WishUserBillingInfo(apiResponse.getData().getJSONObject("user_billing_details")) : null;
                if (successCallback != null) {
                    UpdateBoletoBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUserBillingInfo);
                        }
                    });
                }
            }
        });
    }

    private void addBillingAddressInformation(ApiRequest apiRequest, WishShippingInfo wishShippingInfo) {
        if (wishShippingInfo != null) {
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
        }
    }
}
