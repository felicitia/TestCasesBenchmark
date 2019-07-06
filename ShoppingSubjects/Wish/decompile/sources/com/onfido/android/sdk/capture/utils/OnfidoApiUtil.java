package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.OnfidoAPIFactory;

public class OnfidoApiUtil {
    public static OnfidoAPI createOnfidoApiClient(Context context, OnfidoConfig onfidoConfig) {
        String baseUrl = onfidoConfig.getBaseUrl();
        String a = ApiTokenUtil.a(context, onfidoConfig);
        return baseUrl == null ? OnfidoAPIFactory.create(a) : OnfidoAPIFactory.create(a, baseUrl);
    }
}
