package com.platform.core;

import com.application.Application;
import com.application.Task;
import com.platform.Platform;

abstract public class Core {

    public enum CoreState { IDLE, PROCESSING };

    final float maxFrequency;
    float currentFrequency = 1;
    volatile CoreState state = CoreState.IDLE;

    public Core(float maxFrequency) { this.maxFrequency = maxFrequency; }

    abstract public void executeTask(Platform platform, Task task);

    public void setCurrentFrequency(float frequency) { this.currentFrequency = frequency; }
    public float getCurrentFrequency() { return this.currentFrequency; }

    public CoreState getCoreState() { return state; }
    public void setCoreState(CoreState state) { this.state = state; }

    public boolean isIdle() { return state == CoreState.IDLE; }
}
