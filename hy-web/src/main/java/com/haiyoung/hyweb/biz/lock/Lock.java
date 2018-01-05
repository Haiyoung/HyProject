package com.haiyoung.hyweb.biz.lock;

import com.haiyoung.hyweb.biz.BizModel;

import java.util.Date;

public class Lock extends BizModel{

    private static final long serialVersionUID = 5682936369608308452L;

    private String id;

    private String lockThread;

    private String lockType;

    private String lockObject;

    private Date lockDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLockThread() {
        return lockThread;
    }

    public void setLockThread(String lockThread) {
        this.lockThread = lockThread;
    }

    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    public String getLockObject() {
        return lockObject;
    }

    public void setLockObject(String lockObject) {
        this.lockObject = lockObject;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }
}
