<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@  taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<html:html>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script language="javascript" type="text/javascript">
        function startclock () {
        showtime();
        setInterval("showtime()",1000); 
        } 
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
        document.getElementById("lblTime").innerHTML = " さん ようこそ "+timeValue;
        }
        var pageId="SVNM0610";
        function back() {
            var tempArray=pageId.split(",");
            for(var i=0;i<tempArray.length;i++){
                document.getElementById(tempArray[i]+"link").style.display="";
                document.getElementById(tempArray[i]+"label").style.display="none";
            }
        }
        function selected(selectedValue){
            back();
            document.getElementById(selectedValue+"link").style.display="none"
            document.getElementById(selectedValue+"label").style.display=""
        }
    </script>

    <body style="margin-top: 0;" onload="startclock();">
        <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td rowspan="3">
                    <img alt="" src="<%=request.getContextPath()%>/img/logo.jpg" />
                </td>
            </tr>
            <tr>
                <td  align="right"><label id="userName" class="th_font" ><logic:notEmpty name="sysUserName"><bean:write name="sysUserName" /></logic:notEmpty></label><label id="lblTime" class="th_font"></label></td>
            </tr>
            <tr>
                <td align="right">
                    <strong><span style="font-size: 14pt">SVN管理システム</span></strong>
                </td>
            </tr>
        </table>
            <HR align=left width="100%" color="#DA251A" style="margin-top:-12">
        <table style="margin-top:-8">
            <tr>
                <td style="vertical-align: bottom" align="left" height =15px width="90%">
                    <logic:notEqual name="pjPrivilege" value="0">
                        <span id="SVNM0210link" style="display:none" onclick ="selected('SVNM0210');"><a id="lnk0210" href="<%=request.getContextPath()%>/SVNM0210.do" target="TABLE_align" style="color:#0000FF;font-size:15;text-decoration:underline" id="ahref2">プロジェクト管理</a>&nbsp;&nbsp;</span>
                        <span id="SVNM0210label" style="font-size:16" >プロジェクト管理&nbsp;&nbsp;</span>
                        <script language="javascript">pageId+=",SVNM0210";</script>
                    </logic:notEqual>
                    <logic:notEqual name="masterPrivilege" value="0">
                        <span id="SVNM0510link" onclick ="selected('SVNM0510');"><a id="lnk0510" href="<%=request.getContextPath()%>/jsp/SVNM0510.jsp" target="TABLE_align" style="color:#0000FF;font-size:15;text-decoration:underline" id="ahref6">マスタメンテ</a>&nbsp;&nbsp;</span>
                        <span id="SVNM0510label" style="display:none;font-size:16" >マスタメンテ&nbsp;&nbsp;</span>
                        <script language="javascript">pageId+=",SVNM0510";</script>
                    </logic:notEqual>
                    <span id="SVNM0610link" onclick ="selected('SVNM0610');"><a id="lnk0610" href="<%=request.getContextPath()%>/SVNM0610.do" target="TABLE_align" style="color:#0000FF;font-size:15;text-decoration:underline" id="ahref5">パスワード修正</a>&nbsp;&nbsp;</span>
                    <span id="SVNM0610label" style="display:none;font-size:16" >パスワード修正&nbsp;&nbsp;</span>
                    <a href="<%=request.getContextPath()%>/SVNM0110.do" target="_parent" style="color:#0000FF;font-size:15;text-decoration:underline">ログオフ</a>
                    <script type="text/javascript">
                        if(document.getElementById('SVNM0210link')==null){
                            selected('SVNM0510');
                        }
                        var path=top.frames['mainFrame'].frames['TABLE_align'].location.href;
                        path=path.substring(path.length-11,path.length-5);
                        if(path=='SVNM04'||path=='SVNM03'){
                            selected('SVNM0510');
                        }
                    </script>
                </td>
            </tr>
        </table>
    </body>
</html:html>