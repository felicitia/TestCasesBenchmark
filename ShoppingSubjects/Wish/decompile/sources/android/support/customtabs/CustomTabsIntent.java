package android.support.customtabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.BundleCompat;
import java.util.ArrayList;

public final class CustomTabsIntent {
    public final Intent intent;
    public final Bundle startAnimationBundle;

    public static final class Builder {
        private ArrayList<Bundle> mActionButtons;
        private final Intent mIntent;
        private ArrayList<Bundle> mMenuItems;
        private Bundle mStartAnimationBundle;

        public Builder() {
            this(null);
        }

        public Builder(CustomTabsSession customTabsSession) {
            this.mIntent = new Intent("android.intent.action.VIEW");
            IBinder iBinder = null;
            this.mMenuItems = null;
            this.mStartAnimationBundle = null;
            this.mActionButtons = null;
            if (customTabsSession != null) {
                this.mIntent.setPackage(customTabsSession.getComponentName().getPackageName());
            }
            Bundle bundle = new Bundle();
            String str = "android.support.customtabs.extra.SESSION";
            if (customTabsSession != null) {
                iBinder = customTabsSession.getBinder();
            }
            BundleCompat.putBinder(bundle, str, iBinder);
            this.mIntent.putExtras(bundle);
        }

        public CustomTabsIntent build() {
            if (this.mMenuItems != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", this.mMenuItems);
            }
            if (this.mActionButtons != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", this.mActionButtons);
            }
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle);
        }
    }

    public void launchUrl(Activity activity, Uri uri) {
        this.intent.setData(uri);
        ActivityCompat.startActivity(activity, this.intent, this.startAnimationBundle);
    }

    private CustomTabsIntent(Intent intent2, Bundle bundle) {
        this.intent = intent2;
        this.startAnimationBundle = bundle;
    }
}
