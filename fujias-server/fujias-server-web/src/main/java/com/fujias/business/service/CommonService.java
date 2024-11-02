package com.fujias.business.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fujias.business.common.dao.CommonDaoMapper;
import com.fujias.business.common.entity.CodeEntity;
import com.fujias.business.common.form.CommonCodeForm;
import com.fujias.business.common.form.CommonRateForm;
import com.fujias.business.common.form.CommonTaxForm;
import com.fujias.business.constants.CodeTypes;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.util.DecimalFormatUtil;

import io.netty.util.internal.StringUtil;

/**
 * 共通处理service类
 * 
 * @author chenqiang
 *
 */
@Service
public class CommonService {
    @Autowired
    private CommonDaoMapper commonDaoMapper;

    /**
     * 获取最新汇率
     * 
     * @return 最新汇率
     */
    public CommonRateForm getCurrentRate() {
        CodeEntity codeParam = new CodeEntity();
        codeParam.setCodeTypeCd(CodeTypes.日元汇率.getCode());
        List<SelectItem> list = commonDaoMapper.getCodeList(codeParam);
        CommonRateForm rateInfo = new CommonRateForm();
        if (list.size() > 0) {
            SelectItem newData = list.get(list.size() - 1);

            rateInfo.setRateId(newData.getValue());
            rateInfo.setRateName(newData.getText());
            if (StringUtil.isNullOrEmpty(newData.getOption1())) {
                rateInfo.setRateValue(BigDecimal.ONE);
            } else {
                rateInfo.setRateValue(DecimalFormatUtil.parseDecimal(newData.getOption1()));
            }

        } else {
            rateInfo.setRateValue(BigDecimal.ONE);
        }

        return rateInfo;
    }

    /**
     * 获取最新汇率
     * 
     * @return 最新汇率
     */
    public CommonTaxForm getCurrentTax() {
        CodeEntity codeParam = new CodeEntity();
        codeParam.setCodeTypeCd(CodeTypes.税率区分.getCode());
        List<SelectItem> list = commonDaoMapper.getCodeList(codeParam);
        CommonTaxForm rateInfo = new CommonTaxForm();
        if (list.size() > 0) {
            SelectItem newData = list.get(0);

            rateInfo.setTaxId(newData.getValue());
            rateInfo.setTaxName(newData.getText());
            if (StringUtil.isNullOrEmpty(newData.getOption1())) {
                rateInfo.setTaxValue(BigDecimal.ONE);
            } else {
                rateInfo.setTaxValue(BigDecimal.ONE.add(DecimalFormatUtil.parseDecimal(newData.getOption1())));
            }

        } else {
            rateInfo.setTaxValue(BigDecimal.ONE);
        }

        return rateInfo;
    }

