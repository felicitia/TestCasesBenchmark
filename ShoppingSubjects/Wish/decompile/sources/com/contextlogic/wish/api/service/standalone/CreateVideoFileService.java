package com.contextlogic.wish.api.service.standalone;

import android.os.Environment;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.LocalApiService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.StringUtil;
import java.io.File;
import java.io.IOException;

public class CreateVideoFileService extends LocalApiService<Void, File> {

    public interface SuccessCallback {
        void onSuccess(File file);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new LocalApiCallback<Void, File>() {
            public File processRequest(Void... voidArr) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), StringUtil.capitalize(WishApplication.getAppType()));
                if (!file.exists() && !file.mkdirs()) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(file.getPath());
                sb.append(File.separator);
                sb.append("video_");
                sb.append(String.valueOf(System.currentTimeMillis()));
                sb.append(".mp4");
                File file2 = new File(sb.toString());
                try {
                    if (!file2.createNewFile()) {
                        return null;
                    }
                    return file2;
                } catch (IOException unused) {
                    return null;
                }
            }

            public void processResult(File file) {
                if (file != null) {
                    successCallback.onSuccess(file);
                } else {
                    defaultFailureCallback.onFailure(WishApplication.getInstance().getString(R.string.failed_to_start_recording));
                }
            }
        }, new Void[0]);
    }
}
