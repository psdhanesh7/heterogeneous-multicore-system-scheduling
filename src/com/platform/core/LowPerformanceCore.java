package com.platform.core;

import com.application.Task;
import com.platform.Platform;

public class LowPerformanceCore extends Core {

    public LowPerformanceCore(float maxFrequency) {
        super(maxFrequency);
    }

    public void executeTask(Platform platform, Task task) {
        task.setState(Task.State.RUNNING);
        this.setCoreState(Core.CoreState.PROCESSING);

        ExecuteTask thread = new ExecuteTask(this, task, (long) (task.getWLP() * 1000), platform);
        thread.start();
    }

}
