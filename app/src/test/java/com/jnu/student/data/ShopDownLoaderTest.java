package com.jnu.student.data;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ShopDownLoaderTest {

    // 调用测试方法前执行setUp()
    @Before
    public void setUp() throws Exception {
    }

    // 执行完测试方法后执行tearDown()
    @After
    public void tearDown() throws Exception {
    }

    // 测试方法
    @Test
    public void download() {
        // 从网上下载数据并断言是否下载内容正确
        ShopDownLoader shopDownLoader = new ShopDownLoader();
        String data = shopDownLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore2023.json");
        Log.i("content",data);
        Assert.assertTrue(data.contains("\"name\": \"明珠商\""));
        Assert.assertTrue(data.contains("\"latitude\": \"22.251953\""));
        Assert.assertTrue(data.contains("\"longitude\": \"113.526421\""));

    }

    @Test
    public void parsonJson() {
        String content="{\n" +
                "    \"shops\": [\n" +
                "        {\n" +
                "            \"name\": \"暨珠海\",\n" +
                "            \"latitude\": \"22.255925\",\n" +
                "            \"longitude\": \"113.541112\",\n" +
                "            \"memo\": \"暨南大学珠海校区\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"沃尔玛\",\n" +
                "            \"latitude\": \"22.261365\",\n" +
                "            \"longitude\": \"113.532989\",\n" +
                "            \"memo\": \"沃尔玛(前山店)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"明珠商\",\n" +
                "            \"latitude\": \"22.251953\",\n" +
                "            \"longitude\": \"113.526421\",\n" +
                "            \"memo\": \"珠海二城广场\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ShopDownLoader shopDownLoader = new ShopDownLoader();
        ArrayList<ShopLocation> shopLocations = shopDownLoader.parsonJson(content);
        Assert.assertTrue(shopLocations.size()==3);
        Assert.assertTrue(shopLocations.get(2).getName().equals("明珠商"));


    }
}