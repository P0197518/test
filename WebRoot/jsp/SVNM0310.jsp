<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/fbse-page.tld" prefix="fbse"%>

<html>
    <head>
        <title>SVN管理システム</title>
        <link rel="stylesheet"
            href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/commonCheck.js"></script>
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
        // 削除ボタンを押下する
        function delButton(buttonValue,myvalue) {
            var flag = confirm('削除しますか？');
            if(flag){
                document.forms[0].buttonNo.value=buttonValue;
                if(myvalue != "") {
                    document.forms[0].paramValue.value = myvalue;
                }
                $("pageNo").value=$("drpPage").value
                document.forms[0].submit();
            }
        }
        // 権限名対応するアンカーをクリックする
        function editButton(buttonValue,myvalue) {
            document.forms[0].buttonNo.value=buttonValue;
            if(myvalue != "") {
                document.forms[0].paramValue.value = myvalue;
            }
            $("pageNo").value=$("drpPage").value
            document.forms[0].submit();
            
        }
        // 前画面へ戻るアンカーをクリックする
        function doSubmit(buttonValue) {
            document.forms[0].buttonNo.value=buttonValue;
            document.forms[0].submit();
        }
        // クリアボタンを押下する
        function doClear(){
            document.getElementById('lblMsg').innerHTML='';
            document.forms[0].privilegeName.value = '';
        }
        // windowを变える
        function setSize() {
            if(document.all.myTable != null) {
                var myWindowWidth = parseInt(document.body.clientWidth)
                var myTagWidth = parseFloat($("commentLine").width) * 0.01
                var lineNumber = document.all.myTable.rows.length - 1
                for(var i =0; i<lineNumber; i++){
                    $("comment"+(i+1)).style.width = parseInt(myWindowWidth * myTagWidth)-15
                }
            }
        }
    </script>
    </head>

    <body style="margin-top: 0;" onresize="setSize()" onload="setSize()">
        <html:form action="SVNM0310.do" method="post">
            <html:hidden property="buttonNo" value="privilegeSearch" />
            <html:hidden property="paramValue" />
            <div>
                <div style="height: 13px">
                    <table cellspacing="0" cellpadding="0" width="99%" border="0">
                        <tr>
                            <td class="td_font">
                                <label id="lblMsg" style="color: red; font-family: ＭＳ Ｐゴシック;">
                                    <html:errors />
                                </label>
                            </td>
                        </tr>
                    </table>
                </div>
                <table>
                <tr>
                    <td bgcolor="#224499"><SPAN style="color:#ffffff;font-weight:bold;font-size:14">権限マスタ</SPAN></td>
                </tr>
                <tr>
                    <td><a href="javascript:doSubmit('SVNM0510')" style="text-decoration:underline;font-size:13;color:#0000FF;">前画面へ戻る</a></td>
                </tr>
                </table>
                <table border="0" cellspacing="1" cellpadding="3" class="TB05"
                    style="width: 98%">
                    <tr>
                        <td class="TD03" style="width: 10%">
                            &nbsp;権限名 ：
                        </td>
                        <td class="TD02" style="width: 90%">
                            <html:text property="privilegeName" styleClass="TEXT01"
                                maxlength="10"></html:text>
                        </td>
                    </tr>
                </table>
                <table style="margin-left: -6" cellpadding="3">
                    <tr>
                        <td>
                            <html:button property="privilegeSearch" styleClass="BUTTON01"
                                value=" 検  索 " onclick="doSubmit('privilegeSearch')"></html:button>
                        </td>
                        <td>
                            <html:button property="privilegeClear" styleClass="BUTTON01"
                                value=" クリア " onclick="doClear();"></html:button>
                        </td>
                        <logic:notEmpty name="0310Manage" scope="request">
                            <td>
                                <html:button property="privilegeNew" styleClass="BUTTON01"
                                    value=" 新  規 " onclick="doSubmit('privilegeInsert')"></html:button>
                            </td>
                        </logic:notEmpty>
                    </tr>
                </table>
                <!--一覧部-->
                <fbse:page name="result"
                    property="PRIVILEGENO,PRIVILEGENAME,PJPRIVILEGE,MASTERPRIVILEGE,COMMENT,UPDATEDATE"
                    pageSize="8">
                    <table id="myTable" class="TB03" width="98%" border="0" cellspacing="1" cellpadding="3">
                        <tr bgcolor="#d2d3ff"  height="25px">
                            <th width="4%" scope="col"><span class="th_font">番号</span></th>
                            <th width="15%" scope="col"><span class="th_font">権限名</span></th>
                            <th width="19%" scope="col"><span class="th_font">プロジェクト管理</span></th>
                            <th width="19%" scope="col"><span class="th_font">マスタメンテ</span></th>
                            <th id="commentLine" width="38%" scope="col"><span class="th_font">コメント</span></th>
                            <th ><span class="th_font">操作</span></th>
                        </tr>
                        <% int i = 1; %>
                        <logic:iterate id="result" name="result">
                            <tr height="20px" <%if (i % 2 == 0) { %>
                                style="background-color: #e9f5f5" <%} else { %>
                                style="background-color: #FFFFFF" <% }%>>
                                <% i++; %>
                                <td align="center" class="td_font">
                                    <bean:write name="result" property="No" />
                                </td>
                                <logic:notEmpty name="0310Manage" scope="request">
                                    <bean:define id="privilegeNo" name="result" property="PRIVILEGENO" />
                                    <bean:define id="updateDate" name="result" property="UPDATEDATE" />
                                    <% String editValue = privilegeNo + "," + updateDate; %>
                                    <td align="left" class="td_font" style="width: 10%">
                                        <div
                                            style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
                                            <a href="javascript:editButton('privilegeEdit','<%=editValue%>');"><bean:write
                                                    name="result" property="PRIVILEGENAME" /> </a>
                                        </div>
                                    </td>
                                </logic:notEmpty>
                                <logic:empty name="0310Manage" scope="request">
                                    <td align="left" class="td_font">
                                        <div style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
                                            <bean:write name="result" property="PRIVILEGENAME" />
                                        </div>
                                    </td>
                                </logic:empty>
                                <td align="center" class="td_font"
                                    style="font-family: ＭＳ Ｐゴシック; font-size: 12">
                                    <bean:write name="result" property="PJPRIVILEGE" />
                                </td>
                                <td align="center" class="td_font"
                                    style="font-family: ＭＳ Ｐゴシック; font-size: 12">
                                    <bean:write name="result" property="MASTERPRIVILEGE" />
                                </td>
                                <td align="left" class="td_font">
                                    <div title="<bean:write name="result" property="COMMENT" />" id ="comment<bean:write name="result" property="No" />"  style ="overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                         <bean:write name="result" property="COMMENT" />
                                    </div>
                                </td>
                                <logic:notEmpty name="0310Manage" scope="request">
                                    <td align="center" class="td_font">
                                        <bean:define id="updateDate" name="result" property="UPDATEDATE" />
                                        <bean:define id="privilegeNo" name="result" property="PRIVILEGENO" />
                                        <bean:define id="privilegeName" name="result" property="PRIVILEGENAME" />
                                        <% String deleteValue = privilegeNo + "," + updateDate + "," + privilegeName; %>
                                        <a href="javascript:delButton('privilegeDelete','<%=deleteValue%>');">削除</a>
                                    </td>
                                </logic:notEmpty>
                                <logic:empty name="0310Manage" scope="request">
                                    <td align="center" class="td_font">
                                    </td>
                                </logic:empty>
                            </tr>
                        </logic:iterate>
                    </table>
                </fbse:page>
            </div>
            <!--注釈-->
            <div style="position: absolute; top: 77%">
                <table cellspacing="1" cellpadding="3" width="70%" border="0"
                    class="T01">
                    <tr class="TR01">
                        <td align="right" rowspan="3" width="10%">
                            <label>
                                <strong>
                                <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック; font-size: 10pt">注&nbsp;&nbsp;</span>
                                </strong>
                            </label>
                        </td>
                        <td style="height: 34px; width: 90%; font-family: ＭＳ Ｐゴシック; font-size: 12">
                            ×：該当モジュールのアクセス権限なし。
                        </td>
                    </tr>
                    <tr class="TR01">
                        <td style="height: 34px; width: 90%; font-family: ＭＳ Ｐゴシック; font-size: 12">
                            <span>△：該当モジュールの読権限がある。</span>
                        </td>
                    </tr>
                    <tr class="TR01">
                        <td style="height: 34px; width: 90%; font-family: ＭＳ Ｐゴシック; font-size: 12">
                            <span>〇：該当モジュールの管理権限がある（検索、新規、編集、削除）。</span>
                        </td>
                    </tr>
                </table>
            </div>
        </html:form>
        <bean:write name="SVNM0310Bean" property="hidError" filter="false"/>
    </body>
</html>