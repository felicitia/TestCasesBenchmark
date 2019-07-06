package com.etsy.android.ui.shophome;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d.b;
import com.etsy.android.lib.models.apiv3.CouponData;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopListingsSearchResult;
import com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration;
import com.etsy.android.lib.shophome.ShopHomeStateManager;
import com.etsy.android.lib.util.aj;
import com.etsy.android.vespa.b.a;
import com.etsy.android.vespa.b.c;
import java.lang.ref.WeakReference;
import java.util.List;
import org.parceler.d;

public class ShopHomeMainFragment extends SectionedShopHomeFragment {
    private static final int COUPON_ANIM_DELAY = 150;
    private static final int COUPON_ANIM_DURATION = 300;
    private static final int COUPON_ANIM_START_DELAY = 200;
    private static final int COUPON_SECOND_ANIM_DELAY = 100;
    private static final String DID_INITIAL_PAGE_LOAD = "did_initial_page_load";
    private final TimeInterpolator animInterpolator = new AccelerateDecelerateInterpolator();
    private ViewGroup mCouponBanner;
    protected boolean mDidInitialPageLoad;
    c mPaginationForOffset = new c();

    public int getLayoutId() {
        return R.layout.fragment_shop_home_main;
    }

    @NonNull
    public a getPagination() {
        return this.mPaginationForOffset;
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return this.mPaginationForOffset.a();
    }

    public void onLoadContent() {
        if (getAdapter().getItemCount() == 0) {
            super.onLoadContent();
        } else {
            searchListings(getShopHomeFactoryAdapter().getStateManager(), true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mDidInitialPageLoad = bundle != null && bundle.getBoolean(DID_INITIAL_PAGE_LOAD);
        if (!this.mDidInitialPageLoad && arguments.containsKey("shop_home_load_configuration")) {
            this.mInitialLoadConfig = (ShopHomeInitialLoadConfiguration) d.a(arguments.getParcelable("shop_home_load_configuration"));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(DID_INITIAL_PAGE_LOAD, this.mDidInitialPageLoad);
    }

    /* access modifiers changed from: protected */
    public void handlePageData(@NonNull ShopHomePage shopHomePage, @NonNull ShopHomeStateManager shopHomeStateManager) {
        ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration = this.mInitialLoadConfig;
        if (!this.mDidInitialPageLoad && shopHomeInitialLoadConfiguration != null) {
            shopHomeStateManager.updateWithLoadConfig(shopHomeInitialLoadConfiguration);
        }
        super.handlePageData(shopHomePage, shopHomeStateManager);
    }

    /* access modifiers changed from: protected */
    public void searchListings(@NonNull ShopHomeStateManager shopHomeStateManager, final boolean z) {
        EtsyApiV3Request freshSearchRequest = freshSearchRequest(shopHomeStateManager, this.mShopId, z);
        if (!z) {
            getPagination().reset();
        }
        final WeakReference weakReference = new WeakReference(this);
        getRequestQueue().a((Object) this, ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a(freshSearchRequest).a((C0065a<ResultType>) new b<ShopListingsSearchResult>() {
            public void a(@NonNull List<ShopListingsSearchResult> list, int i, @NonNull com.etsy.android.lib.core.a.a<ShopListingsSearchResult> aVar) {
                ShopHomeMainFragment shopHomeMainFragment = (ShopHomeMainFragment) weakReference.get();
                ShopHomeMainFragment.this.stopLoad();
                if (shopHomeMainFragment != null && list.size() > 0) {
                    ShopListingsSearchResult shopListingsSearchResult = (ShopListingsSearchResult) aVar.h();
                    ShopHomeMainFragment.this.getShopHomeFactoryAdapter().configureForNewListingResults(shopListingsSearchResult, !z, i);
                    ShopHomeMainFragment.this.getPagination().onSuccess(Integer.valueOf(i), shopListingsSearchResult.getListings().size());
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<ShopListingsSearchResult> aVar) {
                if (((SectionedShopHomeFragment) weakReference.get()) != null) {
                    ShopHomeMainFragment.this.stopLoad();
                    aj.a((View) ShopHomeMainFragment.this.mRecyclerView, (CharSequence) ShopHomeMainFragment.this.getString(R.string.error_loading_more_listings));
                }
            }
        }, (Fragment) this)).c());
    }

    /* access modifiers changed from: protected */
    public void onPageLoaded(@NonNull ShopHomePage shopHomePage) {
        super.onPageLoaded(shopHomePage);
        ShopHomeStateManager stateManager = getShopHomeFactoryAdapter().getStateManager();
        getPagination().onSuccess(Integer.valueOf(stateManager.getTotalListingsCount()), stateManager.getListingsCount());
        this.mDidInitialPageLoad = true;
        this.mCouponBanner = (ViewGroup) getView().findViewById(R.id.shop_home_coupon_banner);
        initCouponBanner(shopHomePage.getCouponData());
    }

    private void initCouponBanner(@Nullable CouponData couponData) {
        if (couponData != null && couponData.isValid()) {
            ((TextView) getView().findViewById(R.id.shop_home_coupon_message)).setText(Html.fromHtml(couponData.getMessage().toString()));
            ((ImageView) getView().findViewById(R.id.shop_home_coupon_close)).setOnClickListener(new c(this));
            this.mCouponBanner.postDelayed(new d(this), 200);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$initCouponBanner$0$ShopHomeMainFragment(View view) {
        hideCouponBanner();
    }

    /* access modifiers changed from: private */
    /* renamed from: showCouponBanner */
    public void bridge$lambda$0$ShopHomeMainFragment() {
        float measuredHeight = (float) this.mCouponBanner.getMeasuredHeight();
        float f = -measuredHeight;
        this.mCouponBanner.setTranslationY(f);
        this.mCouponBanner.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mCouponBanner, "translationY", new float[]{f, 0.0f});
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, (int) measuredHeight});
        ofInt.setStartDelay(100);
        ofInt.addUpdateListener(new e(this));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(this.animInterpolator);
        animatorSet.setDuration(300);
        animatorSet.setStartDelay(150);
        animatorSet.playTogether(new Animator[]{ofFloat, ofInt});
        animatorSet.start();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$showCouponBanner$1$ShopHomeMainFragment(ValueAnimator valueAnimator) {
        this.mSwipeRefreshLayout.setPadding(0, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, 0);
    }

    private void hideCouponBanner() {
        float measuredHeight = (float) this.mCouponBanner.getMeasuredHeight();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mCouponBanner, "translationY", new float[]{0.0f, -measuredHeight});
        ofFloat.setStartDelay(100);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{(int) measuredHeight, 0});
        ofInt.addUpdateListener(new f(this));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(this.animInterpolator);
        animatorSet.setDuration(300);
        animatorSet.setStartDelay(150);
        animatorSet.playTogether(new Animator[]{ofFloat, ofInt});
        animatorSet.start();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$hideCouponBanner$2$ShopHomeMainFragment(ValueAnimator valueAnimator) {
        this.mSwipeRefreshLayout.setPadding(0, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, 0);
    }
}
