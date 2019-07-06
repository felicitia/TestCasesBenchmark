package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetProfileUsersService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishUser> arrayList, boolean z);
    }

    public void requestService(String str, int i, int i2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("profile/users/scroll");
        apiRequest.addParameter("offset", (Object) Integer.toString(i));
        apiRequest.addParameter("count", (Object) Integer.toString(i2));
        apiRequest.addParameter("set_id", (Object) str);
        apiRequest.addParameter("include_follow_data", (Object) "true");
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetProfileUsersService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "items", new DataParser<WishUser, JSONObject>() {
                    public WishUser parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishUser(jSONObject);
                    }
                });
                final boolean z = apiResponse.getData().getBoolean("no_more");
                if (successCallback != null) {
                    GetProfileUsersService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, z);
                        }
                    });
                }
            }
        });
    }
}
