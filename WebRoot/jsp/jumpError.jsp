<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
    <meta http-equiv="Content-Type" content="text/html;">
    <LINK rel="stylesheet" href="<%=request.getContextPath()%>/include/body.css" type="text/css">
    <title></title>
    <script type="text/javascript" src="include/FBSE.js"></script>
    <script language="JavaScript" type="text/JavaScript">
    function Close1() 
{ 
var ua=navigator.userAgent 
var ie=navigator.appName=="Microsoft Internet Explorer"?true:false 
    if(ie) 
    { 
        var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE ")))) 
    if(IEversion< 5.5) { 
        var str = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">' 
        str += '<param name="Command" value="Close"></object>'; 
        document.body.insertAdjacentHTML("beforeEnd", str); 
        document.all.noTipClose.Click(); 
        } 
         else if(confirm('終了してもよろしいですか？')) { 
                window.opener =null; 
                   top.document.getElementById("mainFrame").src="<%=request.getContextPath()%>/SVNM0110.do";
              } 
    } 
        else if(confirm('終了してもよろしいですか？')) { 
            top.document.getElementById("mainFrame").src="<%=request.getContextPath()%>/SVNM0110.do";
        } 
}
    </script>
</head>
<body background="img/back00.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="font-size: 12px;">
    <table border="0" style="width: 960px; font-size: 12px" align="center">
        <tr>
            <td style="height: 300px;"></td>
        </tr>
        <tr align="center">
            <TD align="center">
                <font color="red" style="font-size: 30px;font-family:ＭＳ Ｐゴシック;">システムエラーが発生しました！</font>
            </TD>
        </tr>
        <tr align="center">
            <td align="center" style="font-size: 12px;font-family:ＭＳ Ｐゴシック;">
                <input type="button" value=" 終了" onclick="Close1();" />
            </td>
        </tr>
    </table>
</body>
</html:html>
