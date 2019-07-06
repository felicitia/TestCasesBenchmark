package com.etsy.android.ui.cardview.viewholders;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.cardviewelement.SearchFilterHeader;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class SearchFilterHeaderViewHolder extends BaseViewHolder<BasicSectionHeader> {
    private final TextView mCategoryPathView;
    /* access modifiers changed from: private */
    public final b mClickHandler;
    private final TextView mFilterView = ((TextView) this.itemView.findViewById(R.id.filters_text_view));
    private final TextView mResultCountView = ((TextView) this.itemView.findViewById(R.id.header_result_count_textview));

    public SearchFilterHeaderViewHolder(ViewGroup viewGroup, b bVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_listings_result_header, null));
        this.mClickHandler = bVar;
        this.mCategoryPathView = (TextView) this.itemView.findViewById(R.id.header_category_textview);
    }

    public void bind(BasicSectionHeader basicSectionHeader) {
        if (basicSectionHeader instanceof SearchFilterHeader) {
            final SearchFilterHeader searchFilterHeader = (SearchFilterHeader) basicSectionHeader;
            this.mFilterView.setText(searchFilterHeader.getActionTitle());
            this.mResultCountView.setText(searchFilterHeader.getTitle());
            String subtitle = searchFilterHeader.getSubtitle();
            if (TextUtils.isEmpty(subtitle)) {
                this.mCategoryPathView.setVisibility(8);
            } else {
                this.mCategoryPathView.setVisibility(0);
                Context context = this.mCategoryPathView.getContext();
                this.mCategoryPathView.setText(com.etsy.android.ui.search.v2.b.a(context, subtitle, context.getResources().getColor(R.color.text_grey_white)));
            }
            this.mFilterView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (SearchFilterHeaderViewHolder.this.mClickHandler != null) {
                        SearchFilterHeaderViewHolder.this.mClickHandler.a(searchFilterHeader);
                    }
                }
            });
            return;
        }
        this.mFilterView.setVisibility(8);
        this.mCategoryPathView.setVisibility(0);
        this.mCategoryPathView.setText(basicSectionHeader.getTitle());
        this.mResultCountView.setText(basicSectionHeader.getSubtitle());
    }
}
