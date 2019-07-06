package com.otaliastudios.cameraview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.ViewGroup;

class TextureCameraPreview extends CameraPreview<TextureView, SurfaceTexture> {
    TextureCameraPreview(Context context, ViewGroup viewGroup, SurfaceCallback surfaceCallback) {
        super(context, viewGroup, surfaceCallback);
    }

    /* access modifiers changed from: protected */
    public TextureView onCreateView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.texture_view, viewGroup, false);
        viewGroup.addView(inflate, 0);
        TextureView textureView = (TextureView) inflate.findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(new SurfaceTextureListener() {
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                TextureCameraPreview.this.onSurfaceAvailable(i, i2);
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                TextureCameraPreview.this.onSurfaceSizeChanged(i, i2);
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                TextureCameraPreview.this.onSurfaceDestroyed();
                return true;
            }
        });
        return textureView;
    }

    /* access modifiers changed from: 0000 */
    public Class<SurfaceTexture> getOutputClass() {
        return SurfaceTexture.class;
    }

    /* access modifiers changed from: 0000 */
    public SurfaceTexture getOutput() {
        return ((TextureView) getView()).getSurfaceTexture();
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(15)
    public void setDesiredSize(int i, int i2) {
        super.setDesiredSize(i, i2);
        if (((TextureView) getView()).getSurfaceTexture() != null) {
            ((TextureView) getView()).getSurfaceTexture().setDefaultBufferSize(i, i2);
        }
    }
}
