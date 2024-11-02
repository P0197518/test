<%@ page language="java" pageEncoding="Shift_JIS"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>SVNä«óùÉVÉXÉeÉÄ</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language="JavaScript" type="text/JavaScript">
      function LogOff() {
if(top!=null&&top.document.getElementById("mainFrame")!=null){
    top.document.getElementById("mainFrame").src="<%=request.getContextPath()%>/SVNM0110.do";
}else if(top.top!=null&&top.top.document.getElementById("mainFrame")!=null){
    top.top.document.getElementById("mainFrame").src="<%=request.getContextPath()%>/SVNM0110.do";
}else{
    top.location="<%=request.getContextPath()%>";
}
      }
    </script>
  </head>
  
  <body onload="LogOff()">
  </body>
</html:html>
