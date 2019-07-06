package androidx.work;

import android.os.Build.VERSION;

public final class Constraints {
    public static final Constraints NONE = new Builder().build();
    ContentUriTriggers mContentUriTriggers;
    NetworkType mRequiredNetworkType;
    boolean mRequiresBatteryNotLow;
    boolean mRequiresCharging;
    boolean mRequiresDeviceIdle;
    boolean mRequiresStorageNotLow;

    public static final class Builder {
        /* access modifiers changed from: private */
        public ContentUriTriggers mContentUriTriggers = new ContentUriTriggers();
        /* access modifiers changed from: private */
        public NetworkType mRequiredNetworkType = NetworkType.NOT_REQUIRED;
        /* access modifiers changed from: private */
        public boolean mRequiresBatteryNotLow = false;
        /* access modifiers changed from: private */
        public boolean mRequiresCharging = false;
        /* access modifiers changed from: private */
        public boolean mRequiresDeviceIdle = false;
        /* access modifiers changed from: private */
        public boolean mRequiresStorageNotLow = false;

        public Constraints build() {
            return new Constraints(this);
        }
    }

    public Constraints() {
    }

    private Constraints(Builder builder) {
        this.mRequiresCharging = builder.mRequiresCharging;
        this.mRequiresDeviceIdle = VERSION.SDK_INT >= 23 && builder.mRequiresDeviceIdle;
        this.mRequiredNetworkType = builder.mRequiredNetworkType;
        this.mRequiresBatteryNotLow = builder.mRequiresBatteryNotLow;
        this.mRequiresStorageNotLow = builder.mRequiresStorageNotLow;
        this.mContentUriTriggers = VERSION.SDK_INT >= 24 ? builder.mContentUriTriggers : new ContentUriTriggers();
    }

    public NetworkType getRequiredNetworkType() {
        return this.mRequiredNetworkType;
    }

    public void setRequiredNetworkType(NetworkType networkType) {
        this.mRequiredNetworkType = networkType;
    }

    public boolean requiresCharging() {
        return this.mRequiresCharging;
    }

    public void setRequiresCharging(boolean z) {
        this.mRequiresCharging = z;
    }

    public boolean requiresDeviceIdle() {
        return this.mRequiresDeviceIdle;
    }

    public void setRequiresDeviceIdle(boolean z) {
        this.mRequiresDeviceIdle = z;
    }

    public boolean requiresBatteryNotLow() {
        return this.mRequiresBatteryNotLow;
    }

    public void setRequiresBatteryNotLow(boolean z) {
        this.mRequiresBatteryNotLow = z;
    }

    public boolean requiresStorageNotLow() {
        return this.mRequiresStorageNotLow;
    }

    public void setRequiresStorageNotLow(boolean z) {
        this.mRequiresStorageNotLow = z;
    }

    public void setContentUriTriggers(ContentUriTriggers contentUriTriggers) {
        this.mContentUriTriggers = contentUriTriggers;
    }

    public ContentUriTriggers getContentUriTriggers() {
        return this.mContentUriTriggers;
    }

    public boolean hasContentUriTriggers() {
        return this.mContentUriTriggers.size() > 0;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Constraints constraints = (Constraints) obj;
        if (!(this.mRequiredNetworkType == constraints.mRequiredNetworkType && this.mRequiresCharging == constraints.mRequiresCharging && this.mRequiresDeviceIdle == constraints.mRequiresDeviceIdle && this.mRequiresBatteryNotLow == constraints.mRequiresBatteryNotLow && this.mRequiresStorageNotLow == constraints.mRequiresStorageNotLow && (this.mContentUriTriggers == null ? constraints.mContentUriTriggers == null : this.mContentUriTriggers.equals(constraints.mContentUriTriggers)))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((((this.mRequiredNetworkType.hashCode() * 31) + (this.mRequiresCharging ? 1 : 0)) * 31) + (this.mRequiresDeviceIdle ? 1 : 0)) * 31) + (this.mRequiresBatteryNotLow ? 1 : 0)) * 31) + (this.mRequiresStorageNotLow ? 1 : 0)) * 31) + (this.mContentUriTriggers != null ? this.mContentUriTriggers.hashCode() : 0);
    }
}
