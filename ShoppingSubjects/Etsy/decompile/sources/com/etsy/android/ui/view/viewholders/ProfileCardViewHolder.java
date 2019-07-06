package com.etsy.android.ui.view.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.view.RatingIconView;
import java.util.ArrayList;
import java.util.List;

public class ProfileCardViewHolder extends ViewHolder {
    private static final int IMAGES_PER_GRID_ROW = 2;
    private final ImageView mAvatar;
    private final int mAvatarCornerRadius;
    private final int mAvatarSize;
    private final View mClickRegion;
    private final c mImageBatch;
    private final ViewGroup mImageLayout;
    private List<ListingFullImageView> mImages = new ArrayList(0);
    private final boolean mIsGrid;
    private final int mNumItems;
    private final RatingIconView mRating;
    private final TextView mRatingCount;
    private final TextView mRatingTitle;
    private final TextView mSubTitle;
    private final TextView mTitle;
    private final ImageView mTitleIcon;

    public ProfileCardViewHolder(View view, c cVar, int i, boolean z) {
        super(view);
        this.mImageBatch = cVar;
        this.mNumItems = i;
        this.mClickRegion = view.findViewById(R.id.click_region);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mTitleIcon = (ImageView) view.findViewById(R.id.title_icon);
        this.mSubTitle = (TextView) view.findViewById(R.id.subtitle);
        this.mRating = (RatingIconView) view.findViewById(R.id.rating);
        this.mRatingCount = (TextView) view.findViewById(R.id.rating_count);
        this.mRatingTitle = (TextView) view.findViewById(R.id.rating_title);
        this.mImageLayout = (ViewGroup) view.findViewById(R.id.image_layout);
        this.mAvatar = (ImageView) view.findViewById(R.id.avatar);
        Resources resources = view.getResources();
        this.mAvatarSize = resources.getDimensionPixelOffset(R.dimen.card_avatar_small);
        this.mAvatarCornerRadius = resources.getDimensionPixelOffset(R.dimen.gen_avatar_corners_small);
        if (this.mImageLayout instanceof TableLayout) {
            this.mImages = configureImageGridLayout((TableLayout) this.mImageLayout, this.mNumItems);
        } else if (this.mImageLayout instanceof LinearLayout) {
            this.mImages = configureImageRowLayout((LinearLayout) this.mImageLayout, this.mNumItems);
        }
        this.mIsGrid = z;
    }

