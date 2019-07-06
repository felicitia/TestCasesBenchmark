package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.convos.contentprovider.b;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;

public class RecentContactsAdapter extends ResourceCursorAdapter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = f.a(RecentContactsAdapter.class);
    private int mAvatarSize;
    Context mContext;
    private c mImageBatch;

    private static class a {
        public final TextView a;
        public final ImageView b;

        public a(View view) {
            this.a = (TextView) view.findViewById(i.convo_username);
            this.b = (ImageView) view.findViewById(i.convo_user_img);
        }
    }

    public RecentContactsAdapter(Context context, c cVar) {
        super(context, k.list_item_convo_contact, null, true);
        this.mContext = context;
        this.mImageBatch = cVar;
        this.mAvatarSize = context.getResources().getDimensionPixelSize(com.etsy.android.lib.a.f.conversation_avatar);
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        Cursor queryCursor = getQueryCursor(charSequence == null ? "" : charSequence.toString());
        if (queryCursor == null) {
            return null;
        }
        queryCursor.getCount();
        return queryCursor;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View newView = super.newView(context, cursor, viewGroup);
        newView.setTag(new a(newView));
        return newView;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        aVar.a.setText(cursor.getString(2));
        this.mImageBatch.b(cursor.getString(3), aVar.b, this.mAvatarSize);
    }

    private Cursor getQueryCursor(String str) {
        return b.a(this.mContext, str);
    }
}
