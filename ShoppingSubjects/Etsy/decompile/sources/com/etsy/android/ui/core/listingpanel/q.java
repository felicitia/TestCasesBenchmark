package com.etsy.android.ui.core.listingpanel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.apiv3.ShippingAddressPreference;
import com.etsy.android.lib.models.apiv3.ShippingDisplay;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.apiv3.StructuredShopShipping;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.CountryUtil.d;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.s;
import com.etsy.android.lib.util.sharedprefs.b;
import com.etsy.android.ui.core.i;
import com.etsy.android.ui.core.k;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.shop.policies.StructuredShopPaymentsView;
import com.etsy.android.uikit.view.shop.policies.StructuredShopRefundsView;
import com.etsy.android.uikit.view.shop.policies.StructuredShopShippingView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ListingPanelShippingAndPolicies */
public class q extends d implements d {
    private ViewStub A;
    private View B;
    private ViewStub C;
    private View D;
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private TextView J;
    private final boolean K;
    /* access modifiers changed from: private */
    public ListingShippingDetails L;
    private boolean M;
    private List<Country> N;
    private Country O;
    private ShippingOption P;
    private z Q;
    private y R;
    @NonNull
    private final i S;
    @NonNull
    private final b T;
    @NonNull
    private final com.etsy.android.lib.f.a U;
    private PostalCodeTextWatcher V;
    @NonNull
    private io.reactivex.disposables.a W = new io.reactivex.disposables.a();
    private View j;
    private TextView k;
    private TextView l;
    private LinearLayout m;
    private TextView n;
    private TextView o;
    private TextView p;
    private LinearLayout q;
    private Button r;
    private EditText s;
    private TextView t;
    private StructuredShopShippingView u;
    private StructuredShopPaymentsView v;
    private StructuredShopRefundsView w;
    private View x;
    private TextView y;
    private TextView z;

    /* compiled from: ListingPanelShippingAndPolicies */
    public class a implements com.etsy.android.uikit.view.shop.policies.StructuredShopPoliciesView.a {
        public a() {
        }

        public void a() {
            q.this.t();
        }
    }

    public q(Listing listing, BaseActivity baseActivity, @NonNull w wVar, boolean z2, @NonNull i iVar, @NonNull b bVar, @NonNull com.etsy.android.lib.f.a aVar) {
        super(listing, baseActivity, wVar);
        this.K = z2;
        this.T = bVar;
        this.U = aVar;
        this.S = iVar;
        a(R.id.panel_details_shipping_and_policies, R.id.panel_title_shipping_and_policies, R.id.img_shipping_and_policies_open, R.id.txt_shipping_and_policies_title);
    }

    /* access modifiers changed from: protected */
    public void c() {
        u();
        w();
        v();
        x();
        z();
        A();
        B();
        C();
        D();
    }

    public void s() {
        if (this.b.isSoldOut()) {
            this.c.findViewById(R.id.section_calculated_shipping).setVisibility(8);
            G();
        } else if (this.L == null) {
            Z();
            CountryUtil.a((d) this, true);
        } else {
            a(this.L);
            G();
        }
    }

