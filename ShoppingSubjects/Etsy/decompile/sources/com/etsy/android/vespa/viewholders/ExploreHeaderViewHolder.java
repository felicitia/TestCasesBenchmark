package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ExploreHeader;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.vespa.k;
import kotlin.jvm.internal.p;

/* compiled from: ExploreHeaderViewHolder.kt */
public final class ExploreHeaderViewHolder extends BaseViewHolder<k> {
    private final c imageBatch;
    private final ImageView imageView;
    private final TextView txtSubtitle;
    private final TextView txtTitle;

    public ExploreHeaderViewHolder(ViewGroup viewGroup, c cVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(cVar, "imageBatch");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(a.k.list_item_explore_header, viewGroup, false));
        this.imageBatch = cVar;
        View view = this.itemView;
        p.a((Object) view, "itemView");
        TextView textView = (TextView) view.findViewById(i.txt_title);
        p.a((Object) textView, "itemView.txt_title");
        this.txtTitle = textView;
        View view2 = this.itemView;
        p.a((Object) view2, "itemView");
        TextView textView2 = (TextView) view2.findViewById(i.txt_subtitle);
        p.a((Object) textView2, "itemView.txt_subtitle");
        this.txtSubtitle = textView2;
        View view3 = this.itemView;
        p.a((Object) view3, "itemView");
        ImageView imageView2 = (ImageView) view3.findViewById(i.image);
        p.a((Object) imageView2, "itemView.image");
        this.imageView = imageView2;
    }

    public void bind(k kVar) {
        p.b(kVar, "data");
        super.bind(kVar);
        ExploreHeader exploreHeader = (ExploreHeader) kVar;
        this.itemView.setBackgroundColor(exploreHeader.getBackgroundColor());
        this.txtTitle.setText(exploreHeader.getTitle());
        this.txtTitle.setTextColor(exploreHeader.getTitleTextColor());
        this.txtSubtitle.setText(exploreHeader.getSubtitle());
        this.txtSubtitle.setTextColor(exploreHeader.getSubtitleTextColor());
        TextView textView = this.txtSubtitle;
        CharSequence subtitle = exploreHeader.getSubtitle();
        int i = 0;
        if (subtitle == null || subtitle.length() == 0) {
            i = 8;
        }
        textView.setVisibility(i);
        Image image = exploreHeader.getImage();
        if (image != null) {
            j.a(this.imageView);
            this.imageBatch.a(image, this.imageView);
            return;
        }
        j.b(this.imageView);
    }
}
