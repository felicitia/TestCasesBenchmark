package com.etsy.android.ui.local.marketdetails;

import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalStoreImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.c.a;
import com.etsy.android.ui.local.g;
import com.etsy.android.uikit.share.SocialShareBrokerFragment;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import org.parceler.d;

public class SocialShareLocalMarketBrokerFragment extends SocialShareBrokerFragment {
    private LocalMarket mLocalMarket;

    /* access modifiers changed from: protected */
    public void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mLocalMarket = (LocalMarket) d.a(bundle.getParcelable(ResponseConstants.LOCAL_MARKET));
        this.mUrl = "";
        this.mImageUrl = "";
        if (this.mLocalMarket != null) {
            this.mUrl = this.mLocalMarket.getDetailsUrl();
            if (!this.mLocalMarket.isWholesaleStore() && this.mLocalMarket.getAttendeeListingImages().size() > 0) {
                this.mImageUrl = ((ListingImage) this.mLocalMarket.getAttendeeListingImages().get(0)).getImageUrl();
            } else if (this.mLocalMarket.getStore() != null && this.mLocalMarket.getStore().getImages().size() > 0) {
                this.mImageUrl = ((LocalStoreImage) this.mLocalMarket.getStore().getImages().get(0)).getImageUrl();
            }
        }
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.list_item_local_market_share_card, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        setupMarketCard(view);
        setupShareSheet(view);
    }

    private void setupMarketCard(View view) {
        TextView textView = (TextView) view.findViewById(R.id.market_date);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.image_layout_1);
        FrameLayout frameLayout2 = (FrameLayout) view.findViewById(R.id.image_layout_2);
        TextView textView2 = (TextView) view.findViewById(R.id.market_type);
        TextView textView3 = (TextView) view.findViewById(R.id.market_title);
        Resources resources = view.getResources();
        view.getContext();
        boolean isWholesaleStore = this.mLocalMarket.isWholesaleStore();
        int integer = resources.getInteger(R.integer.local_browse_images_per_row);
        if (!isWholesaleStore) {
            textView.setText(a.b(this.mLocalMarket));
        } else {
            textView.setVisibility(8);
        }
        ArrayList arrayList = new ArrayList();
        if (!isWholesaleStore) {
            arrayList.addAll(this.mLocalMarket.getAttendeeListingImages());
        } else {
            arrayList.addAll(this.mLocalMarket.getStore().getImages());
        }
        g.a(frameLayout, frameLayout2, getImageBatch(), arrayList, isWholesaleStore, integer);
        textView2.setText(this.mLocalMarket.getMarketTypeLabelString(resources));
        textView3.setText(this.mLocalMarket.getTitle());
    }

    private void setupShareSheet(View view) {
        TextView textView = (TextView) view.findViewById(R.id.social_share_title);
        TextView textView2 = (TextView) view.findViewById(R.id.social_share_message);
        textView.setText(R.string.local_share_prompt_title);
        textView2.setText(R.string.local_share_prompt_message);
        textView2.setVisibility(0);
    }

    public void onIntentItemClick(int i) {
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        if (this.mLocalMarket != null) {
            if (isFacebook(resolveInfo)) {
                this.mText = null;
                this.mSubject = null;
                this.mImageUrl = null;
            } else if (this.mLocalMarket.isWholesaleStore()) {
                this.mText = getString(R.string.local_share_store_text, this.mLocalMarket.getCity(), this.mLocalMarket.getTitle(), this.mUrl);
                this.mSubject = getString(R.string.local_share_store_email_subject);
                if (isGoogleEmail(resolveInfo)) {
                    this.mText = getString(R.string.local_share_store_email_text, this.mLocalMarket.getTitle(), this.mLocalMarket.getCity(), this.mUrl);
                }
            } else {
                if (this.mLocalMarket.hasCity()) {
                    this.mText = getString(R.string.local_share_event_text, this.mLocalMarket.getTitle(), this.mLocalMarket.getCity(), this.mUrl);
                } else {
                    this.mText = getString(R.string.local_share_event_text_no_city, this.mLocalMarket.getTitle(), this.mUrl);
                }
                this.mSubject = getString(R.string.local_share_event_email_subject);
            }
            if (isTwitter(resolveInfo)) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.mText);
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(getString(R.string.local_irl_hashtag));
                this.mText = sb.toString();
            }
        }
        super.onIntentItemClick(i);
    }
}
