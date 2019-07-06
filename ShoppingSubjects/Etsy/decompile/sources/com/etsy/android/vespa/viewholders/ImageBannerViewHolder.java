package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.vespa.ImageBanner;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: ImageBannerViewHolder.kt */
public final class ImageBannerViewHolder extends BaseViewHolder<ImageBanner> {
    private ImageBanner boundImageBanner;
    private final c imageBatch;

    public ImageBannerViewHolder(ViewGroup viewGroup, c cVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(cVar, "imageBatch");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_image_banner, viewGroup, false));
        this.imageBatch = cVar;
    }

    public void bind(ImageBanner imageBanner) {
        if (!p.a((Object) this.boundImageBanner, (Object) imageBanner)) {
            if (imageBanner != null) {
                Image image = imageBanner.getImage();
                if (image != null) {
                    c cVar = this.imageBatch;
                    View view = this.itemView;
                    if (view == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                    }
                    cVar.a(image, (ImageView) view);
                }
            }
            this.boundImageBanner = imageBanner;
        }
    }
}
