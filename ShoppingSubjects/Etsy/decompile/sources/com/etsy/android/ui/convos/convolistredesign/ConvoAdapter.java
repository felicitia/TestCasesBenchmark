package com.etsy.android.ui.convos.convolistredesign;

import android.content.Context;
import android.support.constraint.Group;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.e;
import com.etsy.android.extensions.j;
import com.etsy.android.extensions.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.ConvoUser;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.convo.context.CustomOrderContext;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.view.ClickableImageView;
import java.util.Arrays;
import kotlin.h;
import kotlin.jvm.a.a;
import kotlin.jvm.a.b;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.u;

/* compiled from: ConvoAdapter.kt */
public final class ConvoAdapter extends BaseRecyclerViewAdapter<e> {
    private final b<Conversation3, h> onConvoClicked;
    private final a<h> onScrollToEnd;
    private final m<Boolean, EtsyId, h> onUserClicked;

    /* compiled from: ConvoAdapter.kt */
    public final class ConvoItemViewHolder extends ViewHolder {
        final /* synthetic */ ConvoAdapter this$0;

        public ConvoItemViewHolder(ConvoAdapter convoAdapter, View view) {
            p.b(view, "view");
            this.this$0 = convoAdapter;
            super(view);
        }

        public final void bind(e eVar) {
            p.b(eVar, "convoListViewItem");
            if (eVar instanceof e.a) {
                bind(((e.a) eVar).a());
            } else if (eVar instanceof e.b) {
                e.b bVar = (e.b) eVar;
                bind(bVar.a());
                showHeader(bVar.b());
            }
        }

        public final void bind(Conversation3 conversation3) {
            p.b(conversation3, "conversation");
            View view = this.itemView;
            p.a((Object) view, "itemView");
            j.a(view, new ConvoAdapter$ConvoItemViewHolder$bind$1(this, conversation3));
            ConvoUser otherUser = conversation3.getOtherUser();
            CharSequence displayName = otherUser != null ? otherUser.getDisplayName() : null;
            ConvoUser otherUser2 = conversation3.getOtherUser();
            boolean isGuest = otherUser2 != null ? otherUser2.isGuest() : false;
            if (isGuest) {
                View view2 = this.itemView;
                p.a((Object) view2, "itemView");
                TextView textView = (TextView) view2.findViewById(e.a.convo_username);
                p.a((Object) textView, "itemView.convo_username");
                u uVar = u.a;
                Object[] objArr = {displayName, ConvoAdapter.access$getMContext$p(this.this$0).getString(R.string.guest)};
                String format = String.format("%s (%s)", Arrays.copyOf(objArr, objArr.length));
                p.a((Object) format, "java.lang.String.format(format, *args)");
                textView.setText(format);
            } else {
                View view3 = this.itemView;
                p.a((Object) view3, "itemView");
                TextView textView2 = (TextView) view3.findViewById(e.a.convo_username);
                p.a((Object) textView2, "itemView.convo_username");
                textView2.setText(displayName);
            }
            View view4 = this.itemView;
            p.a((Object) view4, "itemView");
            TextView textView3 = (TextView) view4.findViewById(e.a.convo_title);
            p.a((Object) textView3, "itemView.convo_title");
            textView3.setText(conversation3.getTitle());
            View view5 = this.itemView;
            p.a((Object) view5, "itemView");
            TextView textView4 = (TextView) view5.findViewById(e.a.convo_time);
            p.a((Object) textView4, "itemView.convo_time");
            textView4.setText(DateUtils.getRelativeTimeSpanString(conversation3.getLastUpdateDateInMillis()));
            View view6 = this.itemView;
            p.a((Object) view6, "itemView");
            TextView textView5 = (TextView) view6.findViewById(e.a.convo_message_preview);
            p.a((Object) textView5, "itemView.convo_message_preview");
            textView5.setText(conversation3.getLastMessage());
            c access$getMImageBatch$p = this.this$0.mImageBatch;
            ConvoUser otherUser3 = conversation3.getOtherUser();
            String avatarUrl = otherUser3 != null ? otherUser3.getAvatarUrl() : null;
            View view7 = this.itemView;
            p.a((Object) view7, "itemView");
            ClickableImageView clickableImageView = (ClickableImageView) view7.findViewById(e.a.convo_user_img);
            View view8 = this.itemView;
            p.a((Object) view8, "itemView");
            Context context = view8.getContext();
            p.a((Object) context, "itemView.context");
            access$getMImageBatch$p.b(avatarUrl, clickableImageView, context.getResources().getDimensionPixelSize(R.dimen.convo_smaller_avatar));
            ConvoUser otherUser4 = conversation3.getOtherUser();
            EtsyId userId = otherUser4 != null ? otherUser4.getUserId() : null;
            View view9 = this.itemView;
            p.a((Object) view9, "itemView");
            ClickableImageView clickableImageView2 = (ClickableImageView) view9.findViewById(e.a.convo_user_img);
            p.a((Object) clickableImageView2, "itemView.convo_user_img");
            j.a(clickableImageView2, new ConvoAdapter$ConvoItemViewHolder$bind$2(this, isGuest, userId));
            if (conversation3.getRead()) {
                View view10 = this.itemView;
                p.a((Object) view10, "itemView");
                j.c(view10.findViewById(e.a.new_message_indicator));
                View view11 = this.itemView;
                p.a((Object) view11, "itemView");
                view11.setBackground(null);
                View view12 = this.itemView;
                p.a((Object) view12, "itemView");
                com.etsy.android.stylekit.e.b((TextView) view12.findViewById(e.a.convo_username), com.etsy.android.lib.a.p.TextBlack_Larger);
                View view13 = this.itemView;
                p.a((Object) view13, "itemView");
                com.etsy.android.stylekit.e.b((TextView) view13.findViewById(e.a.convo_title), com.etsy.android.lib.a.p.TextBlack_Large);
            } else {
                View view14 = this.itemView;
                p.a((Object) view14, "itemView");
                j.a(view14.findViewById(e.a.new_message_indicator));
                this.itemView.setBackgroundResource(R.color.white);
                View view15 = this.itemView;
                p.a((Object) view15, "itemView");
                com.etsy.android.stylekit.e.b((TextView) view15.findViewById(e.a.convo_username), com.etsy.android.lib.a.p.TextBlack_Larger_Bold);
                View view16 = this.itemView;
                p.a((Object) view16, "itemView");
                com.etsy.android.stylekit.e.b((TextView) view16.findViewById(e.a.convo_title), com.etsy.android.lib.a.p.TextBlack_Large_Bold);
            }
            if (conversation3.getHasAttachments()) {
                View view17 = this.itemView;
                p.a((Object) view17, "itemView");
                j.a((ImageView) view17.findViewById(e.a.convo_has_attachment));
            } else {
                View view18 = this.itemView;
                p.a((Object) view18, "itemView");
                j.c((ImageView) view18.findViewById(e.a.convo_has_attachment));
            }
            if (conversation3.getConversationContext() instanceof CustomOrderContext) {
                View view19 = this.itemView;
                p.a((Object) view19, "itemView");
                j.a((TextView) view19.findViewById(e.a.convo_custom_badge));
            } else {
                View view20 = this.itemView;
                p.a((Object) view20, "itemView");
                j.b((TextView) view20.findViewById(e.a.convo_custom_badge));
            }
            View view21 = this.itemView;
            p.a((Object) view21, "itemView");
            j.b((Group) view21.findViewById(e.a.header_group));
        }

