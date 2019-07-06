package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.cart.CartThankYouGroup;
import com.etsy.android.ui.cart.MultiShopCartGroupAdapter;
import com.etsy.android.ui.cart.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class CartThankYouGroupViewHolder extends BaseViewHolder<CartThankYouGroup> {
    private final MultiShopCartGroupAdapter mAdapter;
    private final View mBtnDivider;
    private final ImageView mBtnIcon;
    private final ViewGroup mBtnItemsToggleDisplay;
    private final TextView mBtnText;
    private final ImageView mImage;
    private final c mImageBatch;
    private final RecyclerView mRecyclerView = ((RecyclerView) findViewById(R.id.items));
    private final TextView mSubtitle;
    private final TextView mTitle;

    public CartThankYouGroupViewHolder(ViewGroup viewGroup, e eVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_cart_thank_you_group, viewGroup, false));
        this.mImageBatch = eVar.d();
        Context context = viewGroup.getContext();
        this.mAdapter = new MultiShopCartGroupAdapter(eVar);
        this.mRecyclerView.setRecycledViewPool(eVar.e());
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.mRecyclerView.setAdapter(this.mAdapter);
        Resources resources = this.itemView.getResources();
        int dimension = (int) resources.getDimension(R.dimen.padding_large);
        RecyclerView recyclerView = this.mRecyclerView;
        InsetDrawable insetDrawable = new InsetDrawable(resources.getDrawable(R.drawable.list_divider_dark), dimension, 0, dimension, 0);
        recyclerView.addItemDecoration(new ItemDividerDecoration((Drawable) insetDrawable));
        this.mTitle = (TextView) findViewById(R.id.title);
        this.mSubtitle = (TextView) findViewById(R.id.subtitle);
        this.mImage = (ImageView) findViewById(R.id.image);
        this.mBtnItemsToggleDisplay = (ViewGroup) findViewById(R.id.btn_toggle_items_display);
        this.mBtnText = (TextView) this.mBtnItemsToggleDisplay.findViewById(R.id.btn_text);
        this.mBtnIcon = (ImageView) this.mBtnItemsToggleDisplay.findViewById(R.id.btn_icon);
        this.mBtnDivider = findViewById(R.id.btn_divider);
    }

    public void bind(final CartThankYouGroup cartThankYouGroup) {
        this.mTitle.setText(cartThankYouGroup.getTitle());
        this.mSubtitle.setText(cartThankYouGroup.getSubtitle());
        this.mAdapter.clearData();
        setItemsVisible(cartThankYouGroup);
        this.mImageBatch.a(cartThankYouGroup.getImageUrl(), this.mImage);
        this.mBtnItemsToggleDisplay.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                cartThankYouGroup.setItemsVisible(!cartThankYouGroup.isItemsVisible());
                CartThankYouGroupViewHolder.this.setItemsVisible(cartThankYouGroup);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setItemsVisible(CartThankYouGroup cartThankYouGroup) {
        boolean isItemsVisible = cartThankYouGroup.isItemsVisible();
        this.mBtnText.setText(isItemsVisible ? R.string.hide_order_details : R.string.view_order_details);
        this.mBtnIcon.setRotation(isItemsVisible ? 0.0f : 180.0f);
        this.mBtnDivider.setVisibility(isItemsVisible ? 0 : 8);
        if (isItemsVisible) {
            ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).setInitialPrefetchItemCount(cartThankYouGroup.getItems().size());
            this.mAdapter.addCartGroupItems(cartThankYouGroup.getItems());
            return;
        }
        ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).setInitialPrefetchItemCount(0);
        this.mAdapter.clearData();
    }
}
