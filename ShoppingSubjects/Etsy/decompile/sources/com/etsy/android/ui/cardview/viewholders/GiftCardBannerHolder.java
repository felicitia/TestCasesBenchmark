package com.etsy.android.ui.cardview.viewholders;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.models.finds.GiftCardBanner;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class GiftCardBannerHolder extends BaseViewHolder<GiftCardBanner> {
    protected c mImageBatch;
    protected FullImageView mImageView;
    protected TextView mTextCta = ((TextView) this.itemView.findViewById(R.id.cta));
    protected TextView mTextSubtitle = ((TextView) this.itemView.findViewById(R.id.subtitle));
    protected TextView mTextTitle = ((TextView) this.itemView.findViewById(R.id.title));

    public GiftCardBannerHolder(ViewGroup viewGroup, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_giftcard_banner, viewGroup, false));
        this.mImageBatch = cVar;
        this.mTextSubtitle.setVisibility(8);
        this.mImageView = (FullImageView) this.itemView.findViewById(R.id.banner_image);
        getRootView().setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                b.a().d("open_create_gift_card", "finds_page");
                e.a((FragmentActivity) GiftCardBannerHolder.this.itemView.getContext()).a().h();
            }
        });
    }

    public void bind(GiftCardBanner giftCardBanner) {
        this.mTextTitle.setText(giftCardBanner.getText());
        this.mTextCta.setText(giftCardBanner.getCta());
        this.mImageView.setImageInfo(giftCardBanner.getGiftCardBannerImages(), this.mImageBatch);
    }
}
