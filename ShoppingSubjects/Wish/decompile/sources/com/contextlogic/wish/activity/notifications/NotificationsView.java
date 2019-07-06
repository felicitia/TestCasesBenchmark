package com.contextlogic.wish.activity.notifications;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.api.model.WishNotification.NotificationDisplayType;
import com.contextlogic.wish.api.model.WishNotification.NotificationTargetType;
import com.contextlogic.wish.api.model.WishTag;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.scrollview.ScrollRestorable;
import com.contextlogic.wish.util.NotificationUtil;
import java.util.ArrayList;

public class NotificationsView extends LoadingPageView implements ImageRestorable, LoadingPageManager, ScrollRestorable {
    private boolean mAutoscrolled;
    /* access modifiers changed from: private */
    public DrawerActivity mBaseActivity;
    /* access modifiers changed from: private */
    public int mBucketNumber;
    private int mCurrentIndex;
    private FrameLayout mHeaderContainer;
    private NotificationsAdapter mListAdapter;
    /* access modifiers changed from: private */
    public ListView mListView;
    private LoadingFooterView mLoadingFooter;
    private boolean mNeedsReload;
    /* access modifiers changed from: private */
    public boolean mNoMoreItems;
    /* access modifiers changed from: private */
    public ArrayList<WishNotification> mNotifications = new ArrayList<>();
    /* access modifiers changed from: private */
    public NotificationsFragment mNotificationsFragment;
    private SwipeRefreshLayout mRefresherView;
    /* access modifiers changed from: private */
    public WishTag mWishTag;

