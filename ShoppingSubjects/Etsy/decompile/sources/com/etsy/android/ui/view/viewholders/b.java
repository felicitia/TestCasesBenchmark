package com.etsy.android.ui.view.viewholders;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Coupon;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

/* compiled from: ReceiptTotalsViewHolder */
public class b {
    public View a;
    public TextView b;
    public TextView c;
    public TextView d;
    public TextView e;
    public TextView f;
    public View g;
    public TextView h;
    public TextView i;
    public TextView j;
    public View k;
    public TextView l;
    public View m;
    public TextView n;
    public View o;
    public TextView p;
    public TextView q;
    public TextView r;
    public TextView s;
    public ViewGroup t;
    @NonNull
    private final w u;

    public b(View view, @NonNull w wVar) {
        this.u = wVar;
        this.a = view.findViewById(R.id.shipping_row);
        this.c = (TextView) view.findViewById(R.id.text_item_total_value);
        this.b = (TextView) view.findViewById(R.id.text_item_total);
        this.d = (TextView) view.findViewById(R.id.text_shipping_value);
        this.e = (TextView) view.findViewById(R.id.text_order_total_value);
        this.f = (TextView) view.findViewById(R.id.text_order_total);
        this.g = view.findViewById(R.id.coupon_row);
        this.h = (TextView) view.findViewById(R.id.text_coupon_title);
        this.i = (TextView) view.findViewById(R.id.text_coupon_value);
        this.j = (TextView) view.findViewById(R.id.text_coupon_details);
        this.k = view.findViewById(R.id.tax_row);
        this.l = (TextView) view.findViewById(R.id.text_tax_value);
        this.o = view.findViewById(R.id.vat_row);
        this.p = (TextView) view.findViewById(R.id.text_vat_value);
        this.q = (TextView) view.findViewById(R.id.text_vat_desc);
        this.r = (TextView) view.findViewById(R.id.text_vat_invoice);
        this.s = (TextView) view.findViewById(R.id.text_transparent_pricing);
        this.m = view.findViewById(R.id.discount_row);
        this.n = (TextView) view.findViewById(R.id.text_discount_value);
        this.t = (ViewGroup) view.findViewById(R.id.refund_view);
    }

    public void a(FragmentActivity fragmentActivity, Receipt receipt) {
        this.b.setText(R.string.item_total);
        this.c.setText(receipt.getTotalPrice().format());
        this.f.setText(R.string.order_total);
        this.e.setText(receipt.getFormattedGrandTotal());
        a(fragmentActivity.getResources(), receipt);
        a(receipt);
        c(receipt);
        d(receipt);
        a(receipt, fragmentActivity);
        b(receipt);
    }

    private void a(Resources resources, Receipt receipt) {
        String str;
        String str2;
        Coupon coupon = receipt.getCoupon();
        boolean z = this.j == null;
        if (coupon == null || receipt.isInPerson()) {
            this.g.setVisibility(8);
            if (this.j != null) {
                this.j.setVisibility(8);
                return;
            }
            return;
        }
        if (coupon.isFreeShipping()) {
            str = resources.getString(R.string.coupon_free_shipping);
            this.d.setPaintFlags(this.d.getPaintFlags() | 16);
            if (z) {
                str2 = String.format(resources.getString(R.string.coupon_with_code), new Object[]{coupon.getCouponCode()});
            } else {
                str2 = String.format(resources.getString(R.string.coupon_details_text_freeshipping), new Object[]{coupon.getCouponCode()});
            }
        } else if (coupon.isPercentDiscount()) {
            StringBuilder sb = new StringBuilder();
            sb.append("- ");
            sb.append(receipt.getDiscountAmt().format());
            str = sb.toString();
            if (z) {
                str2 = String.format(resources.getString(R.string.coupon_with_code_and_percentage), new Object[]{coupon.getCouponCode(), String.valueOf(coupon.getPercentDiscount())});
            } else {
                str2 = String.format(resources.getString(R.string.coupon_details_text_discount), new Object[]{coupon.getCouponCode(), Integer.valueOf(coupon.getPercentDiscount())});
            }
        } else {
            String format = receipt.getDiscountAmt().format();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("- ");
            sb2.append(format);
            String sb3 = sb2.toString();
            if (z) {
                str2 = String.format(resources.getString(R.string.coupon_with_code), new Object[]{coupon.getCouponCode()});
            } else {
                String formattedFixedDiscount = coupon.getFormattedFixedDiscount();
                if (af.a(formattedFixedDiscount)) {
                    format = formattedFixedDiscount;
                }
                str2 = String.format(resources.getString(R.string.coupon_details_text_fixed_discount), new Object[]{coupon.getCouponCode(), format});
            }
            str = sb3;
        }
        this.i.setText(str);
        if (z) {
            this.h.setText(str2);
        } else {
            this.j.setText(str2);
        }
    }

