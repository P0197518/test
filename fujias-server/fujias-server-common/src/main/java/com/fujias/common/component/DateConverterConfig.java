package com.fujias.common.component;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fujias.common.serializer.UtcDateDeserializer;
import com.fujias.common.util.DateUtil;
import com.fujias.common.util.ExceptionUtil;

/**
 * 日付処理のコンフィグクラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@Component
public class DateConverterConfig implements Converter<String, Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtcDateDeserializer.class);

    @Override
    public Date convert(String source) {
        try {
            return DateUtil.formatStringToDateAllFormat(source);
        } catch (ParseException e) {
            LOGGER.error(ExceptionUtil.getMessage(e));
            return null;
        }
    }

}
