package cn.edu.sgu.www.easyui.component;

import cn.edu.sgu.www.easyui.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * easyui树形表格对象
 * @author heyunlin
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTreeGrid extends Menu implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 折叠状态
     */
    private String state;

    /**
     * 子树表格
     */
    private List<MenuTreeGrid> children;
}