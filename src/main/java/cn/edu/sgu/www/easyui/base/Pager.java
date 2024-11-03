package cn.edu.sgu.www.easyui.base;

import cn.edu.sgu.www.easyui.util.CollectionUtils;
import cn.edu.sgu.www.easyui.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 基础分页参数对象，包含页数和每页的记录数
 * @author heyunlin
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class Pager<T> extends Sorter implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer rows = 10;

    /**
     * 过滤规则
     */
    @ApiModelProperty(value = "过滤规则")
    private List<FilterRule> filterRules;

    /**
     * 根据Pager创建Page对象
     * @param pager Pager
     * @return Page
     */
    public static <T> Page<T> ofPage(Pager<T> pager) {
        return new Page<>(pager.getPage(), pager.getRows());
    }

    /**
     * 根据Pager创建QueryWrapper对象
     * @param pager Pager
     * @return QueryWrapper<T>
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Pager<T> pager) {
        return getQueryWrapper(pager, true);
    }

    /**
     * 根据Pager创建QueryWrapper对象
     * @param pager Pager
     * @return QueryWrapper<T>
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Pager<T> pager, boolean enableSort) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();

        List<FilterRule> filterRules = pager.getFilterRules();

        if (CollectionUtils.isNotEmpty(filterRules)) {
            for (FilterRule filterRule : filterRules) {
                // 字段名：转为小写字母+下划线的格式
                String field = StringUtils.toLower(filterRule.getField());
                // 字段值
                String value = filterRule.getValue();

                if (StringUtils.isNotEmpty(value)) {
                    switch (filterRule.getOp()) {
                        case less:
                            wrapper.lt(field, value);
                            break;
                        case equal:
                            wrapper.eq(field, value);
                            break;
                        case greater:
                            wrapper.gt(field, value);
                            break;
                        case notequal:
                            wrapper.ne(field, value);
                            break;
                        case lessorequal:
                            wrapper.le(field, value);
                            break;
                        case greaterorequal:
                            wrapper.ge(field, value);
                            break;
                        case beginwith:
                            wrapper.likeLeft(field, value);
                            break;
                        case endwith:
                            wrapper.likeRight(field, value);
                            break;
                        case contains:
                            wrapper.like(field, value);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        if (enableSort) {
            // 得到order by语句
            String statement = getOrderByStatement(pager);

            if (StringUtils.isNotEmpty(statement)) {
                wrapper.last(statement);
            }
        }

        return wrapper;
    }

}