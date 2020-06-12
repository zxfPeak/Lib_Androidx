package com.zxf.lib_androidx.asynctask;

/**
 * Created by zxf on 2018/9/18.
 */

public class TaskItem {
    
    private int position;
    private TaskListener listener;
    
    public TaskItem () {
    }
    
    public TaskItem (TaskListener listener) {
        this.listener = listener;
    }
    
    public int getPosition () {
        return this.position;
    }
    
    public void setPosition (int position) {
        this.position = position;
    }
    
    public TaskListener getListener () {
        return this.listener;
    }
    
    public void setListener (TaskListener listener) {
        this.listener = listener;
    }
}
