package com.etsy.android.ui.adapters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.AppreciationPhotoFeature;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.cardview.clickhandlers.f;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.shop.AppreciationPhotoFeatureCardViewHolder;
import com.etsy.android.ui.shop.ShopInfoViewHolder;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.ButtonViewHolder;
import com.etsy.android.uikit.viewholder.ButtonViewHolder.a;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;
import com.etsy.android.uikit.viewholder.MarginsItemDecoration;
import com.etsy.android.vespa.d;
import com.etsy.android.vespa.viewholders.ListSectionHeaderViewHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppreciationPhotoLandingPageAdapter extends Adapter<ViewHolder> implements d {
    private static final int TYPE_PRIMARY_CARD = 501;
    private static final int TYPE_SHOP_INFO = 502;
    private static final int TYPE_SHOP_LISTING_CARD = 505;
    private static final int TYPE_SIMILAR_LISTINGS_HEADER = 504;
    private static final int TYPE_SIMILAR_LISTING_CARD = 506;
    private static final int TYPE_VISIT_SHOP = 503;
    /* access modifiers changed from: private */
    public Activity mActivity;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                AppreciationPhotoLandingPageAdapter.this.listingStateChanged(intent.getExtras());
            }
        }
    };
    private EtsyId mCurrentUserShopId;
    /* access modifiers changed from: private */
    public AppreciationPhotoFeature mData;
    private boolean mDidMapListings = false;
    private c mImageBatch;
    private f mListingCardClickListener;
    private HashMap<String, List<Integer>> mListingIdToPositionMap = new HashMap<>();
    /* access modifiers changed from: private */
    public int mMaxSpanSize;
    private HashMap<Integer, ListingLike> mPositionToListingCardMap = new HashMap<>();
    private boolean mShouldShowShop;
    @NonNull
    private w mViewTracker;
    private ArrayList<Integer> mViewTypes = new ArrayList<>();
    private a mVisitShopButtonData;

    public boolean canRemoveItems() {
        return false;
    }

    public void onRemoveItem(int i) {
    }

    public AppreciationPhotoLandingPageAdapter(Activity activity, c cVar, @NonNull w wVar, int i) {
        this.mImageBatch = cVar;
        this.mViewTracker = wVar;
        this.mActivity = activity;
        this.mMaxSpanSize = i;
        this.mCurrentUserShopId = SharedPreferencesUtility.j(this.mActivity);
        this.mListingCardClickListener = new f((FragmentActivity) this.mActivity, this, this.mViewTracker);
    }

    public void setData(AppreciationPhotoFeature appreciationPhotoFeature) {
        this.mData = appreciationPhotoFeature;
        this.mViewTypes.add(Integer.valueOf(501));
        int size = this.mData.getShopListings().size();
        boolean z = true;
        boolean z2 = !this.mData.isShopOnVacation() && size > 0;
        if (z2) {
            this.mViewTypes.add(Integer.valueOf(502));
            if (size % 4 == 0) {
                for (int i = 0; i < size; i++) {
                    this.mViewTypes.add(Integer.valueOf(505));
                }
            } else {
                appreciationPhotoFeature.getShopListings().clear();
            }
            this.mViewTypes.add(Integer.valueOf(503));
        }
        int size2 = this.mData.getSimilarListings().size();
        if (size2 <= 0) {
            z = false;
        }
        if (z) {
            this.mViewTypes.add(Integer.valueOf(TYPE_SIMILAR_LISTINGS_HEADER));
            for (int i2 = 0; i2 < size2; i2++) {
                this.mViewTypes.add(Integer.valueOf(TYPE_SIMILAR_LISTING_CARD));
            }
        }
        this.mVisitShopButtonData = new a(this.mActivity.getString(R.string.visit_shop), new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a(AppreciationPhotoLandingPageAdapter.this.mActivity).b(AppreciationPhotoLandingPageAdapter.this.mData.getShopId());
            }
        });
        this.mShouldShowShop = z2;
        notifyItemRangeChanged(0, this.mViewTypes.size());
    }

    public void clear() {
        this.mData = null;
        this.mViewTypes.clear();
        this.mPositionToListingCardMap.clear();
        this.mListingIdToPositionMap.clear();
        this.mCurrentUserShopId = null;
        this.mShouldShowShop = false;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mData != null) {
            switch (viewHolder.getItemViewType()) {
                case 501:
                    ((AppreciationPhotoFeatureCardViewHolder) viewHolder).bind(this.mData);
                    break;
                case 502:
                    ((ShopInfoViewHolder) viewHolder).bind(this.mData, this.mImageBatch);
                    break;
                case 503:
                    ((ButtonViewHolder) viewHolder).bind(this.mVisitShopButtonData);
                    break;
                case TYPE_SIMILAR_LISTINGS_HEADER /*504*/:
                    ((ListSectionHeaderViewHolder) viewHolder).bind(new BasicSectionHeader(this.mActivity.getString(R.string.similar_items), null));
                    break;
                case 505:
                    ListingCardViewHolder listingCardViewHolder = (ListingCardViewHolder) viewHolder;
                    listingCardViewHolder.bind(retrieveListingFromHolder(listingCardViewHolder));
                    listingCardViewHolder.hideShopName();
                    break;
                default:
                    ListingCardViewHolder listingCardViewHolder2 = (ListingCardViewHolder) viewHolder;
                    listingCardViewHolder2.bind(retrieveListingFromHolder(listingCardViewHolder2));
                    break;
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        switch (i) {
            case 501:
                return new AppreciationPhotoFeatureCardViewHolder(from, viewGroup, this.mImageBatch, new com.etsy.android.ui.shop.a((FragmentActivity) this.mActivity, this, this.mViewTracker));
            case 502:
                return new ShopInfoViewHolder(from.inflate(R.layout.ap_landing_page_shop_info_view, viewGroup, false), this.mActivity);
            case 503:
                return new ButtonViewHolder(from.inflate(R.layout.button_blue, viewGroup, false));
            case TYPE_SIMILAR_LISTINGS_HEADER /*504*/:
                return new ListSectionHeaderViewHolder(viewGroup);
            default:
                ListingCardViewHolder listingCardViewHolder = new ListingCardViewHolder(viewGroup, this.mListingCardClickListener, this.mImageBatch, false, false);
                return listingCardViewHolder;
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        switch (viewHolder.getItemViewType()) {
            case 505:
            case TYPE_SIMILAR_LISTING_CARD /*506*/:
                ((ListingCardViewHolder) viewHolder).recycle();
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.mViewTypes.size();
    }

    public int getItemViewType(int i) {
        return ((Integer) this.mViewTypes.get(i)).intValue();
    }

    public void onItemChanged(int i) {
        notifyItemChanged(i);
    }

    public SpanSizeLookup spanSizeLookup() {
        AnonymousClass3 r0 = new SpanSizeLookup() {
            public int getSpanSize(int i) {
                switch (AppreciationPhotoLandingPageAdapter.this.getItemViewType(i)) {
                    case 505:
                    case AppreciationPhotoLandingPageAdapter.TYPE_SIMILAR_LISTING_CARD /*506*/:
                        return 1;
                    default:
                        return AppreciationPhotoLandingPageAdapter.this.mMaxSpanSize;
                }
            }
        };
        r0.setSpanIndexCacheEnabled(true);
        return r0;
    }

    private int similarListingsOffset() {
        return this.mData.getShopListings().size() + (this.mShouldShowShop ? 4 : 2);
    }

    public ListingCard retrieveListingFromHolder(ListingCardViewHolder listingCardViewHolder) {
        int itemViewType = listingCardViewHolder.getItemViewType();
        int adapterPosition = listingCardViewHolder.getAdapterPosition();
        if (itemViewType == 505) {
            return (ListingCard) this.mData.getShopListings().get(adapterPosition - 2);
        }
        return (ListingCard) this.mData.getSimilarListings().get(adapterPosition - similarListingsOffset());
    }

    public BroadcastReceiver getStateBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    /* access modifiers changed from: private */
    public void listingStateChanged(Bundle bundle) {
        if (!this.mDidMapListings) {
            this.mPositionToListingCardMap.put(Integer.valueOf(0), this.mData.getListingCard());
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(0));
            this.mListingIdToPositionMap.put(this.mData.getListingCard().getListingId().getId(), arrayList);
            mapListings(this.mData.getShopListings(), 2);
            mapListings(this.mData.getSimilarListings(), similarListingsOffset());
            this.mDidMapListings = true;
        }
        String string = bundle.getString("id");
        if (string != null && this.mListingIdToPositionMap.containsKey(string)) {
            for (Integer intValue : (List) this.mListingIdToPositionMap.get(string)) {
                int intValue2 = intValue.intValue();
                updateListingState((ListingLike) this.mPositionToListingCardMap.get(Integer.valueOf(intValue2)), bundle);
                notifyItemChanged(intValue2);
            }
        }
    }

    private void mapListings(List<ListingCard> list, int i) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ListingCard listingCard = (ListingCard) list.get(i2);
            String etsyId = listingCard.getListingId().toString();
            if (this.mListingIdToPositionMap.get(etsyId) == null) {
                this.mListingIdToPositionMap.put(etsyId, new ArrayList());
            }
            int i3 = i2 + i;
            ((List) this.mListingIdToPositionMap.get(etsyId)).add(Integer.valueOf(i3));
            this.mPositionToListingCardMap.put(Integer.valueOf(i3), listingCard);
        }
    }

    private void updateListingState(ListingLike listingLike, Bundle bundle) {
        if (bundle.containsKey(EtsyAction.STATE_FAVORITE)) {
            listingLike.setIsFavorite(bundle.getBoolean(EtsyAction.STATE_FAVORITE));
        }
        if (bundle.containsKey(EtsyAction.STATE_COLLECTIONS)) {
            listingLike.setHasCollections(bundle.getBoolean(EtsyAction.STATE_COLLECTIONS));
        }
    }

    public MarginsItemDecoration getDecoration() {
        AnonymousClass4 r0 = new MarginsItemDecoration(0, 0, 0, this.mActivity.getResources().getDimensionPixelSize(R.dimen.margin_large)) {
            public boolean isDecorated(View view, RecyclerView recyclerView) {
                return recyclerView.getChildViewHolder(view).getItemViewType() == 503;
            }
        };
        return r0;
    }
}
