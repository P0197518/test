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
            //確定ボタンを押下することの処理
            function kakuTei() {
                document.forms[0].pageId.value="SVNM0212,ok";
            }
            //戻るボタンを押下することの処理
            function modoRu() {
                document.forms[0].pageId.value="SVNM0212,return";
            }
            //虫眼鏡を押下すると、SVNM0413従業員検索（ポップアップ）画面をポップアップする。
            function openChild() {
                var child=window.open('SVNM0413.do',null,'top=100,left=300,width=530,height=560');
                var flag=true;
                while(flag){
                    try{
                        while(child.document.forms[0]==null){}
                        if(document.forms[0].pjMasterNo.value!=""){
                            child.document.SVNM0413Bean.pjMaster.value=document.forms[0].pjMasterNo.value+
                            "                "+document.forms[0].pjMaster.value;
                            child.document.SVNM0413Bean.pjMasterNo.value=document.forms[0].pjMasterNo.value+
                            ","+document.forms[0].pjMaster.value;
                        }
                        if(document.forms[0].pjLeaderNo.value!=""){
                            child.document.SVNM0413Bean.pjLeader.value=document.forms[0].pjLeaderNo.value+
                            "                "+document.forms[0].pjLeader.value;
                            child.document.SVNM0413Bean.pjLeaderNo.value=document.forms[0].pjLeaderNo.value+
                            ","+document.forms[0].pjLeader.value;
                        }
                        if(document.forms[0].memberNo.value!=""){
                            var memberNo=document.forms[0].memberNo.value.split(",");
                            var member=document.forms[0].member.value.split("、");
                            var sentakuMember="";
                            for(var i=0;i<memberNo.length;i++){
                                sentakuMember+=memberNo[i];
                                sentakuMember+=",";
                                sentakuMember+=member[i];
                                if(i<memberNo.length-1){
                                    sentakuMember+=","
                                }
                            }
                            child.document.forms[0].sentakuMember.value=sentakuMember;
                            child.memberStart();
                        }
                        flag=false;
                    }catch(e){}
                }
            }
            //プロジェクト完了日を選択すると、フォルダ移動日を設定する。
            function setRemoveDate(){
                var date=new Date(document.forms[0].pjEndDate.value);
                date=new Date(date.getTime()+<logic:notEmpty name="leaveDays"><bean:write name="leaveDays" /></logic:notEmpty><logic:empty name="leaveDays">0</logic:empty>*24*60*60*1000);
                var strDate=date.getYear();
                strDate+=date.getMonth()+1<10?"/0"+(date.getMonth()+1):"/"+(date.getMonth()+1);
                strDate+=date.getDate()<10?"/0"+date.getDate():"/"+date.getDate();
                document.forms[0].pjRemoveDate.value=strDate;
            }
        </script>
    </head>
    <body style="margin-top: 0;">
        <html:form action="SVNM0212.do">
            <div>
                <!--メッセージ-->
                <table cellspacing="0" cellpadding="0" width="100%" border="0">
                    <tr>
                        <td style="height: 13px" colspan="2" class="td_font">
                            <label id="lblMsg" style="color: red;font-family: ＭＳ Ｐゴシック">
                                <html:errors/>
                            </label>
                        </td>
                    </tr>
                </table>
                <table cellspacing="1" cellpadding="3" width="70%" border="0" style="margin-left:0;" bgcolor="#999999">
                    <tr class="TR01">
                        <th class="TH01" style="width: 30%;" >
                            <label>
                                <strong>
                                    <span style="font-size: 10pt">SVNフォルダ名：</span>
                                    <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック;font-size: 10pt">*</span>
                                </strong>
                            </label>
                        </th>
                        <td class="TD02" style="width: 80%">
                            <html:text property="svnFolderName" style="width: 42%" styleClass="TEXT01" maxlength="20" />
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <label>
                                <strong>
                                    <span style="font-size: 10pt">プロジェクト名：</span>
                                    <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                                </strong>
                            </label>
                        </th>
                        <td class="TD02">
                            <html:text property="projectName" style="width: 42%" styleClass="TEXT01" maxlength="20" />
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <label>
                                <strong>
                                <span style="font-size: 10pt">ＰＪ責任者：</span>
                                <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック;font-size: 10pt">*</span>
                                </strong>
                            </label>
                        </th>
                        <td class="TD02">
                            <html:text property="pjMaster" style="width: 17%" styleClass="TEXT01" maxlength="10" readonly="true" tabindex="-1" />
                            <html:link href="#" onclick="openChild();">
                                <html:img src='<%=request.getContextPath()+"/img/search.gif"%>' styleClass="I02"/>
                            </html:link>
                            <html:hidden property="pjMasterNo"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <label>
                                <strong>
                                    <span style="font-size: 10pt">ＰＪリーダ：</span>
                                    <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック;font-size: 10pt">*</span>
                                </strong>
                            </label>
                        </th>
                        <td class="TD02">
                            <html:text property="pjLeader" style="width: 17%" styleClass="TEXT01" maxlength="10" readonly="true" tabindex="-1" />
                            <html:link href="#" onclick="openChild();">
                                <html:img src='<%=request.getContextPath()+"/img/search.gif"%>' styleClass="I02"/>
                            </html:link>
                            <html:hidden property="pjLeaderNo"/>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <label>
                                <strong>
                                    <span style="font-size: 10pt">プロジェクト期間：</span>
                                    <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                                </strong>
                            </label>
                        </th>
                        <td class="TD02">
                            <html:text property="pjStartDate" styleClass="TEXT01" style="width: 15%" readonly="true" tabindex="-1" />
                            <%String tempStr="/img/calendar.gif\" name=\"imgStartDate"; %>
                            <html:link href="#" ><html:img styleClass="I02" src='<%=request.getContextPath() + tempStr%>' /></html:link>
                                ～
                               <html:text property="pjEndDate" styleClass="TEXT01" style="width: 15%" readonly="true" onchange="setRemoveDate();" tabindex="-1" />
                               <%tempStr="/img/calendar.gif\" name=\"imgEndDate"; %>
                            <html:link href="#" ><html:img styleClass="I02" src='<%=request.getContextPath() + tempStr%>' /></html:link>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <label>
                                <strong>
                                    <span style="font-size: 10pt">フォルダ移動日：</span>
                                    <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                                </strong>
                            </label>
                        </th>
                        <td class="TD02">
                            <html:text property="pjRemoveDate" styleClass="TEXT01" style="width: 15%" readonly="true" tabindex="-1" />
                            <%tempStr="/img/calendar.gif\" name=\"imgRemoveDate"; %>
                            <html:link href="#" ><html:img styleClass="I02" src='<%=request.getContextPath() + tempStr%>' /></html:link>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <label>
                                <strong>
                                    <span style="font-size: 10pt">バックアップ間隔：&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                </strong>
                            </label>
                        </th>
                        <td class="TD02">
                            <html:text property="pjBackupTime" style="width:7%;ime-mode: disabled" styleClass="TEXT01" maxlength="3"
                                   onkeypress="return event.keyCode>=48&&event.keyCode<=57"/>&nbsp;日
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <strong>
                                <span style="font-size: 10pt">メンバー</span>
                                <span style="font-size: 10pt">：</span>
                                <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                            </strong>
                        </th>
                        <td class="TD02">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td width="70%"><html:textarea property="member" styleClass="TEXTAREA01" style="height:80px;width:99%" readonly="true" tabindex="-1" />
                                    </td>
                                    <td valign="top">
                                        <html:link href="#" onclick="openChild();">
                                            <html:img src='<%=request.getContextPath()+"/img/search.gif"%>' styleClass="I02"/>
                                        </html:link>
                                        <html:hidden property="memberNo" />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <th class="TH01">
                            <label>
                                <strong><span style="font-size: 10pt">コメント：&nbsp;&nbsp;&nbsp;&nbsp;</span></strong>
                            </label>
                        </th>
                        <td class="TD02">
                            <html:textarea property="comment" styleClass="TEXTAREA01" style="height:80px;width:69.5%" />
                        </td>
                    </tr>
                </table>
                <table cellpadding="3" border="0" style="margin-left:-6">
                    <tr>
                        <td>
                            <html:submit styleClass="BUTTON01" value=" 確  定 " onclick="kakuTei();" />
                        </td>
                        <td>
                            <html:submit styleClass="BUTTON01" value=" 戻  る " onclick="modoRu();"/>
                        </td>
                    </tr>
                </table>
            </div>
            <html:hidden property="projectNo"/>
            <html:hidden property="updateDate"/>
            <html:hidden property="pageId" value="SVNM0212"/>
            <bean:write name="SVNM0212Bean" property="hidError" filter="false"/>
        </html:form>
    </body>
    <script type="text/javascript">
        document.forms[0].pjBackupTime.onpaste=function(){return !clipboardData.getData('text').match(/\D/);};
        document.forms[0].pjBackupTime.ondragenter=function(){return false;};
        Calendar.setup(
        {
            inputField  : "pjStartDate",
            ifFormat    : "%Y/%m/%d",
            button      : "imgStartDate"
        }
        );  
            Calendar.setup(
        {
            inputField  : "pjEndDate",
            ifFormat    : "%Y/%m/%d",
            button      : "imgEndDate"
        }
        );
        Calendar.setup(
        {
            inputField  : "pjRemoveDate",
            ifFormat    : "%Y/%m/%d",
            button      : "imgRemoveDate"
        }
        );
    </script>
</html:html>