<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html:html>
    <head>
        <title>従業員検索</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/svnm_css.css" type="text/css">
        <script type="text/javascript" src="js/commonCheck.js"></script>
        <script language="javascript">
            var path=window.location.href;
            path=path.substring(path.length-4);
            if(path==".jsp"){
                location.href=location.href.substring(0,location.href.lastIndexOf("/jsp"));
            }
            var temp;
            window.onload=window_onload;
            function window_onload(){
                document.all.svnUserId.focus();
            }
            //メンバー追加ボタンを押下の処理
            function tuiKa(){
                $("lblMsg").innerHTML="";
                //データの重複をチェック
                multiCheck("member");
                //メンバーを追加
                for(var i=$("svnUser").length-1;i>=0;i--){
                    if($("svnUser")[i].selected){
                        $("member").add(new Option($("svnUser")[i].text,$("svnUser")[i].value));
                        $("svnUser").remove(i);
                    }
                }
                if(temp!=null){
                    $("svnUser").add(new Option(temp.text,temp.value));
                    temp=null;
                }
            }
            //メンバー削除ボタンを押下の処理
            function sakuJyo(){
                $("lblMsg").innerHTML="";
                //データの重複をチェック
                for(var i=$("member").length-1;i>=0;i--){
                        if($("member")[i].selected){
                            for(var j=0;j<$("svnUser").length;j++){
                                if($("member")[i].value==$("svnUser")[j].value){
                                    $("member").remove(i);
                                    break;
                                }
                            }
                        }
                }
                //メンバーを削除
                for(var i=$("member").length-1;i>=0;i--){
                    if($("member")[i].selected){
                        $("svnUser").add(new Option($("member")[i].text,$("member")[i].value));
                        $("member").remove(i);
                    }
                }
            }
            //確定ボタンを押下の処理
            function kakuTei(){
                //メンバーを設定
                window.opener.document.forms[0].member.value="";
                window.opener.document.forms[0].memberNo.value="";
                for(var i=0;i<$("member").length;i++){
                    window.opener.document.forms[0].member.value+=$("member")[i].value.split(",")[1];
                    window.opener.document.forms[0].memberNo.value+=$("member")[i].value.split(",")[0];
                    if(i<$("member").length-1){
                        window.opener.document.forms[0].member.value+="、";
                        window.opener.document.forms[0].memberNo.value+=",";
                    }
                }
                //PJ責任者を設定
                if($("pjMasterNo").value != "") {
                    window.opener.document.forms[0].pjMaster.value=$("pjMasterNo").value.split(",")[1];
                    window.opener.document.forms[0].pjMasterNo.value=$("pjMasterNo").value.split(",")[0];
                }else {
                    window.opener.document.forms[0].pjMaster.value="";
                    window.opener.document.forms[0].pjMasterNo.value="";
                }
                //PJリーダを設定
                if($("pjLeaderNo").value != "") {
                    window.opener.document.forms[0].pjLeader.value=$("pjLeaderNo").value.split(",")[1];
                    window.opener.document.forms[0].pjLeaderNo.value=$("pjLeaderNo").value.split(",")[0];
                }else {
                    window.opener.document.forms[0].pjLeader.value="";
                    window.opener.document.forms[0].pjLeaderNo.value="";
                }
                window.close();
            }
            //PJ責任者とPJリーダを追加ボタンの押下
            function tuiKaMaster(flag){
                var flg = 0;
                if(check(flag)){
                    var selectUser = $('svnUser')[$('svnUser').selectedIndex].value;
                    for(var i=0; i<$("member").length; i++){
                        if($("member")[i].value == selectUser){
                            flg = 1;
                            $("lblMsg").innerHTML="選択したユーザーはもうメンバーに選択されましたから、ほかのユーザーを選んでください。";
                            break;
                        }
                    }
                if(flg == 0) {
                    $("lblMsg").innerHTML="";
                    //データの重複をチェック
                    multiCheck(flag);
                    //PJ責任者を追加ボタンの押下
                    if(flag=="pjMaster"){
                        if($("pjMaster").value!=""){
                            $("svnUser").add(new Option($("pjMaster").value,$("pjMasterNo").value));
                        }
                        $("pjMaster").value=$('svnUser')[$('svnUser').selectedIndex].text;
                        $("pjMasterNo").value=$('svnUser')[$('svnUser').selectedIndex].value;
                        //PJリーダを追加ボタンの押下
                    }else{
                        if($("pjLeader").value!=""){
                            $("svnUser").add(new Option($("pjLeader").value,$("pjLeaderNo").value));
                        }
                        $("pjLeader").value=$('svnUser')[$('svnUser').selectedIndex].text;
                        $("pjLeaderNo").value=$('svnUser')[$('svnUser').selectedIndex].value;
                    }
                    $('svnUser').remove($('svnUser').selectedIndex);
                }
                 }
                
            }
            //PJ責任者とPJリーダを削除ボタンの押下
            function sakuJyoMaster(flag){
                $("lblMsg").innerHTML="";
                //データの重複をチェック
                multiCheck(flag);
                //PJ責任者を削除ボタンの押下
                if(flag=="pjMaster"&&$("pjMaster").value!=""){
                    flg = 0;
                    for(var i=0; i<$("svnUser").length; i++){
                        if($("svnUser")[i].value == $("pjMasterNo").value){
                            flg = 1;
                            break;
                        }
                    }
                    if(flg == 0) {
                        $("svnUser").add(new Option($("pjMaster").value,$("pjMasterNo").value));
                    }
                    $("pjMaster").value="";
                    $("pjMasterNo").value="";
                //PJリーダを削除ボタンの押下
                }else if(flag=="pjLeader"&&$("pjLeader").value!=""){
                    flg = 0;
                    for(var i=0; i<$("svnUser").length; i++){
                        if($("svnUser")[i].value == $("pjLeaderNo").value){
                            flg = 1;
                            break;
                        }
                    }
                    if(flg == 0) {
                        $("svnUser").add(new Option($("pjLeader").value,$("pjLeaderNo").value));
                    }
                    $("pjLeader").value="";
                    $("pjLeaderNo").value="";
                }
            }
            //二人以上を選択をチェック
            function check(flag){
                var count=0;
                for(var i=0;i<$("svnUser").length;i++){
                    if($("svnUser")[i]!=null&&$("svnUser")[i].selected){
                        count++;
                    }
                }
                //未選択
                if(count==0){
                    return false;
                }
                //二人以上を選択
                if(count>1){
                    if(flag=="pjMaster"){
                        $("lblMsg").innerHTML="PJ責任者は一人だけ選択してください！";
                    }else{
                        $("lblMsg").innerHTML="PJリーダは一人だけ選択してください！";
                    }
                    return false;
                //一人を選択
                }else{
                    return true;
                }
            }
            //データの重複をチェック
            function multiCheck(flag){
                //PJ責任者とPJリーダのデータの重複をチェック
                if(flag!="member"){
                    for(var i=0;i<$("svnUser").length;i++){
                        if($(flag + "No").value==$("svnUser")[i].text){
                            $(flag + "No").value="";
                            $(flag).value="";
                            break;
                        }
                    }
                    for(var i=$("member").length-1;i>=0;i--){
                       for(var j=$("svnUser").length-1;j>=0;j--){
                            if($("svnUser")[j].selected){
                                if($("member")[i].value==$("svnUser")[j].value){
                                    
                                }
                            }
                        }
                    }
                //メンバーのデータの重複をチェック
                }else{
                    for(var i=$("svnUser").length-1;i>=0;i--){
                        if($("svnUser")[i].selected){
                            if($("pjMasterNo").value==$("svnUser")[i].value) {
                                temp=$("svnUser")[i];
                                $("svnUser").remove(i);
                                $("lblMsg").innerHTML="選択したユーザーはもうPJ責任者に選択されましたから、ほかのユーザーを選んでください。";
                                continue;
                            }
                            if($("pjLeaderNo").value==$("svnUser")[i].value) {
                                temp=$("svnUser")[i];
                                $("svnUser").remove(i);
                                $("lblMsg").innerHTML="選択したユーザーはもうPJリーダに選択されましたから、ほかのユーザーを選んでください。";
                                continue;
                            }
                            for(var j=0;j<$("member").length;j++){
                                if($("svnUser")[i].value==$("member")[j].value){
                                    $("svnUser").remove(i);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            // クリアボタンを押下する。
            function kuria() {
                $("lblMsg").innerHTML="";
                $("svnUserId").value = "";
                $("svnUserName").value = "";
            }
            // 検索
            function kensaku() {
                var memberAll = "";
                if($("member").length > 0) {
                    memberAll = $("member")[0].value;
                }
                for(var i=1; i<$("member").length; i++){
                    memberAll = memberAll + ","  + $("member")[i].value;
                 }
                $("sentakuMember").value = memberAll;
            }
            // メンバーを表示する。
            function memberStart() {
                var memberAll = $("sentakuMember").value.split(",");
                var members = $("member");
                for(var i=0; i<memberAll.length; i=i+2){
                    var id=memberAll[i];
                    var name=memberAll[i+1];
                    members.add(new Option(id+"                 "+name,id+","+name));
                }
            }
        </script>
    </head>
    <body>
        <html:form action="SVNM0413.do" method="post" >
            <div align="left">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                    <tr valign="bottom">
                        <td align="left" width="125">
                            <em>従業員検索</em>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_font" height="15px">
                        <label id="lblMsg" style="color: red;font-family: ＭＳ Ｐゴシック;">
                        <html:errors/>
                        </label></td>
                    </tr>
                </table>
                <hr width="100%">
                <table border="0" cellspacing="1" cellpadding="3" class="TB05" style="width:100%">
                    <tr>
                        <td class="TD03" style="width:23%">&nbsp;従業員番号 ：</td>
                        <td class="TD02" style="width:20%">
                            <html:text tabindex="1" name="SVNM0413Bean" property="svnUserId" style="width:90%;" styleClass="TEXT01" maxlength="5"/>
                        </td>
                        <td class="TD03" style="width:24%">&nbsp;従業員名前 ：</td>
                        <td class="TD02">
                            <html:text tabindex="2" name="SVNM0413Bean" property="svnUserName" style="width:90%" styleClass="TEXT01" maxlength="10"/>
                        </td>
                    </tr>
                </table>
                <table style="margin-left:-6" cellpadding="3" border="0">
                    <tr>
                        <td>
                            <html:submit tabindex="3" property="btnKenSaku" styleClass="BUTTON01" onclick="kensaku();"> 検  索 </html:submit>
                        </td>
                        <td>
                            <html:button tabindex="4" property="btnKuRiA" styleClass="BUTTON01" value=" クリア " onclick="kuria();"></html:button>
                        </td>
                    </tr>
                </table>
                <hr width="100%" style="margin-top:-6">
                <table width="100%" style="margin-top:-6" border="0">
                    <tr>
                        <td style="width:40%" valign="top" align="center">
                            <table border="0">
                                <tr style="font-size: 12px; font-family: ＭＳ Ｐゴシック">
                                    <td style="width:53%">番号</td>
                                    <td>名前</td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                    <logic:present name="SVNM0413Bean" property="selectBean" scope="request">
                                        <html:select tabindex="5" name="SVNM0413Bean" multiple="multiple" styleClass="SELECT01" property="svnUser" 
                                        style="width:195px;font-size: 12px;height:336px" size="15">
                                            <html:optionsCollection filter="false" name="SVNM0413Bean" property="selectBean" label="name" value="code"/>
                                        </html:select>
                                    </logic:present>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td style="width:20%" valign="top" align="left">
                            <table border="0" style="width:100%">
                                <tr style="height:14px">
                                    <td>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <html:button tabindex="6" property="btnTuiKaMaster" styleClass="BUTTON01" value="追 加>>" onclick="tuiKaMaster('pjMaster');" ></html:button>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <html:button tabindex="7" property="btnSakuJyoMaster" styleClass="BUTTON01" value="<<削 除" onclick="sakuJyoMaster('pjMaster');" ></html:button>
                                    </td>
                                </tr>
                                <tr style="height:14px">
                                    <td>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <html:button tabindex="8" property="btnTuiKaLeader" styleClass="BUTTON01" value="追 加>>" onclick="tuiKaMaster('pjLeader');" ></html:button>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <html:button tabindex="9" property="btnSakuJyoLeader" styleClass="BUTTON01" value="<<削 除" onclick="sakuJyoMaster('pjLeader');" ></html:button>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="height:30px">
                                        &nbsp;
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <html:button tabindex="10" property="btnTuiKa" styleClass="BUTTON01" value="追 加>>" onclick="tuiKa();"></html:button>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <html:button tabindex="11" property="btnSakuJyo" styleClass="BUTTON01" value="<<削 除" onclick="sakuJyo();"></html:button>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td style="width:40%" valign="top" align="center">
                            <table border="0" style="width:100%">
                                <tr>
                                    <td style="font-size: 12px; font-family: ＭＳ Ｐゴシック;height:12px">
                                        ＰＪ責任者：
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <html:text tabindex="-1" name="SVNM0413Bean" property="pjMaster" styleClass="TEXT01" readonly="true"/>
                                        <html:hidden name="SVNM0413Bean" property="pjMasterNo"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        &nbsp;
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size: 12px; font-family: ＭＳ Ｐゴシック;height:12px">
                                        ＰＪリーダ：
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <html:text tabindex="-1" name="SVNM0413Bean" property="pjLeader" styleClass="TEXT01"  readonly="true"/>
                                        <html:hidden name="SVNM0413Bean" property="pjLeaderNo"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="height:21px">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size: 12px; font-family: ＭＳ Ｐゴシック;height:12px">
                                        メンバー：
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                            <tr style="font-size: 12px; font-family: ＭＳ Ｐゴシック;">
                                                <td width=52%>番号</td>
                                                <td>名前</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <select tabindex="12" class="SELECT01" id="member" name="member" style="width:185px;font-size:12px;height:186px" size="15"
                                            multiple="multiple">
                                        </select>
                                        <html:hidden name="SVNM0413Bean" property="sentakuMember"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <hr width="100%" style="margin-top:-3">
                <table cellpadding="3" border="0" style="margin-left:-6">
                    <tr>
                        <td>
                            <input id="btnKakuTei" class="BUTTON01" value=" 確  定 " type="button" onclick="kakuTei();" />
                        </td>
                        <td>
                            <input id="btnModoru" class="BUTTON01" type="button" value=" 戻  る " onclick="window.close();" />
                        </td>
                    </tr>
                </table>
            </div>
        </html:form>
    </body>
</html:html>
<script language="javascript">
if($("sentakuMember").value.length>0)
    memberStart();
</script>
<logic:present name="error" scope="request">
    <bean:write name="error" filter="false" scope="request"/>
</logic:present>