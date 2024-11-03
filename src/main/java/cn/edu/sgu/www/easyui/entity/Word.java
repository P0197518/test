package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 词语表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("word")
public class Word implements Serializable {
    private static final long serialVersionUID = 18L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 词语
     */
    @ApiModelProperty(value = "词语")
    private String name;

    /**
     * 词语类型
     */
    @ApiModelProperty(value = "词语类型")
    private Integer type;

    /**
     * 含义
     */
    @ApiModelProperty(value = "含义")
    private String note;
}
