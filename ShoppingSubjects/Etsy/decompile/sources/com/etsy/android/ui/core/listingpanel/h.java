package com.etsy.android.ui.core.listingpanel;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.f.a;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.PaymentTemplate;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import com.etsy.android.lib.models.apiv3.ShippingAddressPreference;
import com.etsy.android.lib.models.apiv3.ShippingDisplay;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import com.etsy.android.lib.util.s;
import com.etsy.android.lib.util.sharedprefs.b;
import com.etsy.android.ui.core.i;
import com.etsy.android.ui.core.k;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ListingPanelShipping */
public class h extends d {
    private TextView A;
    private View B;
    private ListingShippingDetails C;
    private List<Country> D;
    private Country E;
    private ShippingOption F;
    private PostalCodeTextWatcher G;
    private z H;
    private y I;
    @NonNull
    private final i J;
    @NonNull
    private final a K;
    @NonNull
    private final b L;
    @NonNull
    private io.reactivex.disposables.a M = new io.reactivex.disposables.a();
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
    private View u;
    private View v;
    private View w;
    private TextView x;
    private TextView y;
    private TextView z;

    public h(Listing listing, BaseActivity baseActivity, @NonNull w wVar, @NonNull i iVar, @NonNull b bVar, @NonNull a aVar) {
        super(listing, baseActivity, wVar);
        this.J = iVar;
        this.K = aVar;
        this.L = bVar;
        a(R.id.panel_details_shipping, R.id.panel_title_shipping, R.id.img_shipping_open, R.id.txt_shipping_title);
    }

    /* access modifiers changed from: protected */
    public void c() {
        t();
        u();
        v();
        y();
        w();
        x();
    }

    public void s() {
        if (this.b.isSoldOut()) {
            this.c.findViewById(R.id.panel_calculated_shipping).setVisibility(8);
            B();
        } else if (this.C == null) {
            U();
        } else {
            a(this.C);
            B();
        }
    }

    private void t() {
        this.j = this.d.findViewById(R.id.shipping_panel_view_loading);
        this.k = (TextView) this.c.findViewById(R.id.shipping_cost_header);
        this.l = (TextView) this.c.findViewById(R.id.shipping_cost_calculate);
        this.m = (LinearLayout) this.c.findViewById(R.id.shipping_costs_view_only);
        this.n = (TextView) this.c.findViewById(R.id.shipping_costs_view_only_destination);
        this.o = (TextView) this.c.findViewById(R.id.shipping_costs_view_only_update_button);
        this.p = (TextView) this.c.findViewById(R.id.shipping_costs_view_only_cost);
        this.q = (LinearLayout) this.c.findViewById(R.id.shipping_costs_edit);
        this.r = (Button) this.c.findViewById(R.id.shipping_costs_edit_country);
        this.s = (EditText) this.c.findViewById(R.id.shipping_costs_edit_zip);
        this.t = (TextView) this.c.findViewById(R.id.shipping_cost_error);
        this.u = this.c.findViewById(R.id.panel_shipping_policy);
        this.v = this.d.findViewById(R.id.gift_message_available);
        this.w = this.d.findViewById(R.id.gift_wrap_available);
        this.x = (TextView) this.d.findViewById(R.id.estimated_delivery_second_text);
        this.y = (TextView) this.d.findViewById(R.id.estimated_delivery_first_line);
        this.z = (TextView) this.d.findViewById(R.id.shipping_origin);
        this.A = (TextView) this.d.findViewById(R.id.shipping_time);
        this.B = this.d.findViewById(R.id.shipping_panel_cost_lower_divider);
        z();
        if (!this.b.isDigitalDownload()) {
            this.c.findViewById(R.id.panel_calculated_shipping).setVisibility(0);
            H();
            a((String) null);
            return;
        }
        ((TextView) this.f).setText(R.string.shop_policies);
    }

