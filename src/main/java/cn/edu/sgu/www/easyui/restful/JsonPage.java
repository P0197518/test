package cn.edu.sgu.www.easyui.restful;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 根据easyui datagrid数据格式封装得到的实体类对象
 * @param <T> 查询结果的数据类型
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class JsonPage<T> implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long total;

    /**
     * 查询结果
     */
    @ApiModelProperty(value = "查询结果")
    private List<T> rows;

    /**
     * 页脚数据
     */
    @ApiModelProperty(value = "页脚数据")
    private T footer;

    public static <T> JsonPage<T> restPage(Page<T> page) {
        JsonPage<T> jsonPage = new JsonPage<>();

        jsonPage.setTotal(page.getTotal());
        jsonPage.setRows(page.getRecords());

        return jsonPage;
    }

}
