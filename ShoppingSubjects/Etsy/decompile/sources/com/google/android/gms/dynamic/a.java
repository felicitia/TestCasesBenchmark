package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.dynamic.b;
import java.util.LinkedList;

public abstract class a<T extends b> {
    /* access modifiers changed from: private */
    public T a;
    /* access modifiers changed from: private */
    public Bundle b;
    /* access modifiers changed from: private */
    public LinkedList<C0137a> c;
    private final c<T> d = new d(this);

    /* renamed from: com.google.android.gms.dynamic.a$a reason: collision with other inner class name */
    private interface C0137a {
        int a();

        void a(b bVar);
    }

    private final void a(int i) {
        while (!this.c.isEmpty() && ((C0137a) this.c.getLast()).a() >= i) {
            this.c.removeLast();
        }
    }

    private final void a(Bundle bundle, C0137a aVar) {
        if (this.a != null) {
            aVar.a(this.a);
            return;
        }
        if (this.c == null) {
            this.c = new LinkedList<>();
        }
        this.c.add(aVar);
        if (bundle != null) {
            if (this.b == null) {
                this.b = (Bundle) bundle.clone();
            } else {
                this.b.putAll(bundle);
            }
        }
        a(this.d);
    }

    public static void b(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String errorMessage = ConnectionErrorMessages.getErrorMessage(context, isGooglePlayServicesAvailable);
        String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(errorMessage);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = instance.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(errorDialogButtonMessage);
            linearLayout.addView(button);
            button.setOnClickListener(new h(context, errorResolutionIntent));
        }
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        g gVar = new g(this, frameLayout, layoutInflater, viewGroup, bundle);
        a(bundle, (C0137a) gVar);
        if (this.a == null) {
            a(frameLayout);
        }
        return frameLayout;
    }

    public T a() {
        return this.a;
    }

    public void a(Activity activity, Bundle bundle, Bundle bundle2) {
        a(bundle2, (C0137a) new e(this, activity, bundle, bundle2));
    }

    public void a(Bundle bundle) {
        a(bundle, (C0137a) new f(this, bundle));
    }

    /* access modifiers changed from: protected */
    public void a(FrameLayout frameLayout) {
        b(frameLayout);
    }

    /* access modifiers changed from: protected */
    public abstract void a(c<T> cVar);

    public void b() {
        a((Bundle) null, (C0137a) new i(this));
    }

    public void b(Bundle bundle) {
        if (this.a != null) {
            this.a.b(bundle);
            return;
        }
        if (this.b != null) {
            bundle.putAll(this.b);
        }
    }

    public void c() {
        a((Bundle) null, (C0137a) new j(this));
    }

    public void d() {
        if (this.a != null) {
            this.a.c();
        } else {
            a(5);
        }
    }

    public void e() {
        if (this.a != null) {
            this.a.d();
        } else {
            a(4);
        }
    }

    public void f() {
        if (this.a != null) {
            this.a.e();
        } else {
            a(2);
        }
    }

    public void g() {
        if (this.a != null) {
            this.a.f();
        } else {
            a(1);
        }
    }

    public void h() {
        if (this.a != null) {
            this.a.g();
        }
    }
}
