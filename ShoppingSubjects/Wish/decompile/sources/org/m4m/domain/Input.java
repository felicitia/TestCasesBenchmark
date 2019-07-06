package org.m4m.domain;

abstract class Input implements IInput {
    private CommandQueue inputQueue = new CommandQueue();
    protected PluginState state = PluginState.Starting;
    protected int trackId;

    public abstract void configure();

    /* access modifiers changed from: protected */
    public void initInputCommandQueue() {
    }

    Input() {
    }

    /* access modifiers changed from: 0000 */
    public void setState(PluginState pluginState) {
        this.state = pluginState;
    }

    public CommandQueue getInputCommandQueue() {
        return this.inputQueue;
    }

    public void drain(int i) {
        setState(PluginState.Draining);
        getInputCommandQueue().clear();
    }

    /* access modifiers changed from: protected */
    public void feedMeIfNotDraining() {
        if (this.state != PluginState.Draining && this.state != PluginState.Drained) {
            getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(getTrackId()));
        }
    }

    public void skipProcessing() {
        getInputCommandQueue().clear();
        getInputCommandQueue().queue(Command.NextPair, Integer.valueOf(getTrackId()));
    }

    public int getTrackId() {
        return this.trackId;
    }

    public void setTrackId(int i) {
        this.trackId = i;
        initInputCommandQueue();
    }
}
