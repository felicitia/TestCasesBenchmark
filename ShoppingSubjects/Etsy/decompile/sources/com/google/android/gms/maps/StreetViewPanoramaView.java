package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.d;
import com.google.android.gms.maps.internal.e;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private final b zzcc;

    @VisibleForTesting
    static class a implements com.google.android.gms.maps.internal.b {
        private final ViewGroup a;
        private final IStreetViewPanoramaViewDelegate b;
        private View c;

        public a(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.b = (IStreetViewPanoramaViewDelegate) Preconditions.checkNotNull(iStreetViewPanoramaViewDelegate);
            this.a = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }

        public final View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void a() {
            try {
                this.b.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void a(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                d.a(bundle, bundle2);
                this.b.onCreate(bundle2);
                d.a(bundle2, bundle);
                this.c = (View) ObjectWrapper.unwrap(this.b.getView());
                this.a.removeAllViews();
                this.a.addView(this.c);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a(f fVar) {
            try {
                this.b.getStreetViewPanoramaAsync(new m(this, fVar));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void b() {
            try {
                this.b.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void b(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                d.a(bundle, bundle2);
                this.b.onSaveInstanceState(bundle2);
                d.a(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void c() {
            try {
                this.b.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void d() {
            try {
                this.b.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void e() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void f() {
            try {
                this.b.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void g() {
            try {
                this.b.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    @VisibleForTesting
    static class b extends com.google.android.gms.dynamic.a<a> {
        private final ViewGroup a;
        private final Context b;
        private c<a> c;
        private final StreetViewPanoramaOptions d;
        private final List<f> e = new ArrayList();

        @VisibleForTesting
        b(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.a = viewGroup;
            this.b = context;
            this.d = streetViewPanoramaOptions;
        }

        /* access modifiers changed from: protected */
        public final void a(c<a> cVar) {
            this.c = cVar;
            if (this.c != null && a() == null) {
                try {
                    d.a(this.b);
                    this.c.a(new a(this.a, e.a(this.b).zza(ObjectWrapper.wrap(this.b), this.d)));
                    for (f a2 : this.e) {
                        ((a) a()).a(a2);
                    }
                    this.e.clear();
                } catch (RemoteException e2) {
                    throw new RuntimeRemoteException(e2);
                } catch (GooglePlayServicesNotAvailableException unused) {
                }
            }
        }

        public final void a(f fVar) {
            if (a() != null) {
                ((a) a()).a(fVar);
            } else {
                this.e.add(fVar);
            }
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzcc = new b(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzcc = new b(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzcc = new b(this, context, null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.zzcc = new b(this, context, streetViewPanoramaOptions);
    }

    public void getStreetViewPanoramaAsync(f fVar) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzcc.a(fVar);
    }

    public final void onCreate(Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            this.zzcc.a(bundle);
            if (this.zzcc.a() == null) {
                com.google.android.gms.dynamic.a.b((FrameLayout) this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onDestroy() {
        this.zzcc.g();
    }

    public final void onLowMemory() {
        this.zzcc.h();
    }

    public final void onPause() {
        this.zzcc.d();
    }

    public void onResume() {
        this.zzcc.c();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzcc.b(bundle);
    }

    public void onStart() {
        this.zzcc.b();
    }

    public void onStop() {
        this.zzcc.e();
    }
}
