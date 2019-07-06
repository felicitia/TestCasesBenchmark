package com.etsy.android.ui.shop;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.apiv3.ListingMemberData;
import com.etsy.android.lib.models.apiv3.ShopHomeMemberData;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopListingsSearchResult;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.etsy.android.lib.models.interfaces.BasicShopLike;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.qualtrics.b;
import com.etsy.android.lib.shophome.ShopHomeAdapter;
import com.etsy.android.lib.shophome.ShopHomeStateManager;
import com.etsy.android.lib.shophome.c;
import com.etsy.android.lib.shophome.vertical.BaseShopHomeFragment;
import com.etsy.android.ui.util.e;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public class ShopHomeFragment extends BaseShopHomeFragment implements a, b {
    boolean mIsSignedIn;
    private final BroadcastReceiver mListingStateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                ShopHomeFragment.this.listingStateChanged(intent.getExtras());
            }
        }
    };
    private e mShopFavoriteUtil;
    com.etsy.android.lib.shophome.b mShopHomeRouter;
    @Nullable
    private EtsyAction mSignInAction;

    @NonNull
    public String getTrackingName() {
        return "shop_home";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShopFavoriteUtil = new e(getActivity(), null, getAnalyticsContext());
        this.mIsSignedIn = v.a().e();
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mListingStateReceiver, new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
        QualtricsController.b((b) this);
    }

    public void onResume() {
        super.onResume();
        QualtricsController.a((b) this);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mListingStateReceiver);
        boolean z = this.mIsSignedIn;
        boolean e = v.a().e();
        this.mIsSignedIn = e;
        if (z != e) {
            ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
            ShopHomePage pageData = shopHomeAdapter.getPageData();
            if (pageData != null && e) {
                getMemberData(pageData);
            } else if (pageData == null) {
                loadContent();
            }
            ShopHomeStateManager stateManager = shopHomeAdapter.getStateManager();
            if (stateManager != null && pageData != null) {
                stateManager.setIsSelf(e && pageData.getShop().getUserId().equals(v.a().l()));
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mListingStateReceiver);
    }

    /* access modifiers changed from: 0000 */
    public void listingStateChanged(@NonNull Bundle bundle) {
        String string = bundle.getString("id");
        if (!TextUtils.isEmpty(string)) {
            ((ShopHomeAdapter) this.mAdapter).listingStateChanged(new EtsyId(string), bundle);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem != null) {
            ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
            if (shopHomeAdapter == null || shopHomeAdapter.getPageData() == null) {
                findItem.setVisible(false);
            } else {
                findItem.setVisible(true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void handlePageData(@NonNull ShopHomePage shopHomePage, @NonNull ShopHomeStateManager shopHomeStateManager, @Nullable Bundle bundle) {
        super.handlePageData(shopHomePage, shopHomeStateManager, bundle);
        if (!shopHomeStateManager.hasMemberData() && !shopHomeStateManager.isSelf() && this.mIsSignedIn) {
            getMemberData(shopHomePage);
        }
    }

    /* access modifiers changed from: protected */
    public void onPageLoaded(@NonNull ShopHomePage shopHomePage) {
        getAnalyticsContext().a("shop_home_complementary", shopHomePage.getTrackingParameters());
    }

    private void getMemberData(@NonNull ShopHomePage shopHomePage) {
        ShopV3 shop = shopHomePage.getShop();
        EtsyApiV3Request.a aVar = (EtsyApiV3Request.a) ((EtsyApiV3Request.a) EtsyApiV3Request.a.a(ShopHomeMemberData.class, com.etsy.android.lib.core.http.url.a.b.e.e(shop.getShopId())).a(1)).b(true).a((BaseHttpBody) (FormBody) ((FormBody.a) ((FormBody.a) new FormBody.a().b("listing_ids[]", c.a((List<? extends ListingLike>[]) new List[]{shopHomePage.getFeaturedListings(), shopHomePage.getShopListings()}))).b("vacation_subscription_status_required", Boolean.toString(shop.isVacation()))).f());
        final WeakReference weakReference = new WeakReference(this);
        getRequestQueue().a((Object) this, ((d.a) d.a.a((EtsyApiV3Request) aVar.d()).a((C0065a<ResultType>) new d.b<ShopHomeMemberData>() {
            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<ShopHomeMemberData> aVar) {
            }

            public void a(@NonNull List<ShopHomeMemberData> list, int i, @NonNull com.etsy.android.lib.core.a.a<ShopHomeMemberData> aVar) {
                ShopHomeFragment shopHomeFragment = (ShopHomeFragment) weakReference.get();
                if (shopHomeFragment != null && shopHomeFragment.getActivity() != null && !list.isEmpty() && shopHomeFragment.mAdapter != null) {
                    ShopHomeFragment.this.handleMemberDataResponse((ShopHomeMemberData) list.get(0));
                }
            }
        }, (Fragment) this)).c());
    }

    /* access modifiers changed from: protected */
    public void handleMemberDataResponse(@NonNull ShopHomeMemberData shopHomeMemberData) {
        ((ShopHomeAdapter) this.mAdapter).configureForMemberData(shopHomeMemberData);
    }

    /* access modifiers changed from: protected */
    public void onNewListingsResponse(@NonNull ShopListingsSearchResult shopListingsSearchResult) {
        List listings = shopListingsSearchResult.getListings();
        if (!listings.isEmpty() && this.mIsSignedIn) {
            EtsyApiV3Request.a aVar = (EtsyApiV3Request.a) ((EtsyApiV3Request.a) EtsyApiV3Request.a.a(ListingMemberData.class, "/member/users/listings-favorites-collections").a(1)).b(true).a((BaseHttpBody) (FormBody) ((FormBody.a) new FormBody.a().b("listing_ids[]", c.a((List<? extends ListingLike>[]) new List[]{listings}))).f());
            final WeakReference weakReference = new WeakReference(this);
            getRequestQueue().a((Object) this, ((d.a) d.a.a((EtsyApiV3Request) aVar.d()).a((C0065a<ResultType>) new d.b<ListingMemberData>() {
                public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<ListingMemberData> aVar) {
                }

                public void a(@NonNull List<ListingMemberData> list, int i, @NonNull com.etsy.android.lib.core.a.a<ListingMemberData> aVar) {
                    ShopHomeFragment shopHomeFragment = (ShopHomeFragment) weakReference.get();
                    if (shopHomeFragment != null && shopHomeFragment.getActivity() != null && !list.isEmpty() && shopHomeFragment.mAdapter != null) {
                        ((ShopHomeAdapter) ShopHomeFragment.this.mAdapter).applyListingsMemberInfo(list);
                    }
                }
            }, (Fragment) this)).c());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        shareShop();
        return true;
    }

    private void shareShop() {
        ShopHomePage pageData = ((ShopHomeAdapter) this.mAdapter).getPageData();
        if (pageData != null) {
            ShopV3 shop = pageData.getShop();
            final EtsyId shopId = shop.getShopId();
            SocialShareUtil.a("shop_home", ShareType.SHOP_SHARE, getActivity().getLocalClassName());
            getAnalyticsContext().a("share_shop", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.SHOP_ID, shopId);
                }
            });
            ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a(getActivity()).a().a((j) this)).a((ShopShareable) shop.getShareable());
        }
    }

    public void performShopFavorite(boolean z) {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            if (v.a().e()) {
                this.mShopFavoriteUtil.a((BasicShopLike) shopHomeAdapter.getPageData().getShop(), (e.b) null, z);
            } else {
                com.etsy.android.ui.nav.e.a(getActivity()).a().a(EtsyAction.FAVORITE, "");
            }
        }
    }

    public void didSubmitSearchQuery(@NonNull final String str) {
        getAnalyticsContext().a("searched", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.QUERY, str);
            }
        });
    }

    public void didSelectSortOption(@NonNull final ShopHomeSortOption shopHomeSortOption) {
        getAnalyticsContext().a("selected_shop_items_sort", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.SORT_OPTION_ID, shopHomeSortOption.getOptionId());
            }
        });
    }

    public void didSelectSection(@NonNull final ShopSection shopSection) {
        getAnalyticsContext().a("selected_shop_section", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.SHOP_SECTION_ID, shopSection.getShopSectionId());
            }
        });
    }

    /* access modifiers changed from: protected */
    public void didSelectTab(@Nullable String str) {
        if (str != null) {
            w analyticsContext = getAnalyticsContext();
            StringBuilder sb = new StringBuilder();
            sb.append("clicked_tab_nav_");
            sb.append(str);
            analyticsContext.a(sb.toString(), null);
        }
    }

    /* access modifiers changed from: protected */
    public void didChangeTabSelectionOnScroll(@Nullable String str) {
        if (str != null) {
            w analyticsContext = getAnalyticsContext();
            StringBuilder sb = new StringBuilder();
            sb.append("scrolled_to_shop_tab_section_");
            sb.append(str);
            analyticsContext.a(sb.toString(), null);
        }
    }

    /* access modifiers changed from: protected */
    public com.etsy.android.lib.shophome.b getShopHomeRouter() {
        if (this.mShopHomeRouter == null) {
            this.mShopHomeRouter = new b((TrackingBaseActivity) getActivity(), (ShopHomeAdapter) this.mAdapter, this.mShopId) {
                public void a(String str, boolean z) {
                    Tab a2 = com.etsy.android.lib.shophome.vertical.b.a(ShopHomeFragment.this.mTabLayout, str);
                    if (a2 != null) {
                        a(a2, z);
                    }
                }

                public void a(@NonNull Tab tab, boolean z) {
                    int a2 = com.etsy.android.lib.shophome.vertical.b.a(tab);
                    if (a2 >= 0) {
                        RecyclerView access$400 = ShopHomeFragment.this.mRecyclerView;
                        access$400.removeOnScrollListener(ShopHomeFragment.this.mScrollListener);
                        if (z) {
                            access$400.smoothScrollToPosition(a2);
                        } else {
                            access$400.scrollToPosition(a2);
                        }
                        access$400.addOnScrollListener(ShopHomeFragment.this.mScrollListener);
                    }
                }

                public void d() {
                    int findFirstVisibleItemPosition = ((GridLayoutManager) ShopHomeFragment.this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    TabLayout access$800 = ShopHomeFragment.this.mTabLayout;
                    access$800.removeOnTabSelectedListener(ShopHomeFragment.this.mOnTabSelectedListener);
                    int tabCount = access$800.getTabCount();
                    int i = 0;
                    while (true) {
                        if (i >= tabCount) {
                            break;
                        }
                        Tab tabAt = access$800.getTabAt(i);
                        int a2 = com.etsy.android.lib.shophome.vertical.b.a(tabAt);
                        int b = com.etsy.android.lib.shophome.vertical.b.b(tabAt);
                        if (a2 <= findFirstVisibleItemPosition && ((b < 0 || findFirstVisibleItemPosition <= b) && tabAt != null && !tabAt.isSelected())) {
                            tabAt.select();
                            ShopHomeFragment.this.didChangeTabSelectionOnScroll(com.etsy.android.lib.shophome.vertical.b.c(tabAt));
                            break;
                        }
                        i++;
                    }
                    access$800.addOnTabSelectedListener(ShopHomeFragment.this.mOnTabSelectedListener);
                }
            };
        }
        return this.mShopHomeRouter;
    }

    public void performVacationNotificationSubscription(boolean z) {
        if (!v.a().e()) {
            ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) getActivity()).a((j) this)).a(300, (Fragment) this).a(EtsyAction.SUBSCRIBE_VACATION_NOTIFICATION, "");
            this.mSignInAction = EtsyAction.SUBSCRIBE_VACATION_NOTIFICATION;
            return;
        }
        toggleVacationNotificationSettings(z);
    }

    private void toggleVacationNotificationSettings(final boolean z) {
        getRequestQueue().a((Object) this, ((d.a) d.a.a((EtsyApiV3Request) ((EtsyApiV3Request.a) ((EtsyApiV3Request.a) EtsyApiV3Request.a.a(EmptyResult.class, com.etsy.android.lib.core.http.url.a.b.e.f(this.mShopId)).a(1)).b(true).a((BaseHttpBody) (FormBody) ((FormBody.a) new FormBody.a().b("notification", Boolean.toString(z))).f())).d()).a((C0065a<ResultType>) new d.b<EmptyResult>() {
            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
            }

            public void a(@NonNull List<EmptyResult> list, int i, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
                if (ShopHomeFragment.this.mAdapter != null && ShopHomeFragment.this.getActivity() != null) {
                    ((ShopHomeAdapter) ShopHomeFragment.this.mAdapter).handleVacationSubscriptionStatusChange(z);
                }
            }
        }, (Fragment) this)).c());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 300 && this.mSignInAction == EtsyAction.SUBSCRIBE_VACATION_NOTIFICATION) {
            if (i2 == 311) {
                toggleVacationNotificationSettings(true);
            } else {
                ((ShopHomeAdapter) this.mAdapter).handleVacationSubscriptionStatusChange(false);
            }
        }
    }

    public void didClearSearch() {
        super.didClearSearch();
        getAnalyticsContext().a("cleared_search", null);
    }

    public void loadMoreListings(@NonNull ShopHomeStateManager shopHomeStateManager) {
        super.loadMoreListings(shopHomeStateManager);
        getAnalyticsContext().a("load_more_listings", null);
    }

    public Context getContextForQualtricsPrompt() {
        return getContext().getApplicationContext();
    }

    public ViewGroup getContainerRootViewGroupForQualtricsSurvey() {
        return (ViewGroup) this.mRecyclerView.getParent();
    }
}
