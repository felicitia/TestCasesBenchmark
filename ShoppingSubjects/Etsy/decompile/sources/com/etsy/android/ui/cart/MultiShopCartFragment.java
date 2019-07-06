package com.etsy.android.ui.cart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.config.b.e;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.JsonBody;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayData;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.lib.models.apiv3.cart.CartReceipt;
import com.etsy.android.lib.models.apiv3.cart.CheckoutSection;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.aj;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.etsy.android.messaging.CartRefreshDelegate.a;
import com.etsy.android.ui.cart.CartWithSavedActivity.b;
import com.etsy.android.ui.cart.googlewallet.GoogleWalletHelperBase.ReadyState;
import com.etsy.android.ui.cart.googlewallet.a.C0087a;
import com.etsy.android.ui.cart.viewholders.CartGroupBottomDecoration;
import com.etsy.android.vespa.PositionList;
import com.etsy.android.vespa.VespaBaseFragment;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.ResultCallback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.parceler.d;

public class MultiShopCartFragment extends VespaBaseFragment implements a, b, d {
    public static final String LAST_STARTED_ORIENTATION = "last_started_orientation";
    private CartRefreshDelegate mCartRefreshDelegate;
    private com.etsy.android.ui.cart.googlewallet.a mGoogleWalletHelper;
    /* access modifiers changed from: private */
    public boolean mIsAndroidPayAvailable;
    private boolean mIsAndroidPayEnabled;
    private int mLastStartedOrientation;
    private com.etsy.android.vespa.b.b mPaginator = new com.etsy.android.vespa.b.b();
    /* access modifiers changed from: private */
    @Nullable
    public EtsyId mThankYouReceiptId = null;

    @NonNull
    public String getTrackingName() {
        return "cart_view";
    }

