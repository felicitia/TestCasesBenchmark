package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.util.Log;
import androidx.work.impl.constraints.ConstraintListener;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class ConstraintTracker<T> {
    protected final Context mAppContext;
    private T mCurrentState;
    private final Set<ConstraintListener<T>> mListeners = new LinkedHashSet();

    public abstract T getInitialState();

    public abstract void startTracking();

    public abstract void stopTracking();

    ConstraintTracker(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    public void addListener(ConstraintListener<T> constraintListener) {
        if (this.mListeners.add(constraintListener)) {
            if (this.mListeners.size() == 1) {
                this.mCurrentState = getInitialState();
                Log.d("ConstraintTracker", String.format("%s: initial state = %s", new Object[]{getClass().getSimpleName(), this.mCurrentState}));
                startTracking();
            }
            constraintListener.onConstraintChanged(this.mCurrentState);
        }
    }

    public void removeListener(ConstraintListener<T> constraintListener) {
        if (this.mListeners.remove(constraintListener) && this.mListeners.isEmpty()) {
            stopTracking();
        }
    }

    public void setState(T t) {
        if (this.mCurrentState != t && (this.mCurrentState == null || !this.mCurrentState.equals(t))) {
            this.mCurrentState = t;
            for (ConstraintListener onConstraintChanged : this.mListeners) {
                onConstraintChanged.onConstraintChanged(this.mCurrentState);
            }
        }
    }
}
