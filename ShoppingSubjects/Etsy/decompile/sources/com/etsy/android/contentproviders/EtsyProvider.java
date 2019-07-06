package com.etsy.android.contentproviders;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.SearchRecentSuggestionsProvider;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.ArrayList;

public class EtsyProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.etsy.android.contentproviders.EtsyProvider";
    /* access modifiers changed from: private */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://com.etsy.android.contentproviders.EtsyProvider");
    public static final int MODE = 1;
    private static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.etsy.android.contentproviders.EtsyProvider.item";
    private static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.etsy.android.contentproviders.EtsyProvider.item";
    private static final String TAG = f.a(EtsyProvider.class);
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private EtsyDatabase mDb;

    public static class a {
        public static final Uri a = EtsyProvider.BASE_CONTENT_URI.buildUpon().appendPath("listing").build();

        public static Uri a(EtsyId etsyId) {
            return a.buildUpon().appendPath(etsyId.getId()).build();
        }
    }

    public static class b {
        public static final Uri a = EtsyProvider.BASE_CONTENT_URI.buildUpon().appendPath("history").appendPath(ResponseConstants.LOCAL_MARKET).build();
    }

    public static class c {
        public static final Uri a = EtsyProvider.BASE_CONTENT_URI.buildUpon().appendPath(ResponseConstants.SUGGESTIONS).build();
    }

    public static class d {
        public static final Uri a = EtsyProvider.BASE_CONTENT_URI.buildUpon().appendPath(ResponseConstants.SHOP).build();

        public static Uri a(EtsyId etsyId) {
            return a.buildUpon().appendPath("user").appendPath(etsyId.getId()).build();
        }
    }

    public static class e {
        public static final Uri a = EtsyProvider.BASE_CONTENT_URI.buildUpon().appendPath("user").build();
    }

    public boolean onCreate() {
        setupSuggestions(AUTHORITY, 1);
        super.onCreate();
        this.mDb = new EtsyDatabase(getContext());
        return true;
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(AUTHORITY, "history/local_market", 10);
        uriMatcher.addURI(AUTHORITY, "search_suggest_query", 2);
        uriMatcher.addURI(AUTHORITY, ResponseConstants.SUGGESTIONS, 2);
        uriMatcher.addURI(AUTHORITY, "listing", 3);
        uriMatcher.addURI(AUTHORITY, "listing/*", 3);
        uriMatcher.addURI(AUTHORITY, ResponseConstants.SHOP, 5);
        uriMatcher.addURI(AUTHORITY, "shop/user/*", 5);
        uriMatcher.addURI(AUTHORITY, "user", 6);
        return uriMatcher;
    }

    private static int getMatch(Uri uri) {
        return sUriMatcher.match(uri);
    }

    public String getType(Uri uri) {
        switch (getMatch(uri)) {
            case 2:
            case 10:
                return MULTIPLE_RECORDS_MIME_TYPE;
            case 3:
            case 5:
            case 6:
                return SINGLE_RECORD_MIME_TYPE;
            default:
                return MULTIPLE_RECORDS_MIME_TYPE;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x008c A[Catch:{ Exception -> 0x009e, all -> 0x009b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor query(android.net.Uri r14, java.lang.String[] r15, java.lang.String r16, java.lang.String[] r17, java.lang.String r18) {
        /*
            r13 = this;
            r1 = r14
            r6 = r17
            java.lang.String r2 = TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "query - uri:"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = " projection:"
            r3.append(r4)
            java.lang.String r4 = java.util.Arrays.toString(r15)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.etsy.android.lib.logger.f.c(r2, r3)
            int r2 = getMatch(r1)
            r10 = r13
            com.etsy.android.contentproviders.EtsyDatabase r3 = r10.mDb
            android.database.sqlite.SQLiteDatabase r11 = r3.getReadableDatabase()
            r12 = 0
            if (r11 == 0) goto L_0x00af
            r11.beginTransaction()
            android.database.sqlite.SQLiteQueryBuilder r3 = new android.database.sqlite.SQLiteQueryBuilder     // Catch:{ Exception -> 0x009e }
            r3.<init>()     // Catch:{ Exception -> 0x009e }
            r4 = 2
            if (r2 == r4) goto L_0x006c
            r4 = 10
            if (r2 == r4) goto L_0x0059
            java.lang.String r2 = TAG     // Catch:{ Exception -> 0x009e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e }
            r3.<init>()     // Catch:{ Exception -> 0x009e }
            java.lang.String r4 = "query() - UNKNOWN MATCH FOR URI"
            r3.append(r4)     // Catch:{ Exception -> 0x009e }
            r3.append(r1)     // Catch:{ Exception -> 0x009e }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x009e }
            com.etsy.android.lib.logger.f.d(r2, r3)     // Catch:{ Exception -> 0x009e }
            goto L_0x008a
        L_0x0059:
            java.lang.String r2 = "local_market_history"
            r3.setTables(r2)     // Catch:{ Exception -> 0x009e }
            r7 = 0
            r8 = 0
            r2 = r3
            r3 = r11
            r4 = r15
            r5 = r16
            r9 = r18
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x009e }
            goto L_0x0089
        L_0x006c:
            if (r6 == 0) goto L_0x0085
            int r2 = r6.length     // Catch:{ Exception -> 0x009e }
            if (r2 <= 0) goto L_0x0085
            r2 = 0
            r3 = r6[r2]     // Catch:{ Exception -> 0x009e }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x009e }
            if (r3 != 0) goto L_0x0085
            android.content.Context r3 = r10.getContext()     // Catch:{ Exception -> 0x009e }
            r2 = r6[r2]     // Catch:{ Exception -> 0x009e }
            android.database.Cursor r2 = com.etsy.android.contentproviders.b.a(r3, r2)     // Catch:{ Exception -> 0x009e }
            goto L_0x0089
        L_0x0085:
            android.database.Cursor r2 = super.query(r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x009e }
        L_0x0089:
            r12 = r2
        L_0x008a:
            if (r12 == 0) goto L_0x0097
            android.content.Context r2 = r10.getContext()     // Catch:{ Exception -> 0x009e }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Exception -> 0x009e }
            r12.setNotificationUri(r2, r1)     // Catch:{ Exception -> 0x009e }
        L_0x0097:
            r11.setTransactionSuccessful()     // Catch:{ Exception -> 0x009e }
            goto L_0x00a7
        L_0x009b:
            r0 = move-exception
            r1 = r0
            goto L_0x00ab
        L_0x009e:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = TAG     // Catch:{ all -> 0x009b }
            java.lang.String r3 = "query error"
            com.etsy.android.lib.logger.f.e(r2, r3, r1)     // Catch:{ all -> 0x009b }
        L_0x00a7:
            r11.endTransaction()
            goto L_0x00af
        L_0x00ab:
            r11.endTransaction()
            throw r1
        L_0x00af:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.contentproviders.EtsyProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x006d, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0087, code lost:
        getContext().getContentResolver().notifyChange(r5, null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri insert(android.net.Uri r5, android.content.ContentValues r6) {
        /*
            r4 = this;
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "insert - uri:"
            r1.append(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            com.etsy.android.lib.logger.f.c(r0, r1)
            int r0 = getMatch(r5)
            com.etsy.android.contentproviders.EtsyDatabase r1 = r4.mDb
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()
            if (r1 == 0) goto L_0x009b
            r2 = 5
            r3 = 0
            switch(r0) {
                case 2: goto L_0x006f;
                case 3: goto L_0x005d;
                case 4: goto L_0x0027;
                case 5: goto L_0x004c;
                case 6: goto L_0x003b;
                case 7: goto L_0x0027;
                case 8: goto L_0x0027;
                case 9: goto L_0x0027;
                case 10: goto L_0x002a;
                default: goto L_0x0027;
            }
        L_0x0027:
            java.lang.String r6 = TAG     // Catch:{ Exception -> 0x0093 }
            goto L_0x0073
        L_0x002a:
            java.lang.String r0 = "local_market_history"
            long r0 = r1.insertWithOnConflict(r0, r3, r6, r2)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = com.etsy.android.contentproviders.EtsyProvider.b.a     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = android.net.Uri.withAppendedPath(r6, r0)     // Catch:{ Exception -> 0x0093 }
            goto L_0x006d
        L_0x003b:
            java.lang.String r0 = "user"
            long r0 = r1.insertWithOnConflict(r0, r3, r6, r2)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = com.etsy.android.contentproviders.EtsyProvider.e.a     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = android.net.Uri.withAppendedPath(r6, r0)     // Catch:{ Exception -> 0x0093 }
            goto L_0x006d
        L_0x004c:
            java.lang.String r0 = "shop"
            long r0 = r1.insertWithOnConflict(r0, r3, r6, r2)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = com.etsy.android.contentproviders.EtsyProvider.d.a     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = android.net.Uri.withAppendedPath(r6, r0)     // Catch:{ Exception -> 0x0093 }
            goto L_0x006d
        L_0x005d:
            java.lang.String r0 = "listing"
            long r0 = r1.insertWithOnConflict(r0, r3, r6, r2)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = com.etsy.android.contentproviders.EtsyProvider.a.a     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0093 }
            android.net.Uri r6 = android.net.Uri.withAppendedPath(r6, r0)     // Catch:{ Exception -> 0x0093 }
        L_0x006d:
            r5 = r6
            goto L_0x0087
        L_0x006f:
            super.insert(r5, r6)     // Catch:{ Exception -> 0x0093 }
            goto L_0x0087
        L_0x0073:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0093 }
            r0.<init>()     // Catch:{ Exception -> 0x0093 }
            java.lang.String r1 = "insert() - UNKNOWN MATCH FOR URI"
            r0.append(r1)     // Catch:{ Exception -> 0x0093 }
            r0.append(r5)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0093 }
            com.etsy.android.lib.logger.f.d(r6, r0)     // Catch:{ Exception -> 0x0093 }
        L_0x0087:
            android.content.Context r6 = r4.getContext()     // Catch:{ Exception -> 0x0093 }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ Exception -> 0x0093 }
            r6.notifyChange(r5, r3)     // Catch:{ Exception -> 0x0093 }
            goto L_0x009b
        L_0x0093:
            r6 = move-exception
            java.lang.String r0 = TAG
            java.lang.String r1 = "insert error"
            com.etsy.android.lib.logger.f.e(r0, r1, r6)
        L_0x009b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.contentproviders.EtsyProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("delete - uri:");
        sb.append(uri);
        f.c(str2, sb.toString());
        int match = getMatch(uri);
        SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
        int i = -1;
        if (writableDatabase == null) {
            return -1;
        }
        if (match == 2) {
            super.delete(uri, str, strArr);
            return -1;
        } else if (match != 10) {
            try {
                String str3 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("delete() - UNKNOWN MATCH FOR URI");
                sb2.append(uri);
                f.d(str3, sb2.toString());
                return -1;
            } catch (Exception e2) {
                e = e2;
            }
        } else {
            int delete = writableDatabase.delete("local_market_history", str, strArr);
            try {
                getContext().getContentResolver().notifyChange(uri, null);
                return delete;
            } catch (Exception e3) {
                e = e3;
                i = delete;
                f.e(TAG, "delete error", e);
                return i;
            }
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i;
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("update - uri:");
        sb.append(uri);
        f.c(str2, sb.toString());
        int match = getMatch(uri);
        SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
        int i2 = -1;
        if (writableDatabase == null) {
            return -1;
        }
        if (match != 3) {
            switch (match) {
                case 5:
                    i = writableDatabase.update(ResponseConstants.SHOP, contentValues, str, strArr);
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                case 6:
                    i = writableDatabase.update("user", contentValues, str, strArr);
                    try {
                        getContext().getContentResolver().notifyChange(uri, null);
                        break;
                    } catch (Exception e2) {
                        e = e2;
                        i2 = i;
                        break;
                    }
                default:
                    try {
                        String str3 = TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("update() - UNKNOWN MATCH FOR URI");
                        sb2.append(uri);
                        f.d(str3, sb2.toString());
                        return -1;
                    } catch (Exception e3) {
                        e = e3;
                        f.e(TAG, "update error", e);
                        return i2;
                    }
            }
        } else {
            i = writableDatabase.update("listing", contentValues, str, strArr);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return i;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        f.c(TAG, "applyBatch");
        SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
        if (writableDatabase != null) {
            writableDatabase.beginTransaction();
            try {
                int size = arrayList.size();
                ContentProviderResult[] contentProviderResultArr = new ContentProviderResult[size];
                for (int i = 0; i < size; i++) {
                    contentProviderResultArr[i] = ((ContentProviderOperation) arrayList.get(i)).apply(this, contentProviderResultArr, i);
                }
                writableDatabase.setTransactionSuccessful();
                return contentProviderResultArr;
            } catch (Exception e2) {
                f.e(TAG, "applyBatch error", e2);
            } finally {
                writableDatabase.endTransaction();
            }
        }
        return new ContentProviderResult[0];
    }
}
