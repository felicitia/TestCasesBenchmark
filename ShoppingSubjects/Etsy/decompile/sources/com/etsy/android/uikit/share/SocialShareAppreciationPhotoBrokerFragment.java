package com.etsy.android.uikit.share;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class SocialShareAppreciationPhotoBrokerFragment extends SocialShareBrokerFragment {
    private static final int MAX_TITLE_LENGTH = 35;
    private AppreciationPhotoLike mAppreciationPhoto;

    /* access modifiers changed from: protected */
    public void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mAppreciationPhoto = (AppreciationPhotoLike) bundle.getSerializable(ResponseConstants.APPRECIATION_PHOTO);
        if (this.mAppreciationPhoto == null) {
            f.a((RuntimeException) new IllegalArgumentException("No appreciation photo specified"));
            return;
        }
        String listingTitle = this.mAppreciationPhoto.getListingTitle();
        if (listingTitle == null) {
            listingTitle = "";
        } else if (listingTitle.length() > 35) {
            StringBuilder sb = new StringBuilder();
            sb.append(listingTitle.substring(0, 32));
            sb.append("...");
            listingTitle = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(String.format(getString(o.review_share_message), new Object[]{this.mAppreciationPhoto.getShopName()}));
        sb2.append(listingTitle);
        this.mSubject = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(String.format(getString(o.review_share_body), new Object[]{this.mAppreciationPhoto.getShopName()}));
        sb3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb3.append(this.mAppreciationPhoto.getShortenedShareUrl().getShortUrl());
        this.mText = sb3.toString();
        if (this.mAppreciationPhoto.getShortenedShareUrl().isShortened()) {
            this.mShareUrl = this.mAppreciationPhoto.getShortenedShareUrl();
        }
        this.mUrl = this.mAppreciationPhoto.getShortenedShareUrl().getShortUrl();
        this.mImageUrl = this.mAppreciationPhoto.getShareImageUrl();
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(k.fragment_social_share_appreciation_photo_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ImageView imageView = (ImageView) view.findViewById(i.appreciation_photo);
        if (imageView != null) {
            getImageBatch().a(this.mAppreciationPhoto.getShareImageUrl(), imageView);
        }
    }
}
