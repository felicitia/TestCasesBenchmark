package com.etsy.android.uikit.viewholder;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class ButtonAndDescriptionViewHolder extends BaseViewHolder<a> {
    protected final Button mButton = ((Button) findViewById(i.button));
    /* access modifiers changed from: private */
    public final b mClickHandler;
    private final TextView mDescription;

    public static class a {
        public String a;
        public String b = "";
        int c;
        @ColorInt
        public int d = 0;

        public a(@NonNull String str, @Nullable String str2) {
            this.a = str;
            this.b = str2;
        }

        public void a(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }
    }

    public ButtonAndDescriptionViewHolder(ViewGroup viewGroup, b bVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.button_blue_with_description, viewGroup, false));
        TextView textView = (TextView) findViewById(16908308);
        textView.setVisibility(0);
        this.mClickHandler = bVar;
        this.mDescription = textView;
    }

    public void bind(@NonNull final a aVar) {
        if ((aVar.c & 1) == 1) {
            this.mButton.setText(aVar.a);
            this.mButton.setVisibility(0);
        } else {
            this.mButton.setVisibility(8);
        }
        if ((aVar.c & 2) == 2) {
            this.mDescription.setText(aVar.b);
            this.mDescription.setVisibility(0);
        } else {
            this.mDescription.setVisibility(8);
        }
        this.itemView.setBackgroundColor(aVar.d);
        this.mButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (ButtonAndDescriptionViewHolder.this.mClickHandler != null) {
                    ButtonAndDescriptionViewHolder.this.mClickHandler.a(aVar);
                }
            }
        });
    }
}
