package com.etsy.android.ui.cart.viewholders;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.MessageToSeller;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.a;
import com.jakewharton.rxbinding2.widget.h;
import com.jakewharton.rxbinding2.widget.s;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.q;
import java.util.concurrent.TimeUnit;

public class MessageToSellerViewHolder extends BaseCartGroupItemViewHolder {
    private static final int TEXT_CHANGE_TIMEOUT = 500;
    private final a mClickHandler;
    private final EditText mMessageToSeller = ((EditText) findViewById(R.id.txt_message));
    @Nullable
    private Disposable mTextChangeDisposable;
    private final q<s> mTextChangeObservable;
    private final TextView mTxtNoteExplanation;

    public MessageToSellerViewHolder(ViewGroup viewGroup, a aVar) {
        int i = 0;
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_msg_to_seller, viewGroup, false));
        this.mClickHandler = aVar;
        this.mMessageToSeller.setHorizontallyScrolling(false);
        this.mMessageToSeller.setMaxLines(Integer.MAX_VALUE);
        this.mTextChangeObservable = h.c(this.mMessageToSeller).b(1).b(500, TimeUnit.MILLISECONDS).a(io.reactivex.a.b.a.a());
        h.a(this.mMessageToSeller).a(io.reactivex.a.b.a.a()).a(l.a, m.a);
        this.mTxtNoteExplanation = (TextView) findViewById(R.id.txt_note_explanation);
        TextView textView = this.mTxtNoteExplanation;
        if (!this.mViewTracker.c().c(b.cA)) {
            i = 8;
        }
        textView.setVisibility(i);
    }

    static final /* synthetic */ void lambda$new$0$MessageToSellerViewHolder(com.jakewharton.rxbinding2.widget.q qVar) throws Exception {
        if (qVar.actionId() != 0) {
            qVar.view().clearFocus();
            com.etsy.android.lib.util.s.a((View) qVar.view());
        }
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        if (this.mTextChangeDisposable != null) {
            this.mTextChangeDisposable.dispose();
        }
        MessageToSeller messageToSeller = (MessageToSeller) cartGroupItem.getData();
        String message = messageToSeller.getMessage();
        this.mMessageToSeller.setHint(messageToSeller.getHint());
        this.mMessageToSeller.setText(message);
        this.mTextChangeDisposable = this.mTextChangeObservable.a((Consumer<? super T>) new n<Object>(this, cartGroupItem), o.a);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$1$MessageToSellerViewHolder(CartGroupItem cartGroupItem, s sVar) throws Exception {
        updateMessage(cartGroupItem);
    }

    private void updateMessage(CartGroupItem cartGroupItem) {
        MessageToSeller messageToSeller = (MessageToSeller) cartGroupItem.getData();
        String obj = this.mMessageToSeller.getText().toString();
        messageToSeller.setMessage(obj);
        ServerDrivenAction action = cartGroupItem.getAction("message_to_seller");
        if (action != null) {
            action.addParam("message_to_seller", obj);
            this.mClickHandler.c(getRootView(), action);
        }
    }
}
