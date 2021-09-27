package com.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Task {

    public enum State { BLANK, READY, RUNNING, COMPLETE };

    final private  int taskNo;

    final private float wHP;
    final private float wLP;
    final private float alphaHP;
    final private float alphaLP;

    volatile private State state;
    private ArrayList<Task> children = null;
    private HashMap<Integer, Task> map = null;
    private int dependencies;

    public Task(int taskNo, float wHP, float wLP, float alphaHP, float alphaLP) {
        this.taskNo = taskNo;
        this.wHP = wHP;
        this.wLP = wLP;
        this.alphaHP = alphaHP;
        this.alphaLP = alphaLP;

        state = State.BLANK;
        children = new ArrayList<Task>();
        map = new HashMap<Integer, Task>();
        dependencies = 0;
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

    public float getwHP() { return wHP; }
    public float getwLP() { return wLP; }

    public int getTaskNo() { return taskNo; }
    public ArrayList<Task> getChildren() { return children; }
    public int getNumberOfDependencies() { return dependencies; }

    public float getLTFPriority() { return wHP; }
}
