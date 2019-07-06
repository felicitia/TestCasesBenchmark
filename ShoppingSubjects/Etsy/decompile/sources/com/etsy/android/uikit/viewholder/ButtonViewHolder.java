package com.etsy.android.uikit.viewholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.etsy.android.lib.a.i;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.ButtonViewHolder.a;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class ButtonViewHolder<T extends a> extends BaseViewHolder<T> {
    protected final Button mButton;

    public static class a {
        public final String a;
        public final TrackingOnClickListener b;

        public a(@Nullable String str, @Nullable TrackingOnClickListener trackingOnClickListener) {
            this.a = str;
            this.b = trackingOnClickListener;
        }
    }

    public ButtonViewHolder(View view) {
        super(view);
        this.mButton = (Button) view.findViewById(i.button);
    }

    public void bind(@NonNull a aVar) {
        this.mButton.setText(aVar.a);
        if (aVar.b != null) {
            this.mButton.setOnClickListener(aVar.b);
        }
    }
}
