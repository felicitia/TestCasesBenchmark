package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CheckoutSection;
import com.etsy.android.ui.cart.a.d;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.lang.ref.WeakReference;

public class CheckoutSectionViewHolder extends BaseCartGroupItemViewHolder {
    private Button mBtnCheckoutDefault;
    private Button mBtnCheckoutPaypal;
    /* access modifiers changed from: private */
    public final d mClickListener;

    private class a extends ImageSpan {
        private WeakReference<Drawable> b;
        private Resources c;

        public a(Context context) {
            super(context, R.drawable.paypal_logo_white, 1);
            this.c = context.getResources();
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable a2 = a();
            canvas.save();
            int i6 = i5 - a2.getBounds().bottom;
            if (this.mVerticalAlignment == 1) {
                i6 -= paint.getFontMetricsInt().descent;
            }
            canvas.translate(f, (float) (i6 + this.c.getDimensionPixelOffset(R.dimen.msco_paypal_logo_vertical_offset)));
            a2.draw(canvas);
            canvas.restore();
        }

        private Drawable a() {
            WeakReference<Drawable> weakReference = this.b;
            Drawable drawable = weakReference != null ? (Drawable) weakReference.get() : null;
            if (drawable != null) {
                return drawable;
            }
            Drawable drawable2 = getDrawable();
            this.b = new WeakReference<>(drawable2);
            return drawable2;
        }
    }

    public CheckoutSectionViewHolder(ViewGroup viewGroup, d dVar) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_checkout, viewGroup, false), dVar);
    }

    protected CheckoutSectionViewHolder(View view, d dVar) {
        super(view);
        this.mClickListener = dVar;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mBtnCheckoutDefault = (Button) findViewById(R.id.btn_checkout_default);
        this.mBtnCheckoutPaypal = (Button) findViewById(R.id.btn_checkout_paypal);
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        CheckoutSection checkoutSection = (CheckoutSection) cartGroupItem.getData();
        if (checkoutSection != null) {
            this.mBtnCheckoutDefault.setVisibility(8);
            this.mBtnCheckoutPaypal.setVisibility(8);
            String buttonType = checkoutSection.getButtonType();
            char c = 65535;
            if (buttonType.hashCode() == -995205389 && buttonType.equals("paypal")) {
                c = 0;
            }
            if (c != 0) {
                setupDefaultCheckoutButton(checkoutSection);
            } else {
                setupPaypalCheckoutButton(checkoutSection);
            }
        }
    }

    private void setupDefaultCheckoutButton(CheckoutSection checkoutSection) {
        this.mBtnCheckoutDefault.setText(checkoutSection.getButtonText());
        this.mBtnCheckoutDefault.setVisibility(0);
        setupClickListener(this.mBtnCheckoutDefault, checkoutSection);
    }

    private void setupPaypalCheckoutButton(CheckoutSection checkoutSection) {
        Resources resources = this.itemView.getResources();
        String string = resources.getString(R.string.payment_method_label_paypal);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(resources.getString(R.string.checkout_button_with_method, new Object[]{string}));
        int indexOf = spannableStringBuilder.toString().indexOf(string);
        spannableStringBuilder.replace(indexOf, string.length() + indexOf, "  ");
        spannableStringBuilder.setSpan(new a(this.itemView.getContext()), indexOf + 1, indexOf + 2, 33);
        this.mBtnCheckoutPaypal.setText(spannableStringBuilder);
        this.mBtnCheckoutPaypal.setVisibility(0);
        setupClickListener(this.mBtnCheckoutPaypal, checkoutSection);
    }

    /* access modifiers changed from: protected */
    public void setupClickListener(View view, final CheckoutSection checkoutSection) {
        view.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (CheckoutSectionViewHolder.this.mClickListener != null) {
                    CheckoutSectionViewHolder.this.mClickListener.a(checkoutSection);
                }
            }
        });
    }
}
