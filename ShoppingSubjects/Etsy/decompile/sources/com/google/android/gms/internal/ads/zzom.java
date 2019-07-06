package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

@bu
public final class zzom extends RelativeLayout {
    private static final float[] zzbhs = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    @Nullable
    private AnimationDrawable zzbht;

    public zzom(Context context, zzoj zzoj, LayoutParams layoutParams) {
        super(context);
        Preconditions.checkNotNull(zzoj);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzbhs, null, null));
        shapeDrawable.getPaint().setColor(zzoj.getBackgroundColor());
        setLayoutParams(layoutParams);
        ao.g().a((View) this, (Drawable) shapeDrawable);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzoj.getText())) {
            LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzoj.getText());
            textView.setTextColor(zzoj.getTextColor());
            textView.setTextSize((float) zzoj.getTextSize());
            ajh.a();
            int a = jp.a(context, 4);
            ajh.a();
            textView.setPadding(a, 0, jp.a(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzon> zzjs = zzoj.zzjs();
        if (zzjs != null && zzjs.size() > 1) {
            this.zzbht = new AnimationDrawable();
            for (zzon zzjy : zzjs) {
                try {
                    this.zzbht.addFrame((Drawable) ObjectWrapper.unwrap(zzjy.zzjy()), zzoj.zzjt());
                } catch (Exception e) {
                    gv.b("Error while getting drawable.", e);
                }
            }
            ao.g().a((View) imageView, (Drawable) this.zzbht);
        } else if (zzjs.size() == 1) {
            try {
                imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(((zzon) zzjs.get(0)).zzjy()));
            } catch (Exception e2) {
                gv.b("Error while getting drawable.", e2);
            }
        }
        addView(imageView);
    }

    public final void onAttachedToWindow() {
        if (this.zzbht != null) {
            this.zzbht.start();
        }
        super.onAttachedToWindow();
    }
}
