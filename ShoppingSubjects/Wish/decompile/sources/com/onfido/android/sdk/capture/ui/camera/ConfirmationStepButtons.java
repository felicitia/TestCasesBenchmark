package com.onfido.android.sdk.capture.ui.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import java.util.HashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

public final class ConfirmationStepButtons extends LinearLayout {
    public static final Companion Companion = new Companion(null);
    private Button a;
    private Button b;
    /* access modifiers changed from: private */
    public Listener c;
    private HashMap d;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public interface Listener {
        void confirmed();

        void discarded();
    }

    static final class a implements Runnable {
        final /* synthetic */ Button a;

        a(Button button) {
            this.a = button;
        }

        public final void run() {
            ViewExtensionsKt.toGone(this.a);
        }
    }

    static final class b implements OnClickListener {
        final /* synthetic */ ConfirmationStepButtons a;

        b(ConfirmationStepButtons confirmationStepButtons) {
            this.a = confirmationStepButtons;
        }

        public final void onClick(View view) {
            Listener access$getListener$p = this.a.c;
            if (access$getListener$p != null) {
                access$getListener$p.confirmed();
            }
        }
    }

    static final class c implements OnClickListener {
        final /* synthetic */ ConfirmationStepButtons a;

        c(ConfirmationStepButtons confirmationStepButtons) {
            this.a = confirmationStepButtons;
        }

        public final void onClick(View view) {
            Listener access$getListener$p = this.a.c;
            if (access$getListener$p != null) {
                access$getListener$p.discarded();
            }
        }
    }

    static final class d implements OnClickListener {
        final /* synthetic */ ConfirmationStepButtons a;
        final /* synthetic */ CaptureType b;

        d(ConfirmationStepButtons confirmationStepButtons, CaptureType captureType) {
            this.a = confirmationStepButtons;
            this.b = captureType;
        }

        public final void onClick(View view) {
            Listener access$getListener$p = this.a.c;
            if (access$getListener$p != null) {
                access$getListener$p.discarded();
            }
        }
    }

    static final class e implements OnClickListener {
        final /* synthetic */ ConfirmationStepButtons a;

        e(ConfirmationStepButtons confirmationStepButtons) {
            this.a = confirmationStepButtons;
        }

        public final void onClick(View view) {
            Listener access$getListener$p = this.a.c;
            if (access$getListener$p != null) {
                access$getListener$p.confirmed();
            }
        }
    }

    static final class f implements OnClickListener {
        final /* synthetic */ ConfirmationStepButtons a;

        f(ConfirmationStepButtons confirmationStepButtons) {
            this.a = confirmationStepButtons;
        }

        public final void onClick(View view) {
            Listener access$getListener$p = this.a.c;
            if (access$getListener$p != null) {
                access$getListener$p.discarded();
            }
        }
    }

    public ConfirmationStepButtons(Context context) {
        this(context, null, 0, 6, null);
    }

    public ConfirmationStepButtons(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public ConfirmationStepButtons(Context context, AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i);
    }

    public /* synthetic */ ConfirmationStepButtons(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i);
    }

    private final void a() {
        Button button = (Button) _$_findCachedViewById(R.id.button_primary);
        button.setOnClickListener(new b(this));
        Intrinsics.checkExpressionValueIsNotNull(button, "button_confirmation_vert…?.confirmed() }\n        }");
        this.a = button;
        Button button2 = (Button) _$_findCachedViewById(R.id.button_secondary);
        button2.setOnClickListener(new c(this));
        button2.setText(button2.getResources().getString(R.string.onfido_discard));
        ViewExtensionsKt.toVisible(button2);
        Intrinsics.checkExpressionValueIsNotNull(button2, "button_discard_vert.appl…    toVisible()\n        }");
        this.b = button2;
    }

    private final void b() {
        Button button;
        String str;
        if (((LinearLayout) _$_findCachedViewById(R.id.verticalContainer)) != null) {
            Button button2 = (Button) _$_findCachedViewById(R.id.button_primary);
            Intrinsics.checkExpressionValueIsNotNull(button2, "button_confirmation_vert");
            this.a = button2;
            button = (Button) _$_findCachedViewById(R.id.button_secondary);
            str = "button_discard_vert";
        } else {
            Button button3 = (Button) _$_findCachedViewById(R.id.button_primary);
            Intrinsics.checkExpressionValueIsNotNull(button3, "button_confirmation_horiz");
            this.a = button3;
            button = (Button) _$_findCachedViewById(R.id.button_secondary);
            str = "button_discard_horiz";
        }
        Intrinsics.checkExpressionValueIsNotNull(button, str);
        this.b = button;
        Button button4 = this.a;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonConfirmation");
        }
        button4.setText(button4.getResources().getString(R.string.onfido_confirm_face));
        button4.setOnClickListener(new e(this));
        Button button5 = this.b;
        if (button5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonDiscard");
        }
        button5.setText(button5.getResources().getString(R.string.onfido_discard_face));
        button5.setOnClickListener(new f(this));
        ViewExtensionsKt.toVisible(button5);
    }

    public View _$_findCachedViewById(int i) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        View view = (View) this.d.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.d.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void setDocumentCapture() {
        View.inflate(getContext(), R.layout.onfido_confirmation_step_buttons_vertical, this);
        a();
    }

    public final void setDocumentCaptureCopy(DocumentType documentType, boolean z) {
        int i;
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        if (z) {
            i = R.string.onfido_submit_my_picture;
        } else {
            switch (documentType) {
                case PASSPORT:
                    i = R.string.onfido_confirm_passport;
                    break;
                case RESIDENCE_PERMIT:
                    i = R.string.onfido_confirm_residence_permit;
                    break;
                case NATIONAL_IDENTITY_CARD:
                    i = R.string.onfido_confirm_national_id;
                    break;
                case DRIVING_LICENCE:
                    i = R.string.onfido_confirm_driving_license;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        Button button = this.a;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonConfirmation");
        }
        button.setText(getResources().getString(i));
    }

    public final void setErrorState(boolean z, CaptureType captureType) {
        int i;
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        if (z) {
            Button button = this.a;
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("buttonConfirmation");
            }
            switch (captureType) {
                case DOCUMENT:
                    i = R.string.onfido_discard;
                    break;
                case FACE:
                    i = R.string.onfido_discard_face;
                    break;
                default:
                    i = 0;
                    break;
            }
            button.setText(button.getResources().getString(i));
            button.setOnClickListener(new d(this, captureType));
            Button button2 = this.b;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("buttonDiscard");
            }
            button2.setText("");
            View view = button2;
            if (((LinearLayout) view.findViewById(R.id.verticalContainer)) != null) {
                button2.postDelayed(new a(button2), 300);
            } else {
                ViewExtensionsKt.toGone(view);
            }
        } else if (Intrinsics.areEqual(captureType, CaptureType.DOCUMENT)) {
            a();
        } else {
            b();
        }
    }

    public final void setFaceCapture() {
        View.inflate(getContext(), R.layout.onfido_confirmation_step_buttons, this);
        b();
    }

    public final void setListener(Listener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.c = listener;
    }
}
