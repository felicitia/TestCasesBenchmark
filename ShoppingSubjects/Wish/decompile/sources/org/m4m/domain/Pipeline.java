package org.m4m.domain;

import java.io.Closeable;
import java.io.IOException;
import org.m4m.AudioFormat;
import org.m4m.domain.pipeline.ConnectorFactory;
import org.m4m.domain.pipeline.IOnStopListener;

public class Pipeline {
    /* access modifiers changed from: private */
    public final ICommandProcessor commandProcessor;
    private IOnStopListener onStopListener = new IOnStopListener() {
        public void onStop() {
            Pipeline.this.commandProcessor.stop();
        }
    };
    private final TopologySolver topologySolver = new TopologySolver();

    public Pipeline(ICommandProcessor iCommandProcessor) {
        this.commandProcessor = iCommandProcessor;
    }

    public void setMediaSource(IOutput iOutput) {
        this.topologySolver.add((IOutputRaw) iOutput);
    }

    public void addVideoDecoder(Plugin plugin) {
        this.topologySolver.add((ITransform) plugin);
    }

    public void addVideoEncoder(VideoEncoder videoEncoder) {
        this.topologySolver.add((ITransform) videoEncoder);
    }

    public void addAudioDecoder(Plugin plugin) {
        this.topologySolver.add((ITransform) plugin);
    }

    public void addAudioEncoder(AudioEncoder audioEncoder) {
        this.topologySolver.add((ITransform) audioEncoder);
    }

    public void addVideoEffect(VideoEffector videoEffector) {
        this.topologySolver.add((ITransform) videoEffector);
    }

    public void addVideoTimeScaler(VideoTimeScaler videoTimeScaler) {
        this.topologySolver.add((ITransform) videoTimeScaler);
    }

    public void addAudioEffect(AudioEffector audioEffector) {
        this.topologySolver.add((ITransform) audioEffector);
    }

    public void setSink(Render render) {
        this.topologySolver.add((IInputRaw) render);
        if (render != null) {
            render.addOnStopListener(this.onStopListener);
        }
    }

    public void resolve() {
        ConnectorFactory connectorFactory = new ConnectorFactory(this.commandProcessor, getAudioFormat());
        for (IsConnectable addConnectionRule : connectorFactory.createConnectionRules()) {
            this.topologySolver.addConnectionRule(addConnectionRule);
        }
        for (Pair pair : this.topologySolver.getConnectionsQueue()) {
            connectorFactory.connect((IOutputRaw) pair.left, (IInputRaw) pair.right);
        }
        startSource();
    }

    private void startSource() {
        for (IOutputRaw iOutputRaw : this.topologySolver.getSources()) {
            ((IRunnable) iOutputRaw).start();
        }
    }

    private AudioFormat getAudioFormat() {
        AudioFormat audioFormat = null;
        for (IOutputRaw iOutputRaw : this.topologySolver.getSources()) {
            if (iOutputRaw instanceof IOutput) {
                audioFormat = (AudioFormat) ((IOutput) iOutputRaw).getMediaFormatByType(MediaFormatType.AUDIO);
                if (audioFormat != null) {
                    break;
                }
            }
        }
        return audioFormat;
    }

    public void stop() {
        for (IOutputRaw iOutputRaw : this.topologySolver.getSources()) {
            ((IRunnable) iOutputRaw).stop();
        }
    }

    public void release() throws IOException {
        for (IOutputRaw iOutputRaw : this.topologySolver.getSources()) {
            ((Closeable) iOutputRaw).close();
        }
        for (IInputRaw iInputRaw : this.topologySolver.getSinks()) {
            ((Closeable) iInputRaw).close();
        }
    }
}
