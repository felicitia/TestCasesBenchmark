package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.ShopHeader;
import com.etsy.android.ui.cardview.clickhandlers.m;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ShopCartHeaderViewHolder extends BaseCartGroupItemViewHolder {
    private final TextView mBtnShopPolicies = ((TextView) findViewById(R.id.btn_shop_policies));
    /* access modifiers changed from: private */
    public final m mClickListener;
    private final c mImageBatch;
    private final ImageView mImageShopAvatar = ((ImageView) findViewById(R.id.image_shop_avatar));
    private final View mTextContactShop = findViewById(R.id.txt_contact_shop);
    private final TextView mTextShopName = ((TextView) findViewById(R.id.txt_shop_name));

    public ShopCartHeaderViewHolder(ViewGroup viewGroup, c cVar, m mVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_shop_cart_header, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickListener = mVar;
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        final ShopHeader shopHeader = (ShopHeader) cartGroupItem.getData();
        this.mTextShopName.setText(shopHeader.getShopName());
        this.mImageShopAvatar.setImageBitmap(null);
        Image icon = shopHeader.getIcon();
        if (icon != null) {
            this.mImageBatch.a(icon, this.mImageShopAvatar);
        } else if (!TextUtils.isEmpty(shopHeader.getAvatarUrl())) {
            this.mImageBatch.a(shopHeader.getAvatarUrl(), this.mImageShopAvatar);
        } else {
            this.mImageShopAvatar.setImageBitmap(null);
        }
        AnonymousClass1 r0 = new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (ShopCartHeaderViewHolder.this.mClickListener != null && !shopHeader.isPrivate()) {
                    ShopCartHeaderViewHolder.this.mClickListener.a(shopHeader, shopHeader.getContentSource());
                }
            }
        };
        this.mTextShopName.setOnClickListener(r0);
        this.mImageShopAvatar.setOnClickListener(r0);
        this.itemView.setOnClickListener(r0);
        if (!shopHeader.isPrivate()) {
            this.mTextContactShop.setVisibility(0);
            this.mTextContactShop.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (ShopCartHeaderViewHolder.this.mClickListener != null) {
                        ShopCartHeaderViewHolder.this.mClickListener.a(shopHeader.getLoginName());
                    }
                }
            });
        } else {
            this.mTextContactShop.setVisibility(8);
        }
        if (shopHeader.isTrustSignalsOn()) {
            this.mBtnShopPolicies.setVisibility(0);
            this.mBtnShopPolicies.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (ShopCartHeaderViewHolder.this.mClickListener != null) {
                        ShopCartHeaderViewHolder.this.mClickListener.a(shopHeader.getShopId());
                    }
                }
            });
            return;
        }
        this.mBtnShopPolicies.setVisibility(8);
    }
}