    private void u() {
        this.j = this.d.findViewById(R.id.calculated_shipping_view_loading);
        this.k = (TextView) this.c.findViewById(R.id.heading_calculated_shipping_cost);
        this.l = (TextView) this.c.findViewById(R.id.button_shipping_cost_calculate);
        this.m = (LinearLayout) this.c.findViewById(R.id.section_shipping_costs_view_only);
        this.n = (TextView) this.c.findViewById(R.id.txt_shipping_costs_view_only_destination);
        this.o = (TextView) this.c.findViewById(R.id.button_shipping_costs_view_only_update);
        this.p = (TextView) this.c.findViewById(R.id.txt_shipping_costs_view_only_cost);
        this.q = (LinearLayout) this.c.findViewById(R.id.section_shipping_costs_edit);
        this.r = (Button) this.c.findViewById(R.id.button_shipping_costs_edit_country);
        this.s = (EditText) this.c.findViewById(R.id.input_shipping_costs_edit_zip);
        this.t = (TextView) this.c.findViewById(R.id.txt_shipping_cost_error);
        this.u = (StructuredShopShippingView) this.c.findViewById(R.id.structured_policies_shipping);
        this.v = (StructuredShopPaymentsView) this.c.findViewById(R.id.structured_policies_payments);
        this.w = (StructuredShopRefundsView) this.c.findViewById(R.id.structured_policies_refunds);
        this.x = this.c.findViewById(R.id.section_seller_details);
        this.y = (TextView) this.c.findViewById(R.id.txt_seller_details);
        this.z = (TextView) this.c.findViewById(R.id.btn_seller_details_contact);
        this.A = (ViewStub) this.c.findViewById(R.id.additional_terms);
        this.C = (ViewStub) this.c.findViewById(R.id.dispute_resolution);
        this.E = (TextView) this.d.findViewById(R.id.gift_message_available);
        this.F = (TextView) this.d.findViewById(R.id.gift_wrap_available);
        this.G = (TextView) this.d.findViewById(R.id.estimated_delivery_second_text);
        this.H = (TextView) this.d.findViewById(R.id.estimated_delivery_first_line);
        this.J = (TextView) this.c.findViewById(R.id.txt_shipping_time);
        this.I = (TextView) this.c.findViewById(R.id.subhead_shipping_time);
        E();
        if (!this.b.isDigitalDownload()) {
            this.c.findViewById(R.id.section_calculated_shipping).setVisibility(0);
            M();
            b((String) null);
            return;
        }
        ((TextView) this.f).setText(R.string.shop_policies);
    }

