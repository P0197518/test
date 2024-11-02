package com.fujias.common.util;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * okhttp模拟请求工具类
 * 
 * @author fujias
 *
 */
public class OkHttpUtil {
    private static Logger logger = Logger.getLogger(OkHttpUtil.class);
    // private static OkHttpClient okHttpClient;
    //
    // @Autowired
    // public OkHttpUtil(OkHttpClient okHttpClient) {
    // OkHttpUtil.okHttpClient = okHttpClient;
    // }

    private static OkHttpClient okHttpClient = new OkHttpClient();
    // private static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * get
     * 
     * @param url 请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return 获取数据
     */
    public static String get(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator<?> iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                @SuppressWarnings("unchecked")
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder().url(sb.toString()).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * post
     *
     * @param url 请求的url
     * @param params post form 提交的参数
     * @return 获取数据
     */
    public static String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        // 添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * post
     *
     * @param url 请求的url
     * @param params post form 提交的参数
     * @return 获取数据
     */
    public static String postForHeader(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        // 添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        // Request request = new Request.Builder()
        // .addHeader("Authorization", "Basic aVlhcEQ0aFQ5eUNQS2trbDdsYjdiaDlXcjJpY2V6VVE6c1VJejZPdng4b2xkNzVwUGxIeXhEUHY0c0FHa3RRbHQ=")
        // .url(url).post(builder.build()).build();

        // String token = appKey + ":" + appSecret;
        // String encodedToken = encoder.encodeToString(token.getBytes(StandardCharsets.UTF_8));

        Request request = new Request.Builder()
                        .addHeader("Authorization", "Basic SllkOWowbVJqYnZpVVdJYXBVSGlGdkxvbENYazExUXI6QUVjeTdVUWxnWDZTS3V6ak5JMHNxQnpsS0FzNElYcmM=")
                        .url(url).post(builder.build()).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * get
     * 
     * @param url 请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @param token token
     * @return 获取数据
     */
    public static String getForHeader(String url, Map<String, String> queries, String token) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator<?> iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                @SuppressWarnings("unchecked")
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder().addHeader("Authorization", "bearer " + token).url(sb.toString()).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"} 参数一：请求Url 参数二：请求的JSON 参数三：请求回调
     * 
     * @param url url
     * @param jsonParams jsonParams
     * @return getData
     */
    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送xml数据.... 参数一：请求Url 参数二：请求的xmlString 参数三：请求回调
     * 
     * @param url url
     * @param xml xml
     * @return 获取数据
     */
    public static String postXmlParams(String url, String xml) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

}