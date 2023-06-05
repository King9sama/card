package com.yintech.commons.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description apache http工具
 * @Company: yintech
 * @date 2021/6/29 2:53 下午
 */
public class ApacheHttpUtils {
    private ApacheHttpUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LoggerFactory.getLogger(ApacheHttpUtils.class);
    private static final CloseableHttpClient HTTP_CLIENT;
    public static final String CHAR_SET = "UTF-8";
    //默认拿连接超时时间 1 分钟
    public static final Integer DEFAULT_CONNECTION_REQUEST_TIMEOUT = 1000 * 60;
    private static PoolingHttpClientConnectionManager cm = null;

    static {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);//连接池最大并发连接数
        cm.setDefaultMaxPerRoute(200);//单路由最大并发数
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(cm).evictIdleConnections(10, TimeUnit.SECONDS);
        HTTP_CLIENT = httpClientBuilder.build();
    }


    /**
     * 获取Http 请求配置
     *
     * @param connectTime       请求连接超时时间
     * @param socketConnectTime socket连接超时时间
     * @return RequestConfig
     */
    public static RequestConfig getRequestConfig(int connectTime, int socketConnectTime) {
        return getRequestConfig(connectTime, socketConnectTime, 1000 * 60 * 10);
    }

    /**
     * 获取Http请求配置
     *
     * @param connectTimeout           建连超时时间
     * @param socketTimeout            socket连接超时
     * @param connectionRequestTimeout 连接池中拿连接超时
     * @return
     */
    public static RequestConfig getRequestConfig(int connectTimeout, int socketTimeout, int connectionRequestTimeout) {
        return RequestConfig
                .custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
    }

    /**
     * 发送Http-Get 请求，connectionRequestTimeout默认为1分钟
     *
     * @param url               请求URL
     * @param connectTime       请求连接超时时间
     * @param socketConnectTime socket连接超时时间
     * @param header            请求头
     * @param params            请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpGet(String url, int connectTime, int socketConnectTime, Map<String, String> header, Map<String, String> params) throws IOException {
        return sendHttpGet(url, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT, header, params);
    }

    /**
     * 发送Http-Get 请求
     *
     * @param url                      请求URL
     * @param connectTime              请求连接超时时间
     * @param socketConnectTime        socket连接超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @param header                   请求头
     * @param params                   请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpGet(String url, int connectTime, int socketConnectTime, int connectionRequestTimeout, Map<String, String> header, Map<String, String> params) throws IOException {
        String body = null;
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        //装填参数
        url = urlParamJoint(url, params);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpGet.setHeader(CONTENT_TYPE, CONTENT_VALUE_FORM);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHAR_SET);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    private static final String CONTENT_VALUE_FORM = "application/x-www-form-urlencoded";
    private static final String CONTENT_VALUE_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-type";

    /**
     * 发送Http-Delete 请求， connectionRequestTimeout默认为1分钟
     *
     * @param url               请求url
     * @param connectTime       请求连接超时时间
     * @param socketConnectTime socket连接超时时间
     * @param header            请求头
     * @param params            请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpDelete(String url, int connectTime, int socketConnectTime, Map<String, String> header, Map<String, String> params) throws IOException {
        return sendHttpDelete(url, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT, header, params);
    }

    /**
     * 发送Http-Delete 请求
     *
     * @param url                      请求url
     * @param connectTime              请求连接超时时间
     * @param socketConnectTime        socket连接超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @param header                   请求头
     * @param params                   请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpDelete(String url, int connectTime, int socketConnectTime, int connectionRequestTimeout, Map<String, String> header, Map<String, String> params) throws IOException {
        String body = null;
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        url = urlParamJoint(url, params);
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(requestConfig);
        httpDelete.setHeader(CONTENT_TYPE, CONTENT_VALUE_FORM);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpDelete.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpDelete);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHAR_SET);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    /**
     * 发送Http-Put 请求, connectionRequestTimout默认为1分钟
     *
     * @param url               请求url
     * @param connectTime       请求连接超时时间
     * @param socketConnectTime socket连接超时时间
     * @param header            请求头
     * @param param             请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpUrlEncodedPut(String url, int connectTime, int socketConnectTime, Map<String, String> header, Map<String, String> param) throws IOException {
        return sendHttpUrlEncodedPut(url, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT, header, param);
    }

    /**
     * 发送Http-Put 请求
     *
     * @param url                      请求url
     * @param connectTime              请求连接超时时间
     * @param socketConnectTime        socket连接超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @param header                   请求头
     * @param param                    请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpUrlEncodedPut(String url, int connectTime, int socketConnectTime, int connectionRequestTimeout, Map<String, String> header, Map<String, String> param) throws IOException {
        String body = null;
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(requestConfig);
        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (param != null) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        httpPut.setEntity(new UrlEncodedFormEntity(nvps, CHAR_SET));
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPut.setHeader(CONTENT_TYPE, CONTENT_VALUE_FORM);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpPut);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHAR_SET);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    /**
     * 发送Http-Post 请求，connectionRequestTimeout默认为1分钟
     *
     * @param url               请求url
     * @param connectTime       请求连接超时时间
     * @param socketConnectTime socket连接超时时间
     * @param header            请求头
     * @param param             请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpUrlEncodedPost(String url, int connectTime, int socketConnectTime, Map<String, String> header, Map<String, String> param) throws IOException {
        return sendHttpUrlEncodedPost(url, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT, header, param);
    }

    /**
     * 发送Http-Post 请求
     *
     * @param url                      请求url
     * @param connectTime              请求连接超时时间
     * @param socketConnectTime        socket连接超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @param header                   请求头
     * @param param                    请求参数
     * @return
     * @throws IOException
     */
    public static String sendHttpUrlEncodedPost(String url, int connectTime, int socketConnectTime, int connectionRequestTimeout, Map<String, String> header, Map<String, String> param) throws IOException {
        String body = null;
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        //装填参数
        List<NameValuePair> nvps = new ArrayList<>();

        if (param != null) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHAR_SET));
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader(CONTENT_TYPE, CONTENT_VALUE_FORM);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHAR_SET);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    /**
     * 发送Http-Json POST请求, connectionRequestTimeout默认为1分钟
     *
     * @param url               请求url
     * @param connectTime       请求连接超时时间
     * @param socketConnectTime socket连接超时时间
     * @param header            请求头
     * @param param             请求参数
     * @return
     * @throws IOException
     */
    public static String sendJsonPost(String url, int connectTime, int socketConnectTime, Map<String, String> header, String param) throws IOException {
        return sendJsonPost(url, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT, header, param);
    }

    /**
     * 发送Http-Json POST请求
     *
     * @param url                      请求url
     * @param connectTime              请求连接超时时间
     * @param socketConnectTime        socket连接超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @param header                   请求头
     * @param param                    请求参数
     * @return
     * @throws IOException
     */
    public static String sendJsonPost(String url, int connectTime, int socketConnectTime, int connectionRequestTimeout, Map<String, String> header, String param) throws IOException {
        String body = null;
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        StringEntity stringEntity = new StringEntity(param, CHAR_SET);
        stringEntity.setContentEncoding(CHAR_SET);
        stringEntity.setContentType(CONTENT_VALUE_JSON);
        httpPost.setEntity(stringEntity);
        //设置header信息
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHAR_SET);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    /**
     * 发送Http-Json Put请求, connectionRequestTimeout默认为1分钟
     *
     * @param url               请求url
     * @param connectTime       请求连接超时时间
     * @param socketConnectTime socket连接超时时间
     * @param header            请求头
     * @param param             请求参数
     * @return
     * @throws IOException
     */
    public static String sendJsonPut(String url, int connectTime, int socketConnectTime, Map<String, String> header, String param) throws IOException {
        return sendJsonPut(url, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT, header, param);
    }

    /**
     * 发送Http-Json Put请求
     *
     * @param url                      请求url
     * @param connectTime              请求连接超时时间
     * @param socketConnectTime        socket连接超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @param header                   请求头
     * @param param                    请求参数
     * @return
     * @throws IOException
     */
    public static String sendJsonPut(String url, int connectTime, int socketConnectTime, int connectionRequestTimeout, Map<String, String> header, String param) throws IOException {
        String body = null;
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(requestConfig);

        StringEntity stringEntity = new StringEntity(param, CHAR_SET);
        stringEntity.setContentEncoding(CHAR_SET);
        stringEntity.setContentType(CONTENT_VALUE_JSON);
        httpPut.setEntity(stringEntity);
        //设置header信息
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = HTTP_CLIENT.execute(httpPut);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHAR_SET);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    /**
     * url参数拼接
     *
     * @param url    请求url
     * @param params 请求参数
     * @return
     * @throws IOException
     */
    public static String urlParamJoint(String url, Map<String, String> params) throws IOException {
        //装填参数
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> pairs = new ArrayList<>(
                    params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
            url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHAR_SET));
        }
        return url;

    }

    public static final ContentType TEXT_PLAIN_UTF_8 = ContentType.create("text/plain", Consts.UTF_8);

    /**
     * 上传文件， connectionRequestTimeout默认为1分钟
     *
     * @param url               上传地址
     * @param param             上传参数
     * @param name              上传名称
     * @param file              上传文件体
     * @param connectTime       建连超时时间
     * @param socketConnectTime socket超时时间
     * @return
     * @throws IOException
     */
    public static String sendFileUpload(String url, Map<String, String> param, String name, Object file, int connectTime, int socketConnectTime) throws IOException {
        return sendFileUpload(url, param, name, file, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT);
    }

    /**
     * 上传文件
     *
     * @param url                      上传地址
     * @param param                    上传参数
     * @param name                     上传名称
     * @param file                     上传文件体
     * @param connectTime              建连超时时间
     * @param socketConnectTime        socket超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @return
     * @throws IOException
     */
    public static String sendFileUpload(String url, Map<String, String> param, String name, Object file, int connectTime, int socketConnectTime, int connectionRequestTimeout) throws IOException {
        String body = null;
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        httpPost.setConfig(requestConfig);
        //创建待上传的文件
        MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
        if (file instanceof byte[]) {
            mEntityBuilder.addBinaryBody(name, (byte[]) file, ContentType.MULTIPART_FORM_DATA, param.get("filename"));
        } else if (file instanceof InputStream) {
            mEntityBuilder.addBinaryBody(name, (InputStream) file, ContentType.MULTIPART_FORM_DATA, param.get("filename"));
        }

        //对请求的表单域进行填充
        param.forEach((key, value) -> mEntityBuilder.addTextBody(key, value, TEXT_PLAIN_UTF_8));

        httpPost.setEntity(mEntityBuilder.build());
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, CHAR_SET);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    /**
     * 下载文件， connectionRequestTimeout默认为1分钟
     *
     * @param url               下载地址
     * @param param             参数
     * @param connectTime       建连超时时间
     * @param socketConnectTime socket超时时间
     * @return
     * @throws IOException
     */
    public static byte[] sendFileDownload(String url, Map<String, String> param, int connectTime, int socketConnectTime) throws IOException {
        return sendFileDownload(url, param, connectTime, socketConnectTime, DEFAULT_CONNECTION_REQUEST_TIMEOUT);
    }

    /**
     * 下载文件
     *
     * @param url                      下载地址
     * @param param                    参数
     * @param connectTime              建连超时时间
     * @param socketConnectTime        socket超时时间
     * @param connectionRequestTimeout 连接池拿连接超时时间
     * @return
     * @throws IOException
     */
    public static byte[] sendFileDownload(String url, Map<String, String> param, int connectTime, int socketConnectTime, int connectionRequestTimeout) throws IOException {
        RequestConfig requestConfig = getRequestConfig(connectTime, socketConnectTime, connectionRequestTimeout);
        url = urlParamJoint(url, param);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        //发送文件下载请求
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();

        try {
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                return inputStreamToByte(inputStream);
            }
        } finally {
            EntityUtils.consume(httpEntity);
            response.close();
        }
        logger.warn("url={}文件下载失败，响应httpEntity为null", url);
        return null;
    }


    private static byte[] inputStreamToByte(InputStream inputStream) throws IOException {

        if (inputStream != null) {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            //buff用于存放循环读取的临时数据
            byte[] buff = new byte[1024];
            int len = -1;
            try {
                while ((len = inputStream.read(buff, 0, buff.length)) != -1) {
                    swapStream.write(buff, 0, len);
                    swapStream.flush();
                }
                return swapStream.toByteArray();
            } finally {
                swapStream.close();
                inputStream.close();
            }
        } else {
            return null;
        }
    }

}
