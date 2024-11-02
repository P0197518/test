<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
    <head>
        <title>SVN管理システム</title>
        <link rel="stylesheet"
            href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/commonCheck.js"></script>
        <script language="javascript" type="text/javascript">
            var path=window.location.href;
            path=path.substring(path.length-4);
            if(path==".jsp"){
                location.href=location.href.substring(0,location.href.lastIndexOf("/jsp"));
            }
            if(top.top!=null){
                path=top.top.location.href;
                path=path.substring(path.length-3);
            }
            if(path==".do"){
                location.href=location.href.substring(0,location.href.lastIndexOf("/"));
            }
        // ボタンを押下する
        function clickButton(butValue) {
            document.forms[0].submitValue.value = butValue;
        }
    </script>
    </head>

    <body style="margin-top: 0;">
        <html:form action="/SVNM0312.do" method="post">
            <html:hidden property="submitValue" name="SVNM0312Bean" value="edit"/>
            <html:hidden property="privilegeNo" name="SVNM0312Bean" />
            <html:hidden property="updateDate" name="SVNM0312Bean" />
            <div style="height: 13px">
                <table cellspacing="0" cellpadding="0" width="100%" border="0">
                    <tr>
                        <td style="height: 13px" class="td_font">
                            <label id="lblMsg"style="color: red;font-family: ＭＳ Ｐゴシック;">
                                <html:errors />
                            </label>
                        </td>
                    </tr>
                </table>
            </div>
            <table>
                <tr>
                    <td bgcolor="#224499"><SPAN style="color:#ffffff;font-weight:bold;font-size:14">権限マスタ</SPAN></td>
                </tr>
                <tr>
                    <td><a href="javascript:clickButton('back');" style="text-decoration:underline;font-size:13;color:#0000FF;">前画面へ戻る</a></td>
                </tr>
            </table>
            <table cellspacing="1" cellpadding="3" width="70%" border="0" bgcolor="#999999">
                <!--権限名前-->
                <tr class="TR01">
                    <th class="TH01" style="width: 20%;" colspan="1">
                        <label>
                            <strong> <span style="font-size: 10pt">権限名：</span> <span
                                style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                            </strong>
                        </label>
                    </th>
                    <td class="TD02" style="width: 40%;">
                        <label id="lblTxtSunUserNO">
                            <html:text name="SVNM0312Bean" property="svnm0312PrivilegeName"
                                styleClass="TEXT01" style="width: 30%" maxlength="10"></html:text>
                        </label>
                    </td>
                </tr>
                <tr class="TR01">
                    <th class="TH01" rowspan="1" style="width: 20%; height: 28px;">
                        <span class="th_font" style="font-family: ＭＳ Ｐゴシック">プロジェクト管理</span><span
                            style="font-size: 10pt">：</span> &nbsp; &nbsp;
                    </th>
                    <td class="TD02" style="width: 40%; height: 28px;">
                        <html:select name="SVNM0312Bean" style="width: 18%" styleClass="SELECT01"
                            property="projectPrivilegeCode">
                            <logic:notEmpty name="SVNM0312Bean"
                                property="projectPrivilegeBean">
                                <html:optionsCollection name="SVNM0312Bean"
                                    property="projectPrivilegeBean" label="name" value="code" />
                            </logic:notEmpty>
                        </html:select>
                    </td>
                </tr>
                <tr class="TR01">
                    <th class="TH01" rowspan="1" style="width: 20%; height: 28px;">
                        <span class="th_font" style="font-family: ＭＳ Ｐゴシック">マスタメンテ</span><span
                            style="font-size: 10pt">：</span> &nbsp; &nbsp;
                    </th>
                    <td class="TD02" style="width: 40%; height: 28px;">
                        <html:select name="SVNM0312Bean" style="width: 18%" styleClass="SELECT01"
                            property="masterPrivilegeCode">
                            <logic:notEmpty name="SVNM0312Bean"
                                property="masterPrivilegeBean">
                                <html:optionsCollection name="SVNM0312Bean"
                                    property="masterPrivilegeBean" label="name" value="code" />
                            </logic:notEmpty>
                        </html:select>
                    </td>
                </tr>
                <tr class="TR01">
                    <th class="TH01" colspan="1" style="width: 20%">
                        <label>
                            <strong><span style="font-size: 10pt">コメント：&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            </strong>
                        </label>
                    </th>
                    <td class="TD02" style="width: 40%">
                        <html:textarea name="SVNM0312Bean" property="privilegeComment"
                            styleClass="TEXTAREA01" rows="5" cols="50"></html:textarea>
                    </td>
                </tr>
            </table>
            <table style="margin-left: -6;" cellpadding="3">
                <tr>
                    <td>
                        <html:submit property="submitButton" value=" 確  定 "
                            styleClass="BUTTON01" onclick="clickButton('edit');"></html:submit>
                    </td>
                    <td>
                        <html:submit property="backButton" value=" 戻  る "
                            styleClass="BUTTON01" onclick="clickButton('back')"></html:submit>
                    </td>
                </tr>
            </table>
            <br>
            <br />
            <div style="position: absolute; top: 77%">
                <table cellspacing="1" cellpadding="3" width="70%" border="0"
                    class="T01">
                    <tr class="TR01">
                        <td align="right" rowspan="3" width="10%">
                            <label>
                                <strong><span
                                    style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">注&nbsp;&nbsp;</span>
                                </strong>
                            </label>
                        </td>
                        <td style="height: 34px; width: 90%;">
                            <span style="font-size: 10pt">なし：該当モジュールのアクセス権限なし。</span>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <td style="height: 34px; width: 90%;">
                            <span style="font-size: 10pt">読み権限：該当モジュールの読権限がある。</span>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <td style="height: 34px; width: 90%;">
                            <span style="font-size: 10pt">管理権限：該当モジュールの管理権限がある（検索、新規、編集、削除）。</span>
                        </td>
                    </tr>
                </table>
            </div>
            <bean:write name="SVNM0312Bean" property="hidError" filter="false"/>
        </html:form>
    </body>
</html>
