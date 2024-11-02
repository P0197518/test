package cn.afterturn.easypoi.excel.entity.params;

import java.io.Serializable;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;

import cn.afterturn.easypoi.excel.entity.enmus.CellValueType;
import lombok.Data;

/**
 * 模板for each是的参数
 *
 * @author JueYue 2015年4月29日 下午9:22:48
 */
@Data
public class ExcelForEachParams implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * key
     */
    private String name;
    /**
     * key
     */
    private Stack<String> tempName;
    /**
     * 模板的cellStyle
     */
    private CellStyle cellStyle;
    /**
     * 行高
     */
    private short height;
    /**
     * 列宽(仅横向遍历支持)
     */
    private int width;
    /**
     * 常量值
     */
    private String constValue;

    /**
     * 单元格值类型
     */
    private CellValueType cellValueType;

    /**
     * 列合并
     */
    private int colspan = 1;
    /**
     * 行合并
     */
    private int rowspan = 1;
    /**
     * 行合并
     */
    private boolean collectCell;
    /**
     * 需要统计
     */
    private boolean needSum;

    public ExcelForEachParams() {

    }

    public ExcelForEachParams(String name, CellStyle cellStyle, short height, CellValueType cellValueType) {
        this.name = name;
        this.cellStyle = cellStyle;
        this.height = height;
        this.cellValueType = cellValueType;
    }

    public ExcelForEachParams(String name, CellStyle cellStyle, short height, boolean needSum) {
        this.name = name;
        this.cellStyle = cellStyle;
        this.height = height;
        this.needSum = needSum;
    }

    public void addConstValue(int num) {
        if (StringUtils.isNotEmpty(constValue)) {
            this.constValue = Integer.parseInt(constValue) + 1 + "";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stack<String> getTempName() {
        return tempName;
    }

    public void setTempName(Stack<String> tempName) {
        this.tempName = tempName;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getConstValue() {
        return constValue;
    }

    public void setConstValue(String constValue) {
        this.constValue = constValue;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public boolean isCollectCell() {
        return collectCell;
    }

    public void setCollectCell(boolean collectCell) {
        this.collectCell = collectCell;
    }

    public boolean isNeedSum() {
        return needSum;
    }

    public void setNeedSum(boolean needSum) {
        this.needSum = needSum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
