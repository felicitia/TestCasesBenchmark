package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;

public class ListSectionLinkFooterViewHolder extends BaseViewHolder<PageLink> {
    b mCardClickHandler;
    TextView mTextLink;

    public ListSectionLinkFooterViewHolder(ViewGroup viewGroup, b bVar, boolean z) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutForOrientation(z), viewGroup, false));
        if (z) {
            getRootView().getLayoutParams().width = this.itemView.getResources().getDimensionPixelSize(f.horizontal_listing_card_section_item_width);
        }
        this.mTextLink = (TextView) findViewById(i.txt_link_title);
        this.mCardClickHandler = bVar;
    }

    private static int getLayoutForOrientation(boolean z) {
        return z ? k.list_item_card_view_horiz_scroll_footer : k.list_item_card_view_section_footer;
    }

    public void bind(final PageLink pageLink) {
        this.mTextLink.setText(pageLink.getLinkTitle());
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (ListSectionLinkFooterViewHolder.this.mCardClickHandler != null) {
                    ListSectionLinkFooterViewHolder.this.mCardClickHandler.a(pageLink);
                }
            }
        });
    }
}
