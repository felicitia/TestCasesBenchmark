package com.etsy.android.ui.finds.cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.models.finds.FindsTwoTitledListingsModule.Footer;
import com.etsy.android.ui.finds.cardview.listener.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class FindsTwoTitledListingFooterViewHolder extends BaseViewHolder<Footer> {
    /* access modifiers changed from: private */
    public a mClickListener;
    private TextView mTextView = ((TextView) findViewById(R.id.txt_link_title));

    public FindsTwoTitledListingFooterViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_section_footer, viewGroup, false));
        this.mClickListener = aVar;
    }

    public void bind(final Footer footer) {
        if (footer.canDisplay()) {
            this.mTextView.setText(footer.getBottomText());
            getRootView().setVisibility(0);
            getRootView().setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (FindsTwoTitledListingFooterViewHolder.this.mViewTracker.c().c(b.A)) {
                        FindsTwoTitledListingFooterViewHolder.this.mClickListener.a(footer.getUrl());
                    } else if (footer.getTitleLink() != null) {
                        FindsTwoTitledListingFooterViewHolder.this.mClickListener.a(footer.getTitleLink());
                    } else {
                        FindsTwoTitledListingFooterViewHolder.this.mClickListener.a(footer.getUrl());
                    }
                }
            });
            return;
        }
        getRootView().setVisibility(8);
        getRootView().setOnClickListener(null);
        this.mTextView.setText(null);
    }
}
