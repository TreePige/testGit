package com.rongdu.eloan.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lsk on 2015/12/16.
 */
public class HttpUtil {

    public static String doGet(String url){
        return send(url,null,false,"utf8");
    }
    public static String
    doPost(String url, Map<String, String> params){
        return send(url,params,true,"utf8");
    }

    public static String send(String url, Map<String, String> params,boolean post, String readEncode){
        InputStream input=null;
        try {
            URL realUrl=new URL(url);
            URLConnection connection=realUrl.openConnection();
            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding","gzip,deflate,sdch");
            connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36 SE 2.X MetaSr 1.0");
//            connection.setRequestProperty("Cookie","JSESSIONID=32754410A9908881317F32FE3FA84CB3; j_username=; j_password=");
//            connection.setRequestProperty("Cache-Control","max-age=0");

            if(post){
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                ((HttpURLConnection)connection).setRequestMethod("POST");

                if(params!=null && params.size()>0){
                    StringBuilder sb=new StringBuilder();
                    for (String name : params.keySet()) {
                        String value=params.get(name);
                        sb.append(name+"="+URLEncoder.encode(value,"utf8")+"&");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    System.out.println(sb);
                    OutputStream out=connection.getOutputStream();
                    out.write(sb.toString().getBytes("utf8"));
                }
            }



            Map<String,List<String>> headeers=connection.getHeaderFields();
//            for (String name : headeers.keySet()) {
//                System.out.println(name);
//                System.out.println(headeers.get(name));
//                System.out.println();
//            }
            input=connection.getInputStream();
            Scanner scanner=new Scanner(input,readEncode);
            scanner.useDelimiter("$");
            String html=scanner.next();
            return html;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
