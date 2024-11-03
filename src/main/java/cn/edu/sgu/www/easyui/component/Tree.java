package cn.edu.sgu.www.easyui.component;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * easyui树对象
 * @author heyunlin
 * @version 1.0
 */
/*
树的数据格式
    每个节点可以包括下列属性：
        id：节点的 id，它对于加载远程数据很重要。
        text：要显示的节点文本。
        state：节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
        checked：指示节点是否被选中。
        attributes：给一个节点添加的自定义属性。
        children：定义了一些子节点的节点数组。
*/
@Data
public class Tree implements Serializable {
    private static final long serialVersionUID = 18L;

    private String id;

    /**
     * 节点名称
     */
    private String text;

    /**
     * 树节点的展开状态open/closed
     */
    private String state = "open";

    /**
     * 是否被选中
     */
    private boolean checked;

    /**
     * 子树
     */
    private List<Tree> children;

    /**
     * 排序号
     */
    private Integer pxh;
}