package com.contextlogic.wish.activity.share;

import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.invite.InviteCouponActivity;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.util.IntentUtil;

public class ShareActivity extends FullScreenActivity {
    public static String EXTRA_MESSAGE = "ExtraMessage";
    public static String EXTRA_SUBJECT = "ExtraSubject";
    public static String EXTRA_USE_DEFAULT_INVITE_MESSAGE = "ExtraUseDefaultInviteMessage";

    /* access modifiers changed from: protected */
    public boolean canHaveActionBar() {
        return false;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return null;
    }

    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public boolean immediatelyEnforceNotTaskRoot() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isHeadlessActivity() {
        return true;
    }

    public void handleOnCreate(Bundle bundle) {
        String str;
        String str2;
        super.handleOnCreate(bundle);
        if (ConfigDataCenter.getInstance().getInviteCouponSpec() != null) {
            Intent intent = new Intent();
            intent.setClass(this, InviteCouponActivity.class);
            startActivity(intent);
        } else {
            if (getIntent().getBooleanExtra(EXTRA_USE_DEFAULT_INVITE_MESSAGE, false)) {
                str = ConfigDataCenter.getInstance().getInviteSubject();
                str2 = ConfigDataCenter.getInstance().getInviteMessage();
            } else {
                str = getIntent().getStringExtra(EXTRA_SUBJECT);
                str2 = getIntent().getStringExtra(EXTRA_MESSAGE);
            }
            Intent shareIntent = IntentUtil.getShareIntent(str, str2);
            if (shareIntent != null) {
                startActivity(shareIntent);
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public boolean requiresAuthentication() {
        return getIntent().getBooleanExtra(EXTRA_USE_DEFAULT_INVITE_MESSAGE, false);
    }
}
