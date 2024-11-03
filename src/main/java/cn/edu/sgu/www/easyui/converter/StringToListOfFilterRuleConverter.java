package cn.edu.sgu.www.easyui.converter;

import cn.edu.sgu.www.easyui.base.FilterRule;
import com.alibaba.fastjson.JSON;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Component
public class StringToListOfFilterRuleConverter implements Converter<String, List<FilterRule>> {

    @Override
    public List<FilterRule> convert(@NonNull String source) {
        return JSON.parseArray(source, FilterRule.class);
    }

}