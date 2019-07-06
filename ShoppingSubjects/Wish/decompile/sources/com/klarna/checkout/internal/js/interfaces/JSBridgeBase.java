package com.klarna.checkout.internal.js.interfaces;

import android.webkit.JavascriptInterface;
import com.klarna.checkout.internal.a;
import com.klarna.checkout.internal.js.a.o;
import java.util.ArrayList;
import java.util.List;

public abstract class JSBridgeBase {
    public a controller;
    public final List<o> eventHandlers = new ArrayList();
    public boolean isMessageFromOverlay;
    public boolean unitTestMode;

    protected JSBridgeBase(a aVar, boolean z) {
        this.isMessageFromOverlay = z;
        this.controller = aVar;
        registerHandlers();
    }

    @JavascriptInterface
    public void postMessage(String str) {
        postMessage(str, "");
    }

    @JavascriptInterface
    public abstract void postMessage(String str, String str2);

    public abstract void registerHandlers();
}
