package com.pinterest.pinit;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.pinterest.pinit.a.a;

public class PinItButton extends ImageView {
    /* access modifiers changed from: private */
    public a _pinIt;

    public PinItButton(Context context) {
        this(context, null);
    }

    public PinItButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16973844);
    }

    public PinItButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this._pinIt = new a();
        setScaleType(ScaleType.FIT_XY);
        setImageDrawable(a.a(getContext()));
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (view != null) {
                    PinItButton.this._pinIt.a(view.getContext());
                }
            }
        });
    }

    public String getImageUrl() {
        return this._pinIt.d();
    }

    public void setImageUrl(String str) {
        this._pinIt.a(str);
    }

    public Uri getImageUri() {
        return this._pinIt.e();
    }

    public void setImageUri(Uri uri) {
        this._pinIt.a(uri);
    }

    public String getUrl() {
        return this._pinIt.f();
    }

    public void setUrl(String str) {
        this._pinIt.b(str);
    }

    public String getDescription() {
        return this._pinIt.g();
    }

    public void setDescription(String str) {
        this._pinIt.c(str);
    }

    public b getListener() {
        return this._pinIt.h();
    }

    public void setListener(b bVar) {
        this._pinIt.a(bVar);
    }

    public static String getPartnerId() {
        return a.i();
    }

    public void reset() {
        this._pinIt.b();
    }

    public static void setDebugMode(boolean z) {
        a.a(z);
    }

    public static void setPartnerId(String str) {
        a.d(str);
    }

    public static boolean isDebugMode() {
        return a.c();
    }

    public static boolean meetsRequirements() {
        return a.a();
    }
}
