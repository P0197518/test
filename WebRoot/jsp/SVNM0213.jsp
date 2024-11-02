<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
    <head>
        <title>SVN管理システム</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/calendar-win2k-1.css" type="text/css" media="screen">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-en.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-setup.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/commonCheck.js"></script>
        <script type="text/javascript">
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
            // 確認ボタンを押下することの処理
            function kakuTei() {
                var message;
                if(document.forms[0].projectNo.value==""){
                    message="このプロジェクトを新規しますか？";
                } else {
                    message="このプロジェクトを更新しますか？";
                }
                if(confirm(message)){
                    document.forms[0].pageId.value="SVNM0213,ok";
                    document.forms[0].submit();
                }
            }
            // 戻るボタンを押下することの処理
            function modoRu() {
                document.forms[0].pageId.value="SVNM0213,return";
            }
        </script>
    </head>
    <body style="margin-top: 0;">
        <html:form action="SVNM0213.do">
            <div>
                <table cellspacing="0" cellpadding="0" width="100%" border="0">
                    <tr>
                        <td style="height: 13px" colspan="2" class="td_font">
                            <label id="lblMsg" style="color: red;font-family: ＭＳ Ｐゴシック">
                                <html:errors/>
                            </label>
                        </td>
                    </tr>
                </table>
                <table cellspacing="1" cellpadding="3" width="70%" border="0" style="margin-left:0;" class="T01">
                    <tr class="TR01">
                        <th class="TH01" style="width: 30%; height:29px" >
                            <strong><span style="font-size: 10pt">SVNフォルダ名：</span></strong>
                        </th>
                        <td class="TD02" style="width: 80%">
                            <html:hidden property="svnFolderName" write="true"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">プロジェクト名：</span></strong>
                        </th>
                        <td class="TD02">
                            <html:hidden property="projectName" write="true"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">ＰＪ責任者：</span></strong>
                        </th>
                        <td class="TD02">
                            <html:hidden property="pjMaster" write="true"/>
                            <html:hidden property="pjMasterNo"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">ＰＪリーダ：</span></strong>
                        </th>
                        <td class="TD02">
                               <html:hidden property="pjLeader" write="true"/>
                               <html:hidden property="pjLeaderNo"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">プロジェクト期間：</span></strong>
                        </th>
                        <td class="TD02">
                            <html:hidden property="pjStartDate" write="true"/> ～ <html:hidden property="pjEndDate" write="true"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">フォルダ移動日：</span></strong>
                        </th>
                        <td class="TD02">
                            <html:hidden property="pjRemoveDate" write="true"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">バックアップ間隔：</span></strong>
                        </th>
                        <td class="TD02">
                            <html:hidden property="pjBackupTime" write="true"/><logic:notEmpty name="SVNM0213Bean" property="pjBackupTime">日</logic:notEmpty>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">メンバー</span><span style="font-size: 10pt">：</span></strong>
                        </th>
                        <td class="TD02">
                            <html:hidden property="member" write="true"/>
                            <html:hidden property="memberNo"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01" style="height:29px">
                            <strong><span style="font-size: 10pt">コメント：</span></strong>
                        </th>
                        <td class="TD02">
                            <html:hidden property="comment" write="true"/>
                        </td>
                    </tr>
                </table>
                <table cellpadding="3" border="0" style="margin-left:-6">
                    <tr>
                        <td>
                            <html:button property="btKakuNin" styleClass="BUTTON01" value=" 確  認 " onclick="kakuTei();"/>
                        </td>
                        <td>
                            <html:submit styleClass="BUTTON01" value=" 戻  る " onclick="modoRu();" />
                        </td>
                    </tr>
                </table>
            </div>
            <html:hidden property="projectNo"/>
            <html:hidden property="updateDate"/>
            <html:hidden property="pageId" value="SVNM0213"/>
            <bean:write name="SVNM0213Bean" property="hidError" filter="false"/>
        </html:form>
    </body>
</html:html>