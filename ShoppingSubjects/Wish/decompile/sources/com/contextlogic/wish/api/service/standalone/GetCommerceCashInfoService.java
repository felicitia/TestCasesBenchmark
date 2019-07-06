package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCommerceCashHelpInfo;
import com.contextlogic.wish.api.model.WishCommerceCashSpecs;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class GetCommerceCashInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCommerceCashSpecs wishCommerceCashSpecs, WishCommerceCashUserInfo wishCommerceCashUserInfo, WishCommerceCashHelpInfo wishCommerceCashHelpInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("commerce-cash-data/get"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetCommerceCashInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                JSONObject data = apiResponse.getData();
                final WishCommerceCashSpecs wishCommerceCashSpecs = new WishCommerceCashSpecs(data.getJSONObject("commerce_cash_spec"));
                final WishCommerceCashUserInfo wishCommerceCashUserInfo = new WishCommerceCashUserInfo(data.getJSONObject("user_commerce_cash"));
                final WishCommerceCashHelpInfo wishCommerceCashHelpInfo = new WishCommerceCashHelpInfo(data.getJSONObject("commerce_cash_info"));
                if (successCallback != null) {
                    GetCommerceCashInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCommerceCashSpecs, wishCommerceCashUserInfo, wishCommerceCashHelpInfo);
                        }
                    });
                }
            }
        });
    }
}
