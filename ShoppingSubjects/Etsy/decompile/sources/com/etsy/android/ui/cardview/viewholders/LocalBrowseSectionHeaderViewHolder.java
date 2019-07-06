package com.etsy.android.ui.cardview.viewholders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;

public class LocalBrowseSectionHeaderViewHolder extends ViewHolder {
    private TextView mTitle = ((TextView) this.itemView.findViewById(R.id.title));

    public LocalBrowseSectionHeaderViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_title_local_browse, viewGroup, false));
    }

    public void bind(LocalBrowseModule localBrowseModule) {
        if (localBrowseModule != null) {
            this.mTitle.setText(localBrowseModule.getTitle());
        }
    }
}
