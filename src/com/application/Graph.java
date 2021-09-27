package com.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private ArrayList<Task> nodes = new ArrayList<Task>();
    private HashMap<Integer, Task> map = new HashMap<Integer, Task>();

    public void createNode(Task task) {
        int taskNo = task.getTaskNo();
        if(map.containsKey(taskNo)) return;

        nodes.add(task);
        map.put(taskNo, task);
    }

    public void addEdge(int startTaskNo, int endTaskNo) {
        Task start = getNode(startTaskNo);
        Task end = getNode(endTaskNo);

        start.addChild(end);
    }

    public ArrayList<Task> getNodes() { return nodes; }
    public Task getNode(int taskNo) { return map.get(taskNo); }
}
