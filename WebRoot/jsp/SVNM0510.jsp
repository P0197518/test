<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
  <head>
    <title>SVN管理システム</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <link href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" rel="stylesheet" />
  </head>

<body style="margin-top: 0;">
    <table>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td><a style="color:#0000FF;font-size:15;text-decoration:underline" href="<%=request.getContextPath()%>/SVNM0410.do">従業員マスタ</a></td>
            <td>&nbsp;&nbsp;</td>
            <td><a style="color:#0000FF;font-size:15;text-decoration:underline" href="<%=request.getContextPath()%>/SVNM0310.do">権限マスタ</a></td>
            <td>&nbsp;&nbsp;</td>
            <logic:equal name="masterPrivilege" value="2">
                <td><a style="color:#0000FF;font-size:15;text-decoration:underline" href="<%=request.getContextPath()%>/SVNM0511.do">SVNパスワード定期維持</a></td>
            </logic:equal>
        </tr>
    </table>
</body>
</html:html>