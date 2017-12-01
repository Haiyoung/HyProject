package com.haiyoung.hyweb.biz;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Haiyoung on 2017/11/30.
 */
public class BizModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
