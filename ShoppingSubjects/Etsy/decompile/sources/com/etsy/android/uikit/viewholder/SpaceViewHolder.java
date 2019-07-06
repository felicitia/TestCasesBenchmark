package com.etsy.android.uikit.viewholder;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.etsy.android.lib.a.k;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class SpaceViewHolder extends BaseViewHolder<a> {

    public static class a {
        public final int a;
        public final int b;
        @ColorInt
        public final int c;

        public a(@ColorInt int i, int i2, int i3) {
            this.c = i;
            this.a = i2;
            this.b = i3;
        }
    }

    public SpaceViewHolder(Context context, ViewGroup viewGroup) {
        super(LayoutInflater.from(context).inflate(k.space, viewGroup, false));
    }

    public void bind(@NonNull a aVar) {
        this.itemView.setBackgroundColor(aVar.c);
        LayoutParams layoutParams = this.itemView.getLayoutParams();
        layoutParams.width = aVar.a;
        layoutParams.height = aVar.b;
    }
}
