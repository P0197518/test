package cn.edu.sgu.www.easyui.consts;

/**
 * mime类型常量接口
 * @author heyunlin
 * @version 1.0
 */
public interface MimeType {

    String CHARSET_UTF_8 = ";charset=utf-8";

    String TEXT_HTML = "text/html";

    String APPLICATION_JSON = "application/json";

    String TEXT_HTML_CHARSET_UTF_8 = TEXT_HTML + CHARSET_UTF_8;

    String APPLICATION_JSON_CHARSET_UTF_8 = APPLICATION_JSON + CHARSET_UTF_8;

    String APPLICATION_SHEET = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
}