        private final void showHeader(String str) {
            View view = this.itemView;
            p.a((Object) view, "itemView");
            j.a((Group) view.findViewById(e.a.header_group));
            View view2 = this.itemView;
            p.a((Object) view2, "itemView");
            TextView textView = (TextView) view2.findViewById(e.a.convo_month_header_text);
            p.a((Object) textView, "itemView.convo_month_header_text");
            textView.setText(str);
        }
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        return 0;
    }

    public static final /* synthetic */ FragmentActivity access$getMContext$p(ConvoAdapter convoAdapter) {
        return (FragmentActivity) convoAdapter.mContext;
    }

    public final m<Boolean, EtsyId, h> getOnUserClicked() {
        return this.onUserClicked;
    }

    public final b<Conversation3, h> getOnConvoClicked() {
        return this.onConvoClicked;
    }

    public ConvoAdapter(FragmentActivity fragmentActivity, c cVar, m<? super Boolean, ? super EtsyId, h> mVar, b<? super Conversation3, h> bVar, a<h> aVar) {
        p.b(fragmentActivity, "activity");
        p.b(cVar, "imageBatch");
        p.b(mVar, "onUserClicked");
        p.b(bVar, "onConvoClicked");
        p.b(aVar, "onScrollToEnd");
        super(fragmentActivity, cVar);
        this.onUserClicked = mVar;
        this.onConvoClicked = bVar;
        this.onScrollToEnd = aVar;
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        p.b(viewGroup, ResponseConstants.PARENT);
        return new ConvoItemViewHolder(this, k.a(viewGroup, R.layout.item_conversation, false, 2, null));
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        p.b(viewHolder, "viewHolder");
        e eVar = (e) getItem(i);
        ConvoItemViewHolder convoItemViewHolder = (ConvoItemViewHolder) viewHolder;
        p.a((Object) eVar, "convoListViewItem");
        convoItemViewHolder.bind(eVar);
        if (i == getItemCount() - 1) {
            this.onScrollToEnd.invoke();
        }
    }
}
