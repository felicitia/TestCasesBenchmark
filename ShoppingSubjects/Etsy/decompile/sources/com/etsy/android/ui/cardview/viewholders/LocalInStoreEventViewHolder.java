package com.etsy.android.ui.cardview.viewholders;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.local.g;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;

public class LocalInStoreEventViewHolder extends ViewHolder {
    private FrameLayout mImageLayout1 = ((FrameLayout) this.itemView.findViewById(R.id.image_layout_1));
    private FrameLayout mImageLayout2 = ((FrameLayout) this.itemView.findViewById(R.id.image_layout_2));
    private int mImagesPerRow = this.itemView.getContext().getResources().getInteger(R.integer.local_browse_images_per_row);
    /* access modifiers changed from: private */
    public a mListener;
    private TextView mMarketAttendance = ((TextView) this.itemView.findViewById(R.id.market_attendance));
    private TextView mMarketDate = ((TextView) this.itemView.findViewById(R.id.market_date));
    private TextView mMarketTitle = ((TextView) this.itemView.findViewById(R.id.market_title));

    public interface a {
        void a(EtsyId etsyId);
    }

    public LocalInStoreEventViewHolder(ViewGroup viewGroup, @NonNull a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_local_in_store_event, viewGroup, false));
        this.mListener = aVar;
    }

    public void bind(c cVar, final LocalMarketCard localMarketCard) {
        if (localMarketCard != null) {
            Resources resources = this.itemView.getResources();
            boolean equals = LocalMarket.MARKET_TYPE_WHOLESALE_BUYER.equals(localMarketCard.getType());
            this.mMarketDate.setText(localMarketCard.getDateSubtitle());
            ArrayList arrayList = new ArrayList();
            if (!equals) {
                arrayList.addAll(localMarketCard.getListingImages());
            } else {
                arrayList.addAll(localMarketCard.getStoreImages());
            }
            g.a(this.mImageLayout1, this.mImageLayout2, cVar, arrayList, equals, this.mImagesPerRow);
            this.mMarketTitle.setText(localMarketCard.getTitle());
            this.mMarketAttendance.setText(localMarketCard.getAttendanceSummary(resources));
            this.itemView.setOnClickListener(new TrackingOnClickListener(new i[]{localMarketCard}) {
                public void onViewClick(View view) {
                    LocalInStoreEventViewHolder.this.mListener.a(localMarketCard.getLocalMarketId());
                }
            });
        }
    }
}
