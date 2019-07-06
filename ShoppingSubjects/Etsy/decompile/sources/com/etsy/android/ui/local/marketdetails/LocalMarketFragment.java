package com.etsy.android.ui.local.marketdetails;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.BOEApplication;
import com.etsy.android.R;
import com.etsy.android.lib.core.f;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalStoreImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.c.b;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.r;
import com.etsy.android.ui.local.h;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.view.ImageBatchSwitcher;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.SectionedRecyclerViewAdapter;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio.CropType;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.parceler.d;

public class LocalMarketFragment extends BaseRecyclerViewListFragment<BaseRecyclerViewAdapter> {
    private static final String SAVED_LOCAL_EVENT = "LOCAL_EVENT";
    private static final long SWITCH_IMAGE_SPEED_MILLIS = 10000;
    private com.etsy.android.ui.cardview.viewholders.LocalInStoreEventViewHolder.a mEventListener = new com.etsy.android.ui.cardview.viewholders.LocalInStoreEventViewHolder.a() {
        public void a(EtsyId etsyId) {
            e.a((Activity) LocalMarketFragment.this.getActivity()).a(etsyId, LocalMarketFragment.this.mShowLocalBrowseLink);
        }
    };
    private com.etsy.android.ui.cardview.viewholders.LocalAttendeeShopViewHolder.a mListener = new com.etsy.android.ui.cardview.viewholders.LocalAttendeeShopViewHolder.a() {
        public void a(EtsyId etsyId) {
            e.a((Activity) LocalMarketFragment.this.getActivity()).b(etsyId);
        }

        public void a(Attendee attendee) {
            if (l.c((Activity) LocalMarketFragment.this.getActivity())) {
                e.a(LocalMarketFragment.this.getActivity()).d().a(attendee, LocalMarketFragment.this.mLocalMarket);
            } else {
                e.a((Activity) LocalMarketFragment.this.getActivity()).a(attendee, LocalMarketFragment.this.mLocalMarket);
            }
        }
    };
    private FrameLayout mListingHeroRow1;
    private FrameLayout mListingHeroRow2;
    private e mLocalDecoration;
    /* access modifiers changed from: private */
    public LocalMarket mLocalMarket;
    private EtsyId mLocalMarketId;
    /* access modifiers changed from: private */
    public boolean mShowLocalBrowseLink;
    private ImageBatchSwitcher mStoreHeroImageSwitcher;

    private class a extends SectionedRecyclerViewAdapter {
        private a b = new a() {
            public void onMapReady(c cVar) {
                a(cVar, LocalMarketFragment.this.mLocalMarket);
            }

            public void a(String str) {
                r.a((Context) LocalMarketFragment.this.getActivity(), str);
            }

            public void a(TextView textView) {
                EtsyLinkify.a((Context) LocalMarketFragment.this.getActivity(), textView, false);
            }

            public void b(String str) {
                h.a((Activity) LocalMarketFragment.this.getActivity(), str);
            }

            public void c(String str) {
                h.a((Activity) LocalMarketFragment.this.getActivity(), LocalMarketFragment.this.getAnalyticsContext(), str);
            }

            public void a() {
                h.a((Activity) LocalMarketFragment.this.getActivity(), LocalMarketFragment.this.mLocalMarket);
            }

            public void b() {
                e.a(LocalMarketFragment.this.getActivity()).a().a(LocalMarketFragment.this.mLocalMarket);
            }

            private void a(c cVar, final LocalMarket localMarket) {
                if (cVar != null && localMarket != null) {
                    if (b.a(localMarket) != null) {
                        cVar.a(b.a(localMarket));
                    }
                    cVar.a((c.e) new c.e() {
                        public void a(LatLng latLng) {
                            AnonymousClass1.this.c(com.etsy.android.lib.util.c.a.a(localMarket));
                        }
                    });
                }
            }
        };
        private a c = new a() {
            public void a() {
                e.a((Activity) LocalMarketFragment.this.getActivity()).c("local");
            }
        };
        private d d;

        public a(FragmentActivity fragmentActivity, com.etsy.android.lib.core.img.c cVar) {
            super(fragmentActivity, cVar);
        }

        public int getSpanSize(int i, int i2) {
            return getItemViewType(i) != 502 ? super.getSpanSize(i, i2) : i2;
        }

        public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
            if (this.d == null) {
                this.d = new d(this.mInflater, viewGroup, this.b);
            }
            return this.d;
        }

