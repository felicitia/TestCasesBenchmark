package com.etsy.android.ui.search.v2;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.h;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.search.SortOrder;
import com.etsy.android.ui.search.v2.SearchOptions.Location.LocationType;
import com.etsy.android.ui.search.v2.SearchOptions.MarketPlace;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RangedProgressBar;
import java.util.ArrayList;
import java.util.List;

public class SearchFiltersSheet {
    private final SwitchCompat A;
    private final SwitchCompat B;
    private final SwitchCompat C;
    private final LinearLayout D;
    private final SwitchCompat E;
    private final LinearLayout F;
    private final SwitchCompat G;
    /* access modifiers changed from: private */
    public final View a;
    /* access modifiers changed from: private */
    public final View b;
    private final View c;
    private final ScrollView d;
    private final b e;
    private final c f = new c();
    /* access modifiers changed from: private */
    public final TimeInterpolator g = new DecelerateInterpolator();
    /* access modifiers changed from: private */
    public final SearchOptions h;
    /* access modifiers changed from: private */
    public final TextView i;
    /* access modifiers changed from: private */
    public final TextView j;
    private final TextView k;
    private final TextView l;
    private final TextView m;
    private final RadioGroup n;
    /* access modifiers changed from: private */
    public final RangedProgressBar o;
    /* access modifiers changed from: private */
    public final TextView p;
    private final SwitchCompat q;
    private final TextView r;
    private final TextView s;
    /* access modifiers changed from: private */
    public final SelectView t;
    /* access modifiers changed from: private */
    public boolean u;
    /* access modifiers changed from: private */
    public List<FacetCount> v;
    /* access modifiers changed from: private */
    public boolean w = false;
    private final LinearLayout x;
    private final SwitchCompat y;
    private final LinearLayout z;

    enum FilterType {
        FILTER_ONSALE,
        OPTION_SORT_ORDER,
        FILTER_CATEGORY,
        FILTER_MARKETPLACE,
        FILTER_PRICE,
        FILTER_FREE_SHIPPING,
        FILTER_1_DAY_SHIPPING,
        FILTER_3_DAY_SHIPPING,
        FILTER_SHIPS_TO,
        FILTER_SHOP_LOCATION,
        FILTER_GIFT_CARDS,
        FILTER_CUSTOMIZABLE,
        FILTER_GIFT_WRAP
    }

    public static class SelectView implements AnimatorListener {
        @Nullable
        private a activeController;
        private final TimeInterpolator interpolator = new AccelerateInterpolator();
        private boolean isShowing;
        private int layerType;
        private final View loadingView;
        private final ViewGroup parentView;

        public interface a {
            View b();

            void c();
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public SelectView(ViewGroup viewGroup, View view) {
            this.parentView = viewGroup;
            this.loadingView = view;
        }

        public void showWith(a aVar) {
            showWith(aVar, true);
        }

        public void showWith(a aVar, boolean z) {
            View b = aVar.b();
            b.setLayoutParams(new LayoutParams(-1, -1));
            b.setBackgroundColor(-1);
            this.parentView.addView(b);
            this.activeController = aVar;
            this.isShowing = true;
            b.bringToFront();
            b.setVisibility(0);
            if (z) {
                b.setAlpha(0.0f);
                b.animate().alpha(1.0f).setDuration(200).setListener(null).setInterpolator(this.interpolator).start();
            }
        }

        public void hide() {
            this.isShowing = false;
            if (this.activeController != null) {
                this.activeController.b().animate().alpha(0.0f).setInterpolator(this.interpolator).setDuration(200).setListener(this).start();
                fireSelectViewIsDone();
            }
        }

        /* access modifiers changed from: private */
        public void fireSelectViewIsDone() {
            if (this.activeController != null) {
                this.activeController.c();
            }
        }

        /* access modifiers changed from: private */
        public void reset() {
            this.isShowing = false;
            if (this.activeController != null) {
                this.activeController.b().setVisibility(8);
            }
        }

        public Context getContext() {
            return this.parentView.getContext();
        }

