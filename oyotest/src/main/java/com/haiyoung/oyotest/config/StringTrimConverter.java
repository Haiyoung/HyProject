package com.haiyoung.oyotest.config; /**
 * @Author: Haiyang
 * @Date: 2018/12/16 下午9:45
 */

import org.springframework.core.convert.converter.Converter;

public class StringTrimConverter implements Converter<String, String> {
    @Override
    public String convert(String source) {
        if (source != null) {
            source = source.trim();
            if (!"".equals(source)) {
                return source;
            }
        }
        return null;
    }
}
