package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.service.ExternalJsonApiService;
import com.contextlogic.wish.api.service.ExternalJsonApiService.DefaultFailureCallback;
import com.contextlogic.wish.util.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class EbanxSetCVVService extends ExternalJsonApiService {

    public interface SuccessCallback {
        void onSuccess(String str, String str2);
    }

    public void requestService(String str, String str2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest();
        StringBuilder sb = new StringBuilder();
        sb.append(ConfigDataCenter.getInstance().getPaymentProcessorData().getEbanxApiUrl());
        sb.append("token/setCVV");
        apiRequest.setUrl(sb.toString());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("public_integration_key", ConfigDataCenter.getInstance().getPaymentProcessorData().getEbanxKey());
            jSONObject.put("token", str);
            jSONObject.put("card_cvv", str2);
        } catch (JSONException e) {
            if (defaultFailureCallback != null) {
                postRunnable(new Runnable() {
                    public void run() {
                        defaultFailureCallback.onFailure(e.getMessage());
                    }
                });
            }
        }
        apiRequest.addParameter("request_body", (Object) jSONObject.toString());
        startService(apiRequest, new ExternalJsonApiCallback() {
            public void handleFailure(final String str) {
                if (defaultFailureCallback != null) {
                    EbanxSetCVVService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(JSONObject jSONObject) {
                try {
                    if (jSONObject.getString("status").equals("SUCCESS")) {
                        final String string = jSONObject.getString("token");
                        final String string2 = jSONObject.getString("masked_card_number");
                        if (successCallback != null) {
                            EbanxSetCVVService.this.postRunnable(new Runnable() {
                                public void run() {
                                    successCallback.onSuccess(string, string2);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    final String format = String.format("Error Code: %s. Error Message: %s.", new Object[]{JsonUtil.optString(jSONObject, "status_code"), JsonUtil.optString(jSONObject, "status_message")});
                    if (defaultFailureCallback != null) {
                        EbanxSetCVVService.this.postRunnable(new Runnable() {
                            public void run() {
                                defaultFailureCallback.onFailure(format);
                            }
                        });
                    }
                } catch (JSONException e) {
                    if (defaultFailureCallback != null) {
                        EbanxSetCVVService.this.postRunnable(new Runnable() {
                            public void run() {
                                defaultFailureCallback.onFailure(e.getMessage());
                            }
                        });
                    }
                }
            }
        });
    }
}
