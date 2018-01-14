package com.haiyoung.hyweb.biz.lock;

import java.io.Serializable;

public class LockInfo implements Serializable{

    private static final long serialVersionUID = -1961553889495496772L;

    private String lockId;

    private boolean isNew;

    public LockInfo(String lockId, boolean isNew) {
        this.lockId = lockId;
        this.isNew = isNew;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}
