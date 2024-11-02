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
       document.SVNM0610Bean.sousa.value="kakutei";
    }
    // 「クリア」ボタンを押下する
   　function kuria(){
       document.forms(0).oldPassword.focus();
       document.forms(0).oldPassword.value="";
       document.forms(0).newPassword.value="";
       document.forms(0).kakuninnPassword.value="";
       document.getElementById('lblMsg').innerText=""
       document.forms(0).oldPassword.style.backgroundColor='';
       document.forms(0).newPassword.style.backgroundColor='';
       document.forms(0).kakuninnPassword.style.backgroundColor='';
    }
    </script>
</head>
<body style="margin-top: 0;">
    <html:form action="SVNM0610.do" method="post">
        <html:hidden property="sousa" value="" />
        <table cellspacing="0" cellpadding="0" width="99%" border="0">
            <tr>
                <td style="height: 13px" colspan="2">
                    <!--エラーメッセージ-->
                    <font id="lblMsg" style="color: red;" class="td_font"> <html:errors />
                    </font>
                </td>
            </tr>
        </table>
        <table class="TB03" cellspacing="1" cellpadding="3" border="0"
            width="70%">
            <!--従業員番号-->
            <tr class="TR01">
                <th class="TH01" style="width: 30%">
                    <label>
                        <strong><span style="font-size: 10pt">従業員番号：&nbsp;
                                &nbsp;</span>&nbsp;</strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:hidden property="sysUserNo" write="true" style="width :23%" />
                </td>
            </tr>
            <!--現行パスワード-->
            <tr class="TR01">
                <th class="TH01" style="width: 30%">
                    <label>
                        <strong> <span style="font-size: 10pt">現行パスワード：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password styleClass="TEXT01" property="oldPassword"
                        style="width: 28%" maxlength="15" />
                </td>
            </tr>
            <!--新パスワード-->
            <tr class="TR01">
                <th class="TH01" style="width: 30%">
                    <label>
                        <strong> <span style="font-size: 10pt">新パスワード：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password styleClass="TEXT01" property="newPassword"
                        style="width: 28%" maxlength="15" />
                </td>
            </tr>
            <!--新パスワード確認-->
            <tr class="TR01">
                <th class="TH01" style="width: 30%">
                    <label>
                        <strong> <span style="font-size: 10pt">新パスワード確認：</span> <span
                            style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password styleClass="TEXT01" property="kakuninnPassword"
                        style="width: 28%" maxlength="15" />
                </td>
            </tr>
        </table>
        <table border="0" cellspacing="0" cellpadding="0" width="70%">
            <tr>
                <td style="height: 5px">
                </td>
            </tr>
            <tr>
                <td>
                    <!--確認-->
                    <html:submit property="btnKakutei" styleClass="BUTTON01"
                        value=" 確  定 " onclick="kakutei();" />&nbsp;
                    <!--クリア-->
                    <html:button property="btnKuria" styleClass="BUTTON01"
                        value="ク リ ア" onclick="kuria();" />
                </td>
            </tr>
            <tr>
                <td style="height: 299px">
                </td>
            </tr>
        </table>
        <br />
        <bean:write name="SVNM0610Bean" property="hidError" filter="false" />
    </html:form>
</body>
</html:html>