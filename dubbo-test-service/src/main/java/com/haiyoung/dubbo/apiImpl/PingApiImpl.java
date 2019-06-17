package com.haiyoung.dubbo.apiImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.haiyoung.dubbo.facade.api.PingApi;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-16 17:28
 * @Version 1.0
 */
@Service(version = "1.0.0")
public class PingApiImpl implements PingApi {

    @Override
    public String ping() {
        return null;
    }
}
