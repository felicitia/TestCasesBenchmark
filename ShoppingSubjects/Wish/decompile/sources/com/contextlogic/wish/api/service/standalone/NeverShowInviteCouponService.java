package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class NeverShowInviteCouponService extends SingleApiService {
    public void requestService() {
        startService(new ApiRequest("mobile/invite-coupon-never-show"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
            }
        });
    }
}
