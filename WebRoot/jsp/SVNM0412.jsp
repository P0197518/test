<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
  <head>
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
        // ボタンを押下するとsubValueに値を設定する
        function clickButton(butValue) {
            document.forms[0].subValue.value = butValue;
            document.forms[0].pageId.value = "SVNM0412";
        }
        // 戻る
        function clickBack () {
            document.forms[0].subValue.value = "back";
            document.forms[0].pageId.value = "";
            document.forms[0].submit();
        }
    </script>
  </head>
  <body>
    <html:form action="/SVNM0412.do" method="post">
    <!-- 操作状態 -->
    <html:hidden property="subValue" name="SVNM0412Bean"/>
    <!-- 更新時間-->
    <html:hidden property="updateDate" name="SVNM0412Bean"/>
    <!--元のｓｖｎパスワード -->
    <html:hidden property="oldPassword" name="SVNM0412Bean"/>
    <!-- 従業員番号 -->
    <html:hidden property="systemUserNo" name="SVNM0412Bean"/>
    <!-- 元のSVN登録名 -->
    <html:hidden property="oldLogName" name="SVNM0412Bean"/>
    <html:hidden property="pageId" name="SVNM0412Bean"/>
        <!--ボディー部-->
        <!--メッセージ-->
        <div style="height: 13px">
            <table cellspacing="0" cellpadding="0" width="100%" border="0">
                <tr>
                    <td style="height: 13px" class="td_font">
                        <label id="lblMsg" style="color: red;font-family: ＭＳ Ｐゴシック">
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
                <td><a href="SVNM0410.do" style="text-decoration:underline;font-size:13;color:#0000FF;">前画面へ戻る</a></td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="3" width="70%" border="0" bgcolor="#999999">
            <!--従業員番号-->
            <tr class="TR01">
                <th class="TH01" style="width: 30%;height:29">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">従業員番号： </span>&nbsp;&nbsp;&nbsp;
                            <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt"></span>
                        </strong>
                    </label>
                </th>
                <td class="TD02" style="width: 70%">
                    <bean:write property="systemUserNo" name="SVNM0412Bean"/>
                </td>
            </tr>
            <!--従業員名前-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">従業員名前：</span>
                            <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック;font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:text name="SVNM0412Bean" property="systemUserName"  styleClass="TEXT01"  maxlength="10" style="width: 25%">
                        <bean:write property="systemUserName" name="SVNM0412Bean"/>
                    </html:text>
                </td>
            </tr>
            <!--システム登録パスワード-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">新システム登録パスワード：</span>
                            <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt"></span>&nbsp;&nbsp;&nbsp;
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password  name="SVNM0412Bean" styleClass="TEXT01" maxlength="15" property="sysPassword" style="width: 25%" />
                </td>
            </tr>
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">システム登録パスワード確認： </span>
                            <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック;font-size: 10pt"></span>&nbsp;&nbsp;&nbsp;
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password name="SVNM0412Bean" property="sysRegPassword" maxlength="15" styleClass="TEXT01" style="width:25%"></html:password>
                </td>
            </tr>
            <!--SVN登録名-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">SVN登録名：</span>
                            <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:text name="SVNM0412Bean" styleClass="TEXT01" maxlength="20" property="loginName" style="width: 33%">
                    <bean:write property="loginName" name="SVNM0412Bean"/>
                    </html:text>
                </td>
            </tr>
            <!--SVN登録パスワード-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">新SVNパスワード：</span>
                            <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt"></span>&nbsp;&nbsp;&nbsp;
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password  name="SVNM0412Bean" styleClass="TEXT01" maxlength="15" property="loginPassword" style="width: 25%" />
                </td>
            </tr>
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">SVNパスワード確認： </span>
                            <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック;font-size: 10pt"></span>&nbsp;&nbsp;&nbsp;
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password name="SVNM0412Bean" property="userRegPassword" maxlength="15" styleClass="TEXT01" style="width:25%"></html:password>
                </td>
            </tr>
            <!--メールアドレス-->
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">メール：</span>
                            <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:text name="SVNM0412Bean" styleClass="TEXT01" maxlength="20" property="userMail" style="width: 33%" >
                        <bean:write property="userMail" name="SVNM0412Bean"/>
                    </html:text>
                </td>
            </tr>
            <tr class="TR01">
            <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">権限：</span>
                            <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <logic:present name="SVNM0412Bean" property="privilegeComBean" scope="request" >
                        <html:select name="SVNM0412Bean" property="comPriviege" style ="background-color:#FFFFE0;width:29%">
                            <html:optionsCollection name="SVNM0412Bean" property="privilegeComBean" label="name" value="code"/>
                        </html:select>
                    </logic:present>
                </td>
            </tr>
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong><span style="font-size: 10pt">コメント：&nbsp;&nbsp;&nbsp;&nbsp;</span></strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:textarea  name="SVNM0412Bean" property="userComment" styleClass="TEXTAREA01" rows="5" cols="50"></html:textarea>
                </td>
            </tr>
        </table>
        <!--従業員修正ボタン-->
        <table  style="margin-left: -6;" cellpadding="3">
                <tr>
                    <td>
                        <html:submit property="btnSubmit" value=" 確  定 " styleClass="BUTTON01" onclick="clickButton('update')" />
                    </td>
                    <td><html:button property="btnBack" value=" 戻  る " styleClass="BUTTON01" onclick="clickBack();"/>
                    </td>
                </tr>
         </table>
        <br />
        <bean:write name="SVNM0412Bean" property="hidError" filter="false"/>
    </html:form>
  </body>
</html:html>