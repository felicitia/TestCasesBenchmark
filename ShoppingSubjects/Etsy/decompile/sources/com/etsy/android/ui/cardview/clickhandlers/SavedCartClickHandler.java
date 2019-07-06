package com.etsy.android.ui.cardview.clickhandlers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import com.etsy.android.R;
import com.etsy.android.e.a;
import com.etsy.android.lib.c.g;
import com.etsy.android.lib.config.b.h;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.lib.models.apiv3.cart.SavedCart;
import com.etsy.android.lib.models.apiv3.cart.SavedCartListing;
import com.etsy.android.lib.util.aj;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.d;
import io.reactivex.functions.Consumer;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;
import java.util.List;

public class SavedCartClickHandler extends b<SavedCart> {
    /* access modifiers changed from: private */
    public boolean a;
    /* access modifiers changed from: private */
    public d b;
    private g c;
    private boolean d;
    /* access modifiers changed from: private */
    public PublishSubject<String> e = PublishSubject.a();

    private enum Action {
        REMOVE,
        MOVE_TO_FAVORITES,
        MOVE_TO_CART
    }

    static final /* synthetic */ void a(DialogInterface dialogInterface, int i) {
    }

    public void a(SavedCart savedCart) {
    }

    public SavedCartClickHandler(FragmentActivity fragmentActivity, @NonNull w wVar, d dVar, g gVar) {
        super(fragmentActivity, wVar);
        this.b = dVar;
        this.c = gVar;
        this.d = ((TrackingBaseActivity) fragmentActivity).getConfigMap().c(h.a);
    }

    public q<String> a() {
        return this.e;
    }

    public void a(SavedCartListing savedCartListing) {
        e.a(d()).a().a(savedCartListing.getListingId());
    }

    /* access modifiers changed from: protected */
    public void a(SavedCart savedCart, int i) {
        a(Action.REMOVE, savedCart, i, "/etsyapps/v3/bespoke/member/carts/saved-for-later/%s", null);
    }

    public void b(SavedCart savedCart, int i) {
        SavedCart savedCart2 = savedCart;
        int i2 = i;
        a(Action.MOVE_TO_FAVORITES, savedCart2, i2, "/etsyapps/v3/bespoke/member/carts/saved-for-later/%s/to-favorites", d().getString(R.string.toast_move_to_favorites));
    }

    public void c(SavedCart savedCart, int i) {
        SavedCart savedCart2 = savedCart;
        int i2 = i;
        a(Action.MOVE_TO_CART, savedCart2, i2, "/etsyapps/v3/bespoke/member/carts/saved-for-later/%s/to-cart", d().getString(R.string.toast_move_to_cart));
    }

    public void d(SavedCart savedCart, int i) {
        Builder builder = new Builder(d());
        builder.setMessage((int) R.string.remove_item_confirm_msg);
        builder.setPositiveButton((int) R.string.yes, (OnClickListener) new h(this, savedCart, i));
        builder.setNegativeButton((int) R.string.no, i.a);
        builder.setNeutralButton((int) R.string.move_item_to_favorites, (OnClickListener) new j(this, savedCart, i));
        builder.create().show();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(SavedCart savedCart, int i, DialogInterface dialogInterface, int i2) {
        a(savedCart, i);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(SavedCart savedCart, int i, DialogInterface dialogInterface, int i2) {
        b(savedCart, i);
    }

    private void a(Action action, SavedCart savedCart, int i, String str, String str2) {
        q qVar;
        if (this.a) {
            this.e.onNext(d().getResources().getString(R.string.toast_saved_cart_processing));
            return;
        }
        this.a = true;
        savedCart.getViewState().setIsLoading(true);
        this.b.onItemChanged(i);
        if (this.d) {
            String id = savedCart.getId().getId();
            a aVar = (a) this.c.a().a(a.class);
            switch (action) {
                case REMOVE:
                    qVar = aVar.a(id);
                    break;
                case MOVE_TO_CART:
                    qVar = aVar.c(id);
                    break;
                case MOVE_TO_FAVORITES:
                    qVar = aVar.b(id);
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Saved cart action ");
                    sb.append(action.name());
                    sb.append(" not supported.");
                    throw new IllegalStateException(sb.toString());
            }
            qVar.b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new k<Object>(this, savedCart, str2, i), (Consumer<? super Throwable>) new l<Object>(this, action, savedCart, i));
        } else {
            com.etsy.android.lib.core.http.request.d.a a2 = com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) ((EtsyApiV3Request.a) EtsyApiV3Request.a.a(CartPage.class, String.format(str, new Object[]{savedCart.getId().getId()})).a(3)).d());
            final SavedCart savedCart2 = savedCart;
            final String str3 = str2;
            final int i2 = i;
            final Action action2 = action;
            AnonymousClass1 r0 = new com.etsy.android.lib.core.http.request.d.b<CartPage>() {
                public void a(@NonNull List<CartPage> list, int i, @NonNull com.etsy.android.lib.core.a.a<CartPage> aVar) {
                    SavedCartClickHandler.this.a = false;
                    savedCart2.getViewState().setIsLoading(false);
                    if (!TextUtils.isEmpty(str3)) {
                        aj.a((Context) SavedCartClickHandler.this.d(), str3);
                    }
                    SavedCartClickHandler.this.b.onRemoveItem(i2);
                    CartPage cartPage = (CartPage) aVar.h();
                    CartRefreshDelegate.sendBroadcast(SavedCartClickHandler.this.d(), cartPage.getCartCount(), cartPage.getSavedCount(), true, 2);
                }

                public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<CartPage> aVar) {
                    SavedCartClickHandler.this.a = false;
                    SavedCartClickHandler.this.e.onNext(SavedCartClickHandler.this.d().getResources().getString(SavedCartClickHandler.this.a(action2)));
                    savedCart2.getViewState().setIsLoading(false);
                    SavedCartClickHandler.this.b.onItemChanged(i2);
                }
            };
            v.a().j().a((Object) this, (com.etsy.android.lib.core.http.request.a<?, ?, ?>) (com.etsy.android.lib.core.http.request.d) ((com.etsy.android.lib.core.http.request.d.a) a2.a((C0065a<ResultType>) r0, (Activity) d())).c());
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(SavedCart savedCart, String str, int i, CartPage cartPage) throws Exception {
        this.a = false;
        savedCart.getViewState().setIsLoading(false);
        if (!TextUtils.isEmpty(str)) {
            aj.a((Context) d(), str);
        }
        this.b.onRemoveItem(i);
        CartRefreshDelegate.sendBroadcast(d(), cartPage.getCartCount(), cartPage.getSavedCount(), true, 2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Action action, SavedCart savedCart, int i, Throwable th) throws Exception {
        this.a = false;
        this.e.onNext(d().getResources().getString(a(action)));
        savedCart.getViewState().setIsLoading(false);
        this.b.onItemChanged(i);
    }

    /* access modifiers changed from: private */
    @StringRes
    public int a(Action action) {
        switch (action) {
            case REMOVE:
                return R.string.toast_saved_cart_error_removing;
            case MOVE_TO_CART:
                return R.string.toast_saved_cart_error_move_to_cart;
            case MOVE_TO_FAVORITES:
                return R.string.toast_saved_cart_error_favorites;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported action ");
                sb.append(action.name());
                throw new IllegalStateException(sb.toString());
        }
    }
}
