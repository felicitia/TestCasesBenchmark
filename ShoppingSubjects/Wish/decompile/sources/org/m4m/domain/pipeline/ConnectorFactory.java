package org.m4m.domain.pipeline;

import java.util.Collection;
import java.util.LinkedList;
import org.m4m.AudioFormat;
import org.m4m.domain.AudioDecoder;
import org.m4m.domain.AudioEffector;
import org.m4m.domain.AudioEncoder;
import org.m4m.domain.Encoder;
import org.m4m.domain.ICameraSource;
import org.m4m.domain.ICaptureSource;
import org.m4m.domain.ICommandProcessor;
import org.m4m.domain.IInputRaw;
import org.m4m.domain.IMediaSource;
import org.m4m.domain.IMicrophoneSource;
import org.m4m.domain.IOutputRaw;
import org.m4m.domain.IPluginOutput;
import org.m4m.domain.IsConnectable;
import org.m4m.domain.MediaFormatType;
import org.m4m.domain.MediaSource;
import org.m4m.domain.PassThroughPlugin;
import org.m4m.domain.Plugin;
import org.m4m.domain.Render;
import org.m4m.domain.SurfaceRender;
import org.m4m.domain.VideoDecoder;
import org.m4m.domain.VideoEffector;
import org.m4m.domain.VideoEncoder;
import org.m4m.domain.VideoTimeScaler;

public class ConnectorFactory {
    private final AudioFormat audioMediaFormat;
    private final ICommandProcessor commandProcessor;

    public ConnectorFactory(ICommandProcessor iCommandProcessor, AudioFormat audioFormat) {
        this.commandProcessor = iCommandProcessor;
        this.audioMediaFormat = audioFormat;
    }

    public void connect(IOutputRaw iOutputRaw, IInputRaw iInputRaw) {
        boolean z = iOutputRaw instanceof IMediaSource;
        if (z && (iInputRaw instanceof Plugin)) {
            new PluginConnector(this.commandProcessor).connect((IMediaSource) iOutputRaw, (Plugin) iInputRaw);
        } else if (!z || !(iInputRaw instanceof Render)) {
            boolean z2 = iOutputRaw instanceof VideoDecoder;
            if (!z2 || !(iInputRaw instanceof VideoEncoder)) {
                boolean z3 = iOutputRaw instanceof AudioDecoder;
                if (z3 && (iInputRaw instanceof AudioEncoder)) {
                    new PluginConnector(this.commandProcessor).connect((AudioDecoder) iOutputRaw, (AudioEncoder) iInputRaw, this.audioMediaFormat);
                } else if ((iOutputRaw instanceof VideoEffector) && (iInputRaw instanceof VideoEncoder)) {
                    new PluginConnector(this.commandProcessor).connect((VideoEffector) iOutputRaw, (VideoEncoder) iInputRaw);
                } else if (!z2 || !(iInputRaw instanceof VideoEffector)) {
                    if (z2 && (iInputRaw instanceof SurfaceRender)) {
                        new PluginConnector(this.commandProcessor).connect((VideoDecoder) iOutputRaw, (SurfaceRender) iInputRaw);
                    }
                    if ((iOutputRaw instanceof AudioEffector) && (iInputRaw instanceof AudioEncoder)) {
                        new PluginConnector(this.commandProcessor).connect((AudioEffector) iOutputRaw, (AudioEncoder) iInputRaw, this.audioMediaFormat);
                    } else if (z3 && (iInputRaw instanceof AudioEffector)) {
                        new PluginConnector(this.commandProcessor).connect((AudioDecoder) iOutputRaw, (AudioEffector) iInputRaw);
                    } else if (!(iOutputRaw instanceof ICaptureSource) || !(iInputRaw instanceof Encoder)) {
                        boolean z4 = iOutputRaw instanceof ICameraSource;
                        if (z4 && (iInputRaw instanceof Encoder)) {
                            new PluginConnector(this.commandProcessor).connect((ICameraSource) iOutputRaw, (Encoder) iInputRaw);
                        } else if (!z4 || !(iInputRaw instanceof VideoEffector)) {
                            boolean z5 = iOutputRaw instanceof IMicrophoneSource;
                            if (z5 && (iInputRaw instanceof AudioEncoder)) {
                                new PluginConnector(this.commandProcessor).connect((IMicrophoneSource) iOutputRaw, (AudioEncoder) iInputRaw);
                            } else if (z5 && (iInputRaw instanceof AudioEffector)) {
                                new PluginConnector(this.commandProcessor).connect((IMicrophoneSource) iOutputRaw, (AudioEffector) iInputRaw);
                            } else if ((iOutputRaw instanceof IPluginOutput) && (iInputRaw instanceof Render)) {
                                new PluginConnector(this.commandProcessor).connect((IPluginOutput) iOutputRaw, (Render) iInputRaw);
                            } else if ((iOutputRaw instanceof VideoTimeScaler) && (iInputRaw instanceof VideoEncoder)) {
                                new PluginConnector(this.commandProcessor).connect((VideoTimeScaler) iOutputRaw, (VideoEncoder) iInputRaw);
                            } else if (!z2 || !(iInputRaw instanceof VideoTimeScaler)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("No connection between ");
                                sb.append(iOutputRaw.getClass().toString());
                                sb.append(" and ");
                                sb.append(iInputRaw.getClass().toString());
                                throw new RuntimeException(sb.toString());
                            } else {
                                new PluginConnector(this.commandProcessor).connect((VideoDecoder) iOutputRaw, (VideoTimeScaler) iInputRaw);
                            }
                        } else {
                            new PluginConnector(this.commandProcessor).connect((ICameraSource) iOutputRaw, (VideoEffector) iInputRaw);
                        }
                    } else {
                        new PluginConnector(this.commandProcessor).connect((ICaptureSource) iOutputRaw, (Encoder) iInputRaw);
                    }
                } else {
                    new PluginConnector(this.commandProcessor).connect((VideoDecoder) iOutputRaw, (VideoEffector) iInputRaw);
                }
            } else {
                new PluginConnector(this.commandProcessor).connect((VideoDecoder) iOutputRaw, (VideoEncoder) iInputRaw);
            }
        } else {
            new PluginConnector(this.commandProcessor).connect((IMediaSource) iOutputRaw, (Render) iInputRaw);
        }
    }