        public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
            ((d) viewHolder).a(LocalMarketFragment.this, LocalMarketFragment.this.getChildFragmentManager(), LocalMarketFragment.this.mLocalMarket);
        }

        public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
            if (i == 502) {
                return new b(this.mInflater, viewGroup, this.c);
            }
            return new c(this.mInflater, viewGroup);
        }

        public void onBindFooterViewHolder(ViewHolder viewHolder, int i) {
            if (viewHolder instanceof c) {
                ((c) viewHolder).a(LocalMarketFragment.this.mLocalMarket.isWholesaleStore());
            }
        }
    }

    public int getLayoutId() {
        return R.layout.fragment_local_market;
    }

    @NonNull
    public String getTrackingName() {
        return "local_event_view";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(ResponseConstants.LOCAL_MARKET)) {
                LocalMarket localMarket = (LocalMarket) d.a(arguments.getParcelable(ResponseConstants.LOCAL_MARKET));
                this.mLocalMarketId = localMarket.getLocalMarketId();
                this.mLocalMarket = localMarket;
            } else {
                this.mLocalMarketId = (EtsyId) arguments.getSerializable(ResponseConstants.LOCAL_MARKET_ID);
            }
            this.mShowLocalBrowseLink = getArguments().getBoolean("show_local_browse_link");
        }
        if (this.mLocalMarketId == null) {
            this.mLocalMarketId = new EtsyId();
        }
        if (bundle != null) {
            this.mLocalMarket = (LocalMarket) bundle.getSerializable(SAVED_LOCAL_EVENT);
        }
        this.mAdapter = new a(getActivity(), getImageBatch());
    }

    /* access modifiers changed from: protected */
    @NonNull
    public LayoutManager createLayoutManager() {
        int integer = getResources().getInteger(R.integer.local_market_attendee_columns);
        int integer2 = getResources().getInteger(R.integer.local_market_category_columns);
        int integer3 = getResources().getInteger(R.integer.local_market_in_store_events_columns);
        final int i = (integer == integer2 && integer3 == integer) ? integer : integer2 * integer * integer3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), i);
        AnonymousClass3 r4 = new SpanSizeLookup() {
            public int getSpanSize(int i) {
                return LocalMarketFragment.this.mAdapter.getSpanSize(i, i);
            }
        };
        r4.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(r4);
        e eVar = new e(getActivity(), r4, i, integer, integer3);
        this.mLocalDecoration = eVar;
        return gridLayoutManager;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mListingHeroRow1 = (FrameLayout) onCreateView.findViewById(R.id.hero_row_1);
        this.mListingHeroRow2 = (FrameLayout) onCreateView.findViewById(R.id.hero_row_2);
        this.mStoreHeroImageSwitcher = (ImageBatchSwitcher) onCreateView.findViewById(R.id.wholesale_store_hero);
        this.mRecyclerView.addItemDecoration(this.mLocalDecoration);
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle == null || !bundle.containsKey(SAVED_LOCAL_EVENT)) {
            loadContent();
            return;
        }
        this.mLocalMarket = (LocalMarket) bundle.getSerializable(SAVED_LOCAL_EVENT);
        fillView(this.mLocalMarket);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mLocalMarket != null) {
            bundle.putSerializable(SAVED_LOCAL_EVENT, this.mLocalMarket);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem == null) {
            return;
        }
        if (this.mLocalMarket != null) {
            findItem.setVisible(true);
        } else {
            findItem.setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        showShareDialog();
        return true;
    }

    private void showShareDialog() {
        com.etsy.android.ui.util.d.a("local_event_view", ShareType.LOCAL_SHARE, this.mLocalMarketId.getId());
        e.a(getActivity()).a().b(this.mLocalMarket);
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        fetchLocalMarket();
    }

    /* access modifiers changed from: protected */
    public boolean isEmpty() {
        return this.mLocalMarket == null;
    }

    private void fetchLocalMarket() {
        Locale locale = Locale.getDefault();
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage());
        sb.append("-");
        sb.append(locale.getCountry());
        m a2 = m.a(LocalMarket.class, (v.a().e() ? "/etsyapps/v3/member/localmarkets/${local_market_id}$?locale=${language_tag}$" : "/etsyapps/v3/public/localmarkets/${local_market_id}$?locale=${language_tag}$").replace("${local_market_id}$", this.mLocalMarketId.getId()).replace("${language_tag}$", sb.toString()));
        a2.a((m.c) new m.c() {
            public void a() {
                if (!LocalMarketFragment.this.isRefreshing()) {
                    LocalMarketFragment.this.showLoadingView();
                }
            }
        });
        a2.a((m.b<Result>) new m.b<LocalMarket>() {
            public void a(k<LocalMarket> kVar) {
                Context applicationContext = BOEApplication.get().getApplicationContext();
                if (kVar != null && kVar.a() && kVar.i() && SharedPreferencesUtility.k(applicationContext)) {
                    com.etsy.android.contentproviders.a.a(applicationContext, (LocalMarket) kVar.g().get(0));
                }
            }
        });
        a2.a((f.c<Result>) new f.c<LocalMarket>() {
            public void a(List<LocalMarket> list, int i, k<LocalMarket> kVar) {
                LocalMarketFragment.this.mLocalMarket = (LocalMarket) list.get(0);
                LocalMarketFragment.this.fillView(LocalMarketFragment.this.mLocalMarket);
                if (LocalMarketFragment.this.getActivity() != null) {
                    LocalMarketFragment.this.getActivity().supportInvalidateOptionsMenu();
                }
            }
        });
        a2.a((f.b) new f.b() {
            public void a(int i, String str, k kVar) {
                LocalMarketFragment.this.onLoadFailure();
            }
        });
        a2.a((com.etsy.android.lib.core.f.a) new com.etsy.android.lib.core.f.a() {
            public void a(k kVar) {
                LocalMarketFragment.this.onLoadFailure();
            }
        });
        getRequestQueue().a((Object) this, (g<Result>) a2.a());
    }

    /* access modifiers changed from: private */
    public void fillView(@NonNull LocalMarket localMarket) {
        getActivity().setTitle(localMarket.getMarketTypeLabelString(getResources()));
        ArrayList arrayList = new ArrayList();
        if (localMarket.isWholesaleStore()) {
            fillWholesaleStoreHero(localMarket.getStore().getImages());
            if (localMarket.getStore().getStockedShops().size() > 0) {
                g gVar = new g(getActivity(), getImageBatch(), this.mListener);
                gVar.a(R.string.recently_featured_etsy_sellers_title);
                gVar.addItems(localMarket.getStore().getStockedShops());
                arrayList.add(gVar);
            }
            if (localMarket.getChildLocalMarkets() != null && localMarket.getChildLocalMarkets().size() > 0) {
                LocalInStoreEventsAdapter localInStoreEventsAdapter = new LocalInStoreEventsAdapter(getActivity(), getImageBatch(), this.mEventListener);
                localInStoreEventsAdapter.addHeader(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
                localInStoreEventsAdapter.addItems(localMarket.getChildLocalMarkets());
                arrayList.add(localInStoreEventsAdapter);
            }
            if (localMarket.getStore().getCategories().size() > 0) {
                f fVar = new f(getActivity(), getImageBatch());
                fVar.addHeader(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
                fVar.addItems(localMarket.getStore().getCategories());
                arrayList.add(fVar);
            }
        } else {
            fillListingHeroRows(localMarket.getAttendeeListingImages());
            if (localMarket.getUpcomingAttendees().size() > 0) {
                a aVar = new a(getActivity(), getImageBatch(), this.mListener);
                aVar.a(R.plurals.etsy_shops_attending);
                aVar.addItems(localMarket.getUpcomingAttendees());
                arrayList.add(aVar);
            }
            if (localMarket.getPastAttendees().size() > 0) {
                a aVar2 = new a(getActivity(), getImageBatch(), this.mListener);
                aVar2.a(R.plurals.etsy_shops_have_attended);
                aVar2.addItems(localMarket.getPastAttendees());
                arrayList.add(aVar2);
            }
        }
        if (this.mAdapter.getHeaderCount() > 0) {
            this.mAdapter.notifyItemChanged(0);
        } else {
            this.mAdapter.addHeader(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
            if (this.mShowLocalBrowseLink) {
                this.mAdapter.addFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR);
            }
            this.mAdapter.addFooter(AbstractContextRecyclerViewAdapter.VIEW_TYPE_FOOTER);
        }
        onLoadSuccess(arrayList, 0);
    }

    private void fillListingHeroRows(@NonNull List<ListingImage> list) {
        int integer = getResources().getInteger(R.integer.local_market_details_listings_per_hero_row);
        int d = (int) ((0.75d * ((double) new l(getActivity()).d())) / ((double) integer));
        com.etsy.android.ui.local.g.a(this.mListingHeroRow1, this.mListingHeroRow2, getImageBatch(), list, false, integer, true);
        setHeroHeightOffset(d * 2);
        ((ImageViewWithAspectRatio) this.mListingHeroRow1.findViewById(R.id.pattern)).setSpecialCrop(CropType.BOTTOM);
        ((ImageViewWithAspectRatio) this.mListingHeroRow2.findViewById(R.id.pattern)).setSpecialCrop(CropType.TOP);
    }

    private void fillWholesaleStoreHero(@NonNull List<LocalStoreImage> list) {
        int d = new l(getActivity()).d() / getResources().getInteger(R.integer.local_market_details_wholesale_hero_ratio);
        if (list.size() > 0) {
            this.mListingHeroRow1.setVisibility(8);
            this.mListingHeroRow2.setVisibility(8);
            this.mStoreHeroImageSwitcher.setVisibility(0);
            setHeightParams(d, this.mStoreHeroImageSwitcher);
            this.mStoreHeroImageSwitcher.setVisibility(0);
            this.mStoreHeroImageSwitcher.startSwitchingBaseModelImages(list, R.anim.fade_in, R.anim.fade_out, SWITCH_IMAGE_SPEED_MILLIS);
        } else {
            this.mStoreHeroImageSwitcher.setVisibility(8);
            com.etsy.android.ui.local.g.a(this.mListingHeroRow1, this.mListingHeroRow2, getImageBatch(), (List<? extends BaseModelImage>) new ArrayList<Object>(), true, getResources().getInteger(R.integer.local_market_details_listings_per_hero_row), true);
        }
        setHeroHeightOffset(d);
    }

    private void setHeightParams(int i, View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    private void setHeroHeightOffset(int i) {
        int dimensionPixelOffset = i - getResources().getDimensionPixelOffset(R.dimen.local_market_details_hero_overlap);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.local_market_details_card_padding);
        this.mRecyclerView.setPadding(dimensionPixelOffset2, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset2);
    }
}
