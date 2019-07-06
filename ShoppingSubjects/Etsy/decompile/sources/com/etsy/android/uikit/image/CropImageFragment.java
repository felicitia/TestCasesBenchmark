package com.etsy.android.uikit.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.l;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.img.g;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.w;
import com.etsy.android.lib.util.x;
import com.etsy.android.uikit.image.CropImageUtil.Options;
import com.etsy.android.uikit.ui.core.BaseFragment;
import com.lyft.android.scissors.CropView;
import io.reactivex.functions.Consumer;
import org.parceler.d;

public class CropImageFragment extends BaseFragment implements OnRequestPermissionsResultCallback, OnMenuItemClickListener {
    private static final int REQUEST_PERMISSIONS = 10;
    private static final String TAG = f.a(CropImageFragment.class);
    private Uri mDestUri;
    private TextView mHelpText;
    private String mImageUrl;
    private CropView mImageView;
    private float mMaxScale;
    private float mMinScale;
    private MenuItem mNextButton;
    private Options mOptions;
    private View mParentView;
    private Uri mSourceUri;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mSourceUri = Uri.parse(arguments.getString("source_uri"));
        this.mDestUri = Uri.parse(arguments.getString("dest_uri"));
        this.mImageUrl = arguments.getString("url");
        this.mOptions = (Options) d.a(arguments.getParcelable(ResponseConstants.OPTIONS));
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mParentView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mParentView == null) {
            this.mParentView = layoutInflater.inflate(this.mOptions.getLayoutId(), viewGroup, false);
        }
        return this.mParentView;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (passesPermissionCheck(getActivity())) {
            init(view);
        }
    }

    private void init(View view) {
        this.mImageView = (CropView) view.findViewById(i.cropped_image);
        this.mImageView.setUseOvalViewport(this.mOptions.useOvalViewport());
        this.mImageView.setViewportRatio(this.mOptions.getAspectRatioX() / this.mOptions.getAspectRatioY());
        if (this.mImageUrl != null) {
            getImageBatch().a(this.mImageUrl, (ImageView) this.mImageView);
        } else {
            g.b((Context) getActivity(), this.mSourceUri).a((Consumer<? super T>) new a<Object>(this), (Consumer<? super Throwable>) new b<Object>(this));
        }
        this.mHelpText = (TextView) view.findViewById(i.crop_help_text);
        this.mHelpText.setText(this.mOptions.getHelpTextId());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$init$0$CropImageFragment(Bitmap bitmap) throws Exception {
        this.mImageView.setImageBitmap(bitmap);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$init$1$CropImageFragment(Throwable th) throws Exception {
        aj.a(getContext(), getString(o.error_image_too_small, Integer.valueOf(this.mOptions.getMinWidth()), Integer.valueOf(this.mOptions.getMinHeight())));
        finishCropImage(false);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(l.shop_share_menu, menu);
        this.mNextButton = menu.findItem(i.menu_next);
        this.mNextButton.setOnMenuItemClickListener(this);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        cropImage();
        return true;
    }

    public boolean handleBackPressed() {
        finishCropImage(false);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040 A[SYNTHETIC, Splitter:B:15:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A[SYNTHETIC, Splitter:B:25:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void cropImage() {
        /*
            r5 = this;
            r0 = 0
            r1 = 1
            r2 = 0
            android.support.v4.app.FragmentActivity r3 = r5.getActivity()     // Catch:{ Exception -> 0x004c, all -> 0x003c }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x004c, all -> 0x003c }
            android.net.Uri r4 = r5.mDestUri     // Catch:{ Exception -> 0x004c, all -> 0x003c }
            java.io.OutputStream r3 = r3.openOutputStream(r4)     // Catch:{ Exception -> 0x004c, all -> 0x003c }
            if (r3 != 0) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            com.lyft.android.scissors.CropView r2 = r5.mImageView     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            com.lyft.android.scissors.CropView$a r2 = r2.extensions()     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            com.lyft.android.scissors.c$a r2 = r2.a()     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            com.lyft.android.scissors.c$a r2 = r2.a(r4)     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            r4 = 90
            com.lyft.android.scissors.c$a r2 = r2.a(r4)     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            java.util.concurrent.Future r2 = r2.a(r3, r0)     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            r2.get()     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            r0 = r1
        L_0x0032:
            if (r3 == 0) goto L_0x0057
            r3.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0057
        L_0x0038:
            r1 = move-exception
            goto L_0x0054
        L_0x003a:
            r0 = move-exception
            goto L_0x003e
        L_0x003c:
            r0 = move-exception
            r3 = r2
        L_0x003e:
            if (r3 == 0) goto L_0x0048
            r3.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r2 = move-exception
            com.google.a.a.a.a.a.a.a(r2)
        L_0x0048:
            r5.finishCropImage(r1)
            throw r0
        L_0x004c:
            r3 = r2
        L_0x004d:
            if (r3 == 0) goto L_0x0057
            r3.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r1 = move-exception
        L_0x0054:
            com.google.a.a.a.a.a.a.a(r1)
        L_0x0057:
            r5.finishCropImage(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.uikit.image.CropImageFragment.cropImage():void");
    }

    private void finishCropImage(boolean z) {
        getActivity().setResult(z ? 50 : 51, new Intent().putExtras(getArguments()));
        getActivity().finish();
    }

    private boolean passesPermissionCheck(Activity activity) {
        if (x.a((Context) activity, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        x.a(activity, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 10, (OnRequestPermissionsResultCallback) this);
        return false;
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i != 10) {
            return;
        }
        if (w.a(iArr)) {
            init(this.mParentView);
        } else {
            finishCropImage(false);
        }
    }
}
