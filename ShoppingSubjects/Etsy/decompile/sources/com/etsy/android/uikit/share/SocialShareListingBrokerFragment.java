package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.uikit.view.ListingFullImageView;

public class SocialShareListingBrokerFragment extends SocialShareBrokerFragment {
    private ListingCollection mCollection;
    private BasicListingLike mListing;

    /* access modifiers changed from: protected */
    public void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mListing = (BasicListingLike) bundle.getSerializable("listing");
        this.mCollection = (ListingCollection) bundle.getSerializable(Collection.TYPE_COLLECTION);
        if (this.mListing == null) {
            f.a((RuntimeException) new IllegalArgumentException("No listing specified"));
            return;
        }
        if (this.mCollection != null) {
            this.mUrl = this.mCollection.getUrl();
            this.mText = getString(o.social_share_collection_message, this.mCollection.getUrl());
            this.mSubject = getString(o.share_collection_subject);
        } else {
            this.mUrl = this.mListing.getUrl();
            this.mText = getString(o.social_share_listing_message, this.mListing.getUrl());
            this.mSubject = getString(o.share_listing_subject);
        }
        this.mImageUrl = this.mListing.getListingImage().get4to3ImageUrlForPixelWidth(0);
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(k.fragment_social_share_listing_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        showListing(view);
        TextView textView = (TextView) view.findViewById(i.social_share_title);
        if (this.mCollection != null) {
            textView.setText(o.share_prompt_collection_message);
        } else {
            textView.setText(o.share_prompt_listing_message);
        }
    }

    public void showListing(View view) {
        if (view != null) {
            TextView textView = (TextView) view.findViewById(i.listing_title);
            ListingFullImageView listingFullImageView = (ListingFullImageView) view.findViewById(i.listing_image);
            listingFullImageView.setUseStandardRatio(true);
            if (this.mListing == null) {
                textView.setVisibility(8);
                listingFullImageView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            listingFullImageView.setVisibility(0);
            textView.setText(this.mListing.getTitle());
            if (this.mImageUrl != null) {
                listingFullImageView.setImageInfo(this.mListing.getListingImage(), getImageBatch());
            } else {
                listingFullImageView.setVisibility(8);
            }
        }
    }

    public void onIntentItemClick(int i) {
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        if (this.mCollection == null) {
            if (isTwitter(resolveInfo)) {
                this.mText = getString(o.share_listing_twitter, this.mUrl);
            } else if (isGmail(resolveInfo)) {
                this.mSubject = getString(o.share_listing_email_subject);
                this.mText = getString(o.share_listing_email_message, this.mUrl);
            } else if (isPinterest(resolveInfo) || isGooglePlus(resolveInfo)) {
                this.mText = getString(o.share_listing_pinterest);
            } else if (isHangouts(resolveInfo)) {
                this.mText = getString(o.share_listing_hangouts, this.mUrl);
            }
        } else if (isTwitter(resolveInfo)) {
            this.mText = getString(o.share_collection_twitter, this.mUrl);
        } else if (isGmail(resolveInfo)) {
            this.mSubject = getString(o.share_collection_email_subject);
            this.mText = getString(o.share_collection_email_message, this.mUrl);
        } else if (isPinterest(resolveInfo) || isGooglePlus(resolveInfo)) {
            this.mText = getString(o.share_collection_pinterest);
        }
        super.onIntentItemClick(i);
    }
}
