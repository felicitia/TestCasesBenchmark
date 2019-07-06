package com.google.android.gms.c;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.Method;

public class a {
    private static final GoogleApiAvailabilityLight a = GoogleApiAvailabilityLight.getInstance();
    private static final Object b = new Object();
    private static Method c;

    public static void a(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Preconditions.checkNotNull(context, "Context must not be null");
        a.verifyGooglePlayServicesIsAvailable(context, 11925000);
        try {
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext == null) {
                if (Log.isLoggable("ProviderInstaller", 6)) {
                    Log.e("ProviderInstaller", "Failed to get remote context");
                }
                throw new GooglePlayServicesNotAvailableException(8);
            }
            synchronized (b) {
                try {
                    if (c == null) {
                        c = remoteContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[]{Context.class});
                    }
                    c.invoke(null, new Object[]{remoteContext});
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    if (Log.isLoggable("ProviderInstaller", 6)) {
                        String message = cause == null ? e.getMessage() : cause.getMessage();
                        String str = "ProviderInstaller";
                        String str2 = "Failed to install provider: ";
                        String valueOf = String.valueOf(message);
                        Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    }
                    throw new GooglePlayServicesNotAvailableException(8);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (NotFoundException unused) {
            if (Log.isLoggable("ProviderInstaller", 6)) {
                Log.e("ProviderInstaller", "Failed to get remote context - resource not found");
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}
