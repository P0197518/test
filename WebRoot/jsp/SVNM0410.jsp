<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/fbse-page.tld" prefix="fbse"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
  <head>
    <html:base />
    <title>SVN管理システム</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/svnm_css.css" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/commonCheck.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script type="text/javascript">
       // 登録画面へ遷移する 
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
        // ボタンを押下すると対応するボタンの値をbuttonValueに設定する
        function clickButton(butValue,paraValue) {
            if (butValue == "update") {
            $("pageNo").value=$("drpPage").value;
            }
            document.forms[0].buttonValue.value = butValue;
            if(paraValue != "") {
                document.forms[0].paramValue.value = paraValue;
            }
            document.forms[0].submit();
        }
        // 削除アンカーをクリックする
        function clickDel(butValue,paraValue) {
            $("pageNo").value=$("drpPage").value;
            if(confirm("削除しますか？"))　{
                document.forms[0].buttonValue.value = butValue;
                if(paraValue != "") {
                    document.forms[0].paramValue.value = paraValue;
                }
                document.forms[0].submit();
            }
        }
        // 修復アンカーをクリックする
        function clickBack(butValue,paraValue) {
            $("pageNo").value=$("drpPage").value;
            if(confirm("修復しますか？"))　{
                document.forms[0].buttonValue.value = butValue;
                if(paraValue != "") {
                    document.forms[0].paramValue.value = paraValue;
                }
                document.forms[0].submit();
            }
        }
        // メッセージと入力したデータを空白にする
        function doClear(){
            document.getElementById('lblSeikouMsg').innerHTML='';
            document.forms[0].userNo.value = '';
            document.forms[0].userName.value = '';
            document.forms[0].svnLoginName.value = '';
            if (document.forms[0].privilege.options.length > 0) {
                document.forms[0].privilege.options[0].selected = 'selected';
            }
            if (document.forms[0].stateValue.options.length > 0) {
                document.forms[0].stateValue.options[0].selected = 'selected';
            }
        }
        // 一覧部の内容が長すぎの処理
        function setSize() {
            if (document.all.myTable != null) {
                var myWindowWidth = parseInt(document.body.clientWidth)
                var myTagWidth = parseFloat($("commentLine").width) * 0.01
                var myPrivilegenage = parseFloat($("privilegeLine").width) * 0.01
                var lineNumber = document.all.myTable.rows.length - 1
                var myUserNo = parseFloat($("userLine").width) * 0.01
                for(var i =0; i<lineNumber; i++){
                    $("comment"+(i+1)).style.width = parseInt(myWindowWidth * myTagWidth)-15
                    $("privilege"+(i+1)).style.width = parseInt(myWindowWidth * myPrivilegenage)-20
                    $("userNo"+(i+1)).style.width = parseInt(myWindowWidth * myPrivilegenage)-20
                }
            }
        }
    </script>
  </head>
  <body onload="setSize();" onresize="setSize();">
    <html:form action="/SVNM0410.do" method="post">
        <html:hidden property="buttonValue" />
        <html:hidden property="paramValue"/>
        <div>
            <div style="height:13px">
                <table cellspacing="0" cellpadding="0" width="99%" border="0">
                    <tr>
                        <td class="td_font">
                            <label id="lblSeikouMsg" style="color: red;font-family: ＭＳ Ｐゴシック">
                                <html:errors/>
                            </label>
                        </td>
                    </tr>
                </table>
            </div>
             <table>
                <tr>
                    <td bgcolor="#224499"><SPAN style="color:#ffffff;font-weight:bold;font-size:14">従業員マスタ</SPAN></td>
                </tr>
                <tr>
                    <td><a href="javascript:clickButton('back','SVNM0510');" style="text-decoration:underline;font-size:13;color:#0000FF;">前画面へ戻る</a></td>
                </tr>
            </table>
            <table border="0" cellspacing="1" cellpadding="3" class="TB05" style="width:98%">
                <tr>
                    <td class="TD03" style="width:12%">&nbsp;従業員番号 ：</td>
                    <td class="TD02" style="width:7%">
                        <html:text style="width:100%;" property="userNo" maxlength="5" styleClass="TEXT01"/>
                    </td>
                    <td class="TD03" style="width:12%">&nbsp;従業員名前 ：</td>
                    <td class="TD02" style="width:11%">
                        <html:text  property="userName" style="width:100%" maxlength="10" styleClass="TEXT01"/>
                    </td>
                    <td class="TD03" style="width:10%">&nbsp;SVN登録名 ：</td>
                    <td class="TD02" style="width:20%">
                        <html:text property="svnLoginName" style="width:100%" maxlength="20" styleClass="TEXT01"/>
                    </td>
                    <td class="TD03" style="width:6%">&nbsp;権限 ：</td>
                    <td class="TD02" style="width:14%">
                        <logic:notEmpty name="SVNM0410Bean">
                            <html:select name="SVNM0410Bean" property="privilege" style ="background-color:#FFFFE0;width:100%">
                                <html:option value=""></html:option>
                                <html:optionsCollection name="SVNM0410Bean" property="privilegeBean" label="name" value="code"/>
                            </html:select>
                        </logic:notEmpty>
                    </td>
                    <td class="TD03" style="width:6%">&nbsp;状態 ：</td>
                    <td class="TD02" style="width:9%">
                        <logic:notEmpty name="SVNM0410Bean" property="selectBean" >
                            <html:select name="SVNM0410Bean" property="stateValue" style ="background-color:#FFFFE0;">
                                <html:optionsCollection name="SVNM0410Bean" property="selectBean" label="name" value="code"/>
                            </html:select>
                        </logic:notEmpty>
                    </td>
                 </tr>
            </table>
            <table  style="margin-left: -6;" cellpadding="3">
                <tr>
                    <td>
                        <html:submit styleClass="BUTTON01" property="btnSelect" value="検  索" onclick="clickButton('select','');"/>
                    </td>
                    <td>
                        <html:button styleClass="BUTTON01" property="btnClear" value="クリア" onclick="doClear();"/>
                    </td>
                    <logic:notEmpty name="rwPrivilege" scope="request">
                    <td>
                        <html:submit styleClass="BUTTON01" property="btnInsert" value="新  規" onclick="clickButton('insert','');"/>
                    </td>
                    </logic:notEmpty>
                </tr>
            </table>
            <logic:notEmpty name="result">
              <fbse:page name="result" property="USERNO,USERNAME,SVNLOGINNAME,PRIVILEGENAME,MAIL,COMMENT,UPDATEDATE,SVNPASSWORD" pageSize="14">
                <table cellspacing="0" cellpadding="0" width="100%" border="0">
                      <tr>
                        <td align="left">
                            <table id="myTable" class="TB03" width="98%" border="0" cellspacing="1" cellpadding="3">
                                <!--テーブルヘッダ-->
                                <tr bgcolor="#d2d3ff" height="25px">
                                    <th width="4%" scope="col">
                                        <span class="th_font">番号</span></th>
                                    <th width="8%" scope="col">
                                        <span class="th_font">従業員番号</span></th>
                                    <th  id="userLine" width="12%" scope="col">
                                        <span class="th_font">従業員名前</span></th>
                                    <th width="15%" scope="col">
                                        <span class="th_font">SVN登録名</span></th>
                                    <th id="privilegeLine" width="13%" scope="col">
                                        <span class="th_font">権限</span></th>
                                    <th width="16%" scope="col">
                                        <span class="th_font">メールアドレス</span></th>
                                    <th id="commentLine" width="25%" scope="col">
                                        <span class="th_font">コメント</span></th>
                                    <th>
                                        <span class="th_font">操作</span></th>
                                </tr>
                                <!--一覧内容-->
                                <% int i = 1;%>
                                   <logic:iterate id="result" name="result">
                                            <tr height="20px" <%if (i % 2 == 0) { %> style="background-color: #e9f5f5" <%} else { %> style="background-color: #FFFFFF" <% }%> ><%i++;%>
                                                <td align="center" class="td_font" >
                                                    <bean:write name="result" property="No"/>
                                                </td>
                                                <logic:empty name="deteted" scope="request">
                                                     <logic:notEmpty name="rwPrivilege" scope="request">
                                                         <td align="center" class="td_font" style="height: 20px">
                                                            <bean:define id="updateDate" name="result" property="UPDATEDATE"/>
                                                            <bean:define id="userNo" name="result" property="USERNO" />
                                                            <%String update0410 = updateDate + "," + userNo;%>
                                                            <a href="javascript:clickButton('update','<%=update0410 %>');"><bean:write name="result" property="USERNO" /></a>
                                                         </td>
                                                      </logic:notEmpty>
                                                      <logic:empty name="rwPrivilege" scope="request">
                                                         <td align="center" class="td_font" style="height: 20px">
                                                            <bean:write name="result" property="USERNO" />
                                                         </td>
                                                      </logic:empty>
                                               </logic:empty>
                                               <logic:notEmpty name="deteted" scope="request">
                                                    <td align="center" class="td_font" style="height: 20px">
                                                        <bean:write name="result" property="USERNO" />
                                                    </td>
                                               </logic:notEmpty>
                                                    <td align="left" class="td_font" >
                                                       <div title="<bean:write name="result" property="USERNAME" />" id ="userNo<bean:write name="result" property="No"/>" style ="width:100px; overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                                           <bean:write name="result" property="USERNAME" />
                                                       </div>
                                                    </td>
                                                    <td align="left" class="td_font" ><bean:write name="result" property="SVNLOGINNAME" /></td>
                                                    <td align="left" class="td_font" >
                                                       <div title="<bean:write name="result" property="PRIVILEGENAME" />" id ="privilege<bean:write name="result" property="No"/>" style ="width:110px; overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                                           <bean:write name="result" property="PRIVILEGENAME" />
                                                       </div>
                                                    </td>
                                                    <td align="left" class="td_font" ><bean:write name="result" property="MAIL" /></td>
                                                    <td align="left" class="td_font" >
                                                       <div title="<bean:write name="result" property="COMMENT" />" id ="comment<bean:write name="result" property="No"/>" style ="width:200px; overflow:hidden ; text-overflow:ellipsis ; white-space :nowrap ;">
                                                           <bean:write name="result" property="COMMENT" />
                                                       </div>
                                                    </td>
                                                    <td align="center" class="td_font" style="height: 20px">
                                                        <logic:notEmpty name="rwPrivilege" scope="request">
                                                            <bean:define id="updateDate" name="result" property="UPDATEDATE"/>
                                                            <bean:define id="userNo" name="result" property="USERNO" />
                                                            <bean:define id="svnPassword" name="result" property="SVNPASSWORD" />
                                                            <bean:define id="svnLonginName" name="result" property="SVNLOGINNAME" />
                                                            <logic:empty name="deteted" scope="request">
                                                                <%String delete0410 = updateDate + "," + userNo + "," + svnPassword + "," + svnLonginName;%>
                                                                <a href="javascript:clickDel('delete','<%=delete0410 %>');" >削除</a>
                                                                </logic:empty>
                                                                <logic:notEmpty name="deteted" scope="request">
                                                                <%String toBack0410 = updateDate + "," + userNo + "," + svnPassword+"," + svnLonginName;%>
                                                                <a href="javascript:clickBack('backUp','<%=toBack0410 %>');" >修復</a>
                                                            </logic:notEmpty>
                                                        </logic:notEmpty>
                                                    </td>
                                            </tr>
                                        </logic:iterate>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </fbse:page>
                </logic:notEmpty>
           </div>
    </html:form>
    <bean:write name="SVNM0410Bean" property="hidError" filter="false"/>
  </body>
</html:html>