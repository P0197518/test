<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fbse" uri="/WEB-INF/fbse-page.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<html:html>
<head>
    <title>プロジェクト一覧</title>
    <link href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/calendar-win2k-1.css" type="text/css" media="screen" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-en.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-setup.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/commonCheck.js"></script>
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
    //クリア
    function kuria(){
        // SVNフォルダー名
        $("svnFolderNameSql").value = "";
        // プロジェクト名
        $("projectNameSql").value = "";
        // 開始日
        $("pjStartDateSql").value = "";
        // 終了日
        $("pjEndDateSql").value = "";
        // ＰＪ責任者
        $("pjMasterNoSql").value = "";
        // ＰＪリーダ
        $("pjLeaderNoSql").value = "";
        // 状態
        $("delflgSql").value = "";
        // メンバー
        $("memberSql").value = "";
        $("lblMsg").innerHTML = "";
        $("svnFolderName").value = "";
        $("projectName").value = "";
        $("pjStartDate").value = "";
        $("pjEndDate").value = "";
        $("pjLeader").value = "";
        $("pjLeaderNo").value = "";
        $("pjMaster").value = "";
        $("pjMasterNo").value = "";
        $("member").value = "";
        $("memberNo").value = "";
        if($("delflg").options.length > 0) {
            $("delflg").options[0].selected = "selected";
        }
    }
    // 操作状態の保存。
    function sousa(pItem) {
        $("sousaStart").value = pItem;
        document.forms[0].submit();
    }
    // 検索ボタンを押下する。
    function kensaku(pItem) {
        // SVNフォルダー名
        $("svnFolderNameSql").value = $("svnFolderName").value;
        // プロジェクト名
        $("projectNameSql").value = $("projectName").value;
        // 開始日
        $("pjStartDateSql").value = $("pjStartDate").value;
        // 終了日
        $("pjEndDateSql").value = $("pjEndDate").value;
        // ＰＪ責任者
        $("pjMasterNoSql").value = $("pjMasterNo").value;
        // ＰＪリーダ
        $("pjLeaderNoSql").value = $("pjLeaderNo").value;
        // 状態
        $("delflgSql").value = $("delflg").value;
        // メンバー
        $("memberSql").value = $("memberNo").value;
        $("sousaStart").value = pItem;
    }

    function fukuGen(pId,pName,pUpdateTime){
        $("projectNo").value = pId;
        $("deleteProjectName").value = pName;
        $("updateData").value = pUpdateTime;
        if(confirm("SVNサーバで処理失敗するファイルを処理しましたか？")){
            $("sousaStart").value="fukuGen";
            document.forms[0].submit();
        }
    }

    // 削除或いは修復アンカーをクリック。
    function delOrupd(pItem, pId, pName, pUpdateTime, pFolder) {
        $("pageNo").value=$("drpPage").value;
        $("projectNo").value = pId;
        $("deleteProjectName").value = pName;
        $("updateData").value = pUpdateTime;
        $("updateSvnFolder").value = pFolder;
        if(pItem == "delete") {
            if(confirm("削除しますか？"))　{
                $("sousaStart").value = pItem;
                document.forms[0].submit();
            }
        }
        else if(pItem == "recovery") {
            if(confirm("修復しますか？"))　{
                $("sousaStart").value = pItem;
                document.forms[0].submit();
            }
        }
    }
    // 編集
    function edit(pId, pUpdateTime) {
        $("pageNo").value=$("drpPage").value;
        $("sousaStart").value = "edit";
        $("projectNo").value = pId;
        $("updateData").value = pUpdateTime;
        document.forms[0].submit();
    }
    // ポップアップ画面をポップアップする。
    function popuapuOpen() {
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
    // 漏出テキストの処理。
    // tableName:      テーブル名
    // headCell:       テーブルヘッドのセル名
    // startDivName:   DIV名
    function setSize(tableName, headCell, startDivName, w) {
        if(document.getElementById(tableName) != null) {
            var windowWidth = parseInt(document.body.clientWidth);
            var myTagWidth = parseFloat($(headCell).width) * 0.01;
            var lineNumber = document.getElementById(tableName).rows.length - 1;
            for(var i =0; i<lineNumber; i++){
                $(startDivName+(i+1)).style.width = parseInt(windowWidth * myTagWidth) - w;
            }
        }
    }
    // 漏出テキストの処理。
    function start() {
        setSize('projectData', 'memberWidth', 'member', 7);
        setSize('projectData', 'commentWidth', 'comment', 7);
        setSize('projectData', 'svnFolderNameWidth', 'svnFolderName', 7);
        setSize('projectData', 'projectNameWidth', 'projectName', 7);
        setSize('projectData', 'pjMasterWidth', 'pjMaster', 7);
        setSize('projectData', 'pjLeaderWidth', 'pjLeader', 7);
    }
    </script>
</head>
<body onresize="start()" onload="start()">
    <!-- フォーム -->
    <html:form action="SVNM0210.do" method="post">
    <!-- 画面コード -->
    <html:hidden name="SVNM0210Bean" property="gamenId" value="SVNM0210"/>
    <!-- 操作状態 -->
    <html:hidden name="SVNM0210Bean" property="sousaStart"/>
    <!-- 更新日 -->
    <html:hidden name="SVNM0210Bean" property="updateData"/>
    <!-- プロジェクト番号 -->
    <html:hidden name="SVNM0210Bean" property="projectNo"/>
    <!-- 削除プロジェクト名 -->
    <html:hidden name="SVNM0210Bean" property="deleteProjectName"/>
    <!-- SVNフォルダー名 -->
    <html:hidden name="SVNM0210Bean" property="updateSvnFolder"/>
    <table cellspacing="0" cellpadding="0" width="98%" border="0">
        <tr>
            <td class="td_font" style="height: 13px">
                <label id="lblMsg" style="color: red;font-family: ＭＳ Ｐゴシック;">
                    <!-- メッセージ -->
                    <html:errors/>
                </label>
            </td>
        </tr>
    </table>
    <table border="0" cellspacing="1" cellpadding="3" class="TB05" style="width: 98%">
        <tr>
            <td class="TD03" style="width: 11%">
                &nbsp;SVNフォルダ名：</td>
            <td class="TD02" style="width: 19%">
                <!-- SVNフォルダー名 -->
                <html:text name="SVNM0210Bean" property="svnFolderName" style="width: 95%;" styleClass="TEXT01" maxlength="20"/>
                <html:hidden name="SVNM0210Bean" property="svnFolderNameSql"/>
            </td>
            <td class="TD03" style="width: 11%">
                &nbsp;プロジェクト名：</td>
            <td class="TD02" style="width: 19%">
                <!-- プロジェクト名 -->
                <html:text name="SVNM0210Bean" property="projectName" style="width: 95%;" styleClass="TEXT01" maxlength="20"/>
                <html:hidden name="SVNM0210Bean" property="projectNameSql"/>
            </td>
            <td class="TD03" align="left" style="width: 11%">
                &nbsp;プロジェクト期間 ：
            </td>
            <td class="TD02" colspan="3" style="width: 29%">
                <!-- 開始日 -->
                <html:text tabindex="-1" name="SVNM0210Bean" property="pjStartDate" style="width: 25%" styleClass="TEXT01" maxlength="20" readonly="true"/>
                <html:hidden name="SVNM0210Bean" property="pjStartDateSql"/><a href="#"><img name="imgFirstTime" class="I02" src="<%=request.getContextPath()%>/img/calendar.gif"></a> ～
                <!-- 終了日 -->
                <html:text tabindex="-1" name="SVNM0210Bean" property="pjEndDate" style="width: 25%" styleClass="TEXT01" maxlength="20" readonly="true"/>
                <html:hidden name="SVNM0210Bean" property="pjEndDateSql"/><a href="#"><img name="imgLastTime" class="I02" src="<%=request.getContextPath()%>/img/calendar.gif"></a>
            </td>
        </tr>
        <tr>
            <td class="TD03" style="width: 11%; height: 20px;">
                &nbsp;ＰＪ責任者：</td>
            <td class="TD02" style="width: 19%; height: 20px;">
                <!-- ＰＪ責任者 -->
                <html:text tabindex="-1" name="SVNM0210Bean" property="pjMaster" style="width: 60%;" styleClass="TEXT01" maxlength="10" readonly="true"/>
                <a href="#" onclick="popuapuOpen();"><img src="<%=request.getContextPath()%>/img/search.gif" class="I02"/></a>
                <html:hidden name="SVNM0210Bean" property="pjMasterNo"/>
                <html:hidden name="SVNM0210Bean" property="pjMasterNoSql"/>
            </td>
            <td class="TD03" style="width: 11%; height: 20px;">
                &nbsp;ＰＪリーダ：</td>
            <td class="TD02" style="width: 19%; height: 20px;">
                <!-- ＰＪリーダ -->
                <html:text tabindex="-1" name="SVNM0210Bean" property="pjLeader" style="width: 60%;" styleClass="TEXT01" maxlength="10" readonly="true"/>
                <a href="#" onclick="popuapuOpen();"><img src="<%=request.getContextPath()%>/img/search.gif" class="I02"/></a>
                <html:hidden name="SVNM0210Bean" property="pjLeaderNoSql"/>
                <html:hidden name="SVNM0210Bean" property="pjLeaderNo"/>
            </td>
            <td class="TD03" style="width: 5%; height: 20px;">
                &nbsp;プロジェクト状態：</td>
            <td class="TD02" style="width: 8%; height: 20px;">
                <html:select name="SVNM0210Bean" property="delflg" style="background-color: #FFFFE0;height:20px;">
                    <logic:present name="SVNM0210Bean" property="selectBean" scope="request">
                        <html:optionsCollection name="SVNM0210Bean" property="selectBean" label="name" value="code"/>
                    </logic:present>
                </html:select>
                <html:hidden name="SVNM0210Bean" property="delflgSql"/>
            </td>
        </tr>
        <tr>
            <td class="TD03" style="width: 11%">
                &nbsp;メンバー：</td>
            <td class="TD02" style="width: 16%" colspan="7">
                <html:text tabindex="-1" name="SVNM0210Bean" property="member" style="width: 90%" styleClass="TEXT01" maxlength="10" readonly="true"/>
                <a href="#" onclick="popuapuOpen();">
                    <img src="<%=request.getContextPath()%>/img/search.gif" class="I02"/>
                </a>
                <html:hidden name="SVNM0210Bean" property="memberNo"/>
                <html:hidden name="SVNM0210Bean" property="memberSql"/>
            </td>
        </tr>
    </table>
    <table style="margin-left: -6;" cellpadding="3">
        <tr>
            <td>
                <html:submit property="btnSelect" styleClass="BUTTON01" value=" 検  索 " onclick="kensaku('select');"></html:submit>
            </td>
            <td>
                <html:button property="btnKuria" styleClass="BUTTON01" value=" クリア " onclick="kuria();"></html:button>
            </td>
            <td>
                <logic:notEmpty name="kanri" scope="request">
                    <html:button property="btnInsert" styleClass="BUTTON01" value="新  規" onclick="sousa('add');"></html:button>
                </logic:notEmpty>
            </td>
        </tr>
    </table>
    <logic:present name="result" scope="request">
    <div id="meisai">
    <fbse:page name="result" property="PROJECTNO,SVNFOLDERNAME,PROJECTNAME,PJMASTER,PJLEADER,MEMBER,PJSTARTDATE,PJENDDATE,COMMENT,UPDATEDATE,DEALFLG" pageSize="13">
        <table cellspacing="0" cellpadding="0" width="98%" border="0">
            <tr>
                <td align="left">
                    <table id="projectData" class="TB03" width="100%" border="0" cellspacing="1px" cellpadding="0">
                        <tr bgcolor="#d2d3ff" height="25px">
                            <th width="4%" scope="col">
                                <span class="th_font">番号</span></th>
                            <th id="svnFolderNameWidth" width="10%" scope="col">
                                <span class="th_font">SVNフォルダ名</span></th>
                            <th id="projectNameWidth" width="13%" scope="col">
                                <span class="th_font">プロジェクト名</span></th>
                            <th id="pjMasterWidth" width="7%" scope="col">
                                <span class="th_font">ＰＪ責任者</span></th>
                            <th id="pjLeaderWidth" width="7%" scope="col">
                                <span class="th_font">ＰＪリーダ</span></th>
                            <th id="memberWidth" width="20%" scope="col">
                                <span class="th_font">メンバー</span></th>
                            <th width="8%" scope="col">
                                <span class="th_font">開始時間</span></th>
                            <th width="8%" scope="col">
                                <span class="th_font">終了時間</span></th>
                            <th id="commentWidth" width="12%" scope="col">
                                <span class="th_font">コメント</span></th>
                            <th width="7%" scope="col">
                                <span class="th_font">処理状態</span></th>
                            <th width="4%" scope="col">
                                <span class="th_font">操作</span></th>
                        </tr>
                            <%int i = 1;
                              String pInfo = "";
                            %>
                            <logic:iterate id="result" name="result">
                                <bean:define id="projectNo" name="result" property="PROJECTNO" />
                                <bean:define id="updateDate" name="result" property="UPDATEDATE"/>
                                <tr height="20px" <%if (i % 2 == 0) { %> style="background-color: #e9f5f5" <%} else { %> style="background-color: #FFFFFF" <% }%> ><%i++;%>
                                <td align="center" class="td_font" ><bean:write name="result" property="No"/></td>
                                <td align="left" class="td_font" >
                                    <div id="svnFolderName<bean:write name="result" property="No"/>" title="<bean:write name="result" property="SVNFOLDERNAME" />"  style="overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                        <logic:empty name="kanri" scope="request">
                                            <bean:write name="result" property="SVNFOLDERNAME" />
                                        </logic:empty>
                                        <logic:notEmpty name="kanri" scope="request">
                                            <logic:equal name="result" property="DEALFLG" value="正常">
	                                            <%pInfo = projectNo + "','" + updateDate; %>
	                                            <logic:equal name="result" property="DELFLG" value="3">
	                                                <bean:write name="result" property="SVNFOLDERNAME" />
	                                            </logic:equal>
	                                            <logic:empty name="delete" scope="request">
	                                                <bean:write name="result" property="SVNFOLDERNAME" />
	                                            </logic:empty>
	                                            <logic:notEmpty name="delete" scope="request">
	                                                <logic:notEqual name="result" property="DELFLG" value="3">
	                                                    <a href="javascript:edit('<%=pInfo %>')"><bean:write name="result" property="SVNFOLDERNAME" /></a>
	                                                </logic:notEqual>
	                                            </logic:notEmpty>
                                            </logic:equal>
                                            <logic:notEqual name="result" property="DEALFLG" value="正常">
                                                <bean:write name="result" property="SVNFOLDERNAME" />
                                            </logic:notEqual>
                                        </logic:notEmpty>
                                    </div>
                                </td>
                                <td align="left" class="td_font" >
                                    <div id="projectName<bean:write name="result" property="No"/>" title="<bean:write name="result" property="PROJECTNAME" />"  style="overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                        <bean:write name="result" property="PROJECTNAME" />
                                    </div>
                                </td>
                                <td align="left" class="td_font" >
                                    <div id="pjMaster<bean:write name="result" property="No"/>" title="<bean:write name="result" property="PJMASTER" />"  style="overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                        <bean:write name="result" property="PJMASTER" />
                                    </div>
                                </td>
                                <td align="left" class="td_font" >
                                    <div id="pjLeader<bean:write name="result" property="No"/>" title="<bean:write name="result" property="PJLEADER" />"  style="overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                        <bean:write name="result" property="PJLEADER" />
                                    </div>
                                </td>
                                <td align="left" class="td_font" >
                                    <div id="member<bean:write name="result" property="No"/>" title="<bean:write name="result" property="MEMBER" />"  style="overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                         <bean:write name="result" property="MEMBER" />
                                    </div>
                                </td>
                                <td align="center" class="td_font" ><bean:write name="result" property="PJSTARTDATE" /></td>
                                <td align="center" class="td_font" ><bean:write name="result" property="PJENDDATE" /></td>
                                <td align="left" class="td_font" >
                                    <div id="comment<bean:write name="result" property="No"/>" title="<bean:write name="result" property="COMMENT" />" style="overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                         <bean:write name="result" property="COMMENT" />
                                    </div>
                                </td>
                                <td align="center" class="td_font" >
                                    <bean:write name="result" property="DEALFLG" />
                                </td>
                                <td align="center" class="td_font" style="height: 20px">
                                <bean:define id="projectName" name="result" property="PROJECTNAME" />
                                <bean:define id="svnFolderName" name="result" property="SVNFOLDERNAME" />
                                <logic:notEmpty name="kanri" scope="request">
                                    <logic:equal name="result" property="DEALFLG" value="正常">
	                                    <logic:notEmpty name="delete" scope="request">
	                                        <%pInfo = projectNo + "','" + projectName + "','" + updateDate + "','" + svnFolderName; %>
	                                        <a href="javascript:delOrupd('delete','<%=pInfo %>');" >削除</a>
	                                    </logic:notEmpty>
	                                    <logic:empty name="delete" scope="request">
	                                        <%pInfo = projectNo + "','" + projectName + "','" + updateDate + "','" + svnFolderName; %>
	                                        <a href="javascript:delOrupd('recovery','<%=pInfo %>');" >修復</a>
	                                    </logic:empty>
	                                </logic:equal>
	                                <logic:equal name="result" property="DEALFLG" value="処理失敗">
	                                   <%pInfo = projectNo + "','" + projectName + "','" + updateDate + "','" + svnFolderName; %>
                                       <a href="javascript:fukuGen('<%=projectNo+"','"+ projectName +"','"+ updateDate%>');" >復元</a>
	                                </logic:equal>
                                </logic:notEmpty>
                                </td>
                                 </tr>
                            </logic:iterate>
                    </table>
                </td>
            </tr>
        </table>
    </fbse:page>
    </div>
    </logic:present>
    </html:form>
</body>
<script language="JavaScript" type="text/JavaScript">
    Calendar.setup(
    {
      inputField  : "pjStartDate",
      ifFormat    : "%Y/%m/%d",
      button      : "imgFirstTime"
    }
    );
    Calendar.setup(
    {
      inputField  : "pjEndDate",
      ifFormat    : "%Y/%m/%d",
      button      : "imgLastTime"
    }
    );
</script>
</html:html>