    /* renamed from: com.contextlogic.wish.activity.notifications.NotificationsView$11 reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType = new int[NotificationTargetType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType[] r0 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.Website     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.Product     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.Invite     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.Feed     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.BrandFeed     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x004b }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.Profile     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.Alert     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.Rate     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType     // Catch:{ NoSuchFieldError -> 0x006e }
                com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r1 = com.contextlogic.wish.api.model.WishNotification.NotificationTargetType.DeepLink     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.notifications.NotificationsView.AnonymousClass11.<clinit>():void");
        }
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.notifications_fragment_view;
    }

    public NotificationsView(DrawerActivity drawerActivity, NotificationsFragment notificationsFragment, WishTag wishTag, int i) {
        super(drawerActivity);
        this.mBaseActivity = drawerActivity;
        this.mNotificationsFragment = notificationsFragment;
        this.mWishTag = wishTag;
        this.mCurrentIndex = i;
        setLoadingPageManager(this);
    }

    public boolean isNoMoreItems() {
        return this.mNoMoreItems;
    }

    public void handleDestroy() {
        releaseImages();
    }

    private void refreshRowTimestamps() {
        if (this.mListAdapter != null) {
            this.mListAdapter.refreshTimestamps();
        }
    }

    public void handleNotificationClick(int i) {
        if (i < this.mNotifications.size()) {
            handleNotificationClick((WishNotification) this.mNotifications.get(i));
        }
    }

    public void handleNotificationClick(final WishNotification wishNotification) {
        this.mNotificationsFragment.withServiceFragment(new ServiceTask<BaseActivity, NotificationsServiceFragment>() {
            /* JADX WARNING: Removed duplicated region for block: B:55:0x0153  */
            /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void performTask(com.contextlogic.wish.activity.BaseActivity r4, com.contextlogic.wish.activity.notifications.NotificationsServiceFragment r5) {
                /*
                    r3 = this;
                    com.contextlogic.wish.api.model.WishNotification r0 = r3
                    r0.markClicked()
                    com.contextlogic.wish.api.model.WishNotification r0 = r3
                    int r0 = r0.getNotificationNumber()
                    com.contextlogic.wish.api.model.WishNotification r1 = r3
                    int r1 = r1.getBucketNumber()
                    r2 = 0
                    r5.handleNotificationClicked(r0, r1, r2)
                    int[] r5 = com.contextlogic.wish.activity.notifications.NotificationsView.AnonymousClass11.$SwitchMap$com$contextlogic$wish$api$model$WishNotification$NotificationTargetType
                    com.contextlogic.wish.api.model.WishNotification r0 = r3
                    com.contextlogic.wish.api.model.WishNotification$NotificationTargetType r0 = r0.getTargetType()
                    int r0 = r0.ordinal()
                    r5 = r5[r0]
                    r0 = 0
                    switch(r5) {
                        case 1: goto L_0x012d;
                        case 2: goto L_0x0108;
                        case 3: goto L_0x00f3;
                        case 4: goto L_0x00cb;
                        case 5: goto L_0x00a6;
                        case 6: goto L_0x0069;
                        case 7: goto L_0x0050;
                        case 8: goto L_0x0043;
                        case 9: goto L_0x0029;
                        default: goto L_0x0027;
                    }
                L_0x0027:
                    goto L_0x0151
                L_0x0029:
                    com.contextlogic.wish.api.model.WishNotification r5 = r3
                    java.lang.Object r5 = r5.getTarget()
                    if (r5 == 0) goto L_0x0151
                    com.contextlogic.wish.api.model.WishNotification r5 = r3
                    java.lang.Object r5 = r5.getTarget()
                    java.lang.String r5 = (java.lang.String) r5
                    com.contextlogic.wish.link.DeepLink r1 = new com.contextlogic.wish.link.DeepLink     // Catch:{ ClassCastException -> 0x0151 }
                    r1.<init>(r5)     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.link.DeepLinkManager.processDeepLink(r4, r1)     // Catch:{ ClassCastException -> 0x0151 }
                    goto L_0x0151
                L_0x0043:
                    android.content.Intent r0 = new android.content.Intent
                    java.lang.String r4 = "android.intent.action.VIEW"
                    android.net.Uri r5 = com.contextlogic.wish.util.StoreUtil.getStoreUri()
                    r0.<init>(r4, r5)
                    goto L_0x0151
                L_0x0050:
                    com.contextlogic.wish.api.model.WishNotification r5 = r3
                    java.lang.Object r5 = r5.getTarget()
                    if (r5 == 0) goto L_0x0151
                    com.contextlogic.wish.api.model.WishNotification r5 = r3     // Catch:{ ClassCastException -> 0x0151 }
                    java.lang.Object r5 = r5.getTarget()     // Catch:{ ClassCastException -> 0x0151 }
                    java.lang.String r5 = (java.lang.String) r5     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment r5 = com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.createMultiButtonOkDialog(r0, r5)     // Catch:{ ClassCastException -> 0x0151 }
                    r4.startDialog(r5)     // Catch:{ ClassCastException -> 0x0151 }
                    goto L_0x0151
                L_0x0069:
                    com.contextlogic.wish.api.model.WishNotification r4 = r3
                    java.lang.Object r4 = r4.getTarget()
                    if (r4 == 0) goto L_0x0151
                    com.contextlogic.wish.api.model.WishNotification r4 = r3     // Catch:{ ClassCastException -> 0x0151 }
                    java.lang.Object r4 = r4.getTarget()     // Catch:{ ClassCastException -> 0x0151 }
                    boolean r4 = r4 instanceof com.contextlogic.wish.api.model.WishUser     // Catch:{ ClassCastException -> 0x0151 }
                    if (r4 == 0) goto L_0x0088
                    com.contextlogic.wish.api.model.WishNotification r4 = r3     // Catch:{ ClassCastException -> 0x0151 }
                    java.lang.Object r4 = r4.getTarget()     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.api.model.WishUser r4 = (com.contextlogic.wish.api.model.WishUser) r4     // Catch:{ ClassCastException -> 0x0151 }
                    java.lang.String r4 = r4.getUserId()     // Catch:{ ClassCastException -> 0x0151 }
                    goto L_0x0090
                L_0x0088:
                    com.contextlogic.wish.api.datacenter.ProfileDataCenter r4 = com.contextlogic.wish.api.datacenter.ProfileDataCenter.getInstance()     // Catch:{ ClassCastException -> 0x0151 }
                    java.lang.String r4 = r4.getUserId()     // Catch:{ ClassCastException -> 0x0151 }
                L_0x0090:
                    android.content.Intent r5 = new android.content.Intent     // Catch:{ ClassCastException -> 0x0151 }
                    r5.<init>()     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ ClassCastException -> 0x00a3 }
                    java.lang.Class<com.contextlogic.wish.activity.profile.ProfileActivity> r1 = com.contextlogic.wish.activity.profile.ProfileActivity.class
                    r5.setClass(r0, r1)     // Catch:{ ClassCastException -> 0x00a3 }
                    java.lang.String r0 = com.contextlogic.wish.activity.profile.ProfileActivity.EXTRA_USER_ID     // Catch:{ ClassCastException -> 0x00a3 }
                    r5.putExtra(r0, r4)     // Catch:{ ClassCastException -> 0x00a3 }
                L_0x00a3:
                    r0 = r5
                    goto L_0x0151
                L_0x00a6:
                    com.contextlogic.wish.api.model.WishNotification r4 = r3
                    java.lang.Object r4 = r4.getTarget()
                    if (r4 == 0) goto L_0x0151
                    android.content.Intent r4 = new android.content.Intent     // Catch:{ ClassCastException -> 0x0151 }
                    r4.<init>()     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.application.WishApplication r5 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.Class<com.contextlogic.wish.activity.feed.brand.BrandFeedActivity> r0 = com.contextlogic.wish.activity.feed.brand.BrandFeedActivity.class
                    r4.setClass(r5, r0)     // Catch:{ ClassCastException -> 0x0150 }
                    com.contextlogic.wish.api.model.WishNotification r5 = r3     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.Object r5 = r5.getTarget()     // Catch:{ ClassCastException -> 0x0150 }
                    com.contextlogic.wish.api.model.WishBrandFilter r5 = (com.contextlogic.wish.api.model.WishBrandFilter) r5     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.String r0 = com.contextlogic.wish.activity.feed.brand.BrandFeedActivity.EXTRA_BRAND_FILTER     // Catch:{ ClassCastException -> 0x0150 }
                    com.contextlogic.wish.util.IntentUtil.putParcelableExtra(r4, r0, r5)     // Catch:{ ClassCastException -> 0x0150 }
                    goto L_0x0150
                L_0x00cb:
                    com.contextlogic.wish.api.model.WishNotification r4 = r3
                    java.lang.Object r4 = r4.getTarget()
                    if (r4 == 0) goto L_0x0151
                    android.content.Intent r4 = new android.content.Intent     // Catch:{ ClassCastException -> 0x0151 }
                    r4.<init>()     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.application.WishApplication r5 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.Class<com.contextlogic.wish.activity.feed.tag.TagFeedActivity> r0 = com.contextlogic.wish.activity.feed.tag.TagFeedActivity.class
                    r4.setClass(r5, r0)     // Catch:{ ClassCastException -> 0x0150 }
                    com.contextlogic.wish.api.model.WishNotification r5 = r3     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.Object r5 = r5.getTarget()     // Catch:{ ClassCastException -> 0x0150 }
                    com.contextlogic.wish.api.model.WishTag r5 = (com.contextlogic.wish.api.model.WishTag) r5     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.String r0 = com.contextlogic.wish.activity.feed.tag.TagFeedActivity.EXTRA_TAG_ID     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.String r5 = r5.getTagId()     // Catch:{ ClassCastException -> 0x0150 }
                    r4.putExtra(r0, r5)     // Catch:{ ClassCastException -> 0x0150 }
                    goto L_0x0150
                L_0x00f3:
                    android.content.Intent r0 = new android.content.Intent
                    r0.<init>()
                    com.contextlogic.wish.application.WishApplication r4 = com.contextlogic.wish.application.WishApplication.getInstance()
                    java.lang.Class<com.contextlogic.wish.activity.share.ShareActivity> r5 = com.contextlogic.wish.activity.share.ShareActivity.class
                    r0.setClass(r4, r5)
                    java.lang.String r4 = com.contextlogic.wish.activity.share.ShareActivity.EXTRA_USE_DEFAULT_INVITE_MESSAGE
                    r5 = 1
                    r0.putExtra(r4, r5)
                    goto L_0x0151
                L_0x0108:
                    com.contextlogic.wish.api.model.WishNotification r4 = r3
                    java.lang.Object r4 = r4.getTarget()
                    if (r4 == 0) goto L_0x0151
                    com.contextlogic.wish.api.model.WishNotification r4 = r3     // Catch:{ ClassCastException -> 0x0151 }
                    java.lang.Object r4 = r4.getTarget()     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.api.model.WishProduct r4 = (com.contextlogic.wish.api.model.WishProduct) r4     // Catch:{ ClassCastException -> 0x0151 }
                    if (r4 == 0) goto L_0x0151
                    android.content.Intent r5 = new android.content.Intent     // Catch:{ ClassCastException -> 0x0151 }
                    r5.<init>()     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ ClassCastException -> 0x00a3 }
                    java.lang.Class<com.contextlogic.wish.activity.productdetails.ProductDetailsActivity> r1 = com.contextlogic.wish.activity.productdetails.ProductDetailsActivity.class
                    r5.setClass(r0, r1)     // Catch:{ ClassCastException -> 0x00a3 }
                    com.contextlogic.wish.activity.productdetails.ProductDetailsActivity.addInitialProduct(r5, r4)     // Catch:{ ClassCastException -> 0x00a3 }
                    goto L_0x00a3
                L_0x012d:
                    com.contextlogic.wish.api.model.WishNotification r4 = r3
                    java.lang.Object r4 = r4.getTarget()
                    if (r4 == 0) goto L_0x0151
                    android.content.Intent r4 = new android.content.Intent     // Catch:{ ClassCastException -> 0x0151 }
                    r4.<init>()     // Catch:{ ClassCastException -> 0x0151 }
                    com.contextlogic.wish.application.WishApplication r5 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.Class<com.contextlogic.wish.activity.webview.WebViewActivity> r0 = com.contextlogic.wish.activity.webview.WebViewActivity.class
                    r4.setClass(r5, r0)     // Catch:{ ClassCastException -> 0x0150 }
                    com.contextlogic.wish.api.model.WishNotification r5 = r3     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.Object r5 = r5.getTarget()     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.String r5 = (java.lang.String) r5     // Catch:{ ClassCastException -> 0x0150 }
                    java.lang.String r0 = "ExtraUrl"
                    r4.putExtra(r0, r5)     // Catch:{ ClassCastException -> 0x0150 }
                L_0x0150:
                    r0 = r4
                L_0x0151:
                    if (r0 == 0) goto L_0x015c
                    com.contextlogic.wish.activity.notifications.NotificationsView r4 = com.contextlogic.wish.activity.notifications.NotificationsView.this
                    com.contextlogic.wish.activity.DrawerActivity r4 = r4.mBaseActivity
                    r4.startActivity(r0)
                L_0x015c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.notifications.NotificationsView.AnonymousClass1.performTask(com.contextlogic.wish.activity.BaseActivity, com.contextlogic.wish.activity.notifications.NotificationsServiceFragment):void");
            }
        });
        if (this.mListAdapter != null) {
            this.mListAdapter.refreshNewState();
        }
    }

    /* access modifiers changed from: private */
    public void handleScrollLoad(final int i, final int i2, final int i3) {
        this.mNotificationsFragment.withServiceFragment(new ServiceTask<BaseActivity, NotificationsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, NotificationsServiceFragment notificationsServiceFragment) {
                if (!(!NotificationsView.this.mNoMoreItems && !notificationsServiceFragment.isNotificationsLoading() && NotificationsView.this.isLoadingComplete() && !NotificationsView.this.isLoadingErrored()) || i <= i3 - (i2 * 2)) {
                    NotificationsView.this.mNotificationsFragment.updateNotifications(NotificationsView.this.mNotifications);
                } else {
                    NotificationsView.this.loadNextPage(NotificationsView.this.mBucketNumber, NotificationsView.this.mWishTag.getTagId());
                }
            }
        });
    }

    public void loadFirstPage() {
        if (this.mWishTag.getTagId() != null && !hasItems()) {
            loadNextPage(this.mBucketNumber, this.mWishTag.getTagId());
        }
    }

    /* access modifiers changed from: private */
    public void loadNextPage(final int i, final String str) {
        NotificationUtil.clearAllPushNotifications();
        this.mNotificationsFragment.withServiceFragment(new ServiceTask<BaseActivity, NotificationsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, NotificationsServiceFragment notificationsServiceFragment) {
                notificationsServiceFragment.loadNotifications(i, str, NotificationsView.this.getCurrentIndex());
            }
        });
    }

    public void handleLoadingSuccess(ArrayList<WishNotification> arrayList, int i) {
        markLoadingComplete();
        if (this.mBucketNumber == 0 && ExperimentDataCenter.getInstance().shouldSeeNotificationSettingsBannerView()) {
            this.mListView.setSelectionAfterHeaderView();
            this.mHeaderContainer.removeAllViews();
            NotificationSettingsBannerView notificationSettingsBannerView = new NotificationSettingsBannerView(getContext());
            notificationSettingsBannerView.setFragment(this.mNotificationsFragment);
            notificationSettingsBannerView.trackImpression("notifications_screen");
            this.mHeaderContainer.addView(notificationSettingsBannerView, new LayoutParams(-1, -2));
        }
        this.mBucketNumber++;
        this.mNoMoreItems = this.mBucketNumber >= i;
        if (this.mNoMoreItems) {
            markNoMoreItems();
        }
        addNotifications(arrayList);
        loadMoreIfNecessary();
        autoscrollToFirstNotificationOfType();
        this.mRefresherView.setRefreshing(false);
        this.mRefresherView.setEnabled(true);
    }

    public void handleLoadingFailure() {
        markLoadingErrored();
        this.mRefresherView.setRefreshing(false);
        this.mRefresherView.setEnabled(true);
    }

    public void loadMoreIfNecessary() {
        if (isLoadingComplete() && this.mNotifications.size() < 10 && !isNoMoreItems()) {
            loadNextPage(this.mBucketNumber, this.mWishTag.getTagId());
        }
    }

    public ArrayList<WishNotification> getNotifications() {
        return this.mNotifications;
    }

    public void addNotifications(ArrayList<WishNotification> arrayList) {
        markLoadingComplete();
        if (arrayList.size() > 0) {
            if (this.mNotifications.size() == 0) {
                View view = new View(getContext());
                view.setLayoutParams(new AbsListView.LayoutParams(-1, 0));
                this.mListView.addFooterView(view);
            }
            this.mNotifications.addAll(arrayList);
            markLoadingComplete();
        }
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    public void onPause() {
        this.mNotificationsFragment.withServiceFragment(new ServiceTask<BaseActivity, NotificationsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, NotificationsServiceFragment notificationsServiceFragment) {
                notificationsServiceFragment.cancelAllRequests();
            }
        });
    }

    public void handleResume() {
        refreshRowTimestamps();
        if (!isLoadingComplete() || this.mNeedsReload) {
            reload();
        } else {
            loadMoreIfNecessary();
        }
    }

    public Bundle getSavedInstanceState() {
        if (!isLoadingComplete() || this.mNeedsReload) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SavedNotifications", StateStoreCache.getInstance().storeParcelableList(this.mNotifications));
        bundle.putInt("SavedStateBucketNumber", this.mBucketNumber);
        bundle.putBoolean("SavedStateNoMoreItems", this.mNoMoreItems);
        bundle.putInt("SavedStateFirstVisiblePosition", getFirstItemPosition());
        return bundle;
    }

    public void releaseImages() {
        if (this.mListAdapter != null) {
            this.mListAdapter.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mListAdapter != null) {
            this.mListAdapter.restoreImages();
        }
    }

    public void handleReload() {
        this.mNeedsReload = false;
        this.mNotifications.clear();
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
        this.mNoMoreItems = false;
        this.mBucketNumber = 0;
        loadNextPage(this.mBucketNumber, this.mWishTag.getTagId());
    }

    public void initializeLoadingContentView(View view) {
        this.mRefresherView = (SwipeRefreshLayout) view.findViewById(R.id.notifications_fragment_tab_refresher);
        this.mRefresherView.setColorSchemeResources(R.color.main_primary);
        this.mRefresherView.setEnabled(false);
        this.mRefresherView.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                NotificationsView.this.reload();
            }
        });
        this.mListView = (ListView) view.findViewById(R.id.notifications_fragment_tab_listview);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i - 1;
                if (i2 >= 0) {
                    NotificationsView.this.handleNotificationClick(i2);
                }
            }
        });
        this.mListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                NotificationsView.this.handleScrollLoad(i, i2, i3);
            }
        });
        this.mLoadingFooter = new LoadingFooterView(this.mNotificationsFragment.getActivity());
        this.mLoadingFooter.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationsView.this.loadNextPage(NotificationsView.this.mBucketNumber, NotificationsView.this.mWishTag.getTagId());
            }
        });
        setLoadingFooter(this.mLoadingFooter);
        NotificationsAdapter notificationsAdapter = new NotificationsAdapter(this.mBaseActivity, this.mNotificationsFragment, this.mNotifications, this.mListView, this);
        this.mListAdapter = notificationsAdapter;
        this.mHeaderContainer = new FrameLayout(this.mNotificationsFragment.getActivity());
        this.mListView.addHeaderView(this.mHeaderContainer);
        this.mListView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.gray7));
        this.mListView.setAdapter(this.mListAdapter);
        this.mListAdapter.notifyDataSetChanged();
        this.mListView.setFadingEdgeLength(0);
        switch (getCurrentIndex()) {
            case 0:
                setNoItemsCaption(WishApplication.getInstance().getString(R.string.no_notifications_caption), WishApplication.getInstance().getString(R.string.no_notifications_message_all));
                break;
            case 1:
                setNoItemsCaption(WishApplication.getInstance().getString(R.string.no_notifications_caption), WishApplication.getInstance().getString(R.string.no_notifications_message_deals));
                break;
            case 2:
                setNoItemsCaption(WishApplication.getInstance().getString(R.string.no_notifications_caption), WishApplication.getInstance().getString(R.string.no_notifications_message_your_orders));
                break;
            default:
                setNoItemsCaption(WishApplication.getInstance().getString(R.string.no_notifications_caption), null);
                break;
        }
        setEmptyBrowseButton(new OnClickListener() {
            public void onClick(View view) {
                NotificationsView.this.mNotificationsFragment.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NOTIFICATION_V2_CONTINUE_SHOPPING);
                        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
                            Intent intent = new Intent();
                            intent.setClass(NotificationsView.this.mBaseActivity, BrowseActivity.class);
                            intent.setFlags(131072);
                            NotificationsView.this.mBaseActivity.startActivity(intent);
                        }
                        NotificationsView.this.mBaseActivity.finishActivity();
                    }
                });
            }
        });
        this.mListView.setFooterDividersEnabled(true);
        this.mListView.setDivider(new ColorDrawable(WishApplication.getInstance().getResources().getColor(R.color.transparent)));
        this.mListView.setDividerHeight((int) WishApplication.getInstance().getResources().getDimension(R.dimen.sixteen_padding));
        initializeValues();
    }

    public void initializeValues() {
        Bundle savedInstanceState = this.mNotificationsFragment.getSavedInstanceState(getCurrentIndex());
        if (savedInstanceState != null) {
            this.mNotificationsFragment.clearSavedInstanceState(getCurrentIndex());
            int i = savedInstanceState.getInt("SavedStateBucketNumber");
            ArrayList parcelableList = StateStoreCache.getInstance().getParcelableList(savedInstanceState, "SavedNotifications", WishNotification.class);
            if (parcelableList != null) {
                handleLoadingSuccess(parcelableList, i);
            }
            restorePosition(savedInstanceState.getInt("SavedStateFirstVisiblePosition"));
        }
    }

    public boolean hasItems() {
        return this.mNotifications.size() > 0;
    }

    public int getCurrentIndex() {
        return this.mCurrentIndex;
    }

    public int getFirstItemPosition() {
        return this.mListView.getFirstVisiblePosition();
    }

    public void restorePosition(final int i) {
        this.mListView.post(new Runnable() {
            public void run() {
                NotificationsView.this.mListView.setSelection(i);
            }
        });
    }

    private void autoscrollToFirstNotificationOfType() {
        if (!this.mAutoscrolled) {
            BaseActivity baseActivity = this.mNotificationsFragment.getBaseActivity();
            if (baseActivity instanceof NotificationsActivity) {
                int autoscrollNotificationType = ((NotificationsActivity) baseActivity).getAutoscrollNotificationType();
                if (autoscrollNotificationType != -1) {
                    NotificationDisplayType fromInteger = NotificationDisplayType.fromInteger(autoscrollNotificationType);
                    for (int i = 0; i < this.mNotifications.size(); i++) {
                        if (((WishNotification) this.mNotifications.get(i)).getNotificationDisplayType() == fromInteger) {
                            restorePosition(this.mListView.getHeaderViewsCount() + i);
                            this.mAutoscrolled = true;
                        }
                    }
                }
            }
        }
    }
}
