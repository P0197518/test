package cn.edu.sgu.www.easyui.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 过滤规则
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class FilterRule implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 字段名
     */
    @ApiModelProperty(value = "字段名")
    private String field;

    /**
     * 比较符
     */
    @ApiModelProperty(value = "比较符")
    private Operator op;

    /**
     * 字段值
     */
    @ApiModelProperty(value = "字段值")
    private String value;
}