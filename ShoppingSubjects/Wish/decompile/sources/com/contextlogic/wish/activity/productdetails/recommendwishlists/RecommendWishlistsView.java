package com.contextlogic.wish.activity.productdetails.recommendwishlists;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsServiceFragment;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.ArrayList;

public class RecommendWishlistsView extends LinearLayout implements ImageRestorable, Recyclable {
    protected ProductDetailsFragment mFragment;
    private boolean mLoaded;
    protected RecommendedWishlistAdapter mRecommendedWishlistAdapter;
    private HorizontalListView mRecommendedWishlistsList;
    private ThemedTextView mWishlistTitle;

    public RecommendWishlistsView(Context context) {
        this(context, null);
    }

    public RecommendWishlistsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.product_details_fragment_wishlist_recommend_header, this, true);
        this.mRecommendedWishlistsList = (HorizontalListView) findViewById(R.id.fragment_recommended_wishlists_list);
        this.mWishlistTitle = (ThemedTextView) findViewById(R.id.fragment_wishlist_title);
        this.mLoaded = false;
        setLayoutParams(new LayoutParams(-1, -2));
        setPadding(0, 0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.sixteen_padding));
        setOrientation(1);
        setVisibility(8);
    }

    public void setup(DrawerActivity drawerActivity, ProductDetailsFragment productDetailsFragment) {
        this.mRecommendedWishlistAdapter = new RecommendedWishlistAdapter(drawerActivity, new ArrayList());
        this.mRecommendedWishlistsList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                RecommendWishlistsView.this.mRecommendedWishlistAdapter.onWishlistClicked(i);
            }
        });
        this.mFragment = productDetailsFragment;
    }

    public void loadRecommendedWishlists() {
        if (this.mFragment != null && !this.mLoaded) {
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                    String productId = RecommendWishlistsView.this.mFragment.getLoadedProduct() == null ? null : RecommendWishlistsView.this.mFragment.getLoadedProduct().getProductId();
                    if (TextUtils.isEmpty(productId)) {
                        RecommendWishlistsView.this.onFailure();
                    } else {
                        productDetailsServiceFragment.loadRecommendedWishlists(productId);
                    }
                }
            });
        }
    }

    public void handleRecommendedWishlistsLoaded(ArrayList<WishWishlist> arrayList, ArrayList<WishUser> arrayList2, String str) {
        this.mLoaded = true;
        if (arrayList.size() <= 0 || this.mRecommendedWishlistAdapter == null || this.mRecommendedWishlistsList == null) {
            onFailure();
            return;
        }
        this.mRecommendedWishlistAdapter.setRecommendedWishlists(arrayList, arrayList2);
        this.mRecommendedWishlistsList.setAdapter(this.mRecommendedWishlistAdapter);
        setVisibility(0);
        this.mWishlistTitle.setText(str);
    }

    public void onFailure() {
        this.mLoaded = true;
        setVisibility(8);
    }

    public void releaseImages() {
        if (this.mRecommendedWishlistsList != null) {
            this.mRecommendedWishlistsList.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mRecommendedWishlistsList != null) {
            this.mRecommendedWishlistsList.restoreImages();
        }
    }

    public void recycle() {
        releaseImages();
    }

    public boolean isLoaded() {
        return this.mLoaded;
    }
}
