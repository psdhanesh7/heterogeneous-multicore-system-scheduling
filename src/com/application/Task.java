package com.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Task {

    public enum State { BLANK, READY, RUNNING, COMPLETE }

    final private  int taskNo;

    final private float wHP;
    final private float wLP;
    final private float alphaHP;
    final private float alphaLP;

    final private ArrayList<Task> children = new ArrayList<>();
    final private HashMap<Integer, Task> map = new HashMap<>();

    volatile private State state = State.BLANK;
    private int dependencies = 0;

    public Task(int taskNo, float wHP, float wLP, float alphaHP, float alphaLP) {
        this.taskNo = taskNo;
        this.wHP = wHP;
        this.wLP = wLP;
        this.alphaHP = alphaHP;
        this.alphaLP = alphaLP;
    }

    public void addChild(Task child) {
        if(!map.containsKey(child.getTaskNo())) {
            children.add(child);
            map.put(child.getTaskNo(), child);
            child.incrementDependencies();
        }
    }

    public void incrementDependencies() { dependencies++; }
    public void decrementDependencies() { dependencies--; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public float getWHP() { return wHP; }
    public float getWLP() { return wLP; }

    public int getTaskNo() { return taskNo; }
    public ArrayList<Task> getChildren() { return children; }
    public int getNumberOfDependencies() { return dependencies; }

    public float getLTFPriority() { return wHP; }
}
