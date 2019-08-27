package com.couponDigender.comm.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.resp.RespData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;

/**
 * http请求工具类
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * get请求
     *
     * @param methodDesc 请求描述
     * @param url        url
     * @return
     */
    public static RespData get(String methodDesc, String url) {
        String respStr;
        try {

            HttpGet httpGet = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                respStr = EntityUtils.toString(resp.getEntity());
                logger.error("{}请求【{}】成功 --> {}", methodDesc, url, resp);
            } else {
                logger.error("{}请求【{}】异常 --> {}", methodDesc, url, resp);
                throw new RuntimeException("请求异常：" + resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}请求【{}】异常 --> {}", methodDesc, url, e.getMessage());
            throw new RuntimeException("请求异常：" + e.getMessage());
        }
        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功", respStr);
    }

    /**
     * post 请求
     *
     * @param methodDesc
     * @param url
     * @param param
     * @return
     */
    public static RespData post(String methodDesc, String url, JSONObject param) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String result = "";
        try {
            StringEntity s = new StringEntity(param.toString(), "UTF-8");
            s.setContentType("application/json");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setEntity(s);
            HttpResponse resp = client.execute(post);
            if (resp.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                result = EntityUtils.toString(resp.getEntity());
            } else {
                logger.error("{}请求【{}】异常 --> {}", methodDesc, url, resp);
                throw new RuntimeException("请求异常：" + resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}请求【{}】异常 --> {}", methodDesc, url, e.getMessage());
            throw new RuntimeException("请求异常：" + e.getMessage());
        }

        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功", result);
    }


    /**
     * 解析url
     *
     * @param url
     * @return
     */
    private static RespData analysisUrl(String url) {

        String protocol = url.substring(0, url.indexOf(":"));

        url = url.substring(url.indexOf(":"), url.length() - 1);
        url = url.replace("://", "");

        String host = url.substring(0, url.indexOf("/"));

        url = url.substring(url.indexOf("/"), url.length() - 1);

        String servUri = url.substring(0, url.indexOf("?"));

        url = url.substring(url.indexOf("?"), url.length() - 1);

        String param = url;

        JSONObject urlInfo = new JSONObject();
        urlInfo.put("protocol", protocol);
        urlInfo.put("host", host);
        urlInfo.put("servUri", servUri);
        urlInfo.put("param", param);

        return RespData.org(RespCode.SUCCESS.getCode(), "解析URL参数成功", urlInfo);
    }

}
