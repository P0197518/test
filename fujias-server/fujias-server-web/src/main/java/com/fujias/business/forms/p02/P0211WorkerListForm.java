package com.fujias.business.forms.p02;

import com.fujias.common.db.entity.T0202Workers;
import com.fujias.common.entity.Pager;

/**
 * @author 
 *
 */
public class P0211WorkerListForm extends T0202Workers{
    
    private Pager pager;
    
    private String codeCd;
    
    private String codeName;
    
    private String codeTypeCd;
    
    private String value;
    
    private String text;
    
    

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeTypeCd() {
        return codeTypeCd;
    }

    public void setCodeTypeCd(String codeTypeCd) {
        this.codeTypeCd = codeTypeCd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
