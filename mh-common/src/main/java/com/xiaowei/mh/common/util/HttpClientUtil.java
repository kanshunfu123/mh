package com.xiaowei.mh.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*******************************************************************
 * jumeiti-app-server - com.jumeiti.web.utils.https
 *
 * 类描述：  
 *
 *
 *     ___       ___       ___       ___       ___       ___
 *    /\  \     /\  \     /\__\     /\__\     /\  \     /\__\
 *    \:\  \   /::\  \   /:| _|_   |::L__L   _\:\  \   /:| _|_
 *    /::\__\ /::\:\__\ /::|/\__\ /::::\__\ /\/::\__\ /::|/\__\
 *   /:/\/__/ \/\::/  / \/|::/  / \;::;/__/ \::/\/__/ \/|::/  /
 *   \/__/      /:/  /    |:/  /   |::|__|   \:\__\     |:/  /
 *             \/__/     \/__/     \/__/     \/__/     \/__/
 *
 *
 *
 *                _ _             
 *               (_|_)            
 *  __      _____ _ _ _   _ _ __  
 *  \ \ /\ / / _ \ | | | | | '_ \ 
 *   \ V  V /  __/ | | |_| | | | |
 *    \_/\_/ \___|_| |\__,_|_| |_|
 *                _/ |            
 *               |__/   
 *
 *
 * @version ${VERSION}
 *
 *
 * Version    Date       ModifiedBy                 Content  
 * -------- ---------    ----------         ------------------------  
 * ${VERSION}      2018/7/1     weijun
 *
 *******************************************************************
 */
@Slf4j
public class HttpClientUtil {

    /**
     * 向目标地址 post json 参数
     *
     * @param url
     * @param json
     * @return
     */
    public static String doJsonPost(String url, String json) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(json));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity, "UTF-8");
            response.close();
            httpClient.close();
            return responseContent;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public static String doPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url     链接地址
     * @param charset 字符编码，若为null则默认utf-8
     * @return
     */
    public static String doGet(String url, String charset) {
        if (null == charset) {
            charset = "utf-8";
        }
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;

        try {
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);

            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

}
