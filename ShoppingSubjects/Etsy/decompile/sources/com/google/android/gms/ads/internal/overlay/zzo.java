package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.jp;

@bu
public final class zzo extends FrameLayout implements OnClickListener {
    private final ImageButton zzbyy;
    private final n zzbyz;

    public zzo(Context context, j jVar, n nVar) {
        super(context);
        this.zzbyz = nVar;
        setOnClickListener(this);
        this.zzbyy = new ImageButton(context);
        this.zzbyy.setImageResource(17301527);
        this.zzbyy.setBackgroundColor(0);
        this.zzbyy.setOnClickListener(this);
        ImageButton imageButton = this.zzbyy;
        ajh.a();
        int a = jp.a(context, jVar.a);
        ajh.a();
        int a2 = jp.a(context, 0);
        ajh.a();
        int a3 = jp.a(context, jVar.b);
        ajh.a();
        imageButton.setPadding(a, a2, a3, jp.a(context, jVar.d));
        this.zzbyy.setContentDescription("Interstitial close button");
        ajh.a();
        jp.a(context, jVar.e);
        ImageButton imageButton2 = this.zzbyy;
        ajh.a();
        int a4 = jp.a(context, jVar.e + jVar.a + jVar.b);
        ajh.a();
        addView(imageButton2, new LayoutParams(a4, jp.a(context, jVar.e + jVar.d), 17));
    }

    public final void onClick(View view) {
        if (this.zzbyz != null) {
            this.zzbyz.zzni();
        }
    }

    public final void zzu(boolean z) {
        ImageButton imageButton;
        int i;
        if (z) {
            imageButton = this.zzbyy;
            i = 8;
        } else {
            imageButton = this.zzbyy;
            i = 0;
        }
        imageButton.setVisibility(i);
    }
}
