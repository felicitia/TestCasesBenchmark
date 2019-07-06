package com.paypal.android.sdk.onetouch.core.config;

import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ConfigFileParser {
    ConfigFileParser() {
    }

    /* access modifiers changed from: 0000 */
    public OtcConfiguration getParsedConfig(JSONObject jSONObject) throws JSONException {
        OtcConfiguration otcConfiguration = new OtcConfiguration();
        otcConfiguration.fileTimestamp(jSONObject.getString("file_timestamp"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("1.0");
        JSONArray jSONArray = jSONObject2.getJSONArray("oauth2_recipes_in_decreasing_priority_order");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            if (jSONObject3 != null) {
                otcConfiguration.withOauth2Recipe(getOAuth2Recipe(jSONObject3));
            }
        }
        JSONArray jSONArray2 = jSONObject2.getJSONArray("checkout_recipes_in_decreasing_priority_order");
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            JSONObject jSONObject4 = jSONArray2.getJSONObject(i2);
            if (jSONObject4 != null) {
                otcConfiguration.withCheckoutRecipe(getCheckoutRecipe(jSONObject4));
            }
        }
        JSONArray jSONArray3 = jSONObject2.getJSONArray("billing_agreement_recipes_in_decreasing_priority_order");
        for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
            JSONObject jSONObject5 = jSONArray3.getJSONObject(i3);
            if (jSONObject5 != null) {
                otcConfiguration.withBillingAgreementRecipe(getBillingAgreementRecipe(jSONObject5));
            }
        }
        return otcConfiguration;
    }

    private CheckoutRecipe getCheckoutRecipe(JSONObject jSONObject) throws JSONException {
        CheckoutRecipe checkoutRecipe = new CheckoutRecipe();
        populateCommonData(checkoutRecipe, jSONObject);
        return checkoutRecipe;
    }

    private BillingAgreementRecipe getBillingAgreementRecipe(JSONObject jSONObject) throws JSONException {
        BillingAgreementRecipe billingAgreementRecipe = new BillingAgreementRecipe();
        populateCommonData(billingAgreementRecipe, jSONObject);
        return billingAgreementRecipe;
    }

    private void populateCommonData(Recipe<?> recipe, JSONObject jSONObject) throws JSONException {
        recipe.target(RequestTarget.valueOf(jSONObject.getString("target"))).protocol(jSONObject.getString("protocol"));
        if (jSONObject.has("intent_action")) {
            recipe.targetIntentAction(jSONObject.getString("intent_action"));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("packages");
        for (int i = 0; i < jSONArray.length(); i++) {
            recipe.targetPackage(jSONArray.getString(i));
        }
        if (jSONObject.has("supported_locales")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("supported_locales");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                recipe.supportedLocale(jSONArray2.getString(i2));
            }
        }
    }

    private OAuth2Recipe getOAuth2Recipe(JSONObject jSONObject) throws JSONException {
        OAuth2Recipe oAuth2Recipe = new OAuth2Recipe();
        populateCommonData(oAuth2Recipe, jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("scope");
        for (int i = 0; i < jSONArray.length(); i++) {
            String string = jSONArray.getString(i);
            if ("*".equals(string)) {
                oAuth2Recipe.validForAllScopes();
            } else {
                oAuth2Recipe.validForScope(string);
            }
        }
        if (jSONObject.has("endpoints")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("endpoints");
            if (jSONObject2.has("live")) {
                addEnvironment(oAuth2Recipe, "live", jSONObject2.getJSONObject("live"));
            }
            if (jSONObject2.has("develop")) {
                addEnvironment(oAuth2Recipe, "develop", jSONObject2.getJSONObject("develop"));
            }
            if (jSONObject2.has("mock")) {
                addEnvironment(oAuth2Recipe, "mock", jSONObject2.getJSONObject("mock"));
            }
        }
        return oAuth2Recipe;
    }

    private void addEnvironment(OAuth2Recipe oAuth2Recipe, String str, JSONObject jSONObject) throws JSONException {
        oAuth2Recipe.withEndpoint(str, new ConfigEndpoint(str, jSONObject.getString("url"), jSONObject.getString("certificate")));
    }
}
