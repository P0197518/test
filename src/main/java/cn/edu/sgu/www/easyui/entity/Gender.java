package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 性别表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("gender")
public class Gender implements Serializable {
    private static final long serialVersionUID = 18L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

    /**
     * 性别名称
     */
    @ApiModelProperty(value = "性别名称", required = true)
    private String name;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片", required = true)
    private String image;
}
