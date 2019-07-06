package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class e {
    private static final String a = "e";
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static Context b;
    private static zze c;

    public static zze a(Context context) throws GooglePlayServicesNotAvailableException {
        zze zze;
        Preconditions.checkNotNull(context);
        if (c != null) {
            return c;
        }
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        if (isGooglePlayServicesAvailable != 0) {
            throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
        Log.i(a, "Making Creator dynamically");
        IBinder iBinder = (IBinder) a(b(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
        if (iBinder == null) {
            zze = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            zze = queryLocalInterface instanceof zze ? (zze) queryLocalInterface : new zzf(iBinder);
        }
        c = zze;
        try {
            c.zza(ObjectWrapper.wrap(b(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            return c;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static <T> T a(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException unused) {
            String str = "Unable to instantiate the dynamic class ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        } catch (IllegalAccessException unused2) {
            String str2 = "Unable to call the default constructor of ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
        }
    }

    private static <T> T a(ClassLoader classLoader, String str) {
        try {
            return a(((ClassLoader) Preconditions.checkNotNull(classLoader)).loadClass(str));
        } catch (ClassNotFoundException unused) {
            String str2 = "Unable to find dynamic class ";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    @Nullable
    private static Context b(Context context) {
        if (b != null) {
            return b;
        }
        Context c2 = c(context);
        b = c2;
        return c2;
    }

    @Nullable
    private static Context c(Context context) {
        try {
            return DynamiteModule.a(context, DynamiteModule.a, "com.google.android.gms.maps_dynamite").a();
        } catch (Throwable th) {
            Log.e(a, "Failed to load maps module, use legacy", th);
            return GooglePlayServicesUtil.getRemoteContext(context);
        }
    }
}