    /* access modifiers changed from: protected */
    public boolean hasRecyclerViewPadding() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCartRefreshDelegate = new CartRefreshDelegate(getActivity(), this);
        this.mAdapter = new MultiShopCartAdapter(getActivity(), getAnalyticsContext(), this);
        addDelegateViewHolderFactory(new e(getActivity(), getAnalyticsContext(), this));
        addDelegateViewHolderFactory(new com.etsy.android.ui.cardview.a(getActivity(), getAdapter(), getAnalyticsContext()));
        if (bundle == null) {
            Bundle arguments = getArguments();
            if (arguments != null && arguments.containsKey(CartWithSavedActivity.LAST_ORDER_ID)) {
                this.mThankYouReceiptId = (EtsyId) arguments.getSerializable(CartWithSavedActivity.LAST_ORDER_ID);
                return;
            }
            return;
        }
        this.mLastStartedOrientation = bundle.getInt(LAST_STARTED_ORIENTATION, 0);
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mRecyclerView.addItemDecoration(new CartGroupBottomDecoration(getActivity()));
        this.mRecyclerView.setDescendantFocusability(131072);
        return onCreateView;
    }

    public void onStart() {
        super.onStart();
        int i = getResources().getConfiguration().orientation;
        if (i == this.mLastStartedOrientation) {
            a.a((com.etsy.android.lib.logger.b) getAnalyticsContext(), ((MultiShopCartAdapter) getAdapter()).getAllLoadedCartIds());
        }
        this.mLastStartedOrientation = i;
    }

    public void onResume() {
        super.onResume();
        this.mCartRefreshDelegate.onResume();
    }

    public void onPause() {
        super.onPause();
        this.mCartRefreshDelegate.onPause();
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.mThankYouReceiptId = (EtsyId) bundle.getSerializable(CartWithSavedActivity.LAST_ORDER_ID);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(LAST_STARTED_ORIENTATION, this.mLastStartedOrientation);
        if (this.mThankYouReceiptId != null) {
            bundle.putSerializable(CartWithSavedActivity.LAST_ORDER_ID, this.mThankYouReceiptId);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        int i3;
        super.onActivityResult(i, i2, intent);
        if (i == 300 && i2 == 311) {
            EtsyAction fromAction = EtsyAction.fromAction(intent.getAction());
            if (fromAction != null) {
                if (fromAction.equals(EtsyAction.CART_ACTION)) {
                    Bundle bundleExtra = intent.getBundleExtra(fromAction.getName());
                    if (bundleExtra != null) {
                        performAction(new PositionList().withParentPosition(bundleExtra.getInt("cart_action_position")), (ServerDrivenAction) d.a(bundleExtra.getParcelable("cart_action")));
                    }
                } else if (fromAction.equals(EtsyAction.PURCHASE)) {
                    Bundle bundleExtra2 = intent.getBundleExtra(fromAction.getName());
                    startCheckout(bundleExtra2.getString(CartWithSavedActivity.CHECKED_OUT_CART_GROUP_ID, ""), bundleExtra2.getString(CartWithSavedActivity.CHECKED_OUT_PAYMENT_METHOD, ""));
                }
            }
        } else if (i == 800 && i2 == 810) {
            if (intent != null) {
                performAction(new PositionList().withParentPosition(intent.getIntExtra("cart_action_position", -1)), (ServerDrivenAction) d.a(intent.getParcelableExtra("cart_action")));
            }
        } else if (i == 700 && i2 == 0) {
            if (intent != null) {
                a.a((com.etsy.android.lib.logger.b) getAnalyticsContext(), intent.getStringExtra("cart_id"), intent.getBooleanExtra("is_paypal", false));
            }
            onRefresh();
        } else if (com.etsy.android.ui.cart.googlewallet.a.b(i)) {
            int a = com.etsy.android.ui.cart.googlewallet.a.a(i);
            if (a < 0 || this.mAdapter == null || a >= this.mAdapter.getItemCount()) {
                aj.b((Context) getActivity(), (int) R.string.whoops_somethings_wrong);
            } else {
                AndroidPayData androidPayData = null;
                for (int i4 = 0; i4 < this.mAdapter.getItemCount(); i4++) {
                    if (this.mAdapter.getItem(i4) instanceof CartGroup) {
                        CartGroup cartGroup = (CartGroup) this.mAdapter.getItem(i4);
                        if (cartGroup == null) {
                            i3 = 0;
                        } else {
                            i3 = cartGroup.getPaymentItems().size();
                        }
                        int i5 = 0;
                        while (true) {
                            if (i5 >= i3) {
                                break;
                            }
                            CartGroupItem cartGroupItem = (CartGroupItem) cartGroup.getPaymentItems().get(i5);
                            if (cartGroupItem.getData() instanceof CheckoutSection) {
                                androidPayData = ((CheckoutSection) cartGroupItem.getData()).getAndroidPayData();
                                break;
                            }
                            i5++;
                        }
                        if (androidPayData != null) {
                            break;
                        }
                    }
                }
                AndroidPayData androidPayData2 = androidPayData;
                if (androidPayData2 != null) {
                    if (intent == null) {
                        intent = new Intent();
                    }
                    Intent intent2 = intent;
                    intent2.putExtra(CartWithSavedActivity.CHECKED_OUT_IS_MSCO, true);
                    this.mGoogleWalletHelper.a(getActivity(), androidPayData2, i, i2, intent2);
                } else {
                    aj.b((Context) getActivity(), (int) R.string.whoops_somethings_wrong);
                }
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mIsAndroidPayEnabled = getConfigMap().c(e.b);
        if (this.mIsAndroidPayEnabled && (getActivity() instanceof C0087a)) {
            this.mGoogleWalletHelper = ((C0087a) getActivity()).getGoogleWalletHelper();
        }
    }

    public void onDestroy() {
        if (this.mGoogleWalletHelper != null && this.mGoogleWalletHelper.b()) {
            this.mGoogleWalletHelper.c();
        }
        super.onDestroy();
    }

    public void onCartPageSelected() {
        a.a((com.etsy.android.lib.logger.b) getAnalyticsContext(), ((MultiShopCartAdapter) getAdapter()).getAllLoadedCartIds());
    }

    @NonNull
    public String getApiUrl() {
        String apiNextLink = getPagination().getApiNextLink();
        return TextUtils.isEmpty(apiNextLink) ? "/etsyapps/v3/bespoke/member/carts" : apiNextLink;
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        if (!this.mIsAndroidPayEnabled || this.mGoogleWalletHelper == null) {
            loadCart();
        } else if (!this.mGoogleWalletHelper.b()) {
            connectAndroidPay();
        } else {
            checkAndroidPayReady();
        }
    }

    private void connectAndroidPay() {
        this.mGoogleWalletHelper.a((com.etsy.android.ui.cart.googlewallet.b) new com.etsy.android.ui.cart.googlewallet.b() {
            public void a() {
                MultiShopCartFragment.this.checkAndroidPayReady();
            }

            public void b() {
                MultiShopCartFragment.this.mIsAndroidPayAvailable = false;
                MultiShopCartFragment.this.loadCart();
            }
        });
    }

    /* access modifiers changed from: private */
    public void checkAndroidPayReady() {
        this.mGoogleWalletHelper.a((ResultCallback<BooleanResult>) new ResultCallback<BooleanResult>() {
            /* renamed from: a */
            public void onResult(@NonNull BooleanResult booleanResult) {
                MultiShopCartFragment.this.mIsAndroidPayAvailable = com.etsy.android.ui.cart.googlewallet.a.a(booleanResult) == ReadyState.READY;
                MultiShopCartFragment.this.loadCart();
            }
        });
    }

    private boolean isAndroidPaySupported() {
        return this.mIsAndroidPayEnabled && this.mGoogleWalletHelper != null && this.mIsAndroidPayAvailable;
    }

    /* access modifiers changed from: private */
    public void loadCart() {
        CountryUtil.a((CountryUtil.a) new CountryUtil.a() {
            WeakReference<MultiShopCartFragment> a = new WeakReference<>(MultiShopCartFragment.this);

            public void onCountriesLoaded(ArrayList<Country> arrayList) {
                MultiShopCartFragment multiShopCartFragment = (MultiShopCartFragment) this.a.get();
                if (multiShopCartFragment != null) {
                    multiShopCartFragment.loadCartPage();
                }
            }

            public void onCountriesError() {
                MultiShopCartFragment multiShopCartFragment = (MultiShopCartFragment) this.a.get();
                if (multiShopCartFragment != null) {
                    multiShopCartFragment.showErrorView();
                }
            }
        });
    }

    public com.etsy.android.vespa.b.a getPagination() {
        return this.mPaginator;
    }

    /* access modifiers changed from: private */
    public void loadCartPage() {
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(CartPage.class, getApiUrl());
        if (isAndroidPaySupported()) {
            a.a("supports_android_pay", String.valueOf(true));
        }
        if (!v.a().e()) {
            a.a("guest_id", g.a().e());
        } else if (this.mThankYouReceiptId != null && this.mThankYouReceiptId.hasId() && getAdapter().getItemCount() == 0) {
            a.a("thank_you_receipt_id", this.mThankYouReceiptId.getId());
        }
        getRequestQueue().a(com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) a.d()).a((C0065a<ResultType>) new com.etsy.android.lib.core.http.request.d.b<CartPage>() {
            public void a(@NonNull List<CartPage> list, int i, @NonNull com.etsy.android.lib.core.a.a<CartPage> aVar) {
                CartPage cartPage = (CartPage) aVar.h();
                if (cartPage == null) {
                    MultiShopCartFragment.this.onLoadFailure();
                    return;
                }
                MultiShopCartAdapter multiShopCartAdapter = (MultiShopCartAdapter) MultiShopCartFragment.this.getAdapter();
                if (MultiShopCartFragment.this.mSwipeRefreshLayout.isRefreshing()) {
                    MultiShopCartFragment.this.mSwipeRefreshLayout.setRefreshing(false);
                    multiShopCartAdapter.clear();
                }
                boolean z = MultiShopCartFragment.this.getAdapter().getItemCount() == 0;
                if (MultiShopCartFragment.this.mThankYouReceiptId != null) {
                    MultiShopCartFragment.this.mThankYouReceiptId = null;
                }
                CartRefreshDelegate.sendBroadcast(MultiShopCartFragment.this.getActivity(), cartPage.getCartCount(), cartPage.getSavedCount(), false, 1);
                MultiShopCartFragment.this.onLoadSuccess(aVar);
                a.a((com.etsy.android.lib.logger.b) MultiShopCartFragment.this.getAnalyticsContext(), multiShopCartAdapter.getLastLoadedPageCartIds());
                List<CartReceipt> cartReceipts = multiShopCartAdapter.getCartReceipts();
                if (z && !cartReceipts.isEmpty()) {
                    for (CartReceipt a2 : cartReceipts) {
                        com.etsy.android.lib.logger.g.a(a2);
                    }
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<CartPage> aVar) {
                MultiShopCartFragment.this.onLoadFailure();
            }
        }, (Fragment) this).c());
    }

    public void proceedToCheckout(@NonNull String str, @NonNull String str2) {
        if (!v.a().e()) {
            Bundle bundle = new Bundle();
            bundle.putString(CartWithSavedActivity.CHECKED_OUT_CART_GROUP_ID, str);
            bundle.putString(CartWithSavedActivity.CHECKED_OUT_PAYMENT_METHOD, str2);
            ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) getActivity()).a((j) this)).a(300, (Fragment) this).a(EtsyAction.PURCHASE, bundle);
            return;
        }
        startCheckout(str, str2);
    }

    private void startCheckout(@NonNull String str, @NonNull String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            com.etsy.android.ui.nav.e.a(getActivity()).a().a(700, (Fragment) this).a(str, str2);
        }
    }

    public void performAction(PositionList positionList, ServerDrivenAction serverDrivenAction) {
        performActionWithToast(positionList, serverDrivenAction, -1);
    }

    public void performActionWithToast(PositionList positionList, final ServerDrivenAction serverDrivenAction, final int i) {
        final int parentPosition = positionList.getParentPosition();
        if (!serverDrivenAction.getAuthNeeded() || v.a().e()) {
            EtsyApiV3Request.a a = EtsyApiV3Request.a.a(CartPage.class, serverDrivenAction.getPath());
            a.a(serverDrivenAction.getRequestMethod());
            if (!v.a().e()) {
                serverDrivenAction.addParam("guest_id", g.a().e());
            }
            if (isAndroidPaySupported()) {
                serverDrivenAction.addParam("supports_android_pay", String.valueOf(true));
            }
            if (serverDrivenAction.getRequestMethod().equals(BaseHttpRequest.POST)) {
                a.a((BaseHttpBody) new JsonBody.a().a(serverDrivenAction.getParams()).f());
            } else {
                a.a(serverDrivenAction.getParams());
            }
            com.etsy.android.lib.core.http.request.a c = com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) a.d()).a((C0065a<ResultType>) new com.etsy.android.lib.core.http.request.d.b<CartPage>() {
                public void a(@NonNull List<CartPage> list, int i, @NonNull com.etsy.android.lib.core.a.a<CartPage> aVar) {
                    if (serverDrivenAction.getRefreshNeeded()) {
                        CartPage cartPage = (CartPage) aVar.h();
                        ((MultiShopCartAdapter) MultiShopCartFragment.this.getAdapter()).updatePage(parentPosition, cartPage);
                        if (cartPage != null) {
                            CartRefreshDelegate.sendBroadcast(MultiShopCartFragment.this.getActivity(), cartPage.getCartCount(), cartPage.getSavedCount(), true, 1);
                        }
                    }
                    if (i != -1) {
                        aj.b((Context) MultiShopCartFragment.this.getActivity(), i);
                    }
                    MultiShopCartFragment.this.showActionLoading(false);
                }

                public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<CartPage> aVar) {
                    Toast.makeText(MultiShopCartFragment.this.getActivity(), str, 1).show();
                    MultiShopCartFragment.this.showActionLoading(false);
                }
            }, (Fragment) this).c();
            if (serverDrivenAction.isImmediatelyRemovable()) {
                getAdapter().removeItem(parentPosition);
            }
            if (serverDrivenAction.getRefreshNeeded()) {
                showActionLoading(true);
            }
            getRequestQueue().a(c);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("cart_action", d.a(serverDrivenAction));
        bundle.putInt("cart_action_position", parentPosition);
        ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) getActivity()).a((j) this)).a(300, (Fragment) this).a(EtsyAction.CART_ACTION, bundle);
    }

    public void showVariationSelectDialog(PositionList positionList, ServerDrivenAction serverDrivenAction) {
        if (!v.a().e()) {
            serverDrivenAction.addParam("guest_id", g.a().e());
        }
        com.etsy.android.ui.nav.e.a(getActivity()).a().a(800, (Fragment) this).a(positionList.getParentPosition(), serverDrivenAction);
    }

    /* access modifiers changed from: protected */
    public void onRetry() {
        onRefresh();
    }

    public void onCartCountsUpdated(int i, int i2, boolean z, int i3) {
        if (!z) {
            return;
        }
        if (i3 == 2) {
            onRefresh();
        } else if (i3 != 1) {
        } else {
            if (i == 0) {
                onRefresh();
            } else if (!TextUtils.isEmpty(getPagination().getApiNextLink())) {
                onLoadContent();
            }
        }
    }
}
