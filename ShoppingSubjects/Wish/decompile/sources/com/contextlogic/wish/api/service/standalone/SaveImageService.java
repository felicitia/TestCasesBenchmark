package com.contextlogic.wish.api.service.standalone;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.LocalApiService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.StringUtil;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraUtils.BitmapCallback;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageService extends LocalApiService<Void, File> {

    public interface SuccessCallback {
        void onSuccess(Uri uri);
    }

    public void requestService(byte[] bArr, Bitmap bitmap, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        final DefaultFailureCallback defaultFailureCallback2 = defaultFailureCallback;
        final Bitmap bitmap2 = bitmap;
        final SuccessCallback successCallback2 = successCallback;
        final byte[] bArr2 = bArr;
        AnonymousClass1 r0 = new LocalApiCallback<Void, File>() {
            public File processRequest(Void... voidArr) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), StringUtil.capitalize(WishApplication.getAppType()));
                if (!file.exists() && !file.mkdirs()) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(file.getPath());
                sb.append(File.separator);
                sb.append("image_");
                sb.append(String.valueOf(System.currentTimeMillis()));
                sb.append(".jpg");
                return new File(sb.toString());
            }

            public void processResult(final File file) {
                if (file == null) {
                    defaultFailureCallback2.onFailure(WishApplication.getInstance().getString(R.string.failed_to_save_image));
                    return;
                }
                if (bitmap2 == null) {
                    CameraUtils.decodeBitmap(bArr2, DisplayUtil.getDisplayWidth(), DisplayUtil.getDisplayHeight(), new BitmapCallback() {
                        public void onBitmapReady(Bitmap bitmap) {
                            if (SaveImageService.this.saveBitmap(bitmap, file)) {
                                successCallback2.onSuccess(Uri.fromFile(file));
                            } else {
                                defaultFailureCallback2.onFailure(WishApplication.getInstance().getString(R.string.failed_to_save_image));
                            }
                        }
                    });
                } else if (SaveImageService.this.saveBitmap(bitmap2, file)) {
                    successCallback2.onSuccess(Uri.fromFile(file));
                } else {
                    defaultFailureCallback2.onFailure(WishApplication.getInstance().getString(R.string.failed_to_save_image));
                }
            }
        };
        startService(r0, new Void[0]);
    }

    /* access modifiers changed from: private */
    public boolean saveBitmap(Bitmap bitmap, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException unused) {
            return false;
        } catch (IOException unused2) {
            return false;
        } catch (RuntimeException unused3) {
            return false;
        }
    }
}
