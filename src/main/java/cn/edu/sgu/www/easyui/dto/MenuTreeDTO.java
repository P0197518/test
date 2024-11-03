package cn.edu.sgu.www.easyui.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class MenuTreeDTO implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 设置隐藏的菜单
     */
    @ApiModelProperty(value = "设置隐藏的菜单")
    private List<String> hideIds;

    /**
     * 设置显示的菜单
     */
    @ApiModelProperty(value = "设置显示的菜单")
    private List<String> displayIds;
}