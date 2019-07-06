package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import java.util.HashMap;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public final class LivenessOverlayView extends RelativeLayout {
    public static final Companion Companion = new Companion(null);
    private RectF a;
    /* access modifiers changed from: private */
    public LivenessOverlayListener b;
    private HashMap c;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public interface LivenessOverlayListener {
        void onNextClick();
    }

    static final class a implements OnClickListener {
        final /* synthetic */ LivenessOverlayView a;
        final /* synthetic */ Context b;

        a(LivenessOverlayView livenessOverlayView, Context context) {
            this.a = livenessOverlayView;
            this.b = context;
        }

        public final void onClick(View view) {
            LivenessOverlayListener access$getListener$p = this.a.b;
            if (access$getListener$p != null) {
                access$getListener$p.onNextClick();
            }
        }
    }

    static final class b implements Runnable {
        final /* synthetic */ LivenessOverlayView a;
        final /* synthetic */ float b;
        final /* synthetic */ float c;

        b(LivenessOverlayView livenessOverlayView, float f, float f2) {
            this.a = livenessOverlayView;
            this.b = f;
            this.c = f2;
        }

        public final void run() {
            this.a.a(this.b, this.c);
        }
    }

    public LivenessOverlayView(Context context) {
        this(context, null, 0, 6, null);
    }

    public LivenessOverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public LivenessOverlayView(Context context, AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i);
        View inflate = View.inflate(context, R.layout.onfido_liveness_overlay_view, this);
        ((Button) inflate.findViewById(R.id.next)).setOnClickListener(new a(this, context));
        ((TextView) inflate.findViewById(R.id.challengeFinish)).setText(context.getString(R.string.onfido_liveness_challenge_next));
        a(1.0f, 0.0f);
        inflate.setWillNotDraw(false);
    }

    public /* synthetic */ LivenessOverlayView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i);
    }

    /* access modifiers changed from: private */
    public final void a(float f, float f2) {
        ((LinearLayout) _$_findCachedViewById(R.id.recordingContainer)).animate().alpha(f).withEndAction(new b(this, f2, f)).setDuration(800).start();
    }

    public View _$_findCachedViewById(int i) {
        if (this.c == null) {
            this.c = new HashMap();
        }
        View view = (View) this.c.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.c.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void onLastChallenge() {
        LayoutParams layoutParams = ((Button) _$_findCachedViewById(R.id.next)).getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.width = getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_liveness_stop_record_button_height);
        layoutParams2.height = getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_liveness_stop_record_button_height);
        layoutParams2.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_margin_bottom_camera_button));
        ((TextView) _$_findCachedViewById(R.id.challengeFinish)).setText(getContext().getString(R.string.onfido_liveness_challenge_stop));
        ((Button) _$_findCachedViewById(R.id.next)).setBackgroundResource(R.drawable.onfido_stop_record);
        ((Button) _$_findCachedViewById(R.id.next)).setText("");
        ((Button) _$_findCachedViewById(R.id.next)).setLayoutParams(layoutParams2);
    }

    public final void setListener(LivenessOverlayListener livenessOverlayListener) {
        Intrinsics.checkParameterIsNotNull(livenessOverlayListener, "listener");
        this.b = livenessOverlayListener;
    }

    public final void updateInfo(LivenessChallenge livenessChallenge) {
        Intrinsics.checkParameterIsNotNull(livenessChallenge, "challenge");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        RectF rectF = this.a;
        if (rectF == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureRect");
        }
        float f = rectF.top;
        RectF rectF2 = this.a;
        if (rectF2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureRect");
        }
        livenessChallenge.onDraw(context, (int) (f + (rectF2.height() / ((float) 2))), this);
    }

    public final void updateTextPosition(RectF rectF) {
        Intrinsics.checkParameterIsNotNull(rectF, "rect");
        this.a = rectF;
    }
}
