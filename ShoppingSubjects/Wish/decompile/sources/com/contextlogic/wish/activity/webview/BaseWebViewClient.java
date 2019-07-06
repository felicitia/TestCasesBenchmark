package com.contextlogic.wish.activity.webview;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.contextlogic.wish.R;
import com.contextlogic.wish.http.ServerConfig;
import java.util.List;

public class BaseWebViewClient extends WebViewClient {
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        ServerConfig instance = ServerConfig.getInstance();
        String apiUsername = instance.getApiUsername();
        String apiPassword = instance.getApiPassword();
        if (apiUsername == null || apiPassword == null) {
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        } else {
            httpAuthHandler.proceed(apiUsername, apiPassword);
        }
    }

    static String mapUrlToTitle(Context context, String str) {
        Uri parse = Uri.parse(str);
        String str2 = null;
        if (parse == null) {
            return null;
        }
        String string = context.getString(R.string.server_host);
        String string2 = context.getString(R.string.server_host_short);
        String host = parse.getHost();
        if (host == null || ((!host.equalsIgnoreCase(string) && !host.equalsIgnoreCase(string2)) || parse.getPath() == null)) {
            return null;
        }
        String fragment = parse.getFragment();
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(string);
        sb.append(fragment);
        List pathSegments = Uri.parse(sb.toString()).getPathSegments();
        if (!TextUtils.isEmpty(fragment)) {
            str2 = mapPathSegmentsToTitle(context, pathSegments);
        }
        if (str2 == null) {
            str2 = mapPathSegmentsToTitle(context, parse.getPathSegments());
        }
        return str2;
    }

    private static String mapPathSegmentsToTitle(Context context, List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        String str = (String) list.get(0);
        if (str.equalsIgnoreCase("static")) {
            return null;
        }
        if (str.equalsIgnoreCase("m")) {
            if (list.size() < 2) {
                return null;
            }
            String str2 = (String) list.get(1);
            if (str2.equalsIgnoreCase("transaction")) {
                if (list.size() < 3) {
                    return context.getString(R.string.order_history);
                }
                String str3 = (String) list.get(2);
                if (str3.equalsIgnoreCase("track")) {
                    return context.getString(R.string.track_package);
                }
                if (str3.length() == 24) {
                    return context.getString(R.string.order_details);
                }
            } else if (str2.equalsIgnoreCase("contact-us")) {
                return context.getString(R.string.contact_support);
            } else {
                if (str2.equalsIgnoreCase("change-transaction-shipping-address")) {
                    return context.getString(R.string.change_transaction_shipping_address);
                }
                if (str2.equalsIgnoreCase("terms")) {
                    return context.getString(R.string.terms_of_use);
                }
                if (str2.equalsIgnoreCase("privacy_policy")) {
                    return context.getString(R.string.privacy_policy);
                }
                if (str2.equalsIgnoreCase("ticket")) {
                    return context.getString(R.string.order_update);
                }
                return null;
            }
        }
        if (str.equalsIgnoreCase("customer-support-center")) {
            return context.getString(R.string.customer_support);
        }
        return null;
    }
}
