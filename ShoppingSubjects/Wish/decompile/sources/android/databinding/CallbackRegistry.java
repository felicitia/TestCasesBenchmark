package android.databinding;

import java.util.ArrayList;
import java.util.List;

public class CallbackRegistry<C, T, A> implements Cloneable {
    private List<C> mCallbacks;
    private long mFirst64Removed;
    private int mNotificationLevel;
    private final NotifierCallback<C, T, A> mNotifier;
    private long[] mRemainderRemoved;

    public static abstract class NotifierCallback<C, T, A> {
        public abstract void onNotifyCallback(C c, T t, int i, A a);
    }

    public synchronized void notifyCallbacks(T t, int i, A a) {
        this.mNotificationLevel++;
        notifyRecurse(t, i, a);
        this.mNotificationLevel--;
        if (this.mNotificationLevel == 0) {
            if (this.mRemainderRemoved != null) {
                for (int length = this.mRemainderRemoved.length - 1; length >= 0; length--) {
                    long j = this.mRemainderRemoved[length];
                    if (j != 0) {
                        removeRemovedCallbacks((length + 1) * 64, j);
                        this.mRemainderRemoved[length] = 0;
                    }
                }
            }
            if (this.mFirst64Removed != 0) {
                removeRemovedCallbacks(0, this.mFirst64Removed);
                this.mFirst64Removed = 0;
            }
        }
    }

    private void notifyFirst64(T t, int i, A a) {
        notifyCallbacks(t, i, a, 0, Math.min(64, this.mCallbacks.size()), this.mFirst64Removed);
    }

    private void notifyRecurse(T t, int i, A a) {
        int size = this.mCallbacks.size();
        int length = this.mRemainderRemoved == null ? -1 : this.mRemainderRemoved.length - 1;
        notifyRemainder(t, i, a, length);
        notifyCallbacks(t, i, a, (length + 2) * 64, size, 0);
    }

    private void notifyRemainder(T t, int i, A a, int i2) {
        if (i2 < 0) {
            notifyFirst64(t, i, a);
            return;
        }
        long j = this.mRemainderRemoved[i2];
        int i3 = (i2 + 1) * 64;
        int min = Math.min(this.mCallbacks.size(), i3 + 64);
        notifyRemainder(t, i, a, i2 - 1);
        notifyCallbacks(t, i, a, i3, min, j);
    }

    private void notifyCallbacks(T t, int i, A a, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.mNotifier.onNotifyCallback(this.mCallbacks.get(i2), t, i, a);
            }
            j2 <<= 1;
            i2++;
        }
    }

    private boolean isRemoved(int i) {
        boolean z = true;
        if (i < 64) {
            if ((this.mFirst64Removed & (1 << i)) == 0) {
                z = false;
            }
            return z;
        } else if (this.mRemainderRemoved == null) {
            return false;
        } else {
            int i2 = (i / 64) - 1;
            if (i2 >= this.mRemainderRemoved.length) {
                return false;
            }
            if ((this.mRemainderRemoved[i2] & (1 << (i % 64))) == 0) {
                z = false;
            }
            return z;
        }
    }

    private void removeRemovedCallbacks(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.mCallbacks.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    public synchronized CallbackRegistry<C, T, A> clone() {
        CallbackRegistry<C, T, A> callbackRegistry;
        CloneNotSupportedException e;
        try {
            callbackRegistry = (CallbackRegistry) super.clone();
            try {
                callbackRegistry.mFirst64Removed = 0;
                callbackRegistry.mRemainderRemoved = null;
                callbackRegistry.mNotificationLevel = 0;
                callbackRegistry.mCallbacks = new ArrayList();
                int size = this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    if (!isRemoved(i)) {
                        callbackRegistry.mCallbacks.add(this.mCallbacks.get(i));
                    }
                }
            } catch (CloneNotSupportedException e2) {
                e = e2;
                e.printStackTrace();
                return callbackRegistry;
            }
        } catch (CloneNotSupportedException e3) {
            CloneNotSupportedException cloneNotSupportedException = e3;
            callbackRegistry = null;
            e = cloneNotSupportedException;
            e.printStackTrace();
            return callbackRegistry;
        }
        return callbackRegistry;
    }
}
