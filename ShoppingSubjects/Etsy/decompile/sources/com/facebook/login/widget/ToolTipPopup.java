package com.facebook.login.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.login.f.b;
import com.facebook.login.f.c;
import com.facebook.login.f.d;
import java.lang.ref.WeakReference;

public class ToolTipPopup {
    private final String a;
    /* access modifiers changed from: private */
    public final WeakReference<View> b;
    private final Context c;
    /* access modifiers changed from: private */
    public a d;
    /* access modifiers changed from: private */
    public PopupWindow e;
    private Style f = Style.BLUE;
    private long g = 6000;
    private final OnScrollChangedListener h = new OnScrollChangedListener() {
        public void onScrollChanged() {
            if (ToolTipPopup.this.b.get() != null && ToolTipPopup.this.e != null && ToolTipPopup.this.e.isShowing()) {
                if (ToolTipPopup.this.e.isAboveAnchor()) {
                    ToolTipPopup.this.d.b();
                } else {
                    ToolTipPopup.this.d.a();
                }
            }
        }
    };

    public enum Style {
        BLUE,
        BLACK
    }

    private class a extends FrameLayout {
        /* access modifiers changed from: private */
        public ImageView b;
        /* access modifiers changed from: private */
        public ImageView c;
        /* access modifiers changed from: private */
        public View d;
        /* access modifiers changed from: private */
        public ImageView e;

        public a(Context context) {
            super(context);
            c();
        }

        private void c() {
            LayoutInflater.from(getContext()).inflate(d.com_facebook_tooltip_bubble, this);
            this.b = (ImageView) findViewById(c.com_facebook_tooltip_bubble_view_top_pointer);
            this.c = (ImageView) findViewById(c.com_facebook_tooltip_bubble_view_bottom_pointer);
            this.d = findViewById(c.com_facebook_body_frame);
            this.e = (ImageView) findViewById(c.com_facebook_button_xout);
        }

        public void a() {
            this.b.setVisibility(0);
            this.c.setVisibility(4);
        }

        public void b() {
            this.b.setVisibility(4);
            this.c.setVisibility(0);
        }
    }

    public ToolTipPopup(String str, View view) {
        this.a = str;
        this.b = new WeakReference<>(view);
        this.c = view.getContext();
    }

    public void a(Style style) {
        this.f = style;
    }

    public void a() {
        if (this.b.get() != null) {
            this.d = new a(this.c);
            ((TextView) this.d.findViewById(c.com_facebook_tooltip_bubble_view_text_body)).setText(this.a);
            if (this.f == Style.BLUE) {
                this.d.d.setBackgroundResource(b.com_facebook_tooltip_blue_background);
                this.d.c.setImageResource(b.com_facebook_tooltip_blue_bottomnub);
                this.d.b.setImageResource(b.com_facebook_tooltip_blue_topnub);
                this.d.e.setImageResource(b.com_facebook_tooltip_blue_xout);
            } else {
                this.d.d.setBackgroundResource(b.com_facebook_tooltip_black_background);
                this.d.c.setImageResource(b.com_facebook_tooltip_black_bottomnub);
                this.d.b.setImageResource(b.com_facebook_tooltip_black_topnub);
                this.d.e.setImageResource(b.com_facebook_tooltip_black_xout);
            }
            View decorView = ((Activity) this.c).getWindow().getDecorView();
            int width = decorView.getWidth();
            int height = decorView.getHeight();
            d();
            this.d.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE));
            this.e = new PopupWindow(this.d, this.d.getMeasuredWidth(), this.d.getMeasuredHeight());
            this.e.showAsDropDown((View) this.b.get());
            c();
            if (this.g > 0) {
                this.d.postDelayed(new Runnable() {
                    public void run() {
                        ToolTipPopup.this.b();
                    }
                }, this.g);
            }
            this.e.setTouchable(true);
            this.d.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ToolTipPopup.this.b();
                }
            });
        }
    }

    public void a(long j) {
        this.g = j;
    }

    private void c() {
        if (this.e != null && this.e.isShowing()) {
            if (this.e.isAboveAnchor()) {
                this.d.b();
            } else {
                this.d.a();
            }
        }
    }

    public void b() {
        e();
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    private void d() {
        e();
        if (this.b.get() != null) {
            ((View) this.b.get()).getViewTreeObserver().addOnScrollChangedListener(this.h);
        }
    }

    private void e() {
        if (this.b.get() != null) {
            ((View) this.b.get()).getViewTreeObserver().removeOnScrollChangedListener(this.h);
        }
    }
}
