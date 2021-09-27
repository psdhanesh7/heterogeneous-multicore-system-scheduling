package com.platform.core;

import com.application.Task;
import com.platform.Platform;

public class LowPerformanceCore extends Core {

    public LowPerformanceCore(float maxFrequency) {
        super(maxFrequency);
    }

    public void executeTask(Platform platform, Task task) {
        ExecuteTask thread = new ExecuteTask(this, task, (long) task.getwLP() * 1000, platform);
        thread.start();
    }

}