package com.etsy.android.ui.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.etsy.android.R;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.z;
import com.etsy.android.lib.models.EtsyError;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.CollectionSocialShare;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.CollectionRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.apiv3.SocialShareRequest;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.aj;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionUtil {
    /* access modifiers changed from: private */
    public Context a;
    private Object b;
    private String c;

    public enum ListingCollectionAction {
        ADD,
        REMOVE
    }

    public enum ListingCollectionsBooleanState {
        UNCHANGED,
        IN_COLLECTIONS,
        NOT_IN_COLLECTIONS
    }

    private class a extends i<CollectionSocialShare> {
        f a;
        ProgressDialog c;
        Reference<Activity> d;
        ListingCollectionsBooleanState e;
        private Map<String, ListingCollectionAction> g;
        /* access modifiers changed from: private */
        public EtsyId h;

        public a(Activity activity, EtsyId etsyId, ListingCollectionsBooleanState listingCollectionsBooleanState, Map<String, ListingCollectionAction> map, f fVar) {
            this.h = etsyId;
            this.g = map;
            this.a = fVar;
            this.e = listingCollectionsBooleanState;
            this.d = new WeakReference(activity);
        }

        /* access modifiers changed from: protected */
        public void b_() {
            if (this.g != null && !this.g.isEmpty()) {
                this.c = aj.b((Context) this.d.get(), CollectionUtil.this.a.getString(R.string.collection_updating_progress));
                this.c.show();
                switch (this.e) {
                    case IN_COLLECTIONS:
                        a(true, true);
                        return;
                    case NOT_IN_COLLECTIONS:
                        a(false, true);
                        return;
                    default:
                        return;
                }
            }
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<CollectionSocialShare> a() {
            if (!this.h.hasId() || this.g == null || this.g.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Entry entry : this.g.entrySet()) {
                switch ((ListingCollectionAction) entry.getValue()) {
                    case REMOVE:
                        arrayList2.add(entry.getKey());
                        break;
                    case ADD:
                        arrayList.add(entry.getKey());
                        break;
                }
            }
            return SocialShareRequest.updateListingForCollections(this.h, arrayList, arrayList2);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<CollectionSocialShare> kVar) {
            if (this.c != null) {
                this.c.dismiss();
            }
            if (kVar == null || !kVar.a() || kVar.g().isEmpty()) {
                i();
                return;
            }
            CollectionSocialShare collectionSocialShare = (CollectionSocialShare) kVar.g().get(0);
            if (collectionSocialShare.getFailedCollectionKeys().size() == this.g.size()) {
                i();
            } else if (this.a != null) {
                this.a.onBatchSuccess(collectionSocialShare.shouldShowSocialInvitesPrompt());
            }
        }

        private void i() {
            switch (this.e) {
                case IN_COLLECTIONS:
                    a(false, true);
                    break;
                case NOT_IN_COLLECTIONS:
                    a(true, true);
                    break;
            }
            if (this.a != null) {
                this.a.onBatchError();
            }
        }

        private void a(final boolean z, boolean z2) {
            z.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    com.etsy.android.contentproviders.a.b(CollectionUtil.this.a, a.this.h, z);
                    return null;
                }
            }, new Void[0]);
        }
    }

    private class b extends i<Collection> {
        String a;
        boolean c;
        g d;
        EtsyId e;
        ProgressDialog f;
        Reference<Activity> g;

        public b(Activity activity, String str, boolean z, g gVar, EtsyId etsyId) {
            this.a = str;
            this.c = z;
            this.d = gVar;
            this.e = etsyId;
            this.g = new WeakReference(activity);
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Collection> a() {
            return CollectionRequest.createCollection(this.a, this.c);
        }

        /* access modifiers changed from: protected */
        public void b_() {
            super.b_();
            this.f = aj.b((Context) this.g.get(), CollectionUtil.this.a.getString(R.string.collection_creating_progress));
            this.f.show();
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k kVar) {
            if (this.f != null) {
                this.f.dismiss();
            }
            if (kVar.a() && kVar.g() != null && this.d != null) {
                this.d.onListingCollectionAdded(new ListingCollection((Collection) kVar.g().get(0)));
            } else if (this.d != null) {
                String c2 = kVar.c();
                if (af.a(c2)) {
                    kVar.n();
                    List d2 = kVar.d();
                    if (d2 != null && !d2.isEmpty()) {
                        c2 = ((EtsyError) d2.get(0)).getName();
                    }
                }
                this.d.onListingCollectionError(c2);
            }
        }
    }

    private class c extends i<Collection> {
        String a;
        e c;
        ProgressDialog d;
        Reference<Activity> e;

        public c(Activity activity, String str, e eVar) {
            this.a = str;
            this.c = eVar;
            this.e = new WeakReference(activity);
        }

        /* access modifiers changed from: protected */
        public void b_() {
            this.d = aj.b((Context) this.e.get(), CollectionUtil.this.a.getString(R.string.deleting));
            this.d.show();
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Collection> a() {
            return CollectionRequest.deleteCollection(this.a);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k kVar) {
            if (this.d != null) {
                this.d.dismiss();
            }
            if (kVar.a() && kVar.g() != null && this.c != null) {
                this.c.onCollectionDeleted();
            } else if (this.c != null) {
                String c2 = kVar.c();
                if (af.a(c2)) {
                    kVar.n();
                    List d2 = kVar.d();
                    if (d2 != null && !d2.isEmpty()) {
                        c2 = ((EtsyError) d2.get(0)).getName();
                    }
                }
                this.c.onCollectionError(c2);
            }
        }
    }

    private class d extends i<Collection> {
        String a;
        String c;
        boolean d;
        e e;
        ProgressDialog f;
        Reference<Activity> g;

        public d(Activity activity, String str, String str2, boolean z, e eVar) {
            this.c = str;
            this.a = str2;
            this.d = z;
            this.e = eVar;
            this.g = new WeakReference(activity);
        }

        /* access modifiers changed from: protected */
        public void b_() {
            this.f = aj.b((Context) this.g.get(), CollectionUtil.this.a.getString(R.string.updating));
            this.f.show();
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Collection> a() {
            return CollectionRequest.editCollection(this.c, this.a, this.d);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k kVar) {
            if (this.f != null) {
                this.f.dismiss();
            }
            if (kVar.a() && kVar.g() != null && this.e != null) {
                this.e.onCollectionEdited((Collection) kVar.g().get(0));
            } else if (this.e != null) {
                String c2 = kVar.c();
                if (af.a(c2)) {
                    kVar.n();
                    List d2 = kVar.d();
                    if (d2 != null && !d2.isEmpty()) {
                        c2 = ((EtsyError) d2.get(0)).getName();
                    }
                }
                this.e.onCollectionError(c2);
            }
        }
    }

    public interface e {
        void onCollectionDeleted();

        void onCollectionEdited(Collection collection);

        void onCollectionError(String str);
    }

    public interface f {
        void onBatchError();

        void onBatchSuccess(boolean z);
    }

    public interface g {
        void onListingCollectionAdded(ListingCollection listingCollection);

        void onListingCollectionError(String str);
    }

    public interface h {
        void onAddListingCollection();
    }

    public CollectionUtil(Context context, Object obj, String str) {
        this.a = context.getApplicationContext();
        this.b = obj;
        this.c = str;
    }

    public void a(Activity activity, EtsyId etsyId, ListingCollectionsBooleanState listingCollectionsBooleanState, Map<String, ListingCollectionAction> map, f fVar) {
        a aVar = new a(activity, etsyId, listingCollectionsBooleanState, map, fVar);
        v.a().j().a(this.b, (com.etsy.android.lib.core.g<Result>) aVar);
    }

    public void a(Activity activity, g gVar, String str, boolean z, EtsyId etsyId) {
        b bVar = new b(activity, str, z, gVar, etsyId);
        v.a().j().a(this.b, (com.etsy.android.lib.core.g<Result>) bVar);
    }

    public void a(Activity activity, e eVar, String str, String str2, boolean z) {
        d dVar = new d(activity, str, str2, z, eVar);
        v.a().j().a(this.b, (com.etsy.android.lib.core.g<Result>) dVar);
    }

    public void a(Activity activity, e eVar, String str) {
        v.a().j().a(this.b, (com.etsy.android.lib.core.g<Result>) new c<Result>(activity, str, eVar));
    }
}
