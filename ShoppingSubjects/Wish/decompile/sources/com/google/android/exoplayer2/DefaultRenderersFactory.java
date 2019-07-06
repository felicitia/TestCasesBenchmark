package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    private final long allowedVideoJoiningTimeMs;
    private final Context context;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final int extensionRendererMode;

    /* access modifiers changed from: protected */
    public void buildMiscellaneousRenderers(Context context2, Handler handler, int i, ArrayList<Renderer> arrayList) {
    }

    public DefaultRenderersFactory(Context context2) {
        this(context2, null);
    }

    public DefaultRenderersFactory(Context context2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2) {
        this(context2, drmSessionManager2, 0);
    }

    public DefaultRenderersFactory(Context context2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, int i) {
        this(context2, drmSessionManager2, i, 5000);
    }

    public DefaultRenderersFactory(Context context2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, int i, long j) {
        this.context = context2;
        this.drmSessionManager = drmSessionManager2;
        this.extensionRendererMode = i;
        this.allowedVideoJoiningTimeMs = j;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
        ArrayList arrayList = new ArrayList();
        buildVideoRenderers(this.context, this.drmSessionManager, this.allowedVideoJoiningTimeMs, handler, videoRendererEventListener, this.extensionRendererMode, arrayList);
        buildAudioRenderers(this.context, this.drmSessionManager, buildAudioProcessors(), handler, audioRendererEventListener, this.extensionRendererMode, arrayList);
        ArrayList arrayList2 = arrayList;
        buildTextRenderers(this.context, textOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildMetadataRenderers(this.context, metadataOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[arrayList.size()]);
    }

    /* access modifiers changed from: protected */
    public void buildVideoRenderers(Context context2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, ArrayList<Renderer> arrayList) {
        int i2 = i;
        ArrayList<Renderer> arrayList2 = arrayList;
        MediaCodecVideoRenderer mediaCodecVideoRenderer = new MediaCodecVideoRenderer(context2, MediaCodecSelector.DEFAULT, j, drmSessionManager2, false, handler, videoRendererEventListener, 50);
        arrayList2.add(mediaCodecVideoRenderer);
        if (i2 != 0) {
            int size = arrayList.size();
            if (i2 == 2) {
                size--;
            }
            try {
                arrayList2.add(size, (Renderer) Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer").getConstructor(new Class[]{Boolean.TYPE, Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE}).newInstance(new Object[]{Boolean.valueOf(true), Long.valueOf(j), handler, videoRendererEventListener, Integer.valueOf(50)}));
                Log.i("DefaultRenderersFactory", "Loaded LibvpxVideoRenderer.");
            } catch (ClassNotFoundException unused) {
            } catch (Exception e) {
                throw new RuntimeException("Error instantiating VP9 extension", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0065, code lost:
        throw new java.lang.RuntimeException("Error instantiating Opus extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0066, code lost:
        r7 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a3, code lost:
        throw new java.lang.RuntimeException("Error instantiating FLAC extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a4, code lost:
        r6 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005c A[ExcHandler: Exception (r0v2 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009a A[ExcHandler: Exception (r0v1 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:19:0x0069] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildAudioRenderers(android.content.Context r13, com.google.android.exoplayer2.drm.DrmSessionManager<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r14, com.google.android.exoplayer2.audio.AudioProcessor[] r15, android.os.Handler r16, com.google.android.exoplayer2.audio.AudioRendererEventListener r17, int r18, java.util.ArrayList<com.google.android.exoplayer2.Renderer> r19) {
        /*
            r12 = this;
            r9 = r18
            r10 = r19
            com.google.android.exoplayer2.audio.MediaCodecAudioRenderer r11 = new com.google.android.exoplayer2.audio.MediaCodecAudioRenderer
            com.google.android.exoplayer2.mediacodec.MediaCodecSelector r2 = com.google.android.exoplayer2.mediacodec.MediaCodecSelector.DEFAULT
            com.google.android.exoplayer2.audio.AudioCapabilities r7 = com.google.android.exoplayer2.audio.AudioCapabilities.getCapabilities(r13)
            r4 = 1
            r1 = r11
            r3 = r14
            r5 = r16
            r6 = r17
            r8 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            r10.add(r11)
            if (r9 != 0) goto L_0x001d
            return
        L_0x001d:
            int r1 = r19.size()
            r2 = 2
            if (r9 != r2) goto L_0x0026
            int r1 = r1 + -1
        L_0x0026:
            r3 = 0
            r4 = 3
            r5 = 1
            java.lang.String r6 = "com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer"
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            java.lang.Class[] r7 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            java.lang.Class<android.os.Handler> r8 = android.os.Handler.class
            r7[r3] = r8     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioRendererEventListener> r8 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class
            r7[r5] = r8     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioProcessor[]> r8 = com.google.android.exoplayer2.audio.AudioProcessor[].class
            r7[r2] = r8     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            java.lang.reflect.Constructor r6 = r6.getConstructor(r7)     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            r7[r3] = r16     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            r7[r5] = r17     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            r7[r2] = r15     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            java.lang.Object r6 = r6.newInstance(r7)     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            com.google.android.exoplayer2.Renderer r6 = (com.google.android.exoplayer2.Renderer) r6     // Catch:{ ClassNotFoundException -> 0x0066, Exception -> 0x005c }
            int r7 = r1 + 1
            r10.add(r1, r6)     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005c }
            java.lang.String r1 = "DefaultRenderersFactory"
            java.lang.String r6 = "Loaded LibopusAudioRenderer."
            android.util.Log.i(r1, r6)     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005c }
            goto L_0x0067
        L_0x005c:
            r0 = move-exception
            r1 = r0
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.String r3 = "Error instantiating Opus extension"
            r2.<init>(r3, r1)
            throw r2
        L_0x0066:
            r7 = r1
        L_0x0067:
            java.lang.String r1 = "com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            java.lang.Class<android.os.Handler> r8 = android.os.Handler.class
            r6[r3] = r8     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioRendererEventListener> r8 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class
            r6[r5] = r8     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioProcessor[]> r8 = com.google.android.exoplayer2.audio.AudioProcessor[].class
            r6[r2] = r8     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            r6[r3] = r16     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            r6[r5] = r17     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            r6[r2] = r15     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            java.lang.Object r1 = r1.newInstance(r6)     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            com.google.android.exoplayer2.Renderer r1 = (com.google.android.exoplayer2.Renderer) r1     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009a }
            int r6 = r7 + 1
            r10.add(r7, r1)     // Catch:{ ClassNotFoundException -> 0x00a5, Exception -> 0x009a }
            java.lang.String r1 = "DefaultRenderersFactory"
            java.lang.String r7 = "Loaded LibflacAudioRenderer."
            android.util.Log.i(r1, r7)     // Catch:{ ClassNotFoundException -> 0x00a5, Exception -> 0x009a }
            goto L_0x00a5
        L_0x009a:
            r0 = move-exception
            r1 = r0
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.String r3 = "Error instantiating FLAC extension"
            r2.<init>(r3, r1)
            throw r2
        L_0x00a4:
            r6 = r7
        L_0x00a5:
            java.lang.String r1 = "com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.Class[] r7 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.Class<android.os.Handler> r8 = android.os.Handler.class
            r7[r3] = r8     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioRendererEventListener> r8 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class
            r7[r5] = r8     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioProcessor[]> r8 = com.google.android.exoplayer2.audio.AudioProcessor[].class
            r7[r2] = r8     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r7)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            r4[r3] = r16     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            r4[r5] = r17     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            r4[r2] = r15     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.Object r1 = r1.newInstance(r4)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            com.google.android.exoplayer2.Renderer r1 = (com.google.android.exoplayer2.Renderer) r1     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            r10.add(r6, r1)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            java.lang.String r1 = "DefaultRenderersFactory"
            java.lang.String r2 = "Loaded FfmpegAudioRenderer."
            android.util.Log.i(r1, r2)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d6 }
            goto L_0x00e0
        L_0x00d6:
            r0 = move-exception
            r1 = r0
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.String r3 = "Error instantiating FFmpeg extension"
            r2.<init>(r3, r1)
            throw r2
        L_0x00e0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.DefaultRenderersFactory.buildAudioRenderers(android.content.Context, com.google.android.exoplayer2.drm.DrmSessionManager, com.google.android.exoplayer2.audio.AudioProcessor[], android.os.Handler, com.google.android.exoplayer2.audio.AudioRendererEventListener, int, java.util.ArrayList):void");
    }

    /* access modifiers changed from: protected */
    public void buildTextRenderers(Context context2, TextOutput textOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(textOutput, looper));
    }

    /* access modifiers changed from: protected */
    public void buildMetadataRenderers(Context context2, MetadataOutput metadataOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(metadataOutput, looper));
    }

    /* access modifiers changed from: protected */
    public AudioProcessor[] buildAudioProcessors() {
        return new AudioProcessor[0];
    }
}
