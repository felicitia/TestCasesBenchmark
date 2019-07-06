package com.etsy.android.ui.cart;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.c.g;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.l;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.etsy.android.ui.cardview.clickhandlers.SavedCartClickHandler;
import com.etsy.android.ui.cart.CartWithSavedActivity.b;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration.Alignment;
import com.etsy.android.uikit.viewholder.MarginsItemDecoration;
import com.etsy.android.vespa.VespaBaseFragment;
import com.etsy.android.vespa.j;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SavedCartItemsFragment extends VespaBaseFragment<CartPage> implements a, CartRefreshDelegate.a, b {
    public static final String NEXT_LINK = "next_link";
    public static final String PAGE = "page";
    private Disposable errorMessageDisposable;
    CartRefreshDelegate mCartRefreshDelegate;
    protected com.etsy.android.vespa.b.b mPagination = new com.etsy.android.vespa.b.b();
    SavedCartClickHandler mSavedCartClickListener;
    g retrofit;

    public String getApiUrl() {
        return "/etsyapps/v3/bespoke/member/carts/saved-for-later";
    }

    @NonNull
    public String getTrackingName() {
        return "cart_saved_view";
    }

    public void onCartPageSelected() {
    }

    public com.etsy.android.vespa.b.a getPagination() {
        return this.mPagination;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mCartRefreshDelegate = new CartRefreshDelegate(getActivity(), this);
        getAdapter().setEditable(true);
        com.etsy.android.ui.cardview.a aVar = new com.etsy.android.ui.cardview.a(getActivity(), getAdapter(), getAnalyticsContext());
        this.mSavedCartClickListener = new SavedCartClickHandler(getActivity(), getAnalyticsContext(), getAdapter(), this.retrofit);
        aVar.a((int) R.id.view_type_saved_cart_listing_card, (com.etsy.android.vespa.b) this.mSavedCartClickListener);
        this.errorMessageDisposable = this.mSavedCartClickListener.a().a((Consumer<? super T>) new f<Object>(this));
        addDelegateViewHolderFactory(aVar);
        if (getConfigMap().c(com.etsy.android.lib.config.b.aT)) {
            this.mRecyclerView.setScrollBarStyle(33554432);
            this.mRecyclerView.addItemDecoration(getItemDividerDecoration());
            if (l.c((Activity) getActivity())) {
                this.mRecyclerView.addItemDecoration(getMarginsItemDecoration());
            }
        }
        return onCreateView;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onCreateView$0$SavedCartItemsFragment(String str) throws Exception {
        aj.a(getContext(), str);
    }

    public void onResume() {
        super.onResume();
        this.mCartRefreshDelegate.onResume();
        if (getAdapter().getItemCount() == 0) {
            onRefresh();
        }
    }

    public void onPause() {
        super.onPause();
        this.mCartRefreshDelegate.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.errorMessageDisposable != null) {
            this.errorMessageDisposable.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void onLoadSuccess(@Nullable com.etsy.android.lib.core.a.a<? extends j> aVar) {
        super.onLoadSuccess(aVar);
        CartPage cartPage = (CartPage) aVar.h();
        CartRefreshDelegate.sendBroadcast(getActivity(), cartPage.getCartCount(), cartPage.getSavedCount(), false, 2);
    }

    public void onRefresh() {
        getAdapter().clear();
        this.mEmptyView.setVisibility(8);
        resetAndLoadContent();
    }

    public void onCartCountsUpdated(int i, int i2, boolean z, int i3) {
        if (!z) {
            return;
        }
        if (i3 != 2) {
            onRefresh();
        } else if (i3 != 2) {
        } else {
            if (i2 == 0) {
                onRefresh();
            } else if (!TextUtils.isEmpty(getPagination().getApiNextLink())) {
                onLoadContent();
            }
        }
    }

    private ItemDividerDecoration getItemDividerDecoration() {
        return new ItemDividerDecoration(getResources().getDrawable(R.drawable.list_divider_dark), (ItemDividerDecoration.a) new ItemDividerDecoration.a() {
            public Alignment a(int i, int i2) {
                return Alignment.ALIGN_CHILD;
            }

            public boolean b(int i, int i2) {
                return i2 < SavedCartItemsFragment.this.getAdapter().getItemCount() - 1;
            }
        });
    }

    private MarginsItemDecoration getMarginsItemDecoration() {
        AnonymousClass2 r0 = new MarginsItemDecoration(0, getResources().getDimensionPixelOffset(R.dimen.margin_large), 0, 0) {
            /* access modifiers changed from: protected */
            public boolean isDecorated(View view, RecyclerView recyclerView) {
                return recyclerView.getChildAdapterPosition(view) == 0;
            }
        };
        return r0;
    }

    /* access modifiers changed from: protected */
    public Class<CartPage> getPageClass() {
        return CartPage.class;
    }
}
