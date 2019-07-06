package com.akexorcist.roundcornerprogressbar;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.akexorcist.roundcornerprogressbar.common.BaseRoundCornerProgressBar;

public class RoundCornerProgressBar extends BaseRoundCornerProgressBar {
    /* access modifiers changed from: protected */
    public void initStyleable(Context context, AttributeSet attributeSet) {
    }

    /* access modifiers changed from: protected */
    public void initView() {
    }

    /* access modifiers changed from: protected */
    public void onViewDraw() {
    }

    public RoundCornerProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RoundCornerProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int initLayout() {
        return R.layout.layout_round_corner_progress_bar;
    }

    /* access modifiers changed from: protected */
    public void drawProgress(LinearLayout linearLayout, float f, float f2, float f3, int i, int i2, int i3, boolean z) {
        GradientDrawable createGradientDrawable = createGradientDrawable(i3);
        float f4 = (float) (i - (i2 / 2));
        createGradientDrawable.setCornerRadii(new float[]{f4, f4, f4, f4, f4, f4, f4, f4});
        if (VERSION.SDK_INT >= 16) {
            linearLayout.setBackground(createGradientDrawable);
        } else {
            linearLayout.setBackgroundDrawable(createGradientDrawable);
        }
        int i4 = (int) ((f3 - ((float) (i2 * 2))) / (f / f2));
        LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.width = i4;
        linearLayout.setLayoutParams(layoutParams);
    }
}
