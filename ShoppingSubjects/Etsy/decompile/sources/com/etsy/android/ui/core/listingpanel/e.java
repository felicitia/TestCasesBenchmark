package com.etsy.android.ui.core.listingpanel;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.t;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.MachineTranslationOneClickView;
import java.util.Locale;

/* compiled from: ListingPanelDescription */
public class e extends d {
    /* access modifiers changed from: private */
    public a j;
    private TextView k;
    /* access modifiers changed from: private */
    public MachineTranslationOneClickView l;
    private Listing m;

    /* compiled from: ListingPanelDescription */
    public interface a {
        void a(MachineTranslationOneClickView machineTranslationOneClickView);
    }

    public e(Listing listing, BaseActivity baseActivity, @NonNull w wVar) {
        super(listing, baseActivity, wVar);
        a(R.id.panel_details_description, R.id.panel_title_description, R.id.img_description_open, R.id.txt_description_title);
    }

    /* access modifiers changed from: protected */
    public void c() {
        this.k = (TextView) this.d.findViewById(R.id.text_description);
        this.l = (MachineTranslationOneClickView) this.d.findViewById(R.id.machine_translation_one_click);
        u();
        t();
        v();
    }

    private void v() {
        if (com.etsy.android.lib.config.a.a().d().c(b.bB) && this.b.hasSellerDiscountDescription()) {
            a(this.b.getListingPromotion().getSellerDescription(), R.string.discount_details, R.id.text_seller_discount_description);
        }
        if (com.etsy.android.lib.config.a.a().d().c(b.bC) && this.b.hasSellerFreeShippingDescription()) {
            a(this.b.getFreeShippingData().getSellerDescription(), R.string.free_shipping_sales_details, R.id.text_seller_free_shipping_description);
        }
    }

    public void s() {
        t();
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = -2;
        this.d.setLayoutParams(layoutParams);
        a(false);
    }

    private void a(String str, @StringRes int i, @IdRes int i2) {
        TextView textView = (TextView) this.d.findViewById(i2);
        if (textView != null) {
            textView.setVisibility(0);
            String string = this.d.getContext().getResources().getString(i);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, string.length(), 33);
            spannableStringBuilder.append(' ');
            spannableStringBuilder.append(str);
            textView.setText(spannableStringBuilder);
            this.d.findViewById(R.id.divider_seller_promotion_descriptions).setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void t() {
        if (af.a(this.m.getDescription())) {
            if (l.c((Activity) this.a)) {
                StringBuilder sb = new StringBuilder();
                sb.append("<br/><b>");
                sb.append(this.a.getResources().getString(R.string.description));
                sb.append(":</b><br/>");
                Spanned fromHtml = Html.fromHtml(sb.toString());
                this.k.setText(TextUtils.concat(new CharSequence[]{fromHtml, this.m.getDescription()}));
            } else {
                this.k.setText(this.m.getDescription());
            }
            EtsyLinkify.a((Context) this.a, this.k);
        } else {
            this.k.setVisibility(8);
        }
        if (l.c((Activity) this.a)) {
            TextView textView = (TextView) this.d.findViewById(R.id.text_overview_title);
            TableLayout tableLayout = (TableLayout) this.d.findViewById(R.id.table_overview);
            if (tableLayout != null && textView != null) {
                if (this.b.hasOverview()) {
                    tableLayout.setVisibility(0);
                    textView.setVisibility(0);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("<b>");
                    sb2.append(this.a.getResources().getString(R.string.overview));
                    sb2.append(":</b>");
                    textView.setText(Html.fromHtml(sb2.toString()));
                    g.a(this.a, this.b.getOverview(), tableLayout);
                    return;
                }
                tableLayout.setVisibility(8);
                textView.setVisibility(8);
            }
        }
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public void b(Listing listing) {
        this.m = listing;
    }

    public void c(Listing listing) {
        this.l.setListingTranslationState(listing.isMachineTranslated(), listing.isMachineTranslated() ? this.b.getOriginalLanguage() : Locale.getDefault().getLanguage());
    }

    /* access modifiers changed from: protected */
    public void u() {
        if (this.l != null) {
            if ((this.b.isMachineTranslatable() || this.b.isMachineTranslated()) && t.g()) {
                ((TextView) this.l.findViewById(R.id.translate_button)).setOnClickListener(new TrackingOnClickListener(this.b) {
                    public void onViewClick(View view) {
                        e.this.j.a(e.this.l);
                    }
                });
                c(this.m);
                this.l.setVisibility(0);
                this.l.showDisclaimer();
                this.l.showButtonElements();
                this.l.hideSpinner();
                this.l.hideErrorMessage();
            } else {
                this.l.setVisibility(8);
            }
        }
    }
}
