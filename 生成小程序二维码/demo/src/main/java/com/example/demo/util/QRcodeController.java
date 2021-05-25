package com.example.demo.util;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;



@Controller
public class QRcodeController {

    /**
     * 详情看官方文档 https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/qr-code.html
     * 此处以官方接口B为例
     * 生成小程序码，可接受页面参数较短，生成个数不受限
     *
     * param :参数 例如：123    page：需要跳转的页面地址 例如：pages/index
     */
    @RequestMapping("/getCode")
    public void smallProgramCode(String param, String page, HttpServletResponse response) {
        OutputStream stream = null;
        try {
            //获取AccessToken
            String accessToken = getAccessToken();
            //设置响应类型
            response.setContentType("image/png");
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            //组装参数
            Map<String, Object> paraMap = new HashMap<>();
            //二维码携带参数 不超过32位 参数类型必须是字符串
            paraMap.put("scene", param);
            //二维码跳转页面
            paraMap.put("page", page);
            //二维码的宽度
            paraMap.put("width", 450);
            //自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
            paraMap.put("auto_color", false);
            //是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
            paraMap.put("is_hyaline", false);
            //执行post 获取数据流
            byte[] result = HttpClientUtils.doImgPost(url, paraMap);
            //输出图片到页面
            response.setContentType("image/jpg");
            stream = response.getOutputStream();
            stream.write(result);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取ACCESS_TOKEN
     * 官方文档地址:https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/access-token/auth.getAccessToken.html
     * <p>
     * 需要正确的 appid  和 secret  查看自己的微信开放平台
     */
    public String getAccessToken() {
        String appid = "wx5esdfsdfe222f5ee3f";
        String appSecret = "c91207f95a6234dd15f9051b0cd";
        //获取微信ACCESS_TOKEN 的Url
        String accent_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String url = accent_token_url.replace("APPID", appid).replace("APPSECRET", appSecret);
        //发送请求
        String result = HttpClientUtils.doGet(url);
        Map<String, Object> resultMap = (Map<String, Object>) JsonUtil.jsonToMap(result);
        System.out.println("access_token------>" + resultMap.get("access_token").toString());
        return resultMap.get("access_token").toString();
    }


}
