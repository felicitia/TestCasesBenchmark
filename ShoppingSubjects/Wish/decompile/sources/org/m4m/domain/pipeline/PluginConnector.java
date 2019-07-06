package org.m4m.domain.pipeline;

import org.m4m.AudioFormat;
import org.m4m.domain.AudioDecoder;
import org.m4m.domain.AudioEffector;
import org.m4m.domain.AudioEncoder;
import org.m4m.domain.Command;
import org.m4m.domain.CommandHandlerFactory;
import org.m4m.domain.Encoder;
import org.m4m.domain.ICameraSource;
import org.m4m.domain.ICaptureSource;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.ICommandProcessor;
import org.m4m.domain.IEglContext;
import org.m4m.domain.IFrameAllocator;
import org.m4m.domain.IHandlerCreator;
import org.m4m.domain.IMediaSource;
import org.m4m.domain.IMicrophoneSource;
import org.m4m.domain.IOnSurfaceReady;
import org.m4m.domain.IOutput;
import org.m4m.domain.IPluginOutput;
import org.m4m.domain.ISurfaceListener;
import org.m4m.domain.IVideoOutput;
import org.m4m.domain.MediaCodecPlugin;
import org.m4m.domain.MediaFormatType;
import org.m4m.domain.OutputInputPair;
import org.m4m.domain.Pair;
import org.m4m.domain.PassThroughPlugin;
import org.m4m.domain.Plugin;
import org.m4m.domain.Render;
import org.m4m.domain.SurfaceRender;
import org.m4m.domain.VideoDecoder;
import org.m4m.domain.VideoEffector;
import org.m4m.domain.VideoEncoder;
import org.m4m.domain.VideoTimeScaler;

class PluginConnector {
    private final ICommandProcessor commandProcessor;

    public PluginConnector(ICommandProcessor iCommandProcessor) {
        this.commandProcessor = iCommandProcessor;
    }

    public void connect(IMediaSource iMediaSource, Plugin plugin) {
        int trackIdByMediaType = iMediaSource.getTrackIdByMediaType(plugin.getMediaFormatType());
        iMediaSource.selectTrack(trackIdByMediaType);
        plugin.setTrackId(trackIdByMediaType);
        configureCommandProcessorPush(iMediaSource, iMediaSource, plugin);
    }

    public void connect(VideoDecoder videoDecoder, VideoEncoder videoEncoder) {
        configureCommandProcessorPushSurfaceDecoderEncoder(videoDecoder, videoEncoder);
        videoEncoder.configure();
        videoDecoder.setOutputSurface(videoEncoder.getSurface());
        videoEncoder.start();
        videoDecoder.configure();
        videoDecoder.start();
    }

    public void connect(AudioDecoder audioDecoder, AudioEncoder audioEncoder, AudioFormat audioFormat) {
        if (audioFormat == null) {
            throw new UnsupportedOperationException("Audio format not specified.");
        }
        configureAudioPipelineCommandProcessorCopy(audioDecoder, audioEncoder);
        audioDecoder.configure();
        audioDecoder.start();
        audioEncoder.configure();
        audioEncoder.start();
    }

    public void connect(final VideoEffector videoEffector, final VideoEncoder videoEncoder) {
        configureCommandProcessorPushSurfaceEffector(videoEffector, videoEncoder);
        videoEffector.onSurfaceAvailable(new ISurfaceListener() {
            public void onSurfaceAvailable(IEglContext iEglContext) {
                videoEncoder.configure();
                videoEffector.setOutputSurface(videoEncoder.getSimpleSurface(iEglContext));
                videoEffector.configure();
                videoEffector.start();
                videoEncoder.start();
            }
        });
    }

    public void connect(VideoDecoder videoDecoder, VideoEffector videoEffector) {
        configureCommandProcessorPushSurfaceEffector(videoDecoder, videoDecoder, videoEffector);
        videoDecoder.setOutputSurface(videoEffector.getSurface());
        videoDecoder.configure();
        videoDecoder.start();
    }

    public void connect(AudioEffector audioEffector, AudioEncoder audioEncoder, AudioFormat audioFormat) {
        if (audioFormat == null) {
            throw new UnsupportedOperationException("Audio format not specified.");
        }
        configureAudioPipelineCommandProcessorCopy(audioEffector, audioEncoder);
        audioEncoder.configure();
        audioEncoder.start();
        audioEffector.configure();
        audioEffector.start();
    }

    public void connect(AudioDecoder audioDecoder, AudioEffector audioEffector) {
        configureAudioPipelineCommandProcessorCopy(audioDecoder, audioEffector);
        audioDecoder.configure();
        audioDecoder.start();
    }

