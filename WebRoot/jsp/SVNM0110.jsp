<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html:html>
    <head>
        <base href="<%=basePath%>">
        <title>SVN管理システム</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="css/svnm_css.css" type="text/css" />
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
        // ヘッダ部表示の時間
        function startclock () {
            showtime();
            setInterval("showtime()",1000);
        }
        // 現在の時間の取得
        function showtime () {
            var now = new Date();
            var hours = now.getHours();
            var minutes = now.getMinutes();
            var seconds = now.getSeconds();
            var timeValue = "";
            timeValue+=now.getYear()+"/";
            timeValue+=(parseInt(now.getMonth())+1)+"/";
            timeValue+=now.getDate()+" ";
            timeValue += hours;
            timeValue += ((minutes < 10) ? ":0" : ":") + minutes;
            timeValue += ((seconds < 10) ? ":0" : ":") + seconds;
            document.getElementById("lblTime").innerHTML = timeValue;
        }
        // 「クリア」ボタンを押した、相応のクリア操作
        function kuria() {
            document.getElementById('userId').focus();
            document.getElementById('lblMsg').innerText = '';
            document.getElementById('userId').value = '';
            document.getElementById('password').value = '';
            colorClear();
        }
        // 色のクリア
        function colorClear() {
              document.getElementById('userId').style.backgroundColor = '#FFFFE0';
              document.getElementById('password').style.backgroundColor = '#FFFFE0';
        }
        </script>
    </head>
    <body topmargin="0" onload="startclock();">
        <html:form action="SVNM0110.do" method="post" focus="userId">
        <html:hidden name="SVNM0110Bean" property="tourouku"/>
        <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td rowspan="3">
                    <img alt="" src="img/logo.jpg" />
                </td>
            </tr>
            <tr>
                <td align="right">
                    <label id="lblTime" class="th_font"></label>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <strong><span style="font-size: 14pt">SVN管理システム</span></strong>
                </td>
            </tr>
        </table>
        <hr align="left" width="100%" color="#DA251A" style="margin-top:-12" />
        <table cellspacing="0" cellpadding="0" width="99%" border="0">
            <tr>
                <td style="height: 13px; text-align: left;font-family: ＭＳ Ｐゴシック;" colspan="2" class="td_font">
                    <label id="lblMsg" style="color: red">
                        <html:errors/>
                    </label>
                </td>
            </tr>
        </table>
        <table width="30%" border="0">
            <!--従業員番号-->
            <tr class="TR01">
                <td style="width: 35%; font-size: 10pt;">
                    従業員番号：<span style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                </td>
                <td style="width: 65%;">
                    <html:text onkeydown="if(event.keyCode==13){document.getElementById('sub').click();return false;};"
                 name="SVNM0110Bean" property="userId" style="width:68%" styleClass="TEXT01" maxlength="5"/></td>
            </tr>
            <!--パスワード-->
            <tr class="TR01">
                <td style="font-size: 10pt;">
                    パスワード：<span style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                </td>
                <td>
                    <html:password onkeydown="if(event.keyCode==13){document.getElementById('sub').click();return false;};"
                 name="SVNM0110Bean" property="password" style="width: 68%" styleClass="TEXT01" maxlength="15"/></td>
            </tr>
            <tr class="TR01">
                <td style="font-size: 10pt; text-align: right">
                </td>
                <td>
                    <!--ログインボタン-->
                <html:button property="sub" styleClass="BUTTON01" onclick="forms[0].tourouku.value='true';document.forms[0].submit();">ログイン</html:button>&nbsp;
                <!--クリアボタン-->
                <input name="btnKuria" type="button" id="btnKuria" onclick="kuria();" class="BUTTON01"
                    value="ク リ ア"/>
                </td>
            </tr>
            <tr class="TR01" style="height: 391px">
                <td></td>
                <td></td>
            </tr>
        </table>
        <HR align=left width="100%" color="#DA251A">
        <table border="0" width="100%">
            <tr align="right">
                <td height="16px" style="font-family: ＭＳ Ｐゴシック; font-size: 12px;">
                    All Rights Reserved,Copyright &copy; 2008 FBSE
                </td>
                <td style="width: 2%">
                </td>
            </tr>
        </table>
    </html:form>
    <bean:write name="SVNM0110Bean" property="hidError" filter="false"/>
    </body>
</html:html>