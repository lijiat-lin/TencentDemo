package com.example.tencent;

import com.example.tencent.util.HttpUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TencentApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void httpURLConnectionTest() throws Exception{
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwf1fcde2ff5839822&corpsecret=ek0HShB5nB7_Y2Xj0dd0rXu67VGxhT9kbJ9YEs6kc2Q";
        String response = HttpUtils.sendRequestHttpUrl(url, "GET");
        JSONObject jsonObject = new JSONObject(response);
    }

    @Test
    void httpClientGetTest() throws Exception{
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwf1fcde2ff5839822&corpsecret=ek0HShB5nB7_Y2Xj0dd0rXu67VGxhT9kbJ9YEs6kc2Q";
        String get = HttpUtils.sendGet(url);
        System.out.println(get);
    }
}
