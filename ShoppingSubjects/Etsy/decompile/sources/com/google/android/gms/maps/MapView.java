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
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.d;
import com.google.android.gms.maps.internal.e;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private final b zzbf;

    @VisibleForTesting
    static class a implements com.google.android.gms.maps.internal.a {
        private final ViewGroup a;
        private final IMapViewDelegate b;
        private View c;

        public a(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.b = (IMapViewDelegate) Preconditions.checkNotNull(iMapViewDelegate);
            this.a = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }

        public final View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public final void a() {
            try {
                this.b.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
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

        public final void a(e eVar) {
            try {
                this.b.getMapAsync(new k(this, eVar));
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

        public final void c(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                d.a(bundle, bundle2);
                this.b.onEnterAmbient(bundle2);
                d.a(bundle2, bundle);
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
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
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

        public final void h() {
            try {
                this.b.onExitAmbient();
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
        private final GoogleMapOptions d;
        private final List<e> e = new ArrayList();

        @VisibleForTesting
        b(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.a = viewGroup;
            this.b = context;
            this.d = googleMapOptions;
        }

        /* access modifiers changed from: protected */
        public final void a(c<a> cVar) {
            this.c = cVar;
            if (this.c != null && a() == null) {
                try {
                    d.a(this.b);
                    IMapViewDelegate zza = e.a(this.b).zza(ObjectWrapper.wrap(this.b), this.d);
                    if (zza != null) {
                        this.c.a(new a(this.a, zza));
                        for (e a2 : this.e) {
                            ((a) a()).a(a2);
                        }
                        this.e.clear();
                    }
                } catch (RemoteException e2) {
                    throw new RuntimeRemoteException(e2);
                } catch (GooglePlayServicesNotAvailableException unused) {
                }
            }
        }

        public final void a(e eVar) {
            if (a() != null) {
                ((a) a()).a(eVar);
            } else {
                this.e.add(eVar);
            }
        }
    }

    public MapView(Context context) {
        super(context);
        this.zzbf = new b(this, context, null);
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbf = new b(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbf = new b(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.zzbf = new b(this, context, googleMapOptions);
        setClickable(true);
    }

    public void getMapAsync(e eVar) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        this.zzbf.a(eVar);
    }

    public final void onCreate(Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            this.zzbf.a(bundle);
            if (this.zzbf.a() == null) {
                com.google.android.gms.dynamic.a.b((FrameLayout) this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void onDestroy() {
        this.zzbf.g();
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        b bVar = this.zzbf;
        if (bVar.a() != null) {
            ((a) bVar.a()).c(bundle);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        b bVar = this.zzbf;
        if (bVar.a() != null) {
            ((a) bVar.a()).h();
        }
    }

    public final void onLowMemory() {
        this.zzbf.h();
    }

    public final void onPause() {
        this.zzbf.d();
    }

    public final void onResume() {
        this.zzbf.c();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzbf.b(bundle);
    }

    public final void onStart() {
        this.zzbf.b();
    }

    public final void onStop() {
        this.zzbf.e();
    }
}
