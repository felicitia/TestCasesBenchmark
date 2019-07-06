package com.klarna.checkout;

import org.json.JSONObject;

public interface SignalListener {
    void onSignal(String str, JSONObject jSONObject);
}
