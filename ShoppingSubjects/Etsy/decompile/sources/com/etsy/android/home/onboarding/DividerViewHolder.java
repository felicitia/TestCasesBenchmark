package com.etsy.android.home.onboarding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.vespa.Divider;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import kotlin.jvm.internal.p;

/* compiled from: DividerViewHolder.kt */
public final class DividerViewHolder extends BaseViewHolder<Divider> {
    private final ViewGroup parent;

    public void bind(Divider divider) {
        p.b(divider, "data");
    }

    public final ViewGroup getParent() {
        return this.parent;
    }

    public DividerViewHolder(ViewGroup viewGroup) {
        p.b(viewGroup, ResponseConstants.PARENT);
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_divider, viewGroup, false));
        this.parent = viewGroup;
    }
}
