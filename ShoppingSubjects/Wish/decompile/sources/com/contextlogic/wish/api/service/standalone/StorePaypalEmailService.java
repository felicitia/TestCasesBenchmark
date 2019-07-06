package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerCashOutOption;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class StorePaypalEmailService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishPartnerCashOutOption> arrayList, String str, String str2);
    }

    public void requestService(String str, String str2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("partner/account/paypal/store");
        apiRequest.addParameter("paypal_email", (Object) str);
        apiRequest.addParameter("verification_code", (Object) str2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (defaultFailureCallback != null) {
                    defaultFailureCallback.onFailure(str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ArrayList parseArray = JsonUtil.hasValue(apiResponse.getData(), "cash_out_options") ? JsonUtil.parseArray(apiResponse.getData(), "cash_out_options", new DataParser<WishPartnerCashOutOption, JSONObject>() {
                    public WishPartnerCashOutOption parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishPartnerCashOutOption(jSONObject);
                    }
                }) : null;
                final String optString = JsonUtil.optString(apiResponse.getData(), "success_title");
                final String optString2 = JsonUtil.optString(apiResponse.getData(), "success_message");
                if (successCallback != null) {
                    StorePaypalEmailService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, optString, optString2);
                        }
                    });
                }
            }
        });
    }
}
