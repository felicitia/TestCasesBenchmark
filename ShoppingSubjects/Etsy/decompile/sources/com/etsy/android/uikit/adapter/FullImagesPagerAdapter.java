package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.img.e;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.uikit.adapter.ImagesPagerAdapter.b;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.TouchImageView;
import java.util.List;

public class FullImagesPagerAdapter extends ImagesPagerAdapter<BaseModelImage> {
    private TouchImageView[] mZoomViews;

    public interface a {
        void onViewUnsupportedImage(String str);
    }

    public FullImagesPagerAdapter(Activity activity, c cVar, @NonNull w wVar, b bVar, @NonNull com.etsy.android.lib.util.b.a aVar) {
        super(activity, wVar, k.imageview_loading_zoom, cVar, aVar);
        setOnImageClickListener(bVar);
    }

    public void resetZoomAtPosition(int i) {
        if (i >= 0 && this.mZoomViews.length > i && this.mZoomViews[i] != null) {
            this.mZoomViews[i].resetZoom();
        }
    }

    public void setImages(List list) {
        this.mZoomViews = new TouchImageView[(list != null ? list.size() : 0)];
        super.setImages(list);
    }

    /* access modifiers changed from: protected */
    public ImageView setUpViewAndReturnImageView(View view, int i) {
        ImageView upViewAndReturnImageView = super.setUpViewAndReturnImageView(view, i);
        if (upViewAndReturnImageView instanceof TouchImageView) {
            this.mZoomViews[i] = (TouchImageView) upViewAndReturnImageView;
        }
        return upViewAndReturnImageView;
    }

    /* access modifiers changed from: protected */
    public void onUnsupportedImageShown(View view, View view2, final String str) {
        super.onUnsupportedImageShown(view, view2, str);
        view.findViewById(i.unsupported_view).setVisibility(0);
        View findViewById = view.findViewById(i.view_btn);
        if (this.mActivity.get() instanceof a) {
            findViewById.setOnClickListener(new TrackingOnClickListener(AnalyticsLogAttribute.URL, str) {
                public void onViewClick(View view) {
                    ((a) FullImagesPagerAdapter.this.mActivity.get()).onViewUnsupportedImage(str);
                }
            });
        } else {
            findViewById.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public e getImageDownloadRequest(@NonNull String str, @NonNull View view, @NonNull ImageView imageView, @NonNull View view2, int i, @NonNull BaseModelImage baseModelImage) {
        e imageDownloadRequest = super.getImageDownloadRequest(str, view, imageView, view2, i, baseModelImage);
        imageDownloadRequest.d(0);
        return imageDownloadRequest;
    }
}
