package com.facebook.login.widget;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.AccessToken;
import com.facebook.FacebookButtonBase;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.c;
import com.facebook.common.a.C0120a;
import com.facebook.f;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.LoginAuthorizationType;
import com.facebook.internal.j;
import com.facebook.internal.k;
import com.facebook.internal.z;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.d;
import com.facebook.login.f.C0122f;
import com.facebook.login.f.b;
import com.facebook.login.f.e;
import com.facebook.login.f.g;
import com.facebook.login.widget.ToolTipPopup.Style;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LoginButton extends FacebookButtonBase {
    private static final String TAG = "com.facebook.login.widget.LoginButton";
    private c accessTokenTracker;
    /* access modifiers changed from: private */
    public boolean confirmLogout;
    /* access modifiers changed from: private */
    public String loginLogoutEventName = "fb_login_view_usage";
    private d loginManager;
    private String loginText;
    private String logoutText;
    /* access modifiers changed from: private */
    public a properties = new a();
    private boolean toolTipChecked;
    private long toolTipDisplayTime = 6000;
    private ToolTipMode toolTipMode;
    private ToolTipPopup toolTipPopup;
    private Style toolTipStyle = Style.BLUE;

    protected class LoginClickListener implements OnClickListener {
        protected LoginClickListener() {
        }

        public void onClick(View view) {
            LoginButton.this.callExternalOnClickListener(view);
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (AccessToken.isCurrentAccessTokenActive()) {
                performLogout(LoginButton.this.getContext());
            } else {
                performLogin();
            }
            AppEventsLogger a = AppEventsLogger.a(LoginButton.this.getContext());
            Bundle bundle = new Bundle();
            bundle.putInt("logging_in", currentAccessToken != null ? 0 : 1);
            bundle.putInt("access_token_expired", AccessToken.isCurrentAccessTokenActive() ? 1 : 0);
            a.a(LoginButton.this.loginLogoutEventName, (Double) null, bundle);
        }

        /* access modifiers changed from: protected */
        public void performLogin() {
            d loginManager = getLoginManager();
            if (LoginAuthorizationType.PUBLISH.equals(LoginButton.this.properties.c)) {
                if (LoginButton.this.getFragment() != null) {
                    loginManager.b(LoginButton.this.getFragment(), (Collection<String>) LoginButton.this.properties.b);
                } else if (LoginButton.this.getNativeFragment() != null) {
                    loginManager.b(LoginButton.this.getNativeFragment(), (Collection<String>) LoginButton.this.properties.b);
                } else {
                    loginManager.b(LoginButton.this.getActivity(), (Collection<String>) LoginButton.this.properties.b);
                }
            } else if (LoginButton.this.getFragment() != null) {
                loginManager.a(LoginButton.this.getFragment(), (Collection<String>) LoginButton.this.properties.b);
            } else if (LoginButton.this.getNativeFragment() != null) {
                loginManager.a(LoginButton.this.getNativeFragment(), (Collection<String>) LoginButton.this.properties.b);
            } else {
                loginManager.a(LoginButton.this.getActivity(), (Collection<String>) LoginButton.this.properties.b);
            }
        }

        /* access modifiers changed from: protected */
        public void performLogout(Context context) {
            String str;
            final d loginManager = getLoginManager();
            if (LoginButton.this.confirmLogout) {
                String string = LoginButton.this.getResources().getString(e.com_facebook_loginview_log_out_action);
                String string2 = LoginButton.this.getResources().getString(e.com_facebook_loginview_cancel_action);
                Profile currentProfile = Profile.getCurrentProfile();
                if (currentProfile == null || currentProfile.getName() == null) {
                    str = LoginButton.this.getResources().getString(e.com_facebook_loginview_logged_in_using_facebook);
                } else {
                    str = String.format(LoginButton.this.getResources().getString(e.com_facebook_loginview_logged_in_as), new Object[]{currentProfile.getName()});
                }
                Builder builder = new Builder(context);
                builder.setMessage(str).setCancelable(true).setPositiveButton(string, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loginManager.d();
                    }
                }).setNegativeButton(string2, null);
                builder.create().show();
                return;
            }
            loginManager.d();
        }

        /* access modifiers changed from: protected */
        public d getLoginManager() {
            d c = d.c();
            c.a(LoginButton.this.getDefaultAudience());
            c.a(LoginButton.this.getLoginBehavior());
            c.a(LoginButton.this.getAuthType());
            return c;
        }
    }

    public enum ToolTipMode {
        AUTOMATIC("automatic", 0),
        DISPLAY_ALWAYS("display_always", 1),
        NEVER_DISPLAY("never_display", 2);
        
        public static ToolTipMode DEFAULT;
        private int intValue;
        private String stringValue;

        static {
            DEFAULT = AUTOMATIC;
        }

        public static ToolTipMode fromInt(int i) {
            ToolTipMode[] values;
            for (ToolTipMode toolTipMode : values()) {
                if (toolTipMode.getValue() == i) {
                    return toolTipMode;
                }
            }
            return null;
        }

        private ToolTipMode(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        public String toString() {
            return this.stringValue;
        }

        public int getValue() {
            return this.intValue;
        }
    }

    static class a {
        private DefaultAudience a = DefaultAudience.FRIENDS;
        /* access modifiers changed from: private */
        public List<String> b = Collections.emptyList();
        /* access modifiers changed from: private */
        public LoginAuthorizationType c = null;
        private LoginBehavior d = LoginBehavior.NATIVE_WITH_FALLBACK;
        private String e = "rerequest";

        a() {
        }

        public void a(DefaultAudience defaultAudience) {
            this.a = defaultAudience;
        }

        public DefaultAudience a() {
            return this.a;
        }

        public void a(List<String> list) {
            if (LoginAuthorizationType.PUBLISH.equals(this.c)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            this.b = list;
            this.c = LoginAuthorizationType.READ;
        }

        public void b(List<String> list) {
            if (LoginAuthorizationType.READ.equals(this.c)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            } else if (z.a((Collection<T>) list)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            } else {
                this.b = list;
                this.c = LoginAuthorizationType.PUBLISH;
            }
        }

        /* access modifiers changed from: 0000 */
        public List<String> b() {
            return this.b;
        }

        public void c() {
            this.b = null;
            this.c = null;
        }

        public void a(LoginBehavior loginBehavior) {
            this.d = loginBehavior;
        }

        public LoginBehavior d() {
            return this.d;
        }

        public String e() {
            return this.e;
        }

        public void a(String str) {
            this.e = str;
        }
    }

    public LoginButton(Context context) {
        super(context, null, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
    }

    public LoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
    }

    public LoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0, "fb_login_button_create", "fb_login_button_did_tap");
    }

    public void setDefaultAudience(DefaultAudience defaultAudience) {
        this.properties.a(defaultAudience);
    }

    public DefaultAudience getDefaultAudience() {
        return this.properties.a();
    }

    public void setReadPermissions(List<String> list) {
        this.properties.a(list);
    }

    public void setReadPermissions(String... strArr) {
        this.properties.a(Arrays.asList(strArr));
    }

    public void setPublishPermissions(List<String> list) {
        this.properties.b(list);
    }

    public void setPublishPermissions(String... strArr) {
        this.properties.b(Arrays.asList(strArr));
    }

    public void clearPermissions() {
        this.properties.c();
    }

    public void setLoginBehavior(LoginBehavior loginBehavior) {
        this.properties.a(loginBehavior);
    }

    public LoginBehavior getLoginBehavior() {
        return this.properties.d();
    }

    public String getAuthType() {
        return this.properties.e();
    }

    public void setAuthType(String str) {
        this.properties.a(str);
    }

    public void setToolTipStyle(Style style) {
        this.toolTipStyle = style;
    }

    public void setToolTipMode(ToolTipMode toolTipMode2) {
        this.toolTipMode = toolTipMode2;
    }

    public ToolTipMode getToolTipMode() {
        return this.toolTipMode;
    }

    public void setToolTipDisplayTime(long j) {
        this.toolTipDisplayTime = j;
    }

    public long getToolTipDisplayTime() {
        return this.toolTipDisplayTime;
    }

    public void dismissToolTip() {
        if (this.toolTipPopup != null) {
            this.toolTipPopup.b();
            this.toolTipPopup = null;
        }
    }

    public void registerCallback(com.facebook.d dVar, com.facebook.e<com.facebook.login.e> eVar) {
        getLoginManager().a(dVar, eVar);
    }

    public void unregisterCallback(com.facebook.d dVar) {
        getLoginManager().a(dVar);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.accessTokenTracker != null && !this.accessTokenTracker.c()) {
            this.accessTokenTracker.a();
            setButtonText();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.toolTipChecked && !isInEditMode()) {
            this.toolTipChecked = true;
            checkToolTipSettings();
        }
    }

    /* access modifiers changed from: private */
    public void showToolTipPerSettings(j jVar) {
        if (jVar != null && jVar.c() && getVisibility() == 0) {
            displayToolTip(jVar.b());
        }
    }

    private void displayToolTip(String str) {
        this.toolTipPopup = new ToolTipPopup(str, this);
        this.toolTipPopup.a(this.toolTipStyle);
        this.toolTipPopup.a(this.toolTipDisplayTime);
        this.toolTipPopup.a();
    }

    private void checkToolTipSettings() {
        switch (this.toolTipMode) {
            case AUTOMATIC:
                final String a2 = z.a(getContext());
                f.d().execute(new Runnable() {
                    public void run() {
                        final j a2 = k.a(a2, false);
                        LoginButton.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                LoginButton.this.showToolTipPerSettings(a2);
                            }
                        });
                    }
                });
                return;
            case DISPLAY_ALWAYS:
                displayToolTip(getResources().getString(e.com_facebook_tooltip_default));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setButtonText();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.accessTokenTracker != null) {
            this.accessTokenTracker.b();
        }
        dismissToolTip();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            dismissToolTip();
        }
    }

    /* access modifiers changed from: 0000 */
    public List<String> getPermissions() {
        return this.properties.b();
    }

    /* access modifiers changed from: 0000 */
    public void setProperties(a aVar) {
        this.properties = aVar;
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        setInternalOnClickListener(getNewLoginClickListener());
        parseLoginButtonAttributes(context, attributeSet, i, i2);
        if (isInEditMode()) {
            setBackgroundColor(getResources().getColor(C0120a.com_facebook_blue));
            this.loginText = "Continue with Facebook";
        } else {
            this.accessTokenTracker = new c() {
                /* access modifiers changed from: protected */
                public void a(AccessToken accessToken, AccessToken accessToken2) {
                    LoginButton.this.setButtonText();
                }
            };
        }
        setButtonText();
        setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(getContext(), b.com_facebook_button_login_logo), null, null, null);
    }

    /* access modifiers changed from: protected */
    public LoginClickListener getNewLoginClickListener() {
        return new LoginClickListener();
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return C0122f.com_facebook_loginview_default_style;
    }

    private void parseLoginButtonAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        this.toolTipMode = ToolTipMode.DEFAULT;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, g.com_facebook_login_view, i, i2);
        try {
            this.confirmLogout = obtainStyledAttributes.getBoolean(g.com_facebook_login_view_com_facebook_confirm_logout, true);
            this.loginText = obtainStyledAttributes.getString(g.com_facebook_login_view_com_facebook_login_text);
            this.logoutText = obtainStyledAttributes.getString(g.com_facebook_login_view_com_facebook_logout_text);
            this.toolTipMode = ToolTipMode.fromInt(obtainStyledAttributes.getInt(g.com_facebook_login_view_com_facebook_tooltip_mode, ToolTipMode.DEFAULT.getValue()));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        FontMetrics fontMetrics = getPaint().getFontMetrics();
        int compoundPaddingTop = getCompoundPaddingTop() + ((int) Math.ceil((double) (Math.abs(fontMetrics.top) + Math.abs(fontMetrics.bottom)))) + getCompoundPaddingBottom();
        Resources resources = getResources();
        String str = this.loginText;
        if (str == null) {
            str = resources.getString(e.com_facebook_loginview_log_in_button_continue);
            int measureButtonWidth = measureButtonWidth(str);
            if (resolveSize(measureButtonWidth, i) < measureButtonWidth) {
                str = resources.getString(e.com_facebook_loginview_log_in_button);
            }
        }
        int measureButtonWidth2 = measureButtonWidth(str);
        String str2 = this.logoutText;
        if (str2 == null) {
            str2 = resources.getString(e.com_facebook_loginview_log_out_button);
        }
        setMeasuredDimension(resolveSize(Math.max(measureButtonWidth2, measureButtonWidth(str2)), i), compoundPaddingTop);
    }

    private int measureButtonWidth(String str) {
        return getCompoundPaddingLeft() + getCompoundDrawablePadding() + measureTextWidth(str) + getCompoundPaddingRight();
    }

    /* access modifiers changed from: private */
    public void setButtonText() {
        String str;
        Resources resources = getResources();
        if (!isInEditMode() && AccessToken.isCurrentAccessTokenActive()) {
            if (this.logoutText != null) {
                str = this.logoutText;
            } else {
                str = resources.getString(e.com_facebook_loginview_log_out_button);
            }
            setText(str);
        } else if (this.loginText != null) {
            setText(this.loginText);
        } else {
            String string = resources.getString(e.com_facebook_loginview_log_in_button_continue);
            int width = getWidth();
            if (width != 0 && measureButtonWidth(string) > width) {
                string = resources.getString(e.com_facebook_loginview_log_in_button);
            }
            setText(string);
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return RequestCodeOffset.Login.toRequestCode();
    }

    /* access modifiers changed from: 0000 */
    public d getLoginManager() {
        if (this.loginManager == null) {
            this.loginManager = d.c();
        }
        return this.loginManager;
    }

    /* access modifiers changed from: 0000 */
    public void setLoginManager(d dVar) {
        this.loginManager = dVar;
    }
}
