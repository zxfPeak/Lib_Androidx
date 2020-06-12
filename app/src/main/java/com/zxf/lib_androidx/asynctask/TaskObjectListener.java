package com.zxf.lib_androidx.asynctask;

/**
 * Created by zxf on 2018/9/18.
 */

public abstract class TaskObjectListener extends TaskListener {
    public TaskObjectListener () {
    }
    
    public abstract <T> T getObject ();
    
    public abstract <T> void update (T var1);
}
