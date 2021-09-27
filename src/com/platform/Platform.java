package com.platform;

import com.application.Application;
import com.application.Task;
import com.platform.core.Core;
import com.platform.core.HighPerformanceCore;
import com.platform.core.LowPerformanceCore;

import java.util.ArrayList;

public class Platform {

    private final HighPerformanceCore highPerformanceCore;
    private final LowPerformanceCore lowPerformanceCore;

    public Platform(float frequencyHPMax, float frequencyLPMax) {
        highPerformanceCore = new HighPerformanceCore(frequencyHPMax);
        lowPerformanceCore = new LowPerformanceCore(frequencyLPMax);
    }

    public void runApplication(Application app) {
        Task[] order = new Task[app.getTaskCount()];

        int endOfList = addNonDependent(order, app.getTaskList(), 0);

        int toBeProcessed = 0;
        while(toBeProcessed < order.length) {
            Task current = getMaxPriorityTask(order, endOfList);

            // circular dependency case
            if(current == null) {
//                System.out.println("Circular dependency in dependency graph");
//                break;
                synchronized (this) {
                    try {
                        this.wait();
                        endOfList = addNonDependent(order, app.getTaskList(), endOfList);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(!highPerformanceCore.isIdle() && !lowPerformanceCore.isIdle()) {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(lowPerformanceCore.isIdle()) {
                current.setState(Task.State.RUNNING);
                lowPerformanceCore.setCoreState(Core.CoreState.PROCESSING);
                lowPerformanceCore.executeTask(this, current);
                System.out.println("Task " + current.getTaskNo() + " allocated to LP");
            }
            else {
                current.setState(Task.State.RUNNING);
                highPerformanceCore.setCoreState(Core.CoreState.PROCESSING);
                highPerformanceCore.executeTask(this, current);
                System.out.println("Task " + current.getTaskNo() + " allocated to HP");
            }

            // Remove myself as a dependency
//            ArrayList<Task> children = current.getChildren();
//            for(Task child : children) {
//                child.decrementDependencies();
//            }

            // Add children that have no dependencies
            endOfList = addNonDependent(order, app.getTaskList(), endOfList);
            toBeProcessed++;
        }
    }

    // A helper function to insert projects with zero dependencies into the order array, starting at index offset
    private int addNonDependent(Task[] order, ArrayList<Task> tasks, int offset) {
        for(Task task : tasks) {
            if(task.getState() == Task.State.BLANK && task.getNumberOfDependencies() == 0) {
                task.setState(Task.State.READY);
                order[offset] = task;
                offset++;
            }
        }
        return offset;
    }

    private Task getMaxPriorityTask(Task[] tasks, int n) {
        Task maxPriorityTask = null;
        float maxPriority = -1;
        for(int i = 0; i < n; i++) {
            if(tasks[i].getState() == Task.State.READY && tasks[i].getLTFPriority() > maxPriority) {
                maxPriorityTask = tasks[i];
                maxPriority = tasks[i].getLTFPriority();
            }
        }

        return maxPriorityTask;
    }
}
