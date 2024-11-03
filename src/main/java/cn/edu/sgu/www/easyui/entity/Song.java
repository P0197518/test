package cn.edu.sgu.www.easyui.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 歌曲表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("song")
public class Song implements Serializable {
    private static final long serialVersionUID = 18L;

    @ExcelProperty("歌曲编号")
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 歌曲名
     */
    @ExcelProperty("歌曲名")
    @ApiModelProperty(value = "歌曲名")
    private String name;

    /**
     * 歌手
     */
    @ExcelProperty("歌手")
    @ApiModelProperty(value = "歌手")
    private String singer;

    /**
     * 描述信息
     */
    @ExcelProperty("描述信息")
    @ApiModelProperty(value = "描述信息")
    private String note;

    /**
     * 最后一次修改时间
     */
    @ExcelIgnore
    @ApiModelProperty(value = "最后一次修改时间")
    @TableField("last_update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastUpdateTime;
}