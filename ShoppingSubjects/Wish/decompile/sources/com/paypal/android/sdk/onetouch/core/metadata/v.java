package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class v extends aa {
    private static final String a = "v";
    private Context e;
    private String f;
    private Handler g;

    public v(Context context, String str, Handler handler) {
        this.e = context;
        this.f = str;
        this.g = handler;
    }

    public void run() {
        ai.a(a, "entering LoadConfigurationRequest.");
        if (this.g != null) {
            try {
                this.g.sendMessage(Message.obtain(this.g, 10, this.f));
                this.g.sendMessage(Message.obtain(this.g, 12, new c(this.e, this.f)));
            } catch (Exception e2) {
                ai.a(a, "LoadConfigurationRequest loading remote config failed.", (Throwable) e2);
                this.g.sendMessage(Message.obtain(this.g, 11, e2));
            } catch (Throwable th) {
                ab.a().b(this);
                throw th;
            }
            ab.a().b(this);
            ai.a(a, "leaving LoadConfigurationRequest.");
        }
    }
}