    private void v() {
        String str;
        TextView textView = (TextView) this.c.findViewById(R.id.subhead_shipping_time);
        TextView textView2 = (TextView) this.c.findViewById(R.id.txt_shipping_time);
        TextView textView3 = (TextView) this.c.findViewById(R.id.txt_file_delivery);
        int i = 8;
        textView.setVisibility(this.b.isDigitalDownload() ? 8 : 0);
        textView2.setVisibility(this.b.isDigitalDownload() ? 8 : 0);
        if (this.b.isDigitalDownload()) {
            i = 0;
        }
        textView3.setVisibility(i);
        if (this.b.isDigitalDownload()) {
            textView3.setText(Html.fromHtml(this.c.getResources().getString(R.string.structured_shipping_digital_message, new Object[]{p().b(com.etsy.android.lib.config.b.l.a.a)})));
            textView3.setMovementMethod(LinkMovementMethod.getInstance());
        } else if (this.b.getProcessingDaysMin() > 0 || this.b.getProcessingDaysMax() > 0) {
            if (this.b.getProcessingDaysMin() == this.b.getProcessingDaysMax()) {
                if (this.b.getProcessingDaysMin() < 5 || this.b.getProcessingDaysMin() % 5 != 0) {
                    str = this.c.getResources().getQuantityString(R.plurals.shipping_processing_days, this.b.getProcessingDaysMin(), new Object[]{Integer.valueOf(this.b.getProcessingDaysMin())});
                } else {
                    int processingDaysMin = this.b.getProcessingDaysMin() / 5;
                    str = this.c.getResources().getQuantityString(R.plurals.weeks_count, processingDaysMin, new Object[]{Integer.valueOf(processingDaysMin)});
                }
            } else if (this.b.getProcessingDaysMax() < 5 || this.b.getProcessingDaysMax() % 5 != 0 || this.b.getProcessingDaysMin() < 5 || this.b.getProcessingDaysMin() % 5 != 0) {
                str = this.c.getResources().getString(R.string.shipping_processing_days_range, new Object[]{Integer.valueOf(this.b.getProcessingDaysMin()), Integer.valueOf(this.b.getProcessingDaysMax())});
            } else {
                str = this.c.getResources().getString(R.string.weeks_range, new Object[]{Integer.valueOf(this.b.getProcessingDaysMin() / 5), Integer.valueOf(this.b.getProcessingDaysMax() / 5)});
            }
            textView2.setText(str);
        } else {
            textView.setText(R.string.structured_shipping_processing_time);
            textView2.setText(Html.fromHtml(this.c.getResources().getString(R.string.structured_shipping_listing_no_processing_time)));
            EtsyLinkify.a(textView2, false, (OnClickListener) new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    q.this.t();
                }
            });
        }
    }

    private void w() {
        TextView textView = (TextView) this.c.findViewById(R.id.heading_shipping);
        if (this.b.isDigitalDownload()) {
            textView.setText(R.string.structured_shipping_digital_title);
        } else if (this.b.getShippingInfo().size() > 0) {
            textView.setText(String.format(this.c.getResources().getString(R.string.shipping_ships_from), new Object[]{((ShippingInfo) this.b.getShippingInfo().get(0)).getOriginCountryName()}));
        } else {
            textView.setText(R.string.structured_shipping);
        }
    }

    private void x() {
        y();
        this.u.setExpanded(false);
    }

    private void y() {
        if (this.b.getShop() == null || !this.b.getShop().isUsingStructuredPolicies() || this.b.isDigitalDownload() || this.b.getShop().getStructuredPolicies() == null) {
            this.u.setVisibility(8);
            return;
        }
        StructuredShopShipping shipping = this.b.getShop().getStructuredPolicies().getShipping();
        if (shipping.hasSetEstimates() || shipping.shipsInternational()) {
            this.u.setVisibility(0);
            this.u.setStructuredShopShipping(shipping, false);
            return;
        }
        this.u.setVisibility(8);
    }

    private void z() {
        if (this.b.getShop() == null || !this.b.getShop().isUsingStructuredPolicies() || this.b.getShop().getStructuredPolicies() == null) {
            this.v.setVisibility(8);
            return;
        }
        this.v.setStructuredShopPayments(this.b.getShop().getStructuredPolicies().getPayments(), new a());
        this.v.setExpanded(false);
    }

    private void A() {
        if (this.b.getShop() == null || !this.b.getShop().isUsingStructuredPolicies() || this.b.getShop().getStructuredPolicies() == null) {
            this.w.setVisibility(8);
            return;
        }
        this.w.setStructuredShopRefunds(this.b.getShop().getStructuredPolicies().getRefunds(), new a());
        this.w.setExpanded(false);
    }

    private void B() {
        if (this.b.getShop() == null) {
            this.x.setVisibility(8);
            return;
        }
        SellerDetails sellerDetails = this.b.getShop().getSellerDetails();
        if (sellerDetails == null || !sellerDetails.hasDetails()) {
            this.x.setVisibility(8);
        } else {
            this.x.setVisibility(0);
            this.y.setText(sellerDetails.getFormattedDetails());
            this.z.setText(this.c.getResources().getString(R.string.seller_details_contact, new Object[]{this.b.getShopName()}));
            this.z.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    q.this.t();
                }
            });
        }
    }

    private void C() {
        final String str;
        boolean z2;
        if (this.K) {
            Shop shop = this.b.getShop();
            if (shop == null || !shop.isUsingStructuredPolicies() || shop.getStructuredPolicies() == null) {
                str = null;
                z2 = false;
            } else {
                StructuredShopPolicies structuredPolicies = shop.getStructuredPolicies();
                str = structuredPolicies.getTermsAndConditions();
                z2 = structuredPolicies.includeResolutionLink();
            }
            if (z2) {
                this.D = this.C.inflate();
                EtsyLinkify.a((Context) this.a, (TextView) this.D.findViewById(R.id.txt_dispute_resolution), false);
            } else if (this.D != null) {
                this.D.setVisibility(8);
            }
            if (af.b(str)) {
                this.B = this.A.inflate();
                TextView textView = (TextView) this.B.findViewById(R.id.txt_terms_and_conditions);
                textView.setText(Html.fromHtml(this.c.getResources().getString(R.string.terms_and_conditions_read_more, new Object[]{this.b.getShopName()})));
                EtsyLinkify.a(textView, false, (OnClickListener) new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        e.a((Activity) q.this.a).k(str);
                    }
                });
            } else if (this.B != null) {
                this.B.setVisibility(8);
            }
        }
    }

    private void D() {
        if (this.b.getGiftInfo() == null) {
            return;
        }
        if (this.b.getGiftInfo().isEligible() || this.b.getGiftInfo().offersGiftMessage()) {
            int i = 0;
            this.d.findViewById(R.id.listing_gift_options_container).setVisibility(0);
            this.E.setVisibility(this.b.getGiftInfo().offersGiftMessage() ? 0 : 8);
            TextView textView = this.F;
            if (!this.b.getGiftInfo().isEligible()) {
                i = 8;
            }
            textView.setVisibility(i);
            this.F.setOnClickListener(new r(this));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void c(View view) {
        e.a((FragmentActivity) this.a).a().a(this.b.getShopName(), this.b.getGiftInfo().getDescription(), this.b.getGiftInfo().getPreviewImage());
    }

    private void E() {
        AnonymousClass5 r0 = new TrackingOnClickListener(this.b) {
            public void onViewClick(View view) {
                q.this.I();
            }
        };
        this.o.setOnClickListener(new TrackingOnClickListener(this.b) {
            public void onViewClick(View view) {
                q.this.J();
            }
        });
        this.l.setOnClickListener(r0);
        this.r.setOnClickListener(r0);
        aa();
        this.V = new PostalCodeTextWatcher(this.s);
        this.s.addTextChangedListener(this.V);
        this.s.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null && i != 6) {
                    return false;
                }
                q.this.c(textView.getText().toString());
                s.a((View) textView);
                return true;
            }
        });
    }

    @CallSuper
    public void b() {
        super.b();
        aa();
        this.W.a();
    }

    private void F() {
        this.j.setBackgroundColor(this.j.getResources().getColor(R.color.sk_white));
        this.j.setVisibility(0);
    }

    private void G() {
        this.j.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void j() {
        ab();
        H();
    }

    /* access modifiers changed from: protected */
    public void k() {
        H();
    }

    /* access modifiers changed from: protected */
    public void l() {
        s.a((View) this.s);
    }

    private void H() {
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = -2;
        this.d.setLayoutParams(layoutParams);
        if (b(false) && this.P != null) {
            O();
            K();
            Q();
        } else if (b(false) && this.L != null && this.P == null) {
            S();
        } else if (this.O != null || !TextUtils.isEmpty(this.s.getText())) {
            N();
            L();
            Q();
        } else {
            O();
            L();
            P();
        }
        a(false);
    }

    /* access modifiers changed from: private */
    public void I() {
        ArrayList arrayList;
        if (this.N == null || this.N.size() <= 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.N.size());
            arrayList.addAll(this.N);
        }
        e.a((FragmentActivity) this.a).d().a(new CountryUtil.b() {
            public void a(Country country) {
                q.this.a(country);
            }
        }, arrayList, null, "view_listing");
    }

    /* access modifiers changed from: private */
    public void J() {
        if (!U()) {
            I();
            return;
        }
        L();
        N();
    }

    private void K() {
        M();
        this.m.setVisibility(0);
    }

    private void L() {
        this.m.setVisibility(8);
    }

    private void M() {
        if (this.O != null) {
            if (TextUtils.isEmpty(this.s.getText()) || !U()) {
                this.n.setText(this.O.getName());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(this.O.getName());
                sb.append(", ");
                sb.append(this.s.getText());
                this.n.setText(sb.toString());
            }
        }
        if (this.P == null || "".equals(this.P.getOptionId())) {
            this.p.setText("");
            return;
        }
        this.p.setText(this.P.getCost().format());
        ac();
    }

    private void N() {
        b(this.s.getText().toString());
        this.q.setVisibility(0);
        this.r.setVisibility(0);
    }

    private void O() {
        this.q.setVisibility(8);
        this.r.setVisibility(8);
    }

    private void b(String str) {
        if (this.O != null) {
            String isoCountryCode = this.O.getIsoCountryCode();
            aa aaVar = new aa();
            this.R = aaVar.b(isoCountryCode);
            this.Q = aaVar.a(isoCountryCode);
            this.r.setText(this.O.getName());
            this.V.setMPostalCodeFormatter(aaVar.c(isoCountryCode));
        } else {
            this.r.setText(R.string.shipping_to_default);
        }
        if (this.O == null || !U()) {
            this.s.setVisibility(8);
        } else {
            this.s.setVisibility(0);
            this.s.setInputType(this.R.b());
            this.s.setHint(this.R.a());
            this.s.setFilters(new InputFilter[]{new LengthFilter(this.Q.a()), new AllCaps()});
        }
        this.s.setText(str);
    }

    private void P() {
        this.l.setVisibility(0);
        this.k.setText(R.string.shipping_panel_cost_label);
    }

    private void Q() {
        this.l.setVisibility(8);
        this.k.setText(R.string.shipping_panel_to_label);
    }

    private void R() {
        L();
        N();
        Toast.makeText(this.a, R.string.shipping_error_cost, 0).show();
    }

    private void S() {
        L();
        O();
        P();
        this.l.setText(R.string.shipping_cost_action_update);
        this.t.setText(this.t.getResources().getString(R.string.shipping_error_destination, new Object[]{this.O.getName()}));
        this.t.setVisibility(0);
        this.u.setVisibility(8);
    }

    private void T() {
        this.t.setText("");
        this.t.setVisibility(8);
        this.l.setText(R.string.shipping_cost_action_calculate);
        this.r.setError(null);
        y();
    }

    private boolean b(boolean z2) {
        if (this.O == null) {
            if (z2) {
                this.r.setError(this.a.getResources().getString(R.string.shipping_error_country));
            }
            return false;
        } else if (!U() || (!TextUtils.isEmpty(this.s.getText()) && this.Q.a(this.s.getText().toString()))) {
            return true;
        } else {
            if (z2) {
                EtsyApplication.get().getAnalyticsTracker().a("invalid_listing_destination", this.b.getTrackingParameters());
                Toast.makeText(this.a, R.string.shipping_error_zip, 0).show();
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void a(ListingShippingDetails listingShippingDetails) {
        this.L = listingShippingDetails;
        if (this.M && this.L != null) {
            G();
            ShippingAddressPreference shippingAddress = listingShippingDetails.getShippingAddress();
            String str = null;
            if (shippingAddress != null) {
                this.O = shippingAddress.getCountry();
                str = shippingAddress.getPostalCode();
            }
            this.P = listingShippingDetails.getShippingOption();
            this.N = listingShippingDetails.getCountries();
            b(str);
            H();
            a(listingShippingDetails.getShippingDisplay());
            this.u.filterEstimates(this.O);
        }
    }

    /* access modifiers changed from: private */
    public void a(Country country) {
        this.O = country;
        this.P = null;
        if (this.L != null) {
            this.L.getShippingAddress().setCountry(this.O);
        }
        b((String) null);
        W();
        this.u.filterEstimates(this.O);
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        this.P = null;
        if (this.L != null) {
            this.L.getShippingAddress().setPostalCode(str);
        }
        b(str);
        V();
    }

    private void a(ShippingOption shippingOption) {
        this.P = shippingOption;
        H();
    }

    private boolean U() {
        return (this.Q == null || this.R == null) ? false : true;
    }

    private void V() {
        T();
        if (b(true)) {
            X();
        }
    }

    private void W() {
        T();
        if (b(false)) {
            X();
            return;
        }
        this.P = null;
        H();
    }

    private void X() {
        this.T.a(this.O.getIsoCountryCode());
        this.T.b(this.s.getText().toString());
        ad();
        F();
        this.W.a(this.S.a(this.b.getListingId().getIdAsLong(), this.O.getIsoCountryCode(), U() ? this.s.getText().toString() : null).b(this.U.a()).a(this.U.b()).a((Consumer<? super T>) new s<Object>(this), (Consumer<? super Throwable>) new t<Object>(this)));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(k kVar) throws Exception {
        if ((kVar instanceof k.b) && this.i) {
            ListingShippingDetails a2 = ((k.b) kVar).a();
            if (a2.getShippingOption() == null) {
                S();
            } else {
                a(a2.getShippingOption());
            }
            a(a2.getShippingDisplay());
            G();
        } else if (kVar instanceof com.etsy.android.ui.core.k.a) {
            Y();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(Throwable th) throws Exception {
        Y();
    }

    private void Y() {
        o().a("shipping_cost_retreival_error", new ListingPanelShippingAndPolicies$8(this));
        R();
        G();
    }

    private void Z() {
        String b = this.T.b();
        String c = this.T.c();
        F();
        this.W.a(this.S.a(this.b.getListingId().getIdAsLong(), b, c).b(this.U.a()).a(this.U.b()).a((Consumer<? super T>) new u<Object>(this), (Consumer<? super Throwable>) new v<Object>(this)));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(k kVar) throws Exception {
        if (kVar instanceof k.b) {
            ListingShippingDetails a2 = ((k.b) kVar).a();
            if (this.i) {
                a(a2);
            }
        } else if (kVar instanceof com.etsy.android.ui.core.k.a) {
            G();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Throwable th) throws Exception {
        G();
    }

    public void a(List<Region> list) {
        this.M = true;
        if (this.a != null) {
            this.a.runOnUiThread(new Runnable() {
                public void run() {
                    if (q.this.i) {
                        q.this.a(q.this.L);
                    }
                }
            });
        }
    }

    public void a(String str) {
        this.M = true;
        if (this.a != null) {
            this.a.runOnUiThread(new Runnable() {
                public void run() {
                    if (q.this.i) {
                        q.this.a(q.this.L);
                    }
                }
            });
        }
    }

    public void t() {
        if (this.b.getShop() != null) {
            String loginName = this.b.getShop().getLoginName();
            Bundle bundle = new Bundle();
            if (v.a().e()) {
                bundle.putString(ResponseConstants.USERNAME, loginName);
                ((com.etsy.android.ui.nav.b) e.a((FragmentActivity) this.a).a().a(this.c)).e(bundle);
            } else {
                bundle.putString(ResponseConstants.USERNAME, loginName);
                ((com.etsy.android.ui.nav.b) e.a((FragmentActivity) this.a).a().a(this.c)).a(EtsyAction.CONTACT_USER, bundle);
            }
        }
    }

    private void aa() {
        if (this.s != null && this.V != null) {
            this.s.removeTextChangedListener(this.V);
            this.V = null;
        }
    }

    private void ab() {
        com.etsy.android.lib.logger.legacy.b.a().b("shipping_tab_click", "view_listing", new ListingPanelShippingAndPolicies$11(this));
    }

    private void ac() {
        o().a("shipping_costs_view", null);
    }

    private void ad() {
        o().a("shipping_costs_request", null);
    }

    private void a(@Nullable ShippingDisplay shippingDisplay) {
        if (shippingDisplay != null && p().c(com.etsy.android.lib.config.b.cy)) {
            if (shippingDisplay.getPrimaryText() != null) {
                this.H.setText(Html.fromHtml(shippingDisplay.getPrimaryText()));
                this.H.setVisibility(0);
            }
            if (shippingDisplay.getSecondaryText() != null) {
                this.G.setText(Html.fromHtml(shippingDisplay.getSecondaryText()));
                this.G.setVisibility(0);
            }
            this.H.setMovementMethod(LinkMovementMethod.getInstance());
            this.G.setMovementMethod(LinkMovementMethod.getInstance());
            EtsyLinkify.a(this.H, true, (OnClickListener) new w(this));
            this.I.setVisibility(8);
            this.J.setVisibility(8);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(View view) {
        new EstimatedDeliveryDateLegaleseDialogFragment().show(this.a.getSupportFragmentManager(), "Bottom sheet dialog");
    }
}
