package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class LifecycleRegistry extends Lifecycle {
    private static final String LOG_TAG = "LifecycleRegistry";
    private int mAddingObserverCounter = 0;
    private boolean mHandlingEvent = false;
    private final WeakReference<e> mLifecycleOwner;
    private boolean mNewEventOccurred = false;
    private FastSafeIterableMap<d, a> mObserverMap = new FastSafeIterableMap<>();
    private ArrayList<State> mParentStates = new ArrayList<>();
    private State mState;

    static class a {
        State a;
        GenericLifecycleObserver b;

        a(d dVar, State state) {
            this.b = f.a((Object) dVar);
            this.a = state;
        }

        /* access modifiers changed from: 0000 */
        public void a(e eVar, Event event) {
            State stateAfter = LifecycleRegistry.getStateAfter(event);
            this.a = LifecycleRegistry.min(this.a, stateAfter);
            this.b.onStateChanged(eVar, event);
            this.a = stateAfter;
        }
    }

    public LifecycleRegistry(@NonNull e eVar) {
        this.mLifecycleOwner = new WeakReference<>(eVar);
        this.mState = State.INITIALIZED;
    }

    @MainThread
    public void markState(@NonNull State state) {
        moveToState(state);
    }

    public void handleLifecycleEvent(@NonNull Event event) {
        moveToState(getStateAfter(event));
    }

    private void moveToState(State state) {
        if (this.mState != state) {
            this.mState = state;
            if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
                this.mNewEventOccurred = true;
                return;
            }
            this.mHandlingEvent = true;
            sync();
            this.mHandlingEvent = false;
        }
    }

    private boolean isSynced() {
        boolean z = true;
        if (this.mObserverMap.size() == 0) {
            return true;
        }
        State state = ((a) this.mObserverMap.eldest().getValue()).a;
        State state2 = ((a) this.mObserverMap.newest().getValue()).a;
        if (!(state == state2 && this.mState == state2)) {
            z = false;
        }
        return z;
    }

    private State calculateTargetState(d dVar) {
        Entry ceil = this.mObserverMap.ceil(dVar);
        State state = null;
        State state2 = ceil != null ? ((a) ceil.getValue()).a : null;
        if (!this.mParentStates.isEmpty()) {
            state = (State) this.mParentStates.get(this.mParentStates.size() - 1);
        }
        return min(min(this.mState, state2), state);
    }

    public void addObserver(@NonNull d dVar) {
        a aVar = new a(dVar, this.mState == State.DESTROYED ? State.DESTROYED : State.INITIALIZED);
        if (((a) this.mObserverMap.putIfAbsent(dVar, aVar)) == null) {
            e eVar = (e) this.mLifecycleOwner.get();
            if (eVar != null) {
                boolean z = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
                State calculateTargetState = calculateTargetState(dVar);
                this.mAddingObserverCounter++;
                while (aVar.a.compareTo(calculateTargetState) < 0 && this.mObserverMap.contains(dVar)) {
                    pushParentState(aVar.a);
                    aVar.a(eVar, upEvent(aVar.a));
                    popParentState();
                    calculateTargetState = calculateTargetState(dVar);
                }
                if (!z) {
                    sync();
                }
                this.mAddingObserverCounter--;
            }
        }
    }

    private void popParentState() {
        this.mParentStates.remove(this.mParentStates.size() - 1);
    }

    private void pushParentState(State state) {
        this.mParentStates.add(state);
    }

    public void removeObserver(@NonNull d dVar) {
        this.mObserverMap.remove(dVar);
    }

    public int getObserverCount() {
        return this.mObserverMap.size();
    }

    @NonNull
    public State getCurrentState() {
        return this.mState;
    }

    static State getStateAfter(Event event) {
        switch (event) {
            case ON_CREATE:
            case ON_STOP:
                return State.CREATED;
            case ON_START:
            case ON_PAUSE:
                return State.STARTED;
            case ON_RESUME:
                return State.RESUMED;
            case ON_DESTROY:
                return State.DESTROYED;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected event value ");
                sb.append(event);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private static Event downEvent(State state) {
        switch (state) {
            case INITIALIZED:
                throw new IllegalArgumentException();
            case CREATED:
                return Event.ON_DESTROY;
            case STARTED:
                return Event.ON_STOP;
            case RESUMED:
                return Event.ON_PAUSE;
            case DESTROYED:
                throw new IllegalArgumentException();
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected state value ");
                sb.append(state);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private static Event upEvent(State state) {
        switch (state) {
            case INITIALIZED:
            case DESTROYED:
                return Event.ON_CREATE;
            case CREATED:
                return Event.ON_START;
            case STARTED:
                return Event.ON_RESUME;
            case RESUMED:
                throw new IllegalArgumentException();
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected state value ");
                sb.append(state);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private void forwardPass(e eVar) {
        d iteratorWithAdditions = this.mObserverMap.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext() && !this.mNewEventOccurred) {
            Entry entry = (Entry) iteratorWithAdditions.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                pushParentState(aVar.a);
                aVar.a(eVar, upEvent(aVar.a));
                popParentState();
            }
        }
    }

    private void backwardPass(e eVar) {
        Iterator descendingIterator = this.mObserverMap.descendingIterator();
        while (descendingIterator.hasNext() && !this.mNewEventOccurred) {
            Entry entry = (Entry) descendingIterator.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                Event downEvent = downEvent(aVar.a);
                pushParentState(getStateAfter(downEvent));
                aVar.a(eVar, downEvent);
                popParentState();
            }
        }
    }

    private void sync() {
        e eVar = (e) this.mLifecycleOwner.get();
        if (eVar == null) {
            Log.w(LOG_TAG, "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo(((a) this.mObserverMap.eldest().getValue()).a) < 0) {
                backwardPass(eVar);
            }
            Entry newest = this.mObserverMap.newest();
            if (!this.mNewEventOccurred && newest != null && this.mState.compareTo(((a) newest.getValue()).a) > 0) {
                forwardPass(eVar);
            }
        }
        this.mNewEventOccurred = false;
    }

    static State min(@NonNull State state, @Nullable State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }
}
