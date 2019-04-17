package com.haiyoung.hyweb.beanCopier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-03-22 18:58
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeanB {

    private String name;

    private List<ClassB> aList;
}
