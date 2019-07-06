package com.etsy.android.uikit.viewholder;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.a.i;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.MachineTranslationButton;
import com.etsy.android.uikit.viewholder.TextViewHolder.c;
import com.etsy.android.uikit.viewholder.TranslationToggleViewHolder.a;

public class TranslationToggleViewHolder<T extends a> extends TextViewHolder<T> {
    /* access modifiers changed from: private */
    public final MachineTranslationButton mTranslateButton = ((MachineTranslationButton) this.itemView.findViewById(i.translate));

    public interface a extends com.etsy.android.uikit.viewholder.TextViewHolder.b {
        boolean c();

        MachineTranslationViewState d();
    }

    public static class b extends com.etsy.android.uikit.viewholder.TextViewHolder.a implements a {
        private final com.etsy.android.uikit.view.MachineTranslationButton.a c;

        public b(CharSequence charSequence, com.etsy.android.uikit.view.MachineTranslationButton.a aVar) {
            super(charSequence);
            this.c = aVar;
        }

        public boolean c() {
            return this.c.isTranslationEligible();
        }

        public MachineTranslationViewState d() {
            return this.c.getTranslationState();
        }
    }

    public TranslationToggleViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, @LayoutRes int i, @IdRes int i2) {
        super(layoutInflater, viewGroup, i, i2);
    }

    public void bind(final T t) {
        super.bind(t);
        if (t.c()) {
            this.mTranslateButton.setVisibility(0);
            this.mTranslateButton.configureForState(t.d());
            this.mTranslateButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (!t.d().hasLoadedTranslation()) {
                        t.d().setLoadingTranslation();
                    }
                    TranslationToggleViewHolder.this.mTranslateButton.configureForState(t.d());
                    t.b().a(TranslationToggleViewHolder.this);
                }
            });
            return;
        }
        this.mTranslateButton.setVisibility(8);
    }

    public void setListener(final c cVar) {
        this.mTranslateButton.setOnClickListener(cVar != null ? new TrackingOnClickListener() {
            public void onViewClick(View view) {
                cVar.a(TranslationToggleViewHolder.this);
            }
        } : null);
    }
}
