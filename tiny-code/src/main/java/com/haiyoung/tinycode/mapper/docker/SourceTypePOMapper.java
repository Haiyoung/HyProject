package com.haiyoung.tinycode.mapper.docker;

import com.haiyoung.tinycode.bean.po.SourceTypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-02 17:33
 * @Version 1.0
 */
@Mapper
public interface SourceTypePOMapper {

    SourceTypePO selectByPrimaryKey(String id);

    int insertSourceType(SourceTypePO sourceTypePO);

}
