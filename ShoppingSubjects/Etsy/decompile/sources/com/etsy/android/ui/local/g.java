package com.etsy.android.ui.local;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.img.d;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: LocalMarketCardUtil */
public class g {

    /* compiled from: LocalMarketCardUtil */
    public interface a {
        void a();
    }

    public static void a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, c cVar, List<? extends BaseModelImage> list, boolean z, int i, boolean z2, a aVar) {
        if (!z) {
            a(frameLayout, frameLayout2, cVar, list, i, z2, aVar);
        } else {
            b(frameLayout, frameLayout2, cVar, list, i, z2, aVar);
        }
    }

    public static void a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, c cVar, List<? extends BaseModelImage> list, boolean z, int i, a aVar) {
        a(frameLayout, frameLayout2, cVar, list, z, i, l.c((View) frameLayout), aVar);
    }

    public static void a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, c cVar, List<? extends BaseModelImage> list, boolean z, int i) {
        a(frameLayout, frameLayout2, cVar, list, z, i, l.c((View) frameLayout), null);
    }

    public static void a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, c cVar, List<? extends BaseModelImage> list, boolean z, int i, boolean z2) {
        a(frameLayout, frameLayout2, cVar, list, z, i, z2, null);
    }

    private static void a(@NonNull FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, @NonNull c cVar, @NonNull List<? extends BaseModelImage> list, int i, boolean z, a aVar) {
        Resources resources = frameLayout.getContext().getResources();
        frameLayout.setVisibility(0);
        List a2 = a(resources);
        a(cVar, (LinearLayout) frameLayout.findViewById(R.id.image_row), list, i, aVar);
        a(frameLayout, i, R.drawable.bg_event_top, a2);
        if (frameLayout2 != null && z) {
            if (list.size() > i) {
                a(cVar, (LinearLayout) frameLayout2.findViewById(R.id.image_row), list.subList(i, list.size()), i, aVar);
            } else {
                ((LinearLayout) frameLayout2.findViewById(R.id.image_row)).removeAllViews();
            }
            a(frameLayout2, i, R.drawable.bg_event_bottom, a2.subList(i, a2.size()));
            frameLayout2.setVisibility(0);
        } else if (frameLayout2 != null) {
            frameLayout2.setVisibility(8);
        }
    }

    private static void b(@NonNull FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, @NonNull c cVar, @NonNull List<? extends BaseModelImage> list, int i, boolean z, a aVar) {
        if (frameLayout2 != null) {
            frameLayout2.setVisibility(8);
        }
        frameLayout.setVisibility(0);
        double d = (1.0d / ((double) i)) * 0.75d;
        if (z) {
            d *= 2.0d;
        }
        if (list.size() > 0) {
            a(cVar, (LinearLayout) frameLayout.findViewById(R.id.image_row), (BaseModelImage) list.get(0), d, aVar);
        } else {
            ((LinearLayout) frameLayout.findViewById(R.id.image_row)).removeAllViews();
        }
        a(frameLayout, z ? R.drawable.bg_local_store_pattern : R.drawable.bg_local_store_top_pattern, d);
        frameLayout.requestLayout();
    }

    private static void a(@NonNull FrameLayout frameLayout, int i, double d) {
        ImageViewWithAspectRatio imageViewWithAspectRatio = (ImageViewWithAspectRatio) frameLayout.findViewById(R.id.pattern);
        imageViewWithAspectRatio.setAspectRatio(d);
        imageViewWithAspectRatio.setImageResource(i);
        ((LinearLayout) frameLayout.findViewById(R.id.bg_row)).removeAllViews();
    }

    private static void a(@NonNull FrameLayout frameLayout, int i, int i2, List<Integer> list) {
        ImageViewWithAspectRatio imageViewWithAspectRatio = (ImageViewWithAspectRatio) frameLayout.findViewById(R.id.pattern);
        imageViewWithAspectRatio.setAspectRatio((1.0d / ((double) i)) * 0.75d);
        imageViewWithAspectRatio.setImageResource(i2);
        LinearLayout linearLayout = (LinearLayout) frameLayout.findViewById(R.id.bg_row);
        linearLayout.removeAllViews();
        for (int i3 = 0; i3 < i; i3++) {
            ImageViewWithAspectRatio imageViewWithAspectRatio2 = new ImageViewWithAspectRatio(linearLayout.getContext());
            imageViewWithAspectRatio2.setAspectRatio(0.75d);
            imageViewWithAspectRatio2.setLayoutParams(new LayoutParams(0, -1, 1.0f));
            imageViewWithAspectRatio2.setBackgroundColor(((Integer) list.get(i3)).intValue());
            linearLayout.addView(imageViewWithAspectRatio2);
        }
        frameLayout.requestLayout();
    }

    private static List<Integer> a(Resources resources) {
        int[] intArray = resources.getIntArray(R.array.local_grid_image_bg_colors);
        ArrayList arrayList = new ArrayList(intArray.length);
        for (int valueOf : intArray) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        Collections.shuffle(arrayList);
        return arrayList;
    }

    private static void a(@NonNull c cVar, @NonNull LinearLayout linearLayout, @NonNull List<? extends BaseModelImage> list, int i, a aVar) {
        linearLayout.removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            BaseModelImage baseModelImage = null;
            if (list.size() > i2) {
                baseModelImage = (BaseModelImage) list.get(i2);
            }
            ImageViewWithAspectRatio imageViewWithAspectRatio = new ImageViewWithAspectRatio(linearLayout.getContext());
            imageViewWithAspectRatio.setScaleType(ScaleType.CENTER_CROP);
            imageViewWithAspectRatio.setUseStandardRatio(true);
            imageViewWithAspectRatio.setLayoutParams(new LayoutParams(0, 0, 1.0f));
            if (baseModelImage != null) {
                imageViewWithAspectRatio.setImageInfo(baseModelImage, cVar);
                a(aVar, imageViewWithAspectRatio);
            }
            linearLayout.addView(imageViewWithAspectRatio);
        }
    }

    private static void a(@NonNull c cVar, @NonNull LinearLayout linearLayout, @NonNull BaseModelImage baseModelImage, double d, a aVar) {
        linearLayout.removeAllViews();
        ImageViewWithAspectRatio imageViewWithAspectRatio = new ImageViewWithAspectRatio(linearLayout.getContext());
        imageViewWithAspectRatio.setScaleType(ScaleType.CENTER_CROP);
        imageViewWithAspectRatio.setAspectRatio(d);
        imageViewWithAspectRatio.setLayoutParams(new LayoutParams(0, 0, 1.0f));
        imageViewWithAspectRatio.setImageInfo(baseModelImage, cVar);
        a(aVar, imageViewWithAspectRatio);
        linearLayout.addView(imageViewWithAspectRatio);
    }

    private static void a(final a aVar, ImageViewWithAspectRatio imageViewWithAspectRatio) {
        if (aVar != null) {
            imageViewWithAspectRatio.setImageDownloadListener(new d(imageViewWithAspectRatio) {
                public void a(Bitmap bitmap, boolean z) {
                    super.a(bitmap, z);
                    aVar.a();
                }
            });
        }
    }
}
