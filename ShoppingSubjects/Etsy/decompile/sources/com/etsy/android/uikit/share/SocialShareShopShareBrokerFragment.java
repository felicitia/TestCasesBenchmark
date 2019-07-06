package com.etsy.android.uikit.share;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.shopshare.ShareItem;
import com.etsy.android.lib.util.af;
import com.etsy.android.uikit.adapter.AnnotationAdapter;
import com.etsy.android.uikit.view.TaggableImageView;
import java.util.Locale;

public class SocialShareShopShareBrokerFragment extends SocialShareBrokerFragment {
    public static final int TWITTER_CHARACTER_LIMIT = 117;
    /* access modifiers changed from: private */
    public ShareItem mShareItem;
    private String mShopName;

    @NonNull
    public String getTrackingName() {
        return "shop_shares_share";
    }

    /* access modifiers changed from: protected */
    public void onShareComplete() {
    }

    /* access modifiers changed from: protected */
    public void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mShareItem = (ShareItem) bundle.getSerializable("shop_share");
        this.mShopName = (String) bundle.getSerializable(ResponseConstants.SHOP);
        this.mText = this.mShareItem.getText();
        this.mSubject = getString(o.post_subject, this.mShopName);
        this.mUrl = this.mShareItem.getUrl();
        this.mImageUrl = this.mShareItem.getPrimaryMedia().getImage().getUrl();
        this.mType = "image/*";
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(k.fragment_social_share_shop_share_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        final TaggableImageView taggableImageView = (TaggableImageView) view.findViewById(i.save_share_image);
        new c().a(this.mShareItem.getPrimaryMedia().getImage(), (ImageView) taggableImageView.getImageView());
        taggableImageView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                taggableImageView.setAdapter(new AnnotationAdapter((Context) SocialShareShopShareBrokerFragment.this.getActivity(), (ImageView) taggableImageView.getImageView(), SocialShareShopShareBrokerFragment.this.mShareItem));
                taggableImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        ((TextView) view.findViewById(i.social_share_title)).setText(o.share_post);
        TextView textView = (TextView) view.findViewById(i.social_share_message);
        textView.setText(o.share_post_message);
        textView.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public d onBeforeShare(ResolveInfo resolveInfo, d dVar) {
        if (isFacebook(resolveInfo)) {
            dVar.e("text/plain").a((String) null).b(null).d(null);
        } else if (isTwitter(resolveInfo)) {
            String a = af.a(dVar.b(), 117 - dVar.c().length());
            dVar.e("text/plain").b(String.format("%s %s", new Object[]{a, dVar.c()}));
        } else if (isEmailLike(resolveInfo)) {
            dVar.e("text/plain").b(getString(o.post_email_body, dVar.c()));
        } else if (isTumblr(resolveInfo)) {
            dVar.e("text/plain");
            if (dVar.b().toLowerCase(Locale.ROOT).contains("http")) {
                dVar.b(String.format("%s %s", new Object[]{dVar.c(), dVar.b()}));
            } else {
                dVar.b(String.format("%s%s", new Object[]{dVar.b(), dVar.c()}));
            }
        } else if (isWhatsApp(resolveInfo)) {
            dVar.e("text/plain").b(String.format("%s: %s", new Object[]{dVar.a(), dVar.c()}));
        } else if (isSms(resolveInfo)) {
            dVar.e("text/plain").b(String.format("%s: %s", new Object[]{dVar.a(), dVar.c()})).a((String) null);
        }
        return dVar;
    }
}
