package com.etsy.android.ui.cardview.clickhandlers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.core.posts.PersistentRequest;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.vespa.UserAction;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.requests.SearchAdsLogRequest;
import com.etsy.android.ui.util.e;
import com.etsy.android.uikit.viewholder.a.a;
import com.etsy.android.vespa.VespaBottomSheetDialog;
import com.etsy.android.vespa.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ListingCardClickHandler */
public class f extends a {
    /* access modifiers changed from: private */
    public e a;
    /* access modifiers changed from: private */
    public d b;

    public f(FragmentActivity fragmentActivity, d dVar, @NonNull b bVar) {
        super(fragmentActivity, bVar);
        this.a = new e(d(), null, bVar);
        this.b = dVar;
    }

    public void a(ListingLike listingLike) {
        HashMap hashMap = new HashMap();
        a(hashMap, listingLike);
        b c = c();
        StringBuilder sb = new StringBuilder();
        sb.append(c().b());
        sb.append("_tapped_listing");
        c.a(sb.toString(), hashMap);
        if (listingLike.isAd()) {
            v.a().k().a((PersistentRequest<Request, Result>) SearchAdsLogRequest.logSearchAdsClick(listingLike));
        }
        com.etsy.android.ui.nav.e.a(d()).a().a(listingLike.getListingId());
    }

