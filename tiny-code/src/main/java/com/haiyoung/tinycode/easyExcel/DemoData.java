package com.haiyoung.tinycode.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-12 15:26
 * @Version 1.0
 */

@Data
public class DemoData {

    @ExcelProperty(index = 6)
    private String employeeId;

    @ExcelProperty(index = 7)
    private String email;

    @ExcelProperty(index = 8)
    private String productId;

    @ExcelProperty(index = 9)
    private String productName;

    @ExcelProperty(index = 5)
    private String accountName;

}
