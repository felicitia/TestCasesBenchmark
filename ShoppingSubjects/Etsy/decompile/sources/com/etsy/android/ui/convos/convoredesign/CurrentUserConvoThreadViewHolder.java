package com.etsy.android.ui.convos.convoredesign;

import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.lib.models.datatypes.TrackedObject;
import com.etsy.android.ui.convos.convoredesign.af.b;
import com.etsy.android.ui.convos.convoredesign.af.e;
import com.etsy.android.ui.convos.convoredesign.aj.d;
import com.etsy.android.ui.convos.convoredesign.o.a;
import com.etsy.android.uikit.view.AttachmentThumbnailsView;
import kotlin.jvm.internal.p;

/* compiled from: CurrentUserConvoThreadViewHolder.kt */
public final class CurrentUserConvoThreadViewHolder extends ViewHolder {
    private final c imageBatch;
    /* access modifiers changed from: private */
    public final b imageClickListener;
    /* access modifiers changed from: private */
    public final e linkCardClickListener;

    public CurrentUserConvoThreadViewHolder(View view, c cVar, b bVar, e eVar) {
        p.b(view, "view");
        p.b(cVar, "imageBatch");
        p.b(bVar, "imageClickListener");
        p.b(eVar, "linkCardClickListener");
        super(view);
        this.imageBatch = cVar;
        this.imageClickListener = bVar;
        this.linkCardClickListener = eVar;
    }

    public final void bind(a aVar) {
        p.b(aVar, "item");
        View view = this.itemView;
        p.a((Object) view, "itemView");
        ((AttachmentThumbnailsView) view.findViewById(com.etsy.android.e.a.linear_convo_images)).setImageBatch(this.imageBatch);
        View view2 = this.itemView;
        p.a((Object) view2, "itemView");
        ((AttachmentThumbnailsView) view2.findViewById(com.etsy.android.e.a.linear_convo_images)).clear();
        boolean z = true;
        if (aVar.b().isEmpty()) {
            View view3 = this.itemView;
            p.a((Object) view3, "itemView");
            j.b((AttachmentThumbnailsView) view3.findViewById(com.etsy.android.e.a.linear_convo_images));
        } else {
            View view4 = this.itemView;
            p.a((Object) view4, "itemView");
            j.a((AttachmentThumbnailsView) view4.findViewById(com.etsy.android.e.a.linear_convo_images));
            int i = 0;
            for (ImageInfo imageInfo : aVar.b()) {
                int i2 = i + 1;
                View view5 = this.itemView;
                p.a((Object) view5, "itemView");
                AttachmentThumbnailsView attachmentThumbnailsView = (AttachmentThumbnailsView) view5.findViewById(com.etsy.android.e.a.linear_convo_images);
                String url75x75 = imageInfo.getUrl75x75();
                CurrentUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1 currentUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1 = new CurrentUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1(i, imageInfo, new i[]{new TrackedObject(AnalyticsLogAttribute.URL, imageInfo.getUrlFullxFull())}, this, aVar);
                attachmentThumbnailsView.addImage(url75x75, (OnClickListener) currentUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1);
                i = i2;
            }
        }
        View view6 = this.itemView;
        p.a((Object) view6, "itemView");
        j.b((Group) view6.findViewById(com.etsy.android.e.a.custom_listing_group));
        ak c = aVar.c();
        if (c != null) {
            View view7 = this.itemView;
            p.a((Object) view7, "itemView");
            j.a((Group) view7.findViewById(com.etsy.android.e.a.custom_listing_group));
            c cVar = this.imageBatch;
            String c2 = c.c();
            View view8 = this.itemView;
            p.a((Object) view8, "itemView");
            cVar.a(c2, (ImageView) view8.findViewById(com.etsy.android.e.a.custom_listing_image));
            View view9 = this.itemView;
            p.a((Object) view9, "itemView");
            TextView textView = (TextView) view9.findViewById(com.etsy.android.e.a.custom_listing_title);
            p.a((Object) textView, "itemView.custom_listing_title");
            textView.setText(c.a());
            View view10 = this.itemView;
            p.a((Object) view10, "itemView");
            TextView textView2 = (TextView) view10.findViewById(com.etsy.android.e.a.custom_listing_price);
            p.a((Object) textView2, "itemView.custom_listing_price");
            textView2.setText(c.b());
            View view11 = this.itemView;
            p.a((Object) view11, "itemView");
            View findViewById = view11.findViewById(com.etsy.android.e.a.custom_listing_background);
            p.a((Object) findViewById, "itemView.custom_listing_background");
            j.a(findViewById, new CurrentUserConvoThreadViewHolder$bind$$inlined$let$lambda$1(c, this));
        }
        if (aVar.a().a().length() <= 0) {
            z = false;
        }
        if (z) {
            View view12 = this.itemView;
            p.a((Object) view12, "itemView");
            j.a((TextView) view12.findViewById(com.etsy.android.e.a.sender_message));
            View view13 = this.itemView;
            p.a((Object) view13, "itemView");
            TextView textView3 = (TextView) view13.findViewById(com.etsy.android.e.a.sender_message);
            p.a((Object) textView3, "itemView.sender_message");
            textView3.setText(aVar.a().a());
        } else {
            View view14 = this.itemView;
            p.a((Object) view14, "itemView");
            j.b((TextView) view14.findViewById(com.etsy.android.e.a.sender_message));
        }
        updateGroupingLayout(aVar.a());
    }

    private final void updateGroupingLayout(aj ajVar) {
        View view = this.itemView;
        p.a((Object) view, "itemView");
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.sk_space_2);
        View view2 = this.itemView;
        p.a((Object) view2, "itemView");
        int dimensionPixelSize2 = view2.getResources().getDimensionPixelSize(R.dimen.convo_card_gutter_width);
        View view3 = this.itemView;
        p.a((Object) view3, "itemView");
        int dimensionPixelSize3 = view3.getResources().getDimensionPixelSize(R.dimen.convo_grouped_message_padding);
        if (ajVar instanceof d) {
            this.itemView.setPadding(dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize3);
            View view4 = this.itemView;
            p.a((Object) view4, "itemView");
            view4.findViewById(com.etsy.android.e.a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_current_user_top);
        } else if (ajVar instanceof aj.c) {
            this.itemView.setPadding(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize, dimensionPixelSize3);
            View view5 = this.itemView;
            p.a((Object) view5, "itemView");
            view5.findViewById(com.etsy.android.e.a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_current_user_middle);
        } else if (ajVar instanceof aj.a) {
            this.itemView.setPadding(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize, dimensionPixelSize);
            View view6 = this.itemView;
            p.a((Object) view6, "itemView");
            view6.findViewById(com.etsy.android.e.a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_current_user_bottom);
        } else if (ajVar instanceof aj.b) {
            this.itemView.setPadding(dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            View view7 = this.itemView;
            p.a((Object) view7, "itemView");
            view7.findViewById(com.etsy.android.e.a.chat_background).setBackgroundResource(R.drawable.bg_chat_message_current_user);
        }
    }
}
