package com.haiyoung.tinycode.mapper.local;

import com.haiyoung.tinycode.bean.po.OrderPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderPOMapper {

    int deleteByPrimaryKey(Long orderId);

    int insert(OrderPO record);

    int insertSelective(OrderPO record);

    OrderPO selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(OrderPO record);

    int updateByPrimaryKey(OrderPO record);
}