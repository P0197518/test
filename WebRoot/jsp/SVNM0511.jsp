<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
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
        // ボタンを押下するとsubValueに値を設定する
        function clickButton(butValue) {
            document.forms[0].subButtonValue.value = butValue;
        }
        // 戻るボタンを押下する
        function backSousa (butValue) {
            document.forms[0].subButtonValue.value = butValue;
            document.forms[0].pageId.value = "";
            document.forms[0].submit();
        }
    </script>
  </head>
  <body>
    <html:form action="/SVNM0511.do" method="post">
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
                <td bgcolor="#224499"><SPAN style="color:#ffffff;font-weight:bold;font-size:14;font-family: ＭＳ Ｐゴシック;">SVNパスワード定期維持</SPAN></td>
            </tr>
            <tr>
                <td><a href="javaScript:backSousa('back');"  style="text-decoration:underline;font-size:13;color:#0000FF;">前画面へ戻る</a></td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="3" width="70%" border="0" bgcolor="#999999">
            <!--SVN登録パスワード-->
            <tr class="TR01">
                <th class="TH01"  style="width: 30%">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">新SVNパスワード：</span>
                            <span style="color: #ff0000;font-family: ＭＳ Ｐゴシック; font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02"  style="width: 70%">
                    <html:password property="newSvnPassword"  styleClass="TEXT01" maxlength="15"  style="width: 25%" />
                </td>
            </tr>
            <tr class="TR01">
                <th class="TH01">
                    <label>
                        <strong>
                            <span style="font-size: 10pt">新SVNパスワード確認：</span>
                            <span style="color: #ff0000; font-family: ＭＳ Ｐゴシック;font-size: 10pt">*</span>
                        </strong>
                    </label>
                </th>
                <td class="TD02">
                    <html:password property="svnRePassword"  styleClass="TEXT01" maxlength="15"  style="width: 25%" />
                </td>
            </tr>
        </table>
        <!--SVNユーザー修正ボタン-->
        <table  style="margin-left: -6;" cellpadding="3">
                <tr>
                    <td>
                        <html:submit property="btnSubmit" value=" 確  定 " styleClass="BUTTON01" onclick="clickButton('submit');"/>
                    </td>
                    <td>
                        <html:button property="button" value=" 戻  る " styleClass="BUTTON01" onclick="backSousa('back');"/>
                    </td>
                </tr>
         </table>
         <html:hidden property="pageId" value="SVNM0511"/>
         <html:hidden property="subButtonValue"/>
         <bean:write name="SVNM0511Bean" property="hidError" filter="false"/>
         <br/>
    </html:form>
  </body>
</html:html>