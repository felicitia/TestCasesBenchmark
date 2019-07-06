package com.google.android.gms.common.api.internal;

public final class ListenerHolder<L> {
    private volatile L zzli;

    public static final class ListenerKey<L> {
        private final L zzli;
        private final String zzll;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zzli == listenerKey.zzli && this.zzll.equals(listenerKey.zzll);
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zzli) * 31) + this.zzll.hashCode();
        }
    }

    public final void clear() {
        this.zzli = null;
    }
}
