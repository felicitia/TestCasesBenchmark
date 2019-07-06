package com.etsy.android.ui.core.listingpanel;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.TranslatedReview;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.apiv3.TranslatedReviewRequest;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.t;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.adapter.ReviewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.etsy.android.uikit.view.MachineTranslationOneClickView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.uikit.viewholder.ReviewViewHolder;
import com.etsy.android.uikit.viewholder.ReviewViewHolder.a;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ListingPanelFeedback */
public class f extends d implements a {
    protected c j;
    private TextView k;
    private RatingIconView l;
    /* access modifiers changed from: private */
    public ReviewAdapter m;
    /* access modifiers changed from: private */
    public ArrayList<ReviewViewHolder> n = new ArrayList<>();

    public void onContactBuyerClicked(int i) {
    }

    public void onShopOwnerClicked(int i) {
    }

    public void onUnhidePhotoClicked(int i) {
    }

    public f(Listing listing, BaseActivity baseActivity, @NonNull w wVar) {
        super(listing, baseActivity, wVar);
        a(R.id.panel_details_feedback, R.id.panel_title_feedback, R.id.img_feedback_open, R.id.panel_title_feedback);
    }

    public void a(c cVar) {
        this.j = cVar;
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (l.c((Activity) this.a)) {
            this.c.findViewById(R.id.txt_feedback_title).setVisibility(8);
        }
        t();
        s();
    }

    private void t() {
        this.l = (RatingIconView) this.c.findViewById(R.id.rating);
        if (l.c((Activity) this.a)) {
            this.c.findViewById(R.id.txt_feedback_title).setVisibility(8);
            this.k = (TextView) this.c.findViewById(R.id.txt_feedback_title_large);
            return;
        }
        this.k = (TextView) this.c.findViewById(R.id.txt_feedback_title);
    }

    /* access modifiers changed from: protected */
    public void s() {
        String str;
        Shop shop = this.b.getShop();
        if (shop == null || shop.getReceiptReviews().size() <= 0) {
            this.e.setVisibility(8);
            this.d.setVisibility(8);
            this.k.setVisibility(8);
            return;
        }
        int i = 0;
        this.k.setVisibility(0);
        if (l.c((Activity) this.a)) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(af.a((double) shop.getNumRatings()));
            sb.append(")");
            str = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(");
            sb2.append(af.a((double) shop.getNumRatings()));
            sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb2.append(this.c.getResources().getString(R.string.reviews));
            sb2.append(")");
            str = sb2.toString();
        }
        this.k.setText(str);
        if (shop.getAverageRating() > 0.0d) {
            this.l.setRating((float) shop.getAverageRating());
        } else {
            String quantityString = this.c.getResources().getQuantityString(R.plurals.reviews_plurals_no_brackets, shop.getNumRatings(), new Object[]{af.a((double) shop.getNumRatings())});
            this.l.setVisibility(8);
            this.k.setText(quantityString);
        }
        List receiptReviews = shop.getReceiptReviews();
        this.m = new ReviewAdapter(this.a, this.j);
        this.m.setShopOwnerName(af.a(this.b.getShop().getUser()));
        this.m.setSellerAvatarUrl(shop.getAvatarUrl());
        this.m.addItems(receiptReviews);
        this.m.setOnClickListener(this);
        this.m.setMachineTranslationEnabled(t.e());
        while (i < this.m.getDataItemCount() && i < 3) {
            ReviewViewHolder onCreateListItemViewHolder = this.m.onCreateListItemViewHolder((ViewGroup) this.d, (int) ReviewAdapter.LIST_ITEM_WITH_APP_PHOTO);
            this.m.onBindViewHolder(onCreateListItemViewHolder, i);
            this.n.add(onCreateListItemViewHolder);
            this.d.addView(onCreateListItemViewHolder.itemView);
            if (i != this.m.getDataItemCount() - 1) {
                this.d.addView(u());
            }
            i++;
        }
        if (shop.getNumRatings() > 3) {
            this.d.addView(u());
            this.d.addView(v());
            this.d.addView(u());
        }
        this.d.requestLayout();
    }

