package com.platform.core;

import com.application.Task;
import com.platform.Platform;

import java.util.ArrayList;

public class ExecuteTask extends Thread {

    private final Core core;
    private final Task task;
    private final long taskTime;
    private final Platform platform;

    public ExecuteTask(Core core, Task task, long taskTime, Platform platform) {
        this.core = core;
        this.task = task;
        this.taskTime = taskTime;
        this.platform = platform;
    }

    public void run() {

        try {
            sleep(taskTime);

            System.out.println("Completed execution of task: " + task.getTaskNo());
            task.setState(Task.State.COMPLETE);
            core.setCoreState(Core.CoreState.IDLE);

            ArrayList<Task> children = task.getChildren();
            for(Task child : children) {
                child.decrementDependencies();
            }

            synchronized (platform) {
                platform.notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
