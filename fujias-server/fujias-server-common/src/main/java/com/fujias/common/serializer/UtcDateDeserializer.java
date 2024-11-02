package com.fujias.common.serializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fujias.common.util.DateUtil;
import com.fujias.common.util.ExceptionUtil;

/**
 * DateDeserializerを定義する
 * 
 * @version 1.0
 * @author 陳強
 */
public class UtcDateDeserializer extends DateDeserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtcDateDeserializer.class);

    private static final long serialVersionUID = -6218693745160760598L;

    @Override
    protected Date _parseDate(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Date parseDate = null;
        String dateStr = jp.getText();
        try {

            parseDate = DateUtil.formatStringToDateAllFormat(dateStr);
        } catch (ParseException e) {
            LOGGER.error(ExceptionUtil.getMessage(e));
        }
        return parseDate;
    }
}
