<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<head>
    <title>SVN管理システム</title>
    <link href="<%=request.getContextPath()%>/css/svnm_css.css"
        type="text/css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
    // 「確定」ボタンを押下する
    function kakutei(){
       document.SVNM0411Bean.sousa.value="kakutei";
    }
    // 「戻る」ボタンを押下する、或いは「前画面へ戻る」リンクをクリックする
    function modoru(){
       document.SVNM0411Bean.sousa.value="modoru";
       document.forms[0].submit();
    }
  </script>
</head>
<body style="margin-top: 0;">
    <html:form action="SVNM0411.do" method="post">
        <html:hidden property="sousa" value="" />
        <!--メッセージ-->
        <table cellspacing="0" cellpadding="0" width="100%" border="0">
            <tr>
                <td class="td_font" style="height: 13px">
                    <label id="lblMsg" style="color: red; font-family: ＭＳ Ｐゴシック;">
                        <html:errors />
                    </label>
                </td>
            </tr>
        </table>
        <!--ボディー部-->
        <table>
            <tr>
                <td bgcolor="#224499"><SPAN style="color:#ffffff;font-weight:bold;font-size:14">従業員マスタ</SPAN></td>
            </tr>
            <tr>
                <td><html:link href="javascript:modoru();" style="text-decoration:underline;font-size:13;color:#0000FF;">前画面へ戻る</html:link></td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="3" width="70%" border="0"
            bgcolor="#999999">
            <!--従業員番号-->
            <tr class="TR01">
                <th class="TH01" style="width: 30%">
                    <label>
                        <strong> <span style="font-size: 10pt">従業員番号：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02" style="width: 70%">
                    <html:text styleClass="TEXT01" property="sysUserNo"
                        style="width: 20%" maxlength="5" />
                </td>
            </tr>
            <!--従業員名前-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong> <span style="font-size: 10pt">従業員名前：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:text styleClass="TEXT01" property="sysUserName"
                        style="width: 25%" maxlength="10" />
                </td>
            </tr>
            <!--システム登録パスワード-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong> <span style="font-size: 10pt">システム登録パスワード：</span>
                                 <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password styleClass="TEXT01" property="sysLoginPsw"
                        style="width: 25%" maxlength="15"></html:password>
                </td>
            </tr>
            <!--システム登録パスワード確認-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong><span style="font-size: 10pt">システム登録パスワード確認：</span>
                        <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password styleClass="TEXT01" property="sysLoginPswKakuninn"
                        style="width: 25%" maxlength="15"></html:password>
                </td>
            </tr>
            <!--SVN登録名-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong> <span style="font-size: 10pt">SVN登録名：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:text styleClass="TEXT01" property="svnLoginName"
                        style="width: 33%" maxlength="20" />
                </td>
            </tr>
            <!--SVN登録パスワード-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong> <span style="font-size: 10pt">SVNパスワード：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password styleClass="TEXT01" property="svnPassword"
                        style="width: 25%" maxlength="15"></html:password>
                </td>
            </tr>
            <!--SVNパスワード確認-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong> <span style="font-size: 10pt">SVNパスワード確認：</span>
                            <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password styleClass="TEXT01" property="svnPasswordKakuninn"
                        style="width: 25%" maxlength="15"></html:password>
                </td>
            </tr>
            <!--メールアドレス-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong> <span style="font-size: 10pt">メール：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:text styleClass="TEXT01" property="mail" style="width: 33%"
                        maxlength="20" />
                </td>
            </tr>
            <!--権限-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong> <span style="font-size: 10pt">権限：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:select property="privilege" style="background-color:#FFFFE0;width: 29%">
                        <html:option value=""></html:option>
                        <logic:notEmpty name="SVNM0411Bean" property="privilegeSelect">
                            <html:optionsCollection property="privilegeSelect" value="code"
                                label="name" />
                        </logic:notEmpty>
                    </html:select>
                </td>
            </tr>
            <!--コメント-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong><span style="font-size: 10pt">コメント：&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:textarea property="comment" styleClass="TEXTAREA01" rows="5"
                        cols="50"></html:textarea>
                </td>
            </tr>
        </table>
        <!--ボタン-->
        <table cellpadding="3" border="0" style="margin-left: -6">
            <tr>
                <!--確定-->
                <td>
                    <html:submit property="btnKakutei" styleClass="BUTTON01"
                        value=" 確  定 " onclick="kakutei();" />
                </td>
                <!--戻る-->
                <td>
                    <html:button property="btnModuru" styleClass="BUTTON01"
                        value=" 戻  る " onclick="modoru();" />
                </td>
            </tr>
        </table>
        <br>
        <bean:write name="SVNM0411Bean" property="hidError" filter="false" />
    </html:form>
</body>
</html:html>