package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishDealDashInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class SpinDealDashService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishDealDashInfo wishDealDashInfo);
    }

    public void requestService(int i, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/spin-deal-dash");
        apiRequest.addParameter("spin_result", (Object) String.valueOf(i));
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    SpinDealDashService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null && JsonUtil.hasValue(apiResponse.getData(), "deal_dash_info")) {
                    final WishDealDashInfo wishDealDashInfo = new WishDealDashInfo(apiResponse.getData().getJSONObject("deal_dash_info"));
                    SpinDealDashService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishDealDashInfo);
                        }
                    });
                }
            }
        });
    }
}
