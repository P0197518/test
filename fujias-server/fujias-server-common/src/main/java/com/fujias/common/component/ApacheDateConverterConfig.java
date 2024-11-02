package com.fujias.common.component;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fujias.common.util.DateUtil;
import com.fujias.common.util.ExceptionUtil;

/**
 * 日付処理のコンフィグクラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@Component
public class ApacheDateConverterConfig implements org.apache.commons.beanutils.Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheDateConverterConfig.class);

    @Override
    public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
        if (value == null) {
            return null;
        }
        if (type == Date.class) {
            try {
                if (value.getClass() == Date.class) {
                    return (Date) value;
                } else {
                    return DateUtil.formatStringToDateAllFormat(value.toString());
                }

            } catch (ParseException e) {
                LOGGER.error(ExceptionUtil.getMessage(e));
                return null;
            }
        }
        throw new ConversionException(value.getClass().getName() + "不能转换为" + type.getName());
    }

}
