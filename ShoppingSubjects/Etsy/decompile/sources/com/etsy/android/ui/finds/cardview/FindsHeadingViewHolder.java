package com.etsy.android.ui.finds.cardview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.finds.FindsHeadingModule;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class FindsHeadingViewHolder extends BaseViewHolder<FindsHeadingModule> {
    private final TextView mHeadingTextView = ((TextView) findViewById(R.id.heading_text));

    public FindsHeadingViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.finds_heading_view, viewGroup, false));
    }

    public void bind(FindsHeadingModule findsHeadingModule) {
        this.mHeadingTextView.setText(findsHeadingModule.getText());
    }
}