        public void onAnimationStart(Animator animator) {
            if (this.activeController != null) {
                View b = this.activeController.b();
                this.layerType = b.getLayerType();
                b.setLayerType(2, null);
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (this.activeController != null) {
                View b = this.activeController.b();
                b.setLayerType(this.layerType, null);
                b.setVisibility(8);
                destroy();
            }
        }

        /* access modifiers changed from: private */
        public boolean isShowing() {
            return this.isShowing;
        }

        /* access modifiers changed from: 0000 */
        public void destroy() {
            destroy(true);
        }

        /* access modifiers changed from: 0000 */
        public void destroy(boolean z) {
            if (this.activeController != null) {
                View b = this.activeController.b();
                if (z) {
                    SearchFiltersSheet.b(b);
                }
                this.parentView.removeView(b);
                this.activeController = null;
            }
        }

        public void loadingDidComplete() {
            this.loadingView.setVisibility(8);
        }

        public void loadingWillBegin() {
            this.loadingView.bringToFront();
            this.loadingView.setVisibility(0);
        }
    }

    public class a {
        private final CharSequence b;
        private final FilterType c;

        private a(CharSequence charSequence, FilterType filterType) {
            this.b = charSequence;
            this.c = filterType;
        }

        public CharSequence a() {
            return this.b;
        }

        public void b() {
            switch (this.c) {
                case FILTER_CATEGORY:
                    SearchFiltersSheet.this.h.resetCategoryFacets();
                    break;
                case FILTER_MARKETPLACE:
                    SearchFiltersSheet.this.h.setMarketplace(MarketPlace.MARKETPLACE_ALL_ITEMS);
                    break;
                case FILTER_PRICE:
                    SearchFiltersSheet.this.h.resetPriceLimit();
                    break;
                case FILTER_SHIPS_TO:
                    SearchFiltersSheet.this.h.resetShipsToCountry();
                    break;
                case FILTER_SHOP_LOCATION:
                    SearchFiltersSheet.this.h.getShopLocation().resetToDefault();
                    break;
                case FILTER_GIFT_CARDS:
                    SearchFiltersSheet.this.h.setAcceptsGiftCards(false);
                    break;
                case OPTION_SORT_ORDER:
                    SearchFiltersSheet.this.h.setSortOrder(SortOrder.DEFAULT);
                    break;
                case FILTER_ONSALE:
                    SearchFiltersSheet.this.h.setOnSale(false);
                    break;
                case FILTER_FREE_SHIPPING:
                    SearchFiltersSheet.this.h.setFreeShipping(false);
                    break;
                case FILTER_1_DAY_SHIPPING:
                    SearchFiltersSheet.this.h.setOneDayShipping(false);
                    break;
                case FILTER_3_DAY_SHIPPING:
                    SearchFiltersSheet.this.h.setThreeDayShipping(false);
                    break;
                case FILTER_CUSTOMIZABLE:
                    SearchFiltersSheet.this.h.setCustomizable(false);
                    break;
                case FILTER_GIFT_WRAP:
                    SearchFiltersSheet.this.h.setGiftWrap(false);
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid filter_id ");
                    sb.append(this.c);
                    throw new IllegalArgumentException(sb.toString());
            }
            SearchFiltersSheet.this.i();
        }
    }

    public interface b {
        void a(SearchOptions searchOptions);
    }

    private class c extends TrackingOnClickListener implements AnimatorListener, OnTouchListener, OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, com.etsy.android.ui.search.v2.SearchCategorySelectView.b, com.etsy.android.ui.search.v2.l.a, com.etsy.android.ui.search.v2.m.a, com.etsy.android.ui.search.v2.n.a, com.etsy.android.uikit.view.RangedProgressBar.a {
        private int b;
        private int c;

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        private c() {
        }

        public void onAnimationStart(Animator animator) {
            this.b = SearchFiltersSheet.this.a.getLayerType();
            this.c = SearchFiltersSheet.this.b.getLayerType();
            SearchFiltersSheet.this.a.setLayerType(2, null);
            SearchFiltersSheet.this.b.setLayerType(2, null);
        }

        public void onAnimationEnd(Animator animator) {
            SearchFiltersSheet.this.a.setVisibility(8);
            SearchFiltersSheet.this.b.setVisibility(8);
            SearchFiltersSheet.this.a.setLayerType(this.b, null);
            SearchFiltersSheet.this.b.setLayerType(this.c, null);
            SearchFiltersSheet.this.t.destroy();
        }

