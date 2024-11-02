package com.fujias.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fujias.common.component.SpringUtil;
import com.fujias.common.db.dao.MIdMapper;
import com.fujias.common.db.entity.MId;
import com.fujias.common.service.NoCreator.NoType;
import com.fujias.common.util.DateUtil;
import com.fujias.common.util.StringUtil;

/**
 * 採番工具类
 * 
 * @author zhangxq
 *
 */
public class NoCreatorOprator {

    MIdMapper idMapper = (MIdMapper) SpringUtil.getBean("MIdMapper");

    /**
     * 获取编码 12桁順番数字 例：00000001
     * 
     * @param type 类型
     * @return 编码
     */
    public String getSaibanNo12(NoType type) {
        return getSaibanNo(type, 12);

    }

    /**
     * 获取编码 8桁順番数字 例：00000001
     * 
     * @param type 类型
     * @return 编码
     */
    public String getSaibanNo4(NoType type) {
        return getSaibanNo(type, 4);
    }

    /**
     * 获取编码 12桁順番数字 例：00000001
     * 
     * @param type 类型
     * @return 编码
     */
    private String getSaibanNo(NoType type, int num) {
        MId saibanInfo = idMapper.selectByPrimaryKey(type.getCode());
        if (saibanInfo == null) {
            saibanInfo = new MId();
            saibanInfo.setIdNo(StringUtil.padLeft("1", num, '0'));
            saibanInfo.setIdType(type.getCode());
            saibanInfo.setIdName(type.getName());
            idMapper.insertSelective(saibanInfo);
        } else {
            List<String> returnArr = splitEnglishNumber(saibanInfo.getIdNo());
            BigDecimal no = new BigDecimal(returnArr.get(0));
            no = no.add(new BigDecimal(1));
            String newNo = StringUtil.padLeft(String.valueOf(no.intValue()), num, '0');
            saibanInfo.setIdNo(newNo);
            idMapper.updateByPrimaryKeySelective(saibanInfo);
        }
        return saibanInfo.getIdNo();
    }

    /**
     * 获取编码 12桁順番数字 例：00000001
     * 
     * @param type 类型
     * @param num 日期后的位数
     * @return 编码
     */
    public String getSaibanDateNo(NoType type, int num) {
        return getSaibanDateNo(type, num, DateUtil.getNowDate());
    }

    /**
     * 获取编码 12桁順番数字 例：00000001
     * 
     * @param type 类型
     * @param num 日期后的位数
     * @param dateSource dateSource
     * @return 编码
     */
    public String getSaibanDateNo(NoType type, int num, Date dateSource) {
        // 当前日期
        String date = DateUtil.formatDateToString(dateSource, DateUtil.DATE_FORMAT6);
        MId saibanInfo = idMapper.selectByPrimaryKey(type.getCode());
        if (saibanInfo == null) {
            saibanInfo = new MId();
            saibanInfo.setIdNo(date + StringUtil.padLeft("1", num, '0'));
            saibanInfo.setIdType(type.getCode());
            saibanInfo.setIdName(type.getName());
            idMapper.insertSelective(saibanInfo);
        } else {
            String newNo = "";
            if (saibanInfo.getIdNo().startsWith(date)) {
                String saibanno = saibanInfo.getIdNo();
                String saibannoEnd = saibanno.replace(date, "");
                List<String> returnArr = splitEnglishNumber(saibannoEnd);
                BigDecimal no = new BigDecimal(returnArr.get(0));
                no = no.add(new BigDecimal(1));
                newNo = date + StringUtil.padLeft(String.valueOf(no.intValue()), saibannoEnd.length() - returnArr.get(1).length(), '0');
            } else {
                newNo = date + StringUtil.padLeft("1", num, '0');
            }
            saibanInfo.setIdNo(newNo);
            idMapper.updateByPrimaryKeySelective(saibanInfo);
        }
        return saibanInfo.getIdNo();
    }

