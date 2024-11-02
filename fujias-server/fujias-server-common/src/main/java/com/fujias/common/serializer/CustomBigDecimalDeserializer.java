package com.fujias.common.serializer;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fujias.common.util.DecimalFormatUtil;

import io.netty.util.internal.StringUtil;

/**
 * DateDeserializerを定義する
 * 
 * @version 1.0
 * @author 陳強
 */
@JsonComponent
public class CustomBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        switch (p.getCurrentTokenId()) {
        case JsonTokenId.ID_NUMBER_INT:
        case JsonTokenId.ID_NUMBER_FLOAT:
            return p.getDecimalValue();
        case JsonTokenId.ID_STRING:
            String text = p.getText().trim();
            // note: no need to call `coerce` as this is never primitive
            if (StringUtil.isNullOrEmpty(text) || "null".equals(text)) {
                return null;
            }
            return DecimalFormatUtil.parseDecimal(text);
        default:
            return null;
        }
    }
}
