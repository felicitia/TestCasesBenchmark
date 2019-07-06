package com.etsy.android.ui.user;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.GiftCardInfo;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RatingIconView;
import java.util.HashMap;
import java.util.List;

public class TransactionAdapter extends BaseModelArrayAdapter<Transaction> {
    private int mImageHeight;
    private int mImageWidth;
    /* access modifiers changed from: private */
    public boolean mIsSellerOrder = false;
    /* access modifiers changed from: private */
    public a mTransactionClickListener;
    /* access modifiers changed from: private */
    public User mUser;

    public interface a {
        void onTransactionClick(Transaction transaction, User user);
    }

    private static class b {
        public TextView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public TextView e;
        public ImageView f;
        public RatingIconView g;
        public View h;
        public TextView i;
        public RatingIconView j;
        public TextView k;

        private b() {
        }
    }

    public TransactionAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, R.layout.list_item_transaction, cVar);
        this.mImageHeight = fragmentActivity.getResources().getDimensionPixelOffset(R.dimen.receipt_image_height);
        this.mImageWidth = fragmentActivity.getResources().getDimensionPixelOffset(R.dimen.receipt_image_width);
    }

    public void setIsSellOrder(boolean z) {
        this.mIsSellerOrder = z;
        notifyDataSetChanged();
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public void setTransactionClickListener(a aVar) {
        this.mTransactionClickListener = aVar;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null);
            bVar = createTransactionHolder(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        final Transaction transaction = (Transaction) getItem(i);
        bVar.a.setText(transaction.getTitle());
        bVar.c.setText(transaction.getFormattedPrice());
        populateTransactionInfo(bVar, transaction);
        populateVariations(bVar, transaction);
        populateImage(bVar, transaction);
        populateRating(bVar, transaction);
        if (!transaction.isGiftCard()) {
            view.setOnClickListener(new TrackingOnClickListener(new i[]{transaction}) {
                public void onViewClick(View view) {
                    if (transaction.getListingId().getIdAsLong() != 0) {
                        com.etsy.android.lib.logger.legacy.b.a().b(TransactionAdapter.this.mIsSellerOrder ? "view_sold_listing" : "view_sold_listing_purchased", "view_receipt", new HashMap<String, Object>() {
                            {
                                put("listing_id", transaction.getListingId().getId());
                            }
                        });
                        e.a((Activity) TransactionAdapter.this.getActivityContext()).a(transaction.getListingId());
                    }
                }
            });
        } else {
            EtsyLinkify.a(getContext(), bVar.b);
        }
        return view;
    }

    private b createTransactionHolder(View view) {
        b bVar = new b();
        bVar.a = (TextView) view.findViewById(R.id.text_receipt_listing_title);
        bVar.b = (TextView) view.findViewById(R.id.text_receipt_listing_transaction_info);
        bVar.c = (TextView) view.findViewById(R.id.text_receipt_listing_price);
        bVar.f = (ImageView) view.findViewById(R.id.image_receipt_listing);
        bVar.d = (TextView) view.findViewById(R.id.text_receipt_listing_variation_1);
        bVar.e = (TextView) view.findViewById(R.id.text_receipt_listing_variation_2);
        bVar.g = (RatingIconView) view.findViewById(R.id.rating_view);
        bVar.k = (TextView) view.findViewById(R.id.txt_review_upcoming);
        bVar.i = (TextView) view.findViewById(R.id.txt_review_button);
        bVar.h = view.findViewById(R.id.panel_review_button);
        bVar.j = (RatingIconView) view.findViewById(R.id.rating_edit);
        return bVar;
    }

    private void populateTransactionInfo(b bVar, Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        if (!transaction.isGiftCard() || transaction.getGiftCardInfo() == null) {
            sb.append(getContext().getString(R.string.quantity));
            sb.append(": ");
            sb.append(transaction.getQuantity());
        } else {
            GiftCardInfo giftCardInfo = transaction.getGiftCardInfo();
            if (giftCardInfo.getRecipientName() != null) {
                sb.append(getContext().getString(R.string.giftcard_to));
                sb.append(": ");
                sb.append(giftCardInfo.getRecipientName());
                sb.append("\n");
            }
            if (giftCardInfo.getSenderName() != null) {
                sb.append(getContext().getString(R.string.giftcard_from));
                sb.append(": ");
                sb.append(giftCardInfo.getSenderName());
                sb.append("\n");
            }
            if (giftCardInfo.isEmail() && giftCardInfo.getRecipientEmail() != null) {
                sb.append(getContext().getString(R.string.giftcard_email));
                sb.append(": ");
                sb.append(giftCardInfo.getRecipientEmail());
            }
        }
        bVar.b.setText(sb.toString());
    }

    private void populateVariations(b bVar, Transaction transaction) {
        List variations = transaction.getVariations();
        if (variations.size() <= 0 || transaction.isGiftCard()) {
            bVar.d.setVisibility(8);
        } else {
            TextView textView = bVar.d;
            StringBuilder sb = new StringBuilder();
            sb.append(((Variation) variations.get(0)).getFormattedName());
            sb.append(": ");
            sb.append(((Variation) variations.get(0)).getFormattedValue());
            textView.setText(sb.toString());
            bVar.d.setVisibility(0);
        }
        if (variations.size() <= 1 || transaction.isGiftCard()) {
            bVar.e.setVisibility(8);
            return;
        }
        TextView textView2 = bVar.e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(((Variation) variations.get(1)).getFormattedName());
        sb2.append(": ");
        sb2.append(((Variation) variations.get(1)).getFormattedValue());
        textView2.setText(sb2.toString());
        bVar.e.setVisibility(0);
    }

    private void populateImage(b bVar, Transaction transaction) {
        String str = (!transaction.isGiftCard() || transaction.getGiftCardDesign() == null || transaction.getGiftCardDesign().getUrl150x119() == null) ? (!l.c((Activity) getActivityContext()) || transaction.getMainImage() == null || transaction.getMainImage().getUrl170x135() == null) ? (transaction.getMainImage() == null || transaction.getMainImage().getUrl75x75() == null) ? null : transaction.getMainImage().getUrl75x75() : transaction.getMainImage().getUrl170x135() : transaction.getGiftCardDesign().getUrl150x119();
        getImageBatch().a(str, bVar.f, this.mImageWidth, this.mImageHeight);
    }

    private void populateRating(b bVar, final Transaction transaction) {
        bVar.g.setVisibility(8);
        bVar.h.setVisibility(8);
        bVar.i.setVisibility(8);
        bVar.j.setVisibility(8);
        bVar.k.setVisibility(8);
        if (!this.mIsSellerOrder && transaction.isFeedbackMutable()) {
            bVar.h.setVisibility(0);
            bVar.i.setVisibility(0);
            if (transaction.getReview() == null) {
                bVar.i.setText(R.string.leave_a_review);
            } else {
                bVar.j.setVisibility(0);
                bVar.j.setRating((float) transaction.getReview().getRating());
                bVar.i.setText(R.string.edit_review);
            }
            bVar.h.setOnClickListener(new TrackingOnClickListener(new i[]{transaction, this.mUser}) {
                public void onViewClick(View view) {
                    if (TransactionAdapter.this.mTransactionClickListener != null) {
                        TransactionAdapter.this.mTransactionClickListener.onTransactionClick(transaction, TransactionAdapter.this.mUser);
                    }
                }
            });
        } else if (!this.mIsSellerOrder && transaction.hasFutureReviewDate()) {
            bVar.k.setVisibility(0);
            bVar.k.setText(getActivityContext().getString(R.string.feedback_available_message, new Object[]{af.a(transaction.getFeedbackOpenDate())}));
        } else if (transaction.getReview() != null) {
            bVar.g.setVisibility(0);
            bVar.g.setRating((float) transaction.getReview().getRating());
        }
    }
}
