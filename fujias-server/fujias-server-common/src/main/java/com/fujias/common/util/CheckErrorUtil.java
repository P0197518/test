package com.fujias.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fujias.common.constants.MessageCodes;
import com.fujias.common.entity.CheckMessage;
import com.fujias.common.validation.IsValidateCollection;

/**
 * FormDataのチェック共通処理クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public final class CheckErrorUtil {

    private static final Logger LORRER = LoggerFactory.getLogger(CheckErrorUtil.class);

    private CheckErrorUtil() {

    }

    /**
     * アノテーション以外の場合、Formデータを検証する
     * 
     * @param formData Formデータ
     * @return チェックエラー
     * @throws Exception Exception
     */
    public static List<CheckMessage> validateForm(Object... formData) {
        return validateForm(null, formData);
    }

    /**
     * アノテーション以外の場合、Formデータを検証する
     * 
     * @param row row
     * @param formData Formデータ
     * @return チェックエラー
     * @throws Exception Exception
     */
    public static List<CheckMessage> validateForm(Integer row, Object... formData) {
        List<CheckMessage> checkMessages = new ArrayList<CheckMessage>();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        for (Object form : formData) {
            runValidateForm(form, validator, row, checkMessages);
        }
        return checkMessages;
    }

    /**
     * 执行form的check
     * 
     * @param formData form数据
     * @param validator 验证类
     * @param checkMessages 返回check消息
     * @throws Exception Exception
     */
    private static void runValidateForm(Object formData, Validator validator, Integer row, List<CheckMessage> checkMessages) {

        // 获取检查错误列表
        Set<ConstraintViolation<Object>> set = validator.validate(formData);
        for (ConstraintViolation<Object> constraintViolation : set) {
            // 根据返回的检查错误，生成checkmessage
            CheckMessage message = new CheckMessage();
            message.setField(constraintViolation.getPropertyPath().toString());
            message.setCode(constraintViolation.getMessage());
            message.setRow(String.valueOf(row));
            String messageCode = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
            if (messageCode != null && messageCode.startsWith("Validate")) {
                List<String> messageArgs = ReflectionUtil.getMessageArgsValue(formData.getClass(), message.getField(), messageCode);

                message.setArgs(messageArgs);
            } else {
                message.setArgs(new ArrayList<String>());
            }
            checkMessages.add(message);
        }

        // 判断form中的字段是否有需要验证的子list
        Field[] fields = formData.getClass().getDeclaredFields();
        for (Field fieldItem : fields) {
            // 判断是不是集合类
            if (!Collection.class.isAssignableFrom(fieldItem.getType())) {
                continue;
            }
            // 判断是否需要验证子list
            if (!fieldItem.isAnnotationPresent(IsValidateCollection.class)) {
                continue;
            }
            Object fieldData = ReflectionUtil.getFieldValue(formData, fieldItem.getName());
            if (fieldData == null) {
                continue;
            }
            Collection<?> fieldDataCol = (Collection<?>) fieldData;
            Iterator<?> fieldDataIterator = fieldDataCol.iterator();
            while (fieldDataIterator.hasNext()) {
                Object listItem = fieldDataIterator.next();
                try {
                    // 递归调用
                    runValidateForm(listItem, validator, row, checkMessages);

                } catch (IllegalArgumentException | SecurityException e) {
                    LORRER.error(ExceptionUtil.getMessage(e));
                    CheckMessage message = new CheckMessage();
                    message.setField(listItem.getClass().getName());
                    message.setCode(MessageCodes.EC0094);
                    message.setRow(String.valueOf(row));
                }
            }
        }
    }
}