    private List<String> splitEnglishNumber(String oriStr) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbEn = new StringBuilder();
        List<String> returnArr = new ArrayList<String>();
        boolean check = true;
        for (int i = 0; i < oriStr.length(); i++) {
            if (check) {
                if (StringUtil.checkEnglish(oriStr.charAt(i))) {
                    sbEn.append(oriStr.charAt(i));
                    continue;
                } else if ('0' == oriStr.charAt(i)) {
                    continue;
                } else {
                    check = false;
                }
            }
            sb.append(oriStr.charAt(i));
        }
        if (sb.toString().isEmpty()) {
            returnArr.add("0");
        } else {
            returnArr.add(sb.toString());
        }
        returnArr.add(sbEn.toString());
        return returnArr;
    }

    /**
     * 获取编码 12桁順番数字 例：00000001
     * 
     * @param type 类型
     * @param num 日期后的位数
     * @param dateSource dateSource
     * @return 编码
     */
    public String getSaibanDateNo2(NoType type, int num, Date dateSource) {
        // 当前日期
        String date = DateUtil.formatDateToString(dateSource, DateUtil.DATE_FORMAT6).substring(0, 2);
        MId saibanInfo = idMapper.selectByPrimaryKey(type.getCode());
        if (saibanInfo == null) {
            saibanInfo = new MId();
            saibanInfo.setIdNo(date + StringUtil.padLeft("1", num, '0'));
            saibanInfo.setIdType(type.getCode());
            saibanInfo.setIdName(type.getName());
            idMapper.insertSelective(saibanInfo);
        } else {
            String newNo = "";
            if (saibanInfo.getIdNo().startsWith(date)) {
                String saibanno = saibanInfo.getIdNo();
                String saibannoEnd = saibanno.replaceFirst(date, "");
                List<String> returnArr = splitEnglishNumber(saibannoEnd);
                BigDecimal no = new BigDecimal(returnArr.get(0));
                no = no.add(new BigDecimal(1));
                newNo = date + StringUtil.padLeft(String.valueOf(no.intValue()), saibannoEnd.length() - returnArr.get(1).length(), '0');
            } else {
                newNo = date + StringUtil.padLeft("1", num, '0');
            }
            saibanInfo.setIdNo(newNo);
            idMapper.updateByPrimaryKeySelective(saibanInfo);
        }
        return saibanInfo.getIdNo();
    }

    /**
     * 获取lOtNo编码 例：WD-11
     * 
     * @param noType noType
     * @return 编码
     * @throws Exception Exception
     */
    public String getLotNo(NoType noType) throws Exception {
        MId saibanInfo = idMapper.selectByPrimaryKey(noType.getCode());
        String newIdNo = this.newLotNo();
        if (saibanInfo == null) {
            saibanInfo = new MId();
            saibanInfo.setIdNo(newIdNo + "1");
            saibanInfo.setIdType(noType.getCode());
            saibanInfo.setIdName(noType.getName());
            idMapper.insertSelective(saibanInfo);
        } else {
            if (!newIdNo.equals(saibanInfo.getIdNo().substring(0, saibanInfo.getIdNo().length() - 1))) {
                saibanInfo.setIdNo(newIdNo + "1");
            } else {
                saibanInfo.setIdNo(newIdNo + String.valueOf(Integer.valueOf(saibanInfo.getIdNo().substring(saibanInfo.getIdNo().length() - 1)) + 1));
            }
            idMapper.updateByPrimaryKeySelective(saibanInfo);
        }
        return saibanInfo.getIdNo();
    }

    /**
     * 编写lOtNo编码 例：WD-1
     * 
     * @return 编码
     * @throws Exception Exception
     */
    private String newLotNo() throws Exception {
        Date startTime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(startTime);
        String year = "";
        String month = "";
        String date = "";
        int day;
        if ((now.get(Calendar.YEAR)) == 2020) {
            year = "W";
        } else if (now.get(Calendar.YEAR) == 2021) {
            year = "X";
        } else if (now.get(Calendar.YEAR) == 2022) {
            year = "Y";
        } else if (now.get(Calendar.YEAR) == 2023) {
            year = "Z";
        } else if (now.get(Calendar.YEAR) == 2024) {
            year = "A";
        } else if (now.get(Calendar.YEAR) == 2025) {
            year = "B";
        } else if (now.get(Calendar.YEAR) == 2026) {
            year = "C";
        } else if (now.get(Calendar.YEAR) == 2027) {
            year = "D";
        } else if (now.get(Calendar.YEAR) == 2028) {
            year = "E";
        } else if (now.get(Calendar.YEAR) == 2029) {
            year = "F";
        } else if (now.get(Calendar.YEAR) == 2030) {
            year = "G";
        } else if (now.get(Calendar.YEAR) == 2031) {
            year = "H";
        } else if (now.get(Calendar.YEAR) == 2032) {
            year = "I";
        } else if (now.get(Calendar.YEAR) == 2033) {
            year = "J";
        } else if (now.get(Calendar.YEAR) == 2034) {
            year = "K";
        } else if (now.get(Calendar.YEAR) == 2035) {
            year = "L";
        } else if (now.get(Calendar.YEAR) == 2036) {
            year = "M";
        } else {
            throw new Exception("处理年度超过系统设置，请联系管理员");
        }
        if (now.get(Calendar.MONTH) + 1 == 1) {
            month = "A";
        } else if (now.get(Calendar.MONTH) + 1 == 2) {
            month = "B";
        } else if (now.get(Calendar.MONTH) + 1 == 3) {
            month = "C";
        } else if (now.get(Calendar.MONTH) + 1 == 4) {
            month = "D";
        } else if (now.get(Calendar.MONTH) + 1 == 5) {
            month = "E";
        } else if (now.get(Calendar.MONTH) + 1 == 6) {
            month = "F";
        } else if (now.get(Calendar.MONTH) + 1 == 7) {
            month = "G";
        } else if (now.get(Calendar.MONTH) + 1 == 8) {
            month = "H";
        } else if (now.get(Calendar.MONTH) + 1 == 9) {
            month = "I";
        } else if (now.get(Calendar.MONTH) + 1 == 10) {
            month = "J";
        } else if (now.get(Calendar.MONTH) + 1 == 11) {
            month = "K";
        } else if (now.get(Calendar.MONTH) + 1 == 12) {
            month = "L";
        }
        day = now.get(Calendar.DAY_OF_MONTH);
        date = year + month + "-" + StringUtil.padLeft(String.valueOf(day), 2, '0');
        return date;
    }

    /**
     * 获取采购委外单号
     * 
     * @param noType noType
     * @param supplierId 供应商编号
     * @param num 日期后的位数
     * @return 单号
     */
    public String getPurchaseNo(NoType noType, String supplierId, int num) {
        // 当前日期
        String date = DateUtil.formatDateToString(DateUtil.getNowDate(), DateUtil.DATE_FORMAT6).substring(0, 2);
        MId saibanInfo = idMapper.selectByPrimaryKey(supplierId);
        if (saibanInfo == null) {
            saibanInfo = new MId();
            saibanInfo.setIdNo(date + StringUtil.padLeft("1", num, '0'));
            saibanInfo.setIdType(supplierId);
            saibanInfo.setIdName(supplierId + "采购单号");
            saibanInfo.setDelFlg("0");
            idMapper.insertSelective(saibanInfo);
        } else {
            String newNo = "";
            if (saibanInfo.getIdNo().startsWith(date)) {
                String saibanno = saibanInfo.getIdNo();
                String saibannoEnd = saibanno.replaceFirst(date, "");
                List<String> returnArr = splitEnglishNumber(saibannoEnd);
                BigDecimal no = new BigDecimal(returnArr.get(0));
                no = no.add(new BigDecimal(1));
                newNo = date + StringUtil.padLeft(String.valueOf(no.intValue()), saibannoEnd.length() - returnArr.get(1).length(), '0');
            } else {
                newNo = date + StringUtil.padLeft("1", num, '0');
            }
            saibanInfo.setIdNo(newNo);
            idMapper.updateByPrimaryKeySelective(saibanInfo);
        }
        if (noType.getCode().equals(NoType.委外加工编号.getCode())) {
            return saibanInfo.getIdNo() + "W";
        }
        return saibanInfo.getIdNo();
    }

    /**
     * 获取联系人顺番号
     * 
     * @param noType noType
     * @return 联系人顺番号
     */
    public String getSCPNo(NoType noType) {
        MId saibanInfo = idMapper.selectByPrimaryKey(noType.getCode());
        if (saibanInfo == null) {
            saibanInfo = new MId();
            saibanInfo.setIdNo("01");
            saibanInfo.setIdType(noType.getCode());
            saibanInfo.setIdName(noType.getName());
            idMapper.insertSelective(saibanInfo);
        } else {
            saibanInfo.setIdNo(String.format("%02d", (Integer.valueOf(saibanInfo.getIdNo()) + 1)));
            idMapper.updateByPrimaryKeySelective(saibanInfo);
        }
        return saibanInfo.getIdNo();
    }
}