    private View u() {
        return this.a.getLayoutInflater().inflate(R.layout.row_divider_dark, null);
    }

    private View v() {
        final Shop shop = this.b.getShop();
        TextView textView = (TextView) LayoutInflater.from(this.a).inflate(R.layout.list_section_footer, null);
        if (shop == null) {
            textView.setVisibility(8);
        } else {
            int numRatings = shop.getNumRatings();
            textView.setText(this.c.getResources().getString(R.string.see_all_reviews, new Object[]{Integer.valueOf(numRatings)}));
            textView.setOnClickListener(new TrackingOnClickListener(new i[]{this.b, shop}) {
                public void onViewClick(View view) {
                    e.a((Activity) f.this.a).g().a(shop);
                }
            });
        }
        return textView;
    }

    public void b(final View view) {
        if (!l.c((Activity) this.a)) {
            final int measuredHeight = view.getMeasuredHeight();
            view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    LayoutParams layoutParams = (LayoutParams) f.this.d.getLayoutParams();
                    layoutParams.height += view.getMeasuredHeight() - measuredHeight;
                    f.this.a(layoutParams.height);
                    f.this.d.requestLayout();
                    j.b(view.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                }
            });
        }
    }

    public void onAppreciationPhotoClicked(int i) {
        ReceiptReview receiptReview = (ReceiptReview) this.m.getItem(i);
        if (receiptReview != null) {
            e.a((FragmentActivity) this.a).a().g(((Review) receiptReview.getReviews().get(0)).getTransactionId());
        }
    }

    public void onListingClicked(int i) {
        if (this.m != null) {
            ReceiptReview receiptReview = (ReceiptReview) this.m.getItem(i);
            if (receiptReview != null) {
                e.a((FragmentActivity) this.a).a().a(((Review) receiptReview.getReviews().get(0)).getListingId());
            }
        }
    }

    public void onUserClicked(int i) {
        if (this.m != null) {
            ReceiptReview receiptReview = (ReceiptReview) this.m.getItem(i);
            if (receiptReview != null) {
                e.a((FragmentActivity) this.a).a().c(receiptReview.getUserId());
            }
        }
    }

    public void onTranslateReviewClicked(final int i) {
        final Review review = (Review) ((ReceiptReview) this.m.getItem(i)).getReviews().get(0);
        Shop shop = this.b.getShop();
        if (shop != null) {
            String d = t.d();
            final MachineTranslationOneClickView machineTranslationOneClickView = ((ReviewViewHolder) this.n.get(i)).mMachineTranslationView;
            n().a((Object) this, (g<Result>) com.etsy.android.lib.core.e.a((EtsyRequest<Result>) TranslatedReviewRequest.getTranslatedReview(shop.getShopId(), review.getTransactionId(), d)).a((com.etsy.android.lib.core.e.c) new com.etsy.android.lib.core.e.c() {
                public void a() {
                    f.this.m.onTranslationLoading(i);
                    f.this.m.onBindViewHolder((ViewHolder) f.this.n.get(i), i);
                }
            }).a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<TranslatedReview>() {
                public void a(List<TranslatedReview> list, int i, k<TranslatedReview> kVar) {
                    f.this.b(machineTranslationOneClickView);
                    f.this.m.onTranslationSuccess(i);
                    review.setTranslatedReviewMessage(((TranslatedReview) list.get(0)).getTranslatedReview());
                    f.this.m.bindMachineTranslationArea((ReviewViewHolder) f.this.n.get(i), review, i);
                }
            }).a((b) new b() {
                public void a(int i, String str, k kVar) {
                    f.this.b(machineTranslationOneClickView);
                    f.this.m.onTranslationError(i);
                    f.this.m.bindMachineTranslationArea((ReviewViewHolder) f.this.n.get(i), review, i);
                }
            }).a());
        }
    }

    @CallSuper
    public void b() {
        super.b();
        this.n.clear();
    }
}
