package com.etsy.android.uikit.viewholder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.TextViewHolder.b;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class TextViewHolder<T extends b> extends BaseViewHolder<T> {
    private TextView mTextView;

    public static class a implements b {
        public CharSequence a;
        public c b;

        public a(CharSequence charSequence) {
            this.a = charSequence;
        }

        public CharSequence a() {
            return this.a;
        }

        public c b() {
            return this.b;
        }
    }

    public interface b {
        @Nullable
        CharSequence a();

        @Nullable
        c b();
    }

    public interface c {
        void a(TextViewHolder textViewHolder);
    }

    public TextViewHolder(Context context) {
        super(new TextView(context));
        this.mTextView = (TextView) this.itemView;
    }

    public TextViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, @LayoutRes int i, @IdRes int i2) {
        super(layoutInflater.inflate(i, viewGroup, false));
        this.mTextView = (TextView) this.itemView.findViewById(i2);
    }

    public TextView getTextView() {
        return this.mTextView;
    }

    public void setListener(final c cVar) {
        this.itemView.setOnClickListener(cVar != null ? new TrackingOnClickListener() {
            public void onViewClick(View view) {
                cVar.a(TextViewHolder.this);
            }
        } : null);
    }

    public void bind(T t) {
        this.mTextView.setText(t.a());
        setListener(t.b());
    }
}
