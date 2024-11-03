package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 词语类型表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("word_type")
public class WordType implements Serializable {
    private static final long serialVersionUID = 18L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
    private String name;
}
