package com.etsy.android.ui.cart.viewholders;

import android.content.res.Resources;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.Pair;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.HashMap;

public abstract class BaseMessageViewHolder<T> extends BaseViewHolder<T> {
    protected static final HashMap<String, Pair<Integer, Integer>> TYPE_COLORS_MAP = new HashMap<>();
    protected final ViewGroup mMessageBubble = ((ViewGroup) findViewById(R.id.message_bubble));
    protected final ImageView mPointer = ((ImageView) findViewById(R.id.pointer));
    protected final TextView mTextMessage = ((TextView) findViewById(R.id.txt_message));

    static {
        TYPE_COLORS_MAP.put("info", new Pair(Integer.valueOf(R.color.sk_gray_10), Integer.valueOf(R.color.dark_grey)));
        TYPE_COLORS_MAP.put(BaseMessage.TYPE_WARNING, new Pair(Integer.valueOf(R.color.sk_secondary_creamsicle), Integer.valueOf(R.color.dark_grey)));
        TYPE_COLORS_MAP.put("error", new Pair(Integer.valueOf(R.color.sk_secondary_autumn), Integer.valueOf(R.color.sk_white)));
        TYPE_COLORS_MAP.put("success", new Pair(Integer.valueOf(R.color.sk_secondary_blinding_sandstorm), Integer.valueOf(R.color.dark_grey)));
        TYPE_COLORS_MAP.put(BaseMessage.TYPE_NOTIFY, new Pair(Integer.valueOf(R.color.sk_gray_60), Integer.valueOf(R.color.sk_white)));
    }

    public BaseMessageViewHolder(View view) {
        super(view);
    }

    public void bind(BaseMessage baseMessage) {
        this.mTextMessage.setText(Html.fromHtml(baseMessage.getMessage()));
        this.mPointer.setVisibility(baseMessage.getHasPointer() ? 0 : 8);
        setType(baseMessage.getType());
    }

    /* access modifiers changed from: protected */
    public void setType(String str) {
        Pair pair = (Pair) TYPE_COLORS_MAP.get(str);
        if (pair == null) {
            pair = (Pair) TYPE_COLORS_MAP.get("info");
        }
        Resources resources = this.itemView.getResources();
        setTextColors(resources.getColor(((Integer) pair.second).intValue()));
        setBackgroundColors(resources.getColor(((Integer) pair.first).intValue()));
    }

    /* access modifiers changed from: protected */
    public void setTextColors(int i) {
        this.mTextMessage.setTextColor(i);
    }

    /* access modifiers changed from: protected */
    public void setBackgroundColors(int i) {
        this.mPointer.setColorFilter(i);
        DrawableCompat.setTint(DrawableCompat.wrap(this.mMessageBubble.getBackground()), i);
    }
}
