package cn.edu.sgu.www.easyui.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class SongDTO implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 歌曲编号/ID
     */
    @ApiModelProperty(value = "歌曲编号", required = false)
    private String id;

    /**
     * 歌曲名
     */
    @NotEmpty(message = "歌曲名不能为空")
    @ApiModelProperty(value = "歌曲名", required = true)
    private String name;

    /**
     * 歌手
     */
    @NotEmpty(message = "歌手不能为空")
    @ApiModelProperty(value = "歌手", required = true)
    private String singer;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息", required = false)
    private String note;
}