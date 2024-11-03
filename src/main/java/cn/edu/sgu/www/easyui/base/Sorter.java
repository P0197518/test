package cn.edu.sgu.www.easyui.base;

import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.util.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础排序对象，包含排序字段和排序方式
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class Sorter implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 空字符串
     */
    private static final String EMPTY_STR = "";

    /**
     * 分割符
     */
    private static final String SEPARATOR = ",";

    /**
     * 排序方式
     */
    private static final List<String> ORDER_STYLES = new ArrayList<>(2);

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private String sort;

    /**
     * 排序方式：asc/desc
     */
    @ApiModelProperty(value = "排序方式")
    private String order;

    static {
        ORDER_STYLES.add("asc");
        ORDER_STYLES.add("desc");
    }

    /**
     * 根据查询条件拼接得到order by语句
     * @param sorter 分页查询条件
     * @return String
     */
    public static String getStatement(Sorter sorter) {
        String sort;
        String sortColumn = sorter.getSort();

        // 处理排序字段
        String[] sortArray = {};

        if (StringUtils.isNotEmpty(sortColumn)) {
            // 驼峰命名转为下划线
            sort = StringUtils.toLower(sortColumn);

            if (sort.contains(SEPARATOR)) {
                sortArray = sort.split(SEPARATOR);
            }
        } else {
            return EMPTY_STR;
        }

        // 处理排序方式
        String[] orderArray = {};
        String order = sorter.getOrder();

        if (StringUtils.isNotEmpty(order)) {
            if (order.contains(SEPARATOR)) {
                orderArray = order.split(SEPARATOR);
            }
        } else {
            return EMPTY_STR;
        }

        StringBuilder statement = new StringBuilder();

        if (sortArray.length > 0 && orderArray.length > 0) {
            int length = sortArray.length;

            for (int i = 0; i < length; i++) {
                String pagerSort = sortArray[i];
                String pagerOrder = orderArray[i];

                boolean result = validate(pagerSort, pagerOrder);

                if (result) {
                    statement.append(pagerSort);
                    statement.append(" ");
                    statement.append(pagerOrder);

                    if (i < length - 1 ) {
                        statement.append(", ");
                    }
                }
            }
        } else {
            // " #{sort} #{order}“
            statement.append(sort);
            statement.append(" ");
            statement.append(order);
        }

        return statement.toString();
    }

    /**
     * 根据查询条件拼接得到order by语句
     * @param sorter 分页查询条件
     * @return String
     */
    public static String getOrderByStatement(Sorter sorter) {
        String statement = getStatement(sorter);

        if (StringUtils.isNotEmpty(statement)) {
            return " order by " + statement;
        } else {
            return EMPTY_STR;
        }
    }

    /**
     * 往Pager的排序字段中添加排序
     * @param pager Pager Pager对象
     * @param sort String 排序字段
     * @param order String 排序方式
     * @return Pager<?> 返回重新设置排序字段和排序方式后的Pager对象
     */
    public static Pager<?> append(Pager<?> pager, String sort, String order) {
        boolean result = validatePager(pager);

        if (result) {
            String pagerSort = pager.getSort();
            String pagerOrder = pager.getOrder();

            pager.setSort(pagerSort.concat(SEPARATOR).concat(sort));
            pager.setOrder(pagerOrder.concat(SEPARATOR).concat(order));

            return pager;
        }

        return null;
    }

    /**
     * 在Pager的排序字段最前面添加排序
     * @param pager Pager Pager对象
     * @param sort 排序字段
     * @param order 排序方式
     * @return Pager<?> 返回重新设置排序字段和排序方式后的Pager对象
     */
    public static Pager<?> appendFirst(Pager<?> pager, String sort, String order) {
        String pagerSort = pager.getSort();
        String pagerOrder = pager.getOrder();

        if (StringUtils.isNotEmpty(pagerSort) && StringUtils.isNotEmpty(pagerOrder)) {
            pager.setSort(sort.concat(SEPARATOR).concat(pagerSort));
            pager.setOrder(order.concat(SEPARATOR).concat(pagerOrder));
        } else if (StringUtils.isEmpty(pagerSort) && StringUtils.isEmpty(pagerOrder)) {
            pager.setSort(sort);
            pager.setOrder(order);
        } else {
            throw new GlobalException(ResponseCode.FORBIDDEN, "数据格式异常：字段sort和order的值必须同为空/非空字符！");
        }

        return pager;
    }

    /**
     * 验证Pager对象的sort和order的值是否合法
     * @param pager Pager<?>
     * @return boolean
     */
    private static boolean validatePager(Pager<?> pager) {
        String sort = pager.getSort();
        String order = pager.getOrder();

        return validate(sort, order);
    }

    /**
     * 验证sort和order的值是否合法
     * @param sort 排序字段
     * @param order 排序方式
     * @return boolean
     */
    private static boolean validate(String sort, String order) {
        if (StringUtils.isEmpty(sort)) {
            throw new GlobalException(ResponseCode.FORBIDDEN, "排序字段不允许为空！");
        } else if (StringUtils.isEmpty(order)) {
            throw new GlobalException(ResponseCode.FORBIDDEN, "排序方式不允许为空！");
        } else if(!ORDER_STYLES.contains(order.toLowerCase())) {
            throw new GlobalException(ResponseCode.FORBIDDEN, "排序方式不合法！");
        }

        return true;
    }

}