package com.platform.core;

import com.application.Task;
import com.platform.Platform;

public class HighPerformanceCore extends Core {

    public HighPerformanceCore(float maxFrequency) {
        super(maxFrequency);
    }

    public void executeTask(Platform platform, Task task) {
        ExecuteTask thread = new ExecuteTask(this, task, (long) task.getwHP() * 1000, platform);
        thread.start();
    }
}
