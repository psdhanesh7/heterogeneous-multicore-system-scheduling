package com.platform.core;

import com.application.Task;
import com.platform.Platform;

public class HighPerformanceCore extends Core {

    public HighPerformanceCore(float maxFrequency) {
        super(maxFrequency);
    }

    public void executeTask(Platform platform, Task task) {
        task.setState(Task.State.RUNNING);
        this.setCoreState(Core.CoreState.PROCESSING);

        ExecuteTask thread = new ExecuteTask(this, task, (long) (task.getWHP() * 1000), platform);
        thread.start();
    }
}
