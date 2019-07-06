package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class OrderConfirmedView extends LinearLayout {
    private NetworkImageView mImage;
    private ThemedTextView mLargeImageButton;
    private LinearLayout mLargeImageContainer;
    private int mLargeImageHeight;
    private HorizontalListView mListView;
    private ThemedTextView mScrollerButton;
    private LinearLayout mScrollerContainer;
    private int mSquareImageHeight;
    private FrameLayout mSquareViewContainer;
    private ThemedTextView mTitle;

    public static abstract class AdjustableSquareView extends FrameLayout {
        protected View mView;

        public abstract void adjust(int i);

        public abstract int getLayoutId();

        public abstract void setup();

        public AdjustableSquareView(Context context) {
            super(context);
            init();
        }

        private void init() {
            this.mView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(getLayoutId(), this);
            setup();
        }
    }

    public static abstract class Builder {
        protected AdjustableSquareView mAdjustableSquareView;
        protected int mBackgroundDrawableId;
        protected String mButtonText;
        protected Context mContext;
        protected boolean mIsLastInList;
        protected OnClickListener mOnClickListener;
        protected String mTitle;
        protected WishImage mWishImage;

        public String getTitle() {
            return this.mTitle;
        }

        public WishImage getWishImage() {
            return this.mWishImage;
        }

        public String getButtonText() {
            return this.mButtonText;
        }

        public int getBackgroundDrawableId() {
            return this.mBackgroundDrawableId;
        }

        public AdjustableSquareView getAdjustableSquareView() {
            return this.mAdjustableSquareView;
        }

        public OnClickListener getOnClickListener() {
            return this.mOnClickListener;
        }

        public boolean getIsLastInList() {
            return this.mIsLastInList;
        }
    }

    public enum DataMode {
        ALSO_BOUGHT,
        DEAL_DASH,
        WISHLIST,
        PRICE_WATCH
    }

    protected OrderConfirmedView(Context context) {
        this(context, null);
    }

    private OrderConfirmedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.order_confirmed_view, this);
        inflate.setLayoutParams(new LayoutParams(-1, -2));
        setOrientation(1);
        setPadding(0, getResources().getDimensionPixelOffset(R.dimen.sixteen_padding), 0, getResources().getDimensionPixelOffset(R.dimen.sixteen_padding));
        Resources resources = getResources();
        if (resources != null) {
            this.mLargeImageHeight = resources.getDimensionPixelOffset(R.dimen.order_confirmed_dialog_large_image_height);
            this.mSquareImageHeight = resources.getDimensionPixelOffset(R.dimen.order_confirmed_dialog_square_image_height);
        }
        this.mImage = (NetworkImageView) inflate.findViewById(R.id.order_confirmed_view_image);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.order_confirmed_view_title);
        this.mScrollerContainer = (LinearLayout) inflate.findViewById(R.id.order_confirmed_view_scroller_container);
        this.mListView = (HorizontalListView) inflate.findViewById(R.id.order_confirmed_view_scroller_list_view);
        this.mScrollerButton = (ThemedTextView) inflate.findViewById(R.id.order_confirmed_view_scroller_button);
        this.mLargeImageContainer = (LinearLayout) inflate.findViewById(R.id.order_confirmed_view_large_image_container);
        this.mLargeImageButton = (ThemedTextView) inflate.findViewById(R.id.order_confirmed_view_large_image_container_button);
        this.mSquareViewContainer = (FrameLayout) inflate.findViewById(R.id.order_confirmed_view_square_view_container);
        ViewGroup.LayoutParams layoutParams = this.mLargeImageContainer.getLayoutParams();
        layoutParams.height = this.mLargeImageHeight;
        this.mLargeImageContainer.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void initialize(Builder builder) {
        this.mTitle.setText(builder.getTitle());
        WishImage wishImage = builder.getWishImage();
        if (wishImage != null) {
            this.mImage.setImage(wishImage);
        } else {
            this.mImage.setVisibility(8);
        }
        if (builder.getButtonText() == null) {
            this.mScrollerButton.setVisibility(8);
            this.mLargeImageButton.setVisibility(8);
        }
        if (builder.getIsLastInList()) {
            setPadding(0, 0, 0, 0);
        }
        if (builder.getAdjustableSquareView() == null) {
            this.mScrollerButton.setText(builder.getButtonText());
            this.mScrollerButton.setOnClickListener(builder.getOnClickListener());
            this.mLargeImageContainer.setVisibility(8);
            return;
        }
        this.mLargeImageButton.setText(builder.getButtonText());
        this.mLargeImageButton.setOnClickListener(builder.getOnClickListener());
        this.mLargeImageContainer.setBackgroundResource(builder.getBackgroundDrawableId());
        AdjustableSquareView adjustableSquareView = builder.getAdjustableSquareView();
        this.mSquareViewContainer.addView(adjustableSquareView);
        LayoutParams layoutParams = (LayoutParams) adjustableSquareView.getLayoutParams();
        layoutParams.gravity = 17;
        adjustableSquareView.setLayoutParams(layoutParams);
        adjustableSquareView.adjust(this.mSquareImageHeight);
        this.mScrollerContainer.setVisibility(8);
    }

    public HorizontalListView getListView() {
        return this.mListView;
    }

    public void setAdapter(ProductHorizontalAdapter productHorizontalAdapter) {
        this.mListView.setAdapter(productHorizontalAdapter);
    }
}
