package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.google.gson.JsonSyntaxException;
import java.text.ParseException;
import org.json.JSONException;

public class GetBuyerGuaranteeService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(BuyerGuaranteeInfo buyerGuaranteeInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("mobile/get-buyer-guarantee"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetBuyerGuaranteeService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException, JsonSyntaxException {
                final BuyerGuaranteeInfo buyerGuaranteeInfo = (BuyerGuaranteeInfo) GetBuyerGuaranteeService.gson.fromJson(apiResponse.getData().toString(), BuyerGuaranteeInfo.class);
                if (successCallback != null) {
                    GetBuyerGuaranteeService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(buyerGuaranteeInfo);
                        }
                    });
                }
            }
        });
    }
}
