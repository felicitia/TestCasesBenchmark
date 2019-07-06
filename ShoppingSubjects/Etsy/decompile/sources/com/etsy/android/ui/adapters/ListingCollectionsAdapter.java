package com.etsy.android.ui.adapters;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.apiv3.CheckableListingCollection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.TrackedEtsyId;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.util.CollectionUtil.ListingCollectionAction;
import com.etsy.android.ui.util.CollectionUtil.ListingCollectionsBooleanState;
import com.etsy.android.ui.util.CollectionUtil.h;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListingCollectionsAdapter extends BaseModelArrayAdapter<CheckableListingCollection> {
    private static final int VIEW_TYPE_COUNT = 2;
    private static final int VIEW_TYPE_HEADER = 1;
    private static final int VIEW_TYPE_NORMAL = 0;
    private int mDefaultIcon = R.drawable.bg_empty_image;
    private View mHeaderView;
    /* access modifiers changed from: private */
    public int mImageHeight;
    /* access modifiers changed from: private */
    public int mImageWidth;
    /* access modifiers changed from: private */
    public h mListingCollectionCreator = null;
    /* access modifiers changed from: private */
    public EtsyId mListingId;
    /* access modifiers changed from: private */
    public String mListingImageUrl;

    private static class a {
        View a;
        View b;
        TextView c;
        TextView d;
        ImageView e;
        CheckBox f;

        private a() {
        }
    }

    private boolean isHeaderPosition(int i) {
        return i == 0;
    }

    public ListingCollectionsAdapter(FragmentActivity fragmentActivity, h hVar, c cVar, EtsyId etsyId, String str) {
        super(fragmentActivity, R.layout.list_item_listingcollection, cVar);
        Resources resources = fragmentActivity.getResources();
        this.mListingId = etsyId;
        this.mListingImageUrl = str;
        this.mListingCollectionCreator = hVar;
        this.mImageWidth = resources.getDimensionPixelOffset(R.dimen.review_image_width);
        this.mImageHeight = resources.getDimensionPixelOffset(R.dimen.review_image_height);
    }

    public int getItemViewType(int i) {
        return isHeaderPosition(i) ? 1 : 0;
    }

    public int getViewTypeCount() {
        return super.getViewTypeCount() + 2;
    }

    public int getCount() {
        return super.getCount() + 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (isHeaderPosition(i)) {
            return getViewHeader();
        }
        return getViewDefault(i - 1, view);
    }

    private View getViewHeader() {
        if (this.mHeaderView == null) {
            this.mHeaderView = getLayoutInflater().inflate(R.layout.list_item_listingcollection_header, null);
        }
        ((RelativeLayout) this.mHeaderView.findViewById(R.id.new_collection_prompt)).setOnClickListener(new TrackingOnClickListener(AnalyticsLogAttribute.LISTING_ID, this.mListingId) {
            public void onViewClick(View view) {
                if (ListingCollectionsAdapter.this.mListingCollectionCreator != null) {
                    ListingCollectionsAdapter.this.mListingCollectionCreator.onAddListingCollection();
                }
            }
        });
        return this.mHeaderView;
    }

    private View getViewDefault(int i, View view) {
        a aVar;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null);
            aVar = createListingCollectionHolder(view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        fillListingCollection(aVar, (CheckableListingCollection) getItem(i));
        return view;
    }

    private void fillListingCollection(a aVar, CheckableListingCollection checkableListingCollection) {
        setIconImageOrDefault(aVar.e, checkableListingCollection.getRepresentativeListings(), 0);
        if (checkableListingCollection.isPrivate()) {
            aVar.b.setVisibility(0);
        } else {
            aVar.b.setVisibility(8);
        }
        aVar.c.setText(checkableListingCollection.getName());
        aVar.d.setText(formatNumItemsText(checkableListingCollection.getListingsCount()));
        bindCollectionSelectedCheckbox(aVar.f, aVar.d, aVar.e, checkableListingCollection);
        bindRowClickHandler(aVar.a, aVar.f);
        if (checkableListingCollection.getIsNew()) {
            aVar.f.setChecked(true);
        }
    }

    private a createListingCollectionHolder(View view) {
        a aVar = new a();
        aVar.a = view;
        aVar.b = view.findViewById(R.id.collection_privacy_icon);
        aVar.c = (TextView) view.findViewById(R.id.collection_title);
        aVar.d = (TextView) view.findViewById(R.id.collection_num_items);
        aVar.e = (ImageView) view.findViewById(R.id.collection_icon);
        aVar.f = (CheckBox) view.findViewById(R.id.collection_selected);
        return aVar;
    }

    private void bindCollectionSelectedCheckbox(CheckBox checkBox, TextView textView, ImageView imageView, CheckableListingCollection checkableListingCollection) {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(checkableListingCollection.getIsChecked());
        final CheckableListingCollection checkableListingCollection2 = checkableListingCollection;
        final CheckBox checkBox2 = checkBox;
        final ImageView imageView2 = imageView;
        final TextView textView2 = textView;
        AnonymousClass2 r1 = new TrackingOnCheckedChangeListener(new i[]{checkableListingCollection, new TrackedEtsyId(AnalyticsLogAttribute.LISTING_ID, this.mListingId)}) {
            public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
                checkableListingCollection2.setIsChecked(z);
                int listingsCount = checkableListingCollection2.getListingsCount();
                List representativeListings = checkableListingCollection2.getRepresentativeListings();
                if (!checkableListingCollection2.getWasChanged()) {
                    ListingCollectionsAdapter.this.setIconImageOrDefault(imageView2, representativeListings, 0);
                } else if (checkBox2.isChecked()) {
                    ListingCollectionsAdapter.this.getImageBatch().a(ListingCollectionsAdapter.this.mListingImageUrl, imageView2, ListingCollectionsAdapter.this.mImageWidth, ListingCollectionsAdapter.this.mImageHeight);
                    listingsCount++;
                } else {
                    if (representativeListings != null && representativeListings.size() > 0 && ListingCollectionsAdapter.this.mListingId.getId().equals(((Listing) representativeListings.get(0)).getListingId().getId())) {
                        ListingCollectionsAdapter.this.setIconImageOrDefault(imageView2, representativeListings, 1);
                    }
                    listingsCount--;
                }
                textView2.setText(ListingCollectionsAdapter.this.formatNumItemsText(listingsCount));
            }
        };
        checkBox.setOnCheckedChangeListener(r1);
        checkBox.setClickable(false);
    }

    private void bindRowClickHandler(View view, final CheckBox checkBox) {
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                checkBox.toggle();
            }
        });
    }

    public Map<String, ListingCollectionAction> getCollectionsChanged() {
        HashMap hashMap = new HashMap(0);
        for (int i = 0; i < super.getCount(); i++) {
            CheckableListingCollection checkableListingCollection = (CheckableListingCollection) getItem(i);
            if (checkableListingCollection.getWasChanged()) {
                if (checkableListingCollection.getIncludesListing()) {
                    hashMap.put(checkableListingCollection.getKey(), ListingCollectionAction.REMOVE);
                } else {
                    hashMap.put(checkableListingCollection.getKey(), ListingCollectionAction.ADD);
                }
            }
        }
        return hashMap;
    }

    @Nullable
    public CheckableListingCollection getCollectionToShare() {
        CheckableListingCollection checkableListingCollection = null;
        for (int i = 0; i < super.getCount(); i++) {
            CheckableListingCollection checkableListingCollection2 = (CheckableListingCollection) getItem(i);
            if (checkableListingCollection2.isPublic() && checkableListingCollection2.getWasChanged() && !checkableListingCollection2.getIncludesListing() && (checkableListingCollection == null || checkableListingCollection2.getListingsCount() > checkableListingCollection.getListingsCount())) {
                checkableListingCollection = checkableListingCollection2;
            }
        }
        return checkableListingCollection;
    }

    public ListingCollectionsBooleanState getInCollectionsStateChanged() {
        int count = super.getCount();
        if (count == 0) {
            return ListingCollectionsBooleanState.UNCHANGED;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < count; i++) {
            CheckableListingCollection checkableListingCollection = (CheckableListingCollection) getItem(i);
            if (checkableListingCollection.getWasChanged()) {
                if (checkableListingCollection.getIncludesListing()) {
                    z2 = true;
                } else {
                    z = true;
                }
            } else if (checkableListingCollection.getIsChecked() && checkableListingCollection.getIncludesListing()) {
                return ListingCollectionsBooleanState.UNCHANGED;
            }
        }
        if (z && !z2) {
            return ListingCollectionsBooleanState.IN_COLLECTIONS;
        }
        if (!z2 || z) {
            return ListingCollectionsBooleanState.UNCHANGED;
        }
        return ListingCollectionsBooleanState.NOT_IN_COLLECTIONS;
    }

    /* access modifiers changed from: private */
    public String formatNumItemsText(int i) {
        return getContext().getResources().getQuantityString(R.plurals.item_titlecase_quantity, i, new Object[]{af.a((double) i)});
    }

    /* access modifiers changed from: private */
    public void setIconImageOrDefault(ImageView imageView, List<Listing> list, int i) {
        if (list == null || list.size() <= i || ((Listing) list.get(i)).getImage() == null) {
            imageView.setImageDrawable(getActivityContext().getResources().getDrawable(this.mDefaultIcon));
            return;
        }
        getImageBatch().a(((Listing) list.get(i)).getImage().getImageUrl(), imageView, this.mImageWidth, this.mImageHeight);
    }
}
