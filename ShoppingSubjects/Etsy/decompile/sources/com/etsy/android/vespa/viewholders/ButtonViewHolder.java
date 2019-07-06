package com.etsy.android.vespa.viewholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.a.c;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Button;
import com.etsy.android.lib.models.apiv3.Button.Style;
import com.etsy.android.lib.models.apiv3.Button.Style.Primary;
import com.etsy.android.lib.models.apiv3.Button.Style.PrimaryAlt;
import com.etsy.android.lib.models.apiv3.Button.Style.Secondary;
import com.etsy.android.stylekit.EtsyButton;
import com.etsy.android.vespa.a.b;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: ButtonViewHolder.kt */
public final class ButtonViewHolder extends BaseViewHolder<Button> {
    private final b clickHandler;

    public final b getClickHandler() {
        return this.clickHandler;
    }

    public ButtonViewHolder(ViewGroup viewGroup, b bVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(bVar, "clickHandler");
        super(new FrameLayout(viewGroup.getContext()));
        this.clickHandler = bVar;
    }

    public void bind(Button button) {
        int i;
        super.bind(button);
        if (button != null) {
            Style style = button.getStyle();
            if (style instanceof Primary) {
                i = c.skButtonPrimary;
            } else if (style instanceof PrimaryAlt) {
                i = c.skButtonPrimaryAlt;
            } else if (style instanceof Secondary) {
                i = c.skButtonSecondary;
            } else {
                throw new NoWhenBranchMatchedException();
            }
            View view = this.itemView;
            p.a((Object) view, "itemView");
            Context context = view.getContext();
            p.a((Object) context, "itemView.context");
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(f.sk_space_1);
            View view2 = this.itemView;
            p.a((Object) view2, "itemView");
            Context context2 = view2.getContext();
            p.a((Object) context2, "itemView.context");
            int dimensionPixelSize2 = context2.getResources().getDimensionPixelSize(f.sk_space_6);
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
            View view3 = this.itemView;
            p.a((Object) view3, "itemView");
            EtsyButton etsyButton = new EtsyButton(view3.getContext(), null, i);
            etsyButton.setText(button.getText());
            etsyButton.setLayoutParams(layoutParams);
            View view4 = etsyButton;
            j.a(view4, new ButtonViewHolder$bind$$inlined$let$lambda$1(button, layoutParams, this, button));
            View view5 = this.itemView;
            if (view5 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            ((ViewGroup) view5).addView(view4);
        }
    }

    public void recycle() {
        super.recycle();
        View view = this.itemView;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        ((ViewGroup) view).removeAllViews();
    }
}
