package com.facebook.login.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import com.facebook.login.LoginBehavior;
import com.facebook.login.d;

public class DeviceLoginButton extends LoginButton {
    private Uri deviceRedirectUri;

    private class a extends LoginClickListener {
        private a() {
            super();
        }

        /* access modifiers changed from: protected */
        public d getLoginManager() {
            com.facebook.login.a a2 = com.facebook.login.a.a();
            a2.a(DeviceLoginButton.this.getDefaultAudience());
            a2.a(LoginBehavior.DEVICE_AUTH);
            a2.a(DeviceLoginButton.this.getDeviceRedirectUri());
            return a2;
        }
    }

    public DeviceLoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DeviceLoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DeviceLoginButton(Context context) {
        super(context);
    }

    public void setDeviceRedirectUri(Uri uri) {
        this.deviceRedirectUri = uri;
    }

    public Uri getDeviceRedirectUri() {
        return this.deviceRedirectUri;
    }

    /* access modifiers changed from: protected */
    public LoginClickListener getNewLoginClickListener() {
        return new a();
    }
}
