package com.haiyoung.hyweb.beanCopier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-03-22 17:53
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeanA {

    private String name;

    private List<ClassA> aList;
}
