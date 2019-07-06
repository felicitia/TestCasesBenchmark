package com.etsy.android.ui.cardview.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.local.g;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;

public class LocalAttendeeShopViewHolder extends ViewHolder {
    private TextView mAttendingNext = ((TextView) this.itemView.findViewById(R.id.selling_time));
    private ImageView mAvatar = ((ImageView) this.itemView.findViewById(R.id.object_avatar));
    private final int mAvatarCornerRadius = this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.feed_avatar_corner_radius);
    private final int mAvatarSize = this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.local_market_details_avatar);
    private View mCommentHoursButton = this.itemView.findViewById(R.id.view_hours_button);
    private FrameLayout mImagesLayout1 = ((FrameLayout) this.itemView.findViewById(R.id.images1));
    private FrameLayout mImagesLayout2 = ((FrameLayout) this.itemView.findViewById(R.id.images2));
    private final int mImagesPerRow = this.itemView.getContext().getResources().getInteger(R.integer.local_market_details_images_per_attendee);
    /* access modifiers changed from: private */
    public a mListener;
    private TextView mShopName = ((TextView) this.itemView.findViewById(R.id.shop_name));
    private View mShopSelectLayout = this.itemView.findViewById(R.id.shop_select_layout);

    public interface a {
        void a(Attendee attendee);

        void a(EtsyId etsyId);
    }

    public LocalAttendeeShopViewHolder(ViewGroup viewGroup, @NonNull a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_local_attendee_shop, viewGroup, false));
        this.mListener = aVar;
    }

    public void bind(c cVar, final Attendee attendee) {
        if (attendee != null) {
            cVar.b(attendee.getAvatarUrl(), this.mAvatar, this.mAvatarCornerRadius, this.mAvatarSize, this.mAvatarSize);
            this.mShopName.setText(attendee.getShopName());
            g.a(this.mImagesLayout1, this.mImagesLayout2, cVar, attendee.getListingImages(), false, this.mImagesPerRow, l.c(this.itemView));
            this.mShopSelectLayout.setOnClickListener(new TrackingOnClickListener(new i[]{attendee}) {
                public void onViewClick(View view) {
                    LocalAttendeeShopViewHolder.this.mListener.a(attendee.getShopId());
                }
            });
            int i = 8;
            if (attendee.attendedInPast()) {
                this.mCommentHoursButton.setVisibility(8);
            } else if (attendee.getSchedule() != null || af.b(attendee.getComment())) {
                this.mCommentHoursButton.setVisibility(0);
                this.mAttendingNext.setText(attendee.getUpcomingStatusLabel());
                TextView textView = this.mAttendingNext;
                if (af.b(attendee.getUpcomingStatusLabel())) {
                    i = 0;
                }
                textView.setVisibility(i);
                this.mCommentHoursButton.setOnClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        LocalAttendeeShopViewHolder.this.mListener.a(attendee);
                    }
                });
            } else {
                this.mCommentHoursButton.setVisibility(4);
            }
        }
    }

    public void bind(c cVar, final ShopCard shopCard) {
        if (shopCard != null) {
            this.mCommentHoursButton.setVisibility(8);
            cVar.b(shopCard.getAvatarUrl(), this.mAvatar, this.mAvatarCornerRadius, this.mAvatarSize, this.mAvatarSize);
            this.mShopName.setText(shopCard.getShopName());
            ArrayList arrayList = new ArrayList();
            for (ListingLike listingImage : shopCard.getCardListings()) {
                arrayList.add((ListingImage) listingImage.getListingImage());
            }
            g.a(this.mImagesLayout1, this.mImagesLayout2, cVar, (List<? extends BaseModelImage>) arrayList, false, this.mImagesPerRow, l.c(this.itemView));
            this.mShopSelectLayout.setOnClickListener(new TrackingOnClickListener(new i[]{shopCard}) {
                public void onViewClick(View view) {
                    LocalAttendeeShopViewHolder.this.mListener.a(shopCard.getShopId());
                }
            });
        }
    }
}
