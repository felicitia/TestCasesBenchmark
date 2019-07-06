package com.contextlogic.wish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.braintreepayments.api.BraintreeFragment;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.DialogTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.dailybonus.DailyLoginBonusCouponDialogFragment;
import com.contextlogic.wish.activity.dailybonus.DailyLoginBonusDialogFragment;
import com.contextlogic.wish.activity.dailybonus.DailyLoginBonusStampPopupDialog;
import com.contextlogic.wish.activity.dailybonus.StampRowView.Callback;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.invitepopup.InviteCouponPopupDialogFragment;
import com.contextlogic.wish.activity.login.signin.SignInActivity;
import com.contextlogic.wish.activity.notshippablecountrypopup.NotShippableCountryPopupDialogFragment;
import com.contextlogic.wish.activity.profile.WishStarDialogFragment;
import com.contextlogic.wish.activity.termspolicy.TermsPolicyTextView;
import com.contextlogic.wish.activity.ugcfeedback.UgcFeedbackDialogFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.LoggedOutCountdownCoupon;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishClipboardCouponPopupDialogSpec;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.api.model.WishGoogleAppIndexingData;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishLoginAction;
import com.contextlogic.wish.api.model.WishLoginAction.ActionType;
import com.contextlogic.wish.api.model.WishLoginActionPopup;
import com.contextlogic.wish.api.model.WishLoginActionRateApp;
import com.contextlogic.wish.api.model.WishLoginActionUgcFeedback;
import com.contextlogic.wish.api.model.WishLoginActionUpdateApp;
import com.contextlogic.wish.api.model.WishNotShippableCountryPopupSpec;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishPromotionSpec;
import com.contextlogic.wish.api.model.WishRotatingSaleNotificationSpec;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.model.WishVideoPopupSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.compound.AuthenticationService;
import com.contextlogic.wish.api.service.compound.AuthenticationService.AuthenticationCallback;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.ClickNotificationService;
import com.contextlogic.wish.api.service.standalone.ClipboardCouponPopupDialogClaimService;
import com.contextlogic.wish.api.service.standalone.GetBraintreeClientTokenService;
import com.contextlogic.wish.api.service.standalone.GetBraintreeClientTokenService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetLoginActionService;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetRotatingPromoNotificationSpecService;
import com.contextlogic.wish.api.service.standalone.LogSignupDeepLinkService;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext;
import com.contextlogic.wish.api.service.standalone.MarkRedDotNotificationsSeenService;
import com.contextlogic.wish.api.service.standalone.NeverShowInviteCouponService;
import com.contextlogic.wish.api.service.standalone.ShareProductEventService;
import com.contextlogic.wish.api.service.standalone.UpdateCartService;
import com.contextlogic.wish.api.service.standalone.UpdateRotatingPromoNotificationTagsService;
import com.contextlogic.wish.api.service.standalone.ViewNotificationService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocartoffer.AddToCartOfferDialogFragment;
import com.contextlogic.wish.dialog.clipboard.ClipboardCouponPopupDialogFragment;
import com.contextlogic.wish.dialog.countdowncoupon.CountdownCouponDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.dialog.popupanimation.itemadded.ItemAddedDialogFragment;
import com.contextlogic.wish.dialog.promotion.SplashPromotionDialogFragment;
import com.contextlogic.wish.dialog.promotion.rotating.PromoRotatingNotiDialogFragment;
import com.contextlogic.wish.http.ServerConfig;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.social.SmartLockManager;
import com.contextlogic.wish.social.SmartLockManager.ResolutionActivityTask;
import com.contextlogic.wish.social.SocialSession.ErrorContext;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.social.google.GoogleSignInApiClient;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.StoreUtil;
import com.contextlogic.wish.video.VideoPopupDialogFragment;
import com.crashlytics.android.Crashlytics;
import com.facebook.applinks.AppLinkData;
import com.facebook.applinks.AppLinkData.CompletionHandler;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.Wallet.WalletOptions.Builder;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.Indexable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServiceFragment<A extends BaseActivity> extends BaseFragment<A> {
    /* access modifiers changed from: private */
    public AuthenticationService mAuthenticationService;
    private BaseDialogCallback mBaseDialogCallback;
    /* access modifiers changed from: private */
    public BraintreeFragment mBraintreeFragment;
    /* access modifiers changed from: private */
    public ClipboardCouponPopupDialogClaimService mClipboardCouponPopupDialogClaimService;
    /* access modifiers changed from: private */
    public Object mDeferredAppLinkRequestTag;
    protected boolean mFeedLoaded;
    private GetBraintreeClientTokenService mGetBraintreeClientTokenService;
    private GetLoginActionService mGetLoginActionService;
    private GetProductService mGetProductService;
    private GetRotatingPromoNotificationSpecService mGetRotatingPromoNotiSpec;
    private Action mGoogleAppIndexAction;
    private Indexable mGoogleAppIndexIndexable;
    private boolean mGoogleAppIndexTracked;
    private Handler mHandler;
    private boolean mIsActive;
    /* access modifiers changed from: private */
    public List<Runnable> mLoginActionTasks;
    private MarkRedDotNotificationsSeenService mMarkRedDotNotificationsSeenService;
    private NeverShowInviteCouponService mNeverShowInviteCouponService;
    private ConcurrentLinkedQueue<Runnable> mOnFeedLoadedTasks;
    /* access modifiers changed from: private */
    public boolean mPageViewTracked;
    private PaymentsClient mPaymentsClient;
    private PermissionCallback mPermissionCallback;
    private Random mRandom;
    private HashMap<Object, ActivityResultCallback> mResultCallbackLookup;
    private ArrayList<ActivityResultCallback> mResultCallbacks;
    private SparseArray<ActivityResultCallback> mResultCodeCallbacks;
    private UpdateCartService mUpdateCartService;
    private UpdateRotatingPromoNotificationTagsService mUpdateRotatingPromoTagsService;

    /* renamed from: com.contextlogic.wish.activity.ServiceFragment$47 reason: invalid class name */
    static /* synthetic */ class AnonymousClass47 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType = new int[ActionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.contextlogic.wish.api.model.WishLoginAction$ActionType[] r0 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.POPUP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.DEEP_LINK     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.PROMOTION     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.UPDATE_APP     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.RATE_APP     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x004b }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.INVITE_FRIEND     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.CLIPBOARD     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.UGC_FEEDBACK     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x006e }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.DAILY_LOGIN_BONUS     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x007a }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.WISH_STAR     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x0086 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.CAMERA_FEATURE     // Catch:{ NoSuchFieldError -> 0x0086 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0086 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0086 }
            L_0x0086:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x0092 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.COUNTDOWN_COUPON_CLAIMED     // Catch:{ NoSuchFieldError -> 0x0092 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0092 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0092 }
            L_0x0092:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x009e }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.NOT_SHIPPABLE_POPUP     // Catch:{ NoSuchFieldError -> 0x009e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009e }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009e }
            L_0x009e:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x00aa }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.TERMS_OF_USE_UPDATE     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType     // Catch:{ NoSuchFieldError -> 0x00b6 }
                com.contextlogic.wish.api.model.WishLoginAction$ActionType r1 = com.contextlogic.wish.api.model.WishLoginAction.ActionType.VIDEO_POPUP     // Catch:{ NoSuchFieldError -> 0x00b6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b6 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b6 }
            L_0x00b6:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.ServiceFragment.AnonymousClass47.<clinit>():void");
        }
    }

    public interface PermissionCallback {
        void onPermissionDenied();

        void onPermissionGranted();
    }

    /* access modifiers changed from: protected */
    public final void initialize() {
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mResultCodeCallbacks = new SparseArray<>();
        this.mResultCallbacks = new ArrayList<>();
        this.mResultCallbackLookup = new HashMap<>();
        this.mRandom = new Random();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mIsActive = false;
        this.mBaseDialogCallback = null;
        this.mPageViewTracked = false;
        this.mGoogleAppIndexTracked = false;
        this.mFeedLoaded = false;
        this.mOnFeedLoadedTasks = new ConcurrentLinkedQueue<>();
        this.mLoginActionTasks = new ArrayList();
        initializeServices();
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        this.mGetProductService = new GetProductService();
        this.mAuthenticationService = new AuthenticationService();
        this.mGetLoginActionService = new GetLoginActionService();
        this.mGetBraintreeClientTokenService = new GetBraintreeClientTokenService();
        this.mNeverShowInviteCouponService = new NeverShowInviteCouponService();
        this.mClipboardCouponPopupDialogClaimService = new ClipboardCouponPopupDialogClaimService();
        this.mGetRotatingPromoNotiSpec = new GetRotatingPromoNotificationSpecService();
        this.mUpdateRotatingPromoTagsService = new UpdateRotatingPromoNotificationTagsService();
        this.mUpdateCartService = new UpdateCartService();
        this.mMarkRedDotNotificationsSeenService = new MarkRedDotNotificationsSeenService();
        setupPaymentsClient();
    }

    public void onAuthenticationVerifiedResume() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                WishNotification wishNotification = (WishNotification) IntentUtil.getParcelableExtra(a.getIntent(), "ExtraSourceNotification");
                if (wishNotification != null) {
                    a.setIsFromNotification(true);
                    new ViewNotificationService().requestService(wishNotification.getNotificationNumber(), wishNotification.getBucketNumber(), null, null);
                    new ClickNotificationService().requestService(wishNotification.getNotificationNumber(), wishNotification.getBucketNumber(), true, null, null);
                    StatusDataCenter.getInstance().decrementUnviewedNotificationCount();
                    StatusDataCenter.getInstance().refresh();
                    IntentUtil.putParcelableExtra(a.getIntent(), "ExtraSourceNotification", null);
                }
            }
        });
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if (!ServiceFragment.this.mPageViewTracked) {
                    if (a.getGoogleAnalyticsPageViewType() != null) {
                        GoogleAnalyticsLogger.getInstance().trackPageView(a.getGoogleAnalyticsPageViewType());
                    }
                    if (a.getWishAnalyticsPageViewType() != null) {
                        WishAnalyticsLogger.trackEvent(a.getWishAnalyticsPageViewType());
                    }
                    ServiceFragment.this.mPageViewTracked = true;
                }
            }
        });
    }

    public void handleActivityResult(int i, int i2, Intent intent) {
        synchronized (this.mResultCallbacks) {
            Iterator it = this.mResultCallbacks.iterator();
            while (it.hasNext()) {
                final ActivityResultCallback activityResultCallback = (ActivityResultCallback) it.next();
                Handler handler = this.mHandler;
                final int i3 = i;
                final int i4 = i2;
                final Intent intent2 = intent;
                AnonymousClass3 r3 = new Runnable() {
                    public void run() {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                activityResultCallback.onActivityResult(a, i3, i4, intent2);
                            }
                        });
                    }
                };
                handler.post(r3);
            }
        }
        synchronized (this.mResultCodeCallbacks) {
            final ActivityResultCallback activityResultCallback2 = (ActivityResultCallback) this.mResultCodeCallbacks.get(i);
            if (activityResultCallback2 != null) {
                Handler handler2 = this.mHandler;
                final int i5 = i;
                final int i6 = i2;
                final Intent intent3 = intent;
                AnonymousClass4 r2 = new Runnable() {
                    public void run() {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                activityResultCallback2.onActivityResult(a, i5, i6, intent3);
                            }
                        });
                    }
                };
                handler2.post(r2);
            }
        }
    }

    public void onStart() {
        super.onStart();
        startGoogleAppIndexTracking();
    }

    public void onStop() {
        stopGoogleAppIndexTracking();
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        cancelAllRequests();
    }

    public void trackGoogleAppIndexAction(WishGoogleAppIndexingData wishGoogleAppIndexingData) {
        this.mGoogleAppIndexAction = wishGoogleAppIndexingData.toAppIndexAction();
        this.mGoogleAppIndexIndexable = wishGoogleAppIndexingData.toAppIndexIndexable();
        startGoogleAppIndexTracking();
    }

    private void startGoogleAppIndexTracking() {
        if (!this.mGoogleAppIndexTracked && this.mGoogleAppIndexAction != null) {
            this.mGoogleAppIndexTracked = true;
            if (this.mGoogleAppIndexIndexable != null) {
                FirebaseAppIndex.getInstance().update(this.mGoogleAppIndexIndexable).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                        Crashlytics.logException(exc);
                    }
                });
            }
            FirebaseUserActions.getInstance().start(this.mGoogleAppIndexAction).addOnFailureListener(new OnFailureListener() {
                public void onFailure(Exception exc) {
                    Crashlytics.logException(exc);
                }
            });
        }
    }

    private void stopGoogleAppIndexTracking() {
        if (this.mGoogleAppIndexAction != null && this.mGoogleAppIndexTracked) {
            FirebaseUserActions.getInstance().end(this.mGoogleAppIndexAction);
            this.mGoogleAppIndexAction = null;
        }
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        cancelAppForegroundedChecks();
        this.mAuthenticationService.cancelAllRequests();
        this.mGetBraintreeClientTokenService.cancelAllRequests();
        if (this.mBraintreeFragment != null) {
            BraintreeManager.getInstance().clearBraintreeListeners(this.mBraintreeFragment);
        }
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void cancelAppForegroundedChecks() {
        this.mDeferredAppLinkRequestTag = null;
        this.mGetProductService.cancelAllRequests();
        this.mGetLoginActionService.cancelAllRequests();
        this.mNeverShowInviteCouponService.cancelAllRequests();
        this.mClipboardCouponPopupDialogClaimService.cancelAllRequests();
        this.mGetRotatingPromoNotiSpec.cancelAllRequests();
        this.mUpdateRotatingPromoTagsService.cancelAllRequests();
        this.mUpdateCartService.cancelAllRequests();
        this.mMarkRedDotNotificationsSeenService.cancelAllRequests();
    }

    public void withBraintreeFragment(final BraintreeFragmentCallback braintreeFragmentCallback) {
        if (this.mBraintreeFragment != null) {
            braintreeFragmentCallback.onBraintreeFragmentLoaded(this.mBraintreeFragment);
        } else {
            this.mGetBraintreeClientTokenService.requestService(new SuccessCallback() {
                public void onSuccess(final String str) {
                    ServiceFragment.this.withActivity(new ActivityTask<A>() {
                        public void performTask(A a) {
                            try {
                                ServiceFragment.this.mBraintreeFragment = BraintreeFragment.newInstance(a, str);
                                ServiceFragment.this.mBraintreeFragment.setRetainInstance(true);
                                braintreeFragmentCallback.onBraintreeFragmentLoaded(ServiceFragment.this.mBraintreeFragment);
                            } catch (Exception unused) {
                                braintreeFragmentCallback.onBraintreeFragmentLoadFailed(null);
                            }
                        }
                    });
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    braintreeFragmentCallback.onBraintreeFragmentLoadFailed(str);
                }
            });
        }
    }

    private void setupPaymentsClient() {
        Builder builder = new Builder();
        if (ServerConfig.getInstance().getServerHost().equals("testing.wish.com")) {
            builder.setEnvironment(3);
        } else {
            builder.setEnvironment(1);
        }
        this.mPaymentsClient = Wallet.getPaymentsClient(getBaseActivity(), builder.build());
    }

    public void loadPaymentData(PaymentDataRequest paymentDataRequest, int i) {
        if (this.mPaymentsClient == null) {
            setupPaymentsClient();
        }
        AutoResolveHelper.resolveTask(this.mPaymentsClient.loadPaymentData(paymentDataRequest), getBaseActivity(), i);
    }

    public void performAppForegroundedChecks() {
        performDeferredAppLinkForegroundCheck();
    }

    private void performDeferredAppLinkForegroundCheck() {
        this.mDeferredAppLinkRequestTag = new Object();
        if (PreferenceUtil.getBoolean("FacebookDeferredLinkChecked")) {
            final String string = PreferenceUtil.getString("CachedDeferredDeepLink");
            if (string != null) {
                withActivity(new ActivityTask<A>() {
                    public void performTask(A a) {
                        if (!DeepLinkManager.processDeepLink(a, new DeepLink(string))) {
                            ServiceFragment.this.performLoginActionCheck();
                        } else {
                            PreferenceUtil.setString("CachedDeferredDeepLink", null);
                        }
                    }
                });
            } else {
                performLoginActionCheck();
            }
            return;
        }
        final Object obj = this.mDeferredAppLinkRequestTag;
        AppLinkData.fetchDeferredAppLinkData(WishApplication.getInstance(), FacebookManager.getAppId(), new CompletionHandler() {
            public void onDeferredAppLinkDataFetched(final AppLinkData appLinkData) {
                ServiceFragment.this.getHandler().post(new Runnable() {
                    public void run() {
                        PreferenceUtil.setBoolean("FacebookDeferredLinkChecked", true);
                        if (obj != ServiceFragment.this.mDeferredAppLinkRequestTag) {
                            if (!(appLinkData == null || appLinkData.getTargetUri() == null)) {
                                PreferenceUtil.setString("CachedDeferredDeepLink", appLinkData.getTargetUri().toString());
                            }
                            return;
                        }
                        if (appLinkData == null || appLinkData.getTargetUri() == null) {
                            ServiceFragment.this.performLoginActionCheck();
                        } else {
                            new LogSignupDeepLinkService().requestService(appLinkData.getTargetUri().toString(), null, null);
                            ServiceFragment.this.withActivity(new ActivityTask<A>() {
                                public void performTask(A a) {
                                    if (!DeepLinkManager.processDeepLink(a, new DeepLink(appLinkData.getTargetUri()))) {
                                        ServiceFragment.this.performLoginActionCheck();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void performLoginActionCheck() {
        this.mGetLoginActionService.requestService(new GetLoginActionService.SuccessCallback() {
            public void onSuccess(final WishLoginAction wishLoginAction) {
                if (wishLoginAction.getActionTypesArray() != null) {
                    for (ActionType ordinal : wishLoginAction.getActionTypesArray()) {
                        switch (AnonymousClass47.$SwitchMap$com$contextlogic$wish$api$model$WishLoginAction$ActionType[ordinal.ordinal()]) {
                            case 1:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.performLoginPopupAction(wishLoginAction.getWishLoginActionPopup());
                                    }
                                });
                                break;
                            case 2:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.performLoginDeepLinkAction(wishLoginAction);
                                    }
                                });
                                break;
                            case 3:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.performLoginPromotionAction(wishLoginAction.getWishLoginActionPromotion());
                                    }
                                });
                                break;
                            case 4:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.performLoginUpdateAppAction(wishLoginAction.getWishLoginActionUpdateApp());
                                    }
                                });
                                break;
                            case 5:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.performLoginRateAppAction(wishLoginAction.getWishLoginActionRateApp());
                                    }
                                });
                                break;
                            case 6:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.showInviteCouponDialogFragment();
                                    }
                                });
                                break;
                            case 7:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.showClipboardCouponDialogFragment(wishLoginAction.getWishCouponPopup());
                                    }
                                });
                                break;
                            case 8:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.whenFeedLoaded(new Runnable() {
                                            public void run() {
                                                ServiceFragment.this.showUgcFeedbackDialogFragment(wishLoginAction.getWishUgcFeedback());
                                            }
                                        });
                                    }
                                });
                                break;
                            case 9:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.whenFeedLoaded(new Runnable() {
                                            public void run() {
                                                ServiceFragment.this.handleDailyLoginBonusDialogFragment(wishLoginAction.getWishDailyLoginStampSpec(), ExperimentDataCenter.getInstance().shouldSeeDailyLoginAfterNewUserSplash());
                                            }
                                        });
                                    }
                                });
                                break;
                            case 10:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.showWishStarPopup();
                                    }
                                });
                                break;
                            case 11:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.setCameraDialogRunnable();
                                    }
                                });
                                ServiceFragment.this.setCameraDialogRunnable();
                                break;
                            case 12:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.showCouponPopup(wishLoginAction.getLoggedOutCountdownCoupon());
                                    }
                                });
                                break;
                            case 13:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.showNotShippableCountryPopup(wishLoginAction.getWishNotShippableCountryPopupSpec());
                                    }
                                });
                                break;
                            case 14:
                                if (!ExperimentDataCenter.getInstance().shouldShowTermsOfUseUpdateLoginAction()) {
                                    break;
                                } else {
                                    ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                        public void run() {
                                            ServiceFragment.this.showTermsOfUseUpdatePopup(wishLoginAction.getWishLoginActionPopup());
                                        }
                                    });
                                    break;
                                }
                            case 15:
                                ServiceFragment.this.mLoginActionTasks.add(new Runnable() {
                                    public void run() {
                                        ServiceFragment.this.showVideoPopup(wishLoginAction.getVideoPopupSpec());
                                    }
                                });
                                break;
                        }
                    }
                    ServiceFragment.this.executeNextLoginAction();
                }
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }

    public void setCameraDialogRunnable() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if (a.getUiFragment("FragmentTagMainContent") instanceof ProductFeedFragment) {
                    ServiceFragment.this.withUiFragment(new UiTask<A, ProductFeedFragment>() {
                        public void performTask(A a, ProductFeedFragment productFeedFragment) {
                            productFeedFragment.setCameraDialogRunnable();
                        }
                    }, "FragmentTagMainContent");
                }
            }
        });
    }

    public void showWishStarPopup() {
        withActivity(new ActivityTask<A>() {
            public void performTask(BaseActivity baseActivity) {
                final WishStarDialogFragment createOneTimePopup = WishStarDialogFragment.createOneTimePopup();
                ServiceFragment.this.withActivity(new ActivityTask<A>() {
                    public void performTask(A a) {
                        a.startDialog(createOneTimePopup);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void performLoginPopupAction(WishLoginActionPopup wishLoginActionPopup) {
        if (wishLoginActionPopup != null) {
            performLoginPopupAction(wishLoginActionPopup.getTitle(), wishLoginActionPopup.getDescription(), wishLoginActionPopup.getConfirmButtonText(), new BaseDialogCallback() {
                public void onCancel(BaseDialogFragment baseDialogFragment) {
                }

                public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                    if (i == 0) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_LOG_IN_ACTION_POPUP_CONFIRM);
                    }
                }
            });
        }
    }

    private void performLoginPopupAction(String str, CharSequence charSequence, String str2, BaseDialogCallback baseDialogCallback) {
        if (str != null || charSequence != null || str2 != null) {
            final String str3 = str2;
            final String str4 = str;
            final CharSequence charSequence2 = charSequence;
            final BaseDialogCallback baseDialogCallback2 = baseDialogCallback;
            AnonymousClass16 r0 = new ActivityTask<A>() {
                public void performTask(A a) {
                    String string;
                    if (str3 != null) {
                        string = str3;
                    } else {
                        string = a.getString(R.string.ok);
                    }
                    MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, string, R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(multiButtonDialogChoice);
                    a.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(str4).setSubTitle(charSequence2).setButtons(arrayList).hideXButton().build(), baseDialogCallback2);
                }
            };
            withActivity(r0);
        }
    }

    /* access modifiers changed from: private */
    public void performLoginDeepLinkAction(WishLoginAction wishLoginAction) {
        String deeplink = wishLoginAction.getWishLoginActionDeepLink().getDeeplink();
        final DeepLink deepLink = deeplink != null ? new DeepLink(deeplink) : null;
        if (deepLink != null) {
            withActivity(new ActivityTask<A>() {
                public void performTask(A a) {
                    DeepLinkManager.processDeepLink(a, deepLink, true);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void performLoginPromotionAction(final WishPromotionSpec wishPromotionSpec) {
        if (wishPromotionSpec != null) {
            withActivity(new ActivityTask<A>() {
                public void performTask(A a) {
                    SplashPromotionDialogFragment createDialog = SplashPromotionDialogFragment.createDialog(wishPromotionSpec.getWishPromotionDeal());
                    if (createDialog != null) {
                        a.startDialog(createDialog);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void performLoginUpdateAppAction(final WishLoginActionUpdateApp wishLoginActionUpdateApp) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                A a2 = a;
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, a2.getString(R.string.update_app), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(3, a2.getString(R.string.remind_me_later), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                arrayList.add(multiButtonDialogChoice2);
                a2.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(wishLoginActionUpdateApp.getTitle()).setSubTitle(wishLoginActionUpdateApp.getDescription()).setButtons(arrayList).hideXButton().build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_UPDATE_APP_UPDATE);
                            ServiceFragment.this.withActivity(new ActivityTask<A>() {
                                public void performTask(A a) {
                                    StoreUtil.startStoreActivity(a);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void showInviteCouponDialogFragment() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.startDialog(new InviteCouponPopupDialogFragment());
            }
        });
    }

    public void setNeverShowInviteCoupon() {
        this.mNeverShowInviteCouponService.requestService();
    }

    public void showClipboardCouponDialogFragment(final WishClipboardCouponPopupDialogSpec wishClipboardCouponPopupDialogSpec) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                ClipboardCouponPopupDialogFragment clipboardCouponPopupDialogFragment = new ClipboardCouponPopupDialogFragment();
                clipboardCouponPopupDialogFragment.setSpecifications(wishClipboardCouponPopupDialogSpec);
                a.startDialog(clipboardCouponPopupDialogFragment);
            }
        });
    }

    public void showUgcFeedbackDialogFragment(final WishLoginActionUgcFeedback wishLoginActionUgcFeedback) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                UgcFeedbackDialogFragment createUgcDialogFragment = UgcFeedbackDialogFragment.createUgcDialogFragment(wishLoginActionUgcFeedback);
                if (createUgcDialogFragment != null) {
                    a.startDialog(createUgcDialogFragment);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showTermsOfUseUpdatePopup(WishLoginActionPopup wishLoginActionPopup) {
        if (wishLoginActionPopup != null) {
            String title = wishLoginActionPopup.getTitle();
            String description = wishLoginActionPopup.getDescription();
            performLoginPopupAction(title, TermsPolicyTextView.generateTermsPolicyText(getContext(), 1, getString(R.string.terms_policy_placeholder_login_action_popup, "%1$s", description)), wishLoginActionPopup.getConfirmButtonText(), new BaseDialogCallback() {
                public void onCancel(BaseDialogFragment baseDialogFragment) {
                }

                public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                    if (i == 0) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_TERMS_OF_USE_UPDATE_LOGIN_ACTION_CONFIRM);
                    }
                }
            });
        }
    }

    public void handleDailyLoginBonusDialogFragment(final WishDailyLoginStampSpec wishDailyLoginStampSpec, final boolean z) {
        withUiFragment(new UiTask<BaseActivity, UiFragment>() {
            public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                if (wishDailyLoginStampSpec != null) {
                    if (wishDailyLoginStampSpec.showStampRow()) {
                        if (z) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_LOGIN_AFTER_NEW_USER_SPLASH);
                        } else {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_DAILY_LOGIN_BONUS_STAMP_POPUP);
                        }
                        if (uiFragment == null || !(uiFragment instanceof Callback)) {
                            baseActivity.startDialog(DailyLoginBonusStampPopupDialog.createDailyLoginBonusStampPopupDialog(wishDailyLoginStampSpec, false));
                        } else {
                            ((Callback) uiFragment).showStampRowDialog(wishDailyLoginStampSpec, z);
                        }
                    } else if (ExperimentDataCenter.getInstance().shouldShowDayPrizes() && ExperimentDataCenter.getInstance().shouldSeeNonAnimatedDailyLoginAfterNewUserSplash()) {
                        if (ExperimentDataCenter.getInstance().shouldSeeNonAnimatedDailyLoginAfterNewUserSplash()) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_LOGIN_AFTER_NEW_USER_SPLASH_NO_ANIMATION);
                        }
                        DailyLoginBonusDialogFragment createDailyLoginBonusDialogFragment = DailyLoginBonusDialogFragment.createDailyLoginBonusDialogFragment(wishDailyLoginStampSpec);
                        if (wishDailyLoginStampSpec.getCouponWon()) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_DAILY_LOGIN_BONUS_FINAL_POPUP);
                        } else {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_DAILY_LOGIN_BONUS_FULL_POPUP);
                        }
                        baseActivity.startDialog(createDailyLoginBonusDialogFragment);
                    }
                }
            }
        }, "FragmentTagMainContent");
    }

    public void handleDailyLoginBonusCouponDialogFragment(final WishDailyLoginStampSpec wishDailyLoginStampSpec) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.startDialog(DailyLoginBonusCouponDialogFragment.createDailyLoginBonusCouponDialogFragment(wishDailyLoginStampSpec));
            }
        });
    }

    public void claimClipboardCouponCode(final String str, final ClipboardCouponPopupDialogClaimService.SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                ServiceFragment.this.mClipboardCouponPopupDialogClaimService.requestService(str, successCallback, defaultFailureCallback);
            }
        });
    }

    /* access modifiers changed from: private */
    public void performLoginRateAppAction(final WishLoginActionRateApp wishLoginActionRateApp) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                A a2 = a;
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, a2.getString(R.string.rate_app), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(2, a2.getString(R.string.no_thanks), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                arrayList.add(multiButtonDialogChoice2);
                MultiButtonDialogChoice multiButtonDialogChoice3 = new MultiButtonDialogChoice(3, a2.getString(R.string.remind_me_later), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                arrayList.add(multiButtonDialogChoice3);
                a2.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(wishLoginActionRateApp.getTitle()).setSubTitle(wishLoginActionRateApp.getDescription()).setButtons(arrayList).hideXButton().build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATE_APP_RATE);
                            ServiceFragment.this.withActivity(new ActivityTask<A>() {
                                public void performTask(A a) {
                                    StoreUtil.startStoreActivity(a);
                                }
                            });
                        } else if (i == 3) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATE_APP_REMIND_LATER);
                        } else if (i == 2) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RATE_APP_NO_THANKS);
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void showCouponPopup(final LoggedOutCountdownCoupon loggedOutCountdownCoupon) {
        if (loggedOutCountdownCoupon != null) {
            withActivity(new ActivityTask<A>() {
                public void performTask(A a) {
                    CountdownCouponDialogFragment createCountdownCouponDialogFragment = CountdownCouponDialogFragment.createCountdownCouponDialogFragment(loggedOutCountdownCoupon);
                    if (createCountdownCouponDialogFragment != null) {
                        a.startDialog(createCountdownCouponDialogFragment);
                    }
                }
            });
        }
    }

    public void showNotShippableCountryPopup(final WishNotShippableCountryPopupSpec wishNotShippableCountryPopupSpec) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.startDialog(NotShippableCountryPopupDialogFragment.createNotShippableCountryPopupDialogFragment(wishNotShippableCountryPopupSpec));
            }
        });
    }

    /* access modifiers changed from: private */
    public void showVideoPopup(final WishVideoPopupSpec wishVideoPopupSpec) {
        if (wishVideoPopupSpec != null) {
            withActivity(new ActivityTask<A>() {
                public void performTask(A a) {
                    VideoPopupDialogFragment createDialog = VideoPopupDialogFragment.createDialog(wishVideoPopupSpec);
                    if (createDialog != null) {
                        a.startDialog(createDialog);
                    }
                }
            });
        }
    }

    public void login(LoginMode loginMode) {
        login(loginMode, new LoginContext());
    }

    public void login(final LoginMode loginMode, final LoginContext loginContext) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.showLoadingDialog();
                ServiceFragment.this.mAuthenticationService.login(a, loginContext, loginMode, new AuthenticationCallback() {
                    public void onSuccess(String str, final boolean z, final SignupFlowContext signupFlowContext) {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                a.finishLogin(z, signupFlowContext, loginMode);
                            }
                        });
                    }

                    public void onFailure(final ErrorContext errorContext) {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                if (!ExperimentDataCenter.getInstance().shouldSeeFreeGiftTextOnSignup() || errorContext.errorCode != 10 || loginContext.email == null || !loginContext.createNewUser) {
                                    a.hideLoadingDialog();
                                    ServiceFragment.this.mAuthenticationService.showErrorDialog(a, errorContext);
                                    return;
                                }
                                a.hideLoadingDialog();
                                Intent intent = new Intent();
                                intent.setClass(a, SignInActivity.class);
                                intent.putExtra("ExtraPrefilledEmailAddress", loginContext.email);
                                intent.putExtra("ExtraShowFreeGiftErrorMessage", true);
                                a.startActivity(intent);
                            }
                        });
                    }

                    public void onCancel() {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                a.hideLoadingDialog();
                            }
                        });
                    }
                });
            }
        });
    }

    public void ensureLoggedIn() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                ServiceFragment.this.mAuthenticationService.ensureLoggedIn(a, new AuthenticationCallback() {
                    public void onSuccess(String str, boolean z, SignupFlowContext signupFlowContext) {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                a.handleAuthenticationStateVerified();
                            }
                        });
                    }

                    public void onFailure(ErrorContext errorContext) {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                a.handleLogoutRequired();
                            }
                        });
                    }

                    public void onCancel() {
                        ServiceFragment.this.withActivity(new ActivityTask<A>() {
                            public void performTask(A a) {
                                a.handleLogoutRequired();
                            }
                        });
                    }
                });
            }
        });
    }

    public void attemptSmartLockLogin(BaseActivity baseActivity) {
        SmartLockManager.getInstance().requestCredentials(new SmartLockManager.Callback() {
            public void onSuccess(Credential credential) {
                ServiceFragment.this.handleCredentialsRetrieved(credential);
            }

            public void onFailure() {
                ServiceFragment.this.handleCredentialsNotRetrieved();
            }

            public void withActivityForResolution(final ResolutionActivityTask resolutionActivityTask) {
                ServiceFragment.this.withActivity(new ActivityTask<A>() {
                    public void performTask(A a) {
                        resolutionActivityTask.performTask(a);
                    }
                });
            }
        }, false);
    }

    /* access modifiers changed from: protected */
    public void handleCredentialsRetrieved(final Credential credential) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.hideLoadingDialog();
                String accountType = credential.getAccountType();
                if (accountType != null && accountType.equals("https://accounts.google.com")) {
                    ServiceFragment.this.handleGoogleSmartLogin(credential);
                } else if (accountType != null && accountType.equals("https://www.facebook.com")) {
                    ServiceFragment.this.handleFacebookSmartLogin(credential);
                } else if (accountType == null) {
                    ServiceFragment.this.handleEmailSmartLogin(credential);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleCredentialsNotRetrieved() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.hideLoadingDialog();
            }
        });
    }

    public void handleGoogleSmartLogin(Credential credential) {
        GoogleSignInApiClient.getInstance().updateSignInOptions(new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestServerAuthCode(WishApplication.getInstance().getString(R.string.google_server_key)).setAccountName(credential.getId()).build());
        login(LoginMode.GOOGLE, new LoginContext());
    }

    public void handleFacebookSmartLogin(Credential credential) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                ServiceFragment.this.login(LoginMode.FACEBOOK, new LoginContext());
            }
        });
    }

    public void handleEmailSmartLogin(Credential credential) {
        LoginContext loginContext = new LoginContext();
        loginContext.email = credential.getId();
        loginContext.password = credential.getPassword();
        login(LoginMode.EMAIL, loginContext);
    }

    public AuthenticationService getAuthenticationService() {
        return this.mAuthenticationService;
    }

    public void addResultCallback(ActivityResultCallback activityResultCallback, Object obj) {
        synchronized (this.mResultCallbacks) {
            if (!this.mResultCallbacks.contains(activityResultCallback) && (obj == null || !this.mResultCallbackLookup.containsKey(obj))) {
                this.mResultCallbacks.add(activityResultCallback);
                if (obj == null) {
                    this.mResultCallbackLookup.put(obj, activityResultCallback);
                }
            }
        }
    }

    public int addResultCodeCallback(ActivityResultCallback activityResultCallback) {
        int nextInt;
        synchronized (this.mResultCodeCallbacks) {
            do {
                nextInt = this.mRandom.nextInt(8000) + 1000;
            } while (this.mResultCodeCallbacks.get(nextInt) != null);
            this.mResultCodeCallbacks.put(nextInt, activityResultCallback);
        }
        return nextInt;
    }

    public void removeResultCallbackTag(Object obj) {
        synchronized (this.mResultCallbacks) {
            ActivityResultCallback activityResultCallback = (ActivityResultCallback) this.mResultCallbackLookup.get(obj);
            if (activityResultCallback != null) {
                this.mResultCallbackLookup.remove(obj);
                this.mResultCallbacks.remove(activityResultCallback);
            }
        }
    }

    public void removeResultCodeCallback(int i) {
        synchronized (this.mResultCodeCallbacks) {
            this.mResultCodeCallbacks.remove(i);
        }
    }

    public void onDialogCancel(BaseDialogFragment baseDialogFragment) {
        if (this.mBaseDialogCallback != null) {
            BaseDialogCallback baseDialogCallback = this.mBaseDialogCallback;
            this.mBaseDialogCallback = null;
            baseDialogCallback.onCancel(baseDialogFragment);
        }
        executeNextLoginAction();
    }

    public void onDialogSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
        if (this.mBaseDialogCallback != null) {
            BaseDialogCallback baseDialogCallback = this.mBaseDialogCallback;
            this.mBaseDialogCallback = null;
            baseDialogCallback.onSelection(baseDialogFragment, i, bundle);
        }
        executeNextLoginAction();
    }

    public void setDialogCallback(BaseDialogCallback baseDialogCallback) {
        this.mBaseDialogCallback = baseDialogCallback;
    }

    public void setPermissionCallback(PermissionCallback permissionCallback) {
        this.mPermissionCallback = permissionCallback;
    }

    public void permissionGranted() {
        if (this.mPermissionCallback != null) {
            this.mPermissionCallback.onPermissionGranted();
        }
    }

    public void permissionDenied() {
        if (this.mPermissionCallback != null) {
            this.mPermissionCallback.onPermissionDenied();
        }
    }

    /* access modifiers changed from: protected */
    public void handleFeedLoaded() {
        this.mFeedLoaded = true;
        while (true) {
            Runnable runnable = (Runnable) this.mOnFeedLoadedTasks.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    public void whenFeedLoaded(Runnable runnable) {
        if (this.mFeedLoaded) {
            runnable.run();
        } else {
            this.mOnFeedLoadedTasks.add(runnable);
        }
    }

    public void executeNextLoginAction() {
        if (this.mLoginActionTasks.size() > 0 && this.mLoginActionTasks.get(0) != null) {
            ((Runnable) this.mLoginActionTasks.remove(0)).run();
        }
    }

    public void requestRotatingPromoNotiSpec() {
        this.mGetRotatingPromoNotiSpec.requestService(new GetRotatingPromoNotificationSpecService.SuccessCallback() {
            public void onSuccess(final WishRotatingSaleNotificationSpec wishRotatingSaleNotificationSpec) {
                ServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, PromoRotatingNotiDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, PromoRotatingNotiDialogFragment promoRotatingNotiDialogFragment) {
                        promoRotatingNotiDialogFragment.handleSpecLoadSuccess(wishRotatingSaleNotificationSpec);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, PromoRotatingNotiDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, PromoRotatingNotiDialogFragment promoRotatingNotiDialogFragment) {
                        promoRotatingNotiDialogFragment.handleFailure(str);
                    }
                });
            }
        });
    }

    public void updateRotatingPromoNotiTags(ArrayList<String> arrayList) {
        this.mUpdateRotatingPromoTagsService.requestService(arrayList, new DefaultSuccessCallback() {
            public void onSuccess() {
                ServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, PromoRotatingNotiDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, PromoRotatingNotiDialogFragment promoRotatingNotiDialogFragment) {
                        promoRotatingNotiDialogFragment.handleTagUpdateSuccess();
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, PromoRotatingNotiDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, PromoRotatingNotiDialogFragment promoRotatingNotiDialogFragment) {
                        promoRotatingNotiDialogFragment.handleFailure(str);
                    }
                });
            }
        });
    }

    public void shareEventTriggered() {
        new ShareProductEventService().requestService(null, null);
    }

    public void showLoadingSpinner() {
        withActivity(new ActivityTask<A>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.showLoadingDialog();
            }
        });
    }

    public void hideLoadingSpinner() {
        withActivity(new ActivityTask<A>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.hideLoadingDialog();
            }
        });
    }

    public void showErrorMessage(final String str) {
        withVerifiedAuthenticationActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(TextUtils.isEmpty(str) ? a.getString(R.string.general_error) : str));
            }
        });
    }

    public void addItemToCart(WishProduct wishProduct, String str, String str2, String str3, final WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        showLoadingSpinner();
        this.mUpdateCartService.requestService(wishProduct.getCommerceProductId(), str, str2, 1, true, false, str3, new UpdateCartService.SuccessCallback() {
            public void onSuccess(final WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec) {
                ServiceFragment.this.withActivity(new ActivityTask<A>() {
                    public void performTask(BaseActivity baseActivity) {
                        ServiceFragment.this.hideLoadingSpinner();
                        Iterator it = wishCart.getItems().iterator();
                        if (it.hasNext()) {
                            final WishCartItem wishCartItem = (WishCartItem) it.next();
                            if (wishCart.getAddToCartOfferApplied() == null || wishCart.getAddToCartOfferApplied().isExpired()) {
                                ServiceFragment.this.showItemAddedDialogFragment(wishCartItem, 1, wishLocalizedCurrencyValue);
                                return;
                            }
                            AddToCartOfferDialogFragment createAddToCartOfferDialog = AddToCartOfferDialogFragment.createAddToCartOfferDialog(wishCart.getAddToCartOfferApplied());
                            if (createAddToCartOfferDialog != null) {
                                baseActivity.startDialog(createAddToCartOfferDialog, new BaseDialogCallback() {
                                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                    }

                                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                                        ServiceFragment.this.showItemAddedDialogFragment(wishCartItem, 1, wishLocalizedCurrencyValue);
                                    }
                                });
                            } else {
                                ServiceFragment.this.showItemAddedDialogFragment(wishCartItem, 1, wishLocalizedCurrencyValue);
                            }
                        }
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ADD_TO_CART_WITHOUT_CART_REDIRECT_FAILURE);
                ServiceFragment.this.hideLoadingSpinner();
                ServiceFragment.this.showErrorMessage(ServiceFragment.this.getString(R.string.could_not_add_to_cart));
            }
        });
    }

    public void showItemAddedDialogFragment(WishCartItem wishCartItem, int i, WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        final ItemAddedDialogFragment createItemAddedDialogFragment = ItemAddedDialogFragment.createItemAddedDialogFragment(wishCartItem, i, wishLocalizedCurrencyValue);
        if (createItemAddedDialogFragment != null) {
            withActivity(new ActivityTask<A>() {
                public void performTask(BaseActivity baseActivity) {
                    baseActivity.startDialog(createItemAddedDialogFragment, new BaseDialogCallback() {
                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        }

                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                            ServiceFragment.this.withActivity(new ActivityTask<A>() {
                                public void performTask(BaseActivity baseActivity) {
                                    baseActivity.getActionBarManager().updateCartIcon(true);
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    public void markRedDotNotificationsSeenRemotely(DefaultSuccessCallback defaultSuccessCallback, DefaultFailureCallback defaultFailureCallback) {
        this.mMarkRedDotNotificationsSeenService.requestService(defaultSuccessCallback, defaultFailureCallback);
    }
}
