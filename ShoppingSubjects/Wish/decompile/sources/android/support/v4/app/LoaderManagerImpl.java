package android.support.v4.app;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelStore;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
    static boolean DEBUG = false;
    private boolean mCreatingLoader;
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    public static class LoaderInfo<D> extends MutableLiveData<D> implements OnLoadCompleteListener<D> {
        private final Bundle mArgs;
        private final int mId;
        private LifecycleOwner mLifecycleOwner;
        private final Loader<D> mLoader;
        private LoaderObserver<D> mObserver;
        private Loader<D> mPriorLoader;

        LoaderInfo(int i, Bundle bundle, Loader<D> loader, Loader<D> loader2) {
            this.mId = i;
            this.mArgs = bundle;
            this.mLoader = loader;
            this.mPriorLoader = loader2;
            this.mLoader.registerListener(i, this);
        }

        /* access modifiers changed from: 0000 */
        public Loader<D> getLoader() {
            return this.mLoader;
        }

        /* access modifiers changed from: protected */
        public void onActive() {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Starting: ");
                sb.append(this);
                Log.v("LoaderManager", sb.toString());
            }
            this.mLoader.startLoading();
        }

        /* access modifiers changed from: protected */
        public void onInactive() {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Stopping: ");
                sb.append(this);
                Log.v("LoaderManager", sb.toString());
            }
            this.mLoader.stopLoading();
        }

        /* access modifiers changed from: 0000 */
        public Loader<D> setCallback(LifecycleOwner lifecycleOwner, LoaderCallbacks<D> loaderCallbacks) {
            LoaderObserver<D> loaderObserver = new LoaderObserver<>(this.mLoader, loaderCallbacks);
            observe(lifecycleOwner, loaderObserver);
            if (this.mObserver != null) {
                removeObserver(this.mObserver);
            }
            this.mLifecycleOwner = lifecycleOwner;
            this.mObserver = loaderObserver;
            return this.mLoader;
        }

        /* access modifiers changed from: 0000 */
        public void markForRedelivery() {
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (lifecycleOwner != null && loaderObserver != null) {
                super.removeObserver(loaderObserver);
                observe(lifecycleOwner, loaderObserver);
            }
        }

        public void removeObserver(Observer<D> observer) {
            super.removeObserver(observer);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        /* access modifiers changed from: 0000 */
        public Loader<D> destroy(boolean z) {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Destroying: ");
                sb.append(this);
                Log.v("LoaderManager", sb.toString());
            }
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (loaderObserver != null) {
                removeObserver(loaderObserver);
                if (z) {
                    loaderObserver.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if ((loaderObserver == null || loaderObserver.hasDeliveredData()) && !z) {
                return this.mLoader;
            }
            this.mLoader.reset();
            return this.mPriorLoader;
        }

        public void onLoadComplete(Loader<D> loader, D d) {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("onLoadComplete: ");
                sb.append(this);
                Log.v("LoaderManager", sb.toString());
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(d);
                return;
            }
            if (LoaderManagerImpl.DEBUG) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(d);
        }

        public void setValue(D d) {
            super.setValue(d);
            if (this.mPriorLoader != null) {
                this.mPriorLoader.reset();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.mId);
            sb.append(" : ");
            DebugUtils.buildShortClassTag(this.mLoader, sb);
            sb.append("}}");
            return sb.toString();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.mId);
            printWriter.print(" mArgs=");
            printWriter.println(this.mArgs);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.mLoader);
            Loader<D> loader = this.mLoader;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("  ");
            loader.dump(sb.toString(), fileDescriptor, printWriter, strArr);
            if (this.mObserver != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.mObserver);
                LoaderObserver<D> loaderObserver = this.mObserver;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("  ");
                loaderObserver.dump(sb2.toString(), printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(getLoader().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }
    }

    static class LoaderObserver<D> implements Observer<D> {
        private final LoaderCallbacks<D> mCallback;
        private boolean mDeliveredData = false;
        private final Loader<D> mLoader;

        LoaderObserver(Loader<D> loader, LoaderCallbacks<D> loaderCallbacks) {
            this.mLoader = loader;
            this.mCallback = loaderCallbacks;
        }

        public void onChanged(D d) {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  onLoadFinished in ");
                sb.append(this.mLoader);
                sb.append(": ");
                sb.append(this.mLoader.dataToString(d));
                Log.v("LoaderManager", sb.toString());
            }
            this.mCallback.onLoadFinished(this.mLoader, d);
            this.mDeliveredData = true;
        }

        /* access modifiers changed from: 0000 */
        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        /* access modifiers changed from: 0000 */
        public void reset() {
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("  Resetting: ");
                    sb.append(this.mLoader);
                    Log.v("LoaderManager", sb.toString());
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }

        public void dump(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.mDeliveredData);
        }
    }

    static class LoaderViewModel extends ViewModel {
        private static final Factory FACTORY = new Factory() {
            public <T extends ViewModel> T create(Class<T> cls) {
                return new LoaderViewModel();
            }
        };
        private SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat<>();

        LoaderViewModel() {
        }

        static LoaderViewModel getInstance(ViewModelStore viewModelStore) {
            return (LoaderViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(LoaderViewModel.class);
        }

        /* access modifiers changed from: 0000 */
        public void putLoader(int i, LoaderInfo loaderInfo) {
            this.mLoaders.put(i, loaderInfo);
        }

        /* access modifiers changed from: 0000 */
        public <D> LoaderInfo<D> getLoader(int i) {
            return (LoaderInfo) this.mLoaders.get(i);
        }

        /* access modifiers changed from: 0000 */
        public void markForRedelivery() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                ((LoaderInfo) this.mLoaders.valueAt(i)).markForRedelivery();
            }
        }

        /* access modifiers changed from: protected */
        public void onCleared() {
            super.onCleared();
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                ((LoaderInfo) this.mLoaders.valueAt(i)).destroy(true);
            }
            this.mLoaders.clear();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("    ");
                String sb2 = sb.toString();
                for (int i = 0; i < this.mLoaders.size(); i++) {
                    LoaderInfo loaderInfo = (LoaderInfo) this.mLoaders.valueAt(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.mLoaders.keyAt(i));
                    printWriter.print(": ");
                    printWriter.println(loaderInfo.toString());
                    loaderInfo.dump(sb2, fileDescriptor, printWriter, strArr);
                }
            }
        }
    }

    LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore);
    }

    /* JADX INFO: finally extract failed */
    private <D> Loader<D> createAndInstallLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks, Loader<D> loader) {
        try {
            this.mCreatingLoader = true;
            Loader onCreateLoader = loaderCallbacks.onCreateLoader(i, bundle);
            if (!onCreateLoader.getClass().isMemberClass() || Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                LoaderInfo loaderInfo = new LoaderInfo(i, bundle, onCreateLoader, loader);
                if (DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("  Created new loader ");
                    sb.append(loaderInfo);
                    Log.v("LoaderManager", sb.toString());
                }
                this.mLoaderViewModel.putLoader(i, loaderInfo);
                this.mCreatingLoader = false;
                return loaderInfo.setCallback(this.mLifecycleOwner, loaderCallbacks);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Object returned from onCreateLoader must not be a non-static inner member class: ");
            sb2.append(onCreateLoader);
            throw new IllegalArgumentException(sb2.toString());
        } catch (Throwable th) {
            this.mCreatingLoader = false;
            throw th;
        }
    }

    public <D> Loader<D> initLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        } else {
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            if (DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("initLoader in ");
                sb.append(this);
                sb.append(": args=");
                sb.append(bundle);
                Log.v("LoaderManager", sb.toString());
            }
            if (loader == null) {
                return createAndInstallLoader(i, bundle, loaderCallbacks, null);
            }
            if (DEBUG) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("  Re-using existing loader ");
                sb2.append(loader);
                Log.v("LoaderManager", sb2.toString());
            }
            return loader.setCallback(this.mLifecycleOwner, loaderCallbacks);
        }
    }

    /* access modifiers changed from: 0000 */
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.buildShortClassTag(this.mLifecycleOwner, sb);
        sb.append("}}");
        return sb.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }
}
