package com.contextlogic.wish.dialog.quantitydropdown;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class QuantityDropdownView extends LinearLayout {
    AutoReleasableImageView mDropdownIcon;
    ThemedTextView mQuantityTextView;

    public QuantityDropdownView(Context context) {
        this(context, null);
    }

    public QuantityDropdownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QuantityDropdownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment_quantity_dropdown_view, this);
        this.mQuantityTextView = (ThemedTextView) linearLayout.findViewById(R.id.cart_fragment_quantity_dropdown_text);
        this.mDropdownIcon = (AutoReleasableImageView) linearLayout.findViewById(R.id.cart_fragment_quantity_dropdown_icon);
    }

    public void setText(String str) {
        if (this.mQuantityTextView != null) {
            this.mQuantityTextView.setText(str);
        }
    }
}
