package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishSignupFlowCategory;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetSignupFlowCategoriesService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishSignupFlowCategory> arrayList);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/get-onboarding-categories");
        apiRequest.addParameter("gender", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetSignupFlowCategoriesService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "categories", new DataParser<WishSignupFlowCategory, JSONObject>() {
                    public WishSignupFlowCategory parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishSignupFlowCategory(jSONObject);
                    }
                });
                if (successCallback != null) {
                    GetSignupFlowCategoriesService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray);
                        }
                    });
                }
            }
        });
    }
}
