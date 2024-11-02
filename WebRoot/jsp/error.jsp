<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
    <meta http-equiv="Content-Type" content="text/html;">
    <LINK rel="stylesheet" href="<%=request.getContextPath()%>/include/body.css" type="text/css">
    <title></title>

    <script type="text/javascript" src="include/FBSE.js"></script>
    <script language="JavaScript" type="text/JavaScript">
function jumpPage(){
if(top!=null&&top.document.getElementById("mainFrame")!=null){
    top.document.getElementById("mainFrame").src="<%=request.getContextPath()%>/jsp/jumpError.jsp";
}else if(top.top!=null&&top.top.document.getElementById("mainFrame")!=null){
    top.top.document.getElementById("mainFrame").src="<%=request.getContextPath()%>/jsp/jumpError.jsp";
}else{
    top.location="<%=request.getContextPath()%>";
}
}
</script>
</head>
<body onload="jumpPage();" >
</body>
</html:html>
