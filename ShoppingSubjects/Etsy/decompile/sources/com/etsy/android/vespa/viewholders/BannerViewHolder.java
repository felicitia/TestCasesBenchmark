package com.etsy.android.vespa.viewholders;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.img.d;
import com.etsy.android.lib.models.apiv3.Banner;
import com.etsy.android.lib.models.apiv3.BannerButton;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.vespa.CardActionableItem;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.BannerImageView;
import com.etsy.android.vespa.a.a;

public abstract class BannerViewHolder extends BaseViewHolder<CardActionableItem> {
    protected View mBgColorArea;
    private final ImageView mButtonDismiss;
    private final Button mButtonPrimary;
    private final Button mButtonSecondary;
    /* access modifiers changed from: private */
    public a mClickListener;
    protected final BannerImageView mImage;
    private final c mImageBatch;
    /* access modifiers changed from: private */
    public boolean mIsImageDownloaded = false;
    private final TextView mMessage;
    private final TextView mTitle;

    /* access modifiers changed from: protected */
    public abstract void animate(String str);

    public BannerViewHolder(ViewGroup viewGroup, a aVar, c cVar, @LayoutRes int i) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        this.mClickListener = aVar;
        this.mImageBatch = cVar;
        this.mBgColorArea = findViewById(i.bg_color_area);
        this.mTitle = (TextView) findViewById(i.txt_title);
        this.mMessage = (TextView) findViewById(i.txt_message);
        this.mButtonPrimary = (Button) findViewById(i.btn_primary);
        this.mButtonSecondary = (Button) findViewById(i.btn_secondary);
        this.mButtonDismiss = (ImageView) findViewById(i.btn_dismiss);
        this.mImage = (BannerImageView) findViewById(i.image);
    }

    public void bind(CardActionableItem cardActionableItem) {
        super.bind(cardActionableItem);
        Banner banner = (Banner) cardActionableItem.getData();
        this.mBgColorArea.setBackgroundColor(banner.getBackgroundColor());
        this.mTitle.setText(banner.getTitle());
        this.mTitle.setTextColor(banner.getTextColor());
        String message = banner.getMessage();
        if (!TextUtils.isEmpty(message)) {
            this.mMessage.setText(message);
            this.mMessage.setVisibility(0);
            this.mMessage.setTextColor(banner.getTextColor());
        } else {
            this.mMessage.setVisibility(8);
        }
        Image image = banner.getImage();
        if (image != null) {
            bindImage(banner, image);
            if (!"none".equals(banner.getAnimation())) {
                this.mImage.setVisibility(4);
                setupAnimationScrollListeners(banner);
            } else {
                this.mImage.setVisibility(0);
            }
        } else {
            this.mImage.setVisibility(8);
        }
        bindButton(this.mButtonPrimary, banner.getButtonPrimary());
        bindButton(this.mButtonSecondary, banner.getButtonSecondary());
        final ServerDrivenAction action = cardActionableItem.getAction(ServerDrivenAction.TYPE_DISMISS);
        if (action == null || this.mClickListener == null) {
            this.mButtonDismiss.setVisibility(8);
            return;
        }
        this.mButtonDismiss.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                BannerViewHolder.this.mClickListener.c(BannerViewHolder.this.getRootView(), action);
            }
        });
        DrawableCompat.setTint(this.mButtonDismiss.getDrawable(), banner.getDismissButtonColor());
    }

    private void bindButton(Button button, final BannerButton bannerButton) {
        if (this.mClickListener == null || bannerButton == null || TextUtils.isEmpty(bannerButton.getUrl())) {
            button.setVisibility(8);
            return;
        }
        button.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                BannerViewHolder.this.mClickListener.a(bannerButton.getUrl(), bannerButton.getDismissBannerOnTap().booleanValue(), Integer.valueOf(BannerViewHolder.this.getAdapterPosition()));
            }
        });
        button.setVisibility(0);
        button.setText(bannerButton.getText());
    }

    public void recycle() {
        this.mImage.setImageDrawable(null);
    }

    /* access modifiers changed from: private */
    public void onAnimate(Banner banner) {
        this.mImage.setVisibility(0);
        animate(banner.getAnimation());
        banner.setAnimation("none");
    }

    private void bindImage(final Banner banner, Image image) {
        this.mIsImageDownloaded = false;
        this.mImage.setImageInfo(image, this.mImageBatch, new d(this.mImage) {
            public void a(Bitmap bitmap, boolean z) {
                super.a(bitmap, z);
                BannerViewHolder.this.mIsImageDownloaded = true;
                if (!"none".equals(banner.getAnimation())) {
                    BannerViewHolder.this.mImage.setVisibility(4);
                    RecyclerView access$200 = BannerViewHolder.this.getParentRecyclerView();
                    if (access$200 != null && BannerViewHolder.this.isItemFullyOnScreen(access$200)) {
                        BannerViewHolder.this.onAnimate(banner);
                    }
                }
            }
        });
    }

    private void setupAnimationScrollListeners(final Banner banner) {
        final AnonymousClass4 r0 = new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (BannerViewHolder.this.isItemFullyOnScreen(recyclerView) && BannerViewHolder.this.mIsImageDownloaded) {
                    BannerViewHolder.this.onAnimate(banner);
                    recyclerView.removeOnScrollListener(this);
                }
            }
        };
        this.itemView.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                RecyclerView access$200 = BannerViewHolder.this.getParentRecyclerView();
                if (access$200 == null) {
                    return;
                }
                if (!BannerViewHolder.this.isItemFullyOnScreen(access$200) || !BannerViewHolder.this.mIsImageDownloaded) {
                    access$200.addOnScrollListener(r0);
                } else {
                    BannerViewHolder.this.onAnimate(banner);
                }
            }

            public void onViewDetachedFromWindow(View view) {
                RecyclerView access$200 = BannerViewHolder.this.getParentRecyclerView();
                if (access$200 != null) {
                    access$200.removeOnScrollListener(r0);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    @Nullable
    public RecyclerView getParentRecyclerView() {
        if (getRootView().getParent() instanceof RecyclerView) {
            return (RecyclerView) getRootView().getParent();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean isItemFullyOnScreen(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        int findFirstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        int findLastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition();
        boolean z = false;
        if (findFirstCompletelyVisibleItemPosition == -1 || findLastCompletelyVisibleItemPosition == -1) {
            return false;
        }
        if (findFirstCompletelyVisibleItemPosition <= getAdapterPosition() && findLastCompletelyVisibleItemPosition >= getAdapterPosition()) {
            z = true;
        }
        return z;
    }
}
