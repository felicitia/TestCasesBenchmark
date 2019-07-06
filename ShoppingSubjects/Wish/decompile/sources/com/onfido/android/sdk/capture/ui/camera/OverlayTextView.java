package com.onfido.android.sdk.capture.ui.camera;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.api.client.data.DocSide;
import java.util.HashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public final class OverlayTextView extends LinearLayout {
    private final int a;
    private HashMap b;

    public OverlayTextView(Context context) {
        this(context, null, 0, 6, null);
    }

    public OverlayTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public OverlayTextView(Context context, AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i);
        View.inflate(context, R.layout.onfido_overlay_text_view, this);
        this.a = getResources().getDimensionPixelOffset(R.dimen.onfido_document_capture_text_side_margin);
    }

    public /* synthetic */ OverlayTextView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i);
    }

    static /* bridge */ /* synthetic */ void a(OverlayTextView overlayTextView, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        overlayTextView.a(str, str2);
    }

    private final void a(String str, String str2) {
        ((TextView) _$_findCachedViewById(R.id.mainText)).setText(str);
        ((TextView) _$_findCachedViewById(R.id.secondaryText)).setText(str2);
    }

    public View _$_findCachedViewById(int i) {
        if (this.b == null) {
            this.b = new HashMap();
        }
        View view = (View) this.b.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.b.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void setCaptureOverlayText(CaptureType captureType, DocumentType documentType, DocSide docSide) {
        View view;
        Resources resources;
        int i;
        int i2;
        Resources resources2;
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        if (documentType != null) {
            switch (documentType) {
                case PASSPORT:
                    resources = getResources();
                    i = R.string.onfido_message_document_passport;
                    break;
                case RESIDENCE_PERMIT:
                    resources = getResources();
                    if (!Intrinsics.areEqual(docSide, DocSide.FRONT)) {
                        i = R.string.onfido_message_side_document_back_residence_permit;
                        break;
                    } else {
                        i = R.string.onfido_message_side_document_front_residence_permit;
                        break;
                    }
                case NATIONAL_IDENTITY_CARD:
                    resources = getResources();
                    if (!Intrinsics.areEqual(docSide, DocSide.FRONT)) {
                        i = R.string.onfido_message_side_document_back_national_id;
                        break;
                    } else {
                        i = R.string.onfido_message_side_document_front_national_id;
                        break;
                    }
                case DRIVING_LICENCE:
                    resources = getResources();
                    if (!Intrinsics.areEqual(docSide, DocSide.FRONT)) {
                        i = R.string.onfido_message_side_document_back_driving_license;
                        break;
                    } else {
                        i = R.string.onfido_message_side_document_front_driving_license;
                        break;
                    }
                default:
                    throw new NoWhenBranchMatchedException();
            }
            String string = resources.getString(i);
            switch (documentType) {
                case PASSPORT:
                    resources2 = getResources();
                    i2 = R.string.onfido_message_passport_capture_subtitle;
                    break;
                case RESIDENCE_PERMIT:
                    resources2 = getResources();
                    if (!Intrinsics.areEqual(docSide, DocSide.FRONT)) {
                        i2 = R.string.onfido_message_document_capture_info_back_residence_permit;
                        break;
                    } else {
                        i2 = R.string.onfido_message_document_capture_info_front_residence_permit;
                        break;
                    }
                case NATIONAL_IDENTITY_CARD:
                    resources2 = getResources();
                    if (!Intrinsics.areEqual(docSide, DocSide.FRONT)) {
                        i2 = R.string.onfido_message_document_capture_info_back_national_id;
                        break;
                    } else {
                        i2 = R.string.onfido_message_document_capture_info_front_national_id;
                        break;
                    }
                case DRIVING_LICENCE:
                    resources2 = getResources();
                    if (!Intrinsics.areEqual(docSide, DocSide.FRONT)) {
                        i2 = R.string.onfido_message_document_capture_info_back_driving_license;
                        break;
                    } else {
                        i2 = R.string.onfido_message_document_capture_info_front_driving_license;
                        break;
                    }
                default:
                    throw new NoWhenBranchMatchedException();
            }
            String string2 = resources2.getString(i2);
            Intrinsics.checkExpressionValueIsNotNull(string, "mainTextString");
            Intrinsics.checkExpressionValueIsNotNull(string2, "secondaryTextString");
            a(string, string2);
            LayoutParams layoutParams = ((TextView) _$_findCachedViewById(R.id.secondaryText)).getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(this.a, layoutParams2.topMargin, this.a, layoutParams2.bottomMargin);
            view = (TextView) _$_findCachedViewById(R.id.mainText);
        } else {
            OverlayTextView overlayTextView = this;
            if (Intrinsics.areEqual(captureType, CaptureType.FACE)) {
                String string3 = overlayTextView.getResources().getString(R.string.onfido_message_capture_face);
                Intrinsics.checkExpressionValueIsNotNull(string3, "resources.getString(R.st…ido_message_capture_face)");
                overlayTextView.a("", string3);
                ViewExtensionsKt.toGone((TextView) overlayTextView._$_findCachedViewById(R.id.mainText));
                ((TextView) _$_findCachedViewById(R.id.mainText)).setTextColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
                ((TextView) _$_findCachedViewById(R.id.secondaryText)).setTextColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
                ViewExtensionsKt.toVisible((TextView) _$_findCachedViewById(R.id.secondaryText));
            }
            String string4 = overlayTextView.getResources().getString(R.string.onfido_message_capture_face);
            Intrinsics.checkExpressionValueIsNotNull(string4, "resources.getString(R.st…ido_message_capture_face)");
            String string5 = overlayTextView.getResources().getString(R.string.onfido_liveness_preparation_subtitle);
            Intrinsics.checkExpressionValueIsNotNull(string5, "resources.getString(R.st…ess_preparation_subtitle)");
            overlayTextView.a(string4, string5);
            ((TextView) overlayTextView._$_findCachedViewById(R.id.secondaryText)).setTextSize(0, (float) overlayTextView.getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_capture_instructions_subtitle_size));
            TextView textView = (TextView) overlayTextView._$_findCachedViewById(R.id.mainText);
            textView.setTextSize(0, (float) textView.getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_capture_instructions_title_size));
            view = textView;
        }
        ViewExtensionsKt.toVisible(view);
        ((TextView) _$_findCachedViewById(R.id.mainText)).setTextColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        ((TextView) _$_findCachedViewById(R.id.secondaryText)).setTextColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        ViewExtensionsKt.toVisible((TextView) _$_findCachedViewById(R.id.secondaryText));
    }

    public final void setConfirmationOverlayText(DocumentType documentType) {
        int i;
        if (documentType != null) {
            switch (documentType) {
                case PASSPORT:
                    i = R.string.onfido_message_check_readability_subtitle_passport;
                    break;
                case RESIDENCE_PERMIT:
                    i = R.string.onfido_message_check_readability_subtitle_residence_permit;
                    break;
                case NATIONAL_IDENTITY_CARD:
                    i = R.string.onfido_message_check_readability_subtitle_national_id;
                    break;
                case DRIVING_LICENCE:
                    i = R.string.onfido_message_check_readability_subtitle_driving_license;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            i = R.string.onfido_message_confirm_face_subtitle;
        }
        String string = getResources().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(subtitleText)");
        a(this, null, string, 1, null);
        LayoutParams layoutParams = ((TextView) _$_findCachedViewById(R.id.secondaryText)).getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(this.a, layoutParams2.topMargin, this.a, layoutParams2.bottomMargin);
        ((TextView) _$_findCachedViewById(R.id.mainText)).setTextColor(ContextCompat.getColor(getContext(), R.color.onfido_black));
        ((TextView) _$_findCachedViewById(R.id.secondaryText)).setTextColor(ContextCompat.getColor(getContext(), R.color.onfido_black));
        ViewExtensionsKt.toGone((TextView) _$_findCachedViewById(R.id.mainText));
        ViewExtensionsKt.toVisible((TextView) _$_findCachedViewById(R.id.secondaryText));
    }
}
