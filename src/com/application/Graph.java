package com.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    final private ArrayList<Task> nodes = new ArrayList<>();
    final private HashMap<Integer, Task> map = new HashMap<>();

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

    public Task getNode(int taskNo) { return map.get(taskNo); }
}
