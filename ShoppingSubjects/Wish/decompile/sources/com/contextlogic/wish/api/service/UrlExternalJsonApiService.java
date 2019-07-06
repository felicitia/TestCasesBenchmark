package com.contextlogic.wish.api.service;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.service.ExternalJsonApiService.DefaultFailureCallback;
import org.json.JSONException;
import org.json.JSONObject;

public class UrlExternalJsonApiService extends ExternalJsonApiService {

    public interface SuccessCallback {
        void onSuccess(JSONObject jSONObject);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(str);
        startService(apiRequest, new ExternalJsonApiCallback() {
            public void handleFailure(final String str) {
                if (defaultFailureCallback != null) {
                    UrlExternalJsonApiService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(final JSONObject jSONObject) throws JSONException {
                if (successCallback != null) {
                    UrlExternalJsonApiService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(jSONObject);
                        }
                    });
                }
            }
        });
    }
}
