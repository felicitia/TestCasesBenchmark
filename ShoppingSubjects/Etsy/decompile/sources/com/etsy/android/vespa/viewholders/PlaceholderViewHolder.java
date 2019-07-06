package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.i;
import com.etsy.android.vespa.k;

public class PlaceholderViewHolder extends BaseViewHolder<k> {
    private final TextView mText = ((TextView) this.itemView.findViewById(i.text));

    public PlaceholderViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(a.k.list_item_card_view_placeholder, viewGroup, false));
    }

    public void bind(k kVar) {
        TextView textView = this.mText;
        StringBuilder sb = new StringBuilder();
        sb.append("Placeholder for view type: ");
        sb.append(this.itemView.getResources().getResourceEntryName(kVar.getViewType()));
        textView.setText(sb.toString());
    }
}
