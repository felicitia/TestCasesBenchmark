package com.etsy.android.ui.cardview.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.ui.search.v2.o;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class TaxonomyCategoryRowViewHolder extends BaseViewHolder<TaxonomyCategory> {
    b mClickListener;
    private int mIconSize;
    c mImageBatch;
    private ImageView mImageView = ((ImageView) this.itemView.findViewById(R.id.search_taxonomy_image));
    private TextView mTextView = ((TextView) this.itemView.findViewById(R.id.search_taxonomy_text));

    public TaxonomyCategoryRowViewHolder(ViewGroup viewGroup, boolean z, b bVar, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_taxonomy_category_row, viewGroup, z));
        this.mClickListener = bVar;
        this.mImageBatch = cVar;
        this.mIconSize = viewGroup.getResources().getDimensionPixelSize(R.dimen.category_row_icon_size);
    }

    public void bind(final TaxonomyCategory taxonomyCategory) {
        ImageView imageView = this.mImageView;
        int i = this.mIconSize;
        this.mTextView.setText(taxonomyCategory.getName());
        imageView.setImageDrawable(null);
        String path = taxonomyCategory.getPath();
        if (!TextUtils.isEmpty(path)) {
            int a = o.a(path);
            if (a != R.drawable.bg_empty_image) {
                imageView.setImageResource(a);
                ((MarginLayoutParams) imageView.getLayoutParams()).setMargins(0, 0, 0, 0);
                imageView.getLayoutParams().width = -2;
                imageView.getLayoutParams().height = -2;
            }
        }
        List images = taxonomyCategory.getImages();
        if (images != null && images.size() > 0) {
            ListingImage listingImage = (ListingImage) images.get(0);
            this.mImageBatch.a(listingImage.getImageUrlForPixelWidth(i), imageView, i, i, listingImage.getImageColor());
        }
        this.itemView.setOnClickListener(new TrackingOnClickListener(new i[]{taxonomyCategory}) {
            public void onViewClick(View view) {
                if (TaxonomyCategoryRowViewHolder.this.mClickListener != null) {
                    TaxonomyCategoryRowViewHolder.this.mClickListener.a(taxonomyCategory);
                }
            }
        });
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
