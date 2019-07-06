package com.etsy.android.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.z;
import com.etsy.android.lib.logger.g;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.SocialShare;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.BasicShopLike;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.FavoriteListingsRequest;
import com.etsy.android.lib.requests.FavoriteUsersRequest;
import com.etsy.android.lib.requests.apiv3.SocialShareRequest;
import com.etsy.android.uikit.util.AnimationUtil;
import java.lang.ref.WeakReference;

/* compiled from: FavoriteUtil */
public class e {
    /* access modifiers changed from: private */
    public Context a;
    /* access modifiers changed from: private */
    public WeakReference<FragmentActivity> b;
    /* access modifiers changed from: private */
    @NonNull
    public final com.etsy.android.lib.logger.b c;
    private Object d;

    /* compiled from: FavoriteUtil */
    private class a extends d {
        /* access modifiers changed from: private */
        public EtsyId f;
        private ListingLike g;

        public a(b bVar, boolean z, ListingLike listingLike, boolean z2) {
            super(bVar, z, (BaseModel) listingLike, z2);
            this.g = listingLike;
            this.f = listingLike.getListingId();
        }

        /* access modifiers changed from: protected */
        public void b_() {
            boolean z = !this.d;
            a(z, false);
            Intent intent = new Intent();
            intent.setAction(EtsyAction.STATE_CHANGE.getAction());
            intent.putExtra("id", this.f.toString());
            intent.putExtra(EtsyAction.STATE_FAVORITE, z);
            LocalBroadcastManager.getInstance(e.this.a).sendBroadcast(intent);
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<? extends BaseModel> a() {
            if (this.d) {
                return FavoriteListingsRequest.deleteUserFavoriteListings("__SELF__", this.f);
            }
            SocialShareRequest favoriteListings = SocialShareRequest.favoriteListings(this.f);
            g.b(this.f);
            return favoriteListings;
        }

        /* access modifiers changed from: protected */
        public void b(k kVar) {
            if (!kVar.a()) {
                if (this.g != null) {
                    this.g.setIsFavorite(this.d);
                }
                a(this.d, true);
            } else if (this.g != null) {
                this.g.setIsFavorite(true ^ this.d);
            }
            super.a(kVar);
        }

        private void a(final boolean z, boolean z2) {
            z.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    com.etsy.android.contentproviders.a.a(e.this.a, a.this.f, z);
                    return null;
                }
            }, new Void[0]);
        }
    }

    /* compiled from: FavoriteUtil */
    public interface b {
        void a();

        void b();
    }

    /* compiled from: FavoriteUtil */
    private class c extends d {
        /* access modifiers changed from: private */
        public EtsyId f;

        public c(b bVar, boolean z, EtsyId etsyId) {
            super(e.this, bVar, z);
            this.f = etsyId;
        }

        public c(b bVar, boolean z, BasicShopLike basicShopLike) {
            super(e.this, bVar, z, (BaseModel) basicShopLike);
            this.f = basicShopLike.getUserId();
        }

        /* access modifiers changed from: protected */
        public void b_() {
            boolean z = !this.d;
            Intent intent = new Intent();
            intent.setAction(EtsyAction.STATE_CHANGE.getAction());
            intent.putExtra("id", this.f.toString());
            intent.putExtra(EtsyAction.STATE_FAVORITE, z);
            LocalBroadcastManager.getInstance(e.this.a).sendBroadcast(intent);
            a(z, false);
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<? extends BaseModel> a() {
            if (this.d) {
                return FavoriteUsersRequest.deleteUserFavoriteUsers(this.f);
            }
            return SocialShareRequest.favoriteShop(this.f);
        }

        /* access modifiers changed from: protected */
        public void b(k kVar) {
            if (!kVar.a()) {
                a(this.d, true);
            }
            super.a(kVar);
        }

        private void a(final boolean z, boolean z2) {
            z.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    com.etsy.android.contentproviders.a.c(e.this.a, c.this.f, z);
                    return null;
                }
            }, new Void[0]);
        }
    }

    /* compiled from: FavoriteUtil */
    private abstract class d<T extends BaseModel> extends i<T> {
        private b a;
        protected final BaseModel c;
        protected boolean d;
        private boolean f;

        public d(b bVar, boolean z, BaseModel baseModel, boolean z2) {
            this.a = bVar;
            this.d = z;
            this.c = baseModel;
            this.f = z2;
        }

        public d(e eVar, b bVar, boolean z, BaseModel baseModel) {
            this(bVar, z, baseModel, true);
        }

        public d(e eVar, b bVar, boolean z) {
            this(bVar, z, null, true);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k kVar) {
            if (kVar.a()) {
                if (this.f && !this.d && this.c != null && !kVar.g().isEmpty() && (kVar.g().get(0) instanceof SocialShare) && ((SocialShare) kVar.g().get(0)).shouldShowSocialInvitesPrompt()) {
                    FragmentActivity fragmentActivity = (FragmentActivity) e.this.b.get();
                    if (fragmentActivity != null) {
                        d.a(fragmentActivity, e.this.c, this.c, false);
                    }
                }
                this.d = !this.d;
            } else if (com.etsy.android.util.d.b()) {
                Toast.makeText(e.this.a, kVar.c().toString(), 1).show();
            }
            if (this.a == null) {
                return;
            }
            if (this.d) {
                this.a.a();
            } else {
                this.a.b();
            }
        }
    }

    public e(Context context, Object obj, @NonNull com.etsy.android.lib.logger.b bVar) {
        this.a = context.getApplicationContext();
        this.c = bVar;
        this.d = obj;
        if (context instanceof FragmentActivity) {
            this.b = new WeakReference<>((FragmentActivity) context);
        }
    }

    public void a(ImageView imageView, int i, int i2, boolean z) {
        if (!z) {
            AnimationUtil.a(imageView, i2);
        } else {
            imageView.setImageResource(i);
        }
    }

    public void a(ListingLike listingLike, b bVar, boolean z) {
        a aVar = new a(bVar, z, listingLike, true);
        v.a().j().a(this.d, (com.etsy.android.lib.core.g<Result>) aVar);
    }

    public void b(ListingLike listingLike, b bVar, boolean z) {
        a aVar = new a(bVar, z, listingLike, !com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.aX));
        v.a().j().a(this.d, (com.etsy.android.lib.core.g<Result>) aVar);
    }

    public void a(BasicShopLike basicShopLike, b bVar, boolean z) {
        v.a().j().a(this.d, (com.etsy.android.lib.core.g<Result>) new c<Result>(bVar, z, basicShopLike));
    }

    public void a(EtsyId etsyId, b bVar, boolean z) {
        v.a().j().a(this.d, (com.etsy.android.lib.core.g<Result>) new c<Result>(bVar, z, etsyId));
    }

    public void a(final FragmentActivity fragmentActivity, View view, final ListingLike listingLike) {
        Snackbar make = Snackbar.make(view, (int) R.string.saved_to_favorites, 0);
        make.setAction((int) R.string.add_to_list, (OnClickListener) new OnClickListener() {
            boolean a = false;

            public void onClick(View view) {
                if (!this.a) {
                    this.a = true;
                    com.etsy.android.ui.nav.e.a((Activity) fragmentActivity).a(601).a(listingLike);
                }
            }
        });
        View view2 = make.getView();
        view2.setBackgroundColor(view2.getResources().getColor(R.color.sk_orange_30));
        ((TextView) view2.findViewById(R.id.snackbar_text)).setTextColor(-1);
        make.setActionTextColor(-1);
        make.show();
    }
}
