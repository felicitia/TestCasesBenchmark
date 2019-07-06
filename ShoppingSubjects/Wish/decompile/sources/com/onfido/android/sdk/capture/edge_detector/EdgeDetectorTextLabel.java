package com.onfido.android.sdk.capture.edge_detector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public final class EdgeDetectorTextLabel extends FrameLayout {
    private HashMap a;

    public EdgeDetectorTextLabel(Context context, AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet, 0);
        a();
    }

    public EdgeDetectorTextLabel(Context context, AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet, i);
        a();
    }

    private final void a() {
        View.inflate(getContext(), R.layout.onfido_edge_detection_label, this);
    }

    public View _$_findCachedViewById(int i) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        View view = (View) this.a.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.a.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void updateText(String str) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        ((TextView) _$_findCachedViewById(R.id.labelText)).setText(str);
    }
}
