package com.healthcare.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @PostMapping("/1d")
    public static String getHtmlContent(@RequestParam("city") String city) throws UnsupportedEncodingException {
        String httpUrl = "https://geoapi.qweather.com/v2/city/lookup?key=48482c0cf87044ab81d81276da64ee92&location="+ URLEncoder.encode(city,"UTF-8");
        String res=new String();
        try {
            URL url = new URL(httpUrl);
            HttpsURLConnection connection =  (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            GZIPInputStream gzipInputStream =new GZIPInputStream(is);
            String line;
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                res=res+line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    @PostMapping("/3d")
    public static String getHtmlContent2(@RequestParam("cityCode") String cityCode){
        String httpUrl = "https://devapi.qweather.com/v7/weather/3d?key=48482c0cf87044ab81d81276da64ee92&location="+cityCode;
        String res=new String();
        try {
            URL url = new URL(httpUrl);
            HttpsURLConnection connection =  (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            GZIPInputStream gzipInputStream =new GZIPInputStream(is);
            String line;
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                res+=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
