package com.zxf.lib_androidx.model;

import java.io.Serializable;

/**
 * 基础模型类
 * Created by zxf on 2018/9/19.
 */

public class BaseModel implements Serializable{
    private boolean success;
    private String status;
    private String message;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
