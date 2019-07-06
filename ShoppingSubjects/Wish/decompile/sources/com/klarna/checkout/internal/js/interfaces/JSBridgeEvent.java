package com.klarna.checkout.internal.js.interfaces;

import android.webkit.JavascriptInterface;
import com.klarna.checkout.internal.a;
import com.klarna.checkout.internal.js.a.b;
import com.klarna.checkout.internal.js.a.c;
import com.klarna.checkout.internal.js.a.d;
import com.klarna.checkout.internal.js.a.e;
import com.klarna.checkout.internal.js.a.f;
import com.klarna.checkout.internal.js.a.g;
import com.klarna.checkout.internal.js.a.h;
import com.klarna.checkout.internal.js.a.i;
import com.klarna.checkout.internal.js.a.j;
import com.klarna.checkout.internal.js.a.k;
import com.klarna.checkout.internal.js.a.l;
import com.klarna.checkout.internal.js.a.m;
import com.klarna.checkout.internal.js.a.n;
import com.klarna.checkout.internal.js.a.o;

public final class JSBridgeEvent extends JSBridgeBase {
    public JSBridgeEvent(a aVar, boolean z) {
        super(aVar, z);
    }

    @JavascriptInterface
    public final void postMessage(String str) {
        postMessage(str, "");
    }

    @JavascriptInterface
    public final void postMessage(String str, String str2) {
        StringBuilder sb = new StringBuilder("RECEIVED isOverlay=");
        sb.append(this.isMessageFromOverlay);
        sb.append(" ");
        sb.append(str);
        for (o a : this.eventHandlers) {
            if (a.a(str)) {
                return;
            }
        }
    }

    public final void registerHandlers() {
        this.eventHandlers.add(new com.klarna.checkout.internal.js.a.a(this));
        this.eventHandlers.add(new b(this));
        this.eventHandlers.add(new c(this));
        this.eventHandlers.add(new d(this));
        this.eventHandlers.add(new f(this));
        this.eventHandlers.add(new e(this));
        this.eventHandlers.add(new i(this));
        this.eventHandlers.add(new g(this));
        this.eventHandlers.add(new h(this));
        this.eventHandlers.add(new n(this));
        this.eventHandlers.add(new l(this));
        this.eventHandlers.add(new j(this));
        this.eventHandlers.add(new k(this));
        this.eventHandlers.add(new m(this));
    }
}