    public void a(final ListingLike listingLike, final ImageView imageView, final int i) {
        if (v.a().l().equals(listingLike.getShopId())) {
            Toast.makeText(d(), R.string.favorite_own_item_message, 0).show();
            return;
        }
        EtsyId listingId = listingLike.getListingId();
        if (!v.a().e()) {
            ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) d()).a((View) imageView)).a(EtsyAction.FAVORITE, listingId.getId());
        } else {
            this.a.a(imageView, R.drawable.ic_favorite_selector, R.drawable.ic_favorited_selector, listingLike.isFavorite());
            this.a.b(listingLike, new e.b() {
                public void a() {
                    listingLike.setIsFavorite(true);
                    f.this.b.onItemChanged(i);
                    if (com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.aX)) {
                        f.this.a.a(f.this.d(), (View) imageView, listingLike);
                    }
                    f.this.a(listingLike, "favorite_item");
                }

                public void b() {
                    listingLike.setIsFavorite(false);
                    f.this.b.onItemChanged(i);
                }
            }, listingLike.isFavorite());
        }
    }

    /* access modifiers changed from: private */
    public void a(ListingLike listingLike, String str) {
        HashMap hashMap = new HashMap();
        a(hashMap, listingLike);
        c().a(str, hashMap);
    }

    public void b(ListingLike listingLike) {
        if (listingLike != null) {
            a(listingLike, "listing_card_action_add_to_list");
            if (!v.a().e()) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("listing", listingLike);
                com.etsy.android.ui.nav.e.a((Activity) d()).a(EtsyAction.MANAGE_ITEM_COLLECTIONS, bundle);
            } else {
                com.etsy.android.ui.nav.e.a((Activity) d()).a(601).a(listingLike);
            }
        }
    }

    /* renamed from: c */
    public void a(ListingLike listingLike) {
        a(listingLike);
    }

    public void a(HashMap<AnalyticsLogAttribute, Object> hashMap, ListingLike listingLike) {
        hashMap.put(AnalyticsLogAttribute.LISTING_ID, listingLike.getListingId().getId());
        if (listingLike instanceof ListingCard) {
            String contentSource = ((ListingCard) listingLike).getContentSource();
            if (!TextUtils.isEmpty(contentSource)) {
                hashMap.put(AnalyticsLogAttribute.CONTENT_SOURCE, contentSource);
            }
        }
    }

    public void d(final ListingLike listingLike) {
        VespaBottomSheetDialog vespaBottomSheetDialog = new VespaBottomSheetDialog(d(), c());
        vespaBottomSheetDialog.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                f.this.a(listingLike, "listing_card_action_cancel");
            }
        });
        ArrayList arrayList = new ArrayList();
        Resources resources = d().getResources();
        UserAction userAction = new UserAction(UserAction.TYPE_ADD_TO_LIST, resources.getString(v.a().e() && listingLike.hasCollections() ? R.string.manage_lists : R.string.add_to_list), resources.getResourceEntryName(R.drawable.sk_ic_list));
        userAction.setViewType(R.id.view_type_bottom_sheet_list_item);
        arrayList.add(userAction);
        UserAction userAction2 = new UserAction(UserAction.TYPE_SEE_SIMILAR, resources.getString(R.string.see_similar_items), resources.getResourceEntryName(R.drawable.sk_ic_addimages));
        userAction2.setViewType(R.id.view_type_bottom_sheet_list_item);
        arrayList.add(userAction2);
        UserAction userAction3 = new UserAction(UserAction.TYPE_GOTO_SHOP, resources.getString(R.string.visit_shop), resources.getResourceEntryName(R.drawable.sk_ic_shop));
        userAction3.setViewType(R.id.view_type_bottom_sheet_list_item);
        arrayList.add(userAction3);
        UserAction userAction4 = new UserAction("share", resources.getString(R.string.share), resources.getResourceEntryName(R.drawable.sk_ic_androidshare));
        userAction4.setViewType(R.id.view_type_bottom_sheet_list_item);
        arrayList.add(userAction4);
        vespaBottomSheetDialog.addItems(arrayList);
        int viewType = ((UserAction) arrayList.get(0)).getViewType();
        final ListingLike listingLike2 = listingLike;
        final VespaBottomSheetDialog vespaBottomSheetDialog2 = vespaBottomSheetDialog;
        AnonymousClass3 r0 = new com.etsy.android.vespa.b<UserAction>(d(), c()) {
            /* JADX WARNING: Removed duplicated region for block: B:22:0x004a  */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0052  */
            /* JADX WARNING: Removed duplicated region for block: B:24:0x005a  */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x0062  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(com.etsy.android.lib.models.apiv3.vespa.UserAction r3) {
                /*
                    r2 = this;
                    java.lang.String r3 = r3.getType()
                    int r0 = r3.hashCode()
                    r1 = -871352833(0xffffffffcc1035ff, float:-3.7804028E7)
                    if (r0 == r1) goto L_0x003b
                    r1 = 109400031(0x6854fdf, float:5.01464E-35)
                    if (r0 == r1) goto L_0x0031
                    r1 = 164437572(0x9cd1e44, float:4.9380416E-33)
                    if (r0 == r1) goto L_0x0027
                    r1 = 1834514514(0x6d587852, float:4.1871388E27)
                    if (r0 == r1) goto L_0x001d
                    goto L_0x0045
                L_0x001d:
                    java.lang.String r0 = "goto_shop"
                    boolean r3 = r3.equals(r0)
                    if (r3 == 0) goto L_0x0045
                    r3 = 2
                    goto L_0x0046
                L_0x0027:
                    java.lang.String r0 = "add_to_list"
                    boolean r3 = r3.equals(r0)
                    if (r3 == 0) goto L_0x0045
                    r3 = 1
                    goto L_0x0046
                L_0x0031:
                    java.lang.String r0 = "share"
                    boolean r3 = r3.equals(r0)
                    if (r3 == 0) goto L_0x0045
                    r3 = 0
                    goto L_0x0046
                L_0x003b:
                    java.lang.String r0 = "see_similar"
                    boolean r3 = r3.equals(r0)
                    if (r3 == 0) goto L_0x0045
                    r3 = 3
                    goto L_0x0046
                L_0x0045:
                    r3 = -1
                L_0x0046:
                    switch(r3) {
                        case 0: goto L_0x0062;
                        case 1: goto L_0x005a;
                        case 2: goto L_0x0052;
                        case 3: goto L_0x004a;
                        default: goto L_0x0049;
                    }
                L_0x0049:
                    goto L_0x0069
                L_0x004a:
                    com.etsy.android.ui.cardview.clickhandlers.f r3 = com.etsy.android.ui.cardview.clickhandlers.f.this
                    com.etsy.android.lib.models.interfaces.ListingLike r0 = r4
                    r3.e(r0)
                    goto L_0x0069
                L_0x0052:
                    com.etsy.android.ui.cardview.clickhandlers.f r3 = com.etsy.android.ui.cardview.clickhandlers.f.this
                    com.etsy.android.lib.models.interfaces.ListingLike r0 = r4
                    r3.g(r0)
                    goto L_0x0069
                L_0x005a:
                    com.etsy.android.ui.cardview.clickhandlers.f r3 = com.etsy.android.ui.cardview.clickhandlers.f.this
                    com.etsy.android.lib.models.interfaces.ListingLike r0 = r4
                    r3.b(r0)
                    goto L_0x0069
                L_0x0062:
                    com.etsy.android.ui.cardview.clickhandlers.f r3 = com.etsy.android.ui.cardview.clickhandlers.f.this
                    com.etsy.android.lib.models.interfaces.ListingLike r0 = r4
                    r3.f(r0)
                L_0x0069:
                    com.etsy.android.vespa.VespaBottomSheetDialog r3 = r5
                    r3.dismiss()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.cardview.clickhandlers.f.AnonymousClass3.a(com.etsy.android.lib.models.apiv3.vespa.UserAction):void");
            }
        };
        vespaBottomSheetDialog.registerItemClickHandler(viewType, r0);
        vespaBottomSheetDialog.show();
    }

    /* access modifiers changed from: private */
    public void g(ListingLike listingLike) {
        a(listingLike, "listing_card_action_goto_shop");
        com.etsy.android.ui.nav.e.a(d()).a().b(listingLike.getShopId());
    }

    public void e(ListingLike listingLike) {
        a(listingLike, "listing_card_action_see_similar");
        com.etsy.android.ui.nav.e.a(d()).a().d(listingLike.getListingId().getId());
    }

    public void f(ListingLike listingLike) {
        a(listingLike, "listing_card_action_share");
        String str = "";
        if (listingLike.getListingImage() != null) {
            str = listingLike.getListingImage().getUrl570xN();
        }
        Resources resources = d().getResources();
        StringBuilder sb = new StringBuilder();
        sb.append(resources.getString(R.string.share_listing_message));
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(listingLike.getUrl());
        com.etsy.android.ui.nav.e.a(d()).a().a(resources.getString(R.string.share_listing_subject), sb.toString(), listingLike.getUrl(), str);
    }
}
