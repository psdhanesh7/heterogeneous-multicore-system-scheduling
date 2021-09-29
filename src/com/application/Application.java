package com.application;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    final private int taskCount;
    final private int dependencyCount;
    final private ArrayList<Task> taskList = new ArrayList<>();
    final private Graph dependencyGraph = new Graph();

    public Application(int taskCount, int dependencyCount) {
        this.taskCount = taskCount;
        addTaskDetails();

        this.dependencyCount = dependencyCount;
        buildDependencyGraph();
    }

    private void addTaskDetails() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the task matrix: ");
        for(int i = 0; i < taskCount; i++) {
            float wHP = input.nextFloat();
            float wLP = input.nextFloat();
            float alphaHP = input.nextFloat();
            float alphaLP = input.nextFloat();

            taskList.add(new Task(i, wHP, wLP, alphaHP, alphaLP));
        }
    }

    private void buildDependencyGraph() {
        Scanner input = new Scanner(System.in);

        for(Task task : taskList) {
            dependencyGraph.createNode(task);
        }

        System.out.println("Enter the dependencies:");
        for(int i = 0; i < dependencyCount; i++) {
            int first = input.nextInt();
            int second = input.nextInt();

            dependencyGraph.addEdge(first, second);
        }
    }

    public int getTaskCount() { return taskCount; }

    public ArrayList<Task> getTaskList() { return taskList; }
}