    private void b(Receipt receipt) {
        if (!receipt.isInPerson() || receipt.getDiscountAmt().compareTo(0) != 1) {
            this.m.setVisibility(8);
            return;
        }
        String format = receipt.getDiscountAmt().format();
        StringBuilder sb = new StringBuilder();
        sb.append("- ");
        sb.append(format);
        this.n.setText(sb.toString());
        this.m.setVisibility(0);
    }

    private void c(Receipt receipt) {
        if (receipt.getTotalTaxCost().compareTo(0) == 0) {
            this.k.setVisibility(8);
        } else {
            this.l.setText(receipt.getTotalTaxCost().format());
        }
    }

    private void d(Receipt receipt) {
        if (this.o != null) {
            if (receipt.getTotalVatCost().compareTo(0) == 0) {
                this.q.setVisibility(8);
                this.o.setVisibility(8);
                this.r.setVisibility(8);
            } else {
                this.p.setText(receipt.getTotalVatCost().format());
                this.b.setText(R.string.item_total_excluding_vat);
                this.f.setText(R.string.order_total_including_vat);
                String b2 = this.u.c().b(com.etsy.android.lib.config.b.E);
                if (b2 != null) {
                    this.q.setText(Html.fromHtml(this.q.getResources().getString(R.string.vat_desc, new Object[]{b2})));
                    this.q.setVisibility(0);
                    this.q.setMovementMethod(LinkMovementMethod.getInstance());
                }
                String b3 = this.u.c().b(com.etsy.android.lib.config.b.F);
                if (b3 != null) {
                    this.r.setText(Html.fromHtml(String.format(this.r.getResources().getString(R.string.vat_view_invoice, new Object[]{b3}), new Object[]{receipt.getReceiptId().getId()})));
                    this.r.setVisibility(0);
                    this.r.setMovementMethod(LinkMovementMethod.getInstance());
                }
                String b4 = this.u.c().b(com.etsy.android.lib.config.b.G);
                if (!(b4 == null || this.t == null)) {
                    List<String> vatCreditNoteIds = receipt.getVatCreditNoteIds();
                    if (vatCreditNoteIds != null && vatCreditNoteIds.size() > 0) {
                        LayoutInflater layoutInflater = (LayoutInflater) this.t.getContext().getSystemService("layout_inflater");
                        Resources resources = this.t.getResources();
                        String id = receipt.getReceiptId().getId();
                        for (String str : vatCreditNoteIds) {
                            TextView textView = (TextView) layoutInflater.inflate(R.layout.textview_vat_credit_note, null);
                            textView.setText(Html.fromHtml(resources.getString(R.string.vat_credit_note, new Object[]{String.format(b4, new Object[]{id, str}), str})));
                            textView.setMovementMethod(LinkMovementMethod.getInstance());
                            this.t.addView(textView);
                        }
                    }
                }
            }
        }
    }

    private void a(Receipt receipt, FragmentActivity fragmentActivity) {
        if (!this.u.c().c(com.etsy.android.lib.config.b.bL) || !receipt.hasTransparentPriceMessage()) {
            this.s.setVisibility(8);
            return;
        }
        this.s.setText(Html.fromHtml(receipt.getTransparentPriceMessage()));
        a(this.s, fragmentActivity);
        this.s.setVisibility(0);
    }

    public void a(Receipt receipt) {
        if (receipt.isInPerson()) {
            this.a.setVisibility(8);
        } else {
            this.d.setText(receipt.getTotalShippingCost().format());
        }
    }

    private void a(TextView textView, final FragmentActivity fragmentActivity) {
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            final String url = urls[0].getURL();
            EtsyLinkify.a(textView, true, false, (OnClickListener) new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (fragmentActivity != null) {
                        e.a(fragmentActivity).a().f(url);
                    }
                }
            });
        }
    }
}
