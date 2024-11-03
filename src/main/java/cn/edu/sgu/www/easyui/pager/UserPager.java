package cn.edu.sgu.www.easyui.pager;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author heyunlin
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class UserPager extends Pager<User> implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
}