package com.etsy.android.ui.favorites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.R;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.CheckableListingCollection;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.apiv3.ListingCollectionsRequest;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.ui.adapters.ListingCollectionsAdapter;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.util.CollectionUtil;
import com.etsy.android.ui.util.CollectionUtil.ListingCollectionsBooleanState;
import com.etsy.android.ui.util.CollectionUtil.f;
import com.etsy.android.ui.util.CollectionUtil.g;
import com.etsy.android.ui.util.CollectionUtil.h;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListingCollectionsFragment extends EtsyCommonListFragment implements f, g, h {
    private static final String TAG = "com.etsy.android.ui.favorites.ListingCollectionsFragment";
    /* access modifiers changed from: private */
    public ListingCollectionsAdapter mAdapter;
    private CheckableListingCollection mAddedCollection;
    private OnClickListener mClickListener = new TrackingOnClickListener() {
        public void onPreTrack() {
            addEventTrackedAttribute(AnalyticsLogAttribute.LISTING_ID, ListingCollectionsFragment.this.mListingId);
        }

        public void onViewClick(View view) {
            Map collectionsChanged = ListingCollectionsFragment.this.mAdapter.getCollectionsChanged();
            if (collectionsChanged == null || collectionsChanged.isEmpty()) {
                ListingCollectionsFragment.this.mParentDialog.dismiss();
            } else {
                ListingCollectionsFragment.this.mCollectionUtil.a((Activity) ListingCollectionsFragment.this.mActivity, ListingCollectionsFragment.this.mListingId, ListingCollectionsFragment.this.mAdapter.getInCollectionsStateChanged(), collectionsChanged, (f) ListingCollectionsFragment.this);
            }
        }
    };
    /* access modifiers changed from: private */
    public CollectionUtil mCollectionUtil;
    private ListingLike mListing;
    /* access modifiers changed from: private */
    public EtsyId mListingId;
    private String mListingImageUrl;
    /* access modifiers changed from: private */
    public IDialogFragment mParentDialog;
    private boolean mShouldShowSocialInvitesPrompt;

    private class a extends i<CheckableListingCollection> {
        private a() {
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<CheckableListingCollection> a() {
            return ListingCollectionsRequest.getListingCollections(ListingCollectionsFragment.this.mListingId);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<CheckableListingCollection> kVar) {
            if (kVar == null || !kVar.a() || kVar.g() == null) {
                ListingCollectionsFragment.this.showErrorView();
                return;
            }
            List g = kVar.g();
            Iterator it = g.iterator();
            while (it.hasNext()) {
                if (Collection.TYPE_FAVORITES.equals(((Collection) it.next()).getType())) {
                    it.remove();
                }
            }
            ListingCollectionsFragment.this.mAdapter.addAll((java.util.Collection<? extends T>) g);
            ListingCollectionsFragment.this.mAdapter.notifyDataSetChanged();
            ListingCollectionsFragment.this.showListView();
        }
    }

    @NonNull
    public String getTrackingName() {
        return "list_open";
    }

    public void onListingCollectionError(String str) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.etsy.android.lib.logger.f.c(TAG, "onCreate");
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mListing = (ListingLike) arguments.getSerializable("listing");
            if (this.mListing != null) {
                this.mListingId = this.mListing.getListingId();
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.listingcollection_thumbnail);
                BaseModelImage listingImage = this.mListing.getListingImage();
                if (listingImage != null && listingImage.getImageUrlForPixelWidth(dimensionPixelSize) != null) {
                    this.mListingImageUrl = listingImage.getImageUrlForPixelWidth(dimensionPixelSize);
                    return;
                }
                return;
            }
            this.mListingId = (EtsyId) arguments.getSerializable("listing_id");
            this.mListingImageUrl = arguments.getString(ResponseConstants.LISTING_IMAGE_URL);
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        com.etsy.android.lib.logger.f.c(TAG, "onHiddenCHanged");
        if (!z) {
            this.mParentDialog.setTitle(this.mActivity.getString(R.string.add_listing_to_collection_title));
            this.mParentDialog.setOkButtonVisibility(0);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        com.etsy.android.lib.logger.f.c(TAG, "onActivityCreated");
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.setWindowAnimation(R.style.DialogAnimBottom);
        this.mParentDialog.setTitle(this.mActivity.getString(R.string.add_listing_to_collection_title));
        this.mCollectionUtil = new CollectionUtil(getActivity(), this, "list_open");
        setEmptyText((int) R.string.empty_favorites);
        if (this.mAdapter == null) {
            ListingCollectionsAdapter listingCollectionsAdapter = new ListingCollectionsAdapter(this.mActivity, this, getImageBatch(), this.mListingId, this.mListingImageUrl);
            this.mAdapter = listingCollectionsAdapter;
            showLoadingView();
            loadListingCollections();
        } else {
            this.mAdapter.refreshActivity(this.mActivity);
            showListView();
        }
        this.mListView.setDivider(null);
        this.mListView.setPadding(0, 0, getResources().getDimensionPixelOffset(R.dimen.listview_extra_padding), 0);
        this.mAdapter.notifyDataSetChanged();
        setListAdapter(this.mAdapter);
    }

    public void onAddListingCollection() {
        e.a((FragmentActivity) this.mActivity).f().a(this.mParentDialog.getChildFragmentManager()).a(AnimationMode.NONE).b((int) R.id.inner_fragment_container).a(this, this.mListingId, this.mListingImageUrl);
    }

    public void onListingCollectionAdded(ListingCollection listingCollection) {
        CheckableListingCollection checkableListingCollection = new CheckableListingCollection(listingCollection);
        checkableListingCollection.setIsNew(true);
        this.mAdapter.add(checkableListingCollection);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAdapter.notifyDataSetChanged();
    }

    private void loadListingCollections() {
        getRequestQueue().a((Object) this, (com.etsy.android.lib.core.g<Result>) new a<Result>());
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        showLoadingView();
        loadListingCollections();
    }

    private void setResult(int i) {
        Intent intent = this.mActivity.getIntent();
        intent.putExtra("listing", this.mListing);
        intent.putExtra(Collection.TYPE_COLLECTION, this.mAddedCollection);
        intent.putExtra("should_show_social_invites_prompt", this.mShouldShowSocialInvitesPrompt);
        if (this.mActivity.getParent() == null) {
            this.mActivity.setResult(i, intent);
        } else {
            this.mActivity.getParent().setResult(i, intent);
        }
        this.mActivity.setResult(i, intent);
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }

    public void onBatchSuccess(boolean z) {
        ListingCollectionsBooleanState inCollectionsStateChanged = this.mAdapter.getInCollectionsStateChanged();
        this.mShouldShowSocialInvitesPrompt = z;
        this.mAddedCollection = this.mAdapter.getCollectionToShare();
        handleStateResult(inCollectionsStateChanged);
        this.mParentDialog.dismiss();
    }

    private void handleStateResult(ListingCollectionsBooleanState listingCollectionsBooleanState) {
        switch (listingCollectionsBooleanState) {
            case IN_COLLECTIONS:
                setResult(614);
                Intent intent = new Intent();
                intent.setAction(EtsyAction.STATE_CHANGE.getAction());
                intent.putExtra("id", this.mListingId.toString());
                intent.putExtra(EtsyAction.STATE_COLLECTIONS, true);
                LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
                break;
            case NOT_IN_COLLECTIONS:
                setResult(615);
                Intent intent2 = new Intent();
                intent2.setAction(EtsyAction.STATE_CHANGE.getAction());
                intent2.putExtra("id", this.mListingId.toString());
                intent2.putExtra(EtsyAction.STATE_COLLECTIONS, false);
                LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent2);
                break;
            case UNCHANGED:
                setResult(616);
                break;
        }
        Intent intent3 = new Intent("com.etsy.android.listing.lists.EDITED");
        intent3.putExtra("listing_id", this.mListingId);
        intent3.putExtra("listing_collections_state", listingCollectionsBooleanState);
        LocalBroadcastManager.getInstance(this.mActivity).sendBroadcast(intent3);
    }

    public void onBatchError() {
        aj.b((Context) this.mActivity, (int) R.string.whoops_somethings_wrong);
    }
}