    private void configureCommandProcessorPush(final IOutput iOutput, final IVideoOutput iVideoOutput, final Plugin plugin) {
        if (plugin instanceof IFrameAllocator) {
            CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
            commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(plugin.getTrackId())), new Pair(Command.NeedData, Integer.valueOf(plugin.getTrackId())), new IHandlerCreator() {
                public ICommandHandler create() {
                    return new PushDataCommandHandler(iOutput, plugin, (IFrameAllocator) plugin);
                }
            });
            commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(plugin.getTrackId())), new Pair(Command.NeedData, Integer.valueOf(plugin.getTrackId())), new IHandlerCreator() {
                public ICommandHandler create() {
                    return new OutputFormatChangedHandler(iOutput, plugin, (IFrameAllocator) plugin);
                }
            });
            commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(plugin.getTrackId())), new Pair(Command.NeedData, Integer.valueOf(plugin.getTrackId())), new IHandlerCreator() {
                public ICommandHandler create() {
                    return new EofCommandHandler(iOutput, plugin, (IFrameAllocator) plugin);
                }
            });
            commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(plugin.getTrackId())), new Pair(Command.NeedInputFormat, Integer.valueOf(plugin.getTrackId())), new IHandlerCreator() {
                public ICommandHandler create() {
                    return new EofCommandHandler(iOutput, plugin, (IFrameAllocator) plugin);
                }
            });
            commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(plugin.getTrackId())), new Pair(Command.NeedInputFormat, Integer.valueOf(plugin.getTrackId())), new IHandlerCreator() {
                public ICommandHandler create() {
                    return new ConfigureVideoDecoderCommandHandler(iVideoOutput, plugin);
                }
            });
            this.commandProcessor.add(new OutputInputPair(iOutput, plugin, commandHandlerFactory));
        }
        plugin.setMediaFormat(iOutput.getMediaFormatByType(plugin.getMediaFormatType()));
        iOutput.incrementConnectedPluginsCount();
    }

    private void configureCommandProcessorPushSurfaceDecoderEncoder(final IPluginOutput iPluginOutput, final MediaCodecPlugin mediaCodecPlugin) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PushSurfaceCommandHandler(iPluginOutput, mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(mediaCodecPlugin);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iPluginOutput, mediaCodecPlugin, commandHandlerFactory));
    }

    private void configureCommandProcessorPushSurfaceSurfaceRender(final IPluginOutput iPluginOutput, final SurfaceRender surfaceRender) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PushSurfaceCommandHandlerForSurfaceRender(iPluginOutput, surfaceRender);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(surfaceRender);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iPluginOutput, surfaceRender, commandHandlerFactory));
    }

    private void configureCommandProcessorPushSurfaceEffector(final IPluginOutput iPluginOutput, final MediaCodecPlugin mediaCodecPlugin) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PushSurfaceCommandHandlerForEffector(iPluginOutput, mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(mediaCodecPlugin);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iPluginOutput, mediaCodecPlugin, commandHandlerFactory));
    }

    private void configureCommandProcessorPushSurfaceEffector(final IPluginOutput iPluginOutput, final IVideoOutput iVideoOutput, final MediaCodecPlugin mediaCodecPlugin) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PushSurfaceCommandHandlerForEffector(iPluginOutput, mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedInputFormat, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new ConfigureVideoEffectorCommandHandler(iVideoOutput, mediaCodecPlugin);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iPluginOutput, mediaCodecPlugin, commandHandlerFactory));
    }

    private void configureAudioPipelineCommandProcessorCopy(final MediaCodecPlugin mediaCodecPlugin, final MediaCodecPlugin mediaCodecPlugin2) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new CopyDataCommandHandler(mediaCodecPlugin, mediaCodecPlugin2);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new AudioPipelineOutputFormatChangeCommandHandler(mediaCodecPlugin, mediaCodecPlugin2);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(mediaCodecPlugin2);
            }
        });
        this.commandProcessor.add(new OutputInputPair(mediaCodecPlugin, mediaCodecPlugin2, commandHandlerFactory));
    }

    public void connect(final ICaptureSource iCaptureSource, final Encoder encoder) {
        configureCommandProcessorPushSurfaceDecoderEncoder(iCaptureSource, encoder);
        iCaptureSource.addSetSurfaceListener(new ISurfaceListener() {
            public void onSurfaceAvailable(IEglContext iEglContext) {
                encoder.configure();
                iCaptureSource.setOutputSurface(encoder.getSurface());
                encoder.start();
            }
        });
    }

    public void connect(final VideoDecoder videoDecoder, final SurfaceRender surfaceRender) {
        configureCommandProcessorPushSurfaceSurfaceRender(videoDecoder, surfaceRender);
        surfaceRender.onSurfaceAvailable(new IOnSurfaceReady() {
            public void onSurfaceReady() {
                videoDecoder.setOutputSurface(surfaceRender.getSurface());
                videoDecoder.configure();
                videoDecoder.start();
            }
        });
    }

    public void connect(final IPluginOutput iPluginOutput, final Render render) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        if ((iPluginOutput instanceof Encoder) || (iPluginOutput instanceof PassThroughPlugin)) {
            commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedInputFormat, Integer.valueOf(0)), new IHandlerCreator() {
                public ICommandHandler create() {
                    return new EncoderMediaFormatChangedCommandHandler((Plugin) iPluginOutput, render);
                }
            });
        }
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PullDataCommandHandler(iPluginOutput, render);
            }
        });
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedInputFormat, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PullDataCommandHandler(iPluginOutput, render);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainRenderCommandHandler(render);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedInputFormat, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainRenderCommandHandler(render);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iPluginOutput, render, commandHandlerFactory));
        render.configure();
    }

    public void connect(final IMediaSource iMediaSource, final Render render) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PushNewDataCommandHandler(iMediaSource, render);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iMediaSource, render, commandHandlerFactory));
        render.setMediaFormat(iMediaSource.getMediaFormatByType(MediaFormatType.VIDEO));
        render.configure();
        render.getInputCommandQueue().clear();
        render.start();
        iMediaSource.selectTrack(iMediaSource.getTrackIdByMediaType(MediaFormatType.VIDEO));
    }

    public void connect(final ICameraSource iCameraSource, final Encoder encoder) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new CaptureSourcePullSurfaceCommandHandler(iCameraSource, encoder);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(encoder);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(encoder);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iCameraSource, encoder, commandHandlerFactory));
        encoder.configure();
        iCameraSource.setOutputSurface(encoder.getSurface());
        encoder.start();
        iCameraSource.configure();
    }

    public void connect(IMicrophoneSource iMicrophoneSource, AudioEffector audioEffector) {
        configureAudioPipelineWithEffectorCommandProcessorCopy(iMicrophoneSource, audioEffector);
        audioEffector.reInitInputCommandQueue();
        audioEffector.configure();
        audioEffector.start();
    }

    private void configureAudioPipelineWithEffectorCommandProcessorCopy(final IMicrophoneSource iMicrophoneSource, final MediaCodecPlugin mediaCodecPlugin) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new PullFrameToEffectorFromMicrophoneSourceCommandHandler(iMicrophoneSource, mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(mediaCodecPlugin);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(mediaCodecPlugin);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iMicrophoneSource, mediaCodecPlugin, commandHandlerFactory));
    }

    public void connect(final IMicrophoneSource iMicrophoneSource, final AudioEncoder audioEncoder) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new MicrophoneSourcePullFrameCommandHandler(iMicrophoneSource, audioEncoder);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(audioEncoder);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(audioEncoder);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iMicrophoneSource, audioEncoder, commandHandlerFactory));
        audioEncoder.configure();
        audioEncoder.start();
    }

    public void connect(final ICameraSource iCameraSource, final VideoEffector videoEffector) {
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new CaptureSourcePullSurfaceCommandHandler(iCameraSource, videoEffector);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new SkipOutputFormatChangeCommandHandler(videoEffector);
            }
        });
        commandHandlerFactory.register(new Pair(Command.OutputFormatChanged, Integer.valueOf(0)), new Pair(Command.NeedInputFormat, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new ConfigureVideoEffectorCommandHandler(iCameraSource, videoEffector);
            }
        });
        commandHandlerFactory.register(new Pair(Command.HasData, Integer.valueOf(0)), new Pair(Command.NeedInputFormat, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new ConfigureVideoEffectorCommandHandler(iCameraSource, videoEffector);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedData, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(videoEffector);
            }
        });
        commandHandlerFactory.register(new Pair(Command.EndOfFile, Integer.valueOf(0)), new Pair(Command.NeedInputFormat, Integer.valueOf(0)), new IHandlerCreator() {
            public ICommandHandler create() {
                return new DrainCommandHandler(videoEffector);
            }
        });
        this.commandProcessor.add(new OutputInputPair(iCameraSource, videoEffector, commandHandlerFactory));
        videoEffector.onSurfaceAvailable(new ISurfaceListener() {
            public void onSurfaceAvailable(IEglContext iEglContext) {
                iCameraSource.setPreview(videoEffector.getPreview());
                iCameraSource.setOutputSurface(videoEffector.getSurface());
                iCameraSource.configure();
            }
        });
    }

    public void connect(VideoDecoder videoDecoder, VideoTimeScaler videoTimeScaler) {
        configureCommandProcessorPushSurfaceEffector(videoDecoder, videoDecoder, videoTimeScaler);
        videoDecoder.setOutputSurface(videoTimeScaler.getSurface());
        videoDecoder.configure();
        videoDecoder.start();
    }

    public void connect(final VideoTimeScaler videoTimeScaler, final VideoEncoder videoEncoder) {
        configureCommandProcessorPushSurfaceEffector(videoTimeScaler, videoEncoder);
        videoTimeScaler.onSurfaceAvailable(new ISurfaceListener() {
            public void onSurfaceAvailable(IEglContext iEglContext) {
                videoEncoder.configure();
                videoTimeScaler.setOutputSurface(videoEncoder.getSimpleSurface(iEglContext));
                videoTimeScaler.configure();
                videoTimeScaler.start();
                videoEncoder.start();
            }
        });
    }
}
