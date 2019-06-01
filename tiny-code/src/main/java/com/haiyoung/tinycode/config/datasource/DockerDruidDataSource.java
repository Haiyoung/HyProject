package com.haiyoung.tinycode.config.datasource;

import com.haiyoung.tinycode.config.base.BaseDruid;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-01 16:04
 * @Version 1.0
 */

@Data
@ConfigurationProperties(prefix = "spring.datasource.docker")
public class DockerDruidDataSource extends BaseDruid {

}
