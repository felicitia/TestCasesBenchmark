package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class LeftAlignedAllCapsHeaderViewHolder extends BaseViewHolder<BasicSectionHeader> {
    private TextView mTextView = ((TextView) this.itemView.findViewById(R.id.search_taxonomy_header));

    public LeftAlignedAllCapsHeaderViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_header_search_taxonomy, viewGroup, false));
    }

    public void bind(BasicSectionHeader basicSectionHeader) {
        this.mTextView.setText(basicSectionHeader.getTitle());
    }
}
