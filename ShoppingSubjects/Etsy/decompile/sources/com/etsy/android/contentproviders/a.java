package com.etsy.android.contentproviders;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.provider.SearchRecentSuggestions;
import com.etsy.android.contentproviders.EtsyProvider.b;
import com.etsy.android.contentproviders.EtsyProvider.c;
import com.etsy.android.contentproviders.EtsyProvider.d;
import com.etsy.android.contentproviders.EtsyProvider.e;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.ArrayList;

/* compiled from: EtsyDatabaseUtil */
public class a {
    private static final String a = f.a(a.class);

    public static void a(Context context, LocalMarket localMarket) {
        if (localMarket != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ContentProviderOperation.newInsert(b.a).withValue(ResponseConstants.LOCAL_MARKET_ID, localMarket.getLocalMarketId().getId()).withValue("view_time", Long.valueOf(System.currentTimeMillis())).build());
            a(context, arrayList);
        }
    }

    public static void a(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                a.f(context);
                return null;
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public static void f(Context context) {
        context.getContentResolver().delete(b.a, null, null);
    }

    public static void a(Context context, EtsyId etsyId, boolean z) {
        f.c(a, "updateListingFavoriteState listingId:%s isFavorite:%b", etsyId.getId(), Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", Boolean.valueOf(z));
        String[] strArr = {etsyId.getId()};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(com.etsy.android.contentproviders.EtsyProvider.a.a(etsyId)).withValues(contentValues).withSelection("listing_id = ?", strArr).build());
        a(context, arrayList);
    }

    public static void b(Context context, EtsyId etsyId, boolean z) {
        f.c(a, "updateListingCollectionState listingId:%s isInCollection:%b", etsyId.getId(), Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("in_collection", Boolean.valueOf(z));
        String[] strArr = {etsyId.getId()};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(com.etsy.android.contentproviders.EtsyProvider.a.a(etsyId)).withValues(contentValues).withSelection("listing_id = ?", strArr).build());
        a(context, arrayList);
    }

    public static void c(Context context, EtsyId etsyId, boolean z) {
        f.c(a, "updateShopFavoriteState shopUserId:%s isFavorite:%b", etsyId, Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", Boolean.valueOf(z));
        String[] strArr = {String.valueOf(etsyId)};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(d.a(etsyId)).withValues(contentValues).withSelection("user_id = ?", strArr).build());
        a(context, arrayList);
    }

    public static void d(Context context, EtsyId etsyId, boolean z) {
        f.c(a, "updateUserFollowState userId:%s isFollowed:%b", String.valueOf(etsyId), Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("followed", Boolean.valueOf(z));
        String[] strArr = {String.valueOf(etsyId)};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(e.a).withValues(contentValues).withSelection("user_id = ?", strArr).build());
        a(context, arrayList);
    }

    public static int b(Context context) {
        Cursor query = context.getContentResolver().query(c.a, null, null, null, null);
        int count = query.getCount();
        query.close();
        return count;
    }

    public static void a(Context context, String str) {
        new SearchRecentSuggestions(context, EtsyProvider.AUTHORITY, 1).saveRecentQuery(str, null);
    }

    public static void c(Context context) {
        new SearchRecentSuggestions(context, EtsyProvider.AUTHORITY, 1).clearHistory();
    }

    public static void d(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                a.f(context);
                a.c(context);
                com.etsy.android.lib.convos.contentprovider.b.c(context);
                return null;
            }
        }.execute(new Void[0]);
    }

    private static void a(Context context, ArrayList<ContentProviderOperation> arrayList) {
        try {
            context.getContentResolver().applyBatch(EtsyProvider.AUTHORITY, arrayList);
        } catch (RemoteException e) {
            f.e(a, "RemoteException on applyBatch", e);
        } catch (OperationApplicationException e2) {
            f.e(a, "OperationApplicationException on applyBatch", e2);
        }
    }
}
