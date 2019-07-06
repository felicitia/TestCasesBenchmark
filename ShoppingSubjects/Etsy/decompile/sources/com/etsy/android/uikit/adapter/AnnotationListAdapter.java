package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import com.etsy.android.lib.util.fonts.StandardFontIcon;

public class AnnotationListAdapter extends BaseRecyclerViewAdapter<a> {
    /* access modifiers changed from: private */
    public OnClickListener mOnClickListener;

    public interface a {
        ShareAnnotation a();

        Listing b();
    }

    private class b extends ViewHolder {
        private final ImageView b;
        private final TextView c;
        private final IconView d;
        private final View e;

        public b(View view) {
            super(view);
            this.b = (ImageView) view.findViewById(i.listing_image);
            this.c = (TextView) view.findViewById(i.listing_title);
            this.d = (IconView) view.findViewById(i.delete_icon);
            this.e = view.findViewById(i.delete_button);
        }

        public void a(a aVar, int i, Context context, c cVar) {
            Listing b2 = aVar.b();
            ShareAnnotation a2 = aVar.a();
            if (b2 != null) {
                this.c.setText(b2.getTitle());
                ListingImage image = b2.getImage();
                if (image != null) {
                    cVar.a(image.getImageUrlForPixelWidth(context.getResources().getDimensionPixelSize(f.shop_share_annotation_listing_image_size)), this.b);
                }
            }
            if (AnnotationListAdapter.this.mOnClickListener != null) {
                this.d.setIcon(StandardFontIcon.DELETE);
                this.d.setColor(context.getResources().getColor(e.light_grey));
                this.e.setOnClickListener(AnnotationListAdapter.this.mOnClickListener);
                if (a2 != null) {
                    this.e.setTag(Long.valueOf(a2.getShareAnnotationId()));
                    return;
                }
                return;
            }
            this.d.setVisibility(4);
            this.e.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        return 0;
    }

    public AnnotationListAdapter(FragmentActivity fragmentActivity, c cVar, OnClickListener onClickListener) {
        super(fragmentActivity, cVar);
        this.mOnClickListener = onClickListener;
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new b(this.mInflater.inflate(k.list_item_annotation, viewGroup, false));
    }

    public AnnotationListAdapter addAnnotation(Listing listing, ShareAnnotation shareAnnotation) {
        clear();
        addItem(getAnnotationListItem(listing, shareAnnotation));
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        ((b) viewHolder).a((a) getItem(i), i, this.mContext, this.mImageBatch);
    }

    private a getAnnotationListItem(final Listing listing, final ShareAnnotation shareAnnotation) {
        return new a() {
            public ShareAnnotation a() {
                return shareAnnotation;
            }

            public Listing b() {
                return listing;
            }
        };
    }
}
