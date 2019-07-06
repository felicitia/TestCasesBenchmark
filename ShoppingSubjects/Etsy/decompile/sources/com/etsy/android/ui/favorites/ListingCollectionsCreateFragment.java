package com.etsy.android.ui.favorites;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.util.CollectionUtil;
import com.etsy.android.ui.util.CollectionUtil.g;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ListingCollectionsCreateFragment extends EtsyFragment implements g {
    /* access modifiers changed from: private */
    public Button mAddButton;
    g mCallback = null;
    /* access modifiers changed from: private */
    public CollectionUtil mCollectionUtils = null;
    private int mImageHeight;
    private int mImageWidth;
    /* access modifiers changed from: private */
    public EtsyId mListingId;
    private String mListingImageUrl;

    @NonNull
    public String getTrackingName() {
        return "list_create_open";
    }

    public void setListingCollectionCreationListener(g gVar) {
        this.mCallback = gVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mListingId = (EtsyId) arguments.getSerializable("listing_id");
        this.mListingImageUrl = arguments.getString(ResponseConstants.LISTING_IMAGE_URL);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Resources resources = getResources();
        this.mImageWidth = resources.getDimensionPixelOffset(R.dimen.review_image_width);
        this.mImageHeight = resources.getDimensionPixelOffset(R.dimen.review_image_height);
        View inflate = layoutInflater.inflate(R.layout.fragment_create_listingcollection, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.listing_icon);
        final EditText editText = (EditText) inflate.findViewById(R.id.new_collection_name);
        this.mAddButton = (Button) inflate.findViewById(R.id.btn_add);
        getImageBatch().a(this.mListingImageUrl, imageView, this.mImageWidth, this.mImageHeight);
        this.mAddButton.setOnClickListener(new TrackingOnClickListener(AnalyticsLogAttribute.LISTING_ID, this.mListingId) {
            public void onViewClick(View view) {
                ListingCollectionsCreateFragment.this.mAddButton.setEnabled(false);
                editText.clearFocus();
                s.a((View) editText);
                ListingCollectionsCreateFragment.this.mCollectionUtils.a((Activity) ListingCollectionsCreateFragment.this.mActivity, (g) ListingCollectionsCreateFragment.this, editText.getText().toString(), false, ListingCollectionsCreateFragment.this.mListingId);
            }
        });
        s.b((View) editText);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            IDialogFragment iDialogFragment = (IDialogFragment) getParentFragment();
            iDialogFragment.setTitle(this.mActivity.getString(R.string.collection_create_new));
            iDialogFragment.setOkButtonVisibility(8);
        }
        this.mCollectionUtils = new CollectionUtil(getActivity(), this, "list_create_open");
    }

    public void onListingCollectionAdded(ListingCollection listingCollection) {
        if (this.mCallback != null) {
            this.mCallback.onListingCollectionAdded(listingCollection);
        }
        goBack();
    }

    public void onListingCollectionError(String str) {
        this.mAddButton.setEnabled(true);
        if (this.mCallback != null) {
            this.mCallback.onListingCollectionError(str);
        }
        aj.a((Context) getActivity(), str);
    }

    public boolean handleBackPressed() {
        goBack();
        return true;
    }

    private void goBack() {
        if (getParentFragment() != null && getParentFragment().getChildFragmentManager() != null) {
            getParentFragment().getChildFragmentManager().popBackStack();
        }
    }
}
