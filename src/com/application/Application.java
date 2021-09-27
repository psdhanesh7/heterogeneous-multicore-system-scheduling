package com.application;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private int taskCount;
    private int dependencyCount;
    private ArrayList<Task> taskList;
    private Graph dependencyGraph;

    public Application(int taskCount, int dependencyCount) {
        this.taskCount = taskCount;
        this.taskList = new ArrayList<>();
        this.addTaskDetails();

        this.dependencyCount = dependencyCount;
        dependencyGraph = buildDependencyGraph();
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

    private Graph buildDependencyGraph() {
        Scanner input = new Scanner(System.in);

        Graph dependencyGraph = new Graph();
        for(Task task : taskList) {
            dependencyGraph.createNode(task);
        }

        System.out.println("Enter the dependencies:");
        for(int i = 0; i < dependencyCount; i++) {
            int first = input.nextInt();
            int second = input.nextInt();

            dependencyGraph.addEdge(first, second);
        }

        return dependencyGraph;
    }

    public int getTaskCount() { return taskCount; }

    public ArrayList<Task> getTaskList() { return taskList; }
}


