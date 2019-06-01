package com.haiyoung.tinycode.config.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-01 15:55
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDruid {

    private String url;

    private String userName;

    private String password;

    private String driverClass;

    private Integer maxActive;

    private Integer minIdle;

    private Integer initialSize;

    private Boolean testOnBorrow;

    private Boolean logAbandoned;

    private Boolean removeAbandoned;

    private Integer removeAbandonedTimeout;

    private Integer maxWait;

    private String validationQuery;
}
