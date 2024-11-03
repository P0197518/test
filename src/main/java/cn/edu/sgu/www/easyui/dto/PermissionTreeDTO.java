package cn.edu.sgu.www.easyui.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class PermissionTreeDTO implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 角色ID
     */
    @NotNull(message = "角色不允许为空~")
    @ApiModelProperty(value = "用户ID", required = true)
    private Integer roleId;

    /**
     * 待添加权限
     */
    @ApiModelProperty(value = "待添加权限", required = false)
    private List<String> insertIds;

    /**
     * 待删除权限
     */
    @ApiModelProperty(value = "待删除权限", required = false)
    private List<String> deleteIds;
}