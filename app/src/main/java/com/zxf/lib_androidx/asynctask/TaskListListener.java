package com.zxf.lib_androidx.asynctask;

import java.util.List;

/**
 * Created by zxf on 2018/9/18.
 */

public abstract class TaskListListener extends TaskListener {
    public TaskListListener () {
    }
    
    public abstract List<?> getList ();
    
    public abstract void update (List<?> var1);
}
