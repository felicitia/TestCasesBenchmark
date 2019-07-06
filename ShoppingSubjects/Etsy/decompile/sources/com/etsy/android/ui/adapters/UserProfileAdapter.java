package com.etsy.android.ui.adapters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.UserProfilePage;
import com.etsy.android.lib.models.apiv3.UserProfileV3;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.cardview.clickhandlers.f;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.user.profile.viewholders.ProfileUserShopViewHolder;
import com.etsy.android.ui.user.profile.viewholders.UserActionButtonsViewHolder;
import com.etsy.android.ui.user.profile.viewholders.UserProfileHeaderViewHolder;
import com.etsy.android.ui.view.viewholders.ProfileCardViewHolder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.ButtonViewHolder;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;
import com.etsy.android.uikit.viewholder.MarginsItemDecoration;
import com.etsy.android.uikit.viewholder.TitleAndDescriptionViewHolder;
import com.etsy.android.vespa.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserProfileAdapter extends BaseRecyclerViewAdapter<Pair<?, Integer>> implements d {
    private static final int SHOP_LISTING_DISPLAY_COUNT = 4;
    static final int TYPE_FOOTER_BUTTON = 508;
    static final int TYPE_LISTING_CARD = 504;
    private static final int TYPE_SHOP_INFO = 503;
    static final int TYPE_USER_ACTION_BUTTONS = 510;
    static final int TYPE_USER_HEADER = 501;
    private static final int TYPE_USER_LIST_DESCRIPTION = 505;
    static final int TYPE_USER_PROFILE_CARD = 507;
    final a mConfig;
    private UserProfilePage mData;
    private boolean mDidMapListings;
    private final boolean mIsYou;
    private final f mListingCardListener;
    private final HashMap<String, ArrayList<Integer>> mListingIdsToPositionsMap = new HashMap<>();
    private final BroadcastReceiver mListingStateReceiver;
    private final MarginsItemDecoration mMarginsDecoration;

    private enum UserListType {
        LIST_TYPE_COLLECTION,
        LIST_TYPE_FAVORITE_SHOP
    }

    private static class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public boolean g;

        private a() {
        }

        static a a(Activity activity, boolean z) {
            a aVar = new a();
            boolean b2 = l.b(activity);
            boolean e2 = l.e(activity);
            aVar.g = e2 || b2;
            aVar.a = activity.getResources().getInteger(R.integer.user_profile_max_span_count);
            aVar.b = aVar.g ? 4 : 3;
            aVar.c = aVar.g ? aVar.a / 2 : aVar.a;
            aVar.d = aVar.a / aVar.c;
            aVar.e = (!b2 || !e2) ? aVar.a : aVar.a / 4;
            aVar.f = (!b2 || !e2 || z) ? aVar.a : (aVar.a * 3) / 4;
            return aVar;
        }
    }

    public boolean canRemoveItems() {
        return false;
    }

    public void onRemoveItem(int i) {
    }

    public UserProfileAdapter(FragmentActivity fragmentActivity, c cVar, @NonNull w wVar, boolean z) {
        super(fragmentActivity, cVar);
        AnonymousClass1 r0 = new MarginsItemDecoration(0, ((FragmentActivity) this.mContext).getResources().getDimensionPixelOffset(R.dimen.fixed_medium), 0, 0) {
            /* access modifiers changed from: protected */
            public boolean isDecorated(View view, RecyclerView recyclerView) {
                return recyclerView.getChildViewHolder(view).getItemViewType() == 505;
            }
        };
        this.mMarginsDecoration = r0;
        this.mListingStateReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                    UserProfileAdapter.this.listingStateChanged(intent.getExtras());
                }
            }
        };
        this.mListingCardListener = new f(fragmentActivity, this, wVar);
        this.mIsYou = z;
        this.mConfig = a.a(fragmentActivity, z);
    }

    public void setData(UserProfilePage userProfilePage) {
        UserProfileV3 userProfile = userProfilePage.getUserProfile();
        this.mItems.add(new Pair(userProfilePage.getUserProfile(), Integer.valueOf(501)));
        if (!this.mIsYou) {
            this.mItems.add(new Pair(userProfile, Integer.valueOf(TYPE_USER_ACTION_BUTTONS)));
        }
        if (userProfile.isSeller()) {
            final ShopCard shopCard = userProfilePage.getShopCard();
            if (shopCard != null) {
                this.mItems.add(new Pair(shopCard, Integer.valueOf(503)));
                List cardListings = shopCard.getCardListings();
                int size = shopCard.getCardListings().size();
                if (size > 0 && size % 4 == 0) {
                    for (int i = 0; i < size; i++) {
                        this.mItems.add(new Pair(cardListings.get(i), Integer.valueOf(TYPE_LISTING_CARD)));
                    }
                }
                this.mItems.add(new Pair(new com.etsy.android.uikit.viewholder.ButtonViewHolder.a(((FragmentActivity) this.mContext).getString(R.string.visit_shop), new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        e.a((Activity) UserProfileAdapter.this.mContext).b(shopCard.getShopId());
                    }
                }), Integer.valueOf(TYPE_FOOTER_BUTTON)));
            }
        }
        for (UserListType configureForUserListType : UserListType.values()) {
            configureForUserListType(configureForUserListType, userProfilePage);
        }
        this.mData = userProfilePage;
        notifyItemRangeInserted(0, this.mItems.size());
    }

    public void clear() {
        super.clear();
        this.mData = null;
        this.mDidMapListings = false;
        this.mListingIdsToPositionsMap.clear();
    }

    private int configureForUserListType(UserListType userListType, UserProfilePage userProfilePage) {
        List list;
        int i;
        String str;
        String str2;
        String str3;
        TrackingOnClickListener trackingOnClickListener;
        UserProfileV3 userProfile = userProfilePage.getUserProfile();
        Resources resources = ((FragmentActivity) this.mContext).getResources();
        switch (userListType) {
            case LIST_TYPE_COLLECTION:
                int favoriteListingsCount = userProfile.getFavoriteListingsCount();
                if (favoriteListingsCount > 0) {
                    int size = userProfilePage.getCollections().size();
                    list = filterCollections(userProfilePage.getCollections(), this.mConfig.b, this.mIsYou);
                    String string = resources.getString(R.string.favorite_items);
                    str2 = resources.getQuantityString(R.plurals.item_titlecase_quantity, favoriteListingsCount, new Object[]{Integer.valueOf(favoriteListingsCount)});
                    str3 = resources.getString(R.string.see_all_favorite_items);
                    trackingOnClickListener = buildClickListener(userListType, userProfile);
                    i = size;
                    str = string;
                    break;
                } else {
                    return 0;
                }
            case LIST_TYPE_FAVORITE_SHOP:
                int favoriteShopsCount = userProfile.getFavoriteShopsCount();
                if (favoriteShopsCount > 0) {
                    list = userProfilePage.getFavoriteShops();
                    str = resources.getString(R.string.favorite_shops);
                    StringBuilder sb = new StringBuilder();
                    sb.append(favoriteShopsCount);
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    sb.append(resources.getQuantityString(R.plurals.shops_plurals_nt, favoriteShopsCount));
                    String sb2 = sb.toString();
                    str3 = resources.getString(R.string.see_all_favorite_shops);
                    trackingOnClickListener = buildClickListener(userListType, userProfile);
                    i = favoriteShopsCount;
                    str2 = sb2;
                    break;
                } else {
                    return 0;
                }
            default:
                return 0;
        }
        this.mItems.add(new Pair(new com.etsy.android.uikit.viewholder.TitleAndDescriptionViewHolder.a(str, str2), Integer.valueOf(505)));
        int size2 = list.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.mItems.add(new Pair(list.get(i2), Integer.valueOf(507)));
        }
        if (i == size2) {
            return size2;
        }
        this.mItems.add(new Pair(new com.etsy.android.uikit.viewholder.ButtonViewHolder.a(str3, trackingOnClickListener), Integer.valueOf(TYPE_FOOTER_BUTTON)));
        return size2;
    }

    @Nullable
    private TrackingOnClickListener buildClickListener(UserListType userListType, final UserProfileV3 userProfileV3) {
        switch (userListType) {
            case LIST_TYPE_COLLECTION:
                return new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        e.a((FragmentActivity) UserProfileAdapter.this.mContext).a().a(userProfileV3.getUserId(), 0, userProfileV3.getLoginName());
                    }
                };
            case LIST_TYPE_FAVORITE_SHOP:
                return new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        e.a((FragmentActivity) UserProfileAdapter.this.mContext).a().a(userProfileV3.getUserId(), 1, userProfileV3.getLoginName());
                    }
                };
            default:
                return null;
        }
    }

    private static List<Collection> filterCollections(List<Collection> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < i && i3 < size) {
            Collection collection = (Collection) list.get(i3);
            if (collection.getListingsCount() > 0 && (z || collection.isPublic())) {
                arrayList.add(collection);
                i2++;
            }
            i3++;
        }
        return arrayList;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 501:
                return new UserProfileHeaderViewHolder(this.mInflater.inflate(R.layout.user_profile_header, viewGroup, false));
            case 503:
                return new ProfileUserShopViewHolder(this.mInflater.inflate(R.layout.user_profile_shop_info, viewGroup, false));
            case TYPE_LISTING_CARD /*504*/:
                ListingCardViewHolder listingCardViewHolder = new ListingCardViewHolder(viewGroup, this.mListingCardListener, this.mImageBatch, false, false);
                return listingCardViewHolder;
            case 505:
                return new TitleAndDescriptionViewHolder(this.mInflater.inflate(R.layout.layout_heading_and_description, viewGroup, false));
            case 507:
                return new ProfileCardViewHolder(this.mInflater.inflate(R.layout.user_profile_card, viewGroup, false), this.mImageBatch, this.mConfig.b, this.mConfig.g);
            case TYPE_FOOTER_BUTTON /*508*/:
                return new ButtonViewHolder(this.mInflater.inflate(R.layout.button_blue, viewGroup, false));
            case TYPE_USER_ACTION_BUTTONS /*510*/:
                return new UserActionButtonsViewHolder(this.mInflater.inflate(R.layout.user_profile_action_buttons, viewGroup, false));
            default:
                return null;
        }
    }

    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        if (this.mData != null) {
            Pair pair = (Pair) getItem(i);
            switch (((Integer) pair.second).intValue()) {
                case 501:
                    ((UserProfileHeaderViewHolder) viewHolder).bind((UserProfileV3) pair.first, this.mIsYou, this.mImageBatch, (FragmentActivity) this.mContext);
                    break;
                case 503:
                    UserProfileV3 userProfile = this.mData.getUserProfile();
                    ((ProfileUserShopViewHolder) viewHolder).bind((ShopCard) pair.first, userProfile.getFirstName(), userProfile.getTransactionsSoldCount(), this.mIsYou, this.mImageBatch);
                    break;
                case TYPE_LISTING_CARD /*504*/:
                    ((ListingCardViewHolder) viewHolder).bind((ListingCard) pair.first);
                    break;
                case 505:
                    ((TitleAndDescriptionViewHolder) viewHolder).bind((com.etsy.android.uikit.viewholder.TitleAndDescriptionViewHolder.a) pair.first);
                    break;
                case 507:
                    bindUserProfileCard((ProfileCardViewHolder) viewHolder, pair.first);
                    break;
                case TYPE_FOOTER_BUTTON /*508*/:
                    ((ButtonViewHolder) viewHolder).bind((com.etsy.android.uikit.viewholder.ButtonViewHolder.a) pair.first);
                    break;
                case TYPE_USER_ACTION_BUTTONS /*510*/:
                    ((UserActionButtonsViewHolder) viewHolder).bind((UserProfileV3) pair.first, (FragmentActivity) this.mContext);
                    break;
            }
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType == TYPE_LISTING_CARD) {
            ((ListingCardViewHolder) viewHolder).recycle();
        } else if (itemViewType == 507) {
            ((ProfileCardViewHolder) viewHolder).recycle();
        }
    }

    private static void bindUserProfileCard(ProfileCardViewHolder profileCardViewHolder, Object obj) {
        if (obj instanceof Collection) {
            profileCardViewHolder.bind((Collection) obj);
        } else if (obj instanceof ShopCard) {
            profileCardViewHolder.bind((ShopCard) obj);
        }
    }

    public int getListItemViewType(int i) {
        return ((Integer) ((Pair) this.mItems.get(i)).second).intValue();
    }

    public void onItemChanged(int i) {
        notifyItemChanged(i);
    }

    public SpanSizeLookup spanSizeLookup() {
        AnonymousClass6 r0 = new SpanSizeLookup() {
            public int getSpanSize(int i) {
                int listItemViewType = UserProfileAdapter.this.getListItemViewType(i);
                if (listItemViewType == 501) {
                    return UserProfileAdapter.this.mConfig.f;
                }
                if (listItemViewType == UserProfileAdapter.TYPE_LISTING_CARD) {
                    return 1;
                }
                if (listItemViewType == 507) {
                    return UserProfileAdapter.this.mConfig.c;
                }
                if (listItemViewType != UserProfileAdapter.TYPE_USER_ACTION_BUTTONS) {
                    return UserProfileAdapter.this.mConfig.a;
                }
                return UserProfileAdapter.this.mConfig.e;
            }
        };
        r0.setSpanIndexCacheEnabled(true);
        return r0;
    }

    public UserProfilePage getData() {
        return this.mData;
    }

    public MarginsItemDecoration getMarginDividerDecoration() {
        return this.mMarginsDecoration;
    }

    public GridLayoutManager getLayoutManager(Context context) {
        return new GridLayoutManager(context, this.mConfig.a) {
            /* access modifiers changed from: protected */
            public int getExtraLayoutSpace(State state) {
                return 60;
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void listingStateChanged(Bundle bundle) {
        if (!this.mDidMapListings) {
            mapListings(bundle);
            this.mDidMapListings = true;
        }
        String string = bundle.getString("id");
        if (!TextUtils.isEmpty(string) && this.mListingIdsToPositionsMap.containsKey(string)) {
            for (Integer intValue : (List) this.mListingIdsToPositionsMap.get(string)) {
                int intValue2 = intValue.intValue();
                if (((Pair) this.mItems.get(intValue2)).first instanceof ListingLike) {
                    updateListingState((ListingLike) ((Pair) this.mItems.get(intValue2)).first, bundle);
                    notifyItemChanged(intValue2);
                }
            }
        }
    }

    private void mapListings(Bundle bundle) {
        for (int i = 0; i < this.mItems.size(); i++) {
            Object obj = ((Pair) this.mItems.get(i)).first;
            if (obj instanceof ListingLike) {
                String etsyId = ((ListingLike) obj).getListingId().toString();
                ArrayList arrayList = (ArrayList) this.mListingIdsToPositionsMap.get(etsyId);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.mListingIdsToPositionsMap.put(etsyId, arrayList);
                }
                arrayList.add(Integer.valueOf(i));
            }
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

    public BroadcastReceiver getListingStateChangedReceiver() {
        return this.mListingStateReceiver;
    }
}
