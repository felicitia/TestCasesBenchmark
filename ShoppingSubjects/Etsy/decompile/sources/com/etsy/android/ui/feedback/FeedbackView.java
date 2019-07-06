package com.etsy.android.ui.feedback;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.extensions.c;
import com.etsy.android.extensions.d;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.models.ResponseConstants;
import com.jakewharton.rxbinding2.widget.h;
import com.jakewharton.rxbinding2.widget.n;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import kotlin.jvm.internal.p;
import kotlin.text.m;

/* compiled from: FeedbackView.kt */
public final class FeedbackView extends FrameLayout {
    private HashMap _$_findViewCache;
    private final Drawable errorFieldDrawable;
    private final Drawable normalFieldDrawable;

    /* compiled from: FeedbackView.kt */
    static final class a<T> implements Consumer<n> {
        final /* synthetic */ FeedbackView a;

        a(FeedbackView feedbackView) {
            this.a = feedbackView;
        }

        /* renamed from: a */
        public final void accept(n nVar) {
            CharSequence editable = nVar.editable();
            if (!(editable == null || m.a(editable))) {
                this.a.hideError();
            }
        }
    }

    public FeedbackView(Context context) {
        this(context, null, 0, 6, null);
    }

    public FeedbackView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ FeedbackView(Context context, AttributeSet attributeSet, int i, int i2, o oVar) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i);
    }

    public FeedbackView(Context context, AttributeSet attributeSet, int i) {
        p.b(context, ResponseConstants.CONTEXT);
        super(context, attributeSet, i);
        if (d.a()) {
            getLayoutTransition().enableTransitionType(4);
        }
        this.normalFieldDrawable = c.c(context, R.drawable.bg_edit_listing_field);
        this.errorFieldDrawable = c.c(context, R.drawable.bg_im_edit_field_error);
    }

    public final void focus() {
        EditText editText = (EditText) _$_findCachedViewById(com.etsy.android.e.a.edittext_feedback);
        if (editText != null) {
            j.d(editText);
        }
        h.d((EditText) _$_findCachedViewById(com.etsy.android.e.a.edittext_feedback)).b().b(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new a<Object>(this));
    }

    public final String getFeedback() {
        EditText editText = (EditText) _$_findCachedViewById(com.etsy.android.e.a.edittext_feedback);
        if (editText != null) {
            Editable text = editText.getText();
            if (text != null) {
                String obj = text.toString();
                if (obj != null) {
                    return obj;
                }
            }
        }
        return "";
    }

    public final void showError(@StringRes int i) {
        j.a((TextView) _$_findCachedViewById(com.etsy.android.e.a.error_label));
        TextView textView = (TextView) _$_findCachedViewById(com.etsy.android.e.a.error_label);
        p.a((Object) textView, "error_label");
        Context context = getContext();
        p.a((Object) context, ResponseConstants.CONTEXT);
        textView.setText(context.getResources().getString(i));
        EditText editText = (EditText) _$_findCachedViewById(com.etsy.android.e.a.edittext_feedback);
        p.a((Object) editText, "edittext_feedback");
        Drawable mutate = editText.getBackground().mutate();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.etsy.android.e.a.feedback_border_box);
        p.a((Object) linearLayout, "feedback_border_box");
        linearLayout.setBackground(this.errorFieldDrawable);
        mutate.clearColorFilter();
        Context context2 = getContext();
        p.a((Object) context2, ResponseConstants.CONTEXT);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(c.b(context2, R.color.red), Mode.SRC_IN);
        p.a((Object) mutate, "editTextBg");
        mutate.setColorFilter(porterDuffColorFilter);
    }

    /* access modifiers changed from: private */
    public final void hideError() {
        TextView textView = (TextView) _$_findCachedViewById(com.etsy.android.e.a.error_label);
        p.a((Object) textView, "error_label");
        textView.setText(null);
        j.b((TextView) _$_findCachedViewById(com.etsy.android.e.a.error_label));
        EditText editText = (EditText) _$_findCachedViewById(com.etsy.android.e.a.edittext_feedback);
        p.a((Object) editText, "edittext_feedback");
        Drawable mutate = editText.getBackground().mutate();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.etsy.android.e.a.feedback_border_box);
        p.a((Object) linearLayout, "feedback_border_box");
        linearLayout.setBackground(this.normalFieldDrawable);
        mutate.clearColorFilter();
    }

    public final void setFeedback(String str) {
        p.b(str, "previousText");
        EditText editText = (EditText) _$_findCachedViewById(com.etsy.android.e.a.edittext_feedback);
        if (editText != null) {
            editText.setText(str);
        }
    }
}
