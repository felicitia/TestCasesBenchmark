package com.etsy.android.ui.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionHeaderViewHolder;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.PaddedColumnRecyclerViewAdapter;
import com.etsy.android.uikit.viewholder.EmptyHolder;

public class LocalBrowseAdapter extends PaddedColumnRecyclerViewAdapter<LocalMarketCard> {
    private static final int VIEW_TYPE_MARKET_CARD = 0;
    private com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder.a mMarketListener;
    private LocalBrowseModule mSection;
    private com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder.a mSectionListener;

    private static class a extends EmptyHolder {
        public a(Context context) {
            super(context);
            this.itemView.setBackgroundColor(context.getResources().getColor(R.color.background_main_v2));
        }
    }

    /* access modifiers changed from: protected */
    public void bindLeftTitleItemViewType(ViewHolder viewHolder, int i) {
    }

    /* access modifiers changed from: protected */
    public int getCoreItemViewType(int i) {
        return 0;
    }

    protected LocalBrowseAdapter(FragmentActivity fragmentActivity, c cVar, @NonNull com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder.a aVar, @NonNull com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder.a aVar2) {
        super(fragmentActivity, cVar, fragmentActivity.getResources().getInteger(R.integer.local_browse_list_columns), false, true);
        this.mMarketListener = aVar;
        this.mSectionListener = aVar2;
    }

    public void addSectionData(LocalBrowseModule localBrowseModule) {
        this.mSection = localBrowseModule;
        addHeader(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
        if (localBrowseModule.getLandingPage() != null) {
            addFooter(AbstractContextRecyclerViewAdapter.VIEW_TYPE_FOOTER);
        }
    }

    public int getSpanSize(int i, int i2) {
        if (getItemViewType(i) != 0) {
            return super.getSpanSize(i, i2);
        }
        return i2 / this.mColumns;
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        if (i != 601) {
            return super.onCreateListItemViewHolder(viewGroup, i);
        }
        return new a(this.mContext);
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateCoreItemViewHolder(ViewGroup viewGroup, int i) {
        return new LocalBrowseMarketViewHolder(viewGroup, this.mMarketListener);
    }

    /* access modifiers changed from: protected */
    public void bindCoreItemViewType(ViewHolder viewHolder, int i) {
        ((LocalBrowseMarketViewHolder) viewHolder).bind(this.mImageBatch, (LocalMarketCard) getItem(i));
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new LocalBrowseSectionHeaderViewHolder(viewGroup);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        ((LocalBrowseSectionHeaderViewHolder) viewHolder).bind(this.mSection);
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        return new LocalBrowseSectionFooterViewHolder(viewGroup, this.mSectionListener);
    }

    public void onBindFooterViewHolder(ViewHolder viewHolder, int i) {
        ((LocalBrowseSectionFooterViewHolder) viewHolder).bind(this.mSection);
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateLeftTitleItemViewHolder(ViewGroup viewGroup, int i) {
        return new EmptyHolder(this.mContext);
    }
}