    private void u() {
        final Shop shop = this.b.getShop();
        if (shop == null || !shop.hasPolicies()) {
            this.u.setVisibility(8);
            return;
        }
        this.u.setVisibility(0);
        this.u.setOnClickListener(new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{shop, this.b}) {
            public void onViewClick(View view) {
                e.a((Activity) h.this.a).g().c(shop.getShopId(), null);
            }
        });
    }

    private void v() {
        String str;
        TextView textView = (TextView) this.c.findViewById(R.id.shipping_time);
        textView.setVisibility(0);
        if (this.b.isDigitalDownload()) {
            textView.setText(R.string.file_delivery_message);
        } else if (this.b.getProcessingDaysMin() > 0 || this.b.getProcessingDaysMax() > 0) {
            if (this.b.getProcessingDaysMin() == this.b.getProcessingDaysMax()) {
                str = String.valueOf(this.b.getProcessingDaysMin());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(this.b.getProcessingDaysMin());
                sb.append("-");
                sb.append(this.b.getProcessingDaysMax());
                str = sb.toString();
            }
            if ("1".equals(str)) {
                textView.setText(String.format(this.c.getResources().getString(R.string.shipping_processing_singular), new Object[]{str}));
                return;
            }
            textView.setText(String.format(this.c.getResources().getString(R.string.shipping_processing), new Object[]{str}));
        } else {
            textView.setVisibility(8);
        }
    }

    private void w() {
        if (!this.b.isDigitalDownload() && this.b.getShippingInfo().size() > 0) {
            TextView textView = (TextView) this.c.findViewById(R.id.shipping_origin);
            textView.setVisibility(0);
            textView.setText(String.format(this.c.getResources().getString(R.string.shipping_ships_from), new Object[]{((ShippingInfo) this.b.getShippingInfo().get(0)).getOriginCountryName()}));
        }
    }

    private void x() {
        if (this.b.getGiftInfo() == null) {
            return;
        }
        if (this.b.getGiftInfo().isEligible() || this.b.getGiftInfo().offersGiftMessage()) {
            int i = 0;
            this.d.findViewById(R.id.listing_gift_options_container).setVisibility(0);
            this.v.setVisibility(this.b.getGiftInfo().offersGiftMessage() ? 0 : 8);
            View view = this.w;
            if (!this.b.getGiftInfo().isEligible()) {
                i = 8;
            }
            view.setVisibility(i);
            this.w.setOnClickListener(new i(this));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void c(View view) {
        e.a((FragmentActivity) this.a).a().a(this.b.getShopName(), this.b.getGiftInfo().getDescription(), this.b.getGiftInfo().getPreviewImage());
    }

    private void y() {
        PaymentTemplate paymentInfo = this.b.getPaymentInfo();
        if (paymentInfo != null) {
            View findViewById = this.c.findViewById(R.id.credit_card_payments);
            View findViewById2 = this.c.findViewById(R.id.paypal_payments);
            if (paymentInfo.getAllowCc()) {
                findViewById.setVisibility(0);
                findViewById2.setVisibility(0);
            }
            if (paymentInfo.getAllowPaypal()) {
                findViewById2.setVisibility(0);
            }
            TextView textView = (TextView) this.c.findViewById(R.id.text_payment_method_others);
            if (paymentInfo.getAllowOther() || paymentInfo.getAllowCheck() || paymentInfo.getAllowMo() || paymentInfo.getAllowBt()) {
                ArrayList arrayList = new ArrayList();
                if (paymentInfo.getAllowOther()) {
                    arrayList.add(this.c.getResources().getString(R.string.payment_method_label_other));
                }
                if (paymentInfo.getAllowCheck()) {
                    arrayList.add(this.c.getResources().getString(R.string.payment_method_label_check));
                }
                if (paymentInfo.getAllowMo()) {
                    arrayList.add(this.c.getResources().getString(R.string.payment_method_label_money_order));
                }
                if (paymentInfo.getAllowBt()) {
                    arrayList.add(this.c.getResources().getString(R.string.payment_method_label_bank_transfer));
                }
                textView.setText(TextUtils.join(", ", arrayList.toArray()));
                return;
            }
            textView.setVisibility(8);
            return;
        }
        this.c.findViewById(R.id.payments_layout).setVisibility(4);
    }

    private void z() {
        AnonymousClass2 r0 = new TrackingOnClickListener(this.b) {
            public void onViewClick(View view) {
                h.this.D();
            }
        };
        this.o.setOnClickListener(new TrackingOnClickListener(this.b) {
            public void onViewClick(View view) {
                h.this.E();
            }
        });
        this.l.setOnClickListener(r0);
        this.r.setOnClickListener(r0);
        V();
        this.G = new PostalCodeTextWatcher(this.s);
        this.s.addTextChangedListener(this.G);
        this.s.setOnEditorActionListener(new j(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ boolean a(TextView textView, int i, KeyEvent keyEvent) {
        if (keyEvent == null && i != 6) {
            return false;
        }
        b(textView.getText().toString());
        s.a((View) textView);
        return true;
    }

    @CallSuper
    public void b() {
        super.b();
        V();
        this.M.a();
    }

    private void A() {
        this.j.setBackgroundColor(this.j.getResources().getColor(R.color.sk_white));
        this.j.setVisibility(0);
    }

    private void B() {
        this.j.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void j() {
        W();
        C();
    }

    /* access modifiers changed from: protected */
    public void k() {
        C();
    }

    /* access modifiers changed from: protected */
    public void l() {
        s.a((View) this.s);
    }

    private void C() {
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = -2;
        this.d.setLayoutParams(layoutParams);
        if (b(false) && this.F != null) {
            J();
            F();
            L();
        } else if (b(false) && this.C != null && this.F == null) {
            N();
        } else if (this.E != null || !TextUtils.isEmpty(this.s.getText())) {
            I();
            G();
            L();
        } else {
            J();
            G();
            K();
        }
        a(false);
    }

    /* access modifiers changed from: private */
    public void D() {
        ArrayList arrayList;
        if (this.D == null || this.D.size() <= 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.D.size());
            arrayList.addAll(this.D);
        }
        e.a((FragmentActivity) this.a).d().a(new k(this), arrayList, null, "view_listing");
    }

    /* access modifiers changed from: private */
    public void E() {
        if (!P()) {
            D();
            return;
        }
        G();
        I();
    }

    private void F() {
        H();
        this.m.setVisibility(0);
    }

    private void G() {
        this.m.setVisibility(8);
    }

    private void H() {
        if (this.E != null) {
            if (TextUtils.isEmpty(this.s.getText()) || !P()) {
                this.n.setText(this.E.getName());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(this.E.getName());
                sb.append(", ");
                sb.append(this.s.getText());
                this.n.setText(sb.toString());
            }
        }
        if (this.F == null || "".equals(this.F.getOptionId())) {
            this.p.setText("");
            return;
        }
        this.p.setText(this.F.getCost().format());
        X();
    }

    private void I() {
        a(this.s.getText().toString());
        this.q.setVisibility(0);
        this.r.setVisibility(0);
    }

    private void J() {
        this.q.setVisibility(8);
        this.r.setVisibility(8);
    }

    private void a(String str) {
        if (this.E != null) {
            this.r.setText(this.E.getName());
            String isoCountryCode = this.E.getIsoCountryCode();
            aa aaVar = new aa();
            this.I = aaVar.b(isoCountryCode);
            this.H = aaVar.a(isoCountryCode);
            this.G.setMPostalCodeFormatter(aaVar.c(isoCountryCode));
        } else {
            this.r.setText(R.string.shipping_to_default);
        }
        if (this.E == null || !P()) {
            this.s.setVisibility(8);
        } else {
            this.s.setVisibility(0);
            this.s.setInputType(this.I.b());
            this.s.setHint(this.I.a());
            this.s.setFilters(new InputFilter[]{new LengthFilter(this.H.a()), new AllCaps()});
        }
        this.s.setText(str);
    }

    private void K() {
        this.l.setVisibility(0);
        this.k.setText(R.string.shipping_panel_cost_label);
    }

    private void L() {
        this.l.setVisibility(8);
        this.k.setText(R.string.shipping_panel_to_label);
    }

    private void M() {
        G();
        I();
        Toast.makeText(this.a, R.string.shipping_error_cost, 0).show();
    }

    private void N() {
        G();
        J();
        K();
        this.l.setText(R.string.shipping_cost_action_update);
        this.t.setText(this.t.getResources().getString(R.string.shipping_error_destination, new Object[]{this.E.getName()}));
        this.t.setVisibility(0);
    }

    private void O() {
        this.t.setText("");
        this.t.setVisibility(8);
        this.l.setText(R.string.shipping_cost_action_calculate);
        this.r.setError(null);
    }

    private boolean b(boolean z2) {
        if (this.E == null) {
            if (z2) {
                this.r.setError(this.a.getResources().getString(R.string.shipping_error_country));
            }
            return false;
        } else if (!P() || (!TextUtils.isEmpty(this.s.getText()) && this.H.a(this.s.getText().toString()))) {
            return true;
        } else {
            if (z2) {
                o().a("invalid_listing_destination", this.b.getTrackingParameters());
                Toast.makeText(this.a, R.string.shipping_error_zip, 0).show();
            }
            return false;
        }
    }

    private void a(ListingShippingDetails listingShippingDetails) {
        String str;
        this.C = listingShippingDetails;
        ShippingAddressPreference shippingAddress = listingShippingDetails.getShippingAddress();
        if (shippingAddress != null) {
            this.E = shippingAddress.getCountry();
            str = shippingAddress.getPostalCode();
        } else {
            str = null;
        }
        this.F = listingShippingDetails.getShippingOption();
        this.D = listingShippingDetails.getCountries();
        a(listingShippingDetails.getShippingDisplay());
        a(str);
        C();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void a(Country country) {
        this.E = country;
        this.F = null;
        if (this.C != null) {
            this.C.getShippingAddress().setCountry(this.E);
        }
        a((String) null);
        R();
    }

    private void b(String str) {
        this.F = null;
        if (this.C != null) {
            this.C.getShippingAddress().setPostalCode(str);
        }
        a(str);
        Q();
    }

    private void a(ShippingOption shippingOption) {
        this.F = shippingOption;
        C();
    }

    private boolean P() {
        return (this.H == null || this.I == null) ? false : true;
    }

    private void Q() {
        O();
        if (b(true)) {
            S();
        }
    }

    private void R() {
        O();
        if (b(false)) {
            S();
            return;
        }
        this.F = null;
        C();
    }

    private void S() {
        this.L.a(this.E.getIsoCountryCode());
        this.L.b(this.s.getText().toString());
        Y();
        A();
        this.M.a(this.J.a(this.b.getListingId().getIdAsLong(), this.E.getIsoCountryCode(), P() ? this.s.getText().toString() : null).b(this.K.a()).a(this.K.b()).a((Consumer<? super T>) new l<Object>(this), (Consumer<? super Throwable>) new m<Object>(this)));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(k kVar) throws Exception {
        if ((kVar instanceof k.b) && this.i) {
            ListingShippingDetails a = ((k.b) kVar).a();
            if (a.getShippingOption() == null) {
                N();
            } else {
                a(a.getShippingOption());
            }
            a(a.getShippingDisplay());
            B();
        } else if (kVar instanceof k.a) {
            T();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(Throwable th) throws Exception {
        T();
    }

    private void T() {
        o().a("shipping_cost_retreival_error", new ListingPanelShipping$4(this));
        M();
        B();
    }

    private void U() {
        String b = this.L.b();
        String c = this.L.c();
        A();
        this.M.a(this.J.a(this.b.getListingId().getIdAsLong(), b, c).b(this.K.a()).a(this.K.b()).a((Consumer<? super T>) new n<Object>(this), (Consumer<? super Throwable>) new o<Object>(this)));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(k kVar) throws Exception {
        if (kVar instanceof k.b) {
            ListingShippingDetails a = ((k.b) kVar).a();
            if (this.i) {
                a(a);
                B();
            }
        } else if (kVar instanceof k.a) {
            B();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Throwable th) throws Exception {
        B();
    }

    private void V() {
        if (this.s != null && this.G != null) {
            this.s.removeTextChangedListener(this.G);
            this.G = null;
        }
    }

    private void W() {
        com.etsy.android.lib.logger.legacy.b.a().b("shipping_tab_click", "view_listing", new ListingPanelShipping$5(this));
    }

    private void X() {
        o().a("shipping_costs_view", null);
    }

    private void Y() {
        o().a("shipping_costs_request", null);
    }

    private void a(@Nullable ShippingDisplay shippingDisplay) {
        if (shippingDisplay != null && p().c(com.etsy.android.lib.config.b.cy)) {
            if (shippingDisplay.getPrimaryText() != null) {
                this.y.setText(Html.fromHtml(shippingDisplay.getPrimaryText()));
                this.y.setVisibility(0);
            }
            if (shippingDisplay.getSecondaryText() != null) {
                this.x.setText(Html.fromHtml(shippingDisplay.getSecondaryText()));
                this.x.setVisibility(0);
            }
            this.y.setMovementMethod(LinkMovementMethod.getInstance());
            this.x.setMovementMethod(LinkMovementMethod.getInstance());
            EtsyLinkify.a(this.y, true, (OnClickListener) new p(this));
            this.z.setVisibility(8);
            this.A.setVisibility(8);
            this.B.setVisibility(8);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(View view) {
        new EstimatedDeliveryDateLegaleseDialogFragment().show(this.a.getSupportFragmentManager(), "Bottom sheet dialog");
    }
}