    public Collection<IsConnectable> createConnectionRules() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(OneToOneConnectable.OneToOneConnection(ICaptureSource.class, VideoEncoder.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(ICaptureSource.class, VideoEffector.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(ICameraSource.class, VideoEncoder.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(ICameraSource.class, VideoEffector.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(VideoDecoder.class, VideoEncoder.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(VideoDecoder.class, VideoEffector.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(VideoDecoder.class, SurfaceRender.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(VideoEncoder.class, Render.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(VideoEffector.class, VideoEncoder.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(AudioEffector.class, AudioEncoder.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(IMicrophoneSource.class, AudioEffector.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(IMicrophoneSource.class, AudioEncoder.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(IMediaSource.class, Render.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(VideoDecoder.class, VideoTimeScaler.class));
        linkedList.add(OneToOneConnectable.OneToOneConnection(VideoTimeScaler.class, VideoEncoder.class));
        linkedList.add(new OneToOneConnectable<AudioDecoder, AudioEffector>(AudioDecoder.class, AudioEffector.class) {
            public boolean additionalCheck(IOutputRaw iOutputRaw, IInputRaw iInputRaw) {
                return ((AudioDecoder) iOutputRaw).getMediaFormatType() == MediaFormatType.AUDIO;
            }
        });
        linkedList.add(new OneToOneConnectable<AudioDecoder, AudioEncoder>(AudioDecoder.class, AudioEncoder.class) {
            public boolean additionalCheck(IOutputRaw iOutputRaw, IInputRaw iInputRaw) {
                return ((AudioDecoder) iOutputRaw).getMediaFormatType() == MediaFormatType.AUDIO;
            }
        });
        linkedList.add(ManyToOneConnectable.ManyToOneConnections(new ManyTypes(PassThroughPlugin.class, Encoder.class, AudioEncoder.class), Render.class));
        linkedList.add(OneToManyConnectable.OneToManyConnection(MediaSource.class, new ManyTypes(VideoDecoder.class, AudioDecoder.class)));
        linkedList.add(OneToManyConnectable.OneToManyConnection(IMediaSource.class, new ManyTypes(VideoDecoder.class, AudioDecoder.class, PassThroughPlugin.class)));
        return linkedList;
    }
}
