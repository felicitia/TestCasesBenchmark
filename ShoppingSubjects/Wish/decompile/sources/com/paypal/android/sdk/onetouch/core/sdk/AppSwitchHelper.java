package com.paypal.android.sdk.onetouch.core.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.braintreepayments.api.internal.SignatureVerification;
import com.paypal.android.sdk.data.collector.InstallationIdentifier;
import com.paypal.android.sdk.onetouch.core.AuthorizationRequest;
import com.paypal.android.sdk.onetouch.core.CheckoutRequest;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.Result;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.base.DeviceInspector;
import com.paypal.android.sdk.onetouch.core.config.ConfigManager;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.ResponseType;
import com.paypal.android.sdk.onetouch.core.exception.ResponseParsingException;
import com.paypal.android.sdk.onetouch.core.exception.WalletSwitchException;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AppSwitchHelper {
    public static boolean isSignatureValid(Context context, String str) {
        return SignatureVerification.isSignatureValid(context, str, "O=Paypal", "O=Paypal", 34172764);
    }

    public static Intent createBaseIntent(String str, String str2) {
        return new Intent(str).setPackage(str2);
    }

    public static Intent getAppSwitchIntent(ContextInspector contextInspector, ConfigManager configManager, Request request, Recipe recipe) {
        Intent putExtra = createBaseIntent(recipe.getTargetIntentAction(), "com.paypal.android.p2pmobile").putExtra("version", recipe.getProtocol().getVersion()).putExtra("app_guid", InstallationIdentifier.getInstallationGUID(contextInspector.getContext())).putExtra("client_metadata_id", request.getClientMetadataId()).putExtra("client_id", request.getClientId()).putExtra("app_name", DeviceInspector.getApplicationInfoName(contextInspector.getContext())).putExtra("environment", request.getEnvironment()).putExtra("environment_url", EnvironmentManager.getEnvironmentUrl(request.getEnvironment()));
        if (request instanceof AuthorizationRequest) {
            AuthorizationRequest authorizationRequest = (AuthorizationRequest) request;
            putExtra.putExtra("scope", authorizationRequest.getScopeString()).putExtra("response_type", "code").putExtra("privacy_url", authorizationRequest.getPrivacyUrl()).putExtra("agreement_url", authorizationRequest.getUserAgreementUrl());
        } else {
            putExtra.putExtra("response_type", "web").putExtra("webURL", ((CheckoutRequest) request).getBrowserSwitchUrl(contextInspector.getContext(), configManager.getConfig()));
        }
        return putExtra;
    }

    public static Result parseAppSwitchResponse(ContextInspector contextInspector, Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        if (request.validateV1V2Response(contextInspector, extras)) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Return, null);
            return processResponseIntent(extras);
        } else if (extras.containsKey("error")) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Error, null);
            return new Result((Throwable) new WalletSwitchException(extras.getString("error")));
        } else {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Error, null);
            return new Result((Throwable) new ResponseParsingException("invalid wallet response"));
        }
    }

    private static Result processResponseIntent(Bundle bundle) {
        ResponseType responseType;
        String string = bundle.getString("error");
        if (!TextUtils.isEmpty(string)) {
            return new Result((Throwable) new WalletSwitchException(string));
        }
        String string2 = bundle.getString("environment");
        if ("code".equals(bundle.getString("response_type").toLowerCase(Locale.US))) {
            responseType = ResponseType.authorization_code;
        } else {
            responseType = ResponseType.web;
        }
        try {
            if (ResponseType.web == responseType) {
                return new Result(string2, responseType, new JSONObject().put("webURL", bundle.getString("webURL")), null);
            }
            String string3 = bundle.getString("authorization_code");
            return new Result(string2, responseType, new JSONObject().put("code", string3), bundle.getString("email"));
        } catch (JSONException e) {
            return new Result((Throwable) new ResponseParsingException((Throwable) e));
        }
    }
}
