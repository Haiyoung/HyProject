package com.haiyoung.tinycode.easyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.util.StringUtils;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-12 15:23
 * @Version 1.0
 */
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private static final  String sql = "\tinsert into eff_personal_project_manager(project_id,project_name,account_id, account_name, employee_id,email)  \n" +
            "\tvalues('%s','%s',0,'%s','%s','%s');";

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        if(null != demoData){
            if(!StringUtils.isEmpty(demoData.getEmployeeId())){
                System.out.println(String.format(sql, demoData.getProductId(), demoData.getProductName(),
                        demoData.getAccountName(),demoData.getEmployeeId(), demoData.getEmail()));
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
