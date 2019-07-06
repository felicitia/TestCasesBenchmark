package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.k;

public class SingleLineTextViewHolder extends BaseViewHolder<k> {
    /* access modifiers changed from: private */
    public final b mClickHandler;

    public SingleLineTextViewHolder(ViewGroup viewGroup, b bVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(a.k.sk_dropdown_item, viewGroup, false));
        this.mClickHandler = bVar;
    }

    public void bind(final k kVar) {
        ((TextView) this.itemView).setText(kVar.toString());
        if (this.mClickHandler != null) {
            this.itemView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    SingleLineTextViewHolder.this.mClickHandler.a(kVar);
                }
            });
        }
    }
}
