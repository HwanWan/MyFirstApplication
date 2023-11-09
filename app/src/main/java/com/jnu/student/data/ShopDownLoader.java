package com.jnu.student.data;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopDownLoader {

    /**
     * 从网络获取数据
     * */
    public String download(String url_) {
        String responseData=null;
        try {
            // 创建URL对象
            URL apiUrl = new URL(url_);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            // 设置请求方法
            connection.setRequestMethod("GET");
            // 发起请求
            int responseCode = connection.getResponseCode();
            // 检查响应码
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应数据
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // 处理响应数据
                responseData = response.toString();
                System.out.println(responseData);
            } else {
                Log.e("Error: ", String.valueOf(responseCode));
            }
            // 关闭连接
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;

    }

    /**
     * 解析JSON数据
     * */
    public ArrayList<ShopLocation> parsonJson(String content) {
        ArrayList<ShopLocation> locations=new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(content);
            JSONArray shopsArray = jsonObject.getJSONArray("shops");
            for (int i = 0; i < shopsArray.length(); i++) {
                JSONObject shopObject = shopsArray.getJSONObject(i);

                String name = shopObject.getString("name");
                Double latitude = Double.parseDouble(shopObject.getString("latitude"));
                Double longitude = Double.parseDouble(shopObject.getString("longitude"));
                String memo = shopObject.getString("memo");
                locations.add(new ShopLocation(name,latitude,longitude,memo));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        System.out.println(locations);
        return locations;
    }
}
