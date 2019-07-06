package com.etsy.android.ui.onboarding;

import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ExploreBanner;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.ui.cardview.clickhandlers.d;
import com.etsy.android.vespa.k;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import kotlin.jvm.internal.p;

/* compiled from: ExploreBannerViewHolder.kt */
public final class ExploreBannerViewHolder extends BaseViewHolder<k> {
    /* access modifiers changed from: private */
    public final d clickHandler;
    private final c imageBatch;
    private ImageView imageView;
    private TextView txtSubtitle;
    private TextView txtTitle;

    /* compiled from: ExploreBannerViewHolder.kt */
    static final class a implements OnClickListener {
        final /* synthetic */ ExploreBannerViewHolder a;
        final /* synthetic */ ExploreBanner b;

        a(ExploreBannerViewHolder exploreBannerViewHolder, ExploreBanner exploreBanner) {
            this.a = exploreBannerViewHolder;
            this.b = exploreBanner;
        }

        public final void onClick(View view) {
            this.a.clickHandler.a(this.b);
        }
    }

    public ExploreBannerViewHolder(ViewGroup viewGroup, d dVar, c cVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(dVar, "clickHandler");
        p.b(cVar, "imageBatch");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_explore_banner, viewGroup, false));
        this.clickHandler = dVar;
        this.imageBatch = cVar;
        View view = this.itemView;
        p.a((Object) view, "itemView");
        TextView textView = (TextView) view.findViewById(com.etsy.android.e.a.explore_banner_title);
        p.a((Object) textView, "itemView.explore_banner_title");
        this.txtTitle = textView;
        View view2 = this.itemView;
        p.a((Object) view2, "itemView");
        TextView textView2 = (TextView) view2.findViewById(com.etsy.android.e.a.explore_banner_subtitle);
        p.a((Object) textView2, "itemView.explore_banner_subtitle");
        this.txtSubtitle = textView2;
        View view3 = this.itemView;
        p.a((Object) view3, "itemView");
        ImageView imageView2 = (ImageView) view3.findViewById(com.etsy.android.e.a.explore_banner_image);
        p.a((Object) imageView2, "itemView.explore_banner_image");
        this.imageView = imageView2;
    }

    public final TextView getTxtTitle() {
        return this.txtTitle;
    }

    public final void setTxtTitle(TextView textView) {
        p.b(textView, "<set-?>");
        this.txtTitle = textView;
    }

    public final TextView getTxtSubtitle() {
        return this.txtSubtitle;
    }

    public final void setTxtSubtitle(TextView textView) {
        p.b(textView, "<set-?>");
        this.txtSubtitle = textView;
    }

    public final ImageView getImageView() {
        return this.imageView;
    }

    public final void setImageView(ImageView imageView2) {
        p.b(imageView2, "<set-?>");
        this.imageView = imageView2;
    }

    public void bind(k kVar) {
        p.b(kVar, "data");
        super.bind(kVar);
        ExploreBanner exploreBanner = (ExploreBanner) kVar;
        this.txtTitle.setText(exploreBanner.getTitle());
        this.txtTitle.setTextColor(exploreBanner.getTitleColor());
        this.txtSubtitle.setText(exploreBanner.getSubtitle());
        this.txtSubtitle.setTextColor(exploreBanner.getSubtitleColor());
        TextView textView = this.txtSubtitle;
        CharSequence subtitle = exploreBanner.getSubtitle();
        int i = 0;
        if (subtitle == null || subtitle.length() == 0) {
            i = 8;
        }
        textView.setVisibility(i);
        Image image = exploreBanner.getImage();
        if (image != null) {
            j.a(this.imageView);
            this.imageBatch.a(image, this.imageView);
        } else {
            j.b(this.imageView);
        }
        clipBorder();
        View view = this.itemView;
        p.a((Object) view, "itemView");
        Drawable background = view.getBackground();
        p.a((Object) background, "itemView.background");
        changeBackgroundColor(background, exploreBanner.getBackgroundColor());
        this.itemView.setOnClickListener(new a(this, exploreBanner));
    }

    private final void clipBorder() {
        if (VERSION.SDK_INT >= 21) {
            View view = this.itemView;
            p.a((Object) view, "itemView");
            view.setClipToOutline(true);
        }
    }

    private final void changeBackgroundColor(Drawable drawable, int i) {
        if (drawable instanceof ShapeDrawable) {
            Paint paint = ((ShapeDrawable) drawable).getPaint();
            p.a((Object) paint, "drawable.paint");
            paint.setColor(i);
        } else if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setColor(i);
        } else if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable).setColor(i);
        }
    }
}
