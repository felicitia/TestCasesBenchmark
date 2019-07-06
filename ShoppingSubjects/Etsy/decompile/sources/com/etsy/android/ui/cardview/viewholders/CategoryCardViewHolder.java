package com.etsy.android.ui.cardview.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class CategoryCardViewHolder extends BaseViewHolder<TaxonomyCategory> {
    private static final float FEED_IMAGE_HEIGHT_RATIO = 0.75f;
    public View mBorder = findViewById(R.id.segment_frame);
    private final c mImageBatch;
    public int mImageHeight;
    public ImageView mImageView = ((ImageView) findViewById(R.id.img_segment));
    public int mImageWidth;
    /* access modifiers changed from: private */
    public final b mItemClickHandler;
    public TextView mTextShopName = ((TextView) findViewById(R.id.txt_shop_name));
    public TextView mTextTitle = ((TextView) findViewById(R.id.txt_title));

    public CategoryCardViewHolder(ViewGroup viewGroup, b bVar, c cVar, boolean z) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_category_card, viewGroup, false));
        this.mImageBatch = cVar;
        this.mItemClickHandler = bVar;
        Context context = this.itemView.getContext();
        if (z) {
            this.mImageWidth = context.getResources().getDimensionPixelSize(R.dimen.horizontal_category_card_item_width);
        } else {
            this.mImageWidth = new l(getRootView().getContext()).d() / (this.itemView.getResources().getInteger(R.integer.vespa_grid_layout_max_span) / this.itemView.getResources().getInteger(R.integer.vespa_category_card_item_span));
        }
        this.mImageHeight = (int) (((float) this.mImageWidth) * 0.75f);
        this.mImageView.getLayoutParams().height = this.mImageHeight;
        this.mImageView.getLayoutParams().width = this.mImageWidth;
        this.itemView.getLayoutParams().width = this.mImageWidth;
    }

    public void bind(final TaxonomyCategory taxonomyCategory) {
        this.mTextTitle.setText(taxonomyCategory.getName());
        List images = taxonomyCategory.getImages();
        if (images != null && !images.isEmpty()) {
            ListingImage listingImage = (ListingImage) images.get(0);
            this.mImageBatch.a(listingImage.getImageUrl(), this.mImageView, this.mImageWidth, this.mImageHeight, listingImage.getImageColor());
        }
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (CategoryCardViewHolder.this.mItemClickHandler != null) {
                    CategoryCardViewHolder.this.mItemClickHandler.a(taxonomyCategory);
                }
            }
        });
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
