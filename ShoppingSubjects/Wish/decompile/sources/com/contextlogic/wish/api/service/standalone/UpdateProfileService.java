package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class UpdateProfileService extends SingleApiService {
    public void requestService(String str, String str2, int i, int i2, int i3, String str3, boolean z, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("profile/update");
        if (!(i == -1 || i2 == -1 || i3 == -1)) {
            apiRequest.addParameter("dob_day", i);
            apiRequest.addParameter("dob_month", i2);
            apiRequest.addParameter("dob_year", i3);
        }
        apiRequest.addParameter("gender", (Object) str3);
        apiRequest.addParameter("first_name", (Object) str);
        apiRequest.addParameter("last_name", (Object) str2);
        if (z) {
            apiRequest.addParameter("birthday_inferred", (Object) "true");
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UpdateProfileService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws ParseException, JSONException {
                WishUser wishUser = new WishUser(apiResponse.getData());
                if (AuthenticationDataCenter.getInstance().getUserId() != null && AuthenticationDataCenter.getInstance().getUserId().equals(wishUser.getUserId())) {
                    ProfileDataCenter.getInstance().initializeData(wishUser);
                }
                if (defaultSuccessCallback != null) {
                    UpdateProfileService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }
}