    private static List<ListingFullImageView> configureImageRowLayout(LinearLayout linearLayout, int i) {
        Context context = linearLayout.getContext();
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            ListingFullImageView freshListingImageView = freshListingImageView(context, false);
            linearLayout.addView(freshListingImageView);
            arrayList.add(freshListingImageView);
        }
        return arrayList;
    }

    private static List<ListingFullImageView> configureImageGridLayout(TableLayout tableLayout, int i) {
        Context context = tableLayout.getContext();
        int i2 = (i + 1) / 2;
        ArrayList arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < i2; i3++) {
            TableRow tableRow = new TableRow(context);
            ListingFullImageView freshListingImageView = freshListingImageView(context, true);
            tableRow.addView(freshListingImageView);
            arrayList.add(freshListingImageView);
            ListingFullImageView freshListingImageView2 = freshListingImageView(context, true);
            tableRow.addView(freshListingImageView2);
            tableLayout.addView(tableRow, new LayoutParams(-1, -2, 1.0f));
            arrayList.add(freshListingImageView2);
        }
        return arrayList;
    }

    private static ListingFullImageView freshListingImageView(Context context, boolean z) {
        ListingFullImageView listingFullImageView = new ListingFullImageView(context);
        listingFullImageView.setScaleType(ScaleType.CENTER_CROP);
        listingFullImageView.setUseStandardRatio(true);
        if (z) {
            listingFullImageView.setLayoutParams(new TableRow.LayoutParams(0, 0, 1.0f));
        } else {
            listingFullImageView.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 1.0f));
        }
        return listingFullImageView;
    }

    private void setListingImageItems(List<? extends ListingLike> list) {
        int i = 0;
        while (i < this.mNumItems) {
            ListingFullImageView listingFullImageView = (ListingFullImageView) this.mImages.get(i);
            IFullImage listingImage = list.size() > i ? ((ListingLike) list.get(i)).getListingImage() : null;
            if (listingImage != null) {
                listingFullImageView.setImageInfo(listingImage, this.mImageBatch);
            } else if (i == this.mNumItems - 1 || this.mIsGrid) {
                listingFullImageView.setImageDrawable(null);
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image);
            } else {
                listingFullImageView.setImageDrawable(null);
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image_right_divider);
            }
            i++;
        }
    }

    public void bind(final Collection collection) {
        this.mTitle.setText(collection.getName());
        this.mRatingTitle.setVisibility(8);
        this.mRatingCount.setVisibility(8);
        this.mRating.setVisibility(8);
        if (collection.isPrivate()) {
            this.mTitleIcon.setVisibility(0);
        } else {
            this.mTitleIcon.setVisibility(8);
        }
        AnonymousClass1 r0 = new TrackingOnClickListener(new i[]{collection}) {
            public void onViewClick(View view) {
                e.a((FragmentActivity) view.getContext()).a().a(collection);
            }
        };
        int listingsCount = collection.getListingsCount();
        this.mSubTitle.setText(this.itemView.getContext().getResources().getQuantityString(R.plurals.item_titlecase_quantity, listingsCount, new Object[]{af.a((double) listingsCount)}));
        this.mClickRegion.setOnClickListener(r0);
        if (this.mNumItems > 0) {
            setListingImageItems(collection.getRepresentativeListings());
        }
    }

    public void bind(final ShopCard shopCard) {
        String avatarUrl;
        this.mTitle.setText(shopCard.getShopName());
        this.mClickRegion.setOnClickListener(new TrackingOnClickListener(AnalyticsLogAttribute.SHOP_ID, shopCard.getShopId()) {
            public void onViewClick(View view) {
                if (shopCard.getUserId() == null || !shopCard.getUserId().hasId()) {
                    e.a((FragmentActivity) view.getContext()).a().b(shopCard.getShopId());
                } else {
                    e.a((FragmentActivity) view.getContext()).a().a(shopCard.getShopId(), shopCard.getUserId());
                }
            }
        });
        if (this.mNumItems > 0) {
            setListingImageItems(shopCard.getCardListings());
        }
        this.mAvatar.setVisibility(0);
        if (af.a(shopCard.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
            avatarUrl = shopCard.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
        } else {
            avatarUrl = shopCard.getAvatarUrl();
        }
        this.mImageBatch.b(avatarUrl, this.mAvatar, this.mAvatarCornerRadius, this.mAvatarSize, this.mAvatarSize);
        this.mSubTitle.setVisibility(8);
        if (!shopCard.hasRatings() || shopCard.getAverageRating() <= 0.0d) {
            this.mRating.setVisibility(8);
            this.mRatingCount.setVisibility(8);
            this.mRatingTitle.setVisibility(8);
            return;
        }
        this.mRatingCount.setVisibility(0);
        this.mRating.setVisibility(0);
        this.mRating.setRating((float) shopCard.getAverageRating());
        TextView textView = this.mRatingCount;
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(af.a((double) shopCard.getNumRatings()));
        sb.append(")");
        textView.setText(sb.toString());
        this.mRatingTitle.setVisibility(0);
    }

    public void recycle() {
        this.mTitleIcon.setVisibility(8);
        this.mSubTitle.setVisibility(0);
        this.mAvatar.setVisibility(8);
        this.mRatingTitle.setVisibility(8);
        this.mRatingCount.setVisibility(8);
        this.mRating.setVisibility(8);
        int size = this.mImages.size();
        for (int i = 0; i < size; i++) {
            ((ListingFullImageView) this.mImages.get(i)).cleanUp();
        }
    }
}
