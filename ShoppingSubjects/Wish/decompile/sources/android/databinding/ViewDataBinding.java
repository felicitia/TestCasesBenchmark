package android.databinding;

import android.annotation.TargetApi;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.CallbackRegistry.NotifierCallback;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.android.databinding.library.R;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class ViewDataBinding extends BaseObservable {
    private static final int BINDING_NUMBER_START = "binding_".length();
    private static final CreateWeakListener CREATE_LIST_LISTENER = new CreateWeakListener() {
    };
    private static final CreateWeakListener CREATE_LIVE_DATA_LISTENER = new CreateWeakListener() {
    };
    private static final CreateWeakListener CREATE_MAP_LISTENER = new CreateWeakListener() {
    };
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER = new CreateWeakListener() {
    };
    private static final NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER = new NotifierCallback<OnRebindCallback, ViewDataBinding, Void>() {
        public void onNotifyCallback(OnRebindCallback onRebindCallback, ViewDataBinding viewDataBinding, int i, Void voidR) {
            switch (i) {
                case 1:
                    if (!onRebindCallback.onPreBind(viewDataBinding)) {
                        viewDataBinding.mRebindHalted = true;
                        return;
                    }
                    return;
                case 2:
                    onRebindCallback.onCanceled(viewDataBinding);
                    return;
                case 3:
                    onRebindCallback.onBound(viewDataBinding);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public static final OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    static int SDK_INT = VERSION.SDK_INT;
    private static final boolean USE_CHOREOGRAPHER = (SDK_INT >= 16);
    private static final ReferenceQueue<ViewDataBinding> sReferenceQueue = new ReferenceQueue<>();
    protected final DataBindingComponent mBindingComponent;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final FrameCallback mFrameCallback;
    private boolean mIsExecutingPendingBindings;
    private LifecycleOwner mLifecycleOwner;
    private WeakListener[] mLocalFieldObservers;
    /* access modifiers changed from: private */
    public boolean mPendingRebind = false;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    /* access modifiers changed from: private */
    public boolean mRebindHalted = false;
    /* access modifiers changed from: private */
    public final Runnable mRebindRunnable = new Runnable() {
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.mPendingRebind = false;
            }
            ViewDataBinding.processReferenceQueue();
            if (VERSION.SDK_INT < 19 || ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                ViewDataBinding.this.executePendingBindings();
                return;
            }
            ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
            ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
        }
    };
    /* access modifiers changed from: private */
    public final View mRoot;
    private Handler mUIThreadHandler;

    private interface CreateWeakListener {
    }

    protected static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;
    }

    private interface ObservableReference<T> {
        void removeListener(T t);
    }

    public class OnStartListener implements LifecycleObserver {
        final /* synthetic */ ViewDataBinding this$0;

        @OnLifecycleEvent(Event.ON_START)
        public void onStart() {
            this.this$0.executePendingBindings();
        }
    }

    private static class WeakListener<T> extends WeakReference<ViewDataBinding> {
        private final ObservableReference<T> mObservable;
        private T mTarget;

        public boolean unregister() {
            boolean z;
            if (this.mTarget != null) {
                this.mObservable.removeListener(this.mTarget);
                z = true;
            } else {
                z = false;
            }
            this.mTarget = null;
            return z;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void executeBindings();

    public abstract boolean hasPendingBindings();

    static {
        if (VERSION.SDK_INT < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new OnAttachStateChangeListener() {
                public void onViewDetachedFromWindow(View view) {
                }

                @TargetApi(19)
                public void onViewAttachedToWindow(View view) {
                    ViewDataBinding.getBinding(view).mRebindRunnable.run();
                    view.removeOnAttachStateChangeListener(this);
                }
            };
        }
    }

    protected ViewDataBinding(DataBindingComponent dataBindingComponent, View view, int i) {
        this.mBindingComponent = dataBindingComponent;
        this.mLocalFieldObservers = new WeakListener[i];
        this.mRoot = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new FrameCallback() {
                public void doFrame(long j) {
                    ViewDataBinding.this.mRebindRunnable.run();
                }
            };
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    /* access modifiers changed from: protected */
    public void setRootTag(View view) {
        view.setTag(R.id.dataBinding, this);
    }

    public void executePendingBindings() {
        if (this.mContainingBinding == null) {
            executeBindingsInternal();
        } else {
            this.mContainingBinding.executePendingBindings();
        }
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            if (this.mRebindCallbacks != null) {
                this.mRebindCallbacks.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                if (this.mRebindCallbacks != null) {
                    this.mRebindCallbacks.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    static ViewDataBinding getBinding(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(R.id.dataBinding);
        }
        return null;
    }

    public View getRoot() {
        return this.mRoot;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r2.mLifecycleOwner == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r2.mLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(android.arch.lifecycle.Lifecycle.State.STARTED) != false) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (USE_CHOREOGRAPHER == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r2.mChoreographer.postFrameCallback(r2.mFrameCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        r2.mUIThreadHandler.post(r2.mRebindRunnable);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestRebind() {
        /*
            r2 = this;
            android.databinding.ViewDataBinding r0 = r2.mContainingBinding
            if (r0 == 0) goto L_0x000a
            android.databinding.ViewDataBinding r0 = r2.mContainingBinding
            r0.requestRebind()
            goto L_0x003f
        L_0x000a:
            monitor-enter(r2)
            boolean r0 = r2.mPendingRebind     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0011
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            return
        L_0x0011:
            r0 = 1
            r2.mPendingRebind = r0     // Catch:{ all -> 0x0040 }
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            android.arch.lifecycle.LifecycleOwner r0 = r2.mLifecycleOwner
            if (r0 == 0) goto L_0x002c
            android.arch.lifecycle.LifecycleOwner r0 = r2.mLifecycleOwner
            android.arch.lifecycle.Lifecycle r0 = r0.getLifecycle()
            android.arch.lifecycle.Lifecycle$State r0 = r0.getCurrentState()
            android.arch.lifecycle.Lifecycle$State r1 = android.arch.lifecycle.Lifecycle.State.STARTED
            boolean r0 = r0.isAtLeast(r1)
            if (r0 != 0) goto L_0x002c
            return
        L_0x002c:
            boolean r0 = USE_CHOREOGRAPHER
            if (r0 == 0) goto L_0x0038
            android.view.Choreographer r0 = r2.mChoreographer
            android.view.Choreographer$FrameCallback r1 = r2.mFrameCallback
            r0.postFrameCallback(r1)
            goto L_0x003f
        L_0x0038:
            android.os.Handler r0 = r2.mUIThreadHandler
            java.lang.Runnable r1 = r2.mRebindRunnable
            r0.post(r1)
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.requestRebind():void");
    }

    protected static Object[] mapBindings(DataBindingComponent dataBindingComponent, View view, int i, IncludedLayouts includedLayouts, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        mapBindings(dataBindingComponent, view, objArr, includedLayouts, sparseIntArray, true);
        return objArr;
    }

    protected static int getColorFromResource(View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            return view.getContext().getColor(i);
        }
        return view.getResources().getColor(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x010d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void mapBindings(android.databinding.DataBindingComponent r16, android.view.View r17, java.lang.Object[] r18, android.databinding.ViewDataBinding.IncludedLayouts r19, android.util.SparseIntArray r20, boolean r21) {
        /*
            r6 = r16
            r0 = r17
            r7 = r19
            r8 = r20
            android.databinding.ViewDataBinding r1 = getBinding(r17)
            if (r1 == 0) goto L_0x000f
            return
        L_0x000f:
            java.lang.Object r1 = r17.getTag()
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L_0x001a
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x001b
        L_0x001a:
            r1 = 0
        L_0x001b:
            r2 = -1
            r10 = 1
            if (r21 == 0) goto L_0x004c
            if (r1 == 0) goto L_0x004c
            java.lang.String r3 = "layout"
            boolean r3 = r1.startsWith(r3)
            if (r3 == 0) goto L_0x004c
            r3 = 95
            int r3 = r1.lastIndexOf(r3)
            if (r3 <= 0) goto L_0x0048
            int r3 = r3 + r10
            boolean r4 = isNumeric(r1, r3)
            if (r4 == 0) goto L_0x0048
            int r1 = parseTagInt(r1, r3)
            r3 = r18[r1]
            if (r3 != 0) goto L_0x0042
            r18[r1] = r0
        L_0x0042:
            if (r7 != 0) goto L_0x0045
            r1 = -1
        L_0x0045:
            r3 = r1
            r1 = 1
            goto L_0x004a
        L_0x0048:
            r1 = 0
            r3 = -1
        L_0x004a:
            r11 = r3
            goto L_0x006a
        L_0x004c:
            if (r1 == 0) goto L_0x0068
            java.lang.String r3 = "binding_"
            boolean r3 = r1.startsWith(r3)
            if (r3 == 0) goto L_0x0068
            int r3 = BINDING_NUMBER_START
            int r1 = parseTagInt(r1, r3)
            r3 = r18[r1]
            if (r3 != 0) goto L_0x0062
            r18[r1] = r0
        L_0x0062:
            if (r7 != 0) goto L_0x0065
            r1 = -1
        L_0x0065:
            r11 = r1
            r1 = 1
            goto L_0x006a
        L_0x0068:
            r1 = 0
            r11 = -1
        L_0x006a:
            if (r1 != 0) goto L_0x0080
            int r1 = r17.getId()
            if (r1 <= 0) goto L_0x0080
            if (r8 == 0) goto L_0x0080
            int r1 = r8.get(r1, r2)
            if (r1 < 0) goto L_0x0080
            r2 = r18[r1]
            if (r2 != 0) goto L_0x0080
            r18[r1] = r0
        L_0x0080:
            boolean r1 = r0 instanceof android.view.ViewGroup
            if (r1 == 0) goto L_0x0115
            r12 = r0
            android.view.ViewGroup r12 = (android.view.ViewGroup) r12
            int r13 = r12.getChildCount()
            r0 = 0
            r1 = 0
        L_0x008d:
            if (r0 >= r13) goto L_0x0115
            android.view.View r2 = r12.getChildAt(r0)
            if (r11 < 0) goto L_0x00fe
            java.lang.Object r3 = r2.getTag()
            boolean r3 = r3 instanceof java.lang.String
            if (r3 == 0) goto L_0x00fe
            java.lang.Object r3 = r2.getTag()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "_0"
            boolean r4 = r3.endsWith(r4)
            if (r4 == 0) goto L_0x00fe
            java.lang.String r4 = "layout"
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x00fe
            r4 = 47
            int r4 = r3.indexOf(r4)
            if (r4 <= 0) goto L_0x00fe
            int r3 = findIncludeIndex(r3, r1, r7, r11)
            if (r3 < 0) goto L_0x00fe
            int r1 = r3 + 1
            int[][] r4 = r7.indexes
            r4 = r4[r11]
            r4 = r4[r3]
            int[][] r5 = r7.layoutIds
            r5 = r5[r11]
            r3 = r5[r3]
            int r5 = findLastMatching(r12, r0)
            if (r5 != r0) goto L_0x00de
            android.databinding.ViewDataBinding r3 = android.databinding.DataBindingUtil.bind(r6, r2, r3)
            r18[r4] = r3
            r9 = r0
            r14 = r1
            goto L_0x0101
        L_0x00de:
            int r5 = r5 - r0
            int r5 = r5 + r10
            android.view.View[] r14 = new android.view.View[r5]
            r9 = 0
        L_0x00e3:
            if (r9 >= r5) goto L_0x00f1
            int r10 = r0 + r9
            android.view.View r10 = r12.getChildAt(r10)
            r14[r9] = r10
            int r9 = r9 + 1
            r10 = 1
            goto L_0x00e3
        L_0x00f1:
            android.databinding.ViewDataBinding r3 = android.databinding.DataBindingUtil.bind(r6, r14, r3)
            r18[r4] = r3
            int r5 = r5 + -1
            int r0 = r0 + r5
            r9 = r0
            r14 = r1
            r10 = 1
            goto L_0x0101
        L_0x00fe:
            r9 = r0
            r14 = r1
            r10 = 0
        L_0x0101:
            if (r10 != 0) goto L_0x010d
            r5 = 0
            r0 = r6
            r1 = r2
            r2 = r18
            r3 = r7
            r4 = r8
            mapBindings(r0, r1, r2, r3, r4, r5)
        L_0x010d:
            r0 = 1
            int r1 = r9 + 1
            r0 = r1
            r1 = r14
            r10 = 1
            goto L_0x008d
        L_0x0115:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.mapBindings(android.databinding.DataBindingComponent, android.view.View, java.lang.Object[], android.databinding.ViewDataBinding$IncludedLayouts, android.util.SparseIntArray, boolean):void");
    }

    private static int findIncludeIndex(String str, int i, IncludedLayouts includedLayouts, int i2) {
        CharSequence subSequence = str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        String[] strArr = includedLayouts.layouts[i2];
        int length = strArr.length;
        while (i < length) {
            if (TextUtils.equals(subSequence, strArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int i) {
        String str = (String) viewGroup.getChildAt(i).getTag();
        String substring = str.substring(0, str.length() - 1);
        int length = substring.length();
        int childCount = viewGroup.getChildCount();
        for (int i2 = i + 1; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            String str2 = childAt.getTag() instanceof String ? (String) childAt.getTag() : null;
            if (str2 != null && str2.startsWith(substring)) {
                if (str2.length() == str.length() && str2.charAt(str2.length() - 1) == '0') {
                    return i;
                }
                if (isNumeric(str2, length)) {
                    i = i2;
                }
            }
        }
        return i;
    }

    private static boolean isNumeric(String str, int i) {
        int length = str.length();
        if (length == i) {
            return false;
        }
        while (i < length) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static int parseTagInt(String str, int i) {
        int i2 = 0;
        while (i < str.length()) {
            i2 = (i2 * 10) + (str.charAt(i) - '0');
            i++;
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public static void processReferenceQueue() {
        while (true) {
            Reference poll = sReferenceQueue.poll();
            if (poll == null) {
                return;
            }
            if (poll instanceof WeakListener) {
                ((WeakListener) poll).unregister();
            }
        }
    }
}
