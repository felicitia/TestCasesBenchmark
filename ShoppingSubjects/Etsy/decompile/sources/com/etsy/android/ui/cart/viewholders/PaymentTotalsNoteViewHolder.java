package com.etsy.android.ui.cart.viewholders;

import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPMoneyItem;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class PaymentTotalsNoteViewHolder extends BaseCartGroupItemViewHolder {
    private final TextView mText = ((TextView) findViewById(R.id.txt_text));

    public PaymentTotalsNoteViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_totals_note, viewGroup, false));
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        this.mText.setText(Html.fromHtml(((SimpleKVPMoneyItem) cartGroupItem.getData()).getText()));
        linkifyText(this.mText);
    }

    private void linkifyText(TextView textView) {
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            final String url = urls[0].getURL();
            EtsyLinkify.a(textView, true, false, (OnClickListener) new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a((FragmentActivity) PaymentTotalsNoteViewHolder.this.itemView.getContext()).a().f(url);
                }
            });
        }
    }
}
