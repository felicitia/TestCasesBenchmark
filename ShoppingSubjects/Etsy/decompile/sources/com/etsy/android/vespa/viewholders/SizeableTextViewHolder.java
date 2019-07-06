package com.etsy.android.vespa.viewholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.extensions.k;
import com.etsy.android.lib.a;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.vespa.SizeableText;
import kotlin.jvm.internal.p;

/* compiled from: SizeableTextViewHolder.kt */
public final class SizeableTextViewHolder extends BaseViewHolder<SizeableText> {
    public SizeableTextViewHolder(ViewGroup viewGroup) {
        p.b(viewGroup, ResponseConstants.PARENT);
        super(k.a(viewGroup, a.k.list_item_sizeable_text, false, 2, null));
    }

    public void bind(SizeableText sizeableText) {
        super.bind(sizeableText);
        if (sizeableText != null) {
            View view = this.itemView;
            if (!(view instanceof TextView)) {
                view = null;
            }
            TextView textView = (TextView) view;
            if (textView != null) {
                textView.setText(sizeableText.getText());
                Context context = textView.getContext();
                p.a((Object) context, ResponseConstants.CONTEXT);
                textView.setTextSize(0, context.getResources().getDimension(sizeableText.getSize().getTextSize()));
            }
        }
    }
}
