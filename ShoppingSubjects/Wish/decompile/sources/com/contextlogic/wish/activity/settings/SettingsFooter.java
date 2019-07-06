package com.contextlogic.wish.activity.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.service.compound.AuthenticationService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.ViewUtil;

public class SettingsFooter extends LinearLayout {
    public SettingsFooter(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.settings_fragment_footer, this);
        TextView textView = (TextView) inflate.findViewById(R.id.settings_fragment_footer_login_method_text);
        String string = getContext().getString(R.string.email);
        if (AuthenticationService.isUsingFacebookLogin()) {
            string = getContext().getString(R.string.facebook);
        } else if (AuthenticationService.isUsingGoogleLogin()) {
            string = getContext().getString(R.string.google);
        }
        textView.setText(getContext().getString(R.string.logged_in_method, new Object[]{string}));
        TextView textView2 = (TextView) inflate.findViewById(R.id.settings_fragment_footer_email_text);
        if (ProfileDataCenter.getInstance().getEmail() != null) {
            textView2.setText(getContext().getString(R.string.logged_in_email, new Object[]{ProfileDataCenter.getInstance().getEmail()}));
        } else {
            textView2.setVisibility(8);
        }
        ((TextView) inflate.findViewById(R.id.settings_fragment_footer_uid_text)).setText(getContext().getString(R.string.logged_in_user_id, new Object[]{AuthenticationDataCenter.getInstance().getUserId()}));
        ((TextView) inflate.findViewById(R.id.settings_fragment_footer_app_version_text)).setText(getContext().getString(R.string.app_version, new Object[]{WishApplication.getName(), WishApplication.getInstance().getVersionNumber()}));
        setOrientation(1);
        ViewUtil.setDefaultScreenPadding(this);
    }
}
