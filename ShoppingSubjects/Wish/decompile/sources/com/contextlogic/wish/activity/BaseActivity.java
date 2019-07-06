package com.contextlogic.wish.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager.BadTokenException;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.ServiceFragment.PermissionCallback;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.login.landing.LandingActivity;
import com.contextlogic.wish.activity.search.SearchActivity;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftActivity;
import com.contextlogic.wish.activity.signup.SignupProfileUpdateActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowActivity;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishScreenshotShareInfo;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventBundle;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventCallback;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.application.ForegroundWatcher.ForegroundListener;
import com.contextlogic.wish.application.ScreenshotWatcher;
import com.contextlogic.wish.application.ScreenshotWatcher.ScreenshotListener;
import com.contextlogic.wish.application.WishNfcManager;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.WishTooltip;
import com.contextlogic.wish.dialog.dailygiveaway.DailyGiveawayDialogFragment;
import com.contextlogic.wish.dialog.loading.LoadingDialogFragment;
import com.contextlogic.wish.dialog.screenshot.ScreenshotShareDialogFragment;
import com.contextlogic.wish.http.ImageHttpCache;
import com.contextlogic.wish.payments.google.GooglePayManager;
import com.contextlogic.wish.social.SmartLockManager;
import com.contextlogic.wish.social.google.GoogleSignInApiClient;
import com.contextlogic.wish.ui.view.ActionBarItemView;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.ScreenshotUtil;
import com.contextlogic.wish.util.TabletUtil;
import com.contextlogic.wish.util.ValueUtil;
import com.crashlytics.android.Crashlytics;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class BaseActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback, OnGlobalLayoutListener, ApplicationEventCallback, ForegroundListener, ScreenshotListener {
    private ActionBarManager mActionBarManager;
    private boolean mAuthenticationVerified;
    private ConcurrentLinkedQueue<Runnable> mAuthenticationVerifiedTasks;
    /* access modifiers changed from: private */
    public boolean mFollowUpIntentFollowed;
    private Handler mHandler;
    private boolean mIsActive;
    private boolean mIsFromNotification;
    private Rect mKeyboardCheckRect;
    private boolean mKeyboardVisible;
    private WishNfcManager mNfcManager;
    private boolean mReturningFromCart;
    private WishScreenshotShareInfo mScreenshotShareInfo;
    /* access modifiers changed from: private */
    public ServiceFragment mServiceFragment;
    private int mTheme;
    private HashMap<String, UiFragment> mUiFragments;

    protected enum ActivityAnimationTypes {
        NONE,
        SLIDING,
        SLIDE_UP,
        SLIDE_DOWN
    }

    public interface ActivityResultCallback {
        void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent);
    }

    /* access modifiers changed from: protected */
    public boolean canBeTaskRoot() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean canGoBack() {
        return true;
    }

    public boolean canShowDailyGiveawayNotification() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract UiFragment createMainContentFragment();

    /* access modifiers changed from: protected */
    public UiFragment createRightDrawerFragment() {
        return null;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return null;
    }

    public WishAnalyticsEvent getWishAnalyticsPageViewType() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean handleActionBarHomeSelected(MenuItem menuItem) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public boolean immediatelyEnforceNotTaskRoot() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void initializeCoreUi(Bundle bundle);

    /* access modifiers changed from: protected */
    public boolean isHeadlessActivity() {
        return false;
    }

    public void onActivityHandlingBackPress() {
    }

    public void onBackground() {
    }

    public final void onForeground() {
    }

    /* access modifiers changed from: protected */
    public boolean requiresAuthentication() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean requiresNoInterruption() {
        return false;
    }

    public boolean shouldUseDynamicBottomNavigationLayout() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void showAuthenticatingView();

    /* access modifiers changed from: protected */
    public abstract void showContentView();

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r0 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r0 = r0.shouldShowBottomNavigation()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0033
            com.contextlogic.wish.activity.BaseActivity$ActivityAnimationTypes r0 = r5.getEffectiveActivityAnimation()
            com.contextlogic.wish.activity.BaseActivity$ActivityAnimationTypes r3 = com.contextlogic.wish.activity.BaseActivity.ActivityAnimationTypes.SLIDE_UP
            r4 = 2130772006(0x7f010026, float:1.7147118E38)
            if (r0 != r3) goto L_0x0022
            r0 = 2130772002(0x7f010022, float:1.714711E38)
            r5.overridePendingTransition(r0, r4)
        L_0x0020:
            r0 = 1
            goto L_0x002b
        L_0x0022:
            com.contextlogic.wish.activity.BaseActivity$ActivityAnimationTypes r3 = com.contextlogic.wish.activity.BaseActivity.ActivityAnimationTypes.NONE
            if (r0 != r3) goto L_0x002a
            r5.overridePendingTransition(r2, r2)
            goto L_0x0020
        L_0x002a:
            r0 = 0
        L_0x002b:
            if (r0 != 0) goto L_0x0033
            r0 = 2130772003(0x7f010023, float:1.7147112E38)
            r5.overridePendingTransition(r0, r4)
        L_0x0033:
            android.os.Handler r0 = new android.os.Handler
            android.os.Looper r3 = android.os.Looper.getMainLooper()
            r0.<init>(r3)
            r5.mHandler = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r5.mUiFragments = r0
            r5.mIsActive = r2
            r5.mAuthenticationVerified = r2
            java.util.concurrent.ConcurrentLinkedQueue r0 = new java.util.concurrent.ConcurrentLinkedQueue
            r0.<init>()
            r5.mAuthenticationVerifiedTasks = r0
            r5.mFollowUpIntentFollowed = r2
            r5.mIsFromNotification = r2
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r5.mKeyboardCheckRect = r0
            boolean r0 = com.contextlogic.wish.util.TabletUtil.isTablet()
            if (r0 != 0) goto L_0x0072
            java.lang.String r0 = "ForceAllowRotation"
            boolean r0 = com.contextlogic.wish.util.PreferenceUtil.getBoolean(r0, r2)
            if (r0 != 0) goto L_0x0072
            boolean r0 = r5.isTranslucent()
            if (r0 != 0) goto L_0x0072
            r5.setRequestedOrientation(r1)
        L_0x0072:
            android.view.Window r0 = r5.getWindow()
            r3 = 16
            r0.setSoftInputMode(r3)
            boolean r0 = r5.immediatelyEnforceNotTaskRoot()
            if (r0 != 0) goto L_0x0087
            boolean r0 = r5.showingAsModal()
            if (r0 == 0) goto L_0x00c3
        L_0x0087:
            boolean r0 = r5.isTaskRoot()
            if (r0 == 0) goto L_0x00c3
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r3 = "ExtraFromFollowUpIntent"
            boolean r0 = r0.getBooleanExtra(r3, r2)
            if (r0 != 0) goto L_0x00c3
            android.content.Intent r0 = r5.getTaskRootIntent()
            if (r0 == 0) goto L_0x00c3
            android.content.Intent r6 = r5.getTaskRootIntent()
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r2 = "ExtraFromFollowUpIntent"
            r0.putExtra(r2, r1)
            int r1 = r0.getFlags()
            r2 = -32769(0xffffffffffff7fff, float:NaN)
            r1 = r1 & r2
            r0.setFlags(r1)
            java.lang.String r1 = "ExtraFollowUpIntent"
            com.contextlogic.wish.util.IntentUtil.putParcelableExtra(r6, r1, r0)
            r5.startActivity(r6)
            r5.finish()
            return
        L_0x00c3:
            com.contextlogic.wish.activity.actionbar.ActionBarManager r0 = new com.contextlogic.wish.activity.actionbar.ActionBarManager
            r0.<init>(r5, r6)
            r5.mActionBarManager = r0
            com.contextlogic.wish.activity.actionbar.ActionBarManager r0 = r5.mActionBarManager
            r5.initializeActionBarManager(r0)
            if (r6 == 0) goto L_0x00e1
            java.lang.String r0 = "SavedStateFollowUpIntentFollowed"
            boolean r0 = r6.getBoolean(r0)
            r5.mFollowUpIntentFollowed = r0
            java.lang.String r0 = "SavedStateIsFromNotification"
            boolean r0 = r6.getBoolean(r0)
            r5.mIsFromNotification = r0
        L_0x00e1:
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "ExtraFollowUpIntent"
            android.os.Parcelable r0 = com.contextlogic.wish.util.IntentUtil.getParcelableExtra(r0, r1)
            android.content.Intent r0 = (android.content.Intent) r0
            boolean r1 = r5.mFollowUpIntentFollowed
            if (r1 != 0) goto L_0x00fb
            if (r0 == 0) goto L_0x00fb
            com.contextlogic.wish.activity.BaseActivity$1 r1 = new com.contextlogic.wish.activity.BaseActivity$1
            r1.<init>(r0)
            r5.withVerifiedAuthentication(r1)
        L_0x00fb:
            r5.initializeCoreUi(r6)
            r5.initializeFragments()
            com.contextlogic.wish.activity.actionbar.ActionBarManager r0 = r5.mActionBarManager
            r0.apply()
            boolean r0 = r5.isHeadlessActivity()
            if (r0 != 0) goto L_0x011d
            com.contextlogic.wish.analytics.GoogleAnalyticsLogger r0 = com.contextlogic.wish.analytics.GoogleAnalyticsLogger.getInstance()
            boolean r0 = r0.isStarted()
            if (r0 != 0) goto L_0x011d
            com.contextlogic.wish.analytics.GoogleAnalyticsLogger r0 = com.contextlogic.wish.analytics.GoogleAnalyticsLogger.getInstance()
            r0.startAnalytics()
        L_0x011d:
            com.contextlogic.wish.application.ApplicationEventManager r0 = com.contextlogic.wish.application.ApplicationEventManager.getInstance()
            com.contextlogic.wish.application.ApplicationEventManager$EventType r1 = com.contextlogic.wish.application.ApplicationEventManager.EventType.LOGOUT_REQUIRED
            r0.addCallback(r1, r5)
            com.contextlogic.wish.application.ApplicationEventManager r0 = com.contextlogic.wish.application.ApplicationEventManager.getInstance()
            com.contextlogic.wish.application.ApplicationEventManager$EventType r1 = com.contextlogic.wish.application.ApplicationEventManager.EventType.DAILY_GIVEAWAY_SPLASH_NOTIFICATION
            r0.addCallback(r1, r5)
            boolean r0 = r5.requiresAuthentication()
            if (r0 == 0) goto L_0x0141
            java.lang.String r0 = "LoggedInUser"
            java.lang.String r0 = com.contextlogic.wish.util.PreferenceUtil.getString(r0)
            if (r0 != 0) goto L_0x0141
            r5.handleLogoutRequired()
            return
        L_0x0141:
            java.lang.String r0 = "UserAgent"
            java.lang.String r0 = com.contextlogic.wish.util.PreferenceUtil.getString(r0)
            if (r0 != 0) goto L_0x015f
            java.lang.String r0 = "UserAgent"
            android.webkit.WebView r1 = new android.webkit.WebView     // Catch:{ Throwable -> 0x015f }
            com.contextlogic.wish.application.WishApplication r2 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x015f }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x015f }
            android.webkit.WebSettings r1 = r1.getSettings()     // Catch:{ Throwable -> 0x015f }
            java.lang.String r1 = r1.getUserAgentString()     // Catch:{ Throwable -> 0x015f }
            com.contextlogic.wish.util.PreferenceUtil.setString(r0, r1)     // Catch:{ Throwable -> 0x015f }
        L_0x015f:
            android.view.View r0 = r5.getRootView()
            android.view.ViewTreeObserver r0 = r0.getViewTreeObserver()
            r0.addOnGlobalLayoutListener(r5)
            r5.handleOnCreate(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.BaseActivity.onCreate(android.os.Bundle):void");
    }

    public void setTheme(int i) {
        this.mTheme = i;
        super.setTheme(i);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("SavedStateFollowUpIntentFollowed", this.mFollowUpIntentFollowed);
        if (this.mActionBarManager != null) {
            this.mActionBarManager.onSaveInstanceState(bundle);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mActionBarManager != null) {
            this.mActionBarManager.onConfigurationChanged(configuration);
        }
    }

    /* access modifiers changed from: protected */
    public void initializeFragments() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.mServiceFragment = (ServiceFragment) supportFragmentManager.findFragmentByTag("FragmentTagService");
        if (this.mServiceFragment == null) {
            this.mServiceFragment = createServiceFragment();
            supportFragmentManager.beginTransaction().add((Fragment) this.mServiceFragment, "FragmentTagService").commitAllowingStateLoss();
            try {
                supportFragmentManager.executePendingTransactions();
            } catch (IllegalStateException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ServiceFragment();
    }

    public ServiceFragment getServiceFragment() {
        return this.mServiceFragment;
    }

    /* access modifiers changed from: protected */
    public void registerUiFragment(String str, UiFragment uiFragment) {
        this.mUiFragments.put(str, uiFragment);
    }

    /* access modifiers changed from: protected */
    public void unregisterUiFragment(String str) {
        this.mUiFragments.remove(str);
    }

    public UiFragment getUiFragment(String str) {
        return (UiFragment) this.mUiFragments.get(str);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onResume() {
        /*
            r5 = this;
            super.onResume()
            boolean r0 = r5.isFinishing()
            if (r0 == 0) goto L_0x000a
            return
        L_0x000a:
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r0 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r0 = r0.shouldShowBottomNavigation()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0045
            com.contextlogic.wish.activity.BaseActivity$ActivityAnimationTypes r0 = r5.getEffectiveActivityAnimation()
            com.contextlogic.wish.activity.BaseActivity$ActivityAnimationTypes r3 = com.contextlogic.wish.activity.BaseActivity.ActivityAnimationTypes.SLIDE_UP
            r4 = 2130772006(0x7f010026, float:1.7147118E38)
            if (r0 != r3) goto L_0x0029
            r0 = 2130772002(0x7f010022, float:1.714711E38)
            r5.overridePendingTransition(r0, r4)
        L_0x0027:
            r0 = 1
            goto L_0x003d
        L_0x0029:
            com.contextlogic.wish.activity.BaseActivity$ActivityAnimationTypes r3 = com.contextlogic.wish.activity.BaseActivity.ActivityAnimationTypes.SLIDE_DOWN
            if (r0 != r3) goto L_0x0034
            r0 = 2130772005(0x7f010025, float:1.7147116E38)
            r5.overridePendingTransition(r2, r0)
            goto L_0x0027
        L_0x0034:
            com.contextlogic.wish.activity.BaseActivity$ActivityAnimationTypes r3 = com.contextlogic.wish.activity.BaseActivity.ActivityAnimationTypes.NONE
            if (r0 != r3) goto L_0x003c
            r5.overridePendingTransition(r2, r2)
            goto L_0x0027
        L_0x003c:
            r0 = 0
        L_0x003d:
            if (r0 != 0) goto L_0x0045
            r0 = 2130772003(0x7f010023, float:1.7147112E38)
            r5.overridePendingTransition(r0, r4)
        L_0x0045:
            r5.mReturningFromCart = r2
            boolean r0 = r5.isHeadlessActivity()
            if (r0 != 0) goto L_0x0062
            com.contextlogic.wish.application.ForegroundWatcher r0 = com.contextlogic.wish.application.ForegroundWatcher.getInstance()
            r0.addListener(r5)
            com.contextlogic.wish.application.ForegroundWatcher r0 = com.contextlogic.wish.application.ForegroundWatcher.getInstance()
            r0.onActivityResumed(r5)
            com.contextlogic.wish.application.ScreenshotWatcher r0 = com.contextlogic.wish.application.ScreenshotWatcher.getInstance()
            r0.addListener(r5)
        L_0x0062:
            r5.mIsActive = r1
            boolean r0 = r5.requiresAuthentication()
            if (r0 == 0) goto L_0x0073
            r5.showAuthenticatingView()
            com.contextlogic.wish.activity.ServiceFragment r0 = r5.mServiceFragment
            r0.ensureLoggedIn()
            goto L_0x0076
        L_0x0073:
            r5.handleAuthenticationStateVerified()
        L_0x0076:
            com.contextlogic.wish.social.SmartLockManager r0 = com.contextlogic.wish.social.SmartLockManager.getInstance()
            boolean r0 = r0.showCredentialSavedConfirmation()
            if (r0 == 0) goto L_0x008c
            com.contextlogic.wish.social.SmartLockManager r0 = com.contextlogic.wish.social.SmartLockManager.getInstance()
            com.contextlogic.wish.activity.BaseActivity$2 r1 = new com.contextlogic.wish.activity.BaseActivity$2
            r1.<init>()
            r0.saveCredentials(r1)
        L_0x008c:
            java.lang.String r0 = "android.nfc.action.NDEF_DISCOVERED"
            android.content.Intent r1 = r5.getIntent()
            java.lang.String r1 = r1.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00a9
            com.contextlogic.wish.application.WishNfcManager r0 = r5.mNfcManager
            if (r0 == 0) goto L_0x00a9
            com.contextlogic.wish.application.WishNfcManager r0 = r5.mNfcManager
            android.content.Intent r1 = r5.getIntent()
            r0.processNdefIntent(r1)
        L_0x00a9:
            com.contextlogic.wish.analytics.GoogleAnalyticsLogger r0 = com.contextlogic.wish.analytics.GoogleAnalyticsLogger.getInstance()
            r0.logLastActivity(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.BaseActivity.onResume():void");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        GoogleSignInApiClient.getInstance().connect();
        SmartLockManager.getInstance().connectCredentialsClient();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mAuthenticationVerified = false;
        this.mIsActive = false;
        this.mServiceFragment.cancelAppForegroundedChecks();
        if (isFinishing()) {
            boolean z = true;
            if (getEffectiveActivityAnimation() == ActivityAnimationTypes.SLIDE_UP) {
                overridePendingTransition(0, R.anim.window_enter_up_bottom_animation);
            } else if (getEffectiveActivityAnimation() == ActivityAnimationTypes.NONE) {
                overridePendingTransition(0, 0);
            } else {
                z = false;
            }
            if (!z) {
                overridePendingTransition(0, R.anim.window_enter_slide_out_animation);
            }
        }
        if (!isHeadlessActivity()) {
            ForegroundWatcher.getInstance().removeListener(this);
            ForegroundWatcher.getInstance().onActivityPaused(this);
        }
        KeyboardUtil.hideKeyboard((Activity) this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        ImageHttpCache.getInstance().clearActivityCache(this);
        if (!isHeadlessActivity()) {
            ScreenshotWatcher.getInstance().removeListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        View rootView = getRootView();
        if (rootView != null) {
            rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        this.mHandler.removeCallbacksAndMessages(null);
        ImageHttpCache.getInstance().clearActivityCache(this);
        ApplicationEventManager.getInstance().removeCallback(this);
    }

    public void onBackPressed() {
        if (!this.mActionBarManager.onBackPressed()) {
            UiFragment uiFragment = (UiFragment) getSupportFragmentManager().findFragmentByTag("FragmentTagMainContent");
            if (uiFragment == null || !uiFragment.onBackPressed()) {
                onActivityHandlingBackPress();
                if (canGoBack()) {
                    if (isTaskRoot()) {
                        Intent taskRootIntent = getTaskRootIntent();
                        if (taskRootIntent == null || canBeTaskRoot()) {
                            moveTaskToBack(true);
                        } else {
                            startActivity(taskRootIntent, true);
                        }
                    } else {
                        try {
                            super.onBackPressed();
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (IntentUtil.safeToUnparcel(intent)) {
            if (intent.getBooleanExtra("ExtraRequiresReload", false)) {
                setResult(i2, intent);
            }
            if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
                this.mReturningFromCart = intent.getBooleanExtra(CartActivity.EXTRA_RETURNING_FROM_CART, false);
            }
        }
        this.mServiceFragment.handleActivityResult(i, i2, intent);
    }

    public void onLoggedInForeground() {
        if (!requiresNoInterruption()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    BaseActivity.this.mServiceFragment.performAppForegroundedChecks();
                    GooglePayManager.getInstance().updateGooglePayReadyStatus(BaseActivity.this.mServiceFragment);
                }
            });
        }
    }

    public void onScreenshotTaken() {
        if (isActive()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAPTURE_SCREENSHOT);
        }
        if (AuthenticationDataCenter.getInstance().isLoggedIn() && this.mScreenshotShareInfo != null) {
            Uri takeScreenshot = ScreenshotUtil.takeScreenshot(this);
            if (takeScreenshot != null) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_SCREENSHOT_SHARE_DIALOG);
                ScreenshotShareDialogFragment createScreesnhotShareDialog = ScreenshotShareDialogFragment.createScreesnhotShareDialog(takeScreenshot, this.mScreenshotShareInfo);
                if (createScreesnhotShareDialog != null) {
                    startDialog(createScreesnhotShareDialog);
                }
            }
        }
    }

    public final void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
        if (isActive() && eventType == EventType.LOGOUT_REQUIRED) {
            handleLogoutRequired();
        } else if (isActive() && eventType == EventType.DAILY_GIVEAWAY_SPLASH_NOTIFICATION) {
            handleDailyGiveawayNotification();
        }
    }

    public void setScreenshotShareInfo(WishScreenshotShareInfo wishScreenshotShareInfo) {
        this.mScreenshotShareInfo = wishScreenshotShareInfo;
    }

    public int getBackgroundColor() {
        return getResources().getColor(R.color.white);
    }

    public void setIsFromNotification(boolean z) {
        this.mIsFromNotification = z;
    }

    /* access modifiers changed from: protected */
    public boolean isActive() {
        return this.mIsActive;
    }

    public boolean isKeyboardVisible() {
        return this.mKeyboardVisible;
    }

    /* access modifiers changed from: protected */
    public View getRootView() {
        return ((ViewGroup) findViewById(16908290)).getChildAt(0);
    }

    /* access modifiers changed from: protected */
    public void handleAuthenticationStateVerified() {
        this.mAuthenticationVerified = true;
        this.mServiceFragment.initializeIfNeccessary();
        for (UiFragment initializeIfNeccessary : new HashMap(this.mUiFragments).values()) {
            initializeIfNeccessary.initializeIfNeccessary();
        }
        showContentView();
        if (AuthenticationDataCenter.getInstance().isLoggedIn()) {
            ForegroundWatcher.getInstance().onActivityLoggedInResumed(this);
        }
        while (true) {
            Runnable runnable = (Runnable) this.mAuthenticationVerifiedTasks.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    public void withVerifiedAuthentication(Runnable runnable) {
        if (this.mAuthenticationVerified) {
            runnable.run();
        } else {
            this.mAuthenticationVerifiedTasks.add(runnable);
        }
    }

    /* access modifiers changed from: protected */
    public void handleLogoutRequired() {
        this.mServiceFragment.getAuthenticationService().quickLogout();
        ForegroundWatcher.getInstance().onActivityLogout(this);
        Intent intent = new Intent();
        intent.setClass(this, LandingActivity.class);
        IntentUtil.putParcelableExtra(intent, "ExtraPreLoginIntent", getIntent());
        startActivity(intent, true);
    }

    public void closeForLogout() {
        ForegroundWatcher.getInstance().onActivityLogout(this);
        Intent intent = new Intent();
        intent.setClass(this, LandingActivity.class);
        startActivity(intent, true);
    }

    public void finishActivity() {
        Intent taskRootIntent = getTaskRootIntent();
        if (!isTaskRoot() || taskRootIntent == null || canBeTaskRoot()) {
            finish();
        } else {
            startActivity(taskRootIntent, true);
        }
    }

    public void onDialogCancel(BaseDialogFragment baseDialogFragment) {
        if (this.mServiceFragment != null) {
            this.mServiceFragment.onDialogCancel(baseDialogFragment);
        }
    }

    public void onDialogSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
        this.mServiceFragment.onDialogSelection(baseDialogFragment, i, bundle);
    }

    public void startDialog(BaseDialogFragment baseDialogFragment) {
        startDialog(baseDialogFragment, null);
    }

    public void startDialog(BaseDialogFragment baseDialogFragment, BaseDialogCallback baseDialogCallback) {
        dismissModal();
        this.mServiceFragment.setDialogCallback(baseDialogCallback);
        try {
            baseDialogFragment.show(getSupportFragmentManager(), "FragmentTagDialog");
            getSupportFragmentManager().executePendingTransactions();
        } catch (BadTokenException | IllegalStateException unused) {
        }
    }

    public void showTooltip(WishTooltip wishTooltip) {
        dismissTooltip();
        try {
            wishTooltip.show(getSupportFragmentManager(), "FragmentTagTooltipDialog");
            getSupportFragmentManager().executePendingTransactions();
        } catch (BadTokenException | IllegalStateException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public WishTooltip getCurrentTooltip() {
        return (WishTooltip) getSupportFragmentManager().findFragmentByTag("FragmentTagTooltipDialog");
    }

    /* access modifiers changed from: protected */
    public BaseDialogFragment getCurrentBaseDialog() {
        return (BaseDialogFragment) getSupportFragmentManager().findFragmentByTag("FragmentTagDialog");
    }

    /* access modifiers changed from: protected */
    public LoadingDialogFragment getCurrentLoadingDialog() {
        return (LoadingDialogFragment) getSupportFragmentManager().findFragmentByTag("FragmentTagLoadingDialog");
    }

    public void showLoadingDialog() {
        if (getCurrentLoadingDialog() == null) {
            dismissModal();
            this.mServiceFragment.setDialogCallback(null);
            try {
                new LoadingDialogFragment().show(getSupportFragmentManager(), "FragmentTagLoadingDialog");
                getSupportFragmentManager().executePendingTransactions();
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void hideLoadingDialog() {
        LoadingDialogFragment currentLoadingDialog = getCurrentLoadingDialog();
        if (currentLoadingDialog != null) {
            currentLoadingDialog.cancel();
        }
        try {
            getSupportFragmentManager().executePendingTransactions();
        } catch (IllegalStateException unused) {
        }
    }

    public void dismissModal() {
        BaseDialogFragment currentBaseDialog = getCurrentBaseDialog();
        if (currentBaseDialog != null) {
            currentBaseDialog.cancel();
        }
        LoadingDialogFragment currentLoadingDialog = getCurrentLoadingDialog();
        if (currentLoadingDialog != null) {
            currentLoadingDialog.cancel();
        }
        try {
            getSupportFragmentManager().executePendingTransactions();
        } catch (IllegalStateException unused) {
        }
    }

    public void dismissTooltip() {
        WishTooltip currentTooltip = getCurrentTooltip();
        if (currentTooltip != null) {
            currentTooltip.dismissAllowingStateLoss();
        }
        try {
            getSupportFragmentManager().executePendingTransactions();
        } catch (IllegalStateException unused) {
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 101 && this.mServiceFragment != null) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                this.mServiceFragment.permissionDenied();
            } else {
                this.mServiceFragment.permissionGranted();
            }
        }
    }

    public void requestPermission(String str, PermissionCallback permissionCallback) {
        this.mServiceFragment.setPermissionCallback(permissionCallback);
        if (ContextCompat.checkSelfPermission(this, str) != 0) {
            ActivityCompat.requestPermissions(this, new String[]{str}, 101);
            return;
        }
        this.mServiceFragment.permissionGranted();
    }

    public void finishLogin(boolean z, SignupFlowContext signupFlowContext, LoginMode loginMode) {
        if (!z || signupFlowContext == null) {
            startPostLoginActivity();
            return;
        }
        if (PreferenceUtil.getBoolean("SkipSignUp") && signupFlowContext.signupFlowMode == SignupFlowType.FreeGifts) {
            signupFlowContext.signupFlowMode = SignupFlowType.Categories;
        }
        startSignupFlow(signupFlowContext, loginMode);
    }

    public void startSignupFlow(SignupFlowContext signupFlowContext, LoginMode loginMode) {
        if (ExperimentDataCenter.getInstance().canSeeNewSingleOnboardingFlow()) {
            switch (signupFlowContext.signupFlowMode) {
                case FreeGifts:
                case Categories:
                    Intent intent = new Intent();
                    IntentUtil.putParcelableExtra(intent, "ArgSignupFlowContext", signupFlowContext);
                    IntentUtil.putParcelableExtra(intent, "ExtraLoginMode", loginMode);
                    intent.setClass(this, SignupFlowActivity.class);
                    Intent intent2 = new Intent();
                    intent2.setClass(this, BrowseActivity.class);
                    intent2.putExtra("ExtraPlaceholderMode", true);
                    IntentUtil.putParcelableExtra(intent2, "ExtraFollowUpIntent", intent);
                    startActivity(intent2);
                    return;
                case None:
                    startHomeActivity();
                    return;
                default:
                    return;
            }
        } else {
            switch (signupFlowContext.signupFlowMode) {
                case FreeGifts:
                    Intent intent3 = new Intent();
                    IntentUtil.putParcelableExtra(intent3, "ArgSignupFlowContext", signupFlowContext);
                    IntentUtil.putParcelableExtra(intent3, "ExtraLoginMode", loginMode);
                    intent3.setClass(this, SignupFreeGiftActivity.class);
                    Intent intent4 = new Intent();
                    intent4.setClass(this, BrowseActivity.class);
                    intent4.putExtra("ExtraPlaceholderMode", true);
                    IntentUtil.putParcelableExtra(intent4, "ExtraFollowUpIntent", intent3);
                    startActivity(intent4, true);
                    return;
                case Categories:
                    Intent intent5 = new Intent();
                    IntentUtil.putParcelableExtra(intent5, "ArgSignupFlowContext", signupFlowContext);
                    IntentUtil.putParcelableExtra(intent5, "ExtraLoginMode", loginMode);
                    intent5.setClass(this, SignupProfileUpdateActivity.class);
                    Intent intent6 = new Intent();
                    intent6.setClass(this, BrowseActivity.class);
                    intent6.putExtra("ExtraPlaceholderMode", true);
                    IntentUtil.putParcelableExtra(intent6, "ExtraFollowUpIntent", intent5);
                    startActivity(intent6);
                    return;
                case None:
                    startHomeActivity();
                    return;
                default:
                    return;
            }
        }
    }

    public void startPostLoginActivity() {
        Intent intent = (Intent) IntentUtil.getParcelableExtra(getIntent(), "ExtraPreLoginIntent");
        if (intent != null) {
            startActivity(intent, true);
        } else {
            startHomeActivity();
        }
    }

    public void startHomeActivity() {
        Intent intent = new Intent();
        intent.setClass(this, BrowseActivity.class);
        startActivity(intent, true);
    }

    public void startActivity(Intent intent) {
        startActivity(intent, false);
    }

    public void startActivity(Intent intent, boolean z) {
        if (!isFinishing()) {
            if (z) {
                intent.addFlags(268468224);
            }
            startActivityForResult(intent, 999);
            if (z) {
                finish();
            }
        }
    }

    public void startActivityForResult(Intent intent, int i) {
        try {
            intent.putExtra("ExtraSourceActivity", getClass().toString());
            super.startActivityForResult(intent, i);
        } catch (Throwable unused) {
        }
    }

    public void addResultCallback(ActivityResultCallback activityResultCallback) {
        addResultCallback(activityResultCallback, null);
    }

    public void addResultCallback(ActivityResultCallback activityResultCallback, Object obj) {
        this.mServiceFragment.addResultCallback(activityResultCallback, obj);
    }

    public int addResultCodeCallback(ActivityResultCallback activityResultCallback) {
        return this.mServiceFragment.addResultCodeCallback(activityResultCallback);
    }

    public void removeResultCallbackTag(Object obj) {
        this.mServiceFragment.removeResultCallbackTag(obj);
    }

    public void removeResultCodeCallback(int i) {
        this.mServiceFragment.removeResultCodeCallback(i);
    }

    public final boolean showingAsModal() {
        String str;
        try {
            str = getResources().getResourceName(this.mTheme);
        } catch (Exception unused) {
            str = null;
        }
        return str != null && str.endsWith(".ModalWhenTablet") && TabletUtil.isTablet();
    }

    public final boolean isTranslucent() {
        String str;
        try {
            str = getResources().getResourceName(this.mTheme);
        } catch (Exception unused) {
            str = null;
        }
        return str != null && (str.contains(".Translucent") || str.contains(".Transparent"));
    }

    /* access modifiers changed from: protected */
    public Intent getTaskRootIntent() {
        Intent intent = new Intent();
        intent.setClass(this, BrowseActivity.class);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        if (canGoBack()) {
            actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
        } else {
            actionBarManager.setHomeButtonMode(HomeButtonMode.NO_ICON);
        }
    }

    public ActionBarManager getActionBarManager() {
        return this.mActionBarManager;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        getActionBarManager().applyMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    public final boolean onMenuActionViewClicked(ActionBarItemView actionBarItemView) {
        if (actionBarItemView.getActionId() != 1000) {
            return handleActionBarItemSelected(actionBarItemView.getActionId());
        }
        Intent intent = new Intent();
        intent.setClass(this, CartActivity.class);
        startActivity(intent);
        WishAnalyticsLogger.trackEvent(getClickImpressionActionBarCartButton());
        return true;
    }

    public WishAnalyticsEvent getClickImpressionActionBarCartButton() {
        return WishAnalyticsEvent.CLICK_ACTION_BAR_CART_BUTTON;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return handleActionBarItemSelected(menuItem.getItemId());
        }
        if (!handleActionBarHomeSelected(menuItem)) {
            onBackPressed();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean handleActionBarItemSelected(int i) {
        UiFragment uiFragment = (UiFragment) getSupportFragmentManager().findFragmentByTag("FragmentTagMainContent");
        if (uiFragment != null && uiFragment.handleActionBarItemSelected(i)) {
            return true;
        }
        if (i != -1000) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClass(this, SearchActivity.class);
        startActivity(intent);
        return true;
    }

    public void onGlobalLayout() {
        View rootView = getRootView();
        rootView.getWindowVisibleDisplayFrame(this.mKeyboardCheckRect);
        boolean z = ((float) (rootView.getRootView().getHeight() - (this.mKeyboardCheckRect.bottom - this.mKeyboardCheckRect.top))) >= ValueUtil.convertDpToPx(100.0f);
        if (this.mKeyboardVisible != z) {
            onKeyboardVisiblityChanged(z);
        }
        this.mKeyboardVisible = z;
    }

    public void onKeyboardVisiblityChanged(boolean z) {
        UiFragment uiFragment = (UiFragment) getSupportFragmentManager().findFragmentByTag("FragmentTagMainContent");
        if (uiFragment != null) {
            uiFragment.onKeyboardVisiblityChanged(z);
        }
    }

    public void showShareDialog(String str, String str2) {
        showShareDialog(str, str2, false);
    }

    public void showShareDialog(String str, String str2, boolean z) {
        Intent intent;
        if (z) {
            try {
                intent = IntentUtil.getShareIntentWithoutFB(str, str2);
            } catch (Throwable unused) {
                return;
            }
        } else {
            intent = IntentUtil.getShareIntent(str, str2);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    private void handleDailyGiveawayNotification() {
        if (canShowDailyGiveawayNotification()) {
            DailyGiveawayDialogFragment createDailyGiveawayDialog = DailyGiveawayDialogFragment.createDailyGiveawayDialog(ConfigDataCenter.getInstance().getDailyGiveawayNotiInfo());
            if (createDailyGiveawayDialog != null) {
                startDialog(createDailyGiveawayDialog, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                    }
                });
            }
        }
    }

    private ActivityAnimationTypes getEffectiveActivityAnimation() {
        try {
            if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty() && getIntent().getExtras().getBoolean("ExtraNoAnimationIntent")) {
                return ActivityAnimationTypes.NONE;
            }
        } catch (BadParcelableException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Catch FB BadParcelableException: ");
            sb.append(e.getMessage());
            Crashlytics.logException(new Exception(sb.toString()));
        }
        if (!ExperimentDataCenter.getInstance().shouldShowBottomNavigation() || !this.mReturningFromCart) {
            return getDefaultActivityAnimation();
        }
        return ActivityAnimationTypes.SLIDE_DOWN;
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            return ActivityAnimationTypes.NONE;
        }
        return ActivityAnimationTypes.SLIDING;
    }

    public void shareEventTriggered() {
        if (this.mServiceFragment != null) {
            this.mServiceFragment.shareEventTriggered();
        }
    }
}
