package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.ui.cardview.clickhandlers.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class FindsSmallCrosslinkViewHolder extends BaseViewHolder<FindsCard> {
    /* access modifiers changed from: private */
    public e mClickListener;
    private ImageViewWithAspectRatio mImage = ((ImageViewWithAspectRatio) findViewById(R.id.image));
    private c mImageBatch;
    private TextView mTitle = ((TextView) findViewById(R.id.title));

    public FindsSmallCrosslinkViewHolder(ViewGroup viewGroup, e eVar, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_finds_small_crosslink, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickListener = eVar;
    }

    public void bind(final FindsCard findsCard) {
        List images = findsCard.getImages();
        if (images != null && images.size() > 0) {
            this.mImage.setImageInfo((BaseModelImage) images.get(0), this.mImageBatch);
        }
        this.mTitle.setText(findsCard.getTitle());
        getRootView().setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                FindsSmallCrosslinkViewHolder.this.mClickListener.a(findsCard);
            }
        });
    }

    public void recycle() {
        this.mImage.setImageDrawable(null);
    }
}
