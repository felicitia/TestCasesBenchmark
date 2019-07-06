package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.LoggedOutCountdownCoupon;
import com.contextlogic.wish.api.model.WishReloginInfo;
import com.contextlogic.wish.api.model.WishSignupVideoPopupSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class GetLoggedOutExperimentsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(Map<String, String> map, boolean z, WishReloginInfo wishReloginInfo, LoggedOutCountdownCoupon loggedOutCountdownCoupon, WishSignupVideoPopupSpec wishSignupVideoPopupSpec);
    }

    /* access modifiers changed from: protected */
    public boolean shouldSampleConnection() {
        return true;
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("mobile/get-logged-out-experiments"), new ApiCallback() {
            public String getCallIdentifier() {
                return "mobile/get-logged-out-experiments";
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (defaultFailureCallback != null) {
                    GetLoggedOutExperimentsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(null);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final boolean optBoolean = apiResponse.getData().optBoolean("already_has_app", false);
                JSONObject optJSONObject = apiResponse.getData().optJSONObject("experiments");
                final HashMap hashMap = new HashMap();
                if (optJSONObject != null) {
                    Iterator keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        hashMap.put(str, optJSONObject.getString(str));
                    }
                }
                final WishReloginInfo wishReloginInfo = JsonUtil.hasValue(apiResponse.getData(), "user_info") ? new WishReloginInfo(apiResponse.getData().optJSONObject("user_info")) : null;
                final LoggedOutCountdownCoupon loggedOutCountdownCoupon = JsonUtil.hasValue(apiResponse.getData(), "countdown_coupon") ? new LoggedOutCountdownCoupon(apiResponse.getData().optJSONObject("countdown_coupon")) : null;
                final WishSignupVideoPopupSpec wishSignupVideoPopupSpec = JsonUtil.hasValue(apiResponse.getData(), "signup_video") ? new WishSignupVideoPopupSpec(apiResponse.getData().optJSONObject("signup_video")) : null;
                if (successCallback != null) {
                    GetLoggedOutExperimentsService getLoggedOutExperimentsService = GetLoggedOutExperimentsService.this;
                    AnonymousClass2 r3 = new Runnable() {
                        public void run() {
                            successCallback.onSuccess(hashMap, optBoolean, wishReloginInfo, loggedOutCountdownCoupon, wishSignupVideoPopupSpec);
                        }
                    };
                    getLoggedOutExperimentsService.postRunnable(r3);
                }
            }
        });
    }
}
