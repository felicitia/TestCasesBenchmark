package com.etsy.android.uikit.viewholder;

import android.view.View;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class TitleAndDescriptionViewHolder extends BaseViewHolder<a> {
    public final TextView mSubtitle;
    public final TextView mTitle;

    public static class a {
        public final CharSequence a;
        public final CharSequence b;

        public a(CharSequence charSequence, CharSequence charSequence2) {
            this.a = charSequence;
            this.b = charSequence2;
        }
    }

    public TitleAndDescriptionViewHolder(View view) {
        super(view);
        this.mTitle = (TextView) view.findViewById(i.title);
        this.mSubtitle = (TextView) view.findViewById(i.subtitle);
    }

    public void bind(a aVar) {
        this.mTitle.setText(aVar.a);
        this.mSubtitle.setText(aVar.b);
    }
}