    /**
     * 采番
     * 
     * @param  projectID 采番项目（string）
     *     01:　客先分類
     *     02　図面分類
     *     03　品種分類
     *     04-1　製品分類
     *     04-2　製品分類
     *     04-3　製品分類
     *     04-4　製品分類
     *     04-5　製品分類
     *     05　追番(連番)
     * @param  varietyCode 品種分類
     * @param  uppercCode1 製品分類04-1
     * @param  uppercCode2 品種分類04-2
     * @param  uppercCode3 品種分類04-3
     * @param  uppercCode4 品種分類04-4
     * @param  uppercCode5 品種分類04-5
     * @param  customerCode 客先コード
     * @param  drawingCode 図面分類コード
     * @param  obtainedNumber 確保数量
     * @return 采番后值
     */
    @ResponseBody
    public String[] getNumberCom(String projectID, String varietyCode, String uppercCode1,
                    String uppercCode2,
                    String uppercCode3,
                    String uppercCode4,
                    String uppercCode5,
                    String customerCode,
                    String drawingCode,
                    int obtainedNumber) throws Exception {
        // 根据采番项目取得当前项目的最大值
        String maxNo = "";
        int digit = 1; // 采番位数
        // 返回值：采番后值的数组
        String[] newNo = new String[obtainedNumber];
        // 是否为表中第一条数据0：是；1：不是
        int flag = 1;
        
        // 获取客先分类的最大值
        if ("01".equals(projectID)) {
            maxNo = commonDaoMapper.getMaxCustom();
            if (StringUtil.isNullOrEmpty(maxNo)) {
                newNo[0] = "000";
                maxNo = "000";
                flag = 0;
            }
            digit = 3;
        } else if ("02".equals(projectID)) {
            maxNo = commonDaoMapper.getMaxDrawing();
            if (StringUtil.isNullOrEmpty(maxNo)) {
                newNo[0] = "0";
                maxNo = "0";
                flag = 0;
            }
        } else if ("03".equals(projectID)) {
            maxNo = commonDaoMapper.getMaxVariety();
            if (StringUtil.isNullOrEmpty(maxNo)) {
                newNo[0] = "0";
                maxNo = "0";
                flag = 0;
            }
        } else if (projectID.contains("04")) {
            CommonCodeForm form = new CommonCodeForm();
            form.setVarietyCode(varietyCode);
            form.setUppercCode1(uppercCode1);
            form.setUppercCode2(uppercCode2);
            form.setUppercCode3(uppercCode3);
            form.setUppercCode4(uppercCode4);
            form.setUppercCode5(uppercCode5);
            maxNo = commonDaoMapper.getMaxProduct(form);
            if (StringUtil.isNullOrEmpty(maxNo)) {
                newNo[0] = "0";
                maxNo = "0";
                flag = 0;
            }
        } else {
            CommonCodeForm form = new CommonCodeForm();
            form.setCustomCode(customerCode);
            form.setDrawingCode(drawingCode);
            form.setVarietyCode(varietyCode);
            form.setProductCode1(uppercCode1);
            form.setProductCode2(uppercCode2);
            form.setProductCode3(uppercCode3);
            form.setProductCode4(uppercCode4);
            form.setProductCode5(uppercCode5);
            maxNo = commonDaoMapper.getMaxNumber(form);
            if (StringUtil.isNullOrEmpty(maxNo)) {
                newNo[0] = "000";
                maxNo = "000";
                flag = 0;
            }
            digit = 3;
        }
        
        // 获取采番值
        String newNoSingle = maxNo;
        if (flag == 0) {
            obtainedNumber = obtainedNumber - 1;
        }
        for (int i = 0; i < obtainedNumber; i++) {
            if (digit == 1) {
                newNoSingle = getCode1(newNoSingle);
                if ("error".equals(newNoSingle)) {
                    return new String[] {"error"};
                }
            } else if (digit == 3) {
                newNoSingle = getCode3(newNoSingle);
                if ("error".equals(newNoSingle)) {
                    return new String[] {"error"};
                }
            }
            if (flag == 0) {
                newNo[i + 1] = newNoSingle;
            } else {
                newNo[i] = newNoSingle;
            }
        }
        return newNo;
    }
  
    /**
     * 规格代码的生成
     * @param code 当前最大采番值
     * @return 取得三位采番值
     */
    public String getCode3(String code) throws Exception {
        char[] chars = code.toCharArray();
        if (chars[2] == 57) {
            chars[2] += 8;
        } else if (chars[2] < 90) {
            chars[2]++;
        } else {
            if (chars[1] == 57) {
                chars[1] += 8;
                chars[2] = 48;
            } else if (chars[1] < 90) {
                chars[1]++;
                chars[2] = 48;
            } else {
                if (chars[0] == 57) {
                    chars[0] += 8;
                    chars[1] = 48;
                    chars[2] = 48;
                } else if (chars[0] < 90) {
                    chars[0]++;
                    chars[1] = 48;
                    chars[2] = 48;
                } else {
                    return "error";
                }
            }
        }
        StringBuffer sbu = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            sbu.append(chars[i]);
        }
        return sbu.toString();
    }
    
    /**
     * 规格代码的生成
     * @param code 当前最大采番值
     * @return 取得一位采番值
     */
    public String getCode1(String code) throws Exception {
        char[] chars = code.toCharArray();
        if (chars[0] == 57) {
            chars[0] += 8;
        } else if (chars[0] < 90) {
            chars[0]++;
        } else {
            return "error";
        }
        StringBuffer sbu = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            sbu.append(chars[i]);
        }
        return sbu.toString();
    }
}
