<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
    <head>
        <title>SVN管理システム</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body topmargin="0">
        <form>
            <table border="0" align="center" cellpadding="0" cellspacing="0" width="100%" height="90%">
                <tr align="center" height="10%">
                    <td>
                        <iframe name="TABLE_toubu" hidefocus="true" scrolling="no" width="100%" src="<%=request.getContextPath()%>/jsp/head.jsp" frameborder="0" marginheight="0" marginwidth="0" style="height:84" id="IFRAME1">
                        </iframe>
                    </td>
                </tr>
                <tr align="center" height="90%">
                    <td valign="top" height="100%">
                        <iframe name="TABLE_align" height="480" hidefocus="true" scrolling="no" width="100%" src="<%=request.getContextPath()%>/SVNM0210.do" frameborder="0" marginheight="0" marginwidth="0">
                        </iframe>
                    </td>
                </tr>
            </table>
            <logic:equal name="pjPrivilege" value="0">
                <logic:notEqual name="masterPrivilege" value="0">
                    <script language="javascript">
                        document.getElementById("TABLE_align").src="<%=request.getContextPath()%>/jsp/SVNM0510.jsp";
                    </script>
                </logic:notEqual>
            </logic:equal>
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
        </form>
    </body>
</html:html>
