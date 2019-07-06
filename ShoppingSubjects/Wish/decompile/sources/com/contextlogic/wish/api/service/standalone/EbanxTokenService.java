package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.R;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ExternalJsonApiService;
import com.contextlogic.wish.api.service.ExternalJsonApiService.DefaultFailureCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CardType;
import com.contextlogic.wish.util.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class EbanxTokenService extends ExternalJsonApiService {

    public interface SuccessCallback {
        void onSuccess(String str, String str2, String str3);
    }

    public void requestService(String str, String str2, int i, int i2, String str3, CardType cardType, WishShippingInfo wishShippingInfo, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        String str4;
        switch (cardType) {
            case Visa:
                str4 = "visa";
                break;
            case MasterCard:
                str4 = "mastercard";
                break;
            case Discover:
                str4 = "discover";
                break;
            case Amex:
                str4 = "amex";
                break;
            case DinersClub:
                str4 = "diners";
                break;
            case HiperCard:
                str4 = "hipercard";
                break;
            case Carnet:
                str4 = "carnet";
                break;
            case Aura:
                str4 = "aura";
                break;
            case Elo:
                str4 = "elo";
                break;
            default:
                str4 = null;
                break;
        }
        if (str4 == null) {
            if (defaultFailureCallback != null) {
                postRunnable(new Runnable() {
                    public void run() {
                        defaultFailureCallback.onFailure(WishApplication.getInstance().getString(R.string.invalid_credit_card_type));
                    }
                });
            }
            return;
        }
        ApiRequest apiRequest = new ApiRequest();
        StringBuilder sb = new StringBuilder();
        sb.append(ConfigDataCenter.getInstance().getPaymentProcessorData().getEbanxApiUrl());
        sb.append("token");
        apiRequest.setUrl(sb.toString());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("public_integration_key", ConfigDataCenter.getInstance().getPaymentProcessorData().getEbanxKey());
            jSONObject.put("payment_type_code", str4);
            jSONObject.put("country", ProfileDataCenter.getInstance().getCountryCode());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("card_id", str);
            jSONObject2.put("card_number", CreditCardUtil.sanitizeCreditCardNumber(str2));
            jSONObject2.put("card_due_date", CreditCardUtil.getFormattedExpiryDate(i, i2));
            jSONObject2.put("card_cvv", str3);
            if (wishShippingInfo.getName() != null) {
                jSONObject2.put("card_name", wishShippingInfo.getName());
            }
            jSONObject.put("creditcard", jSONObject2);
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
                    EbanxTokenService.this.postRunnable(new Runnable() {
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
                        final String string2 = jSONObject.getString("payment_type_code");
                        final String string3 = jSONObject.getString("masked_card_number");
                        if (successCallback != null) {
                            EbanxTokenService.this.postRunnable(new Runnable() {
                                public void run() {
                                    successCallback.onSuccess(string, string2, string3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    final String format = String.format("Error Code: %s. Error Message: %s.", new Object[]{JsonUtil.optString(jSONObject, "status_code"), JsonUtil.optString(jSONObject, "status_message")});
                    if (defaultFailureCallback != null) {
                        EbanxTokenService.this.postRunnable(new Runnable() {
                            public void run() {
                                defaultFailureCallback.onFailure(format);
                            }
                        });
                    }
                } catch (JSONException e) {
                    if (defaultFailureCallback != null) {
                        EbanxTokenService.this.postRunnable(new Runnable() {
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