        public void onViewClick(View view) {
            switch (view.getId()) {
                case R.id.filters_veil /*2131362264*/:
                case R.id.search_filters_done /*2131362874*/:
                    SearchFiltersSheet.this.b();
                    return;
                case R.id.search_filters_category /*2131362868*/:
                    b();
                    return;
                case R.id.search_filters_clear_all /*2131362870*/:
                    SearchFiltersSheet.this.h.resetToDefault();
                    SearchFiltersSheet.this.i();
                    return;
                case R.id.search_filters_ships_to /*2131362890*/:
                    d();
                    return;
                case R.id.search_filters_shop_location /*2131362892*/:
                    c();
                    return;
                case R.id.search_filters_sort_by /*2131362897*/:
                    e();
                    return;
                default:
                    return;
            }
        }

        private void c() {
            SearchFiltersSheet.this.i.setText(R.string.search_filter_location_title);
            SearchFiltersSheet.c(SearchFiltersSheet.this.j, SearchFiltersSheet.this.g);
            m.a(SearchFiltersSheet.this.t, SearchFiltersSheet.this.h.getShopLocation(), this);
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (radioGroup.getId() == R.id.search_filters_marketplace) {
                SearchFiltersSheet.this.h.setMarketplace(SearchFiltersSheet.c(i));
                SearchFiltersSheet.this.i();
            }
        }

        public void a(int i, int i2) {
            SearchFiltersSheet.this.h.setMinPrice(i);
            SearchFiltersSheet.this.h.setMaxPrice(i2);
            SearchFiltersSheet.this.p.setText(SearchFiltersSheet.b(SearchFiltersSheet.this.b.getResources(), SearchFiltersSheet.this.h, SearchFiltersSheet.this.o.getMinValue(), SearchFiltersSheet.this.o.getMaxValue()));
            SearchFiltersSheet.this.j();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return view.getId() == R.id.filters_root;
        }

        private void d() {
            SearchFiltersSheet.this.i.setText(R.string.search_filter_ships_to_title);
            SearchFiltersSheet.c(SearchFiltersSheet.this.j, SearchFiltersSheet.this.g);
            l.a(SearchFiltersSheet.this.t, SearchFiltersSheet.this.h.getShipsToCountryCode(), this);
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            SearchFiltersSheet.this.w = true;
            SearchFiltersSheet.this.i.setText(R.string.new_search_filter_categories);
            SearchFiltersSheet.c(SearchFiltersSheet.this.j, SearchFiltersSheet.this.g);
            SearchCategorySelectView.a(SearchFiltersSheet.this.t, SearchFiltersSheet.this.h.getCategoryFacets(), SearchFiltersSheet.this.v, this, true);
        }

        private void e() {
            SearchFiltersSheet.this.i.setText(R.string.new_search_filter_sort_by);
            SearchFiltersSheet.c(SearchFiltersSheet.this.j, SearchFiltersSheet.this.g);
            n.a(SearchFiltersSheet.this.t, SearchFiltersSheet.this.h.getSortOrder(), this);
        }

        public void a(SortOrder sortOrder) {
            if (sortOrder != SearchFiltersSheet.this.h.getSortOrder()) {
                SearchFiltersSheet.this.h.setSortOrder(sortOrder);
                SearchFiltersSheet.this.i();
            }
        }

        public void a(List<FacetCount> list) {
            if (!list.equals(SearchFiltersSheet.this.h.getCategoryFacets())) {
                SearchFiltersSheet.this.h.setCategoryFacets(list);
                SearchFiltersSheet.this.i();
            }
        }

        public void a() {
            SearchFiltersSheet.this.w = false;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            switch (compoundButton.getId()) {
                case R.id.search_filters_1_day_shipping /*2131362862*/:
                    SearchFiltersSheet.this.h.setOneDayShipping(z);
                    SearchFiltersSheet.this.i();
                    return;
                case R.id.search_filters_3_day_shipping /*2131362864*/:
                    SearchFiltersSheet.this.h.setThreeDayShipping(z);
                    SearchFiltersSheet.this.i();
                    return;
                case R.id.search_filters_accepts_giftcards /*2131362866*/:
                    SearchFiltersSheet.this.h.setAcceptsGiftCards(z);
                    SearchFiltersSheet.this.i();
                    return;
                case R.id.search_filters_customizable /*2131362872*/:
                    SearchFiltersSheet.this.h.setCustomizable(z);
                    SearchFiltersSheet.this.i();
                    return;
                case R.id.search_filters_free_shipping /*2131362875*/:
                    SearchFiltersSheet.this.h.setFreeShipping(z);
                    SearchFiltersSheet.this.i();
                    return;
                case R.id.search_filters_gift_wrapped /*2131362876*/:
                    SearchFiltersSheet.this.h.setGiftWrap(z);
                    SearchFiltersSheet.this.i();
                    return;
                case R.id.search_filters_onsale /*2131362884*/:
                    SearchFiltersSheet.this.h.setOnSale(z);
                    SearchFiltersSheet.this.i();
                    return;
                default:
                    return;
            }
        }

