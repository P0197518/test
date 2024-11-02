package com.fujias.common.component;

import java.math.BigDecimal;

import org.apache.commons.beanutils.ConversionException;
import org.springframework.stereotype.Component;

/**
 * 日付処理のコンフィグクラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@Component
public class ApacheBigDecimalConverterConfig implements org.apache.commons.beanutils.Converter {

    @Override
    public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
        if (value == null) {
            return null;
        }
        if (type == BigDecimal.class) {

            if (value.getClass() == BigDecimal.class) {
                return (BigDecimal) value;
            } else {
                return null;
            }

        }
        throw new ConversionException(value.getClass().getName() + "不能转换为" + type.getName());
    }

}
