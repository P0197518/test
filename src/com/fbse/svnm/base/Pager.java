package com.fbse.svnm.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.common.FBSELogHandler;

/**
 * <HR>
 * 分ページ部品である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>一覧の分ページ処理</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 */
public class Pager extends TagSupport {

    // requestで保存されているFBSEDataResultSetオブジェクトのキー
    private String name;

    // FBSEDataResultSetオブジェクトのフィールド名
    private String property;

    // 一頁に格納するレコード数
    private String pageSize;

    // ページ数
    private int maxPage;

    // 表示する頁数
    private int pageNo;

    /**
     * 一頁に格納するレコード数を取得
     * 
     * @return 一頁に格納するレコード数
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * 一頁に格納するレコード数を設定
     * 
     * @param pageSize
     *            一頁に格納するレコード数
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * FBSEDataResultSetオブジェクトのキーを取得
     * 
     * @return FBSEDataResultSetオブジェクトのキー
     */
    public String getName() {
        return name;
    }

    /**
     * FBSEDataResultSetオブジェクトのKeyを設定
     * 
     * @param name
     *            FBSEDataResultSetオブジェクトのキー
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * フィールド名を取得
     * 
     * @return フィールド名
     */
    public String getProperty() {
        return property;
    }

    /**
     * フィールド名を設定
     * 
     * @param property
     *            フィールド名
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * ラベルの始め
     */
    public int doStartTag() throws JspException {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "Pager", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "doStartTag", "Start");
        // requestオブジェクトを取得
        ServletRequest request = pageContext.getRequest();
        // requestで保存されているオブジェクトがヌルの場合
        if (request.getAttribute(name) == null) {
            return SKIP_BODY;
        }
        // 一頁に格納するレコード数を取得
        int Size = Integer.parseInt(pageSize);
        if (request.getParameter("pageNo") != null) {
            // 表示する頁数を取得
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        } else {
            pageNo = 1;
        }
        // FBSEDataResultSetオブジェクトを取得
        FBSEDataResultSet result = (FBSEDataResultSet) request.getAttribute(name);
        // 頁数を取得
        maxPage = result.paging("table1", Size, pageNo);
        pageNo = pageNo > maxPage ? maxPage : pageNo;
        // フィールド名を取得
        String[] strPro = property.split(",");
        // ArrayListオブジェクトを宣言
        ArrayList alInfo = new ArrayList();
        // HashMapオブジェクトを宣言
        HashMap hmInfo = new HashMap();
        // 一時の変数
        int tempInt = 1;
        // FBSEDataResultSetオブジェクトの情報をHashMapオブジェクトに設定
        while (result.getNext("table1")) {
            // HashMapオブジェクトをインスタンス
            hmInfo = new HashMap();
            hmInfo.put("No", String.valueOf(tempInt));
            tempInt++;
            for (int i = 0; i < strPro.length; i++) {
                try{
                    hmInfo.put(strPro[i], result.getObject(strPro[i]));
                }catch(Exception e){
                    hmInfo.put(strPro[i], "");
                }
            }
            alInfo.add(hmInfo);
        }
        // 表示する情報をrequestオブジェクトに設定
        request.setAttribute(name, alInfo);
        // ログ出力
        log.printLog("INFO", "Pager", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "doStartTag", "End");
        return EVAL_BODY_INCLUDE;
    }

    /**
     * ラベルの終わり
     */
    public int doEndTag() throws JspException {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "Pager", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "doEndTag", "Start");
        // requestオブジェクトを宣言
        ServletRequest request = pageContext.getRequest();
        // JspWriterオブジェクトを宣言
        JspWriter pageWriter = pageContext.getOut();
        try {
            // requestで保存されているオブジェクトがヌルの場合
            if (request.getAttribute(name) == null) {
                pageWriter.write("<input id='pageNo' name='pageNo' value='1' type='hidden'/>");
                return EVAL_PAGE;
            }
            // ページング部品を出力
            pageWriter.write("<table width='99%' border='0' style='margin-left: 1%'>");
            pageWriter.write("<tr><td style='width: 83%;'></td><td><div align='right' class='td_font'>");
            if (pageNo != 1) {
                pageWriter.write("<a id='lnkLast' href='javascript:");
                pageWriter.write("$(\"pageNo\").value=parseInt($(\"drpPage\").value)-1;document.forms[0].submit();");
                pageWriter.write("' style='text-decoration: NONE'>▲前</a>");
            }
            else {
                pageWriter.write("<font color='gray'>▲前</font>");
            }
            pageWriter.write("&nbsp;第&nbsp;</div></td><td style='width: 4%'>");
            pageWriter.write("<select id='drpPage' onChange='$(\"pageNo\").value=$(\"drpPage\").value;document.forms[0].submit();' style='font-size: 10px; width: 100%; height: 15px;'>");
            for (int i = 1; i <= maxPage; i++) {
                pageWriter.write("<option value='" + i + "'>" + i + "</option>");
            }
            pageWriter.write("</select></td><td><div align='left' class='td_font'><div align='left'>頁&nbsp;");
            if (pageNo != maxPage) {
                pageWriter.write("<a id='lnkNext' href='javascript:");
                pageWriter.write("$(\"pageNo\").value=parseInt($(\"drpPage\").value)+1;document.forms[0].submit();");
                pageWriter.write("' style='text-decoration: NONE'>次▼</a>");
            } else {
                pageWriter.write("<font color='gray'>次▼</font>");
            }
            pageWriter.write("</div></div></td></tr></table><input id='pageNo' name='pageNo' value='1' type='hidden'/>");
            pageWriter.write("<script language='javascript'>$(\"drpPage\").value="
                            + pageNo + "</script>");
            // ログ出力
            log.printLog("INFO", "Pager", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "doEndTag", "End");
        } catch (IOException e) {
            // ログ出力
            log.printLog("ERROR", "Pager", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "doEndTag", e
                    .toString());
        }
        return EVAL_PAGE;
    }
}