        public void a(Country country) {
            SearchFiltersSheet.this.h.setShipsTo(country.getIsoCountryCode(), country.getName());
            SearchFiltersSheet.this.i();
        }

        public void a(LocationType locationType, String str) {
            SearchFiltersSheet.this.h.getShopLocation().set(locationType, str);
            SearchFiltersSheet.this.i();
        }
    }

    private static void b(View view, TimeInterpolator timeInterpolator) {
        if (view.getVisibility() != 0) {
            view.setVisibility(0);
            view.setClickable(true);
            view.animate().alpha(1.0f).setDuration(200).setInterpolator(timeInterpolator).setListener(null).start();
        }
    }

    /* access modifiers changed from: private */
    public static void c(final View view, TimeInterpolator timeInterpolator) {
        if (view.getVisibility() != 4) {
            view.animate().alpha(0.0f).setDuration(200).setInterpolator(timeInterpolator).setListener(new HardwareAnimatorListener(view) {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    view.setVisibility(4);
                    view.setClickable(false);
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    public static String b(Resources resources, SearchOptions searchOptions, int i2, int i3) {
        boolean z2 = searchOptions.getMinPrice() <= i2;
        boolean z3 = searchOptions.getMaxPrice() >= i3;
        if (z2 && z3) {
            return resources.getString(R.string.search_filter_allprices);
        }
        if (z3) {
            return resources.getString(R.string.search_filter_prices_high, new Object[]{new EtsyMoney((float) searchOptions.getMinPrice(), "USD").format(), new EtsyMoney((float) searchOptions.getMaxPrice(), "USD").format()});
        }
        return resources.getString(R.string.search_filter_price, new Object[]{new EtsyMoney((float) searchOptions.getMinPrice(), "USD").format(), new EtsyMoney((float) searchOptions.getMaxPrice(), "USD").format()});
    }

    public SearchFiltersSheet(View view, SearchOptions searchOptions, b bVar, h hVar) {
        this.h = searchOptions;
        this.e = bVar;
        this.c = view;
        this.a = view.findViewById(R.id.filters_veil);
        this.a.setOnClickListener(this.f);
        this.a.bringToFront();
        this.b = view.findViewById(R.id.filters_root);
        this.b.setOnTouchListener(this.f);
        this.b.bringToFront();
        Boolean valueOf = Boolean.valueOf(hVar.c(com.etsy.android.lib.config.b.cz));
        this.d = (ScrollView) this.b.findViewById(R.id.search_filters_main);
        this.i = (TextView) this.b.findViewById(R.id.search_filters_header_text);
        this.j = (TextView) this.b.findViewById(R.id.search_filters_clear_all);
        this.j.setOnClickListener(this.f);
        this.k = (TextView) this.b.findViewById(R.id.search_filters_done);
        this.k.setOnClickListener(this.f);
        this.l = (TextView) this.b.findViewById(R.id.search_filters_sort_by_text);
        this.m = (TextView) this.b.findViewById(R.id.search_filters_category_text);
        this.n = (RadioGroup) this.b.findViewById(R.id.search_filters_marketplace);
        this.o = (RangedProgressBar) this.b.findViewById(R.id.search_filters_price_bar);
        this.p = (TextView) this.b.findViewById(R.id.search_filters_price_text);
        this.q = (SwitchCompat) this.b.findViewById(R.id.search_filters_accepts_giftcards);
        this.q.setOnCheckedChangeListener(this.f);
        this.r = (TextView) this.b.findViewById(R.id.search_filters_shop_location_text);
        this.s = (TextView) this.b.findViewById(R.id.search_filters_ships_to_text);
        this.t = new SelectView((ViewGroup) this.b.findViewById(R.id.search_filters_content), this.b.findViewById(R.id.search_filters_select_view_loading_view));
        this.x = (LinearLayout) this.b.findViewById(R.id.search_filters_onsale_layout);
        int i2 = 8;
        this.x.setVisibility(valueOf.booleanValue() ? 0 : 8);
        this.y = (SwitchCompat) this.b.findViewById(R.id.search_filters_onsale);
        this.y.setOnCheckedChangeListener(this.f);
        this.z = (LinearLayout) this.b.findViewById(R.id.search_filters_shipping_layout);
        this.z.setVisibility(valueOf.booleanValue() ? 0 : 8);
        this.A = (SwitchCompat) this.b.findViewById(R.id.search_filters_free_shipping);
        this.A.setOnCheckedChangeListener(this.f);
        this.B = (SwitchCompat) this.b.findViewById(R.id.search_filters_1_day_shipping);
        this.B.setOnCheckedChangeListener(this.f);
        this.C = (SwitchCompat) this.b.findViewById(R.id.search_filters_3_day_shipping);
        this.C.setOnCheckedChangeListener(this.f);
        this.D = (LinearLayout) this.b.findViewById(R.id.search_filters_customizable_layout);
        this.D.setVisibility(valueOf.booleanValue() ? 0 : 8);
        this.E = (SwitchCompat) this.b.findViewById(R.id.search_filters_customizable);
        this.E.setOnCheckedChangeListener(this.f);
        this.F = (LinearLayout) this.b.findViewById(R.id.search_filters_gift_wrapped_layout);
        LinearLayout linearLayout = this.F;
        if (valueOf.booleanValue()) {
            i2 = 0;
        }
        linearLayout.setVisibility(i2);
        this.G = (SwitchCompat) this.b.findViewById(R.id.search_filters_gift_wrapped);
        this.G.setOnCheckedChangeListener(this.f);
    }

    private void b(int i2) {
        this.n.setOnCheckedChangeListener(null);
        this.n.check(i2);
        this.n.setOnCheckedChangeListener(this.f);
    }

    private void a(SwitchCompat switchCompat, boolean z2) {
        switchCompat.setOnCheckedChangeListener(null);
        switchCompat.setChecked(z2);
        switchCompat.setOnCheckedChangeListener(this.f);
    }

    private void a(SearchOptions searchOptions) {
        if (!this.t.isShowing()) {
            this.i.setText(R.string.new_search_filter_filters);
            if (!this.h.hasDefaults()) {
                b(this.j, this.g);
            } else {
                c(this.j, this.g);
            }
        }
    }

    private void b(SearchOptions searchOptions) {
        Resources resources = this.b.getResources();
        this.l.setText(SortOrder.getSortOrderLabel(this.b.getContext(), searchOptions.getSortOrder()));
        if (!searchOptions.hasCategoryFacets()) {
            this.m.setText(R.string.new_search_filter_categories_all);
        } else {
            this.m.setText(searchOptions.getSelectedCategoryFacet().getName());
        }
        b(a(searchOptions.getMarketplace()));
        this.o.setProgress(searchOptions.getMinPrice(), searchOptions.getMaxPrice());
        this.p.setText(b(this.b.getResources(), searchOptions, this.o.getMinValue(), this.o.getMaxValue()));
        a(this.q, searchOptions.getAcceptsGiftCards());
        a(this.A, searchOptions.getFreeShipping());
        a(this.B, searchOptions.getOneDayShipping());
        a(this.C, searchOptions.getThreeDayShipping());
        a(this.E, searchOptions.getCustomizable());
        a(this.y, searchOptions.getOnSale());
        a(this.G, searchOptions.getGiftWrap());
        if (searchOptions.getShopLocation().isAnywhere()) {
            this.r.setText(R.string.new_search_filter_shop_location_anywhere);
        } else {
            this.r.setText(this.h.getShopLocation().getLocation(resources));
        }
        this.s.setText(searchOptions.getShipsToCountryName());
        a(searchOptions);
    }

    /* access modifiers changed from: private */
    public static MarketPlace c(int i2) {
        switch (i2) {
            case R.id.search_filters_marketplace_handmade /*2131362882*/:
                return MarketPlace.MARKETPLACE_HANDMADE;
            case R.id.search_filters_marketplace_vintage /*2131362883*/:
                return MarketPlace.MARKETPLACE_VINTAGE;
            default:
                return MarketPlace.MARKETPLACE_ALL_ITEMS;
        }
    }

    private static int a(MarketPlace marketPlace) {
        switch (marketPlace) {
            case MARKETPLACE_HANDMADE:
                return R.id.search_filters_marketplace_handmade;
            case MARKETPLACE_VINTAGE:
                return R.id.search_filters_marketplace_vintage;
            default:
                return R.id.search_filters_marketplace_all;
        }
    }

    public void a(List<FacetCount> list) {
        a(this.f);
        this.v = list;
        this.t.reset();
        b(this.h);
        this.d.fullScroll(33);
        this.a.setAlpha(0.0f);
        this.a.setVisibility(0);
        this.a.clearAnimation();
        this.a.animate().alpha(1.0f).setDuration(200).setInterpolator(this.g).start();
        a();
    }

    private void a(@Nullable c cVar) {
        this.b.findViewById(R.id.search_filters_sort_by).setOnClickListener(cVar);
        this.b.findViewById(R.id.search_filters_category).setOnClickListener(cVar);
        this.n.setOnCheckedChangeListener(cVar);
        this.o.setSeekBarChangeListener(cVar);
        this.q.setOnCheckedChangeListener(cVar);
        this.b.findViewById(R.id.search_filters_shop_location).setOnClickListener(cVar);
        this.b.findViewById(R.id.search_filters_ships_to).setOnClickListener(cVar);
    }

    public void a() {
        int i2;
        Resources resources = this.c.getResources();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.b.getLayoutParams();
        if (l.c(this.b)) {
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.new_search_filter_sheet_centered_margin);
            layoutParams.width = resources.getDimensionPixelOffset(R.dimen.new_search_filter_sheet_centered_width);
            layoutParams.height = -2;
            layoutParams.topMargin = dimensionPixelOffset;
            layoutParams.bottomMargin = dimensionPixelOffset;
            layoutParams.gravity = 17;
            i2 = this.c.getHeight() - dimensionPixelOffset;
        } else {
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.new_search_filter_sheet_top_margin);
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.topMargin = dimensionPixelSize;
            i2 = this.c.getHeight() - dimensionPixelSize;
        }
        this.b.setLayoutParams(layoutParams);
        this.b.setTranslationY((float) i2);
        this.b.setVisibility(0);
        this.b.clearAnimation();
        this.b.animate().translationY(0.0f).setDuration(200).setInterpolator(this.g).setListener(null).start();
    }

    public void b() {
        a((c) null);
        this.a.clearAnimation();
        this.a.animate().alpha(0.0f).setDuration(200).setInterpolator(this.g).start();
        this.b.clearAnimation();
        this.b.animate().translationY((float) this.c.getHeight()).setDuration(200).setInterpolator(this.g).setListener(this.f).start();
        if (this.t.isShowing()) {
            this.t.fireSelectViewIsDone();
        }
    }

    public boolean c() {
        if (!this.t.isShowing()) {
            b();
        } else {
            this.t.hide();
            a(this.h);
        }
        return true;
    }

    public boolean d() {
        return this.b.getVisibility() == 0;
    }

    public SearchOptions e() {
        return this.h;
    }

    public void a(FacetCount facetCount) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(facetCount);
        this.h.setCategoryFacets(arrayList);
        b(this.h);
    }

    public void a(List<FacetCount> list, String str) {
        ArrayList arrayList = new ArrayList();
        if (FacetCount.buildPathToFacet(list, str, arrayList)) {
            this.h.setCategoryFacets(arrayList);
            b(this.h);
            if (this.t.isShowing() && this.w) {
                this.t.destroy(false);
                SearchCategorySelectView.a(this.t, this.h.getCategoryFacets(), this.v, this.f, false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        b(this.h);
        this.e.a(this.h);
        this.u = false;
    }

    /* access modifiers changed from: private */
    public void j() {
        if (!this.u) {
            this.u = true;
            this.b.postDelayed(new Runnable() {
                public void run() {
                    if (SearchFiltersSheet.this.u) {
                        SearchFiltersSheet.this.i();
                    }
                }
            }, 500);
        }
    }

    /* access modifiers changed from: private */
    public static void b(View... viewArr) {
        for (View animate : viewArr) {
            animate.animate().setListener(null).cancel();
        }
    }

    public void f() {
        b(this.j, this.a, this.b);
    }

    private static String a(Resources resources, MarketPlace marketPlace) {
        switch (marketPlace) {
            case MARKETPLACE_HANDMADE:
                return resources.getString(R.string.handmade);
            case MARKETPLACE_VINTAGE:
                return resources.getString(R.string.vintage);
            default:
                return resources.getString(R.string.all_items);
        }
    }

    private static String a(Resources resources, int i2, int i3) {
        if (i2 != 0 && i3 != 1000) {
            return resources.getString(R.string.new_search_empty_view_price_range_desc, new Object[]{new EtsyMoney((float) i2, "USD").format(), new EtsyMoney((float) i3, "USD").format()});
        } else if (i2 != 0) {
            return resources.getString(R.string.new_search_empty_view_price_min_desc, new Object[]{new EtsyMoney((float) i2, "USD").format()});
        } else {
            return resources.getString(R.string.new_search_empty_view_price_max_desc, new Object[]{new EtsyMoney((float) i3, "USD").format()});
        }
    }

    public List<a> g() {
        Resources resources = this.c.getResources();
        ArrayList arrayList = new ArrayList();
        if (this.h.hasCategoryFacets()) {
            arrayList.add(new a(b.a(this.h.getCategoryFacets()), FilterType.FILTER_CATEGORY));
        }
        if (this.h.hasMarketplace()) {
            arrayList.add(new a(a(resources, this.h.getMarketplace()), FilterType.FILTER_MARKETPLACE));
        }
        if (this.h.hasMinPrice() || this.h.hasMaxPrice()) {
            arrayList.add(new a(a(resources, this.h.getMinPrice(), this.h.getMaxPrice()), FilterType.FILTER_PRICE));
        }
        if (this.h.hasShipsToCountry()) {
            arrayList.add(new a(resources.getString(R.string.new_search_empty_view_ships_to_desc, new Object[]{this.h.getShipsToCountryName()}), FilterType.FILTER_SHIPS_TO));
        }
        if (this.h.getAcceptsGiftCards()) {
            arrayList.add(new a(resources.getString(R.string.new_search_empty_view_gift_cards_desc), FilterType.FILTER_GIFT_CARDS));
        }
        if (!this.h.getShopLocation().isAnywhere()) {
            arrayList.add(new a(resources.getString(R.string.new_search_empty_view_shop_location_desc, new Object[]{this.h.getShopLocation().getLocation(this.b.getResources())}), FilterType.FILTER_SHOP_LOCATION));
        }
        if (this.h.getOnSale()) {
            arrayList.add(new a(resources.getString(R.string.new_search_filter_onsale), FilterType.FILTER_ONSALE));
        }
        if (this.h.getFreeShipping()) {
            arrayList.add(new a(resources.getString(R.string.new_search_filter_free_shipping), FilterType.FILTER_FREE_SHIPPING));
        }
        if (this.h.getOneDayShipping()) {
            arrayList.add(new a(resources.getString(R.string.new_search_filter_1_day_shipping), FilterType.FILTER_1_DAY_SHIPPING));
        }
        if (this.h.getThreeDayShipping()) {
            arrayList.add(new a(resources.getString(R.string.new_search_filter_3_day_shipping), FilterType.FILTER_3_DAY_SHIPPING));
        }
        if (this.h.getCustomizable()) {
            arrayList.add(new a(resources.getString(R.string.new_search_filter_customizable), FilterType.FILTER_CUSTOMIZABLE));
        }
        if (this.h.getGiftWrap()) {
            arrayList.add(new a(resources.getString(R.string.new_search_filter_gift_wrapped), FilterType.FILTER_GIFT_WRAP));
        }
        return arrayList;
    }

    public List<a> h() {
        Resources resources = this.c.getResources();
        List<a> g2 = g();
        SortOrder sortOrder = this.h.getSortOrder();
        if (sortOrder != SortOrder.DEFAULT) {
            g2.add(new a(resources.getString(R.string.new_search_sorted_by_desc, new Object[]{SortOrder.getSortOrderLabel(this.c.getContext(), sortOrder)}), FilterType.OPTION_SORT_ORDER));
        }
        return g2;
    }
}
