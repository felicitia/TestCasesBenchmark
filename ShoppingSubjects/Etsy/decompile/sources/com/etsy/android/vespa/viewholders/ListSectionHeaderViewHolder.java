package com.etsy.android.vespa.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;

public class ListSectionHeaderViewHolder extends BaseViewHolder<BasicSectionHeader> {
    TextView mTextSubtitle = ((TextView) findViewById(i.txt_module_subtitle));
    TextView mTextTitle = ((TextView) findViewById(i.txt_module_title));

    public ListSectionHeaderViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_card_view_section_header, viewGroup, false));
    }

    public void bind(BasicSectionHeader basicSectionHeader) {
        if (TextUtils.isEmpty(basicSectionHeader.getTitle())) {
            this.mTextTitle.setVisibility(8);
        } else {
            this.mTextTitle.setText(basicSectionHeader.getTitle());
            this.mTextTitle.setVisibility(0);
        }
        if (TextUtils.isEmpty(basicSectionHeader.getSubtitle())) {
            this.mTextSubtitle.setVisibility(8);
            return;
        }
        this.mTextSubtitle.setVisibility(0);
        this.mTextSubtitle.setText(basicSectionHeader.getSubtitle());
    }
}
