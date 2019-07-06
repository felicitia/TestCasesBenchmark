package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishDealDashInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONException;

public class StartDealDashService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishDealDashInfo wishDealDashInfo);
    }

    public void requestService(int i, int i2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/start-deal-dash");
        apiRequest.addParameter("user_start", (Object) String.valueOf(true));
        apiRequest.addParameter("product_count", (Object) String.valueOf(i));
        apiRequest.addParameter("utc_offset_seconds", (Object) String.valueOf(TimeZone.getDefault().getOffset(new Date().getTime()) / 1000));
        apiRequest.addParameter("play_time", (Object) String.valueOf(i2));
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    StartDealDashService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null && JsonUtil.hasValue(apiResponse.getData(), "deal_dash_info")) {
                    final WishDealDashInfo wishDealDashInfo = new WishDealDashInfo(apiResponse.getData().getJSONObject("deal_dash_info"));
                    StartDealDashService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishDealDashInfo);
                        }
                    });
                }
            }
        });
    }
}
