package com.etsy.android.ui.cardview.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.aa;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.s;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.shopshare.ShareItem;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.y;
import com.etsy.android.ui.cardview.clickhandlers.f;
import com.etsy.android.ui.cardview.clickhandlers.n;
import com.etsy.android.uikit.adapter.AnnotationAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.etsy.android.uikit.view.TaggableImageView;
import com.etsy.android.uikit.view.TaggableImageView.d;
import com.etsy.android.uikit.view.TaggableImageView.e;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class ShopShareCardViewHolder extends BaseViewHolder<ShopShareCard> {
    private static final String MANAGE_POST_MINIMUM_SOE_VERSION = "2.22.0.39";
    private AnnotationAdapter mAdapter;
    /* access modifiers changed from: private */
    public TextView mCaption;
    /* access modifiers changed from: private */
    public ScrollView mCaptionScrollView;
    /* access modifiers changed from: private */
    public final n mCardViewItemClickHandler;
    private Context mContext;
    private TextView mCreateDate;
    private final c mImageBatch;
    /* access modifiers changed from: private */
    public TaggableImageView mImageView;
    private ImageView mMenuIcon;
    /* access modifiers changed from: private */
    public LinearLayout mShareDetails;
    private FrameLayout mShareListing;
    private ImageView mShopAvatarView;
    private TextView mShopName;
    private boolean mShowMenuIcon = true;
    private TextView mTagLabel;

    private class a implements e {
        private ShopShareCard b;

        public void a() {
        }

        public void a(int i, int i2) {
        }

        public void a(View view, d dVar, int i, int i2) {
        }

        public void b() {
        }

        public a(ShopShareCard shopShareCard) {
            this.b = shopShareCard;
        }

        public void a(d dVar) {
            ShopShareCardViewHolder.this.mCardViewItemClickHandler.a(dVar, this.b);
        }

        public void c() {
            ShopShareCardViewHolder.this.mCardViewItemClickHandler.b(this.b);
        }
    }

    private class b implements OnClickListener {
        private ShopShareCard b;

        public b(ShopShareCard shopShareCard) {
            this.b = shopShareCard;
        }

        public void onClick(View view) {
            ShopShareCardViewHolder.this.mCardViewItemClickHandler.d(this.b);
        }
    }

    public ShopShareCardViewHolder(ViewGroup viewGroup, n nVar, c cVar, int i, f fVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        this.mImageBatch = cVar;
        this.mContext = viewGroup.getContext();
        this.mCardViewItemClickHandler = nVar;
        this.mShareListing = (FrameLayout) findViewById(R.id.share_listing);
        this.mShareDetails = (LinearLayout) findViewById(R.id.share_details);
        this.mCreateDate = (TextView) findViewById(R.id.create_date);
        this.mMenuIcon = (ImageView) findViewById(R.id.menu_icon);
        this.mCaption = (TextView) findViewById(R.id.caption);
        this.mShopName = (TextView) findViewById(R.id.shop_name);
        this.mShopAvatarView = (ImageView) findViewById(R.id.shop_avatar);
        this.mTagLabel = (TextView) findViewById(R.id.tag_label);
        this.mCaptionScrollView = (ScrollView) findViewById(R.id.caption_scroll_view);
        this.mImageView = (TaggableImageView) findViewById(R.id.shop_share_image);
        if (this.mShareListing != null) {
            ListingCardViewHolder listingCardViewHolder = new ListingCardViewHolder(viewGroup, fVar, cVar, false, false);
            this.mShareListing.setTag(listingCardViewHolder);
        }
    }

    public ShopShareCardViewHolder setShowMenuIcon(boolean z) {
        this.mShowMenuIcon = z;
        return this;
    }

    public void bind(final ShopShareCard shopShareCard) {
        super.bind(shopShareCard);
        ShareItem shareItem = shopShareCard.getShareItem();
        this.mImageView.getImageView().setImageInfo(shareItem.getPrimaryMedia().getImage(), this.mImageBatch);
        this.mImageBatch.a(shopShareCard.getShopAvatarUrl(), this.mShopAvatarView);
        this.mAdapter = new AnnotationAdapter(this.mContext, (ImageView) this.mImageView.getImageView(), shareItem);
        this.mImageView.setAdapter(this.mAdapter);
        this.mImageView.setListener(new a(shopShareCard));
        this.mCreateDate.setText(ai.a(this.mAdapter.getContext(), Long.valueOf(shareItem.getCreateDate().getTime())));
        this.mCaption.setText(shareItem.getText());
        this.mMenuIcon.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                s.a("feed.ellipsis.tapped");
                ShopShareCardViewHolder.this.getManageShopSharePopup(shopShareCard).showAsDropDown(view);
            }
        });
        b bVar = new b(shopShareCard);
        this.mShopAvatarView.setOnClickListener(bVar);
        this.mShopName.setOnClickListener(bVar);
        if (!shouldShowMenuIcon(shopShareCard)) {
            this.mMenuIcon.setVisibility(8);
        } else {
            this.mMenuIcon.setVisibility(0);
        }
        this.mShopName.setText(shopShareCard.getShopDisplayName());
        if (this.mTagLabel != null) {
            this.mTagLabel.setText(R.string.tag_label);
        }
        if (this.mShareListing != null) {
            ListingCardViewHolder listingCardViewHolder = (ListingCardViewHolder) this.mShareListing.getTag();
            listingCardViewHolder.bind((ListingCard) shareItem.getPrimaryAnnotation().getObject());
            View view = listingCardViewHolder.itemView;
            view.setLayoutParams(new LayoutParams(-1, -2));
            ((CardView) view).setCardElevation(0.0f);
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.mShareListing.addView(view);
            j.a(this.mImageView.getViewTreeObserver(), (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    j.b(ShopShareCardViewHolder.this.mImageView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                    int measuredHeight = ShopShareCardViewHolder.this.mShareDetails.getMeasuredHeight();
                    int measuredWidth = ShopShareCardViewHolder.this.mImageView.getMeasuredWidth();
                    if (measuredHeight > measuredWidth) {
                        ShopShareCardViewHolder.this.mShareDetails.setLayoutParams(new LinearLayout.LayoutParams(ShopShareCardViewHolder.this.mShareDetails.getMeasuredWidth(), measuredWidth));
                    }
                    if (ShopShareCardViewHolder.this.mCaption.getHeight() < ShopShareCardViewHolder.this.mCaptionScrollView.getHeight()) {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ShopShareCardViewHolder.this.mShareDetails.getMeasuredWidth(), ShopShareCardViewHolder.this.mCaption.getHeight());
                        layoutParams.setMargins(0, 0, (int) ShopShareCardViewHolder.this.mImageView.getResources().getDimension(R.dimen.margin_large), 0);
                        ShopShareCardViewHolder.this.mCaptionScrollView.setLayoutParams(layoutParams);
                    }
                }
            });
            this.mCaptionScrollView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (ShopShareCardViewHolder.this.mCaption.getHeight() > ShopShareCardViewHolder.this.mCaptionScrollView.getHeight()) {
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    return false;
                }
            });
        }
    }

    public void recycle() {
        if (this.mShareDetails != null) {
            this.mCaption.setText(null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mShareDetails.getMeasuredWidth(), 0, 1.0f);
            layoutParams.setMargins(0, 0, (int) this.mImageView.getResources().getDimension(R.dimen.margin_large), 0);
            this.mCaptionScrollView.setLayoutParams(layoutParams);
        }
        this.mImageView.getImageView().cleanUp();
    }

    private boolean isOwnedByUser(ShopShareCard shopShareCard) {
        EtsyId ownerId = shopShareCard.getOwnerId();
        return ownerId != null && ownerId.getId().equals(SharedPreferencesUtility.j(this.mAdapter.getContext()).getId());
    }

    private boolean shouldShowMenuIcon(ShopShareCard shopShareCard) {
        EtsyApplication etsyApplication = (EtsyApplication) this.mContext.getApplicationContext();
        return this.mShowMenuIcon && (!isOwnedByUser(shopShareCard) || (etsyApplication.isSOEInstalled() && aa.a(MANAGE_POST_MINIMUM_SOE_VERSION).compareTo(aa.a(etsyApplication.getSOEInstalledVersion())) <= 0));
    }

    /* access modifiers changed from: private */
    public PopupWindow getManageShopSharePopup(final ShopShareCard shopShareCard) {
        View inflate = LayoutInflater.from(this.itemView.getContext()).inflate(R.layout.popup_manage_shop_share_card, null);
        final PopupWindow a2 = y.a(inflate);
        if (isOwnedByUser(shopShareCard)) {
            View findViewById = inflate.findViewById(R.id.manage_post);
            View findViewById2 = inflate.findViewById(R.id.hide_post);
            View findViewById3 = inflate.findViewById(R.id.hide_shop_posts);
            findViewById.setVisibility(0);
            findViewById2.setVisibility(8);
            findViewById3.setVisibility(8);
            inflate.findViewById(R.id.manage_post).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    s.a("feed.manage_post.tapped");
                    ShopShareCardViewHolder.this.mCardViewItemClickHandler.c(shopShareCard);
                    a2.dismiss();
                }
            });
        } else {
            inflate.findViewById(R.id.hide_post).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    s.a("feed.block_this_post.tapped");
                    ShopShareCardViewHolder.this.mCardViewItemClickHandler.a(ShopShareCardViewHolder.this.getAdapterPosition(), shopShareCard);
                    a2.dismiss();
                }
            });
            inflate.findViewById(R.id.hide_shop_posts).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    s.a("feed.block_seller_posts.tapped");
                    ShopShareCardViewHolder.this.mCardViewItemClickHandler.b(ShopShareCardViewHolder.this.getAdapterPosition(), shopShareCard);
                    a2.dismiss();
                }
            });
        }
        return a2;
    }
}
