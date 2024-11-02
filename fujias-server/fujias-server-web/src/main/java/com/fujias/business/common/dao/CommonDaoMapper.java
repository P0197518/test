package com.fujias.business.common.dao;

import java.util.List;

import com.fujias.business.common.entity.CodeEntity;
import com.fujias.business.common.form.CommonCodeForm;
import com.fujias.business.common.form.CommonCustomForm;
import com.fujias.business.common.form.CommonForm;
import com.fujias.business.common.form.CommonSupplierForm;
import com.fujias.business.entity.dms002001.SelectDataEntity;
import com.fujias.common.entity.SelectItem;

/**
 * 共通的Dao
 * 
 * @author huangjiagang
 *
 */
public interface CommonDaoMapper {

    List<SelectItem> getCodeList(CodeEntity kbntypecd);

    List<SelectItem> getDepList();

    List<SelectItem> getDepListByUserId(CodeEntity entity);

    List<SelectItem> getTextureList();

    List<SelectItem> selectUsers();

    List<SelectDataEntity> selectData();

    CommonCustomForm getCustomInfoById(String id);

    List<SelectItem> getAddressById(CommonCustomForm param);

    CommonSupplierForm getSupplierInfoById(String id);

    List<SelectItem> getAutoMaterialsByDrawingNo(CommonForm param);

    List<SelectItem> getAutoMaterialsByProductNo(CommonForm param);

    List<SelectItem> getSupplierInfoData();

    String getMaxCustom();

    String getMaxDrawing();

    String getMaxVariety();

    String getMaxProduct(CommonCodeForm param);

    String getMaxNumber(CommonCodeForm param);
}
