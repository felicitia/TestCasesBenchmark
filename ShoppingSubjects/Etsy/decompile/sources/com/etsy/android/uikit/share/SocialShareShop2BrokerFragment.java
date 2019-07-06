package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.util.af;
import org.parceler.d;

public class SocialShareShop2BrokerFragment extends SocialShareBrokerFragment {
    private int mIconContainerSize;
    private int mIconCornerRadius;
    private ShopShareable mShop;

    /* access modifiers changed from: protected */
    public void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mShop = (ShopShareable) d.a(bundle.getParcelable("shop2"));
        if (this.mShop == null) {
            f.a((RuntimeException) new IllegalArgumentException("No shop specified"));
            return;
        }
        this.mUrl = this.mShop.getShareUrl();
        this.mText = getString(o.social_share_shop_message, this.mUrl);
        Resources resources = getResources();
        this.mIconContainerSize = resources.getDimensionPixelSize(a.f.social_share_shop_icon_size);
        this.mIconCornerRadius = resources.getDimensionPixelSize(a.f.gen_avatar_corners_large);
        this.mImageUrl = this.mShop.getShareImageUrl(this.mIconContainerSize);
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(k.fragment_social_share_shop2_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((TextView) view.findViewById(i.social_share_title)).setText(o.share_prompt_shop_message);
        ((TextView) view.findViewById(i.shop_name)).setText(this.mShop.getShopName());
        TextView textView = (TextView) view.findViewById(i.headline);
        String shopHeadline = this.mShop.getShopHeadline();
        if (af.b(shopHeadline)) {
            textView.setVisibility(0);
            textView.setText(shopHeadline);
        } else {
            textView.setVisibility(8);
        }
        ImageView imageView = (ImageView) view.findViewById(i.shop_icon);
        if (imageView != null) {
            getImageBatch().b(this.mImageUrl, imageView, this.mIconCornerRadius, this.mIconContainerSize, this.mIconContainerSize);
        }
    }

    public void onIntentItemClick(int i) {
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        if (isTwitter(resolveInfo)) {
            this.mText = getString(o.share_shop_twitter, this.mShop.getShopName(), this.mUrl);
        } else if (isGmail(resolveInfo)) {
            this.mSubject = getString(o.share_shop_email_subject);
            this.mText = getString(o.share_shop_email_message, this.mUrl);
        } else if (isPinterest(resolveInfo) || isGooglePlus(resolveInfo)) {
            this.mText = getString(o.share_shop_pinterest, this.mShop.getShopName());
        }
        super.onIntentItemClick(i);
    }
}
