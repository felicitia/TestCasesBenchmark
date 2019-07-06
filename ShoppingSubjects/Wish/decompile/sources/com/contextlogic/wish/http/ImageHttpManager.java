package com.contextlogic.wish.http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.widget.ImageView;
import com.contextlogic.wish.ui.drawable.FadingDrawable;
import com.contextlogic.wish.ui.image.AspectRatioImageView;
import com.contextlogic.wish.ui.image.ZoomingImageView;
import com.contextlogic.wish.util.DisplayUtil;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class ImageHttpManager {
    private static ImageHttpManager sInstance = new ImageHttpManager();
    private Executor mCancellationExecutor;
    private OkHttpClient mHttpClient;
    private ConcurrentHashMap<Object, ImageRequest> mRequests = new ConcurrentHashMap<>();
    private HashMap<ImageRequestPool, ThreadPoolExecutor> mThreadPools;

    public interface ImageCallback {
        void onError();

        void onSuccess();
    }

    public static class ImageRequest {
        private boolean mAlreadyRun;
        private boolean mAlreadyScheduled;
        private Call mCall;
        /* access modifiers changed from: private */
        public boolean mCancelled;
        private boolean mFadeIn;
        private boolean mFetchOnly;
        private Handler mHandler;
        private int mHeight;
        /* access modifiers changed from: private */
        public ImageCallback mImageCallback;
        private int mImageResourceId;
        private Object mImageSource;
        /* access modifiers changed from: private */
        public ImageTarget mImageTarget;
        private ArrayList<ImageTransformation> mImageTransformations;
        private boolean mPaused;
        private boolean mRescheduleForNetwork;
        private Object mTag;
        private String mUrl;
        private int mWidth;

        public ImageRequest(String str) {
            this.mUrl = str;
            init();
        }

        public ImageRequest(int i) {
            this.mImageResourceId = i;
            init();
        }

        private void init() {
            this.mImageTransformations = new ArrayList<>();
            this.mHandler = new Handler(Looper.getMainLooper());
            this.mRescheduleForNetwork = false;
            this.mWidth = DisplayUtil.getDisplayWidth();
            this.mHeight = DisplayUtil.getDisplayHeight();
        }

        public void setImageSource(Object obj) {
            this.mImageSource = obj;
        }

        public void addImageTransformation(ImageTransformation imageTransformation) {
            this.mImageTransformations.add(imageTransformation);
        }

        public void setSize(int i, int i2) {
            this.mWidth = i;
            this.mHeight = i2;
        }

        public void setFadeIn(boolean z) {
            this.mFadeIn = z;
        }

        public void setImageCallback(ImageCallback imageCallback) {
            this.mImageCallback = imageCallback;
        }

        public void setFetchOnly(boolean z) {
            this.mFetchOnly = z;
        }

        public void setImageView(ImageView imageView, Drawable drawable) {
            this.mImageTarget = new ImageViewImageTarget(imageView, drawable, this.mFadeIn);
        }

        public void setImageTarget(ImageTarget imageTarget) {
            this.mImageTarget = imageTarget;
        }

        public void setTag(Object obj) {
            this.mTag = obj;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
            if (r2 == null) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
            r2 = r2.getActivity();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
            r2 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
            if (r2 == null) goto L_0x002e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
            r2 = com.contextlogic.wish.http.ImageHttpCache.getInstance().getCachedActivityBitmap(r1, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
            if (r2 == null) goto L_0x002e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
            finalizeLoading(r2, com.contextlogic.wish.http.ImageHttpManager.LoadingSource.MEMORY);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
            r4.mRescheduleForNetwork = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
            if (r4.mUrl == null) goto L_0x005c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
            if (com.contextlogic.wish.http.ImageHttpCache.getInstance().hasCachedBitmap(r1) != false) goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
            if (com.contextlogic.wish.http.ImageHttpCache.getInstance().hasCachedBitmap(r4.mUrl) == false) goto L_0x004c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
            if (r4.mFetchOnly == false) goto L_0x0053;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
            r0 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.PREFETCH;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0053, code lost:
            r0 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.DEFAULT;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0056, code lost:
            r1 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.CACHED;
            r4.mRescheduleForNetwork = true;
            r0 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x005c, code lost:
            r0 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.CACHED;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x005e, code lost:
            com.contextlogic.wish.http.ImageHttpManager.access$000(com.contextlogic.wish.http.ImageHttpManager.getInstance(), r0, r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0065, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
            r1 = getTransformedCacheKey();
            r2 = r4.mImageTarget;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void schedule() {
            /*
                r4 = this;
                monitor-enter(r4)
                boolean r0 = r4.mCancelled     // Catch:{ all -> 0x0068 }
                if (r0 != 0) goto L_0x0066
                boolean r0 = r4.mAlreadyScheduled     // Catch:{ all -> 0x0068 }
                if (r0 == 0) goto L_0x000a
                goto L_0x0066
            L_0x000a:
                r0 = 1
                r4.mAlreadyScheduled = r0     // Catch:{ all -> 0x0068 }
                monitor-exit(r4)     // Catch:{ all -> 0x0068 }
                java.lang.String r1 = r4.getTransformedCacheKey()
                com.contextlogic.wish.http.ImageHttpManager$ImageTarget r2 = r4.mImageTarget
                if (r2 == 0) goto L_0x001b
                android.app.Activity r2 = r2.getActivity()
                goto L_0x001c
            L_0x001b:
                r2 = 0
            L_0x001c:
                if (r2 == 0) goto L_0x002e
                com.contextlogic.wish.http.ImageHttpCache r3 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                android.graphics.Bitmap r2 = r3.getCachedActivityBitmap(r1, r2)
                if (r2 == 0) goto L_0x002e
                com.contextlogic.wish.http.ImageHttpManager$LoadingSource r0 = com.contextlogic.wish.http.ImageHttpManager.LoadingSource.MEMORY
                r4.finalizeLoading(r2, r0)
                return
            L_0x002e:
                r2 = 0
                r4.mRescheduleForNetwork = r2
                java.lang.String r2 = r4.mUrl
                if (r2 == 0) goto L_0x005c
                com.contextlogic.wish.http.ImageHttpCache r2 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                boolean r1 = r2.hasCachedBitmap(r1)
                if (r1 != 0) goto L_0x0056
                com.contextlogic.wish.http.ImageHttpCache r1 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                java.lang.String r2 = r4.mUrl
                boolean r1 = r1.hasCachedBitmap(r2)
                if (r1 == 0) goto L_0x004c
                goto L_0x0056
            L_0x004c:
                boolean r0 = r4.mFetchOnly
                if (r0 == 0) goto L_0x0053
                com.contextlogic.wish.http.ImageHttpManager$ImageRequestPool r0 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.PREFETCH
                goto L_0x005e
            L_0x0053:
                com.contextlogic.wish.http.ImageHttpManager$ImageRequestPool r0 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.DEFAULT
                goto L_0x005e
            L_0x0056:
                com.contextlogic.wish.http.ImageHttpManager$ImageRequestPool r1 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.CACHED
                r4.mRescheduleForNetwork = r0
                r0 = r1
                goto L_0x005e
            L_0x005c:
                com.contextlogic.wish.http.ImageHttpManager$ImageRequestPool r0 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.CACHED
            L_0x005e:
                com.contextlogic.wish.http.ImageHttpManager r1 = com.contextlogic.wish.http.ImageHttpManager.getInstance()
                r1.scheduleRequest(r0, r4)
                return
            L_0x0066:
                monitor-exit(r4)     // Catch:{ all -> 0x0068 }
                return
            L_0x0068:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0068 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.ImageHttpManager.ImageRequest.schedule():void");
        }

        private String getTransformedCacheKey() {
            StringBuilder sb = new StringBuilder();
            if (this.mUrl != null) {
                sb.append(this.mUrl);
            } else {
                sb.append(this.mImageResourceId);
            }
            Iterator it = this.mImageTransformations.iterator();
            while (it.hasNext()) {
                ImageTransformation imageTransformation = (ImageTransformation) it.next();
                sb.append("-");
                sb.append(imageTransformation.getCacheKey());
            }
            return sb.toString();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:101:0x01af, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x01bc, code lost:
            r0 = r13.mImageTransformations.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:108:0x01c6, code lost:
            if (r0.hasNext() != false) goto L_0x01c8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
            r1 = ((com.contextlogic.wish.http.ImageHttpManager.ImageTransformation) r0.next()).transform(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x01d3, code lost:
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:114:0x01d4, code lost:
            if (r1 != null) goto L_0x01d6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:115:0x01d6, code lost:
            com.contextlogic.wish.http.ImageHttpCache.getInstance().cacheBitmap(getTransformedCacheKey(), r3, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x01e5, code lost:
            com.contextlogic.wish.http.ImageHttpCache.getInstance().cacheActivityBitmap(getTransformedCacheKey(), r1, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:0x01f2, code lost:
            if (r13.mCancelled == false) goto L_0x01f5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x01f4, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:122:0x01f5, code lost:
            finalizeLoading(r1, r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:123:0x01f8, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
            if (r13.mFetchOnly == false) goto L_0x0070;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
            if (r13.mUrl == null) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
            if (r13.mUrl.equals("") != false) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
            if (com.contextlogic.wish.http.ImageHttpCache.getInstance().hasCachedBitmap(r13.mUrl) == false) goto L_0x0040;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
            com.contextlogic.wish.http.ImageHttpCache.getInstance().getCachedBitmap(r13.mUrl, true, r13.mWidth, r13.mHeight);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
            r13.mCall = com.contextlogic.wish.http.ImageHttpManager.access$100(com.contextlogic.wish.http.ImageHttpManager.getInstance(), r13.mUrl, r13.mImageSource);
            r0 = com.contextlogic.wish.http.ImageHttpManager.access$200(com.contextlogic.wish.http.ImageHttpManager.getInstance(), r13.mCall);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
            if (r0 == null) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
            com.contextlogic.wish.http.ImageHttpCache.getInstance().cacheBitmap(r13.mUrl, r0.getExpiryTime(), r0.getImageData());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x006b, code lost:
            cleanup();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0070, code lost:
            r2 = getTransformedCacheKey();
            r3 = -1;
            r5 = r13.mImageTarget;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0079, code lost:
            if (r5 == null) goto L_0x0080;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x007b, code lost:
            r5 = r5.getActivity();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0080, code lost:
            r5 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0081, code lost:
            if (r5 == null) goto L_0x0094;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0083, code lost:
            r7 = com.contextlogic.wish.http.ImageHttpCache.getInstance().getCachedActivityBitmap(r2, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x008b, code lost:
            if (r7 == null) goto L_0x0092;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x008d, code lost:
            r8 = com.contextlogic.wish.http.ImageHttpManager.LoadingSource.MEMORY;
            r0 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0092, code lost:
            r8 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0094, code lost:
            r7 = null;
            r8 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0096, code lost:
            if (r7 != null) goto L_0x00b5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0098, code lost:
            r2 = com.contextlogic.wish.http.ImageHttpCache.getInstance().getCachedBitmap(r2, false, r13.mWidth, r13.mHeight);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a4, code lost:
            if (r2 == null) goto L_0x00b5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a6, code lost:
            r7 = r2.getBitmap();
            r3 = r2.getExpiryTime();
            r8 = r2.getLoadingSource();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b2, code lost:
            if (r7 == null) goto L_0x00b5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b4, code lost:
            r0 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b5, code lost:
            if (r7 != null) goto L_0x00d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b7, code lost:
            r2 = com.contextlogic.wish.http.ImageHttpCache.getInstance().getCachedBitmap(r13.mUrl, false, r13.mWidth, r13.mHeight);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c5, code lost:
            if (r2 == null) goto L_0x00d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c7, code lost:
            r7 = r2.getBitmap();
            r3 = r2.getExpiryTime();
            r8 = r2.getLoadingSource();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d3, code lost:
            if (r7 != null) goto L_0x01aa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d7, code lost:
            if (r13.mRescheduleForNetwork == false) goto L_0x00f0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00db, code lost:
            if (r13.mCancelled != false) goto L_0x00f0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00dd, code lost:
            monitor-enter(r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
            r13.mRescheduleForNetwork = false;
            r13.mAlreadyRun = false;
            com.contextlogic.wish.http.ImageHttpManager.access$000(com.contextlogic.wish.http.ImageHttpManager.getInstance(), com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.DEFAULT, r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00eb, code lost:
            monitor-exit(r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ec, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x00f2, code lost:
            if (r13.mUrl == null) goto L_0x0182;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f4, code lost:
            r1 = android.net.Uri.parse(r13.mUrl);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fa, code lost:
            if (r1 == null) goto L_0x013c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x0100, code lost:
            if (r1.getScheme() == null) goto L_0x013c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x010c, code lost:
            if (r1.getScheme().equals("content") != false) goto L_0x011a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x0118, code lost:
            if (r1.getScheme().equals("file") == false) goto L_0x013c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
            r2 = com.contextlogic.wish.util.BitmapUtil.openImageUri(r1, r13.mWidth, r13.mHeight);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0122, code lost:
            r7 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0125, code lost:
            java.lang.System.gc();
            java.lang.Runtime.getRuntime().gc();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
            r1 = com.contextlogic.wish.util.BitmapUtil.openImageUri(r1, r13.mWidth, r13.mHeight);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x0138, code lost:
            r1 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
            r13.mCall = com.contextlogic.wish.http.ImageHttpManager.access$100(com.contextlogic.wish.http.ImageHttpManager.getInstance(), r13.mUrl, r13.mImageSource);
            r1 = com.contextlogic.wish.http.ImageHttpManager.access$200(com.contextlogic.wish.http.ImageHttpManager.getInstance(), r13.mCall);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x0154, code lost:
            if (r1 == null) goto L_0x017e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x0156, code lost:
            com.contextlogic.wish.http.ImageHttpCache.getInstance().cacheBitmap(r13.mUrl, r1.getExpiryTime(), r1.getImageData());
            r2 = com.contextlogic.wish.util.BitmapUtil.decodeBitmapBytes(r1.getImageData(), r13.mWidth, r13.mHeight);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
            r9 = r1.getExpiryTime();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
            r1 = com.contextlogic.wish.http.ImageHttpManager.LoadingSource.NETWORK;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x0179, code lost:
            r3 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x017b, code lost:
            r7 = r2;
            r3 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:0x017e, code lost:
            r2 = r7;
            r1 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x0180, code lost:
            r8 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
            r1 = android.graphics.BitmapFactory.decodeResource(com.contextlogic.wish.application.WishApplication.getInstance().getResources(), r13.mImageResourceId);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x0191, code lost:
            java.lang.System.gc();
            java.lang.Runtime.getRuntime().gc();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
            r1 = android.graphics.BitmapFactory.decodeResource(com.contextlogic.wish.application.WishApplication.getInstance().getResources(), r13.mImageResourceId);
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [B:78:0x013c, B:91:0x0182] */
        /* JADX WARNING: Removed duplicated region for block: B:101:0x01af A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:102:0x01b0 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:115:0x01d6  */
        /* JADX WARNING: Removed duplicated region for block: B:121:0x01f4 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:122:0x01f5  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void execute() {
            /*
                r13 = this;
                monitor-enter(r13)
                boolean r0 = r13.mCancelled     // Catch:{ all -> 0x01fd }
                r1 = 0
                if (r0 != 0) goto L_0x01f9
                boolean r0 = r13.mAlreadyRun     // Catch:{ all -> 0x01fd }
                if (r0 != 0) goto L_0x01f9
                boolean r0 = r13.mPaused     // Catch:{ all -> 0x01fd }
                if (r0 == 0) goto L_0x0010
                goto L_0x01f9
            L_0x0010:
                r0 = 1
                r13.mAlreadyRun = r0     // Catch:{ all -> 0x01fd }
                monitor-exit(r13)     // Catch:{ all -> 0x01fd }
                boolean r2 = r13.mFetchOnly
                if (r2 == 0) goto L_0x0070
                java.lang.String r1 = r13.mUrl
                if (r1 == 0) goto L_0x006b
                java.lang.String r1 = r13.mUrl
                java.lang.String r2 = ""
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x006b
                com.contextlogic.wish.http.ImageHttpCache r1 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                java.lang.String r2 = r13.mUrl
                boolean r1 = r1.hasCachedBitmap(r2)
                if (r1 == 0) goto L_0x0040
                com.contextlogic.wish.http.ImageHttpCache r1 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                java.lang.String r2 = r13.mUrl
                int r3 = r13.mWidth
                int r4 = r13.mHeight
                r1.getCachedBitmap(r2, r0, r3, r4)
                goto L_0x006b
            L_0x0040:
                com.contextlogic.wish.http.ImageHttpManager r0 = com.contextlogic.wish.http.ImageHttpManager.getInstance()
                java.lang.String r1 = r13.mUrl
                java.lang.Object r2 = r13.mImageSource
                okhttp3.Call r0 = r0.prepareNetworkRequest(r1, r2)
                r13.mCall = r0
                com.contextlogic.wish.http.ImageHttpManager r0 = com.contextlogic.wish.http.ImageHttpManager.getInstance()
                okhttp3.Call r1 = r13.mCall
                com.contextlogic.wish.http.ImageHttpManager$ImageResponse r0 = r0.getImageBytesFromNetwork(r1)
                if (r0 == 0) goto L_0x006b
                com.contextlogic.wish.http.ImageHttpCache r1 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                java.lang.String r2 = r13.mUrl
                long r3 = r0.getExpiryTime()
                byte[] r0 = r0.getImageData()
                r1.cacheBitmap(r2, r3, r0)
            L_0x006b:
                r13.cleanup()
                goto L_0x01f8
            L_0x0070:
                java.lang.String r2 = r13.getTransformedCacheKey()
                r3 = -1
                com.contextlogic.wish.http.ImageHttpManager$ImageTarget r5 = r13.mImageTarget
                r6 = 0
                if (r5 == 0) goto L_0x0080
                android.app.Activity r5 = r5.getActivity()
                goto L_0x0081
            L_0x0080:
                r5 = r6
            L_0x0081:
                if (r5 == 0) goto L_0x0094
                com.contextlogic.wish.http.ImageHttpCache r7 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                android.graphics.Bitmap r7 = r7.getCachedActivityBitmap(r2, r5)
                if (r7 == 0) goto L_0x0092
                com.contextlogic.wish.http.ImageHttpManager$LoadingSource r0 = com.contextlogic.wish.http.ImageHttpManager.LoadingSource.MEMORY
                r8 = r0
                r0 = 0
                goto L_0x0096
            L_0x0092:
                r8 = r6
                goto L_0x0096
            L_0x0094:
                r7 = r6
                r8 = r7
            L_0x0096:
                if (r7 != 0) goto L_0x00b5
                com.contextlogic.wish.http.ImageHttpCache r9 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                int r10 = r13.mWidth
                int r11 = r13.mHeight
                com.contextlogic.wish.http.ImageHttpCache$ExpirableBitmap r2 = r9.getCachedBitmap(r2, r1, r10, r11)
                if (r2 == 0) goto L_0x00b5
                android.graphics.Bitmap r7 = r2.getBitmap()
                long r3 = r2.getExpiryTime()
                com.contextlogic.wish.http.ImageHttpManager$LoadingSource r8 = r2.getLoadingSource()
                if (r7 == 0) goto L_0x00b5
                r0 = 0
            L_0x00b5:
                if (r7 != 0) goto L_0x00d3
                com.contextlogic.wish.http.ImageHttpCache r2 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                java.lang.String r9 = r13.mUrl
                int r10 = r13.mWidth
                int r11 = r13.mHeight
                com.contextlogic.wish.http.ImageHttpCache$ExpirableBitmap r2 = r2.getCachedBitmap(r9, r1, r10, r11)
                if (r2 == 0) goto L_0x00d3
                android.graphics.Bitmap r7 = r2.getBitmap()
                long r3 = r2.getExpiryTime()
                com.contextlogic.wish.http.ImageHttpManager$LoadingSource r8 = r2.getLoadingSource()
            L_0x00d3:
                if (r7 != 0) goto L_0x01aa
                boolean r2 = r13.mRescheduleForNetwork
                if (r2 == 0) goto L_0x00f0
                boolean r2 = r13.mCancelled
                if (r2 != 0) goto L_0x00f0
                monitor-enter(r13)
                r13.mRescheduleForNetwork = r1     // Catch:{ all -> 0x00ed }
                r13.mAlreadyRun = r1     // Catch:{ all -> 0x00ed }
                com.contextlogic.wish.http.ImageHttpManager r0 = com.contextlogic.wish.http.ImageHttpManager.getInstance()     // Catch:{ all -> 0x00ed }
                com.contextlogic.wish.http.ImageHttpManager$ImageRequestPool r1 = com.contextlogic.wish.http.ImageHttpManager.ImageRequestPool.DEFAULT     // Catch:{ all -> 0x00ed }
                r0.scheduleRequest(r1, r13)     // Catch:{ all -> 0x00ed }
                monitor-exit(r13)     // Catch:{ all -> 0x00ed }
                return
            L_0x00ed:
                r0 = move-exception
                monitor-exit(r13)     // Catch:{ all -> 0x00ed }
                throw r0
            L_0x00f0:
                java.lang.String r1 = r13.mUrl
                if (r1 == 0) goto L_0x0182
                java.lang.String r1 = r13.mUrl
                android.net.Uri r1 = android.net.Uri.parse(r1)
                if (r1 == 0) goto L_0x013c
                java.lang.String r2 = r1.getScheme()
                if (r2 == 0) goto L_0x013c
                java.lang.String r2 = r1.getScheme()
                java.lang.String r9 = "content"
                boolean r2 = r2.equals(r9)
                if (r2 != 0) goto L_0x011a
                java.lang.String r2 = r1.getScheme()
                java.lang.String r9 = "file"
                boolean r2 = r2.equals(r9)
                if (r2 == 0) goto L_0x013c
            L_0x011a:
                int r2 = r13.mWidth     // Catch:{ Throwable -> 0x0125 }
                int r9 = r13.mHeight     // Catch:{ Throwable -> 0x0125 }
                android.graphics.Bitmap r2 = com.contextlogic.wish.util.BitmapUtil.openImageUri(r1, r2, r9)     // Catch:{ Throwable -> 0x0125 }
            L_0x0122:
                r7 = r2
                goto L_0x01aa
            L_0x0125:
                java.lang.System.gc()
                java.lang.Runtime r2 = java.lang.Runtime.getRuntime()
                r2.gc()
                int r2 = r13.mWidth     // Catch:{ Throwable -> 0x0138 }
                int r9 = r13.mHeight     // Catch:{ Throwable -> 0x0138 }
                android.graphics.Bitmap r1 = com.contextlogic.wish.util.BitmapUtil.openImageUri(r1, r2, r9)     // Catch:{ Throwable -> 0x0138 }
                goto L_0x0139
            L_0x0138:
                r1 = r7
            L_0x0139:
                r7 = r1
                goto L_0x01aa
            L_0x013c:
                com.contextlogic.wish.http.ImageHttpManager r1 = com.contextlogic.wish.http.ImageHttpManager.getInstance()     // Catch:{ Throwable -> 0x01aa }
                java.lang.String r2 = r13.mUrl     // Catch:{ Throwable -> 0x01aa }
                java.lang.Object r9 = r13.mImageSource     // Catch:{ Throwable -> 0x01aa }
                okhttp3.Call r1 = r1.prepareNetworkRequest(r2, r9)     // Catch:{ Throwable -> 0x01aa }
                r13.mCall = r1     // Catch:{ Throwable -> 0x01aa }
                com.contextlogic.wish.http.ImageHttpManager r1 = com.contextlogic.wish.http.ImageHttpManager.getInstance()     // Catch:{ Throwable -> 0x01aa }
                okhttp3.Call r2 = r13.mCall     // Catch:{ Throwable -> 0x01aa }
                com.contextlogic.wish.http.ImageHttpManager$ImageResponse r1 = r1.getImageBytesFromNetwork(r2)     // Catch:{ Throwable -> 0x01aa }
                if (r1 == 0) goto L_0x017e
                com.contextlogic.wish.http.ImageHttpCache r2 = com.contextlogic.wish.http.ImageHttpCache.getInstance()     // Catch:{ Throwable -> 0x01aa }
                java.lang.String r9 = r13.mUrl     // Catch:{ Throwable -> 0x01aa }
                long r10 = r1.getExpiryTime()     // Catch:{ Throwable -> 0x01aa }
                byte[] r12 = r1.getImageData()     // Catch:{ Throwable -> 0x01aa }
                r2.cacheBitmap(r9, r10, r12)     // Catch:{ Throwable -> 0x01aa }
                byte[] r2 = r1.getImageData()     // Catch:{ Throwable -> 0x01aa }
                int r9 = r13.mWidth     // Catch:{ Throwable -> 0x01aa }
                int r10 = r13.mHeight     // Catch:{ Throwable -> 0x01aa }
                android.graphics.Bitmap r2 = com.contextlogic.wish.util.BitmapUtil.decodeBitmapBytes(r2, r9, r10)     // Catch:{ Throwable -> 0x01aa }
                long r9 = r1.getExpiryTime()     // Catch:{ Throwable -> 0x0122 }
                com.contextlogic.wish.http.ImageHttpManager$LoadingSource r1 = com.contextlogic.wish.http.ImageHttpManager.LoadingSource.NETWORK     // Catch:{ Throwable -> 0x017b }
                r3 = r9
                goto L_0x0180
            L_0x017b:
                r7 = r2
                r3 = r9
                goto L_0x01aa
            L_0x017e:
                r2 = r7
                r1 = r8
            L_0x0180:
                r8 = r1
                goto L_0x0122
            L_0x0182:
                com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x0191 }
                android.content.res.Resources r1 = r1.getResources()     // Catch:{ Throwable -> 0x0191 }
                int r2 = r13.mImageResourceId     // Catch:{ Throwable -> 0x0191 }
                android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeResource(r1, r2)     // Catch:{ Throwable -> 0x0191 }
                goto L_0x01ab
            L_0x0191:
                java.lang.System.gc()
                java.lang.Runtime r1 = java.lang.Runtime.getRuntime()
                r1.gc()
                com.contextlogic.wish.application.WishApplication r1 = com.contextlogic.wish.application.WishApplication.getInstance()     // Catch:{ Throwable -> 0x01aa }
                android.content.res.Resources r1 = r1.getResources()     // Catch:{ Throwable -> 0x01aa }
                int r2 = r13.mImageResourceId     // Catch:{ Throwable -> 0x01aa }
                android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeResource(r1, r2)     // Catch:{ Throwable -> 0x01aa }
                goto L_0x01ab
            L_0x01aa:
                r1 = r7
            L_0x01ab:
                boolean r2 = r13.mCancelled
                if (r2 == 0) goto L_0x01b0
                return
            L_0x01b0:
                if (r1 == 0) goto L_0x01e1
                if (r0 == 0) goto L_0x01e1
                java.util.ArrayList<com.contextlogic.wish.http.ImageHttpManager$ImageTransformation> r0 = r13.mImageTransformations
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x01e1
                java.util.ArrayList<com.contextlogic.wish.http.ImageHttpManager$ImageTransformation> r0 = r13.mImageTransformations
                java.util.Iterator r0 = r0.iterator()
            L_0x01c2:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x01d4
                java.lang.Object r2 = r0.next()
                com.contextlogic.wish.http.ImageHttpManager$ImageTransformation r2 = (com.contextlogic.wish.http.ImageHttpManager.ImageTransformation) r2
                android.graphics.Bitmap r1 = r2.transform(r1)     // Catch:{ Throwable -> 0x01d3 }
                goto L_0x01c2
            L_0x01d3:
                r1 = r6
            L_0x01d4:
                if (r1 == 0) goto L_0x01e1
                com.contextlogic.wish.http.ImageHttpCache r0 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                java.lang.String r2 = r13.getTransformedCacheKey()
                r0.cacheBitmap(r2, r3, r1)
            L_0x01e1:
                if (r5 == 0) goto L_0x01f0
                if (r1 == 0) goto L_0x01f0
                com.contextlogic.wish.http.ImageHttpCache r0 = com.contextlogic.wish.http.ImageHttpCache.getInstance()
                java.lang.String r2 = r13.getTransformedCacheKey()
                r0.cacheActivityBitmap(r2, r1, r5)
            L_0x01f0:
                boolean r0 = r13.mCancelled
                if (r0 == 0) goto L_0x01f5
                return
            L_0x01f5:
                r13.finalizeLoading(r1, r8)
            L_0x01f8:
                return
            L_0x01f9:
                r13.mAlreadyScheduled = r1     // Catch:{ all -> 0x01fd }
                monitor-exit(r13)     // Catch:{ all -> 0x01fd }
                return
            L_0x01fd:
                r0 = move-exception
                monitor-exit(r13)     // Catch:{ all -> 0x01fd }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.ImageHttpManager.ImageRequest.execute():void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
            if (android.os.Looper.myLooper() != android.os.Looper.getMainLooper()) goto L_0x0021;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
            r0.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
            r1.mHandler.post(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
            r0 = new com.contextlogic.wish.http.ImageHttpManager.ImageRequest.AnonymousClass1(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void finalizeLoading(final android.graphics.Bitmap r2, final com.contextlogic.wish.http.ImageHttpManager.LoadingSource r3) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.mCancelled     // Catch:{ all -> 0x0029 }
                if (r0 != 0) goto L_0x0027
                boolean r0 = r1.mPaused     // Catch:{ all -> 0x0029 }
                if (r0 == 0) goto L_0x000a
                goto L_0x0027
            L_0x000a:
                r0 = 1
                r1.mAlreadyRun = r0     // Catch:{ all -> 0x0029 }
                monitor-exit(r1)     // Catch:{ all -> 0x0029 }
                com.contextlogic.wish.http.ImageHttpManager$ImageRequest$1 r0 = new com.contextlogic.wish.http.ImageHttpManager$ImageRequest$1
                r0.<init>(r2, r3)
                android.os.Looper r2 = android.os.Looper.myLooper()
                android.os.Looper r3 = android.os.Looper.getMainLooper()
                if (r2 != r3) goto L_0x0021
                r0.run()
                goto L_0x0026
            L_0x0021:
                android.os.Handler r2 = r1.mHandler
                r2.post(r0)
            L_0x0026:
                return
            L_0x0027:
                monitor-exit(r1)     // Catch:{ all -> 0x0029 }
                return
            L_0x0029:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0029 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.http.ImageHttpManager.ImageRequest.finalizeLoading(android.graphics.Bitmap, com.contextlogic.wish.http.ImageHttpManager$LoadingSource):void");
        }

        public Object getRequestIdentifier() {
            ImageTarget imageTarget = this.mImageTarget;
            if (this.mTag != null) {
                return this.mTag;
            }
            return imageTarget != null ? imageTarget.getIdentifier() : this;
        }

        public void pause() {
            synchronized (this) {
                this.mPaused = true;
            }
        }

        public void resume() {
            synchronized (this) {
                if (this.mPaused && !this.mCancelled && !this.mAlreadyRun && !this.mAlreadyScheduled) {
                    this.mPaused = false;
                    schedule();
                }
            }
        }

        public void cancel() {
            synchronized (this) {
                if (!this.mCancelled) {
                    this.mCancelled = true;
                    cleanup();
                }
            }
        }

        /* access modifiers changed from: private */
        public void cleanup() {
            this.mHandler.removeCallbacksAndMessages(null);
            if (this.mCall != null) {
                ImageHttpManager.getInstance().cancelNetworkRequest(this.mCall);
                this.mCall = null;
            }
            ImageHttpManager.getInstance().clearFromRequestMap(this);
            this.mImageTarget = null;
        }
    }

    private enum ImageRequestPool {
        PREFETCH,
        CACHED,
        DEFAULT
    }

    private static class ImageRequestThread extends Thread {
        private int mPriority;

        public ImageRequestThread(Runnable runnable, int i) {
            super(runnable);
            this.mPriority = i;
        }

        public void run() {
            Process.setThreadPriority(this.mPriority);
            super.run();
        }
    }

    private static class ImageRequestThreadFactory implements ThreadFactory {
        private ImageRequestPool mPool;
        private int mPriority;

        public ImageRequestThreadFactory(ImageRequestPool imageRequestPool) {
            this.mPool = imageRequestPool;
            if (imageRequestPool == ImageRequestPool.PREFETCH) {
                this.mPriority = 9;
            } else if (imageRequestPool == ImageRequestPool.CACHED) {
                this.mPriority = 7;
            } else {
                this.mPriority = 8;
            }
        }

        public Thread newThread(Runnable runnable) {
            ImageRequestThread imageRequestThread = new ImageRequestThread(runnable, this.mPriority);
            StringBuilder sb = new StringBuilder();
            sb.append(this.mPool.name());
            sb.append(imageRequestThread.getId());
            imageRequestThread.setName(sb.toString());
            return imageRequestThread;
        }
    }

    private static class ImageResponse {
        private long mExpiryTime;
        private byte[] mImageData;

        public ImageResponse(byte[] bArr, long j) {
            this.mImageData = bArr;
            this.mExpiryTime = j;
        }

        public byte[] getImageData() {
            return this.mImageData;
        }

        public long getExpiryTime() {
            return this.mExpiryTime;
        }
    }

    public static abstract class ImageTarget {
        public Activity getActivity() {
            return null;
        }

        public Object getIdentifier() {
            return this;
        }

        public abstract void onError();

        public abstract void onSuccess(Bitmap bitmap, LoadingSource loadingSource);
    }

    public interface ImageTransformation {
        String getCacheKey();

        Bitmap transform(Bitmap bitmap);
    }

    private static class ImageViewImageTarget extends ImageTarget {
        private boolean mFade;
        private ImageView mImageView;
        private Drawable mPlaceholder;

        public void onError() {
        }

        public ImageViewImageTarget(ImageView imageView, Drawable drawable, boolean z) {
            this.mImageView = imageView;
            this.mPlaceholder = drawable;
            this.mFade = z;
        }

        /* access modifiers changed from: protected */
        public FadingDrawable getDrawable(Bitmap bitmap, LoadingSource loadingSource) {
            Drawable drawable = this.mImageView.getDrawable();
            if (drawable != null && (drawable instanceof AnimationDrawable)) {
                ((AnimationDrawable) drawable).stop();
            }
            boolean z = this.mFade;
            if (loadingSource == LoadingSource.MEMORY) {
                z = false;
            }
            return new FadingDrawable(this.mImageView.getContext(), bitmap, this.mPlaceholder, z);
        }

        public void onSuccess(Bitmap bitmap, LoadingSource loadingSource) {
            FadingDrawable drawable = getDrawable(bitmap, loadingSource);
            if (this.mImageView instanceof ZoomingImageView) {
                ((ZoomingImageView) this.mImageView).onImageDrawableLoaded(drawable);
            } else if (this.mImageView instanceof AspectRatioImageView) {
                ((AspectRatioImageView) this.mImageView).onImageDrawableLoaded(drawable);
            } else {
                this.mImageView.setImageDrawable(drawable);
            }
        }

        public Activity getActivity() {
            if (this.mImageView.getContext() instanceof Activity) {
                return (Activity) this.mImageView.getContext();
            }
            return null;
        }

        public Object getIdentifier() {
            return this.mImageView;
        }
    }

    public enum LoadingSource {
        MEMORY,
        DISK,
        NETWORK
    }

    private ImageHttpManager() {
        ImageRequestPool[] values;
        initOkHttpClient();
        this.mThreadPools = new HashMap<>();
        for (ImageRequestPool imageRequestPool : ImageRequestPool.values()) {
            int i = 2;
            if (!(imageRequestPool == ImageRequestPool.PREFETCH || imageRequestPool == ImageRequestPool.CACHED)) {
                i = 3;
            }
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(i);
            threadPoolExecutor.setThreadFactory(new ImageRequestThreadFactory(imageRequestPool));
            this.mThreadPools.put(imageRequestPool, threadPoolExecutor);
        }
    }

    private void initOkHttpClient() {
        Builder cookieJar = new Builder().connectTimeout(35000, TimeUnit.MILLISECONDS).readTimeout(35000, TimeUnit.MILLISECONDS).writeTimeout(35000, TimeUnit.MILLISECONDS).cookieJar(HttpCookieManager.getInstance());
        Tls12Helper.enableTls12(cookieJar);
        this.mHttpClient = cookieJar.build();
        this.mCancellationExecutor = this.mHttpClient.dispatcher().executorService();
    }

    public static ImageHttpManager getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public Call prepareNetworkRequest(String str, Object obj) {
        return this.mHttpClient.newCall(new Request.Builder().url(str).tag(obj).build());
    }

    /* access modifiers changed from: private */
    public ImageResponse getImageBytesFromNetwork(Call call) {
        try {
            Response execute = FirebasePerfOkHttpClient.execute(call);
            if (execute.code() != 200) {
                return null;
            }
            long maxAgeSeconds = (long) execute.cacheControl().maxAgeSeconds();
            if (maxAgeSeconds == -1) {
                maxAgeSeconds = 604800;
            }
            return new ImageResponse(execute.body().bytes(), System.currentTimeMillis() + (maxAgeSeconds * 1000));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void cancelNetworkRequest(final Call call) {
        this.mCancellationExecutor.execute(new Runnable() {
            public void run() {
                try {
                    call.cancel();
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void cancelRequest(Object obj) {
        ImageRequest imageRequest = (ImageRequest) this.mRequests.get(obj);
        if (imageRequest != null) {
            imageRequest.cancel();
        }
    }

    public void pauseRequest(Object obj) {
        ImageRequest imageRequest = (ImageRequest) this.mRequests.get(obj);
        if (imageRequest != null) {
            imageRequest.pause();
        }
    }

    public void resumeRequest(Object obj) {
        ImageRequest imageRequest = (ImageRequest) this.mRequests.get(obj);
        if (imageRequest != null) {
            imageRequest.resume();
        }
    }

    /* access modifiers changed from: private */
    public void clearFromRequestMap(ImageRequest imageRequest) {
        this.mRequests.remove(imageRequest.getRequestIdentifier());
    }

    public void request(ImageRequest imageRequest) {
        cancelRequest(imageRequest);
        this.mRequests.put(imageRequest.getRequestIdentifier(), imageRequest);
        imageRequest.schedule();
    }

    /* access modifiers changed from: private */
    public void scheduleRequest(ImageRequestPool imageRequestPool, final ImageRequest imageRequest) {
        ((ThreadPoolExecutor) this.mThreadPools.get(imageRequestPool)).execute(new Runnable() {
            public void run() {
                imageRequest.execute();
            }
        });
    }
}
