package com.haiyoung.lbtest;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-01-27 14:39
 * @Version 1.0
 */
@NoAutoStart
public class SelfDefineTimeBasedFileNamingAndTriggeringPolicy extends DefaultTimeBasedFileNamingAndTriggeringPolicy {

    private Integer multiple = 1;

    @Override
    protected void computeNextCheck() {
        nextCheck = rc.getEndOfNextNthPeriod(dateInCurrentPeriod, multiple).getTime();
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        if (multiple > 1) {
            this.multiple = multiple;
        }
    }

}
