<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html>
    <head>
        <title>SVN管理システム</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body topmargin="0">
        <form>
            <iframe id="mainFrame" hidefocus="true" src="<%=request.getContextPath()%>/SVNM0110.do" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" style="height:608px;width:100%;"></iframe>
        </form>
    </body>
</html:html>