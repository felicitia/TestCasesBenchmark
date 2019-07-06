package com.crittercism.internal;

import android.location.Location;

public final class an {
    private static Location a;

    public static synchronized void a(Location location) {
        synchronized (an.class) {
            if (location != null) {
                location = new Location(location);
            }
            a = location;
        }
    }

    public static synchronized Location a() {
        Location location;
        synchronized (an.class) {
            location = a;
        }
        return location;
    }

    public static synchronized boolean b() {
        boolean z;
        synchronized (an.class) {
            z = a != null;
        }
        return z;
    }
}
